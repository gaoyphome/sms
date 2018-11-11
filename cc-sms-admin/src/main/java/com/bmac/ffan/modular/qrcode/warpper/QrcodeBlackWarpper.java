package com.bmac.ffan.modular.qrcode.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class QrcodeBlackWarpper extends BaseControllerWarpper {
    public QrcodeBlackWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj =  map.get("createTime");

        obj = map.get("createTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("state");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("0".equals(obj.toString())) {
                map.put("state", "已下发");
            }
            if ("1".equals(obj.toString())) {
                map.put("state", "未下发");
            }
        }

        obj = map.get("flag");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("0".equals(obj.toString())) {
                map.put("flag", "虚拟卡黑名单");
            }
            if ("1".equals(obj.toString())) {
                map.put("flag", "二维码TokenId黑名单");
            }
            if ("1".equals(obj.toString())) {
                map.put("flag", "虚拟卡黑名单移除");
            }
            if ("1".equals(obj.toString())) {
                map.put("flag", "TokenId黑名单移除");
            }
        }
    }
}
