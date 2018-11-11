/**
 * 二维码折扣管理初始化
 */
var QrcodeDiscount = {
    id: "QrcodeDiscountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeDiscount.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: '运营分公司代码', field: 'subCompanyId', align: 'center', valign: 'middle'},
         {title: '运营分公司名称', field: 'companyName', align: 'center', valign: 'middle'},
       	 {title: '虚拟卡类型', field: 'cardType', align: 'center', valign: 'middle'},
       	 {title: '市内折扣率', field: 'cityInDiscount', align: 'center', valign: 'middle'},
       	 {title: '市内优惠额度', field: 'cityInPreferentialAmount', align: 'center', valign: 'middle'},
       	 {title: '市外折扣率', field: 'cityOutDiscount', align: 'center', valign: 'middle'},
       	 {title: '最大优惠额度', field: 'maxPreferentialAmount', align: 'center', valign: 'middle'},
       	 {title: '市外优惠额度', field: 'cityOutPreferentialAmount', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
QrcodeDiscount.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeDiscount.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码折扣
 */
QrcodeDiscount.openAddQrcodeDiscount = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码折扣',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeDiscount/qrcodeDiscount_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码折扣详情
 */
QrcodeDiscount.openQrcodeDiscountDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码折扣详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/qrcodeDiscount/qrcodeDiscount_update/' + QrcodeDiscount.seItem.subCompanyId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码折扣
 */
QrcodeDiscount.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeDiscount/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeDiscount.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("subCompanyId",this.seItem.subCompanyId);
        ajax.start();
    }
};

/**
 * 查询二维码折扣列表
 */
QrcodeDiscount.search = function () {
    var queryData = {};
		queryData['subCompanyId'] = $("#subCompanyId").val();
    QrcodeDiscount.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeDiscount.formParams = function() {
    var queryData = {};
		queryData['subCompanyId'] = $("#subCompanyId").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeDiscount.initColumn();
    var table = new BSTable(QrcodeDiscount.id, "/qrcodeDiscount/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeDiscount.formParams());
    QrcodeDiscount.table = table.init();
});
