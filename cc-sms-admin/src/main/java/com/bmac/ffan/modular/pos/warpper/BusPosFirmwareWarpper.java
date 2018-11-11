package com.bmac.ffan.modular.pos.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class BusPosFirmwareWarpper extends BaseControllerWarpper {
    public BusPosFirmwareWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj = map.get("contentType");
        if(ToolUtil.isNotEmpty(obj)){
            if("00".equals(obj.toString())){
                map.put("contentType","固件内容");
            }else if("01".equals(obj.toString())){
                map.put("contentType","下载URL");
            }
        }

        obj = map.get("posVendor");
        if(ToolUtil.isNotEmpty(obj)){
            if("100002".equals(obj.toString())){
                map.put("posVendor","索天");
            }else if("100001".equals(obj.toString())){
                map.put("posVendor","迈圈");
            }
        }
    }
}
