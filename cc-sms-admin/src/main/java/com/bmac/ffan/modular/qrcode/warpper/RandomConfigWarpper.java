package com.bmac.ffan.modular.qrcode.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class RandomConfigWarpper extends BaseControllerWarpper {
    public RandomConfigWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj =  map.get("createTime");

        obj = map.get("updateTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("updateTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
    }
}
