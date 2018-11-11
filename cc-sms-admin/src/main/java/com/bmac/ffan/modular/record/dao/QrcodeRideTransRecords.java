package com.bmac.ffan.modular.record.dao;

/**
 * @Author: Buff
 * @Description: 乘车交易记录对应的乘车记录
 * @Date: Created in 2018/3/9 14:06
 */
public class QrcodeRideTransRecords {
    private static final long serialVersionUID = 1L;
    /**
     * 平台流水号
     */
    private String transPltSsn;
    /**
     * 乘车记录交易标识
     */
    private String recordTradeTypeFlag;
    /**
     * 刷卡站名
     */
    private String recordMarkStationName;

    /**
     * 刷卡时间
     * @return
     */
    private String recordMarkTime;

    public String getTransPltSsn() {
        return transPltSsn;
    }

    public void setTransPltSsn(String transPltSsn) {
        this.transPltSsn = transPltSsn;
    }

    public String getRecordTradeTypeFlag() {
        return recordTradeTypeFlag;
    }

    public void setRecordTradeTypeFlag(String recordTradeTypeFlag) {
        this.recordTradeTypeFlag = recordTradeTypeFlag;
    }

    public String getRecordMarkStationName() {
        return recordMarkStationName;
    }

    public void setRecordMarkStationName(String recordMarkStationName) {
        this.recordMarkStationName = recordMarkStationName;
    }

    public String getRecordMarkTime() {
        return recordMarkTime;
    }

    public void setRecordMarkTime(String recordMarkTime) {
        this.recordMarkTime = recordMarkTime;
    }
}
