package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * pos黑名单文件表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_pos_file_update")
public class CcPosFileUpdate extends Model<CcPosFileUpdate> {

    private static final long serialVersionUID = 1L;

    /**
     * 设备编号
     */
	private Integer id;
    /**
     * 线路更新的版本
     */
	@TableField("line_version")
	private String lineVersion;
    /**
     * 线路更新的文件id
     */
	private Integer linefile;
    /**
     * 增量实体卡黑名单版本文件id
     */
	@TableField("incr_blacklist_file")
	private Integer incrBlacklistFile;
    /**
     * 二维码黑名单文件id
     */
	@TableField("qr_blacklist_file")
	private Integer qrBlacklistFile;
    /**
     * 二维码增量黑名单文件id
     */
	@TableField("qr_incr_blacklist_file")
	private Integer qrIncrBlacklistFile;
    /**
     * 互通卡黑名单文件id
     */
	@TableField("ht_blacklist_file")
	private Integer htBlacklistFile;
    /**
     * 互通增量黑名单文件id
     */
	@TableField("ht_incr_blacklist_file")
	private Integer htIncrBlacklistFile;
	@TableField("ht_whitelist")
	private Integer htWhitelist;
	@TableField("available_card_file")
	private Integer availableCardFile;
	@TableField("admission_file")
	private Integer admissionFile;
	@TableField("mileage_file")
	private Integer mileageFile;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLineVersion() {
		return lineVersion;
	}

	public void setLineVersion(String lineVersion) {
		this.lineVersion = lineVersion;
	}

	public Integer getLinefile() {
		return linefile;
	}

	public void setLinefile(Integer linefile) {
		this.linefile = linefile;
	}

	public Integer getIncrBlacklistFile() {
		return incrBlacklistFile;
	}

	public void setIncrBlacklistFile(Integer incrBlacklistFile) {
		this.incrBlacklistFile = incrBlacklistFile;
	}

	public Integer getQrBlacklistFile() {
		return qrBlacklistFile;
	}

	public void setQrBlacklistFile(Integer qrBlacklistFile) {
		this.qrBlacklistFile = qrBlacklistFile;
	}

	public Integer getQrIncrBlacklistFile() {
		return qrIncrBlacklistFile;
	}

	public void setQrIncrBlacklistFile(Integer qrIncrBlacklistFile) {
		this.qrIncrBlacklistFile = qrIncrBlacklistFile;
	}

	public Integer getHtBlacklistFile() {
		return htBlacklistFile;
	}

	public void setHtBlacklistFile(Integer htBlacklistFile) {
		this.htBlacklistFile = htBlacklistFile;
	}

	public Integer getHtIncrBlacklistFile() {
		return htIncrBlacklistFile;
	}

	public void setHtIncrBlacklistFile(Integer htIncrBlacklistFile) {
		this.htIncrBlacklistFile = htIncrBlacklistFile;
	}

	public Integer getHtWhitelist() {
		return htWhitelist;
	}

	public void setHtWhitelist(Integer htWhitelist) {
		this.htWhitelist = htWhitelist;
	}

	public Integer getAvailableCardFile() {
		return availableCardFile;
	}

	public void setAvailableCardFile(Integer availableCardFile) {
		this.availableCardFile = availableCardFile;
	}

	public Integer getAdmissionFile() {
		return admissionFile;
	}

	public void setAdmissionFile(Integer admissionFile) {
		this.admissionFile = admissionFile;
	}

	public Integer getMileageFile() {
		return mileageFile;
	}

	public void setMileageFile(Integer mileageFile) {
		this.mileageFile = mileageFile;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcPosFileUpdate{" +
			"id=" + id +
			", lineVersion=" + lineVersion +
			", linefile=" + linefile +
			", incrBlacklistFile=" + incrBlacklistFile +
			", qrBlacklistFile=" + qrBlacklistFile +
			", qrIncrBlacklistFile=" + qrIncrBlacklistFile +
			", htBlacklistFile=" + htBlacklistFile +
			", htIncrBlacklistFile=" + htIncrBlacklistFile +
			", htWhitelist=" + htWhitelist +
			", availableCardFile=" + availableCardFile +
			", admissionFile=" + admissionFile +
			", mileageFile=" + mileageFile +
			"}";
	}
}
