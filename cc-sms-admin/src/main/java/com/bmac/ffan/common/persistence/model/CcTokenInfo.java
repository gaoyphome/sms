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
@TableName("cc_token_info")
public class CcTokenInfo extends Model<CcTokenInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * TOKEN ID;
     */
	@TableField("token_id")
	private String tokenId;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * TOKEN类型
     */
	@TableField("token_type")
	private String tokenType;
    /**
     * TOKEN生效时间
     */
	@TableField("start_time")
	private String startTime;
    /**
     * TOKEN失效时间
     */
	@TableField("end_time")
	private String endTime;
    /**
     * TOKEN应用场景
     */
	private String space;
    /**
     * TOKEN担保级别
     */
	@TableField("token_level")
	private String tokenLevel;
    /**
     * 套票ID
     */
	@TableField("ticket_id")
	private String ticketId;
    /**
     * 网络支付平台订单号
     */
	@TableField("nt_orderid")
	private String ntOrderid;
    /**
     * 套票类型
     */
	@TableField("ticket_type")
	private String ticketType;
    /**
     * TOKEN附加信息
     */
	@TableField("token_info")
	private String tokenInfo;
    /**
     * token密文
     */
	@TableField("token_enc")
	private String tokenEnc;
    /**
     * 状态
     */
	private String state;
    /**
     * 使用时间
     */
	@TableField("use_time")
	private String useTime;
    /**
     * POS机ID
     */
	@TableField("pos_id")
	private String posId;
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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getTokenLevel() {
		return tokenLevel;
	}

	public void setTokenLevel(String tokenLevel) {
		this.tokenLevel = tokenLevel;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getNtOrderid() {
		return ntOrderid;
	}

	public void setNtOrderid(String ntOrderid) {
		this.ntOrderid = ntOrderid;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getTokenInfo() {
		return tokenInfo;
	}

	public void setTokenInfo(String tokenInfo) {
		this.tokenInfo = tokenInfo;
	}

	public String getTokenEnc() {
		return tokenEnc;
	}

	public void setTokenEnc(String tokenEnc) {
		this.tokenEnc = tokenEnc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
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
		return "CcTokenInfo{" +
			"id=" + id +
			", tokenId=" + tokenId +
			", userId=" + userId +
			", tokenType=" + tokenType +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", space=" + space +
			", tokenLevel=" + tokenLevel +
			", ticketId=" + ticketId +
			", ntOrderid=" + ntOrderid +
			", ticketType=" + ticketType +
			", tokenInfo=" + tokenInfo +
			", tokenEnc=" + tokenEnc +
			", state=" + state +
			", useTime=" + useTime +
			", posId=" + posId +
			", expireType=" + expireType +
			", expireTime=" + expireTime +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
