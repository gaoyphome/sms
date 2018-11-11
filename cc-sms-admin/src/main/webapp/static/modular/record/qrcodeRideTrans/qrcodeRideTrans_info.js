/**
 * 初始化二维码乘车交易记录 详情对话框
 */
var QrcodeRideTransInfoDlg = {
    qrcodeRideTransInfoData : {}
};

/**
 * 清除数据
 */
QrcodeRideTransInfoDlg.clearData = function() {
    this.qrcodeRideTransInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeRideTransInfoDlg.set = function(key, val) {
    this.qrcodeRideTransInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeRideTransInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeRideTransInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeRideTrans.layerIndex);
}

/**
 * 收集数据
 */
QrcodeRideTransInfoDlg.collectData = function() {
    	  this.set('id');
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
	      this.set('tokenVersion');
	      this.set('cityInLength');
	      this.set('cityOutLength');
	      this.set('cityInRebate');
	      this.set('cityOutRebate');
	      this.set('downTime');
	      this.set('markStationName');
	      this.set('downStationName');
	      this.set('cityInDiscount');
	      this.set('lineName');
	      this.set('cityOutDiscount');
	      this.set('ntOrderId');
	      this.set('channelType');
	      this.set('channelId');
	      this.set('orderDate');
	      this.set('tradeType');
	      this.set('porderId');
	      this.set('posMarkTransSeq');
	      this.set('posDownTransSeq');
	      this.set('markPasmId');
	      this.set('downPasmId');
	      this.set('markPosId');
	      this.set('downPosId');
	      this.set('markTokenId');
	      this.set('downTokenId');
	      this.set('transState');
	      this.set('thirdInstid');
	      this.set('thirdChannelid');
}

/**
 * 提交扣款
 */
QrcodeRideTransInfoDlg.recharge = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeRideTrans/recharge", function(data){
        if(data['code'] != 200){
            Feng.error("操作失败:" + data['message'] + "!");
        }else{
            Feng.success("扣款成功!");
            window.parent.QrcodeRideTrans.table.refresh();
        }
        QrcodeRideTransInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeRideTransInfoData);
    ajax.start();
}
/**
 * 提交添加
 */
QrcodeRideTransInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeRideTrans/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeRideTrans.table.refresh();
        QrcodeRideTransInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeRideTransInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeRideTransInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeRideTrans/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeRideTrans.table.refresh();
        QrcodeRideTransInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeRideTransInfoData);
    ajax.start();
}


QrcodeRideTransInfoDlg.swithPrice = function(flag){
    if(flag){
        $("#recorddown").hide(1000);
        $("#recordmark").show(1000);
     //   $("#recordmark")[0].style.display = 'none';
        $("#switchfalg").children("li").eq(0).addClass("active");
        $("#switchfalg").children("li").eq(1).removeClass("active");
    }else{

        $("#recordmark").hide(1000);
        $("#recorddown").show(1000);
    //    $("#recordmark")[0].style.display = 'block';
        $("#switchfalg").children("li").eq(0).removeClass("active");
        $("#switchfalg").children("li").eq(1).addClass("active");
    }
}

$(function() {

});
