package com.bmac.ffan.modular.system.warpper;

import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.constant.factory.ConstantFactory;
import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author xuzhanfu
 * @date 2017年2月13日 下午10:47:03
 */
public class UserWarpper extends BaseControllerWarpper {

    public UserWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("sex")));
        map.put("roleName", ConstantFactory.me().getRoleName((String) map.get("roleid")));
        map.put("statusName", ConstantFactory.me().getStatusName((Integer) map.get("status")));
        if(SysConstant.DEPTID_ALL_PRIVILEGES.equals(map.get("deptName")) ) {
            map.put("deptName", "北京市政交通一卡通");
        }else {
            map.put("deptName", ConstantFactory.me().getCompanyName((Integer) map.get("deptid")));
        }
    }

}
