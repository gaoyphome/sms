package com.bmac.ffan.modular.biz.service.impl;

import com.bmac.ffan.common.constant.DSEnum;
import com.bmac.ffan.common.persistence.dao.TestMapper;
import com.bmac.ffan.common.persistence.model.Test;
import com.bmac.ffan.core.mutidatasource.annotion.DataSource;
import com.bmac.ffan.modular.biz.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试服务
 *
 * @author xuzhanfu
 * @date 2017-06-23 23:02
 */
@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    TestMapper testMapper;

    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public void testBiz() {
        Test test = testMapper.selectById(1);
        test.setId(22);
        test.insert();
    }


    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_GUNS)
    public void testGuns() {
        Test test = testMapper.selectById(1);
        test.setId(33);
        test.insert();
    }

    @Override
    @Transactional
    public void testAll() {
        testBiz();
        testGuns();
        //int i = 1 / 0;
    }

}
