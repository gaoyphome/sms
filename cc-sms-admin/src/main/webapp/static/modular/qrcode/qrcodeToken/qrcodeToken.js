/**
 * 虚拟卡号表管理初始化
 */
var QrcodeToken = {
    id: "QrcodeTokenTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeToken.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '虚拟卡号', field: 'vsirBmacno', align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'userId', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
       	 {title: '卡号校验位', field: 'cardCheckDigit', align: 'center', valign: 'middle'},
       	 {title: '第三方机构号', field: 'thirdInstid', align: 'center', valign: 'middle'},
       	 {title: '第三方渠道号', field: 'thirdChannelid', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
QrcodeToken.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeToken.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加虚拟卡号表
 */
QrcodeToken.openAddQrcodeToken = function () {
    var index = layer.open({
        type: 2,
        title: '添加虚拟卡号表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeToken/qrcodeToken_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看虚拟卡号表详情
 */
QrcodeToken.openQrcodeTokenDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '虚拟卡号表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeToken/qrcodeToken_update/' + QrcodeToken.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除虚拟卡号表
 */
QrcodeToken.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeToken/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeToken.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrcodeTokenId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询虚拟卡号表列表
 */
QrcodeToken.search = function () {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['userId'] = $("#userId").val();
    QrcodeToken.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeToken.formParams = function() {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['userId'] = $("#userId").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeToken.initColumn();
    var table = new BSTable(QrcodeToken.id, "/qrcodeToken/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeToken.formParams());
    QrcodeToken.table = table.init();
});
