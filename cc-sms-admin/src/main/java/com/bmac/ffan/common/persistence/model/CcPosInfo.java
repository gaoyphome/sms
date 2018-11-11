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
@TableName("cc_pos_info")
public class CcPosInfo extends Model<CcPosInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
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
     * POS机编码
     */
	@TableField("pos_code")
	private String posCode;
    /**
     * POS机描述
     */
	@TableField("pos_desc")
	private String posDesc;
    /**
     * 经度
     */
	private String longitude;
    /**
     * 纬度
     */
	private String latitude;
    /**
     * POS机位置描述
     */
	@TableField("loc_desc")
	private String locDesc;
    /**
     * POS机状态
     */
	private String state;
    /**
     * POS机版本号
     */
	private String version;
    /**
     * 固件版本号
     */
	@TableField("firm_version")
	private String firmVersion;
    /**
     * 黑名单版本号
     */
	@TableField("blacklist_version")
	private String blacklistVersion;
    /**
     * 终端参数版本号
     */
	@TableField("term_version")
	private String termVersion;
    /**
     * 卡类型版本号
     */
	@TableField("cardtype_version")
	private String cardtypeVersion;
    /**
     * 故障原因
     */
	private String reason;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getPosDesc() {
		return posDesc;
	}

	public void setPosDesc(String posDesc) {
		this.posDesc = posDesc;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocDesc() {
		return locDesc;
	}

	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFirmVersion() {
		return firmVersion;
	}

	public void setFirmVersion(String firmVersion) {
		this.firmVersion = firmVersion;
	}

	public String getBlacklistVersion() {
		return blacklistVersion;
	}

	public void setBlacklistVersion(String blacklistVersion) {
		this.blacklistVersion = blacklistVersion;
	}

	public String getTermVersion() {
		return termVersion;
	}

	public void setTermVersion(String termVersion) {
		this.termVersion = termVersion;
	}

	public String getCardtypeVersion() {
		return cardtypeVersion;
	}

	public void setCardtypeVersion(String cardtypeVersion) {
		this.cardtypeVersion = cardtypeVersion;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcPosInfo{" +
			"id=" + id +
			", posId=" + posId +
			", samId=" + samId +
			", posCode=" + posCode +
			", posDesc=" + posDesc +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", locDesc=" + locDesc +
			", state=" + state +
			", version=" + version +
			", firmVersion=" + firmVersion +
			", blacklistVersion=" + blacklistVersion +
			", termVersion=" + termVersion +
			", cardtypeVersion=" + cardtypeVersion +
			", reason=" + reason +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", lineVersion=" + lineVersion +
			", incrBlackVer=" + incrBlackVer +
			", qrBlackVer=" + qrBlackVer +
			", incQrBlackVer=" + incQrBlackVer +
			", htBlackVer=" + htBlackVer +
			", htIncBlackVer=" + htIncBlackVer +
			", priVer=" + priVer +
			", milesVer=" + milesVer +
			", htWhiteVer=" + htWhiteVer +
			"}";
	}
}
