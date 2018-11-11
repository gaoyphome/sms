/**
 * 站点信息管理初始化
 */
var StationInfo = {
    id: "StationInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StationInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
         {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '线路编号', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: '站点名称', field: 'name', align: 'center', valign: 'middle'},
       	 {title: '上/下行', field: 'flag', align: 'center', valign: 'middle'},
       	 {title: '站点序号', field: 'num', align: 'center', valign: 'middle'},
       	 {title: '经度', field: 'longitude', align: 'center', valign: 'middle'},
       	 {title: '纬度', field: 'latitude', align: 'center', valign: 'middle'},
       	 {title: '站点顺序号', field: 'operaStationNum', align: 'center', valign: 'middle'},
       	 {title: 'IC卡站点号', field: 'icStationNum', align: 'center', valign: 'middle'},
       	 {title: 'IC卡招呼站点号', field: 'icSubStationNum', align: 'center', valign: 'middle'},
       	 {title: '注册时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
StationInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        StationInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加站点信息管理
 */
StationInfo.openAddStationInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加站点信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/stationInfo/stationInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看站点信息管理详情
 */
StationInfo.openStationInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '站点信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/stationInfo/stationInfo_update/' + StationInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除站点信息管理
 */
StationInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/stationInfo/delete", function (data) {
            Feng.success("删除成功!");
            StationInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("stationInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询站点信息管理列表
 */
StationInfo.search = function () {
    var queryData = {};
		queryData['name'] = $("#name").val();
		queryData['lineId'] = $("#lineId").val();
    StationInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
StationInfo.formParams = function() {
    var queryData = {};
		queryData['name'] = $("#name").val();
		queryData['lineId'] = $("#lineId").val();
    return queryData;
}


$(function () {
    var defaultColunms = StationInfo.initColumn();
    var table = new BSTable(StationInfo.id, "/stationInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(StationInfo.formParams());
    StationInfo.table = table.init();
});
