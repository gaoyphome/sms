/**
 * POS黑名单文件表初始化
 */
var PosFileUpdate = {
    id: "PosFileUpdateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PosFileUpdate.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: '设备ID', field: 'id', align: 'center', valign: 'middle'},
       	 {title: '线路版本', field: 'lineVersion', align: 'center', valign: 'middle'},
       	 {title: '文件ID', field: 'linefile', align: 'center', valign: 'middle'},
       	 {title: '实体卡增量文件ID', field: 'incrBlacklistFile', align: 'center', valign: 'middle'},
       	 {title: '二维码文件ID', field: 'qrBlacklistFile', align: 'center', valign: 'middle'},
       	 {title: '二维码增量文件ID', field: 'qrIncrBlacklistFile', align: 'center', valign: 'middle'},
       	 {title: '互通卡文件ID', field: 'htBlacklistFile', align: 'center', valign: 'middle'},
       	 {title: '互通卡增量文件ID', field: 'htIncrBlacklistFile', align: 'center', valign: 'middle'},
       	 {title: '互通卡白名单', field: 'htWhitelist', align: 'center', valign: 'middle'},
       	 {title: '可用卡文件ID', field: 'availableCardFile', align: 'center', valign: 'middle'},
       	 {title: '票价表文件ID', field: 'admissionFile', align: 'center', valign: 'middle'},
       	 {title: '折扣率文件ID', field: 'mileageFile', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PosFileUpdate.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PosFileUpdate.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加POS黑名单文件表
 */
PosFileUpdate.openAddPosFileUpdate = function () {
    var index = layer.open({
        type: 2,
        title: '添加POS黑名单文件表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/posFileUpdate/posFileUpdate_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看POS黑名单文件表详情
 */
PosFileUpdate.openPosFileUpdateDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'POS黑名单文件表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/posFileUpdate/posFileUpdate_update/' + PosFileUpdate.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除POS黑名单文件表
 */
PosFileUpdate.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/posFileUpdate/delete", function (data) {
            Feng.success("删除成功!");
            PosFileUpdate.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("posFileUpdateId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询POS黑名单文件表列表
 */
PosFileUpdate.search = function () {
    var queryData = {};
		queryData['id'] = $("#id").val();
    PosFileUpdate.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PosFileUpdate.formParams = function() {
    var queryData = {};
		queryData['id'] = $("#id").val();
    return queryData;
}


$(function () {
    var defaultColunms = PosFileUpdate.initColumn();
    var table = new BSTable(PosFileUpdate.id, "/posFileUpdate/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PosFileUpdate.formParams());
    PosFileUpdate.table = table.init();
});
