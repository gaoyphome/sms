package com.bmac.ffan.core.util;

import com.bmac.ffan.config.properties.BmacProperties;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     *
     * @author xuzhanfu
     * @Date 2017/5/23 22:34
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(BmacProperties.class).getKaptchaOpen();
    }
}