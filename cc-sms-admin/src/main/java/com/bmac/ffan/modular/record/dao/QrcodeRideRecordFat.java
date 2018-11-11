package com.bmac.ffan.modular.record.dao;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/1/9 17:29
 */
public class QrcodeRideRecordFat {

    private static final long serialVersionUID = 1L;

    /**
     * 平台流水号
     */
    private String pltSsn;
    /**
     * 请求方流水号
     */
    private String reqSsn;
    /**
     * 本地清算日期
     */
    private String settleDateLoc;
    /**
     * 接收日期
     */
    private String transRecvDate;
    /**
     * 接收时间
     */
    private String transRecvTime;
    /**
     * 外部交易代码
     */
    private String transCodeOut;
    /**
     * 渠道交易代码
     */
    private String transCodeChnl;
    /**
     * 交易代码名称
     */
    private String transCodeChName;
    /**
     * 商户代码
     */
    private String mchCodeIn;
    /**
     * 商户名称
     */
    private String mchNameOut;
    /**
     * 受理机构
     */
    private String acqInsId;
    /**
     * 受理机构流水号
     */
    private String acqInsSeq;
    /**
     * 本地交易时间
     */
    private String locTracsTime;
    /**
     * 本地交易日期
     */
    private String locTracsDate;
    /**
     * 终端交易时间
     */
    private String termTransTime;
    /**
     * 终端交易日期
     */
    private String termTransDate;
    /**
     * 城市代码
     */
    private String cardCity;
    /**
     * CSN
     */
    private String csn;
    /**
     * 卡序列号
     */
    private String cardSeqNo;
    /**
     * 卡累计交易次数
     */
    private String cardCounter;
    /**
     * 交易卡类型
     */
    private String cardType;
    /**
     * 卡物理类型
     */
    private String cardPhyType;
    /**
     * 平台订单号
     */
    private String orderNo;
    /**
     * 订单状态 1已匹配 0 未匹配
     */
    private String state;
    /**
     * 卡行业代码
     */
    private String cardBramdid;
    /**
     * 用户ID
     */
    private String cardClass;
    /**
     * 交易类型
     */
    private String orderType;
    /**
     * 补票交易状态
     */
    private String tradeStatus;
    /**
     * 记录顺序编号
     */
    private String serialNum;
    /**
     * 套票绑定的卡号
     */
    private String cardno;
    /**
     * 交易类型标识
     */
    private String tradeTypeFlag;
    /**
     * 电子钱包脱机交易序号
     */
    private String cpuCounter;
    /**
     * 发卡机构代码
     */
    private String cardIisCode;
    /**
     * 时间票类型
     */
    private String timeCardType;
    /**
     * 上车时间
     */
    private String markTime;
    /**
     * 上车城市代码
     */
    private String markCityCode;
    /**
     * 上车受理机构代码
     */
    private String markIisCode;
    /**
     * 上车运营商代码
     */
    private String markCsCode;
    /**
     * 上车线路
     */
    private String markLineId;
    /**
     * 上车站
     */
    private String markStation;
    /**
     * 上车车辆号
     */
    private String markBusId;
    /**
     * 下车运营商代码
     */
    private String downCsCode;
    /**
     * 下车线路
     */
    private String downLineId;
    /**
     * 下车站
     */
    private String downStation;
    /**
     * 下车车辆号
     */
    private String downBusId;
    /**
     * 乘车里程
     */
    private String takenLength;
    /**
     * 上下行标志
     */
    private String dirFlag;
    /**
     * 司机ID
     */
    private String driverCode;
    /**
     * 监票员ID
     */
    private String sellorCode;
    /**
     * 监票员ID2
     */
    private String sellorCode2;
    /**
     * 支付提供方代码
     */
    private String posId;
    /**
     * POS机交易流水号
     */
    private String posTradeSeq;
    /**
     * POS机交易时间
     */
    private String posDate;
    /**
     * 交易TAC
     */
    private String tac;
    /**
     * TAC算法标识
     */
    private String tacMode;
    /**
     * PSAM ID
     */
    private String psamId;
    /**
     * PSAM交易流水号
     */
    private String psamSeq;
    /**
     * 交易金额
     */
    private String chargeActual;
    /**
     * 卡内余额
     */
    private String cardBalance;
    /**
     * 交易前卡内余额
     */
    private String preCardBalance;
    /**
     * 应收金额
     */
    private String chargeIdeal;
    /**
     * 密钥版本号
     */
    private String keyVer;
    /**
     * 密钥索引号
     */
    private String keyIndex;
    /**
     * 密钥请求次数
     */
    private Integer keyReqNum;
    /**
     * 密钥授权流水号
     */
    private String authSeq;
    /**
     * 记录检验码
     */
    private String checkCode;
    /**
     * 交易记录类型：0-景点，1-观光车或公交
     */
    private String recordType;
    /**
     * 卡片标识
     */
    private String cardFlag;
    /**
     * 创建时间;默认为记录插入时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    private Integer cardUpload;
    /**
     * tokenId
     */
    private String tokenId;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * token版本号
     */
    private String markBusName;
    private String markStationName;
    private String tokenVersion;
    private String lineName;
    private String thirdInstid;
    private String thirdChannelid;

    private String markLineName;
    private String downLineName;
    private String downStaName;
    private String markStaName;

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

    public String getMarkBusName() {
        return markBusName;
    }

    public void setMarkBusName(String markBusName) {
        this.markBusName = markBusName;
    }

    public String getMarkStationName() {
        return markStationName;
    }

    public void setMarkStationName(String markStationName) {
        this.markStationName = markStationName;
    }

    public String getTokenVersion() {
        return tokenVersion;
    }

    public void setTokenVersion(String tokenVersion) {
        this.tokenVersion = tokenVersion;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
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

    public String getMarkLineName() {
        return markLineName;
    }

    public void setMarkLineName(String markLineName) {
        this.markLineName = markLineName;
    }

    public String getDownLineName() {
        return downLineName;
    }

    public void setDownLineName(String downLineName) {
        this.downLineName = downLineName;
    }

    public String getDownStaName() {
        return downStaName;
    }

    public void setDownStaName(String downStaName) {
        this.downStaName = downStaName;
    }

    public String getMarkStaName() {
        return markStaName;
    }

    public void setMarkStaName(String markStaName) {
        this.markStaName = markStaName;
    }
}
