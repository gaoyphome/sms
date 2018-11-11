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
@TableName("cc_mchnt_pos")
public class CcMchntPos extends Model<CcMchntPos> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * POS机ID
     */
	@TableField("pos_id")
	private String posId;
    /**
     * 商户ID
     */
	@TableField("mchnt_id")
	private String mchntId;
    /**
     * 商户类型
     */
	@TableField("mchnt_type")
	private String mchntType;
    /**
     * POS机安装时间
     */
	@TableField("install_time")
	private String installTime;
    /**
     * 绑定时间；默认为记录插入时间
     */
	@TableField("create_time")
	private String createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getMchntId() {
		return mchntId;
	}

	public void setMchntId(String mchntId) {
		this.mchntId = mchntId;
	}

	public String getMchntType() {
		return mchntType;
	}

	public void setMchntType(String mchntType) {
		this.mchntType = mchntType;
	}

	public String getInstallTime() {
		return installTime;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcMchntPos{" +
			"id=" + id +
			", posId=" + posId +
			", mchntId=" + mchntId +
			", mchntType=" + mchntType +
			", installTime=" + installTime +
			", createTime=" + createTime +
			"}";
	}
}
