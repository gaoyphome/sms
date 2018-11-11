/**
 * 用户补票管理初始化
 */
var QrcodeSysComplement = {
    id: "QrcodeSysComplementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeSysComplement.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: '虚拟卡号', field: 'vsirBmacno', align: 'center', valign: 'middle'},
       	 {title: '月份', field: 'mouth', align: 'center', valign: 'middle'},
       	 {title: '超过规定补票次数', field: 'overComplementCount', align: 'center', valign: 'middle'},
       	 {title: '系统补票次数', field: 'sysComplementCount', align: 'center', valign: 'middle'},
       	 {title: '用户补票次数', field: 'userComplementCount', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
QrcodeSysComplement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeSysComplement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户补票
 */
QrcodeSysComplement.openAddQrcodeSysComplement = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户补票',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeSysComplement/qrcodeSysComplement_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户补票详情
 */
QrcodeSysComplement.openQrcodeSysComplementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户补票详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeSysComplement/qrcodeSysComplement_update?'
            + "vsirBmacno="+QrcodeSysComplement.seItem.vsirBmacno +"&"
            + "mouth="+QrcodeSysComplement.seItem.mouth
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户补票
 */
QrcodeSysComplement.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeSysComplement/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeSysComplement.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("vsirBmacno",this.seItem.vsirBmacno);
        ajax.set("mouth",this.seItem.mouth);
        ajax.start();
    }
};

/**
 * 查询用户补票列表
 */
QrcodeSysComplement.search = function () {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['mouth'] = $("#mouth").val();
    QrcodeSysComplement.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeSysComplement.formParams = function() {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['mouth'] = $("#mouth").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeSysComplement.initColumn();
    var table = new BSTable(QrcodeSysComplement.id, "/qrcodeSysComplement/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeSysComplement.formParams());
    QrcodeSysComplement.table = table.init();
});
