package com.bmac.ffan.modular.record.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.util.Map;

public class QrcodeRideTransWarpper extends BaseControllerWarpper {
    public QrcodeRideTransWarpper(Object obj) {
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
                map.put("stateName","未扣款（单边交易）");
            }else if("1".equals(obj.toString())){
                map.put("stateName","扣款成功");
            }else if("3".equals(obj.toString())){
                map.put("stateName","故障终端末扣款");
            }else if("6".equals(obj.toString())){
                map.put("stateName","扣款失败");
            }else if("7".equals(obj.toString())){
                map.put("stateName","补扣款成功");
            }else if("8".equals(obj.toString())){
                map.put("stateName","扣费超时");
            }
        }

        obj = map.get("transState");
        if(ToolUtil.isNotEmpty(obj)){
            if("1".equals(obj.toString())){
                map.put("transStateName","系统自动匹配");
            }else if("2".equals(obj.toString())){
                map.put("transStateName","未处理单边交易");
            }else if("3".equals(obj.toString())){
                map.put("transStateName","人工补单");
            }else if("4".equals(obj.toString())){
                map.put("transStateName","系统自动补单");
            }else if("5".equals(obj.toString())){
                map.put("transStateName","未处理故障车超时");
            }else if("9".equals(obj.toString())){
                map.put("transStateName","故障车作废交易");
            }
        }

//        obj = map.get("tradeStatus");
//        if(ToolUtil.isNotEmpty(obj)){
//            if("0".equals(obj.toString())){
//                map.put("tradeStatus","正常交易");
//            }else if("1".equals(obj.toString())){
//                map.put("tradeStatus","人工补票");
//            }else if("2".equals(obj.toString())){
//                map.put("tradeStatus","强制扣款");
//            }
//        }

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
