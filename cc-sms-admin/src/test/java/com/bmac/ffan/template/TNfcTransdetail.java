package com.bmac.ffan.template;

import java.io.Serializable;

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
 * @since 2018-04-17
 */
@TableName("T_NFC_TRANSDETAIL")
public class TNfcTransdetail extends Model<TNfcTransdetail> {

    private static final long serialVersionUID = 1L;

	@TableField("TRACE")
	private Integer trace;
	@TableField("PLATDATE")
	private String platdate;
	@TableField("PLATTIME")
	private String plattime;
	@TableField("RPID")
	private String rpid;
	@TableField("FUNCODE")
	private String funcode;
	@TableField("PORDERID")
	private String porderid;
	@TableField("ORDERID")
	private String orderid;
	@TableField("ORDERDATE")
	private String orderdate;
	@TableField("ORPID")
	private String orpid;
	@TableField("MERID")
	private String merid;
	@TableField("PMERID")
	private String pmerid;
	@TableField("BANKTRACE")
	private String banktrace;
	@TableField("TRANSSEQ")
	private String transseq;
	@TableField("GOODSID")
	private String goodsid;
	@TableField("CALLED")
	private String called;
	@TableField("BANKID")
	private String bankid;
	@TableField("AGENTID")
	private String agentid;
	@TableField("CHNLID")
	private String chnlid;
	@TableField("USERID")
	private String userid;
	@TableField("CALLING")
	private String calling;
	@TableField("MOBILEID")
	private String mobileid;
	@TableField("SERVID")
	private String servid;
	@TableField("PAYSTATE")
	private Integer paystate;
	@TableField("AMTTYPE")
	private String amttype;
	@TableField("ORIGAMOUNT")
	private Integer origamount;
	@TableField("AMOUNT")
	private Integer amount;
	@TableField("TRANSTATE")
	private Integer transtate;
	@TableField("TRANSCODE")
	private String transcode;
	@TableField("PAYUID")
	private String payuid;
	@TableField("STLDATE")
	private String stldate;
	@TableField("BANKSTLDATE")
	private String bankstldate;
	@TableField("MERCHECK")
	private Integer mercheck;
	@TableField("BANKCHECK")
	private Integer bankcheck;
	@TableField("PROVCODE")
	private String provcode;
	@TableField("AREACODE")
	private String areacode;
	@TableField("WDPLATDATE")
	private String wdplatdate;
	@TableField("WDRPID")
	private String wdrpid;
	@TableField("DETAIL")
	private String detail;
	@TableField("MERCUSTID")
	private String mercustid;
	@TableField("MERPRIV")
	private String merpriv;
	@TableField("BANKPRIV")
	private String bankpriv;
	@TableField("INTIME")
	private String intime;
	@TableField("MODTIME")
	private String modtime;
	@TableField("TRANSMODE")
	private Integer transmode;
	@TableField("RECHARGETYPE")
	private Integer rechargetype;
	@TableField("HTCARDFLAG")
	private Integer htcardflag;
	@TableField("RESERVE1")
	private String reserve1;
	@TableField("RESERVE2")
	private String reserve2;
	@TableField("ACCTYPE")
	private Integer acctype;
	@TableField("ACCNO")
	private String accno;
	@TableField("TRADESTEP")
	private Integer tradestep;
	@TableField("CHANNELID")
	private String channelid;


	public Integer getTrace() {
		return trace;
	}

	public void setTrace(Integer trace) {
		this.trace = trace;
	}

	public String getPlatdate() {
		return platdate;
	}

	public void setPlatdate(String platdate) {
		this.platdate = platdate;
	}

	public String getPlattime() {
		return plattime;
	}

	public void setPlattime(String plattime) {
		this.plattime = plattime;
	}

	public String getRpid() {
		return rpid;
	}

	public void setRpid(String rpid) {
		this.rpid = rpid;
	}

	public String getFuncode() {
		return funcode;
	}

	public void setFuncode(String funcode) {
		this.funcode = funcode;
	}

	public String getPorderid() {
		return porderid;
	}

