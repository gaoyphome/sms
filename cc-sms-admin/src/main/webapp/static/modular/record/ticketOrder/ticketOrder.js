/**
 * 套票订单 管理初始化
 */
var TicketOrder = {
    id: "TicketOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TicketOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: '平台流水号', field: 'pltSsn', align: 'center', valign: 'middle'},
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
       	 {title: '交易TOKEN', field: 'txnToken', visible: false, align: 'center', valign: 'middle'},
       	 {title: '平台订单号', field: 'orderNo', align: 'center', valign: 'middle'},
       	 {title: '订单状态', field: 'state', visible: false, align: 'center', valign: 'middle'},
       	 {title: '网络平台订单号', field: 'ntorderid', visible: false, align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'userId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '订单交易类型', field: 'orderType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易类型', field: 'txnType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '套票ID', field: 'ticketId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '套票绑定卡号', field: 'cardno', align: 'center', valign: 'middle'},
       	 {title: '代理商ID', field: 'agentId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '套票商户ID', field: 'mchntId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '套票景点ID', field: 'spotId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '景点门店ID', field: 'termId', align: 'center', valign: 'middle'},
       	 {title: '网络平台流水号', field: 'sesq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '总次数', field: 'totalCnt', visible: false, align: 'center', valign: 'middle'},
       	 {title: '已使用次数', field: 'useCnt', visible: false, align: 'center', valign: 'middle'},
       	 {title: '支付提供方代码', field: 'posId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机交易流水号', field: 'posTradeSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机交易时间', field: 'posDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易TAC', field: 'tac', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'TAC算法标识', field: 'tacMode', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'PSAM ID', field: 'psamId', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'PSAM交易流水号', field: 'psamSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥版本号', field: 'keyVer', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥索引号', field: 'keyIndex', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥请求次数', field: 'keyReqNum', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥授权流水号', field: 'authSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易金额', field: 'chargeActual', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡内余额', field: 'cardBalance', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡交易计数', field: 'cardCounter', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡类型', field: 'cardType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡物理类型', field: 'cardPhyType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易前余额', field: 'preCardBalance', visible: false, align: 'center', valign: 'middle'},
       	 {title: '记录序号', field: 'serialNum', visible: false, align: 'center', valign: 'middle'},
       	 {title: '钱包交易序号', field: 'cpuCounter', visible: false, align: 'center', valign: 'middle'},
       	 {title: '票价', field: 'chargeIdeal', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'ticketUpload', field: 'ticketUpload', visible: false, align: 'center', valign: 'middle'},
       	 {title: '旅游产品编码', field: 'productCode', align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
TicketOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TicketOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加套票订单 
 */
TicketOrder.openAddTicketOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加套票订单 ',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ticketOrder/ticketOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看套票订单 详情
 */
TicketOrder.openTicketOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '套票订单 详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ticketOrder/ticketOrder_update/' + TicketOrder.seItem.id
        });
        this.layerIndex = index;
    }
};
TicketOrder.openDetailCardOrder = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '实体卡刷卡交易详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ticketOrder/ticketOrder_detail/' + TicketOrder.seItem.pltSsn
        });
        this.layerIndex = index;
    }
};
/**
 * 删除套票订单 
 */
TicketOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ticketOrder/delete", function (data) {
            Feng.success("删除成功!");
            TicketOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ticketOrderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询套票订单 列表
 */
TicketOrder.search = function () {
    var queryData = {};
		queryData['startTermTransDate'] = $("#startTermTransDate").val();
		queryData['endTermTransTime'] = $("#endTermTransTime").val();
		queryData['cardno'] = $("#cardno").val();
		queryData['orderNo'] = $("#orderNo").val();
		queryData['termId'] = $("#termId").val();
		queryData['productCode'] = $("#productCode").val();
    TicketOrder.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
TicketOrder.formParams = function() {
    var queryData = {};
		queryData['startTermTransDate'] = $("#startTermTransDate").val();
		queryData['endTermTransTime'] = $("#endTermTransTime").val();
		queryData['cardno'] = $("#cardno").val();
		queryData['orderNo'] = $("#orderNo").val();
		queryData['termId'] = $("#termId").val();
		queryData['productCode'] = $("#productCode").val();
    return queryData;
}


$(function () {
    var defaultColunms = TicketOrder.initColumn();
    var table = new BSTable(TicketOrder.id, "/ticketOrder/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(TicketOrder.formParams());
    TicketOrder.table = table.init();
});
