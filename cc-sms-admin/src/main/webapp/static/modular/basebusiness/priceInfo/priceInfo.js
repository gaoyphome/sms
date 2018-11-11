/**
 * 票价信息管理初始化
 */
var PriceInfo = {
    id: "PriceInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PriceInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '线路编号', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: '上/下行', field: 'dirFlag', align: 'center', valign: 'middle'},
       	 {title: '站点顺序号', field: 'num', align: 'center', valign: 'middle'},
       	 {title: '票价', field: 'price', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '修改时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PriceInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PriceInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加票价信息管理
 */
PriceInfo.openAddPriceInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加票价信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/priceInfo/priceInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看票价信息管理详情
 */
PriceInfo.openPriceInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '票价信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/priceInfo/priceInfo_update/' + PriceInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除票价信息管理
 */
PriceInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/priceInfo/delete", function (data) {
            Feng.success("删除成功!");
            PriceInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("priceInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询票价信息管理列表
 */
PriceInfo.search = function () {
    var queryData = {};
		queryData['lineId'] = $("#lineId").val();
		queryData['price'] = $("#price").val();
    PriceInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PriceInfo.formParams = function() {
    var queryData = {};
		queryData['lineId'] = $("#lineId").val();
		queryData['price'] = $("#price").val();
    return queryData;
}


$(function () {
    var defaultColunms = PriceInfo.initColumn();
    var table = new BSTable(PriceInfo.id, "/priceInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PriceInfo.formParams());
    PriceInfo.table = table.init();
});
