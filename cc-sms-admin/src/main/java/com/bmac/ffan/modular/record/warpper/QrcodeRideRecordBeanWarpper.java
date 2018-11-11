package com.bmac.ffan.modular.record.warpper;

import com.bmac.ffan.common.persistence.model.CcQrcodeRideRecord;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/3/8 9:54
 */
public class QrcodeRideRecordBeanWarpper {
    public CcQrcodeRideRecord warpBean(CcQrcodeRideRecord qrcodeRideRecord){
        String state = qrcodeRideRecord.getState();
        if("0".equals(state)){
            qrcodeRideRecord.setState("未扣款（单边交易）");
        }else if("1".equals(state)){
            qrcodeRideRecord.setState("扣款成功");
        }else if("3".equals(state)){
            qrcodeRideRecord.setState("故障终端末扣款");
        }else if("6".equals(state)){
            qrcodeRideRecord.setState("扣款失败");
        }else if("7".equals(state)){
            qrcodeRideRecord.setState("补扣款成功");
        }
        String tradeStatus = qrcodeRideRecord.getTradeStatus();
        if("0".equals(tradeStatus)){
            qrcodeRideRecord.setTradeStatus("正常交易");
        }else if("1".equals(tradeStatus)){
            qrcodeRideRecord.setTradeStatus("人工补票");
        }else if("2".equals(tradeStatus)){
            qrcodeRideRecord.setTradeStatus("强制扣款");
        }

        String tradeTypeFlag = qrcodeRideRecord.getTradeTypeFlag();

        qrcodeRideRecord.setTradeTypeFlag(warptradeTypeFlag(tradeTypeFlag));

        return qrcodeRideRecord;
    }

    public String warptradeTypeFlag(String tradeTypeFlag){
        String tradeType = "";
        if("00".equals(tradeTypeFlag)){
            tradeType = ("默认");
        }else if("8".equals(tradeTypeFlag)){
            tradeType = ("人工补单");
        }else if("9".equals(tradeTypeFlag)){
            tradeType = ("系统自动补单（超时）");
        }else if("10".equals(tradeTypeFlag)){
            tradeType = ("系统自动补单（超次）");
        }
        return  tradeType;
    }
}
