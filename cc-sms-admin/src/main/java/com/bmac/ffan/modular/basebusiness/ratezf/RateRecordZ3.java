package com.bmac.ffan.modular.basebusiness.ratezf;

/**
 * @Author: Buff
 * @Description:Z3文件记录Bean
 * @Date: Created in 2017/12/20 20:34
 */
public class RateRecordZ3 {
    private String issuerNo;
    private String logicType;
    private String costMode;
    private String overdrawMax;
    private String maxLimitOfCardMoney;
    private String maxAmount;
    private String inRate;
    private String inAmount;
    private String outRate;
    private String outAmount;
    private String reserved;

    public String getIssuerNo() {
        return issuerNo;
    }

    public void setIssuerNo(String issuerNo) {
        this.issuerNo = issuerNo;
    }

    public String getLogicType() {
        return logicType;
    }

    public void setLogicType(String logicType) {
        this.logicType = logicType;
    }

    public String getCostMode() {
        return costMode;
    }

    public void setCostMode(String costMode) {
        this.costMode = costMode;
    }

    public String getOverdrawMax() {
        return overdrawMax;
    }

    public void setOverdrawMax(String overdrawMax) {
        this.overdrawMax = overdrawMax;
    }

    public String getMaxLimitOfCardMoney() {
        return maxLimitOfCardMoney;
    }

    public void setMaxLimitOfCardMoney(String maxLimitOfCardMoney) {
        this.maxLimitOfCardMoney = maxLimitOfCardMoney;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getInRate() {
        return inRate;
    }

    public void setInRate(String inRate) {
        this.inRate = inRate;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public String getOutRate() {
        return outRate;
    }

    public void setOutRate(String outRate) {
        this.outRate = outRate;
    }

    public String getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(String outAmount) {
        this.outAmount = outAmount;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "RateRecordZ3{" +
                "issuerNo='" + issuerNo + '\'' +
                ", logicType='" + logicType + '\'' +
                ", costMode='" + costMode + '\'' +
                ", overdrawMax='" + overdrawMax + '\'' +
                ", maxLimitOfCardMoney='" + maxLimitOfCardMoney + '\'' +
                ", maxAmount='" + maxAmount + '\'' +
                ", inRate='" + inRate + '\'' +
                ", inAmount='" + inAmount + '\'' +
                ", outRate='" + outRate + '\'' +
                ", outAmount='" + outAmount + '\'' +
                ", reserved='" + reserved + '\'' +
                '}';
    }
}
