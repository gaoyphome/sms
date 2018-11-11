package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * [云卡二期]运营公司信息表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-15
 */
@TableName("cc_bus_company")
public class CcBusCompany extends Model<CcBusCompany> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 运营公司编码
	 */
	@TableField("company_id")
	private String companyId;
	@TableField("company_name")
	private String companyName;
	/**
	 * 公司类型 0 运营公司 1子运营公司
	 */
	private Integer type;
	/**
	 * 所属父公司编号
	 */
	@TableField("parent_company_id")
	private String parentCompanyId;
	/**
	 * 创建时间
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParentCompanyId() {
		return parentCompanyId;
	}

	public void setParentCompanyId(String parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
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
		return "CcBusCompany [id=" + id + ", companyId=" + companyId + ", companyName=" + companyName + ", type=" + type
				+ ", parentCompanyId=" + parentCompanyId + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}
