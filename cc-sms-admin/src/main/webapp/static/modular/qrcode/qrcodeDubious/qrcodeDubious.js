/**
 * 二维码可疑信息表管理初始化
 */
var QrcodeDubious = {
    id: "QrcodeDubiousTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeDubious.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: '虚拟卡号', field: 'vsirBmacno', align: 'center', valign: 'middle'},
       	 {title: '原因', field: 'reason', align: 'center', valign: 'middle'},
       	 {title: 'TokenId', field: 'tokenId', align: 'center', valign: 'middle'},
       	 {title: '可疑操作次数', field: 'dubiousCount', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
QrcodeDubious.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeDubious.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码可疑信息表
 */
QrcodeDubious.openAddQrcodeDubious = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码可疑信息表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeDubious/qrcodeDubious_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码可疑信息表详情
 */
QrcodeDubious.openQrcodeDubiousDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码可疑信息表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeDubious/qrcodeDubious_update?'
            + "vsirBmacno="+QrcodeDubious.seItem.vsirBmacno +"&"
            + "reason="+QrcodeDubious.seItem.reason +"&"
            + "tokenId="+QrcodeDubious.seItem.tokenId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码可疑信息表
 */
QrcodeDubious.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeDubious/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeDubious.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("vsirBmacno",this.seItem.vsirBmacno);
        ajax.set("reason",this.seItem.reason);
        ajax.set("tokenId",this.seItem.tokenId);
        ajax.start();
    }
};

/**
 * 查询二维码可疑信息表列表
 */
QrcodeDubious.search = function () {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['tokenId'] = $("#tokenId").val();
    QrcodeDubious.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeDubious.formParams = function() {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['tokenId'] = $("#tokenId").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeDubious.initColumn();
    var table = new BSTable(QrcodeDubious.id, "/qrcodeDubious/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeDubious.formParams());
    QrcodeDubious.table = table.init();
});
