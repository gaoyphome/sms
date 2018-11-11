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
 * [云卡二期]站点信息表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-18
 */
@TableName("cc_station_info")
public class CcStationInfo extends Model<CcStationInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 所属线路编号
     */
	@TableField("line_id")
	private String lineId;
    /**
     * 站点名称
     */
	private String name;
    /**
     * 1上行站 0下行站
     */
	private Integer flag;
    /**
     * 站点序号
     */
	private Integer num;
    /**
     * longitude
     */
	private String longitude;
    /**
     * 纬度
     */
	private String latitude;
    /**
     * 运营站点顺序号
     */
	@TableField("opera_station_num")
	private Integer operaStationNum;
    /**
     * IC 卡站点号
     */
	@TableField("ic_station_num")
	private Integer icStationNum;
    /**
     * IC 卡招呼站点号
     */
	@TableField("ic_sub_station_num")
	private Integer icSubStationNum;
    /**
     * 注册时间
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

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Integer getOperaStationNum() {
		return operaStationNum;
	}

	public void setOperaStationNum(Integer operaStationNum) {
		this.operaStationNum = operaStationNum;
	}

	public Integer getIcStationNum() {
		return icStationNum;
	}

	public void setIcStationNum(Integer icStationNum) {
		this.icStationNum = icStationNum;
	}

	public Integer getIcSubStationNum() {
		return icSubStationNum;
	}

	public void setIcSubStationNum(Integer icSubStationNum) {
		this.icSubStationNum = icSubStationNum;
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
		return "CcStationInfo{" +
			"id=" + id +
			", lineId=" + lineId +
			", name=" + name +
			", flag=" + flag +
			", num=" + num +
			", longitude=" + longitude +
			", latitude=" + latitude +
			", operaStationNum=" + operaStationNum +
			", icStationNum=" + icStationNum +
			", icSubStationNum=" + icSubStationNum +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
