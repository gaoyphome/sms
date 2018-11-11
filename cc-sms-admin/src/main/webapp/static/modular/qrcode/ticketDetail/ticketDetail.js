/**
 * 濂楃エ璇︽儏管理初始化
 */
var TicketDetail = {
    id: "TicketDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TicketDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '套票ID', field: 'ticketId', align: 'center', valign: 'middle'},
       	 {title: '订单号', field: 'ntOrderid', align: 'center', valign: 'middle'},
       	 {title: '代理商ID', field: 'agentId', align: 'center', valign: 'middle'},
       	 {title: '商户ID', field: 'mchntId', align: 'center', valign: 'middle'},
       	 {title: '景点ID', field: 'spotId', align: 'center', valign: 'middle'},
       	 {title: '门店ID', field: 'termId', align: 'center', valign: 'middle'},
       	 {title: '流水号', field: 'sesq', align: 'center', valign: 'middle'},
       	 {title: '可使用次数', field: 'totalCnt', align: 'center', valign: 'middle'},
       	 {title: '已使用次数', field: 'useCnt', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
TicketDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TicketDetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加濂楃エ璇︽儏
 */
TicketDetail.openAddTicketDetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加濂楃エ璇︽儏',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ticketDetail/ticketDetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看濂楃エ璇︽儏详情
 */
TicketDetail.openTicketDetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '濂楃エ璇︽儏详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ticketDetail/ticketDetail_update/' + TicketDetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除濂楃エ璇︽儏
 */
TicketDetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ticketDetail/delete", function (data) {
            Feng.success("删除成功!");
            TicketDetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ticketDetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询濂楃エ璇︽儏列表
 */
TicketDetail.search = function () {
    var queryData = {};
		queryData['ticketId'] = $("#ticketId").val();
		queryData['agentId'] = $("#agentId").val();
		queryData['mchntId'] = $("#mchntId").val();
    TicketDetail.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
TicketDetail.formParams = function() {
    var queryData = {};
		queryData['ticketId'] = $("#ticketId").val();
		queryData['agentId'] = $("#agentId").val();
		queryData['mchntId'] = $("#mchntId").val();
    return queryData;
}


$(function () {
    var defaultColunms = TicketDetail.initColumn();
    var table = new BSTable(TicketDetail.id, "/ticketDetail/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(TicketDetail.formParams());
    TicketDetail.table = table.init();
});
