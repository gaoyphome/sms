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
@TableName("cc_pos_card_type")
public class CcPosCardType extends Model<CcPosCardType> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商户简码
     */
	private String mchntcode;
    /**
     * 卡物理类型
     */
	@TableField("card_phy_type")
	private String cardPhyType;
    /**
     * 卡逻辑类型
     */
	@TableField("card_type")
	private String cardType;
    /**
     * 卡类型名称
     */
	@TableField("card_attr")
	private String cardAttr;
    /**
     * 扣款模式
     */
	@TableField("cost_type")
	private String costType;
    /**
     * 卡内最小余额限制
     */
	@TableField("min_balance")
	private String minBalance;
    /**
     * 最大透支额度
     */
	@TableField("credit_balance")
	private String creditBalance;
    /**
     * 卡内最大余额限制
     */
	@TableField("max_balance")
	private String maxBalance;
    /**
     * 最大扣款额度
     */
	@TableField("max_consume")
	private String maxConsume;
    /**
     * 界内优惠率
     */
	@TableField("in_rate")
	private String inRate;
    /**
     * 界内优惠额度
     */
	@TableField("in_limit")
	private String inLimit;
    /**
     * 界外优惠率
     */
	@TableField("out_rate")
	private String outRate;
    /**
     * 界外优惠额度
     */
	@TableField("out_limit")
	private String outLimit;
    /**
     * 保留
     */
	private String reserved;
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

	public String getMchntcode() {
		return mchntcode;
	}

	public void setMchntcode(String mchntcode) {
		this.mchntcode = mchntcode;
	}

	public String getCardPhyType() {
		return cardPhyType;
	}

	public void setCardPhyType(String cardPhyType) {
		this.cardPhyType = cardPhyType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardAttr() {
		return cardAttr;
	}

	public void setCardAttr(String cardAttr) {
		this.cardAttr = cardAttr;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(String minBalance) {
		this.minBalance = minBalance;
	}

	public String getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(String creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getMaxBalance() {
		return maxBalance;
	}

	public void setMaxBalance(String maxBalance) {
		this.maxBalance = maxBalance;
	}

	public String getMaxConsume() {
		return maxConsume;
	}

	public void setMaxConsume(String maxConsume) {
		this.maxConsume = maxConsume;
	}

	public String getInRate() {
		return inRate;
	}

	public void setInRate(String inRate) {
		this.inRate = inRate;
	}

	public String getInLimit() {
		return inLimit;
	}

	public void setInLimit(String inLimit) {
		this.inLimit = inLimit;
	}

	public String getOutRate() {
		return outRate;
	}

	public void setOutRate(String outRate) {
		this.outRate = outRate;
	}

	public String getOutLimit() {
		return outLimit;
	}

	public void setOutLimit(String outLimit) {
		this.outLimit = outLimit;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
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
		return "CcPosCardType{" +
			"id=" + id +
			", mchntcode=" + mchntcode +
			", cardPhyType=" + cardPhyType +
			", cardType=" + cardType +
			", cardAttr=" + cardAttr +
			", costType=" + costType +
			", minBalance=" + minBalance +
			", creditBalance=" + creditBalance +
			", maxBalance=" + maxBalance +
			", maxConsume=" + maxConsume +
			", inRate=" + inRate +
			", inLimit=" + inLimit +
			", outRate=" + outRate +
			", outLimit=" + outLimit +
			", reserved=" + reserved +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
