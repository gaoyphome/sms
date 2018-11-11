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
 * 
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-18
 */
@TableName("cc_mileage_info")
public class CcMileageInfo extends Model<CcMileageInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("line_id")
	private String lineId;
	@TableField("dir_flag")
	private String dirFlag;
	@TableField("mileage_value")
	private String mileageValue;
	@TableField("create_time")
	private String createTime;
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

	public String getDirFlag() {
		return dirFlag;
	}

	public void setDirFlag(String dirFlag) {
		this.dirFlag = dirFlag;
	}

	public String getMileageValue() {
		return mileageValue;
	}

	public void setMileageValue(String mileageValue) {
		this.mileageValue = mileageValue;
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
		return "CcMileageInfo{" +
			"id=" + id +
			", lineId=" + lineId +
			", dirFlag=" + dirFlag +
			", mileageValue=" + mileageValue +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
