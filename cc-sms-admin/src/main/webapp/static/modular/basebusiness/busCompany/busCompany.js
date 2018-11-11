/**
 * 运营公司初始化
 */
var BusCompany = {
    id: "BusCompanyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusCompany.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '公司编码', field: 'companyId', align: 'center', valign: 'middle', sortable: true},
        {title: '公司名称', field: 'companyName', align: 'center', valign: 'middle', sortable: true},
        {title: '公司类型', field: 'type', align: 'center', valign: 'middle', sortable: true},
        {title: '父公司编号', field: 'parentCompanyId', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
BusCompany.formParams = function() {
    var queryData = {};
    queryData['companyId'] = $("#companyId").val();
    queryData['companyName'] = $("#companyName").val();
    return queryData;
}

/**
 * 检查是否选中
 */
BusCompany.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusCompany.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加运营公司
 */
BusCompany.openAddBusCompany = function () {
    var index = layer.open({
        type: 2,
        title: '添加运营公司',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busCompany/busCompany_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看运营公司详情
 */
BusCompany.openBusCompanyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '运营公司详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busCompany/busCompany_edit/' + BusCompany.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除运营公司
 */
BusCompany.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busCompany/delete", function (data) {
            Feng.success("删除成功!");
            BusCompany.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busCompanyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询运营公司列表
 */
BusCompany.search = function () {
    var queryData = {};
    queryData['companyId'] = $("#companyId").val();
    queryData['companyName'] = $("#companyName").val();
    BusCompany.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusCompany.initColumn();
    var table = new BSTable(BusCompany.id, "/busCompany/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(BusCompany.formParams());
    BusCompany.table = table.init();

  //  $("#companyId").attr("maxlength",2);
});
