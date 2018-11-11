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
@TableName("cc_network_mchnt_all")
public class CcNetworkMchntAll extends Model<CcNetworkMchntAll> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商户ID;
     */
	@TableField("mchnt_id")
	private String mchntId;
    /**
     * 商户名
     */
	private String mchntname;
    /**
     * 商户简码
     */
	private String mchntcode;
    /**
     * 商户类型
     */
	@TableField("mchnt_type")
	private String mchntType;
    /**
     * 所属上级商户ID;
     */
	@TableField("father_id")
	private String fatherId;
    /**
     * 淡季时间范围
     */
	@TableField("low_season")
	private String lowSeason;
    /**
     * 淡季票价
     */
	@TableField("low_price")
	private String lowPrice;
    /**
     * 旺季时间范围
     */
	@TableField("mid_season")
	private String midSeason;
    /**
     * 旺季票价
     */
	@TableField("mid_price")
	private String midPrice;
    /**
     * 固定电话
     */
	private String telphone;
    /**
     * Email
     */
	private String email;
    /**
     * 公司联系人
     */
	private String contacts;
    /**
     * 联系人电话
     */
	@TableField("contacts_mobile")
	private String contactsMobile;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMchntId() {
		return mchntId;
	}

	public void setMchntId(String mchntId) {
		this.mchntId = mchntId;
	}

	public String getMchntname() {
		return mchntname;
	}

	public void setMchntname(String mchntname) {
		this.mchntname = mchntname;
	}

	public String getMchntcode() {
		return mchntcode;
	}

	public void setMchntcode(String mchntcode) {
		this.mchntcode = mchntcode;
	}

	public String getMchntType() {
		return mchntType;
	}

	public void setMchntType(String mchntType) {
		this.mchntType = mchntType;
	}

	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getLowSeason() {
		return lowSeason;
	}

	public void setLowSeason(String lowSeason) {
		this.lowSeason = lowSeason;
	}

	public String getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getMidSeason() {
		return midSeason;
	}

	public void setMidSeason(String midSeason) {
		this.midSeason = midSeason;
	}

	public String getMidPrice() {
		return midPrice;
	}

	public void setMidPrice(String midPrice) {
		this.midPrice = midPrice;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsMobile() {
		return contactsMobile;
	}

	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
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
		return "CcNetworkMchntAll{" +
			"id=" + id +
			", mchntId=" + mchntId +
			", mchntname=" + mchntname +
			", mchntcode=" + mchntcode +
			", mchntType=" + mchntType +
			", fatherId=" + fatherId +
			", lowSeason=" + lowSeason +
			", lowPrice=" + lowPrice +
			", midSeason=" + midSeason +
			", midPrice=" + midPrice +
			", telphone=" + telphone +
			", email=" + email +
			", contacts=" + contacts +
			", contactsMobile=" + contactsMobile +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