	public void setPorderid(String porderid) {
		this.porderid = porderid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getOrpid() {
		return orpid;
	}

	public void setOrpid(String orpid) {
		this.orpid = orpid;
	}

	public String getMerid() {
		return merid;
	}

	public void setMerid(String merid) {
		this.merid = merid;
	}

	public String getPmerid() {
		return pmerid;
	}

	public void setPmerid(String pmerid) {
		this.pmerid = pmerid;
	}

	public String getBanktrace() {
		return banktrace;
	}

	public void setBanktrace(String banktrace) {
		this.banktrace = banktrace;
	}

	public String getTransseq() {
		return transseq;
	}

	public void setTransseq(String transseq) {
		this.transseq = transseq;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getCalled() {
		return called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getChnlid() {
		return chnlid;
	}

	public void setChnlid(String chnlid) {
		this.chnlid = chnlid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCalling() {
		return calling;
	}

	public void setCalling(String calling) {
		this.calling = calling;
	}

	public String getMobileid() {
		return mobileid;
	}

	public void setMobileid(String mobileid) {
		this.mobileid = mobileid;
	}

	public String getServid() {
		return servid;
	}

	public void setServid(String servid) {
		this.servid = servid;
	}

	public Integer getPaystate() {
		return paystate;
	}

	public void setPaystate(Integer paystate) {
		this.paystate = paystate;
	}

	public String getAmttype() {
		return amttype;
	}

	public void setAmttype(String amttype) {
		this.amttype = amttype;
	}

	public Integer getOrigamount() {
		return origamount;
	}

	public void setOrigamount(Integer origamount) {
		this.origamount = origamount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTranstate() {
		return transtate;
	}

	public void setTranstate(Integer transtate) {
		this.transtate = transtate;
	}

	public String getTranscode() {
		return transcode;
	}

	public void setTranscode(String transcode) {
		this.transcode = transcode;
	}

	public String getPayuid() {
		return payuid;
	}

	public void setPayuid(String payuid) {
		this.payuid = payuid;
	}

	public String getStldate() {
		return stldate;
	}

	public void setStldate(String stldate) {
		this.stldate = stldate;
	}

	public String getBankstldate() {
		return bankstldate;
	}

	public void setBankstldate(String bankstldate) {
		this.bankstldate = bankstldate;
	}

	public Integer getMercheck() {
		return mercheck;
	}

	public void setMercheck(Integer mercheck) {
		this.mercheck = mercheck;
	}

	public Integer getBankcheck() {
		return bankcheck;
	}

	public void setBankcheck(Integer bankcheck) {
		this.bankcheck = bankcheck;
	}

	public String getProvcode() {
		return provcode;
	}

	public void setProvcode(String provcode) {
		this.provcode = provcode;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getWdplatdate() {
		return wdplatdate;
	}

	public void setWdplatdate(String wdplatdate) {
		this.wdplatdate = wdplatdate;
	}

	public String getWdrpid() {
		return wdrpid;
	}

	public void setWdrpid(String wdrpid) {
		this.wdrpid = wdrpid;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMercustid() {
		return mercustid;
	}

	public void setMercustid(String mercustid) {
		this.mercustid = mercustid;
	}

	public String getMerpriv() {
		return merpriv;
	}

	public void setMerpriv(String merpriv) {
		this.merpriv = merpriv;
	}

	public String getBankpriv() {
		return bankpriv;
	}

	public void setBankpriv(String bankpriv) {
		this.bankpriv = bankpriv;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getModtime() {
		return modtime;
	}

	public void setModtime(String modtime) {
		this.modtime = modtime;
	}

	public Integer getTransmode() {
		return transmode;
	}

	public void setTransmode(Integer transmode) {
		this.transmode = transmode;
	}

	public Integer getRechargetype() {
		return rechargetype;
	}

	public void setRechargetype(Integer rechargetype) {
		this.rechargetype = rechargetype;
	}

	public Integer getHtcardflag() {
		return htcardflag;
	}

	public void setHtcardflag(Integer htcardflag) {
		this.htcardflag = htcardflag;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public Integer getAcctype() {
		return acctype;
	}

	public void setAcctype(Integer acctype) {
		this.acctype = acctype;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public Integer getTradestep() {
		return tradestep;
	}

	public void setTradestep(Integer tradestep) {
		this.tradestep = tradestep;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	@Override
	protected Serializable pkVal() {
		return null;
	}

	@Override
	public String toString() {
		return "TNfcTransdetail{" +
			"trace=" + trace +
			", platdate=" + platdate +
			", plattime=" + plattime +
			", rpid=" + rpid +
			", funcode=" + funcode +
			", porderid=" + porderid +
			", orderid=" + orderid +
			", orderdate=" + orderdate +
			", orpid=" + orpid +
			", merid=" + merid +
			", pmerid=" + pmerid +
			", banktrace=" + banktrace +
			", transseq=" + transseq +
			", goodsid=" + goodsid +
			", called=" + called +
			", bankid=" + bankid +
			", agentid=" + agentid +
			", chnlid=" + chnlid +
			", userid=" + userid +
			", calling=" + calling +
			", mobileid=" + mobileid +
			", servid=" + servid +
			", paystate=" + paystate +
			", amttype=" + amttype +
			", origamount=" + origamount +
			", amount=" + amount +
			", transtate=" + transtate +
			", transcode=" + transcode +
			", payuid=" + payuid +
			", stldate=" + stldate +
			", bankstldate=" + bankstldate +
			", mercheck=" + mercheck +
			", bankcheck=" + bankcheck +
			", provcode=" + provcode +
			", areacode=" + areacode +
			", wdplatdate=" + wdplatdate +
			", wdrpid=" + wdrpid +
			", detail=" + detail +
			", mercustid=" + mercustid +
			", merpriv=" + merpriv +
			", bankpriv=" + bankpriv +
			", intime=" + intime +
			", modtime=" + modtime +
			", transmode=" + transmode +
			", rechargetype=" + rechargetype +
			", htcardflag=" + htcardflag +
			", reserve1=" + reserve1 +
			", reserve2=" + reserve2 +
			", acctype=" + acctype +
			", accno=" + accno +
			", tradestep=" + tradestep +
			", channelid=" + channelid +
			"}";
	}
}
