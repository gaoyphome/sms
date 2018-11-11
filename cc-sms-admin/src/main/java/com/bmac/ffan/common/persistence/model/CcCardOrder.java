package com.bmac.ffan.common.persistence.model;

import java.io.Serializable;

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
@TableName("cc_card_order")
public class CcCardOrder extends Model<CcCardOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 平台流水号
     */
    @TableId("plt_ssn")
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
	private String transRecvDate;
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
     * 订单状态
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
     * 交易收费记录格式  计次 计费
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
     * 交易记录类型 4实体卡交易 5本地互通卡 6 异地互通卡
     */
	@TableField("reserved_type")
	private String reservedType;


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

	public String getTransRecvDate() {
		return transRecvDate;
	}

	public void setTransRecvDate(String transRecvDate) {
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

	public String getReservedType() {
		return reservedType;
	}

	public void setReservedType(String reservedType) {
		this.reservedType = reservedType;
	}

	@Override
	protected Serializable pkVal() {
		return this.pltSsn;
	}

	@Override
	public String toString() {
		return "CcCardOrder{" +
			"pltSsn=" + pltSsn +
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
			", reservedType=" + reservedType +
			"}";
	}
}
