package com.bmac.ffan.modular.qrcode.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class QrcodeDubiousWarpper extends BaseControllerWarpper {
    public QrcodeDubiousWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj = map.get("reason");
        if (ToolUtil.isNotEmpty(obj)) {
            if ("01".equals(obj.toString())) {
                map.put("reason", "二维码盗用");
            }
            if ("02".equals(obj.toString())) {
                map.put("reason", "多次未补票(系统自动帮补票)");
            }
            if ("03".equals(obj.toString())) {
                map.put("reason", "连续多次超过月补次数");
            }
        }
    }
}
