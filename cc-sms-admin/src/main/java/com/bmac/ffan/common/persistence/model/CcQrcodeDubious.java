package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * [业务参数-卡] 二维码可疑信息表 TBL_BSE_QRCODE_DUBIOUS
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_qrcode_dubious")
public class CcQrcodeDubious extends Model<CcQrcodeDubious> {

    private static final long serialVersionUID = 1L;

    /**
     * 虚拟一卡通卡号
     */
    @TableId("vsir_bmacno")
	private String vsirBmacno;
    /**
     * 原因 01二维码盗用 02用户多次未补票(系统自动帮补票) 03用户连续多次超过月规定补票次数
     */
	private String reason;
    /**
     * TokenId
     */
	@TableField("token_id")
	private String tokenId;
    /**
     * 可疑操作次数
     */
	@TableField("dubious_count")
	private String dubiousCount;


	public String getVsirBmacno() {
		return vsirBmacno;
	}

	public void setVsirBmacno(String vsirBmacno) {
		this.vsirBmacno = vsirBmacno;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getDubiousCount() {
		return dubiousCount;
	}

	public void setDubiousCount(String dubiousCount) {
		this.dubiousCount = dubiousCount;
	}

	@Override
	protected Serializable pkVal() {
		return this.vsirBmacno;
	}

	@Override
	public String toString() {
		return "CcQrcodeDubious{" +
			"vsirBmacno=" + vsirBmacno +
			", reason=" + reason +
			", tokenId=" + tokenId +
			", dubiousCount=" + dubiousCount +
			"}";
	}
}
