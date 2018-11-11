package com.bmac.ffan.modular.system.factory;

import com.bmac.ffan.common.persistence.model.CcSysUser;
import com.bmac.ffan.modular.system.transfer.UserDto;
import org.springframework.beans.BeanUtils;

/**
 * 用户创建工厂
 *
 * @author xuzhanfu
 * @date 2017-05-05 22:43
 */
public class UserFactory {

    public static CcSysUser createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            CcSysUser user = new CcSysUser();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
}
