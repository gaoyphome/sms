package com.bmac.ffan.modular.basebusiness.ratezf;

/**
 * @Author: Buff
 * @Description:折扣率文件头
 * @Date: Created in 2017/12/20 20:25
 */
public class RateHead {
    private String frameVersion;
    private String frameAmount;
    private String frameNo;
    private String recordAmount;
    private String singleRecordCount;
    private String recordStartPosition;
    private String generateDate;
    private String reciveUnitCode;
    private String paramVerNo;
    private String timeOfActive;
    private String reserved;
    private String crc;

    public String getFrameVersion() {
        return frameVersion;
    }

    public void setFrameVersion(String frameVersion) {
        this.frameVersion = frameVersion;
    }

    public String getFrameAmount() {
        return frameAmount;
    }

    public void setFrameAmount(String frameAmount) {
        this.frameAmount = frameAmount;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public String getRecordAmount() {
        return recordAmount;
    }

    public void setRecordAmount(String recordAmount) {
        this.recordAmount = recordAmount;
    }

    public String getSingleRecordCount() {
        return singleRecordCount;
    }

    public void setSingleRecordCount(String singleRecordCount) {
        this.singleRecordCount = singleRecordCount;
    }

    public String getRecordStartPosition() {
        return recordStartPosition;
    }

    public void setRecordStartPosition(String recordStartPosition) {
        this.recordStartPosition = recordStartPosition;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(String generateDate) {
        this.generateDate = generateDate;
    }

    public String getReciveUnitCode() {
        return reciveUnitCode;
    }

    public void setReciveUnitCode(String reciveUnitCode) {
        this.reciveUnitCode = reciveUnitCode;
    }

    public String getParamVerNo() {
        return paramVerNo;
    }

    public void setParamVerNo(String paramVerNo) {
        this.paramVerNo = paramVerNo;
    }

    public String getTimeOfActive() {
        return timeOfActive;
    }

    public void setTimeOfActive(String timeOfActive) {
        this.timeOfActive = timeOfActive;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    @Override
    public String toString() {
        return "RateHead{" +
                "frameVersion='" + frameVersion + '\'' +
                ", frameAmount='" + frameAmount + '\'' +
                ", frameNo='" + frameNo + '\'' +
                ", recordAmount='" + recordAmount + '\'' +
                ", singleRecordCount='" + singleRecordCount + '\'' +
                ", recordStartPosition='" + recordStartPosition + '\'' +
                ", generateDate='" + generateDate + '\'' +
                ", reciveUnitCode='" + reciveUnitCode + '\'' +
                ", paramVerNo='" + paramVerNo + '\'' +
                ", timeOfActive='" + timeOfActive + '\'' +
                ", reserved='" + reserved + '\'' +
                ", crc='" + crc + '\'' +
                '}';
    }
}
