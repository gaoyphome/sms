package com.bmac.ffan.modular.pos.dao;

/**
 *
 */
public class BusPosFat {
    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
    private Integer id;
    /**
     * POS机厂商编号 100001 迈圈
     */
    private String posVendor;
    /**
     * 公交车唯一编号
     */
    private String busId;
    /**
     * POS机ID;
     */
    private String posId;
    /**
     * SAM ID;
     */
    private String samId;
    /**
     * POS机状态
     */
    private String state;
    /**
     * 固件版本号
     */
    private String firmVersion;
    /**
     * 故障原因
     */
    private String reason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 绑定时间；默认为记录插入时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 心跳包更新时间
     */
    private String heartbeatTime;
    private String lineVersion;
    private String incrBlackVer;
    private String qrBlackVer;
    private String incQrBlackVer;
    private String htBlackVer;
    private String htIncBlackVer;
    private String priVer;
    private String milesVer;
    private String htWhiteVer;
    private String htTwoVer;

    private String lineId;
    private String lineName;
    private String plateNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosVendor() {
        return posVendor;
    }

    public void setPosVendor(String posVendor) {
        this.posVendor = posVendor;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getSamId() {
        return samId;
    }

    public void setSamId(String samId) {
        this.samId = samId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFirmVersion() {
        return firmVersion;
    }

    public void setFirmVersion(String firmVersion) {
        this.firmVersion = firmVersion;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(String heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public String getLineVersion() {
        return lineVersion;
    }

    public void setLineVersion(String lineVersion) {
        this.lineVersion = lineVersion;
    }

    public String getIncrBlackVer() {
        return incrBlackVer;
    }

    public void setIncrBlackVer(String incrBlackVer) {
        this.incrBlackVer = incrBlackVer;
    }

    public String getQrBlackVer() {
        return qrBlackVer;
    }

    public void setQrBlackVer(String qrBlackVer) {
        this.qrBlackVer = qrBlackVer;
    }

    public String getIncQrBlackVer() {
        return incQrBlackVer;
    }

    public void setIncQrBlackVer(String incQrBlackVer) {
        this.incQrBlackVer = incQrBlackVer;
    }

    public String getHtBlackVer() {
        return htBlackVer;
    }

    public void setHtBlackVer(String htBlackVer) {
        this.htBlackVer = htBlackVer;
    }

    public String getHtIncBlackVer() {
        return htIncBlackVer;
    }

    public void setHtIncBlackVer(String htIncBlackVer) {
        this.htIncBlackVer = htIncBlackVer;
    }

    public String getPriVer() {
        return priVer;
    }

    public void setPriVer(String priVer) {
        this.priVer = priVer;
    }

    public String getMilesVer() {
        return milesVer;
    }

    public void setMilesVer(String milesVer) {
        this.milesVer = milesVer;
    }

    public String getHtWhiteVer() {
        return htWhiteVer;
    }

    public void setHtWhiteVer(String htWhiteVer) {
        this.htWhiteVer = htWhiteVer;
    }

    public String getHtTwoVer() {
        return htTwoVer;
    }

    public void setHtTwoVer(String htTwoVer) {
        this.htTwoVer = htTwoVer;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        return "BusPosFat{" +
                "id=" + id +
                ", posVendor='" + posVendor + '\'' +
                ", busId='" + busId + '\'' +
                ", posId='" + posId + '\'' +
                ", samId='" + samId + '\'' +
                ", state='" + state + '\'' +
                ", firmVersion='" + firmVersion + '\'' +
                ", reason='" + reason + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", heartbeatTime='" + heartbeatTime + '\'' +
                ", lineVersion='" + lineVersion + '\'' +
                ", incrBlackVer='" + incrBlackVer + '\'' +
                ", qrBlackVer='" + qrBlackVer + '\'' +
                ", incQrBlackVer='" + incQrBlackVer + '\'' +
                ", htBlackVer='" + htBlackVer + '\'' +
                ", htIncBlackVer='" + htIncBlackVer + '\'' +
                ", priVer='" + priVer + '\'' +
                ", milesVer='" + milesVer + '\'' +
                ", htWhiteVer='" + htWhiteVer + '\'' +
                ", htTwoVer='" + htTwoVer + '\'' +
                ", lineId='" + lineId + '\'' +
                ", lineName='" + lineName + '\'' +
                ", busId='" + busId + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                '}';
    }
}
