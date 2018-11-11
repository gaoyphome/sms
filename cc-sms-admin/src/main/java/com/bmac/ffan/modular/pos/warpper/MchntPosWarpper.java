package com.bmac.ffan.modular.pos.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class MchntPosWarpper extends BaseControllerWarpper {
    public MchntPosWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj =  map.get("updateTime");
        obj = map.get("createTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("mchntType");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("1".equals(obj.toString())) {
                map.put("mchntType", "普通商户");
            }
            if ("2".equals(obj.toString())) {
                map.put("mchntType", "代理商");
            }
            if ("3".equals(obj.toString())) {
                map.put("mchntType", "代结算商");
            }
            if ("4".equals(obj.toString())) {
                map.put("mchntType", "一级商户");
            }
            if ("5".equals(obj.toString())) {
                map.put("mchntType", "二级商户(景点)");
            }
            if ("6".equals(obj.toString())) {
                map.put("mchntType", "门店");
            }
        }
    }
}
