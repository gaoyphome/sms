package com.bmac.ffan.modular.record.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/3/15 10:40
 */
public class QrcodeRideTransErrorWarpper extends BaseControllerWarpper {
    public QrcodeRideTransErrorWarpper(Object obj) {
        super(obj);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        Object obj =  map.get("markTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("markTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
        obj =  map.get("downTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("downTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
        obj = map.get("createTime");
        if (ToolUtil.isNotEmpty(obj)) {
            map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
        }
        obj = map.get("transState");
        if(ToolUtil.isNotEmpty(obj)){
            if("1".equals(obj.toString())){
                map.put("transState","系统自动匹配");
            }else if("2".equals(obj.toString())){
                map.put("transState","未处理单边交易");
            }else if("3".equals(obj.toString())){
                map.put("transState","人工补单");
            }else if("4".equals(obj.toString())){
                map.put("transState","系统自动补单");
            }else if("5".equals(obj.toString())){
                map.put("transState","未处理故障车超时");
            }else if("9".equals(obj.toString())){
                map.put("transState","故障车作废交易");
            }
        }

        obj = map.get("errType");
        if(ToolUtil.isNotEmpty(obj)) {
            if ("1001".equals(obj.toString())) {
                map.put("errType", "通迅异常");
            } else if ("9001".equals(obj.toString())) {
                map.put("errType", "--");
            } else if ("0105".equals(obj.toString())) {
                map.put("errType", "支付平台内部超时");
            }
        }
    }
}
