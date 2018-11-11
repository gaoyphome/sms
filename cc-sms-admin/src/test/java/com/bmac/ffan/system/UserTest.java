package com.bmac.ffan.system;

import com.bmac.ffan.base.BaseJunit;
import com.bmac.ffan.common.persistence.dao.CcSysUserMapper;
import com.bmac.ffan.modular.system.dao.UserMgrDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 用户测试
 *
 * @author xuzhanfu
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMgrDao userMgrDao;

    @Resource
    CcSysUserMapper userMapper;

    @Test
    public void userTest() throws Exception {

    }

}
