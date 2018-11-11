package com.bmac.ffan.modular.basebusiness.ratezf;

/**
 * @Author: Buff
 * @Description:Z2文件记录Bean
 * @Date: Created in 2017/12/20 20:26
 */
public class RateRecordZ2 {
    private String physicType;
    private String logicType;
    private String cardAttr;
    private String costMode;
    private String minLimitOfCardMoney;
    private String overdrawMax;
    private String maxLimitOfCardMoney;
    private String maxAmount;
    private String inRate;
    private String inAmount;
    private String outRate;
    private String outAmount;
    private String reserved;

    public String getPhysicType() {
        return physicType;
    }

    public void setPhysicType(String physicType) {
        this.physicType = physicType;
    }

    public String getLogicType() {
        return logicType;
    }

    public void setLogicType(String logicType) {
        this.logicType = logicType;
    }

    public String getCardAttr() {
        return cardAttr;
    }

    public void setCardAttr(String cardAttr) {
        this.cardAttr = cardAttr;
    }

    public String getCostMode() {
        return costMode;
    }

    public void setCostMode(String costMode) {
        this.costMode = costMode;
    }

    public String getMinLimitOfCardMoney() {
        return minLimitOfCardMoney;
    }

    public void setMinLimitOfCardMoney(String minLimitOfCardMoney) {
        this.minLimitOfCardMoney = minLimitOfCardMoney;
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
        return "RateRecordZ2{" +
                "physicType='" + physicType + '\'' +
                ", logicType='" + logicType + '\'' +
                ", cardAttr='" + cardAttr + '\'' +
                ", costMode='" + costMode + '\'' +
                ", minLimitOfCardMoney='" + minLimitOfCardMoney + '\'' +
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
