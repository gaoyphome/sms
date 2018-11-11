package com.bmac.ffan.scheme.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 文件接收日志表
 * @author xiongrl
 *
 */
@TableName("cc_file_receive")
public class CcFileReceive extends Model<CcFileReceive> {

    private static final long serialVersionUID = 1L;
    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	
    /**
     * 文件名
     */
	@TableField("file_name")
	private String fileName;
    /**
     * 文件名
     */
	@TableField("file_type")
	private String fileType;
    /**
     * 文件大小
     */
	@TableField("file_size")
	private long fileSize;
	/**
     * 文件路径
     */
	@TableField("file_path")
	private String filePath;
    /**
     * MD5
     */
	@TableField("md5_code")
	private String md5Code;
    /**
     * 处理标识：00未处理，01已处理
     */
	@TableField("deal_flag")
	private String deal_flag;
	
    /**
     * 创建时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private String updateTime;
	
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

	public String getDeal_flag() {
		return deal_flag;
	}

	public void setDeal_flag(String deal_flag) {
		this.deal_flag = deal_flag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
