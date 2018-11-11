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
 * [云卡二期]线路信息表
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-18
 */
@TableName("cc_line_info")
public class CcLineInfo extends Model<CcLineInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 线路编号
     */
	@TableField("line_id")
	private String lineId;
    /**
     * 线路文件ID
     */
	@TableField("file_id")
	private Integer fileId;
    /**
     * 线路名称
     */
	private String name;
    /**
     * 运营公司编号
     */
	@TableField("company_id")
	private String companyId;
	@TableField("sub_company_id")
	private String subCompanyId;
    /**
     * 生效日期
     */
	@TableField("date_enable")
	private String dateEnable;
    /**
     * 本地未定义卡类型处理模式
     */
	@TableField("local_no_mode")
	private String localNoMode;
    /**
     * 异地未定义卡类型处理模式
     */
	@TableField("offsite_no_model")
	private String offsiteNoModel;
    /**
     * 车辆属性
     */
	@TableField("bus_attr")
	private String busAttr;
    /**
     * 异地逃票补票模式
     */
	@TableField("offsite_ticket_model")
	private String offsiteTicketModel;
    /**
     * 上下车方向不同补票规则
     */
	@TableField("dif_ticket_rule")
	private String difTicketRule;
    /**
     * 补票金额是否无折扣标志
     */
	@TableField("ticket_discount_flag")
	private String ticketDiscountFlag;
    /**
     * 功能开关
     */
	@TableField("function_switch")
	private String functionSwitch;
    /**
     * card_issuer_num
     */
	@TableField("card_issuer_num")
	private String cardIssuerNum;
    /**
     * 00H 非环行单一票制 01H非环行分段票制 02H环行单一票制 03H环行分段票制
     */
	@TableField("line_attr")
	private String lineAttr;
    /**
     * 换乘时限
     */
	@TableField("exchange_time")
	private Integer exchangeTime;
    /**
     * 基本票价(折前)
     */
	@TableField("price_base")
	private Integer priceBase;
    /**
     * city_leave_up
     */
	@TableField("city_leave_up")
	private String cityLeaveUp;
    /**
     * 下行市界起点
     */
	@TableField("city_leave_down")
	private String cityLeaveDown;
    /**
     * 上行站点数量
     */
	@TableField("stations_num_up")
	private Integer stationsNumUp;
    /**
     * 下行站点数量
     */
	@TableField("stations_num_dn")
	private Integer stationsNumDn;
    /**
     * 线路格式版本号
     */
	@TableField("file_version")
	private String fileVersion;
    /**
     * 线路版本号 每次导入自动加一
     */
	@TableField("plt_line_version")
	private Integer pltLineVersion;
    /**
     * 0 未生成 1已生成
     */
	@TableField("create_flag")
	private Integer createFlag;
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

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(String subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public String getDateEnable() {
		return dateEnable;
	}

	public void setDateEnable(String dateEnable) {
		this.dateEnable = dateEnable;
	}

	public String getLocalNoMode() {
		return localNoMode;
	}

	public void setLocalNoMode(String localNoMode) {
		this.localNoMode = localNoMode;
	}

	public String getOffsiteNoModel() {
		return offsiteNoModel;
	}

	public void setOffsiteNoModel(String offsiteNoModel) {
		this.offsiteNoModel = offsiteNoModel;
	}

	public String getBusAttr() {
		return busAttr;
	}

	public void setBusAttr(String busAttr) {
		this.busAttr = busAttr;
	}

	public String getOffsiteTicketModel() {
		return offsiteTicketModel;
	}

	public void setOffsiteTicketModel(String offsiteTicketModel) {
		this.offsiteTicketModel = offsiteTicketModel;
	}

	public String getDifTicketRule() {
		return difTicketRule;
	}

	public void setDifTicketRule(String difTicketRule) {
		this.difTicketRule = difTicketRule;
	}

	public String getTicketDiscountFlag() {
		return ticketDiscountFlag;
	}

	public void setTicketDiscountFlag(String ticketDiscountFlag) {
		this.ticketDiscountFlag = ticketDiscountFlag;
	}

	public String getFunctionSwitch() {
		return functionSwitch;
	}

	public void setFunctionSwitch(String functionSwitch) {
		this.functionSwitch = functionSwitch;
	}

	public String getCardIssuerNum() {
		return cardIssuerNum;
	}

	public void setCardIssuerNum(String cardIssuerNum) {
		this.cardIssuerNum = cardIssuerNum;
	}

	public String getLineAttr() {
		return lineAttr;
	}

	public void setLineAttr(String lineAttr) {
		this.lineAttr = lineAttr;
	}

	public Integer getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(Integer exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public Integer getPriceBase() {
		return priceBase;
	}

	public void setPriceBase(Integer priceBase) {
		this.priceBase = priceBase;
	}

	public String getCityLeaveUp() {
		return cityLeaveUp;
	}

	public void setCityLeaveUp(String cityLeaveUp) {
		this.cityLeaveUp = cityLeaveUp;
	}

	public String getCityLeaveDown() {
		return cityLeaveDown;
	}

	public void setCityLeaveDown(String cityLeaveDown) {
		this.cityLeaveDown = cityLeaveDown;
	}

	public Integer getStationsNumUp() {
		return stationsNumUp;
	}

	public void setStationsNumUp(Integer stationsNumUp) {
		this.stationsNumUp = stationsNumUp;
	}

	public Integer getStationsNumDn() {
		return stationsNumDn;
	}

	public void setStationsNumDn(Integer stationsNumDn) {
		this.stationsNumDn = stationsNumDn;
	}

	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public Integer getPltLineVersion() {
		return pltLineVersion;
	}

	public void setPltLineVersion(Integer pltLineVersion) {
		this.pltLineVersion = pltLineVersion;
	}

	public Integer getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(Integer createFlag) {
		this.createFlag = createFlag;
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
		return "CcLineInfo{" +
			"id=" + id +
			", lineId=" + lineId +
			", fileId=" + fileId +
			", name=" + name +
			", companyId=" + companyId +
			", subCompanyId=" + subCompanyId +
			", dateEnable=" + dateEnable +
			", localNoMode=" + localNoMode +
			", offsiteNoModel=" + offsiteNoModel +
			", busAttr=" + busAttr +
			", offsiteTicketModel=" + offsiteTicketModel +
			", difTicketRule=" + difTicketRule +
			", ticketDiscountFlag=" + ticketDiscountFlag +
			", functionSwitch=" + functionSwitch +
			", cardIssuerNum=" + cardIssuerNum +
			", lineAttr=" + lineAttr +
			", exchangeTime=" + exchangeTime +
			", priceBase=" + priceBase +
			", cityLeaveUp=" + cityLeaveUp +
			", cityLeaveDown=" + cityLeaveDown +
			", stationsNumUp=" + stationsNumUp +
			", stationsNumDn=" + stationsNumDn +
			", fileVersion=" + fileVersion +
			", pltLineVersion=" + pltLineVersion +
			", createFlag=" + createFlag +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
