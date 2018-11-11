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
@TableName("cc_pos_version")
public class CcPosVersion extends Model<CcPosVersion> {

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
     * 固件文件大小
     */
	private Integer filesize;
    /**
     * 固件文件索引ID
     */
	@TableField("file_id")
	private Integer fileId;
    /**
     * 版本描述
     */
	private String content;
    /**
     * 下载路径
     */
	private String path;
    /**
     * 升级方式
     */
	private String type;
    /**
     * 升级类型
     */
	@TableField("upgrade_type")
	private String upgradeType;
    /**
     * 商户ID
     */
	@TableField("mchnt_id")
	private String mchntId;
    /**
     * POS机ID
     */
	@TableField("pos_id")
	private String posId;
    /**
     * 绑定时间；默认为记录插入时间
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

	public String getFirmVersion() {
		return firmVersion;
	}

	public void setFirmVersion(String firmVersion) {
		this.firmVersion = firmVersion;
	}

	public Integer getFilesize() {
		return filesize;
	}

	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpgradeType() {
		return upgradeType;
	}

	public void setUpgradeType(String upgradeType) {
		this.upgradeType = upgradeType;
	}

	public String getMchntId() {
		return mchntId;
	}

	public void setMchntId(String mchntId) {
		this.mchntId = mchntId;
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
		return "CcPosVersion{" +
			"id=" + id +
			", firmVersion=" + firmVersion +
			", filesize=" + filesize +
			", fileId=" + fileId +
			", content=" + content +
			", path=" + path +
			", type=" + type +
			", upgradeType=" + upgradeType +
			", mchntId=" + mchntId +
			", posId=" + posId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
