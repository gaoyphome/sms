package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * pos黑名单文件表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
@TableName("cc_pos_file_blacklist")
public class CcPosFileBlacklist extends Model<CcPosFileBlacklist> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 文件名
     */
	private String filename;
    /**
     * 文件尺寸
     */
	private Integer filesize;
    /**
     * CRC16
     */
	private String crc16;
    /**
     * 文件内容
     */
	private String content;
    /**
     * 文件版本
     */
	private Integer version;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getFilesize() {
		return filesize;
	}

	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
	}

	public String getCrc16() {
		return crc16;
	}

	public void setCrc16(String crc16) {
		this.crc16 = crc16;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcPosFileBlacklist{" +
			"id=" + id +
			", filename=" + filename +
			", filesize=" + filesize +
			", crc16=" + crc16 +
			", content=" + content +
			", version=" + version +
			"}";
	}
}
