package com.bmac.ffan;

import com.bmac.ffan.common.constant.Const;
import com.bmac.ffan.core.shiro.ShiroKit;

/**
 * @author gaoypieng
 * @create 2018-11-13 22:19
 */
public class Test {

    @org.junit.Test
    public void test(){
        System.out.println(ShiroKit.md5("2", "ssts3"));
        System.out.println(ShiroKit.md5("2", "cnfcv"));
        System.out.println(ShiroKit.md5("2", "rsj8i"));
        System.out.println(ShiroKit.md5("2", "1kh26"));
        System.out.println(ShiroKit.md5("2", "qkhf8"));
    }
}
