/**
 * 二维码乘车交易记录 管理初始化
 */
var QrcodeRideTrans = {
    id: "QrcodeRideTransTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeRideTrans.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '套票绑定的卡号', field: 'cardno', align: 'center', valign: 'middle'},
        {title: '用户手机号', field: 'userPhone', align: 'center', valign: 'middle'},
        {title: '上车站名', field: 'markStationName',visible: false, align: 'center', valign: 'middle'},
        {title: '  下车站名  ', field: 'downStationName', visible: false, align: 'center', valign: 'middle'},
        {title: '上车信息', field: 'markInfo', align: 'center', valign: 'middle'},
        {title: '下车信息', field: 'downInfo', align: 'center', valign: 'middle'},
        {title: '线路名称', field: 'lineName',  align: 'center', valign: 'middle'},
        {title: '订单支付状态', field: 'stateName',align: 'center', valign: 'middle'},
        {title: '补票交易状态', field: 'tradeStatus',visible: false,  align: 'center', valign: 'middle'},
        {title: '交易状态', field: 'transStateName', align: 'center', valign: 'middle'},

       	 {title: '平台流水号', field: 'pltSsn',visible: false,  align: 'center', valign: 'middle'},
       	 {title: '请求方流水号', field: 'reqSsn', visible: false, align: 'center', valign: 'middle'},
       	 {title: '本地清算日期', field: 'settleDateLoc', visible: false, align: 'center', valign: 'middle'},
       	 {title: '接收日期', field: 'transRecvDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '接收时间', field: 'transRecvTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '外部交易代码', field: 'transCodeOut', visible: false, align: 'center', valign: 'middle'},
       	 {title: '渠道交易代码', field: 'transCodeChnl', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易代码名称', field: 'transCodeChName', visible: false, align: 'center', valign: 'middle'},
       	 {title: '商户代码', field: 'mchCodeIn', visible: false, align: 'center', valign: 'middle'},
       	 {title: '商户名称', field: 'mchNameOut', visible: false, align: 'center', valign: 'middle'},
       	 {title: '受理机构', field: 'acqInsId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '受理机构流水号', field: 'acqInsSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '本地交易时间', field: 'locTracsTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '本地交易日期', field: 'locTracsDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '终端交易时间', field: 'termTransTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '终端交易日期', field: 'termTransDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '城市代码', field: 'cardCity', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'CSN', field: 'csn', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡序列号', field: 'cardSeqNo', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡累计交易次数', field: 'cardCounter', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易卡类型', field: 'cardType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡物理类型', field: 'cardPhyType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易单号', field: 'orderNo', align: 'center', valign: 'middle'},

       	 {title: '卡行业代码', field: 'cardBramdid', visible: false, align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'cardClass', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易类型', field: 'orderType', visible: false, align: 'center', valign: 'middle'},

       	 {title: '记录顺序编号', field: 'serialNum', visible: false, align: 'center', valign: 'middle'},

       	 {title: '交易类型标识', field: 'tradeTypeFlag', align: 'center', valign: 'middle'},
       	 {title: '脱机交易序号', field: 'cpuCounter', visible: false, align: 'center', valign: 'middle'},
       	 {title: '发卡机构代码', field: 'cardIisCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '时间票类型', field: 'timeCardType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车时间', field: 'markTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车城市代码', field: 'markCityCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车受理机构代码', field: 'markIisCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车运营商代码', field: 'markCsCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车路线', field: 'markLineId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车站点', field: 'markStation', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车车辆号', field: 'markBusId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车运营商代码', field: 'downCsCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车路线', field: 'downLineId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车站点', field: 'downStation',  visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车车辆号', field: 'downBusId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '乘车里程', field: 'takenLength', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上下行标志', field: 'dirFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '司机ID', field: 'driverCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '监票员ID', field: 'sellorCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '监票员ID2', field: 'sellorCode2', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS-ID', field: 'posId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机交易流水号', field: 'posTradeSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机交易时间', field: 'posDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易TAC', field: 'tac', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'TAC算法标识', field: 'tacMode', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'PSAM ID', field: 'psamId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'PSAM交易流水号', field: 'psamSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易金额', field: 'chargeActual',align: 'center', valign: 'middle'},
       	 {title: '卡内余额', field: 'cardBalance', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易前卡内余额', field: 'preCardBalance', visible: false, align: 'center', valign: 'middle'},
       	 {title: '应收金额', field: 'chargeIdeal', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥版本号', field: 'keyVer', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥索引号', field: 'keyIndex', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥请求次数', field: 'keyReqNum', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥授权流水号', field: 'authSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '记录检验码', field: 'checkCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易记录类型', field: 'recordType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡片标识', field: 'cardFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime',align: 'center', valign: 'middle'},
       	 {title: 'cardUpload', field: 'cardUpload', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'TokenId', field: 'tokenId', visible: false, align: 'center', valign: 'middle'},

       	 {title: 'Token版本号', field: 'tokenVersion', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市内里程', field: 'cityInLength', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市外里程', field: 'cityOutLength', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市内折扣率', field: 'cityInRebate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市外折扣率', field: 'cityOutRebate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车站时间', field: 'downTime', visible: false, align: 'center', valign: 'middle'},

       	 {title: '市内优惠额度', field: 'cityInDiscount', visible: false, align: 'center', valign: 'middle'},

       	 {title: '市外优惠额度', field: 'cityOutDiscount', visible: false, align: 'center', valign: 'middle'},
       	 {title: '网络平台订单号', field: 'ntOrderId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'channelType', field: 'channelType', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'channelId', field: 'channelId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'orderDate', field: 'orderDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '支付类型', field: 'tradeType',  visible: false,align: 'center', valign: 'middle'},
       	 {title: 'porderId', field: 'porderId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'posMarkTransSeq', field: 'posMarkTransSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'posDownTransSeq', field: 'posDownTransSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'markPasmId', field: 'markPasmId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'downPasmId', field: 'downPasmId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'markPosId', field: 'markPosId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'downPosId', field: 'downPosId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'markTokenId', field: 'markTokenId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'downTokenId', field: 'downTokenId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'thirdInstid', field: 'thirdInstid', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'thirdChannelid', field: 'thirdChannelid', visible: false, align: 'center', valign: 'middle'},

        {title: '操作', field: 'content', align: 'center', valign: 'middle',
            formatter:function(value,row,index) {
                //var e = '<a href="/posFileBlacklist/download?id='+row.id+'" mce_href="#1">重试</a> ';
                if (row.state == "6" || (row.state == "0" && row.transState == "1")){
                    var e = ' <a onclick="QrcodeRideTrans.openRechargeDetail('+row.id+')">扣款</a> ';
					return e;
            	}else{
                	return "";
				}
            } },
    ];
};

/**
 * 打开扣款确认页面
 */
QrcodeRideTrans.openRechargeDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '扣款确认页面',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeRideTrans/qrcodeRideTrans_confirm/' + id
        });
        this.layerIndex = index;
};

/**
 * 检查是否选中
 */
QrcodeRideTrans.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeRideTrans.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码乘车交易记录
 */
QrcodeRideTrans.openAddQrcodeRideTrans = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码乘车交易记录 ',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeRideTrans/qrcodeRideTrans_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码乘车交易记录 详情
 */
QrcodeRideTrans.openQrcodeRideTransDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码乘车交易记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeRideTrans/qrcodeRideTrans_detail/' + QrcodeRideTrans.seItem.pltSsn
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看二维码乘车记录 详情
 */
QrcodeRideTrans.openQrcodeRideTransRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码乘车记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeRideTrans/recordDetail/' + QrcodeRideTrans.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码乘车交易记录
 */
QrcodeRideTrans.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeRideTrans/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeRideTrans.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrcodeRideTransId",this.seItem.id);
        ajax.start();
    }
};



/**
 * 查询二维码乘车交易记录 列表
 */
QrcodeRideTrans.search = function () {
    var queryData = {};
		queryData['startTermTransDate'] = $("#startTermTransDate").val();
		queryData['endTermTransTime'] = $("#endTermTransTime").val();
		queryData['cardno'] = $("#cardno").val();
		queryData['userPhone'] = $("#userPhone").val();
		queryData['lineName'] = $("#lineName").val();
    	queryData['state'] = $("#state").val();
		queryData['transState'] = $("#transState").val();
		queryData['tradeStatus'] = $("#tradeStatus").val();
    	queryData['tradeTypeFlag'] = $("#tradeTypeFlag").val();
    	queryData['posId'] = $("#posId").val();
    	queryData['orderNo'] = $("#orderNo").val();
    QrcodeRideTrans.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeRideTrans.formParams = function() {
    var queryData = {};
		queryData['startTermTransDate'] = $("#startTermTransDate").val();
		queryData['endTermTransTime'] = $("#endTermTransTime").val();
		queryData['cardno'] = $("#cardno").val();
		queryData['userPhone'] = $("#userPhone").val();
		queryData['lineName'] = $("#lineName").val();
    	queryData['state'] = $("#state").val();
		queryData['transState'] = $("#transState").val();
		queryData['tradeStatus'] = $("#tradeStatus").val();
    	queryData['tradeTypeFlag'] = $("#tradeTypeFlag").val();
    	queryData['posId'] = $("#posId").val();
    	queryData['orderNo'] = $("#orderNo").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeRideTrans.initColumn();
    var table = new BSTable(QrcodeRideTrans.id, "/qrcodeRideTrans/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeRideTrans.formParams());
    QrcodeRideTrans.table = table.init();
});
