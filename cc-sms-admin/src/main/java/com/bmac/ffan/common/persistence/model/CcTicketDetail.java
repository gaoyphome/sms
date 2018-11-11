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
@TableName("cc_ticket_detail")
public class CcTicketDetail extends Model<CcTicketDetail> {

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
     * 网络支付平台订单号
     */
	@TableField("nt_orderid")
	private String ntOrderid;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private String agentId;
    /**
     * 商户ID
     */
	@TableField("mchnt_id")
	private String mchntId;
    /**
     * 景点ID
     */
	@TableField("spot_id")
	private String spotId;
    /**
     * 门店ID
     */
	@TableField("term_id")
	private String termId;
    /**
     * 网络平台的流水号
     */
	private String sesq;
    /**
     * 总的可使用次数
     */
	@TableField("total_cnt")
	private Integer totalCnt;
    /**
     * 已使用次数
     */
	@TableField("use_cnt")
	private Integer useCnt;
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

	public String getNtOrderid() {
		return ntOrderid;
	}

	public void setNtOrderid(String ntOrderid) {
		this.ntOrderid = ntOrderid;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getMchntId() {
		return mchntId;
	}

	public void setMchntId(String mchntId) {
		this.mchntId = mchntId;
	}

	public String getSpotId() {
		return spotId;
	}

	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getSesq() {
		return sesq;
	}

	public void setSesq(String sesq) {
		this.sesq = sesq;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	public Integer getUseCnt() {
		return useCnt;
	}

	public void setUseCnt(Integer useCnt) {
		this.useCnt = useCnt;
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
		return "CcTicketDetail{" +
			"id=" + id +
			", ticketId=" + ticketId +
			", ntOrderid=" + ntOrderid +
			", agentId=" + agentId +
			", mchntId=" + mchntId +
			", spotId=" + spotId +
			", termId=" + termId +
			", sesq=" + sesq +
			", totalCnt=" + totalCnt +
			", useCnt=" + useCnt +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
