package com.bmac.ffan.modular.pos.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/26 14:43
 */
public class BusPosVersionWarpper extends BaseControllerWarpper {
    public BusPosVersionWarpper(Object obj) {
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

        obj = map.get("downStartTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("downStartTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("downEndTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("downEndTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("installStartTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("installStartTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("installEndTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("installEndTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("type");
        if(ToolUtil.isNotEmpty(obj)){
            if("1".equals(obj.toString())){
                map.put("type","自动升级");
            }else if("2".equals(obj.toString())){
                map.put("type","手动升级");
            }
        }

        obj = map.get("upgradeType");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("1".equals(obj.toString())) {
                map.put("upgradeType", "所有POS机");
            }
            if ("2".equals(obj.toString())) {
                map.put("upgradeType", "特定线路");
            }
            if ("3".equals(obj.toString())) {
                map.put("upgradeType", "特定POS机");
            }
        }
    }
}
