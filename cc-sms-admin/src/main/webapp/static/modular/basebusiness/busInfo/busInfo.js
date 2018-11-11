/**
 * 车辆信息管理初始化
 */
var BusInfo = {
    id: "BusInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 公交POS信息管理初始化
 */
var BusPos = {
    id: "BusPosTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '线路编号', field: 'lineId', align: 'center', valign: 'middle'},
        {title: '线路名称', field: 'lineName', align: 'center', valign: 'middle'},
       	 {title: '车辆编号', field: 'busId', align: 'center', valign: 'middle'},
       	 {title: '车牌号', field: 'plateNumber', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
BusInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加车辆信息管理
 */
BusInfo.openAddBusInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加车辆信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busInfo/busInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看车辆信息管理详情
 */
BusInfo.openBusInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车辆信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busInfo/busInfo_update/' + BusInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除车辆信息管理
 */
BusInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busInfo/delete", function (data) {
            Feng.success("删除成功!");
            BusInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询车辆信息管理列表
 */
BusInfo.search = function () {
    var queryData = {};
		queryData['lineId'] = $("#lineId").val();
        queryData['lineName'] = $("#lineName").val();
		queryData['plateNumber'] = $("#plateNumber").val();
    BusInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
BusInfo.formParams = function() {
    var queryData = {};
		queryData['lineId'] = $("#lineId").val();
        queryData['lineName'] = $("#lineName").val();
		queryData['plateNumber'] = $("#plateNumber").val();
    return queryData;
}

/**
 * 点击添加POS信息
 */
BusInfo.openAddPos = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加POS信息管理',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busInfo/busInfo_addpos/' + BusInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

$(function () {
    var defaultColunms = BusInfo.initColumn();
    var table = new BSTable(BusInfo.id, "/busInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(BusInfo.formParams());
    BusInfo.table = table.init();
});
