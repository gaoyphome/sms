package com.bmac.ffan.modular.basebusiness.dao;


/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/29 22:47
 */
public class BusInfoFat {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;
    /**
     * 所属线路编号
     */
    private String lineId;

    /**
     * 线路名称
     */
    private String lineName;
    /**
     * 车辆唯一编号
     */
    private String busId;
    /**
     * 车牌号
     */
    private String plateNumber;
    /**
     * create_time
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    @Override
    public String toString() {
        return "BusInfoFat{" +
                "id=" + id +
                ", lineId='" + lineId + '\'' +
                ", lineName='" + lineName + '\'' +
                ", busId='" + busId + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
