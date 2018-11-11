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
 * 二维码乘车交易异常信息表
 * </p>
 *
 * @author stylefeng
 * @since 2018-03-15
 */
@TableName("cc_qrcode_ride_trans_error")
public class CcQrcodeRideTransError extends Model<CcQrcodeRideTransError> {

    private static final long serialVersionUID = 1L;

    /**
     * ID 自增
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 平台流水号
     */
	@TableField("plt_ssn")
	private String pltSsn;
    /**
     * 请求方流水号
     */
	@TableField("req_ssn")
	private String reqSsn;
    /**
     * 本地清算日期
     */
	@TableField("settle_date_loc")
	private String settleDateLoc;
    /**
     * 接收日期
     */
	@TableField("trans_recv_date")
	private Integer transRecvDate;
    /**
     * 接收时间
     */
	@TableField("trans_recv_time")
	private String transRecvTime;
    /**
     * 外部交易代码
     */
	@TableField("trans_code_out")
	private String transCodeOut;
    /**
     * 渠道交易代码
     */
	@TableField("trans_code_chnl")
	private String transCodeChnl;
    /**
     * 交易代码名称
     */
	@TableField("trans_code_ch_name")
	private String transCodeChName;
    /**
     * 商户代码
     */
	@TableField("mch_code_in")
	private String mchCodeIn;
    /**
     * 商户名称
     */
	@TableField("mch_name_out")
	private String mchNameOut;
    /**
     * 受理机构
     */
	@TableField("acq_ins_id")
	private String acqInsId;
    /**
     * 受理机构流水号
     */
	@TableField("acq_ins_seq")
	private String acqInsSeq;
    /**
     * 本地交易时间
     */
	@TableField("loc_tracs_time")
	private String locTracsTime;
    /**
     * 本地交易日期
     */
	@TableField("loc_tracs_date")
	private String locTracsDate;
    /**
     * 终端交易时间
     */
	@TableField("term_trans_time")
	private String termTransTime;
    /**
     * 终端交易日期
     */
	@TableField("term_trans_date")
	private String termTransDate;
    /**
     * 城市代码
     */
	@TableField("card_city")
	private String cardCity;
    /**
     * CSN
     */
	private String csn;
    /**
     * 卡序列号
     */
	@TableField("card_seqNo")
	private String cardSeqNo;
    /**
     * 卡累计交易次数
     */
	@TableField("card_counter")
	private String cardCounter;
    /**
     * 交易卡类型
     */
	@TableField("card_type")
	private String cardType;
    /**
     * 卡物理类型
     */
	@TableField("card_phy_type")
	private String cardPhyType;
    /**
     * 平台订单号
     */
	@TableField("order_no")
	private String orderNo;
    /**
     * 订单支付状态 0 未扣费 1 扣费成功 6 扣费失败 7 补款成功
     */
	private String state;
    /**
     * 卡行业代码
     */
	@TableField("card_bramdid")
	private String cardBramdid;
    /**
     * 用户ID
     */
	@TableField("card_class")
	private String cardClass;
    /**
     * 交易类型
     */
	@TableField("order_type")
	private String orderType;
    /**
     * 补票交易状态
     */
	@TableField("trade_status")
	private String tradeStatus;
    /**
     * 记录顺序编号
     */
	@TableField("serial_num")
	private String serialNum;
    /**
     * 套票绑定的卡号
     */
	private String cardno;
    /**
     * 交易类型标识
     */
	@TableField("trade_type_flag")
	private String tradeTypeFlag;
    /**
     * 电子钱包脱机交易序号
     */
	@TableField("cpu_counter")
	private String cpuCounter;
    /**
     * 发卡机构代码
     */
	@TableField("card_iis_code")
	private String cardIisCode;
    /**
     * 时间票类型
     */
	@TableField("time_card_type")
	private String timeCardType;
    /**
     * 上车时间
     */
	@TableField("mark_time")
	private String markTime;
    /**
     * 上车城市代码
     */
	@TableField("mark_city_code")
	private String markCityCode;
    /**
     * 上车受理机构代码
     */
	@TableField("mark_iis_code")
	private String markIisCode;
    /**
     * 上车运营商代码
     */
	@TableField("mark_cs_code")
	private String markCsCode;
    /**
     * 上车线路
     */
	@TableField("mark_line_id")
	private String markLineId;
    /**
     * 上车站
     */
	@TableField("mark_station")
	private String markStation;
    /**
     * 上车车辆号
     */
	@TableField("mark_bus_id")
	private String markBusId;
    /**
     * 下车运营商代码
     */
	@TableField("down_cs_code")
	private String downCsCode;
    /**
     * 下车线路
     */
	@TableField("down_line_id")
	private String downLineId;
    /**
     * 下车站
     */
	@TableField("down_station")
	private String downStation;
    /**
     * 下车车辆号
     */
	@TableField("down_bus_id")
	private String downBusId;
    /**
     * 乘车里程
     */
	@TableField("taken_length")
	private String takenLength;
    /**
     * 上下行标志
     */
	@TableField("dir_flag")
	private String dirFlag;
    /**
     * 司机ID
     */
	@TableField("driver_code")
	private String driverCode;
    /**
     * 监票员ID
     */
	@TableField("sellor_code")
	private String sellorCode;
    /**
     * 监票员ID2
     */
	@TableField("sellor_code2")
	private String sellorCode2;
    /**
     * 支付提供方代码
     */
	@TableField("pos_id")
	private String posId;
    /**
     * POS机交易流水号
     */
	@TableField("pos_trade_seq")
	private String posTradeSeq;
    /**
     * POS机交易时间
     */
	@TableField("pos_date")
	private String posDate;
    /**
     * 交易TAC
     */
	private String tac;
    /**
     * TAC算法标识
     */
	@TableField("tac_mode")
	private String tacMode;
    /**
     * PSAM ID
     */
	@TableField("psam_id")
	private String psamId;
    /**
     * PSAM交易流水号
     */
	@TableField("psam_seq")
	private String psamSeq;
    /**
     * 交易金额
     */
	@TableField("charge_actual")
	private String chargeActual;
    /**
     * 卡内余额
     */
	@TableField("card_balance")
	private String cardBalance;
    /**
     * 交易前卡内余额
     */
	@TableField("pre_card_balance")
	private String preCardBalance;
    /**
     * 应收金额
     */
	@TableField("charge_ideal")
	private String chargeIdeal;
    /**
     * 密钥版本号
     */
	@TableField("key_ver")
	private String keyVer;
    /**
     * 密钥索引号
     */
	@TableField("key_index")
	private String keyIndex;
    /**
     * 密钥请求次数
     */
	@TableField("key_req_num")
	private Integer keyReqNum;
    /**
     * 密钥授权流水号
     */
	@TableField("auth_seq")
	private String authSeq;
    /**
     * 记录检验码
     */
	@TableField("check_code")
	private String checkCode;
    /**
     * 交易记录类型：0-景点，1-观光车或公交
     */
	@TableField("record_type")
	private String recordType;
    /**
     * 卡片标识
     */
	@TableField("card_flag")
	private String cardFlag;
    /**
     * 创建时间;默认为记录插入时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private String updateTime;
	@TableField("card_upload")
	private Integer cardUpload;
    /**
     * tokenId
     */
	@TableField("token_id")
	private String tokenId;
    /**
     * 用户手机号
     */
	@TableField("user_phone")
	private String userPhone;
    /**
     * token版本号
     */
	@TableField("token_version")
	private String tokenVersion;
    /**
     * 市内里程
     */
	@TableField("city_in_length")
	private String cityInLength;
    /**
     * 市外里程
     */
	@TableField("city_out_length")
	private String cityOutLength;
    /**
     * 市内折扣率
     */
	@TableField("city_in_rebate")
	private String cityInRebate;
    /**
     * 市外折扣率
     */
	@TableField("city_out_rebate")
	private String cityOutRebate;
    /**
     * 下车站时间
     */
	@TableField("down_time")
	private String downTime;
    /**
     * 上车站名
     */
	@TableField("mark_station_name")
	private String markStationName;
    /**
     * 下车站名
     */
	@TableField("down_station_name")
	private String downStationName;
    /**
     * 市内优惠额度
     */
	@TableField("city_in_discount")
	private String cityInDiscount;
    /**
     * 线路名
     */
	@TableField("line_name")
	private String lineName;
    /**
     * 市外优惠额度
     */
	@TableField("city_out_discount")
	private String cityOutDiscount;
    /**
     * 网络平台订单号
     */
	@TableField("nt_order_id")
	private String ntOrderId;
	@TableField("channel_type")
	private String channelType;
	@TableField("channel_id")
	private String channelId;
	@TableField("order_date")
	private String orderDate;
    /**
     * 1：一卡通后台账户2：支付宝3：微信4：其他 D非凡
     */
	@TableField("trade_type")
	private String tradeType;
	@TableField("porder_id")
	private String porderId;
	@TableField("pos_mark_trans_seq")
	private String posMarkTransSeq;
	@TableField("pos_down_trans_seq")
	private String posDownTransSeq;
	@TableField("mark_pasm_id")
	private String markPasmId;
	@TableField("down_pasm_id")
	private String downPasmId;
	@TableField("mark_pos_id")
	private String markPosId;
	@TableField("down_pos_id")
	private String downPosId;
	@TableField("mark_token_id")
	private String markTokenId;
	@TableField("down_token_id")
	private String downTokenId;
    /**
     * 交易状态 9 故障车作废交易 4系统自动补单  2未处理单边交易 5未处理故障车超时  1 系统自动匹配（正常）
     */
	@TableField("trans_state")
	private String transState;
	@TableField("third_instid")
	private String thirdInstid;
	@TableField("third_channelid")
	private String thirdChannelid;
	private String transcode;
	private String transmsg;
    /**
     * 错误类型　1001 通讯异常　9001　0105　网上支付平台内部超时
     */
	@TableField("err_type")
	private String errType;
    /**
     * 错误描述
     */
	@TableField("err_desc")
	private String errDesc;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPltSsn() {
		return pltSsn;
	}

