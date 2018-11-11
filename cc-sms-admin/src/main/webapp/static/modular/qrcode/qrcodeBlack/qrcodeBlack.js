/**
 * 二维码黑名单管理初始化
 */
var QrcodeBlack = {
    id: "QrcodeBlackTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeBlack.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'TokenId', field: 'tokenId', align: 'center', valign: 'middle'},
       	 {title: '虚拟卡号', field: 'vsirBmacno', align: 'center', valign: 'middle'},
       	 {title: '原因', field: 'reason', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '拉黑次数', field: 'blackCount', align: 'center', valign: 'middle'},
       	 {title: '操作人姓名', field: 'operatePerson', align: 'center', valign: 'middle'},
       	 {title: '操作人ID', field: 'operatePersonId', align: 'center', valign: 'middle'},
       	 {title: '下发状态', field: 'state', align: 'center', valign: 'middle'},
       	 {title: '拉黑状态', field: 'flag', align: 'center', valign: 'middle'},
       	 {title: '失效时间', field: 'failTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
QrcodeBlack.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeBlack.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码黑名单
 */
QrcodeBlack.openAddQrcodeBlack = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码黑名单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeBlack/qrcodeBlack_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码黑名单详情
 */
QrcodeBlack.openQrcodeBlackDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码黑名单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeBlack/qrcodeBlack_update/' + QrcodeBlack.seItem.tokenId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码黑名单
 */
QrcodeBlack.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeBlack/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeBlack.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tokenId",this.seItem.tokenId);
        ajax.start();
    }
};

/**
 * 查询二维码黑名单列表
 */
QrcodeBlack.search = function () {
    var queryData = {};
		queryData['tokenId'] = $("#tokenId").val();
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
    QrcodeBlack.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeBlack.formParams = function() {
    var queryData = {};
		queryData['tokenId'] = $("#tokenId").val();
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeBlack.initColumn();
    var table = new BSTable(QrcodeBlack.id, "/qrcodeBlack/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeBlack.formParams());
    QrcodeBlack.table = table.init();
});
