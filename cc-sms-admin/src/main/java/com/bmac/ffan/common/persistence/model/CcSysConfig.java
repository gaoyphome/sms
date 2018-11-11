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
@TableName("cc_sys_config")
public class CcSysConfig extends Model<CcSysConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * ID自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 配置所属大类
     */
	private String category;
    /**
     * 字段名称
     */
	@TableField("param_key")
	private String paramKey;
    /**
     * 字段值
     */
	@TableField("param_value")
	private String paramValue;
    /**
     * 备注
     */
	private String remark;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcSysConfig{" +
			"id=" + id +
			", category=" + category +
			", paramKey=" + paramKey +
			", paramValue=" + paramValue +
			", remark=" + remark +
			"}";
	}
}
