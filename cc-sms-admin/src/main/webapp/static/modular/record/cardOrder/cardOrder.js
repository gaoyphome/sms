/**
 * 实体卡刷卡交易 管理初始化
 */
var CardOrder = {
    id: "CardOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CardOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'PSAM ID', field: 'psamId', align: 'center', valign: 'middle'},
        {title: '套票绑定卡号', field: 'cardno', align: 'center', valign: 'middle'},
       	 {title: '平台流水号', field: 'pltSsn',visible: false, align: 'center', valign: 'middle'},
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

		 {title: '终端交易时间', field: 'termTransDateTime', align: 'center', valign: 'middle'},
       	 {title: '终端交易时间', field: 'termTransTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '终端交易日期', field: 'termTransDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '城市代码', field: 'cardCity', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'CSN', field: 'csn', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡序列号', field: 'cardSeqNo', align: 'center', valign: 'middle'},
       	 {title: '卡累计交易次数', field: 'cardCounter', align: 'center', valign: 'middle'},
       	 {title: '交易卡类型', field: 'cardType', align: 'center', valign: 'middle'},
       	 {title: '卡物理类型', field: 'cardPhyType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '平台订单号', field: 'orderNo', align: 'center', valign: 'middle'},
       	 {title: '订单状态', field: 'state', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡行业代码', field: 'cardBramdid', visible: false, align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'cardClass', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易类型', field: 'orderType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '补票交易状态', field: 'tradeStatus', visible: false, align: 'center', valign: 'middle'},
       	 {title: '记录顺序编号', field: 'serialNum', visible: false, align: 'center', valign: 'middle'},

       	 {title: '交易类型标识', field: 'tradeTypeFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '脱机交易序号', field: 'cpuCounter', align: 'center', valign: 'middle'},
       	 {title: '发卡机构代码', field: 'cardIisCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '时间票类型', field: 'timeCardType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车时间', field: 'markTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车城市代码', field: 'markCityCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车受理机构代码', field: 'markIisCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车运营商代码', field: 'markCsCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车线路', field: 'markLineId', align: 'center', valign: 'middle'},
       	 {title: '上车站', field: 'markStation', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上车车辆号', field: 'markBusId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车运营商代码', field: 'downCsCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车线路', field: 'downLineId', align: 'center', valign: 'middle'},
       	 {title: '下车站', field: 'downStation', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下车车辆号', field: 'downBusId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '乘车里程', field: 'takenLength', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上下行标志', field: 'dirFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '司机ID', field: 'driverCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '监票员ID', field: 'sellorCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '监票员ID2', field: 'sellorCode2', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS-ID', field: 'posId', align: 'center', valign: 'middle'},
       	 {title: 'POS机交易流水号', field: 'posTradeSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机交易时间', field: 'posDate', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易TAC', field: 'tac', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'TAC算法标识', field: 'tacMode', visible: false, align: 'center', valign: 'middle'},

       	 {title: 'PSAM交易流水号', field: 'psamSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易金额', field: 'chargeActual', align: 'center', valign: 'middle'},
       	 {title: '卡内余额', field: 'cardBalance', align: 'center', valign: 'middle'},
       	 {title: '交易前卡内余额', field: 'preCardBalance', visible: false, align: 'center', valign: 'middle'},
       	 {title: '应收金额', field: 'chargeIdeal', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥版本号', field: 'keyVer', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥索引号', field: 'keyIndex', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥请求次数', field: 'keyReqNum', visible: false, align: 'center', valign: 'middle'},
       	 {title: '密钥授权流水号', field: 'authSeq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '记录检验码', field: 'checkCode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '收费记录格式', field: 'recordType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡片标识', field: 'cardFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'cardUpload', field: 'cardUpload', visible: false, align: 'center', valign: 'middle'},
       	 {title: '交易记录类型', field: 'reservedType', visible: false, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
CardOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CardOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加实体卡刷卡交易 
 */
CardOrder.openAddCardOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加实体卡刷卡交易 ',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cardOrder/cardOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看实体卡刷卡交易 详情
 */
CardOrder.openCardOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '实体卡刷卡交易 详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cardOrder/cardOrder_update/' + CardOrder.seItem.id
        });
        this.layerIndex = index;
    }
};
CardOrder.openDetailCardOrder = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '实体卡刷卡交易详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cardOrder/cardOrder_detail/' + CardOrder.seItem.pltSsn
        });
        this.layerIndex = index;
    }
};
/**
 * 删除实体卡刷卡交易 
 */
CardOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cardOrder/delete", function (data) {
            Feng.success("删除成功!");
            CardOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cardOrderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询实体卡刷卡交易 列表
 */
CardOrder.search = function () {
    var queryData = {};
    var sdate = $("#startTermTransDate").val();
    var edate = $("#endTermTransTime").val();
    var sdate_ = beforeDate(sdate, 7).Format('yyyy-MM-dd');

    // if(sdate_ > edate){
    	// alert("开始时间和结束时间相差超过七天");
    	// return false;
	// }

		queryData['startTermTransDate'] = $("#startTermTransDate").val();
		queryData['endTermTransTime'] = $("#endTermTransTime").val();
		queryData['cardno'] = $("#cardno").val();
		queryData['orderNo'] = $("#orderNo").val();
		queryData['markLineId'] = $("#markLineId").val();
		queryData['downLineId'] = $("#downLineId").val();
    queryData['posId'] = $("#posId").val();
    CardOrder.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
CardOrder.formParams = function() {
    var queryData = {};
        // var sdate = $("#startTermTransDate").val();
        // if(sdate == null){
         //    sdate = '2017-04-01'
		// }
		//
        // var edate = $("#endTermTransTime").val();
		// if(edate == null){
         //    edate = '2018-04-01'
		// }
		queryData['startTermTransDate'] = $("#startTermTransDate").val();
		queryData['endTermTransTime'] = $("#endTermTransTime").val();
		queryData['cardno'] = $("#cardno").val();
		queryData['orderNo'] = $("#orderNo").val();
		queryData['markLineId'] = $("#markLineId").val();
		queryData['downLineId'] = $("#downLineId").val();
    	queryData['posId'] = $("#posId").val();
    return queryData;
}

CardOrder.initLaydata = function () {
    var start = {
        elem: '#startTermTransDate',
        format: 'YYYY-MM-DD',
        isclear: true,
        istime: true,
        istoday: true,
        choose: function (datas) {
            end.min = datas;
            end.start = datas;
         //   end.max = beforeDate(datas, 7).Format('yyyy-MM-dd');
        }
    };
    var end = {
        elem: '#endTermTransTime',
        format: 'YYYY-MM-DD',
        istime: true,
        istoday: true,
        isclear: true,
        issure: true,
        choose: function (datas) {
            start.max = datas;
        }
    };
    laydate(start);
    laydate(end);
    $('#startTermTransDate').val(laydate.now(0, 'YYYY-MM-DD'));
    $('#endTermTransTime').val(laydate.now(0, 'YYYY-MM-DD'));
}

$(function () {
    var defaultColunms = CardOrder.initColumn();
    var table = new BSTable(CardOrder.id, "/cardOrder/list", defaultColunms);
    table.setPaginationType("server");
    var params = CardOrder.formParams();
    params['startTermTransDate'] = laydate.now(0, 'YYYY-MM-DD');
    params['endTermTransDate'] = laydate.now(0, 'YYYY-MM-DD');
    // $('#startTermTransDate').val(laydate.now(0, 'YYYY-MM-DD'));
    // $('#endTermTransTime').val(laydate.now(0, 'YYYY-MM-DD'));
    table.setQueryParams(params);
    CardOrder.table = table.init();
    CardOrder.initLaydata();

});
