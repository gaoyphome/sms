/**
 * 乘车交易异常信息表管理初始化
 */
var QrcodeRideTransError = {
    id: "QrcodeRideTransErrorTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeRideTransError.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '平台流水号', field: 'pltSsn', visible: false, align: 'center', valign: 'middle'},
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
       	 {title: '平台订单号', field: 'orderNo', align: 'center', valign: 'middle'},
       	 {title: '订单支付状态', field: 'state', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡行业代码', field: 'cardBramdid', visible: false, align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'cardClass', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易类型', field: 'orderType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '补票交易状态', field: 'tradeStatus', visible: false, align: 'center', valign: 'middle'},
       	 {title: '记录顺序编号', field: 'serialNum', visible: false, align: 'center', valign: 'middle'},
       	 {title: '套票绑定的卡号', field: 'cardno', align: 'center', valign: 'middle'},
       	 {title: '交易类型标识', field: 'tradeTypeFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '电子钱包脱机交易序号', field: 'cpuCounter', visible: false, align: 'center', valign: 'middle'},
       	 {title: '发卡机构代码', field: 'cardIisCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '时间票类型', field: 'timeCardType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车时间', field: 'markTime', align: 'center', valign: 'middle'},
       	 {title: '上车城市代码', field: 'markCityCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车受理机构代码', field: 'markIisCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车运营商代码', field: 'markCsCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车线路', field: 'markLineId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车站', field: 'markStation', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车车辆号', field: 'markBusId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车运营商代码', field: 'downCsCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车线路', field: 'downLineId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车站', field: 'downStation', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车车辆号', field: 'downBusId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '乘车里程', field: 'takenLength', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上下行标志', field: 'dirFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '司机ID', field: 'driverCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '监票员ID', field: 'sellorCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '监票员ID2', field: 'sellorCode2', visible: false, align: 'center', valign: 'middle'},
       	 {title: '支付提供方代码', field: 'posId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机交易流水号', field: 'posTradeSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机交易时间', field: 'posDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易TAC', field: 'tac', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'TAC算法标识', field: 'tacMode', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'PSAM ID', field: 'psamId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'PSAM交易流水号', field: 'psamSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易金额', field: 'chargeActual', visible: false, align: 'center', valign: 'middle'},
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

       	 {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'cardUpload', field: 'cardUpload', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'tokenId', field: 'tokenId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '用户手机号', field: 'userPhone', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'token版本号', field: 'tokenVersion', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市内里程', field: 'cityInLength', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市外里程', field: 'cityOutLength', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市内折扣率', field: 'cityInRebate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '市外折扣率', field: 'cityOutRebate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车时间', field: 'downTime', align: 'center', valign: 'middle'},
       	 {title: '上车站名', field: 'markStationName', align: 'center', valign: 'middle'},
       	 {title: '下车站名', field: 'downStationName', align: 'center', valign: 'middle'},
       	 {title: '市内优惠额度', field: 'cityInDiscount', visible: false, align: 'center', valign: 'middle'},
       	 {title: '线路名', field: 'lineName', align: 'center', valign: 'middle'},
       	 {title: '市外优惠额度', field: 'cityOutDiscount', visible: false, align: 'center', valign: 'middle'},
       	 {title: '网络平台订单号', field: 'ntOrderId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'channelType', field: 'channelType', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'channelId', field: 'channelId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'orderDate', field: 'orderDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '一卡通后台账户', field: 'tradeType', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'porderId', field: 'porderId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'posMarkTransSeq', field: 'posMarkTransSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'posDownTransSeq', field: 'posDownTransSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'markPasmId', field: 'markPasmId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'downPasmId', field: 'downPasmId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'markPosId', field: 'markPosId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'downPosId', field: 'downPosId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'markTokenId', field: 'markTokenId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'downTokenId', field: 'downTokenId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易状态', field: 'transState', align: 'center', valign: 'middle'},
       	 {title: 'thirdInstid', field: 'thirdInstid', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'thirdChannelid', field: 'thirdChannelid', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'transcode', field: 'transcode', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'transmsg', field: 'transmsg', visible: false, align: 'center', valign: 'middle'},
       	 {title: '错误类型', field: 'errType',visible: false, align: 'center', valign: 'middle'},
       	 {title: '错误描述', field: 'errDesc', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
QrcodeRideTransError.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeRideTransError.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加乘车交易异常信息表
 */
QrcodeRideTransError.openAddQrcodeRideTransError = function () {
    var index = layer.open({
        type: 2,
        title: '添加乘车交易异常信息表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeRideTransError/qrcodeRideTransError_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看乘车交易异常信息表详情
 */
QrcodeRideTransError.openQrcodeRideTransErrorDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '乘车交易异常信息表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeRideTransError/qrcodeRideTransError_update/' + QrcodeRideTransError.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除乘车交易异常信息表
 */
QrcodeRideTransError.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeRideTransError/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeRideTransError.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrcodeRideTransErrorId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询乘车交易异常信息表列表
 */
QrcodeRideTransError.search = function () {
    var queryData = {};
	queryData['cardno'] = $("#cardno").val();
	queryData['order_no'] = $("#order_no").val();
    queryData['startTermTransDate'] = $("#startTermTransDate").val();
    queryData['endTermTransTime'] = $("#endTermTransTime").val();
    QrcodeRideTransError.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeRideTransError.formParams = function() {
    var queryData = {};
	queryData['cardno'] = $("#cardno").val();
	queryData['order_no'] = $("#order_no").val();
    queryData['startTermTransDate'] = $("#startTermTransDate").val();
    queryData['endTermTransTime'] = $("#endTermTransTime").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeRideTransError.initColumn();
    var table = new BSTable(QrcodeRideTransError.id, "/qrcodeRideTransError/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeRideTransError.formParams());
    QrcodeRideTransError.table = table.init();
});
