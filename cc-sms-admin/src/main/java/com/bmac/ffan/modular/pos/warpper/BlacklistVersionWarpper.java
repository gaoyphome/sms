package com.bmac.ffan.modular.pos.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/1/29 17:48
 */
public class BlacklistVersionWarpper extends BaseControllerWarpper {
    public BlacklistVersionWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {

        Object obj = map.get("type");
        if(ToolUtil.isNotEmpty(obj)){
            if("0".equals(obj.toString())){
                map.put("type","所有POS机");
            }else if("1".equals(obj.toString())){
                map.put("type","特定线路");
            }else if("2".equals(obj.toString())){
                map.put("type","特定POS机");
            }
        }
    }
}
