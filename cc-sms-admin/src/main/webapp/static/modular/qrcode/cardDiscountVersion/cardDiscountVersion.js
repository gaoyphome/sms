/**
 * 实体卡版本号管理初始化
 */
var CardDiscountVersion = {
    id: "CardDiscountVersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CardDiscountVersion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '子运营公司ID', field: 'subCompanyId', align: 'center', valign: 'middle'},
       	 {title: '版本号', field: 'pltVersion', align: 'center', valign: 'middle'},
       	 {title: '状态', field: 'createFlag', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
CardDiscountVersion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CardDiscountVersion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加实体卡版本号
 */
CardDiscountVersion.openAddCardDiscountVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加实体卡版本号',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cardDiscountVersion/cardDiscountVersion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看实体卡版本号详情
 */
CardDiscountVersion.openCardDiscountVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '实体卡版本号详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cardDiscountVersion/cardDiscountVersion_update/' + CardDiscountVersion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除实体卡版本号
 */
CardDiscountVersion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cardDiscountVersion/delete", function (data) {
            Feng.success("删除成功!");
            CardDiscountVersion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cardDiscountVersionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询实体卡版本号列表
 */
CardDiscountVersion.search = function () {
    var queryData = {};
		queryData['subCompanyId'] = $("#subCompanyId").val();
    CardDiscountVersion.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
CardDiscountVersion.formParams = function() {
    var queryData = {};
		queryData['subCompanyId'] = $("#subCompanyId").val();
    return queryData;
}


$(function () {
    var defaultColunms = CardDiscountVersion.initColumn();
    var table = new BSTable(CardDiscountVersion.id, "/cardDiscountVersion/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(CardDiscountVersion.formParams());
    CardDiscountVersion.table = table.init();
});
