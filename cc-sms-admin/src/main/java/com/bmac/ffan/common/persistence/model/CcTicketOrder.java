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
@TableName("cc_ticket_order")
public class CcTicketOrder extends Model<CcTicketOrder> {

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
     * 交易TOKEN
     */
	@TableField("txn_token")
	private String txnToken;
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
     * 网络平台订单号
     */
	private String ntorderid;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 订单交易类型
     */
	@TableField("order_type")
	private String orderType;
    /**
     * 交易类型：实体卡或二维码
     */
	@TableField("txn_type")
	private String txnType;
    /**
     * 套票ID
     */
	@TableField("ticket_id")
	private String ticketId;
    /**
     * 套票绑定的卡号
     */
	private String cardno;
    /**
     * 代理商ID
     */
	@TableField("agent_id")
	private String agentId;
    /**
     * 套票商户ID
     */
	@TableField("mchnt_id")
	private String mchntId;
    /**
     * 套票景点ID
     */
	@TableField("spot_id")
	private String spotId;
    /**
     * 景点门店ID
     */
	@TableField("term_id")
	private String termId;
    /**
     * 网络平台的流水号
     */
	private String sesq;
    /**
     * 总次数
     */
	@TableField("total_cnt")
	private Integer totalCnt;
    /**
     * 已使用次数
     */
	@TableField("use_cnt")
	private Integer useCnt;
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
     * 创建时间;默认为记录插入时间
     */
	@TableField("create_time")
	private String createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private String updateTime;
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
     * 卡交易计数
     */
	@TableField("card_counter")
	private String cardCounter;
    /**
     * 卡类型
     */
	@TableField("card_type")
	private String cardType;
    /**
     * 卡物理类型
     */
	@TableField("card_phy_type")
	private String cardPhyType;
    /**
     * 交易前余额
     */
	@TableField("pre_card_balance")
	private String preCardBalance;
    /**
     * 记录序号
     */
	@TableField("serial_num")
	private String serialNum;
    /**
     * 钱包交易序号
     */
	@TableField("cpu_counter")
	private String cpuCounter;
    /**
     * 票价
     */
	@TableField("charge_ideal")
	private String chargeIdeal;
	@TableField("ticket_upload")
	private Integer ticketUpload;
    /**
     * 旅游产品编码
     */
	@TableField("product_code")
	private String productCode;


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

	public String getTxnToken() {
		return txnToken;
	}

	public void setTxnToken(String txnToken) {
		this.txnToken = txnToken;
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

	public String getNtorderid() {
		return ntorderid;
	}

	public void setNtorderid(String ntorderid) {
		this.ntorderid = ntorderid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getMchntId() {
		return mchntId;
	}

	public void setMchntId(String mchntId) {
		this.mchntId = mchntId;
	}

	public String getSpotId() {
		return spotId;
	}

	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getSesq() {
		return sesq;
	}

	public void setSesq(String sesq) {
		this.sesq = sesq;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	public Integer getUseCnt() {
		return useCnt;
	}

	public void setUseCnt(Integer useCnt) {
		this.useCnt = useCnt;
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

	public String getPreCardBalance() {
		return preCardBalance;
	}

	public void setPreCardBalance(String preCardBalance) {
		this.preCardBalance = preCardBalance;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getCpuCounter() {
		return cpuCounter;
	}

	public void setCpuCounter(String cpuCounter) {
		this.cpuCounter = cpuCounter;
	}

	public String getChargeIdeal() {
		return chargeIdeal;
	}

	public void setChargeIdeal(String chargeIdeal) {
		this.chargeIdeal = chargeIdeal;
	}

	public Integer getTicketUpload() {
		return ticketUpload;
	}

	public void setTicketUpload(Integer ticketUpload) {
		this.ticketUpload = ticketUpload;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	protected Serializable pkVal() {
		return this.pltSsn;
	}

	@Override
	public String toString() {
		return "CcTicketOrder{" +
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
			", txnToken=" + txnToken +
			", orderNo=" + orderNo +
			", state=" + state +
			", ntorderid=" + ntorderid +
			", userId=" + userId +
			", orderType=" + orderType +
			", txnType=" + txnType +
			", ticketId=" + ticketId +
			", cardno=" + cardno +
			", agentId=" + agentId +
			", mchntId=" + mchntId +
			", spotId=" + spotId +
			", termId=" + termId +
			", sesq=" + sesq +
			", totalCnt=" + totalCnt +
			", useCnt=" + useCnt +
			", posId=" + posId +
			", posTradeSeq=" + posTradeSeq +
			", posDate=" + posDate +
			", tac=" + tac +
			", tacMode=" + tacMode +
			", psamId=" + psamId +
			", psamSeq=" + psamSeq +
			", keyVer=" + keyVer +
			", keyIndex=" + keyIndex +
			", keyReqNum=" + keyReqNum +
			", authSeq=" + authSeq +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", chargeActual=" + chargeActual +
			", cardBalance=" + cardBalance +
			", cardCounter=" + cardCounter +
			", cardType=" + cardType +
			", cardPhyType=" + cardPhyType +
			", preCardBalance=" + preCardBalance +
			", serialNum=" + serialNum +
			", cpuCounter=" + cpuCounter +
			", chargeIdeal=" + chargeIdeal +
			", ticketUpload=" + ticketUpload +
			", productCode=" + productCode +
			"}";
	}
}
