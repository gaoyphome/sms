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
 * [云卡二期]公交POS固件信息表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_bus_pos_firmware")
public class CcBusPosFirmware extends Model<CcBusPosFirmware> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 版本号
     */
	@TableField("firm_version")
	private String firmVersion;
    /**
     * 固件文件内容
     */
	private String content;

	@TableField("crc16")
	private String crc16;

	@TableField("content_type")
	private String contentType;

	@TableField("pos_vendor")
	private String posVendor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirmVersion() {
		return firmVersion;
	}

	public void setFirmVersion(String firmVersion) {
		this.firmVersion = firmVersion;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCrc16() {
		return crc16;
	}

	public void setCrc16(String crc16) {
		this.crc16 = crc16;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getPosVendor() {
		return posVendor;
	}

	public void setPosVendor(String posVendor) {
		this.posVendor = posVendor;
	}

	@Override
	public String toString() {
		return "CcBusPosFirmware{" +
				"id=" + id +
				", firmVersion='" + firmVersion + '\'' +
				", content='" + content + '\'' +
				", crc16='" + crc16 + '\'' +
				", contentType='" + contentType + '\'' +
				", posVendor='" + posVendor + '\'' +
				'}';
	}
}
