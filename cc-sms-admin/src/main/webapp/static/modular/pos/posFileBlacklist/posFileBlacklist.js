/**
 * POS黑名单文件信息 管理初始化
 */
var PosFileBlacklist = {
    id: "PosFileBlacklistTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PosFileBlacklist.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '文件名', field: 'filename', align: 'center', valign: 'middle'},
         {title: '文件版本', field: 'version', align: 'center', valign: 'middle'},
       	 {title: '文件大小', field: 'filesize', align: 'center', valign: 'middle'},
       	 {title: 'crc16', field: 'crc16', align: 'center', valign: 'middle'},
       	 {title: '文件内容', field: 'content', align: 'center', valign: 'middle',
             formatter:function(value,row,index){
             var e = '<a href="/posFileBlacklist/download?id='+row.id+'" mce_href="#1">下载</a> ';
             return e;
         } },

    ];
};

/**
 * 检查是否选中
 */
PosFileBlacklist.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PosFileBlacklist.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加POS黑名单文件信息 
 */
PosFileBlacklist.openAddPosFileBlacklist = function () {
    var index = layer.open({
        type: 2,
        title: '添加POS黑名单文件信息 ',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/posFileBlacklist/posFileBlacklist_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看POS黑名单文件信息 详情
 */
PosFileBlacklist.openPosFileBlacklistDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'POS黑名单文件信息 详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/posFileBlacklist/posFileBlacklist_update/' + PosFileBlacklist.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除POS黑名单文件信息 
 */
PosFileBlacklist.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/posFileBlacklist/delete", function (data) {
            Feng.success("删除成功!");
            PosFileBlacklist.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("posFileBlacklistId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询POS黑名单文件信息 列表
 */
PosFileBlacklist.search = function () {
    var queryData = {};
		queryData['filename'] = $("#filename").val();
    PosFileBlacklist.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PosFileBlacklist.formParams = function() {
    var queryData = {};
		queryData['filename'] = $("#filename").val();
    return queryData;
}


$(function () {
    var defaultColunms = PosFileBlacklist.initColumn();
    var table = new BSTable(PosFileBlacklist.id, "/posFileBlacklist/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PosFileBlacklist.formParams());
    PosFileBlacklist.table = table.init();
});
