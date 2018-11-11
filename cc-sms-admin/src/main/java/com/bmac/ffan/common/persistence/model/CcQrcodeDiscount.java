package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

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
@TableName("cc_qrcode_discount")
public class CcQrcodeDiscount extends Model<CcQrcodeDiscount> {

    private static final long serialVersionUID = 1L;

    /**
     * 运营分公司代码
     */
	@TableField("sub_company_id")
	private String subCompanyId;

	/**
	 * 运营分公司名称
	 */
	private String companyName;
    /**
     * 虚拟卡类型
     */
	@TableField("card_type")
	private String cardType;
    /**
     * 市内折扣率
     */
	@TableField("city_in_discount")
	private String cityInDiscount;
    /**
     * 市内优惠额度
     */
	@TableField("city_in_preferential_amount")
	private String cityInPreferentialAmount;
    /**
     * 市外折扣率
     */
	@TableField("city_out_discount")
	private String cityOutDiscount;
    /**
     * 最大优惠额度
     */
	@TableField("max_preferential_amount")
	private String maxPreferentialAmount;
    /**
     * 市外优惠额度
     */
	@TableField("city_out_preferential_amount")
	private String cityOutPreferentialAmount;


	public String getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(String subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCityInDiscount() {
		return cityInDiscount;
	}

	public void setCityInDiscount(String cityInDiscount) {
		this.cityInDiscount = cityInDiscount;
	}

	public String getCityInPreferentialAmount() {
		return cityInPreferentialAmount;
	}

	public void setCityInPreferentialAmount(String cityInPreferentialAmount) {
		this.cityInPreferentialAmount = cityInPreferentialAmount;
	}

	public String getCityOutDiscount() {
		return cityOutDiscount;
	}

	public void setCityOutDiscount(String cityOutDiscount) {
		this.cityOutDiscount = cityOutDiscount;
	}

	public String getMaxPreferentialAmount() {
		return maxPreferentialAmount;
	}

	public void setMaxPreferentialAmount(String maxPreferentialAmount) {
		this.maxPreferentialAmount = maxPreferentialAmount;
	}

	public String getCityOutPreferentialAmount() {
		return cityOutPreferentialAmount;
	}

	public void setCityOutPreferentialAmount(String cityOutPreferentialAmount) {
		this.cityOutPreferentialAmount = cityOutPreferentialAmount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	protected Serializable pkVal() {
		return this.subCompanyId;
	}

	@Override
	public String toString() {
		return "CcQrcodeDiscount{" +
				"subCompanyId='" + subCompanyId + '\'' +
				", companyName='" + companyName + '\'' +
				", cardType='" + cardType + '\'' +
				", cityInDiscount='" + cityInDiscount + '\'' +
				", cityInPreferentialAmount='" + cityInPreferentialAmount + '\'' +
				", cityOutDiscount='" + cityOutDiscount + '\'' +
				", maxPreferentialAmount='" + maxPreferentialAmount + '\'' +
				", cityOutPreferentialAmount='" + cityOutPreferentialAmount + '\'' +
				'}';
	}
}
