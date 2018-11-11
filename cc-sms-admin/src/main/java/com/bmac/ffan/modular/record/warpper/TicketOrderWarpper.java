package com.bmac.ffan.modular.record.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class TicketOrderWarpper extends BaseControllerWarpper {
    public TicketOrderWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object updateTime =  map.get("updateTime");
        if (ToolUtil.isNotEmpty(updateTime)) {
            map.put("updateTime", DateUtil.stringPattern((String)updateTime, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
        updateTime = map.get("createTime");
        if (ToolUtil.isNotEmpty(updateTime)) {
            map.put("createTime", DateUtil.stringPattern((String)updateTime, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
    }
}
