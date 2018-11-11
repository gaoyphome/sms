package com.bmac.ffan.modular.record.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class QrcodeRideRecordWarpper extends BaseControllerWarpper {
    public QrcodeRideRecordWarpper(Object obj) {
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

        obj = map.get("state");
        if(ToolUtil.isNotEmpty(obj)){
            if("0".equals(obj.toString())){
                map.put("state","未扣款（单边交易）");
            }else if("1".equals(obj.toString())){
                map.put("state","扣款成功");
            }else if("6".equals(obj.toString())){
                map.put("state","扣款失败");
            }else if("7".equals(obj.toString())){
                map.put("state","补扣款成功");
            }
        }
        obj = map.get("tradeTypeFlag");
        if(ToolUtil.isNotEmpty(obj)){
            if("0".equals(obj.toString())){
                map.put("tradeTypeFlag","默认");
            }else if("8".equals(obj.toString())){
                map.put("tradeTypeFlag","人工补单");
            }else if("9".equals(obj.toString())){
                map.put("tradeTypeFlag","系统自动补单（超时）");
            }else if("10".equals(obj.toString())){
                map.put("tradeTypeFlag","系统自动补单（超次）");
            }
        }

    }
}
