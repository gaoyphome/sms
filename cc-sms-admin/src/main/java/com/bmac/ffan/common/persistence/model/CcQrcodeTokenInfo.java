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
@TableName("cc_qrcode_token_info")
public class CcQrcodeTokenInfo extends Model<CcQrcodeTokenInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 虚拟一卡通卡号
     */
	@TableField("vsir_bmacno")
	private String vsirBmacno;
    /**
     * token生效时间
     */
	@TableField("start_time")
	private String startTime;
    /**
     * 卡号校验位
     */
	@TableField("card_check_digit")
	private String cardCheckDigit;
    /**
     * token结束时间
     */
	@TableField("end_time")
	private String endTime;
    /**
     * tokenid
     */
	@TableField("token_id")
	private String tokenId;
    /**
     * 虚拟卡类型
     */
	@TableField("card_type")
	private String cardType;
    /**
     * token类型：01条形码，02二维码
     */
	@TableField("token_type")
	private String tokenType;
    /**
     * token版本 条形码18 二维码19
     */
	@TableField("token_version")
	private String tokenVersion;
    /**
     * token密钥id
     */
	@TableField("token_chumid")
	private String tokenChumid;
    /**
     * token密文
     */
	@TableField("token_enc")
	private String tokenEnc;
    /**
     * 脱机交易限额
     */
	@TableField("dislocat_norm")
	private String dislocatNorm;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 鉴别码
     */
	private String identification;
    /**
     * token更新时间
     */
	@TableField("update_time")
	private String updateTime;
    /**
     * 失效方式
     */
	@TableField("expire_type")
	private String expireType;
    /**
     * 失效时间
     */
	@TableField("expire_time")
	private String expireTime;
    /**
     * TOKEN应用场景
     */
	private String space;
    /**
     * 用户手机号
     */
	@TableField("user_phone")
	private String userPhone;
	@TableField("qrcode_sesq")
	private String qrcodeSesq;
	@TableField("bar_sesq")
	private String barSesq;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVsirBmacno() {
		return vsirBmacno;
	}

	public void setVsirBmacno(String vsirBmacno) {
		this.vsirBmacno = vsirBmacno;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCardCheckDigit() {
		return cardCheckDigit;
	}

	public void setCardCheckDigit(String cardCheckDigit) {
		this.cardCheckDigit = cardCheckDigit;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenVersion() {
		return tokenVersion;
	}

	public void setTokenVersion(String tokenVersion) {
		this.tokenVersion = tokenVersion;
	}

	public String getTokenChumid() {
		return tokenChumid;
	}

	public void setTokenChumid(String tokenChumid) {
		this.tokenChumid = tokenChumid;
	}

	public String getTokenEnc() {
		return tokenEnc;
	}

	public void setTokenEnc(String tokenEnc) {
		this.tokenEnc = tokenEnc;
	}

	public String getDislocatNorm() {
		return dislocatNorm;
	}

	public void setDislocatNorm(String dislocatNorm) {
		this.dislocatNorm = dislocatNorm;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getExpireType() {
		return expireType;
	}

	public void setExpireType(String expireType) {
		this.expireType = expireType;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getQrcodeSesq() {
		return qrcodeSesq;
	}

	public void setQrcodeSesq(String qrcodeSesq) {
		this.qrcodeSesq = qrcodeSesq;
	}

	public String getBarSesq() {
		return barSesq;
	}

	public void setBarSesq(String barSesq) {
		this.barSesq = barSesq;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcQrcodeTokenInfo{" +
			"id=" + id +
			", vsirBmacno=" + vsirBmacno +
			", startTime=" + startTime +
			", cardCheckDigit=" + cardCheckDigit +
			", endTime=" + endTime +
			", tokenId=" + tokenId +
			", cardType=" + cardType +
			", tokenType=" + tokenType +
			", tokenVersion=" + tokenVersion +
			", tokenChumid=" + tokenChumid +
			", tokenEnc=" + tokenEnc +
			", dislocatNorm=" + dislocatNorm +
			", createTime=" + createTime +
			", identification=" + identification +
			", updateTime=" + updateTime +
			", expireType=" + expireType +
			", expireTime=" + expireTime +
			", space=" + space +
			", userPhone=" + userPhone +
			", qrcodeSesq=" + qrcodeSesq +
			", barSesq=" + barSesq +
			"}";
	}
}
