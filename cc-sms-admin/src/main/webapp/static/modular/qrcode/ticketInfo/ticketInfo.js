/**
 * 濂楃エ淇℃伅管理初始化
 */
var TicketInfo = {
    id: "TicketInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TicketInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false,  align: 'center', valign: 'middle'},
       	 {title: '套票ID', field: 'ticketId', align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'userId', align: 'center', valign: 'middle'},
       	 {title: '套票名称', field: 'ticketName', align: 'center', valign: 'middle'},
       	 {title: '套票类型', field: 'ticketType', align: 'center', valign: 'middle'},
       	 {title: '套票描述', field: 'ticketDesc', align: 'center', valign: 'middle'},
       	 {title: '绑定卡号', field: 'cardno', align: 'center', valign: 'middle'},
       	 {title: '订单号', field: 'ntOrderid', align: 'center', valign: 'middle'},
       	 {title: '购买日期', field: 'saleDate', align: 'center', valign: 'middle'},
       	 {title: '过期类型', field: 'expireType', align: 'center', valign: 'middle'},
       	 {title: '使用开始日期', field: 'startDate', align: 'center', valign: 'middle'},
       	 {title: '使用结束日期', field: 'endDate', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
TicketInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TicketInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加濂楃エ淇℃伅
 */
TicketInfo.openAddTicketInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加濂楃エ淇℃伅',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ticketInfo/ticketInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看濂楃エ淇℃伅详情
 */
TicketInfo.openTicketInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '濂楃エ淇℃伅详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ticketInfo/ticketInfo_update/' + TicketInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除濂楃エ淇℃伅
 */
TicketInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/ticketInfo/delete", function (data) {
            Feng.success("删除成功!");
            TicketInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ticketInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询濂楃エ淇℃伅列表
 */
TicketInfo.search = function () {
    var queryData = {};
		queryData['ticketId'] = $("#ticketId").val();
		queryData['ticketName'] = $("#ticketName").val();
		queryData['userId'] = $("#userId").val();
    TicketInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
TicketInfo.formParams = function() {
    var queryData = {};
		queryData['ticketId'] = $("#ticketId").val();
		queryData['ticketName'] = $("#ticketName").val();
		queryData['userId'] = $("#userId").val();
    return queryData;
}


$(function () {
    var defaultColunms = TicketInfo.initColumn();
    var table = new BSTable(TicketInfo.id, "/ticketInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(TicketInfo.formParams());
    TicketInfo.table = table.init();
});
