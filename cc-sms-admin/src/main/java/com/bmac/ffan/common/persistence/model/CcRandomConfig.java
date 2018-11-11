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
@TableName("cc_random_config")
public class CcRandomConfig extends Model<CcRandomConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 当前索引号
     */
	@TableField("curr_index")
	private Long currIndex;
    /**
     * RANDOM最大索引号
     */
	@TableField("max_index")
	private Long maxIndex;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private String updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCurrIndex() {
		return currIndex;
	}

	public void setCurrIndex(Long currIndex) {
		this.currIndex = currIndex;
	}

	public Long getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(Long maxIndex) {
		this.maxIndex = maxIndex;
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
		return "CcRandomConfig{" +
			"id=" + id +
			", currIndex=" + currIndex +
			", maxIndex=" + maxIndex +
			", updateTime=" + updateTime +
			"}";
	}
}
