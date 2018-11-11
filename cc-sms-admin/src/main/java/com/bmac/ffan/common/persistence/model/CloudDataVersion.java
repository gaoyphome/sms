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
 * @since 2017-11-24
 */
@TableName("cloud_data_version")
public class CloudDataVersion extends Model<CloudDataVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商户信息版本
     */
	@TableField("merchant_version")
	private Integer merchantVersion;
    /**
     * 运营参数版本
     */
	@TableField("operate_param_version")
	private Integer operateParamVersion;
    /**
     * 可用卡类型版本号
     */
	@TableField("cardtype_version")
	private Integer cardtypeVersion;
    /**
     * 黑名单版本号
     */
	@TableField("blacklist_version")
	private Integer blacklistVersion;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMerchantVersion() {
		return merchantVersion;
	}

	public void setMerchantVersion(Integer merchantVersion) {
		this.merchantVersion = merchantVersion;
	}

	public Integer getOperateParamVersion() {
		return operateParamVersion;
	}

	public void setOperateParamVersion(Integer operateParamVersion) {
		this.operateParamVersion = operateParamVersion;
	}

	public Integer getCardtypeVersion() {
		return cardtypeVersion;
	}

	public void setCardtypeVersion(Integer cardtypeVersion) {
		this.cardtypeVersion = cardtypeVersion;
	}

	public Integer getBlacklistVersion() {
		return blacklistVersion;
	}

	public void setBlacklistVersion(Integer blacklistVersion) {
		this.blacklistVersion = blacklistVersion;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CloudDataVersion{" +
			"id=" + id +
			", merchantVersion=" + merchantVersion +
			", operateParamVersion=" + operateParamVersion +
			", cardtypeVersion=" + cardtypeVersion +
			", blacklistVersion=" + blacklistVersion +
			"}";
	}
}
