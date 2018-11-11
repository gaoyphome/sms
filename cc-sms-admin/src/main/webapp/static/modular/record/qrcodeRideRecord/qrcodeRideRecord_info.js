/**
 * 初始化二维码乘车记录详情对话框
 */
var QrcodeRideRecordInfoDlg = {
    qrcodeRideRecordInfoData : {}
};

/**
 * 清除数据
 */
QrcodeRideRecordInfoDlg.clearData = function() {
    this.qrcodeRideRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeRideRecordInfoDlg.set = function(key, val) {
    this.qrcodeRideRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeRideRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeRideRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeRideRecord.layerIndex);
}

/**
 * 收集数据
 */
QrcodeRideRecordInfoDlg.collectData = function() {
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
	      this.set('csn');
	      this.set('cardSeqNo');
	      this.set('cardCounter');
	      this.set('cardType');
	      this.set('cardPhyType');
	      this.set('orderNo');
	      this.set('state');
	      this.set('cardBramdid');
	      this.set('cardClass');
	      this.set('orderType');
	      this.set('tradeStatus');
	      this.set('serialNum');
	      this.set('cardno');
	      this.set('tradeTypeFlag');
	      this.set('cpuCounter');
	      this.set('cardIisCode');
	      this.set('timeCardType');
	      this.set('markTime');
	      this.set('markCityCode');
	      this.set('markIisCode');
	      this.set('markCsCode');
	      this.set('markLineId');
	      this.set('markStation');
	      this.set('markBusId');
	      this.set('downCsCode');
	      this.set('downLineId');
	      this.set('downStation');
	      this.set('downBusId');
	      this.set('takenLength');
	      this.set('dirFlag');
	      this.set('driverCode');
	      this.set('sellorCode');
	      this.set('sellorCode2');
	      this.set('posId');
	      this.set('posTradeSeq');
	      this.set('posDate');
	      this.set('tac');
	      this.set('tacMode');
	      this.set('psamId');
	      this.set('psamSeq');
	      this.set('chargeActual');
	      this.set('cardBalance');
	      this.set('preCardBalance');
	      this.set('chargeIdeal');
	      this.set('keyVer');
	      this.set('keyIndex');
	      this.set('keyReqNum');
	      this.set('authSeq');
	      this.set('checkCode');
	      this.set('recordType');
	      this.set('cardFlag');
	      this.set('createTime');
	      this.set('updateTime');
	      this.set('cardUpload');
	      this.set('tokenId');
	      this.set('userPhone');
	      this.set('markBusName');
	      this.set('markStationName');
	      this.set('tokenVersion');
	      this.set('lineName');
	      this.set('thirdInstid');
	      this.set('thirdChannelid');
}

/**
 * 提交添加
 */
QrcodeRideRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeRideRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeRideRecord.table.refresh();
        QrcodeRideRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeRideRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeRideRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeRideRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeRideRecord.table.refresh();
        QrcodeRideRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeRideRecordInfoData);
    ajax.start();
}

$(function() {

});
