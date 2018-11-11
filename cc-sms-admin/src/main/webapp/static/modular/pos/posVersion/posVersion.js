/**
 * 固件版本信息管理初始化
 */
var PosVersion = {
    id: "PosVersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PosVersion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '版本号', field: 'firmVersion', align: 'center', valign: 'middle'},
       	 {title: '固件文件大小', field: 'filesize', align: 'center', valign: 'middle'},
       	 {title: '固件文件索引', field: 'fileId', align: 'center', valign: 'middle'},
       	 {title: '版本描述', field: 'content', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下载路径', field: 'path', visible: false, align: 'center', valign: 'middle'},
       	 {title: '升级方式', field: 'type', visible: false, align: 'center', valign: 'middle'},
       	 {title: '升级类型', field: 'upgradeType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '商户ID', field: 'mchntId', align: 'center', valign: 'middle'},
       	 {title: 'POS机ID', field: 'posId', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PosVersion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PosVersion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加固件版本信息
 */
PosVersion.openAddPosVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加固件版本信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/posVersion/posVersion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看固件版本信息详情
 */
PosVersion.openPosVersionDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '固件版本信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ?  '/posVersion/posVersion_detail/' : '/posVersion/posVersion_update/') + PosVersion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除固件版本信息
 */
PosVersion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/posVersion/delete", function (data) {
            Feng.success("删除成功!");
            PosVersion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("posVersionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询固件版本信息列表
 */
PosVersion.search = function () {
    var queryData = {};
		queryData['firmVersion'] = $("#firmVersion").val();
		queryData['mchntId'] = $("#mchntId").val();
		queryData['posId'] = $("#posId").val();
    PosVersion.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PosVersion.formParams = function() {
    var queryData = {};
		queryData['firmVersion'] = $("#firmVersion").val();
		queryData['mchntId'] = $("#mchntId").val();
		queryData['posId'] = $("#posId").val();
    return queryData;
}


$(function () {
    var defaultColunms = PosVersion.initColumn();
    var table = new BSTable(PosVersion.id, "/posVersion/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PosVersion.formParams());
    PosVersion.table = table.init();
});
