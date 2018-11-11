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
 * POS???????
 * </p>
 *
 * @author stylefeng
 * @since 2018-01-29
 */
@TableName("cc_blacklist_version")
public class CcBlacklistVersion extends Model<CcBlacklistVersion> {

    private static final long serialVersionUID = 1L;

    /**
     * ID ;??
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * ???? 0:????? 1:?????
     */
	@TableField("filename")
	private String filename;
    /**
     * ???
     */
	@TableField("list_version")
	private String listVersion;
    /**
     * ??????ID
     */
	@TableField("file_id")
	private Integer fileId;
    /**
     * ????
     */
	private String content;
    /**
     * ???? 0:??POS?,??;1:????;2:??POS?;
     */
	private String type;
    /**
     * ??ID ??type=1??
     */
	@TableField("line_id")
	private String lineId;
    /**
     * POS?ID ??type=2??
     */
	@TableField("pos_id")
	private String posId;
    /**
     * ????;?????????
     */
	@TableField("create_time")
	private String createTime;
    /**
     * ????
     */
	@TableField("update_time")
	private String updateTime;


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

	public String getListVersion() {
		return listVersion;
	}

	public void setListVersion(String listVersion) {
		this.listVersion = listVersion;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
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
		return "CcBlacklistVersion{" +
				"id=" + id +
				", filename='" + filename + '\'' +
				", listVersion='" + listVersion + '\'' +
				", fileId=" + fileId +
				", content='" + content + '\'' +
				", type='" + type + '\'' +
				", lineId='" + lineId + '\'' +
				", posId='" + posId + '\'' +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				'}';
	}
}
