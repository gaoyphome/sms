/**
 * 里程信息管理初始化
 */
var MileageInfo = {
    id: "MileageInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MileageInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '线路编号', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: '上/下行', field: 'dirFlag', align: 'center', valign: 'middle'},
       	 {title: '里程', field: 'mileageValue', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
MileageInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MileageInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加里程信息管理
 */
MileageInfo.openAddMileageInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加里程信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mileageInfo/mileageInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看里程信息管理详情
 */
MileageInfo.openMileageInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '里程信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mileageInfo/mileageInfo_update/' + MileageInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除里程信息管理
 */
MileageInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mileageInfo/delete", function (data) {
            Feng.success("删除成功!");
            MileageInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mileageInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询里程信息管理列表
 */
MileageInfo.search = function () {
    var queryData = {};
		queryData['lineId'] = $("#lineId").val();
		queryData['mileageValue'] = $("#mileageValue").val();
    MileageInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
MileageInfo.formParams = function() {
    var queryData = {};
		queryData['lineId'] = $("#lineId").val();
		queryData['mileageValue'] = $("#mileageValue").val();
    return queryData;
}


$(function () {
    var defaultColunms = MileageInfo.initColumn();
    var table = new BSTable(MileageInfo.id, "/mileageInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(MileageInfo.formParams());
    MileageInfo.table = table.init();
});
