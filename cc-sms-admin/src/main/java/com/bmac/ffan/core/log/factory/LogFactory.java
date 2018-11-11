package com.bmac.ffan.core.log.factory;

import com.bmac.ffan.common.constant.state.LogSucceed;
import com.bmac.ffan.common.constant.state.LogType;
import com.bmac.ffan.common.persistence.model.CcSysLoginLog;
import com.bmac.ffan.common.persistence.model.CcSysOperationLog;

import java.util.Date;

/**
 * 日志对象创建工厂
 *
 * @author xuzhanfu
 * @date 2016年12月6日 下午9:18:27
 */
public class LogFactory {

    /**
     * 创建操作日志
     *
     * @author xuzhanfu
     * @Date 2017/3/30 18:45
     */
    public static CcSysOperationLog createOperationLog(LogType logType, Integer userId, String bussinessName, String clazzName, String methodName, String msg, LogSucceed succeed) {
        CcSysOperationLog operationLog = new CcSysOperationLog();
        operationLog.setLogtype(logType.getMessage());
        operationLog.setLogname(bussinessName);
        operationLog.setUserid(userId);
        operationLog.setClassname(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreatetime(new Date());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }

    /**
     * 创建登录日志
     *
     * @author xuzhanfu
     * @Date 2017/3/30 18:46
     */
    public static CcSysLoginLog createLoginLog(LogType logType, Integer userId, String msg, String ip) {
        CcSysLoginLog loginLog = new CcSysLoginLog();
        loginLog.setLogname(logType.getMessage());
        loginLog.setUserid(userId);
        loginLog.setCreatetime(new Date());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}
