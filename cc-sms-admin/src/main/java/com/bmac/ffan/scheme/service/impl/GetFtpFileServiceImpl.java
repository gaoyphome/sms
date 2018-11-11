package com.bmac.ffan.scheme.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bmac.ffan.common.persistence.dao.CcPosFileBlacklistMapper;
import com.bmac.ffan.common.persistence.dao.CloudDataVersionMapper;
import com.bmac.ffan.common.persistence.model.CcPosFileBlacklist;
import com.bmac.ffan.common.persistence.model.CloudDataVersion;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.util.CRC16;
import com.bmac.ffan.modular.basebusiness.util.StringUtil;
import com.bmac.ffan.scheme.constant.FileConstant;
import com.bmac.ffan.scheme.model.CcFileReceive;
import com.bmac.ffan.scheme.service.IFileReceiveService;
import com.bmac.ffan.scheme.service.IGetFtpFileService;
import com.bmac.ffan.scheme.util.FileDigest;
import com.bmac.ffan.scheme.util.FileUtil;
import com.bmac.ffan.scheme.util.FtpUtil;
import com.bmac.ffan.scheme.util.NumberStringUtil;

@Service
public class GetFtpFileServiceImpl implements IGetFtpFileService {

    private final static Logger logger = LoggerFactory.getLogger(GetFtpFileServiceImpl.class);

    @Value("${scheme.ftp.ftpPath}")
    private String fileFtpPath;

    @Value("${scheme.ftp.ftpServerIp}")
    private String host;
    
    @Value("${scheme.ftp.ftpServerPort}")
    private Integer port;
    
    @Value("${scheme.ftp.ftpServerUser}")
    private String userName;
    
    @Value("${scheme.ftp.ftpServerPassWord}")
    private String passWord;
    
    @Value("${scheme.ftp.localPath}")
    private String localPath;

    @Autowired
    private  CcPosFileBlacklistMapper  posFileBlacklistMapper;
    @Autowired
    private  CloudDataVersionMapper  cloudDataVersionMapper;
	@Autowired
	private IFileReceiveService fileReceiveService;
    
    @Override
	public void getFileFromFtpServer() {
    	long start = System.currentTimeMillis();
    	logger.info("开始解析中心文件自动任务:"+DateUtil.getTime());
    	String dateStr = DateUtil.getDays();
        String fileTypes [] = {"G1","G4","G6","G7"/*,"MP"*/};
        String path = localPath + "/" + dateStr + "/";
        FtpUtil ftpUtil = new FtpUtil("UTF-8");
        logger.info("connect ftp server");
        try{
        	ftpUtil.connect(host, port, userName, passWord);
            List<FTPFile> fileList = ftpUtil.listFiles("");
            if(fileList == null || fileList.size() == 0){
    			logger.info("中心无黑白名单文件待解析！");
    			return;
    		}
            //根据文件类型获取当前文件类型最大版本
    		Map<String, Object> centerFileListVersion = posFileBlacklistMapper.getCenterFileListVersion();
            for (FTPFile ftpFile : fileList) {
            	if(ftpFile.isDirectory()){
            		logger.info("跳过文件夹。。。");
            		return;
            	}
            	String fileName = ftpFile.getName();
				//1.判断文件是否为黑名单文件或者白名单文件
            	String fileType = fileName.substring(fileName.length()-2);
            	if(StringUtil.judgeExistFlag(fileType, fileTypes)){
            		//2.下载文件
            		logger.info("下载" + fileName);
            		//判断path是否存在，如果不存在则新建
            		if(!new File(path).exists()){
            			new File(path).mkdirs();
            		}
            		ftpUtil.download(fileName, new File(path+fileName));
            		CcFileReceive fileReceive = new CcFileReceive();
            		fileReceive.setFileName(fileName);
            		fileReceive.setFileType(fileType);
            		fileReceive.setDeal_flag(FileConstant.FILE_DOWNLOAD);
            		fileReceive.setFilePath(path);
            		fileReceive.setFileSize(ftpFile.getSize());
            		fileReceive.setMd5Code(FileDigest.calculateMD5(path + fileName));
            		fileReceive.setCreateTime(DateUtil.getAllTime());
                	//3.记录下载日志
                	fileReceiveService.insert(fileReceive);
                	//4.删除文件
                	ftpUtil.deleteFile(fileName);
                	logger.info("删除" + fileName);
                	CcPosFileBlacklist posFileBlacklist = matchingCenterFile(fileReceive,centerFileListVersion);
    				posFileBlacklistMapper.insert(posFileBlacklist);
    				fileReceive.setDeal_flag(FileConstant.PARSE_COMPLETION);
    				fileReceive.setUpdateTime(DateUtil.getAllTime());
    				fileReceiveService.updateById(fileReceive);
    				//如果是全局G1黑名单文件，则同时更新云数据版本信息表
    				if(FileConstant.FILE_BLACKLIST.equals(posFileBlacklist.getFilename())){
    					logger.info("更新实体卡全量黑名单版本...");
    					CloudDataVersion cloudDataVersion = new CloudDataVersion();
    					cloudDataVersion.setId(1);
    					cloudDataVersion.setBlacklistVersion(posFileBlacklist.getVersion());
    					cloudDataVersionMapper.updateById(cloudDataVersion);
    				}
            	}
			}
            logger.info("downloadFile successful");
            long end = System.currentTimeMillis();
   		 	logger.info("完成开始解析中心文件自动任务，耗时：" + (end - start) + "毫秒");
        }catch (Exception e){
        	e.printStackTrace();
            logger.error("从FTP中获取文件失败：", e);
        }finally{
        	try {
				ftpUtil.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("FTP关闭连接失败：",e);
			}
            logger.info("disconnect successful");
        }
	}

