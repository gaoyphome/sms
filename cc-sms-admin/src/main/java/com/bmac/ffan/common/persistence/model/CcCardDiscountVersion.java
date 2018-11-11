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
 * [云卡二期]实体卡版本号
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_card_discount_version")
public class CcCardDiscountVersion extends Model<CcCardDiscountVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 子运营公司id
     */
	@TableField("sub_company_id")
	private String subCompanyId;
    /**
     * 版本号每当子运营公司有更新时加1
     */
	@TableField("plt_version")
	private Integer pltVersion;
    /**
     * 0 未创建 1已创建
     */
	@TableField("create_flag")
	private Integer createFlag;
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

	public String getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(String subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public Integer getPltVersion() {
		return pltVersion;
	}

	public void setPltVersion(Integer pltVersion) {
		this.pltVersion = pltVersion;
	}

	public Integer getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(Integer createFlag) {
		this.createFlag = createFlag;
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
		return "CcCardDiscountVersion{" +
			"id=" + id +
			", subCompanyId=" + subCompanyId +
			", pltVersion=" + pltVersion +
			", createFlag=" + createFlag +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
