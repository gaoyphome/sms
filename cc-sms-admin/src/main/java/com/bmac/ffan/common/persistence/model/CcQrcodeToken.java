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
 * 虚拟卡表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_qrcode_token")
public class CcQrcodeToken extends Model<CcQrcodeToken> {

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
     * 用户唯一标识
     */
	@TableField("user_id")
	private String userId;
    /**
     * 失效时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * token更新时间
     */
	@TableField("update_time")
	private String updateTime;
	@TableField("card_check_digit")
	private String cardCheckDigit;
	@TableField("third_instid")
	private String thirdInstid;
	@TableField("third_channelid")
	private String thirdChannelid;


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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCardCheckDigit() {
		return cardCheckDigit;
	}

	public void setCardCheckDigit(String cardCheckDigit) {
		this.cardCheckDigit = cardCheckDigit;
	}

	public String getThirdInstid() {
		return thirdInstid;
	}

	public void setThirdInstid(String thirdInstid) {
		this.thirdInstid = thirdInstid;
	}

	public String getThirdChannelid() {
		return thirdChannelid;
	}

	public void setThirdChannelid(String thirdChannelid) {
		this.thirdChannelid = thirdChannelid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcQrcodeToken{" +
			"id=" + id +
			", vsirBmacno=" + vsirBmacno +
			", userId=" + userId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", cardCheckDigit=" + cardCheckDigit +
			", thirdInstid=" + thirdInstid +
			", thirdChannelid=" + thirdChannelid +
			"}";
	}
}
