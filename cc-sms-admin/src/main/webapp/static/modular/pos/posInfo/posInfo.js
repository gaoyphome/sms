/**
 * POS信息管理初始化
 */
var PosInfo = {
    id: "PosInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PosInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS-ID', field: 'posId', align: 'center', valign: 'middle'},
       	 {title: 'SAM-ID', field: 'samId', align: 'center', valign: 'middle'},
       	 {title: 'POS编码', field: 'posCode', align: 'center', valign: 'middle'},
       	 {title: 'POS描述', field: 'posDesc', align: 'center', valign: 'middle'},
       	 {title: '经度', field: 'longitude', align: 'center', valign: 'middle'},
       	 {title: '纬度', field: 'latitude', align: 'center', valign: 'middle'},
       	 {title: '位置描述', field: 'locDesc',visible: false, align: 'center', valign: 'middle'},
       	 {title: '状态', field: 'state',visible: false, align: 'center', valign: 'middle'},
       	 {title: '版本号', field: 'version',visible: false, align: 'center', valign: 'middle'},
       	 {title: '固件版本', field: 'firmVersion',visible: false, align: 'center', valign: 'middle'},
       	 {title: '黑名单版本', field: 'blacklistVersion',visible: false, align: 'center', valign: 'middle'},
       	 {title: '终端参数版本', field: 'termVersion',visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡类型版本', field: 'cardtypeVersion',visible: false, align: 'center', valign: 'middle'},
       	 {title: '故障原因', field: 'reason',visible: false, align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
       	 {title: '线路版本', field: 'lineVersion',visible: false, align: 'center', valign: 'middle'},
       	 {title: '实体卡增量黑名单版本', field: 'incrBlackVer',visible: false, align: 'center', valign: 'middle'},
       	 {title: '二维码黑名单版本', field: 'qrBlackVer',visible: false, align: 'center', valign: 'middle'},
       	 {title: '二维码黑名单增量版本', field: 'incQrBlackVer',visible: false, align: 'center', valign: 'middle'},
       	 {title: '互通卡黑名单版本', field: 'htBlackVer',visible: false, align: 'center', valign: 'middle'},
       	 {title: '互通卡增量黑名单版本', field: 'htIncBlackVer',visible: false, align: 'center', valign: 'middle'},
       	 {title: '票价表文件版本', field: 'priVer',visible: false, align: 'center', valign: 'middle'},
       	 {title: '折扣率文件版本', field: 'milesVer',visible: false, align: 'center', valign: 'middle'},
       	 {title: '互通卡白名单版本', field: 'htWhiteVer',visible: false, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PosInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PosInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加POS信息
 */
PosInfo.openAddPosInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加POS信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/posInfo/posInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看POS信息详情
 */
PosInfo.openPosInfoDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'POS信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ? '/posInfo/posInfo_detail/' : '/posInfo/posInfo_update/') + PosInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除POS信息
 */
PosInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/posInfo/delete", function (data) {
            Feng.success("删除成功!");
            PosInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("posInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询POS信息列表
 */
PosInfo.search = function () {
    var queryData = {};
		queryData['posId'] = $("#posId").val();
		queryData['samId'] = $("#samId").val();
    PosInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PosInfo.formParams = function() {
    var queryData = {};
		queryData['posId'] = $("#posId").val();
		queryData['samId'] = $("#samId").val();
    return queryData;
}


$(function () {
    var defaultColunms = PosInfo.initColumn();
    var table = new BSTable(PosInfo.id, "/posInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PosInfo.formParams());
    PosInfo.table = table.init();
});