	public void setPltSsn(String pltSsn) {
		this.pltSsn = pltSsn;
	}

	public String getReqSsn() {
		return reqSsn;
	}

	public void setReqSsn(String reqSsn) {
		this.reqSsn = reqSsn;
	}

	public String getSettleDateLoc() {
		return settleDateLoc;
	}

	public void setSettleDateLoc(String settleDateLoc) {
		this.settleDateLoc = settleDateLoc;
	}

	public Integer getTransRecvDate() {
		return transRecvDate;
	}

	public void setTransRecvDate(Integer transRecvDate) {
		this.transRecvDate = transRecvDate;
	}

	public String getTransRecvTime() {
		return transRecvTime;
	}

	public void setTransRecvTime(String transRecvTime) {
		this.transRecvTime = transRecvTime;
	}

	public String getTransCodeOut() {
		return transCodeOut;
	}

	public void setTransCodeOut(String transCodeOut) {
		this.transCodeOut = transCodeOut;
	}

	public String getTransCodeChnl() {
		return transCodeChnl;
	}

	public void setTransCodeChnl(String transCodeChnl) {
		this.transCodeChnl = transCodeChnl;
	}

	public String getTransCodeChName() {
		return transCodeChName;
	}

	public void setTransCodeChName(String transCodeChName) {
		this.transCodeChName = transCodeChName;
	}

