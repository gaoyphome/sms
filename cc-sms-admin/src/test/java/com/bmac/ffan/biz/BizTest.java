package com.bmac.ffan.biz;

import com.bmac.ffan.base.BaseJunit;
import com.bmac.ffan.modular.biz.service.ITestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 业务测试
 *
 * @author xuzhanfu
 * @date 2017-06-23 23:12
 */
public class BizTest extends BaseJunit {

    @Autowired
    ITestService testService;

    @Test
    public void test() {
        //testService.testGuns();

        testService.testBiz();

        //testService.testAll();
    }
}
