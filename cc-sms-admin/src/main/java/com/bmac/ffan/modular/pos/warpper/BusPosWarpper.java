package com.bmac.ffan.modular.pos.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class BusPosWarpper extends BaseControllerWarpper {
    public BusPosWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj =  map.get("updateTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("updateTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
        obj = map.get("createTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
    }
}