	public String getMchCodeIn() {
		return mchCodeIn;
	}

	public void setMchCodeIn(String mchCodeIn) {
		this.mchCodeIn = mchCodeIn;
	}

	public String getMchNameOut() {
		return mchNameOut;
	}

	public void setMchNameOut(String mchNameOut) {
		this.mchNameOut = mchNameOut;
	}

	public String getAcqInsId() {
		return acqInsId;
	}

	public void setAcqInsId(String acqInsId) {
		this.acqInsId = acqInsId;
	}

	public String getAcqInsSeq() {
		return acqInsSeq;
	}

	public void setAcqInsSeq(String acqInsSeq) {
		this.acqInsSeq = acqInsSeq;
	}

	public String getLocTracsTime() {
		return locTracsTime;
	}

	public void setLocTracsTime(String locTracsTime) {
		this.locTracsTime = locTracsTime;
	}

	public String getLocTracsDate() {
		return locTracsDate;
	}

	public void setLocTracsDate(String locTracsDate) {
		this.locTracsDate = locTracsDate;
	}

	public String getTermTransTime() {
		return termTransTime;
	}

	public void setTermTransTime(String termTransTime) {
		this.termTransTime = termTransTime;
	}

	public String getTermTransDate() {
		return termTransDate;
	}

	public void setTermTransDate(String termTransDate) {
		this.termTransDate = termTransDate;
	}

