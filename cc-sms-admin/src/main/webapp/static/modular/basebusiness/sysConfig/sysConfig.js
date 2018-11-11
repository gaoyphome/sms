/**
 * 系统参数管理初始化
 */
var SysConfig = {
    id: "SysConfigTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SysConfig.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '分类', field: 'category', align: 'center', valign: 'middle'},
       	 {title: '参数名称', field: 'paramKey', align: 'center', valign: 'middle'},
       	 {title: '参数值', field: 'paramValue', align: 'center', valign: 'middle'},
       	 {title: '备注', field: 'remark', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
SysConfig.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SysConfig.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加系统参数管理
 */
SysConfig.openAddSysConfig = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统参数管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sysConfig/sysConfig_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看系统参数管理详情
 */
SysConfig.openSysConfigDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '系统参数管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sysConfig/sysConfig_update/' + SysConfig.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统参数管理
 */
SysConfig.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sysConfig/delete", function (data) {
            Feng.success("删除成功!");
            SysConfig.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sysConfigId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询系统参数管理列表
 */
SysConfig.search = function () {
    var queryData = {};
		queryData['category'] = $("#category").val();
		queryData['paramKey'] = $("#paramKey").val();
    SysConfig.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
SysConfig.formParams = function() {
    var queryData = {};
		queryData['category'] = $("#category").val();
		queryData['paramKey'] = $("#paramKey").val();
    return queryData;
}


$(function () {
    var defaultColunms = SysConfig.initColumn();
    var table = new BSTable(SysConfig.id, "/sysConfig/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(SysConfig.formParams());
    SysConfig.table = table.init();
});
