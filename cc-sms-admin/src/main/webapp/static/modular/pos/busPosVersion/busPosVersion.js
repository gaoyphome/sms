/**
 * 公交POS固件版本管理初始化
 */
var BusPosVersion = {
    id: "BusPosVersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusPosVersion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '版本号', field: 'firmVersion', align: 'center', valign: 'middle'},
       	 {title: '固件文件大小', field: 'filesize', align: 'center', valign: 'middle'},
       	 {title: '文件索引ID', field: 'fileId', align: 'center', valign: 'middle'},
       	 {title: '版本描述', field: 'content', align: 'center', valign: 'middle'},
       	 {title: '下载路径', field: 'path', align: 'center', valign: 'middle'},
       	 {title: '升级方式', field: 'type', align: 'center', valign: 'middle'},
       	 {title: '升级类型', field: 'upgradeType', align: 'center', valign: 'middle'},
       	 {title: '线路ID', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: 'POS-ID', field: 'posId', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
        {title: '下载起始时间', field: 'downStartTime', align: 'center', valign: 'middle'},
        {title: '下载终止时间', field: 'downEndTime', align: 'center', valign: 'middle'},
        {title: '安装起始时间', field: 'installStartTime', align: 'center', valign: 'middle'},
        {title: '安装终止时间', field: 'installEndTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
BusPosVersion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusPosVersion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公交POS固件版本
 */
BusPosVersion.openAddBusPosVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加公交POS固件版本',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busPosVersion/busPosVersion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公交POS固件版本详情
 */
BusPosVersion.openBusPosVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公交POS固件版本详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busPosVersion/busPosVersion_update/' + BusPosVersion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公交POS固件版本
 */
BusPosVersion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busPosVersion/delete", function (data) {
            Feng.success("删除成功!");
            BusPosVersion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busPosVersionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公交POS固件版本列表
 */
BusPosVersion.search = function () {
    var queryData = {};
		queryData['firmVersion'] = $("#firmVersion").val();
		queryData['posId'] = $("#posId").val();
    BusPosVersion.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
BusPosVersion.formParams = function() {
    var queryData = {};
		queryData['firmVersion'] = $("#firmVersion").val();
		queryData['posId'] = $("#posId").val();
    return queryData;
}


$(function () {
    var defaultColunms = BusPosVersion.initColumn();
    var table = new BSTable(BusPosVersion.id, "/busPosVersion/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(BusPosVersion.formParams());
    BusPosVersion.table = table.init();
});
