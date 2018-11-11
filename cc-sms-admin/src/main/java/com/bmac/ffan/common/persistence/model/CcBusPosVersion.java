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
 * [云卡二期]POS固件版本管理表
 * </p>
 *
 * @author stylefeng
 * @since 2017-12-21
 */
@TableName("cc_bus_pos_version")
public class CcBusPosVersion extends Model<CcBusPosVersion> {

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
     * 线路ID
     */
	@TableField("line_id")
	private String lineId;
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

	/**
	 * 更新时间
	 */
	@TableField("down_start_time")
	private String downStartTime;

	/**
	 * 更新时间
	 */
	@TableField("down_end_time")
	private String downEndTime;

	/**
	 * 更新时间
	 */
	@TableField("install_start_time")
	private String installStartTime;

	/**
	 * 更新时间
	 */
	@TableField("install_end_time")
	private String installEndTime;

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

	public String getDownStartTime() {
		return downStartTime;
	}

	public void setDownStartTime(String downStartTime) {
		this.downStartTime = downStartTime;
	}

	public String getDownEndTime() {
		return downEndTime;
	}

	public void setDownEndTime(String downEndTime) {
		this.downEndTime = downEndTime;
	}

	public String getInstallStartTime() {
		return installStartTime;
	}

	public void setInstallStartTime(String installStartTime) {
		this.installStartTime = installStartTime;
	}

	public String getInstallEndTime() {
		return installEndTime;
	}

	public void setInstallEndTime(String installEndTime) {
		this.installEndTime = installEndTime;
	}

	@Override
	public String toString() {
		return "CcBusPosVersion{" +
				"id=" + id +
				", firmVersion='" + firmVersion + '\'' +
				", filesize=" + filesize +
				", fileId=" + fileId +
				", content='" + content + '\'' +
				", path='" + path + '\'' +
				", type='" + type + '\'' +
				", upgradeType='" + upgradeType + '\'' +
				", lineId='" + lineId + '\'' +
				", posId='" + posId + '\'' +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				", downStartTime='" + downStartTime + '\'' +
				", downEndTime='" + downEndTime + '\'' +
				", installStartTime='" + installStartTime + '\'' +
				", installEndTime='" + installEndTime + '\'' +
				'}';
	}
}
