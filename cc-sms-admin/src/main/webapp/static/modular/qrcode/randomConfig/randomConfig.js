/**
 * 随机数索引管理初始化
 */
var RandomConfig = {
    id: "RandomConfigTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RandomConfig.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '当前索引号', field: 'currIndex', align: 'center', valign: 'middle'},
       	 {title: '最大索引号', field: 'maxIndex', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
RandomConfig.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RandomConfig.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加随机数索引
 */
RandomConfig.openAddRandomConfig = function () {
    var index = layer.open({
        type: 2,
        title: '添加随机数索引',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/randomConfig/randomConfig_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看随机数索引详情
 */
RandomConfig.openRandomConfigDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '随机数索引详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/randomConfig/randomConfig_update/' + RandomConfig.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除随机数索引
 */
RandomConfig.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/randomConfig/delete", function (data) {
            Feng.success("删除成功!");
            RandomConfig.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("randomConfigId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询随机数索引列表
 */
RandomConfig.search = function () {
    var queryData = {};
		queryData['currIndex'] = $("#currIndex").val();
    RandomConfig.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
RandomConfig.formParams = function() {
    var queryData = {};
		queryData['currIndex'] = $("#currIndex").val();
    return queryData;
}


$(function () {
    var defaultColunms = RandomConfig.initColumn();
    var table = new BSTable(RandomConfig.id, "/randomConfig/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(RandomConfig.formParams());
    RandomConfig.table = table.init();
});
