package com.bmac.ffan.scheme.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.pos.service.IBusPosService;
import com.bmac.ffan.scheme.service.IGetFtpFileService;

/**
 * 定时服务
 * @author xiongrl
 *
 */
@Component
public class JobServiceSchemeTasks {
	private static Logger logger = LoggerFactory.getLogger(JobServiceSchemeTasks.class);
	
    @Autowired
    private  IGetFtpFileService  getFtpFileService;
    @Autowired
    private IBusPosService busPosService;
    /**
     * POS未通讯时间间隔秒
     */
    @Value("${scheme.posconfig.posTimeoutIntervalTime}")
    private int posTimeoutIntervalTime;
    
    /**
     * 解析中心文件
     */
	@Scheduled(cron = "${scheme.job.timingParseCenterFile}") 
	public void parseCenterFile() {
		getFtpFileService.getFileFromFtpServer();
	}

	/**
	 * 更新未通讯的终端设备
	 */
	@Scheduled(cron = "${scheme.job.timingUpdateNonCommunicationEquipment}")
	public void updateNonCommunicationEquipment() {
		 //更新长时间未通讯的POS
		 logger.info("开始更新长时间未通讯的POS自动任务:"+DateUtil.getTime());
		 long start = System.currentTimeMillis();
		 //获取当前时间-POS通讯间隔最长时间
		 String intervalDateTime = DateUtil.getBeforeSecondTime(posTimeoutIntervalTime,"yyyyMMddHHmmssSSS");
		 busPosService.updateNonCommunicationEquipment(intervalDateTime);
		 long end = System.currentTimeMillis();
		 logger.info("完成更新长时间未通讯的POS自动任务，耗时：" + (end - start) + "毫秒");
	}
}
