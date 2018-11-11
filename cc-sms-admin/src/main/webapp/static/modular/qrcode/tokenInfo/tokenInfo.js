/**
 * 套票二维码信息管理初始化
 */
var TokenInfo = {
    id: "TokenInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TokenInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'TokenId', field: 'tokenId', align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'userId', align: 'center', valign: 'middle'},
       	 {title: 'Token类型', field: 'tokenType', align: 'center', valign: 'middle'},
       	 {title: '生效时间', field: 'startTime', visible: false,align: 'center', valign: 'middle'},
       	 {title: '失效时间', field: 'endTime',visible: false, align: 'center', valign: 'middle'},
       	 {title: '应用场景', field: 'space', visible: false,align: 'center', valign: 'middle'},
       	 {title: '担保级别', field: 'tokenLevel', visible: false,align: 'center', valign: 'middle'},
       	 {title: '套票ID', field: 'ticketId', align: 'center', valign: 'middle'},
       	 {title: '订单号', field: 'ntOrderid', align: 'center', valign: 'middle'},
       	 {title: '套票类型', field: 'ticketType', visible: false,align: 'center', valign: 'middle'},
       	 {title: '附加信息', field: 'tokenInfo', visible: false,align: 'center', valign: 'middle'},
       	 {title: 'Token密文', field: 'tokenEnc',visible: false, align: 'center', valign: 'middle'},
       	 {title: '状态', field: 'state', align: 'center', valign: 'middle'},
       	 {title: '使用时间', field: 'useTime', align: 'center', valign: 'middle'},
       	 {title: 'Pos机Id', field: 'posId', align: 'center', valign: 'middle'},
       	 {title: '失效方式', field: 'expireType',visible: false, align: 'center', valign: 'middle'},
       	 {title: '失效时间', field: 'expireTime',visible: false, align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime',visible: false, align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', visible: false,align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
TokenInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TokenInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加套票二维码信息
 */
TokenInfo.openAddTokenInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加套票二维码信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tokenInfo/tokenInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看套票二维码信息详情
 */
TokenInfo.openTokenInfoDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '套票二维码信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ? '/tokenInfo/tokenInfo_detail/':'/tokenInfo/tokenInfo_update/') + TokenInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除套票二维码信息
 */
TokenInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tokenInfo/delete", function (data) {
            Feng.success("删除成功!");
            TokenInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tokenInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询套票二维码信息列表
 */
TokenInfo.search = function () {
    var queryData = {};
		queryData['tokenId'] = $("#tokenId").val();
		queryData['userId'] = $("#userId").val();
		queryData['ticketId'] = $("#ticketId").val();
		queryData['posId'] = $("#posId").val();
    TokenInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
TokenInfo.formParams = function() {
    var queryData = {};
		queryData['tokenId'] = $("#tokenId").val();
		queryData['userId'] = $("#userId").val();
		queryData['ticketId'] = $("#ticketId").val();
		queryData['posId'] = $("#posId").val();
    return queryData;
}


$(function () {
    var defaultColunms = TokenInfo.initColumn();
    var table = new BSTable(TokenInfo.id, "/tokenInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(TokenInfo.formParams());
    TokenInfo.table = table.init();
});
