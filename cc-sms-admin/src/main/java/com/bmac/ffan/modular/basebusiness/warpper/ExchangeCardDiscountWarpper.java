package com.bmac.ffan.modular.basebusiness.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

import java.text.NumberFormat;
import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/21 17:22
 */
public class ExchangeCardDiscountWarpper extends BaseControllerWarpper {
    public ExchangeCardDiscountWarpper(Object obj) {
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

        obj = map.get("cardType");
        if(ToolUtil.isNotEmpty(obj)){
            if("0".equals(obj.toString())){
                map.put("cardType","未知 (00)");
            }else if("1".equals(obj.toString())){
                map.put("cardType","非记名普通储值卡 (01)");
            }else if("2".equals(obj.toString())){
                map.put("cardType","公交管理卡 (02)");
            }else if("3".equals(obj.toString())){
                map.put("cardType","记名普通储值卡 (03)");
            }else if("4".equals(obj.toString())){
                map.put("cardType","定值卡 (04)");
            }else if("5".equals(obj.toString())){
                map.put("cardType","福利卡 (05)");
            }else if("6".equals(obj.toString())){
                map.put("cardType","纪念卡 (06)");
            }else if("7".equals(obj.toString())){
                map.put("cardType","企业员工卡 (07)");
            }else if("10".equals(obj.toString())){
                map.put("cardType","老年人卡 (10)");
            }else if("11".equals(obj.toString())){
                map.put("cardType","测试卡 (11)");
            }else if("12".equals(obj.toString())){
                map.put("cardType","学生卡1 (12)");
            }else if("13".equals(obj.toString())){
                map.put("cardType","学生卡2 (13)");
            }else if("14".equals(obj.toString())){
                map.put("cardType","学生卡3 (14)");
            }else if("20".equals(obj.toString())){
                map.put("cardType","地铁计次卡 (20)");
            }else if("22".equals(obj.toString())){
                map.put("cardType","预留 (22)");
            }else if("24".equals(obj.toString())){
                map.put("cardType","车站工作卡 (24)");
            }else if("40".equals(obj.toString())){
                map.put("cardType","出租司机卡 (40)");
            }else if("51".equals(obj.toString())){
                map.put("cardType","残疾人卡 (51)");
            }else if("52".equals(obj.toString())){
                map.put("cardType","见义勇为卡 (52)");
            }else if("53".equals(obj.toString())){
                map.put("cardType","公交员工卡 (53)");
            }else if("4".equals(obj.toString())){
                map.put("cardType","地铁员工卡 (54)");
            }
        }

        NumberFormat nf = NumberFormat.getInstance();
        obj = map.get("maxConsume");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("maxConsume", nf.format(Integer.parseInt(obj.toString())/100d)+"元");
        }

        obj = map.get("creditBalance");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("creditBalance", nf.format(Integer.parseInt(obj.toString())/100d)+"元");
        }

        obj = map.get("minBalance");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("minBalance", nf.format(Integer.parseInt(obj.toString())/100d)+"元");
        }

        obj = map.get("maxBalance");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("maxBalance", nf.format(Integer.parseInt(obj.toString())/100d)+"元");
        }

        obj = map.get("inRate");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("inRate", nf.format(Integer.parseInt(obj.toString())/10d)+"折");
        }

        obj = map.get("inLimit");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("inLimit", nf.format(Integer.parseInt(obj.toString())/100d)+"元");
        }

        obj = map.get("outRate");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("outRate", nf.format(Integer.parseInt(obj.toString())/10d)+"折");
        }

        obj = map.get("outLimit");
        if(ToolUtil.isNotEmpty(obj)){
            map.put("outLimit", nf.format(Integer.parseInt(obj.toString())/100d)+"元");
        }
    }
}
