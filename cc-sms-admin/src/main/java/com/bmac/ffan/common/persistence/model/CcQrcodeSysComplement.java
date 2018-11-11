package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户补票记录表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_qrcode_sys_complement")
public class CcQrcodeSysComplement extends Model<CcQrcodeSysComplement> {

    private static final long serialVersionUID = 1L;

    /**
     * 虚拟一卡通卡号
     */
    @TableId("vsir_bmacno")
	private String vsirBmacno;
    /**
     * 月份
     */
	private String mouth;
    /**
     * 超过规定补票次数
     */
	@TableField("over_complement_count")
	private String overComplementCount;
    /**
     * 系统补票次数
     */
	@TableField("sys_complement_count")
	private String sysComplementCount;
    /**
     * 用户补票次数
     */
	@TableField("user_complement_count")
	private String userComplementCount;


	public String getVsirBmacno() {
		return vsirBmacno;
	}

	public void setVsirBmacno(String vsirBmacno) {
		this.vsirBmacno = vsirBmacno;
	}

	public String getMouth() {
		return mouth;
	}

	public void setMouth(String mouth) {
		this.mouth = mouth;
	}

	public String getOverComplementCount() {
		return overComplementCount;
	}

	public void setOverComplementCount(String overComplementCount) {
		this.overComplementCount = overComplementCount;
	}

	public String getSysComplementCount() {
		return sysComplementCount;
	}

	public void setSysComplementCount(String sysComplementCount) {
		this.sysComplementCount = sysComplementCount;
	}

	public String getUserComplementCount() {
		return userComplementCount;
	}

	public void setUserComplementCount(String userComplementCount) {
		this.userComplementCount = userComplementCount;
	}

	@Override
	protected Serializable pkVal() {
		return this.vsirBmacno;
	}

	@Override
	public String toString() {
		return "CcQrcodeSysComplement{" +
			"vsirBmacno=" + vsirBmacno +
			", mouth=" + mouth +
			", overComplementCount=" + overComplementCount +
			", sysComplementCount=" + sysComplementCount +
			", userComplementCount=" + userComplementCount +
			"}";
	}
}