    /**
     * 匹配中心文件信息
     * @param fileReceive 接收文件对象
     * @param centerFileListVersion 待处理文件版本
     * @return
     */
	private CcPosFileBlacklist matchingCenterFile(CcFileReceive fileReceive,Map<String,Object> centerFileListVersion){
		CcPosFileBlacklist posFileBlacklist = new CcPosFileBlacklist();
		logger.info("开始解析" + fileReceive.getFileName() + "文件...");
		byte[] data = FileUtil.readBinaryFile(fileReceive.getFilePath()+fileReceive.getFileName());
		posFileBlacklist.setFilesize(data.length);
		String content = NumberStringUtil.bytesToHexString(data);
		String crc = CRC16.CRC_16(data);
		posFileBlacklist.setContent(content);
		posFileBlacklist.setCrc16(crc);
		String fileType = fileReceive.getFileType();
		if("G1".equals(fileType)){
			posFileBlacklist.setFilename(FileConstant.FILE_BLACKLIST);
			posFileBlacklist.setVersion(Integer.valueOf(centerFileListVersion.get("g1_version").toString()));
		}else if("G4".equals(fileType)){
			posFileBlacklist.setFilename(FileConstant.FILE_INCR_BLACKLIST);
			posFileBlacklist.setVersion(Integer.valueOf(centerFileListVersion.get("g4_version").toString()));
		}else if("G6".equals(fileType)){
			posFileBlacklist.setFilename(FileConstant.FILE_INCR_HT_BLACKLIST);
			posFileBlacklist.setVersion(Integer.valueOf(centerFileListVersion.get("g6_version").toString()));
		}else if("G7".equals(fileType)){
			posFileBlacklist.setFilename(FileConstant.FILE_HT_BLACKLIST);
			posFileBlacklist.setVersion(Integer.valueOf(centerFileListVersion.get("g7_version").toString()));
		}else if("MP".equals(fileType)){
			posFileBlacklist.setFilename(FileConstant.FILE_HT_WHITE);
			posFileBlacklist.setVersion(Integer.valueOf(centerFileListVersion.get("w1_version").toString()));
		}
		return posFileBlacklist;
	}
}
