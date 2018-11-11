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
@TableName("cc_price_info")
public class CcPriceInfo extends Model<CcPriceInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 线路ID
     */
	@TableField("line_id")
	private String lineId;
    /**
     * 上下行标志
     */
	@TableField("dir_flag")
	private String dirFlag;
    /**
     * 开始站点顺序号
     */
	private String num;
    /**
     * 票价
     */
	private String price;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private String updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getDirFlag() {
		return dirFlag;
	}

	public void setDirFlag(String dirFlag) {
		this.dirFlag = dirFlag;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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
		return "CcPriceInfo{" +
			"id=" + id +
			", lineId=" + lineId +
			", dirFlag=" + dirFlag +
			", num=" + num +
			", price=" + price +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
