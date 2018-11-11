package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * [云卡二期]车辆信息表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-18
 */
@TableName("cc_bus_info")
public class CcBusInfo extends Model<CcBusInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 所属线路编号
     */
	@TableField("line_id")
	private String lineId;
    /**
     * 车辆唯一编号
     */
	@TableField("bus_id")
	private String busId;
    /**
     * 车牌号
     */
	@TableField("plate_number")
	private String plateNumber;
    /**
     * create_time
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
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
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcBusInfo{" +
			"id=" + id +
			", lineId=" + lineId +
			", busId=" + busId +
			", plateNumber=" + plateNumber +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
