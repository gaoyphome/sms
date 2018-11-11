package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * [二维码黑名单] 
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_qrcode_black")
public class CcQrcodeBlack extends Model<CcQrcodeBlack> {

    private static final long serialVersionUID = 1L;

    /**
     * TokenId
     */
	@TableField("token_id")
	private String tokenId;
    /**
     * 虚拟一卡通卡号
     */
	@TableField("vsir_bmacno")
	private String vsirBmacno;
    /**
     * 原因
     */
	private String reason;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 被拉黑次数
     */
	@TableField("black_count")
	private String blackCount;
    /**
     * 操作人姓名
     */
	@TableField("operate_person")
	private String operatePerson;
    /**
     * 操作人ID
     */
	@TableField("operate_person_id")
	private String operatePersonId;
    /**
     * 下发状态 0 已下发 1 未下发 
     */
	private String state;
    /**
     * 拉黑状态 0 虚拟卡黑名单 1 二维码TokenId黑名单 2虚拟卡黑名单移除 3 TokenId 黑名单移除
     */
	private String flag;
    /**
     * 失效时间
     */
	@TableField("fail_time")
	private String failTime;


	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getVsirBmacno() {
		return vsirBmacno;
	}

	public void setVsirBmacno(String vsirBmacno) {
		this.vsirBmacno = vsirBmacno;
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

	public String getBlackCount() {
		return blackCount;
	}

	public void setBlackCount(String blackCount) {
		this.blackCount = blackCount;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getOperatePersonId() {
		return operatePersonId;
	}

	public void setOperatePersonId(String operatePersonId) {
		this.operatePersonId = operatePersonId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFailTime() {
		return failTime;
	}

	public void setFailTime(String failTime) {
		this.failTime = failTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.tokenId;
	}

	@Override
	public String toString() {
		return "CcQrcodeBlack{" +
			"tokenId=" + tokenId +
			", vsirBmacno=" + vsirBmacno +
			", reason=" + reason +
			", createTime=" + createTime +
			", blackCount=" + blackCount +
			", operatePerson=" + operatePerson +
			", operatePersonId=" + operatePersonId +
			", state=" + state +
			", flag=" + flag +
			", failTime=" + failTime +
			"}";
	}
}
