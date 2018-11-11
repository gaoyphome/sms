/**
 * 初始化套票订单 详情对话框
 */
var TicketOrderInfoDlg = {
    ticketOrderInfoData : {}
};

/**
 * 清除数据
 */
TicketOrderInfoDlg.clearData = function() {
    this.ticketOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketOrderInfoDlg.set = function(key, val) {
    this.ticketOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TicketOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.TicketOrder.layerIndex);
}

/**
 * 收集数据
 */
TicketOrderInfoDlg.collectData = function() {
	      this.set('pltSsn');
	      this.set('reqSsn');
	      this.set('settleDateLoc');
	      this.set('transRecvDate');
	      this.set('transRecvTime');
	      this.set('transCodeOut');
	      this.set('transCodeChnl');
	      this.set('transCodeChName');
	      this.set('mchCodeIn');
	      this.set('mchNameOut');
	      this.set('acqInsId');
	      this.set('acqInsSeq');
	      this.set('locTracsTime');
	      this.set('locTracsDate');
	      this.set('termTransTime');
	      this.set('termTransDate');
	      this.set('cardCity');
	      this.set('txnToken');
	      this.set('orderNo');
	      this.set('state');
	      this.set('ntorderid');
	      this.set('userId');
	      this.set('orderType');
	      this.set('txnType');
	      this.set('ticketId');
	      this.set('cardno');
	      this.set('agentId');
	      this.set('mchntId');
	      this.set('spotId');
	      this.set('termId');
	      this.set('sesq');
	      this.set('totalCnt');
	      this.set('useCnt');
	      this.set('posId');
	      this.set('posTradeSeq');
	      this.set('posDate');
	      this.set('tac');
	      this.set('tacMode');
	      this.set('psamId');
	      this.set('psamSeq');
	      this.set('keyVer');
	      this.set('keyIndex');
	      this.set('keyReqNum');
	      this.set('authSeq');
	      this.set('createTime');
	      this.set('updateTime');
	      this.set('chargeActual');
	      this.set('cardBalance');
	      this.set('cardCounter');
	      this.set('cardType');
	      this.set('cardPhyType');
	      this.set('preCardBalance');
	      this.set('serialNum');
	      this.set('cpuCounter');
	      this.set('chargeIdeal');
	      this.set('ticketUpload');
	      this.set('productCode');
}

/**
 * 提交添加
 */
TicketOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.TicketOrder.table.refresh();
        TicketOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TicketOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.TicketOrder.table.refresh();
        TicketOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketOrderInfoData);
    ajax.start();
}

$(function() {

});
