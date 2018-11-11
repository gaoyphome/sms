package com.bmac.ffan.modular.pos.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class PosVersionWarpper extends BaseControllerWarpper {
    public PosVersionWarpper(Object obj) {
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
        obj = map.get("type");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("00".equals(obj.toString())) {
                map.put("type", "自动升级");
            }
            if ("01".equals(obj.toString())) {
                map.put("type", "手动升级");
            }
        }

        obj = map.get("upgradeType");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("1".equals(obj.toString())) {
                map.put("upgradeType", "所有POS机");
            }
            if ("2".equals(obj.toString())) {
                map.put("upgradeType", "商户POS机");
            }
            if ("3".equals(obj.toString())) {
                map.put("upgradeType", "特定POS机");
            }
        }
    }
}
