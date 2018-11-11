/**
 * POS黑名升级管理表管理初始化
 */
var BlacklistVersion = {
    id: "BlacklistVersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BlacklistVersion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '升级类型', field: 'filename', align: 'center', valign: 'middle'},
       	 {title: '版本号', field: 'listVersion', align: 'center', valign: 'middle'},
       	 {title: '固件ID', field: 'fileId', align: 'center', valign: 'middle'},
       	 {title: '版本描述', field: 'content', align: 'center', valign: 'middle'},
       	 {title: '升级方式', field: 'type', align: 'center', valign: 'middle'},
       	 {title: '线路ID', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: 'POS-ID', field: 'posId', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
BlacklistVersion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BlacklistVersion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加POS黑名升级管理表
 */
BlacklistVersion.openAddBlacklistVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加POS黑名升级管理表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/blacklistVersion/blacklistVersion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看POS黑名升级管理表详情
 */
BlacklistVersion.openBlacklistVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'POS黑名升级管理表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/blacklistVersion/blacklistVersion_update/' + BlacklistVersion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除POS黑名升级管理表
 */
BlacklistVersion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/blacklistVersion/delete", function (data) {
            Feng.success("删除成功!");
            BlacklistVersion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("blacklistVersionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询POS黑名升级管理表列表
 */
BlacklistVersion.search = function () {
    var queryData = {};
		queryData['listVersion'] = $("#listVersion").val();
    BlacklistVersion.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
BlacklistVersion.formParams = function() {
    var queryData = {};
		queryData['listVersion'] = $("#listVersion").val();
    return queryData;
}


$(function () {
    var defaultColunms = BlacklistVersion.initColumn();
    var table = new BSTable(BlacklistVersion.id, "/blacklistVersion/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(BlacklistVersion.formParams());
    BlacklistVersion.table = table.init();

});
