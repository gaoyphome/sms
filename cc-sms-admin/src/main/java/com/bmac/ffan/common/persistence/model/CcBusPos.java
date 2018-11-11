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
 * [云卡二期]POS信息表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_bus_pos")
public class CcBusPos extends Model<CcBusPos> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * POS机厂商编号 100001 迈圈
     */
	@TableField("pos_vendor")
	private String posVendor;
    /**
     * 公交车唯一编号
     */
	@TableField("bus_id")
	private String busId;
    /**
     * POS机ID;
     */
	@TableField("pos_id")
	private String posId;
    /**
     * SAM ID;
     */
	@TableField("sam_id")
	private String samId;
    /**
     * POS机状态
     */
	private String state;
    /**
     * 固件版本号
     */
	@TableField("firm_version")
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
	@TableField("create_time")
	private String createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private String updateTime;
    /**
     * 心跳包更新时间
     */
	@TableField("heartbeat_time")
	private String heartbeatTime;
	@TableField("line_version")
	private String lineVersion;
	@TableField("incr_black_ver")
	private String incrBlackVer;
	@TableField("qr_black_ver")
	private String qrBlackVer;
	@TableField("inc_qr_black_ver")
	private String incQrBlackVer;
	@TableField("ht_black_ver")
	private String htBlackVer;
	@TableField("ht_inc_black_ver")
	private String htIncBlackVer;
	@TableField("pri_ver")
	private String priVer;
	@TableField("miles_ver")
	private String milesVer;
	@TableField("ht_white_ver")
	private String htWhiteVer;
	@TableField("ht_two_ver")
	private String htTwoVer;


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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcBusPos{" +
			"id=" + id +
			", posVendor=" + posVendor +
			", busId=" + busId +
			", posId=" + posId +
			", samId=" + samId +
			", state=" + state +
			", firmVersion=" + firmVersion +
			", reason=" + reason +
			", remark=" + remark +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", heartbeatTime=" + heartbeatTime +
			", lineVersion=" + lineVersion +
			", incrBlackVer=" + incrBlackVer +
			", qrBlackVer=" + qrBlackVer +
			", incQrBlackVer=" + incQrBlackVer +
			", htBlackVer=" + htBlackVer +
			", htIncBlackVer=" + htIncBlackVer +
			", priVer=" + priVer +
			", milesVer=" + milesVer +
			", htWhiteVer=" + htWhiteVer +
			", htTwoVer=" + htTwoVer +
			"}";
	}
}
