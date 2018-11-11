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
@TableName("cc_ticket_info")
public class CcTicketInfo extends Model<CcTicketInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 套票ID;
     */
	@TableField("ticket_id")
	private String ticketId;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 套票名称
     */
	@TableField("ticket_name")
	private String ticketName;
    /**
     * 套票类型
     */
	@TableField("ticket_type")
	private String ticketType;
    /**
     * 套票描述
     */
	@TableField("ticket_desc")
	private String ticketDesc;
    /**
     * 套票绑定的卡号
     */
	private String cardno;
    /**
     * 网络支付平台订单号
     */
	@TableField("nt_orderid")
	private String ntOrderid;
    /**
     * 套票购买日期
     */
	@TableField("sale_date")
	private String saleDate;
    /**
     * 过期类型
     */
	@TableField("expire_type")
	private String expireType;
    /**
     * 使用开始日期
     */
	@TableField("start_date")
	private String startDate;
    /**
     * 使用结束日期
     */
	@TableField("end_date")
	private String endDate;
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

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getTicketDesc() {
		return ticketDesc;
	}

	public void setTicketDesc(String ticketDesc) {
		this.ticketDesc = ticketDesc;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getNtOrderid() {
		return ntOrderid;
	}

	public void setNtOrderid(String ntOrderid) {
		this.ntOrderid = ntOrderid;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getExpireType() {
		return expireType;
	}

	public void setExpireType(String expireType) {
		this.expireType = expireType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
		return "CcTicketInfo{" +
			"id=" + id +
			", ticketId=" + ticketId +
			", userId=" + userId +
			", ticketName=" + ticketName +
			", ticketType=" + ticketType +
			", ticketDesc=" + ticketDesc +
			", cardno=" + cardno +
			", ntOrderid=" + ntOrderid +
			", saleDate=" + saleDate +
			", expireType=" + expireType +
			", startDate=" + startDate +
			", endDate=" + endDate +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
