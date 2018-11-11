package com.bmac.ffan.modular.qrcode.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class QrcodeTokenInfoWarpper extends BaseControllerWarpper {
    public QrcodeTokenInfoWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj = map.get("tokenType");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("01".equals(obj.toString())) {
                map.put("tokenType", "条形码");
            }
            if ("02".equals(obj.toString())) {
                map.put("tokenType", "二维码");
            }
        }

        obj = map.get("createTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }

        obj = map.get("expireType");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("0".equals(obj.toString())) {
                map.put("expireType", "用不过期");
            }
            if ("1".equals(obj.toString())) {
                map.put("expireType", "有结束时间");
            }
            if ("2".equals(obj.toString())) {
                map.put("expireType", "有开始和结束时间");
            }
            if ("3".equals(obj.toString())) {
                map.put("expireType", "第一次使用后，结束时间生效");
            }
        }

        obj = map.get("space");
        if (ToolUtil.isNotEmpty(obj)) {

            if ("01".equals(obj.toString())) {
                map.put("space", "SE");
            }
            if ("02".equals(obj.toString())) {
                map.put("space", "HCE");
            }
            if ("03".equals(obj.toString())) {
                map.put("space", "二维码");
            }
            if ("04".equals(obj.toString())) {
                map.put("space", "大商户(COF)");
            }
            if ("05".equals(obj.toString())) {
                map.put("space", "数字钱包");
            }
            if ("06".equals(obj.toString())) {
                map.put("space", "套票");
            }
        }

    }
}