	public String getCardCity() {
		return cardCity;
	}

	public void setCardCity(String cardCity) {
		this.cardCity = cardCity;
	}

	public String getCsn() {
		return csn;
	}

	public void setCsn(String csn) {
		this.csn = csn;
	}

	public String getCardSeqNo() {
		return cardSeqNo;
	}

	public void setCardSeqNo(String cardSeqNo) {
		this.cardSeqNo = cardSeqNo;
	}

	public String getCardCounter() {
		return cardCounter;
	}

	public void setCardCounter(String cardCounter) {
		this.cardCounter = cardCounter;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardPhyType() {
		return cardPhyType;
	}

	public void setCardPhyType(String cardPhyType) {
		this.cardPhyType = cardPhyType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCardBramdid() {
		return cardBramdid;
	}

	public void setCardBramdid(String cardBramdid) {
		this.cardBramdid = cardBramdid;
	}

	public String getCardClass() {
		return cardClass;
	}

	public void setCardClass(String cardClass) {
		this.cardClass = cardClass;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getTradeTypeFlag() {
		return tradeTypeFlag;
	}

	public void setTradeTypeFlag(String tradeTypeFlag) {
		this.tradeTypeFlag = tradeTypeFlag;
	}

	public String getCpuCounter() {
		return cpuCounter;
	}

	public void setCpuCounter(String cpuCounter) {
		this.cpuCounter = cpuCounter;
	}

	public String getCardIisCode() {
		return cardIisCode;
	}

	public void setCardIisCode(String cardIisCode) {
		this.cardIisCode = cardIisCode;
	}

	public String getTimeCardType() {
		return timeCardType;
	}

	public void setTimeCardType(String timeCardType) {
		this.timeCardType = timeCardType;
	}

	public String getMarkTime() {
		return markTime;
	}

	public void setMarkTime(String markTime) {
		this.markTime = markTime;
	}

	public String getMarkCityCode() {
		return markCityCode;
	}

	public void setMarkCityCode(String markCityCode) {
		this.markCityCode = markCityCode;
	}

	public String getMarkIisCode() {
		return markIisCode;
	}

	public void setMarkIisCode(String markIisCode) {
		this.markIisCode = markIisCode;
	}

	public String getMarkCsCode() {
		return markCsCode;
	}

	public void setMarkCsCode(String markCsCode) {
		this.markCsCode = markCsCode;
	}

	public String getMarkLineId() {
		return markLineId;
	}

	public void setMarkLineId(String markLineId) {
		this.markLineId = markLineId;
	}

	public String getMarkStation() {
		return markStation;
	}

	public void setMarkStation(String markStation) {
		this.markStation = markStation;
	}

	public String getMarkBusId() {
		return markBusId;
	}

	public void setMarkBusId(String markBusId) {
		this.markBusId = markBusId;
	}

	public String getDownCsCode() {
		return downCsCode;
	}

	public void setDownCsCode(String downCsCode) {
		this.downCsCode = downCsCode;
	}

	public String getDownLineId() {
		return downLineId;
	}

	public void setDownLineId(String downLineId) {
		this.downLineId = downLineId;
	}

	public String getDownStation() {
		return downStation;
	}

	public void setDownStation(String downStation) {
		this.downStation = downStation;
	}

	public String getDownBusId() {
		return downBusId;
	}

	public void setDownBusId(String downBusId) {
		this.downBusId = downBusId;
	}

	public String getTakenLength() {
		return takenLength;
	}

	public void setTakenLength(String takenLength) {
		this.takenLength = takenLength;
	}

	public String getDirFlag() {
		return dirFlag;
	}

	public void setDirFlag(String dirFlag) {
		this.dirFlag = dirFlag;
	}

	public String getDriverCode() {
		return driverCode;
	}

	public void setDriverCode(String driverCode) {
		this.driverCode = driverCode;
	}

	public String getSellorCode() {
		return sellorCode;
	}

	public void setSellorCode(String sellorCode) {
		this.sellorCode = sellorCode;
	}

	public String getSellorCode2() {
		return sellorCode2;
	}

	public void setSellorCode2(String sellorCode2) {
		this.sellorCode2 = sellorCode2;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getPosTradeSeq() {
		return posTradeSeq;
	}

	public void setPosTradeSeq(String posTradeSeq) {
		this.posTradeSeq = posTradeSeq;
	}

	public String getPosDate() {
		return posDate;
	}

	public void setPosDate(String posDate) {
		this.posDate = posDate;
	}

	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}

	public String getTacMode() {
		return tacMode;
	}

	public void setTacMode(String tacMode) {
		this.tacMode = tacMode;
	}

	public String getPsamId() {
		return psamId;
	}

	public void setPsamId(String psamId) {
		this.psamId = psamId;
	}

	public String getPsamSeq() {
		return psamSeq;
	}

	public void setPsamSeq(String psamSeq) {
		this.psamSeq = psamSeq;
	}

	public String getChargeActual() {
		return chargeActual;
	}

	public void setChargeActual(String chargeActual) {
		this.chargeActual = chargeActual;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}

	public String getPreCardBalance() {
		return preCardBalance;
	}

	public void setPreCardBalance(String preCardBalance) {
		this.preCardBalance = preCardBalance;
	}

	public String getChargeIdeal() {
		return chargeIdeal;
	}

	public void setChargeIdeal(String chargeIdeal) {
		this.chargeIdeal = chargeIdeal;
	}

	public String getKeyVer() {
		return keyVer;
	}

	public void setKeyVer(String keyVer) {
		this.keyVer = keyVer;
	}

	public String getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(String keyIndex) {
		this.keyIndex = keyIndex;
	}

	public Integer getKeyReqNum() {
		return keyReqNum;
	}

	public void setKeyReqNum(Integer keyReqNum) {
		this.keyReqNum = keyReqNum;
	}

	public String getAuthSeq() {
		return authSeq;
	}

	public void setAuthSeq(String authSeq) {
		this.authSeq = authSeq;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
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

	public Integer getCardUpload() {
		return cardUpload;
	}

	public void setCardUpload(Integer cardUpload) {
		this.cardUpload = cardUpload;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getTokenVersion() {
		return tokenVersion;
	}

	public void setTokenVersion(String tokenVersion) {
		this.tokenVersion = tokenVersion;
	}

	public String getCityInLength() {
		return cityInLength;
	}

	public void setCityInLength(String cityInLength) {
		this.cityInLength = cityInLength;
	}

	public String getCityOutLength() {
		return cityOutLength;
	}

	public void setCityOutLength(String cityOutLength) {
		this.cityOutLength = cityOutLength;
	}

	public String getCityInRebate() {
		return cityInRebate;
	}

	public void setCityInRebate(String cityInRebate) {
		this.cityInRebate = cityInRebate;
	}

	public String getCityOutRebate() {
		return cityOutRebate;
	}

	public void setCityOutRebate(String cityOutRebate) {
		this.cityOutRebate = cityOutRebate;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public String getMarkStationName() {
		return markStationName;
	}

	public void setMarkStationName(String markStationName) {
		this.markStationName = markStationName;
	}

	public String getDownStationName() {
		return downStationName;
	}

	public void setDownStationName(String downStationName) {
		this.downStationName = downStationName;
	}

	public String getCityInDiscount() {
		return cityInDiscount;
	}

	public void setCityInDiscount(String cityInDiscount) {
		this.cityInDiscount = cityInDiscount;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getCityOutDiscount() {
		return cityOutDiscount;
	}

	public void setCityOutDiscount(String cityOutDiscount) {
		this.cityOutDiscount = cityOutDiscount;
	}

	public String getNtOrderId() {
		return ntOrderId;
	}

	public void setNtOrderId(String ntOrderId) {
		this.ntOrderId = ntOrderId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPorderId() {
		return porderId;
	}

	public void setPorderId(String porderId) {
		this.porderId = porderId;
	}

	public String getPosMarkTransSeq() {
		return posMarkTransSeq;
	}

	public void setPosMarkTransSeq(String posMarkTransSeq) {
		this.posMarkTransSeq = posMarkTransSeq;
	}

	public String getPosDownTransSeq() {
		return posDownTransSeq;
	}

	public void setPosDownTransSeq(String posDownTransSeq) {
		this.posDownTransSeq = posDownTransSeq;
	}

	public String getMarkPasmId() {
		return markPasmId;
	}

	public void setMarkPasmId(String markPasmId) {
		this.markPasmId = markPasmId;
	}

	public String getDownPasmId() {
		return downPasmId;
	}

	public void setDownPasmId(String downPasmId) {
		this.downPasmId = downPasmId;
	}

	public String getMarkPosId() {
		return markPosId;
	}

	public void setMarkPosId(String markPosId) {
		this.markPosId = markPosId;
	}

	public String getDownPosId() {
		return downPosId;
	}

	public void setDownPosId(String downPosId) {
		this.downPosId = downPosId;
	}

	public String getMarkTokenId() {
		return markTokenId;
	}

	public void setMarkTokenId(String markTokenId) {
		this.markTokenId = markTokenId;
	}

	public String getDownTokenId() {
		return downTokenId;
	}

	public void setDownTokenId(String downTokenId) {
		this.downTokenId = downTokenId;
	}

	public String getTransState() {
		return transState;
	}

	public void setTransState(String transState) {
		this.transState = transState;
	}

	public String getThirdInstid() {
		return thirdInstid;
	}

	public void setThirdInstid(String thirdInstid) {
		this.thirdInstid = thirdInstid;
	}

	public String getThirdChannelid() {
		return thirdChannelid;
	}

	public void setThirdChannelid(String thirdChannelid) {
		this.thirdChannelid = thirdChannelid;
	}

	public String getTranscode() {
		return transcode;
	}

	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}

	public String getTransmsg() {
		return transmsg;
	}

	public void setTransmsg(String transmsg) {
		this.transmsg = transmsg;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CcQrcodeRideTransError{" +
			"id=" + id +
			", pltSsn=" + pltSsn +
			", reqSsn=" + reqSsn +
			", settleDateLoc=" + settleDateLoc +
			", transRecvDate=" + transRecvDate +
			", transRecvTime=" + transRecvTime +
			", transCodeOut=" + transCodeOut +
			", transCodeChnl=" + transCodeChnl +
			", transCodeChName=" + transCodeChName +
			", mchCodeIn=" + mchCodeIn +
			", mchNameOut=" + mchNameOut +
			", acqInsId=" + acqInsId +
			", acqInsSeq=" + acqInsSeq +
			", locTracsTime=" + locTracsTime +
			", locTracsDate=" + locTracsDate +
			", termTransTime=" + termTransTime +
			", termTransDate=" + termTransDate +
			", cardCity=" + cardCity +
			", csn=" + csn +
			", cardSeqNo=" + cardSeqNo +
			", cardCounter=" + cardCounter +
			", cardType=" + cardType +
			", cardPhyType=" + cardPhyType +
			", orderNo=" + orderNo +
			", state=" + state +
			", cardBramdid=" + cardBramdid +
			", cardClass=" + cardClass +
			", orderType=" + orderType +
			", tradeStatus=" + tradeStatus +
			", serialNum=" + serialNum +
			", cardno=" + cardno +
			", tradeTypeFlag=" + tradeTypeFlag +
			", cpuCounter=" + cpuCounter +
			", cardIisCode=" + cardIisCode +
			", timeCardType=" + timeCardType +
			", markTime=" + markTime +
			", markCityCode=" + markCityCode +
			", markIisCode=" + markIisCode +
			", markCsCode=" + markCsCode +
			", markLineId=" + markLineId +
			", markStation=" + markStation +
			", markBusId=" + markBusId +
			", downCsCode=" + downCsCode +
			", downLineId=" + downLineId +
			", downStation=" + downStation +
			", downBusId=" + downBusId +
			", takenLength=" + takenLength +
			", dirFlag=" + dirFlag +
			", driverCode=" + driverCode +
			", sellorCode=" + sellorCode +
			", sellorCode2=" + sellorCode2 +
			", posId=" + posId +
			", posTradeSeq=" + posTradeSeq +
			", posDate=" + posDate +
			", tac=" + tac +
			", tacMode=" + tacMode +
			", psamId=" + psamId +
			", psamSeq=" + psamSeq +
			", chargeActual=" + chargeActual +
			", cardBalance=" + cardBalance +
			", preCardBalance=" + preCardBalance +
			", chargeIdeal=" + chargeIdeal +
			", keyVer=" + keyVer +
			", keyIndex=" + keyIndex +
			", keyReqNum=" + keyReqNum +
			", authSeq=" + authSeq +
			", checkCode=" + checkCode +
			", recordType=" + recordType +
			", cardFlag=" + cardFlag +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", cardUpload=" + cardUpload +
			", tokenId=" + tokenId +
			", userPhone=" + userPhone +
			", tokenVersion=" + tokenVersion +
			", cityInLength=" + cityInLength +
			", cityOutLength=" + cityOutLength +
			", cityInRebate=" + cityInRebate +
			", cityOutRebate=" + cityOutRebate +
			", downTime=" + downTime +
			", markStationName=" + markStationName +
			", downStationName=" + downStationName +
			", cityInDiscount=" + cityInDiscount +
			", lineName=" + lineName +
			", cityOutDiscount=" + cityOutDiscount +
			", ntOrderId=" + ntOrderId +
			", channelType=" + channelType +
			", channelId=" + channelId +
			", orderDate=" + orderDate +
			", tradeType=" + tradeType +
			", porderId=" + porderId +
			", posMarkTransSeq=" + posMarkTransSeq +
			", posDownTransSeq=" + posDownTransSeq +
			", markPasmId=" + markPasmId +
			", downPasmId=" + downPasmId +
			", markPosId=" + markPosId +
			", downPosId=" + downPosId +
			", markTokenId=" + markTokenId +
			", downTokenId=" + downTokenId +
			", transState=" + transState +
			", thirdInstid=" + thirdInstid +
			", thirdChannelid=" + thirdChannelid +
			", transcode=" + transcode +
			", transmsg=" + transmsg +
			", errType=" + errType +
			", errDesc=" + errDesc +
			"}";
	}
}
