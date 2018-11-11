package com.bmac.ffan.modular.qrcode.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class TokenInfoWarpper extends BaseControllerWarpper {
    public TokenInfoWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj = map.get("createTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("updateTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("updateTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("expireType");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("0".equals(obj.toString())) {
                map.put("expireType", "过期失效");
            }
            if ("1".equals(obj.toString())) {
                map.put("expireType", "新Token替换");
            }
        }

        obj = map.get("state");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("0".equals(obj.toString())) {
                map.put("state", "未使用");
            }
            if ("1".equals(obj.toString())) {
                map.put("state", "已使用");
            }

            if ("2".equals(obj.toString())) {
                map.put("state", "失效");
            }

            if ("3".equals(obj.toString())) {
                map.put("state", "黑名单");
            }
        }

        obj = map.get("tokenType");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("0".equals(obj.toString())) {
                map.put("tokenType", "条形码");
            }
            if ("1".equals(obj.toString())) {
                map.put("tokenType", "二维码");
            }
            if ("2".equals(obj.toString())) {
                map.put("tokenType", "套票交易");
            }
        }
    }
}
