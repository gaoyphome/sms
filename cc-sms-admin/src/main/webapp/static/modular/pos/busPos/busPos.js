/**
 * 公交POS信息管理初始化
 */
var BusPos = {
    id: "BusPosTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusPos.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'POS机厂商编号', field: 'posVendor', align: 'center', valign: 'middle'},
        {title: '线路名称', field: 'lineName', align: 'center', valign: 'middle'},
        {title: '公交车编号', field: 'busId', align: 'center', valign: 'middle'},
        {title: '车牌号', field: 'plateNumber', align: 'center', valign: 'middle'},
        {title: 'POS-ID', field: 'posId', align: 'center', valign: 'middle'},
        {title: '线路ID', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'SAM-ID', field: 'samId', align: 'center', valign: 'middle'},
       	 {title: 'POS机状态', field: 'state', align: 'center', valign: 'middle'},
       	 {title: '固件版本号', field: 'firmVersion', align: 'center', valign: 'middle'},
       	 {title: '故障原因', field: 'reason', align: 'center', valign: 'middle'},
       	 {title: '备注', field: 'remark', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
       	 {title: '心跳更新时间', field: 'heartbeatTime', visible: false, align : 'center', valign: 'middle'},
       	 {title: '线路版本', field: 'lineVersion', visible: false, align : 'center', valign: 'middle'},
       	 {title: '实体卡增量黑名单版本号', field: 'incrBlackVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '二维码黑名单版本号', field: 'qrBlackVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '二维码黑名单版本号', field: 'incQrBlackVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '互通卡黑名单版本号', field: 'htBlackVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '互通卡增量黑名单版本号', field: 'htIncBlackVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '票价表文件版本号', field: 'priVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '折扣率文件版本号', field: 'milesVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '互通卡白名单版本号', field: 'htWhiteVer', visible: false, align : 'center', valign: 'middle'},
       	 {title: '费率卡二次发行信息版本号', field: 'htTwoVer', visible: false, align : 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
BusPos.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusPos.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公交POS信息
 */
BusPos.openAddBusPos = function () {
    var index = layer.open({
        type: 2,
        title: '添加公交POS信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busPos/busPos_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公交POS信息详情
 */
BusPos.openBusPosDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公交POS信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ? '/busPos/busPos_detail/' : '/busPos/busPos_update/') + BusPos.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公交POS信息
 */
BusPos.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busPos/delete", function (data) {
            Feng.success("删除成功!");
            BusPos.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busPosId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公交POS信息列表
 */
BusPos.search = function () {
    var queryData = {};
		queryData['lineName'] = $("#lineName").val();
		queryData['posId'] = $("#posId").val();
		queryData['plateNumber'] = $("#plateNumber").val();
    BusPos.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
BusPos.formParams = function() {
    var queryData = {};
		queryData['lineName'] = $("#lineName").val();
		queryData['posId'] = $("#posId").val();
		queryData['plateNumber'] = $("#plateNumber").val();
    return queryData;
}


$(function () {
    var defaultColunms = BusPos.initColumn();
    var table = new BSTable(BusPos.id, "/busPos/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(BusPos.formParams());
    BusPos.table = table.init();

    /*$("#posId").bind('input propertychange',function() {
        var intval = $("#posId").val();
        if (intval) {
            intval = parseInt(intval, 10);
            $("#posIdHex").val(intval.toString(16));
        }else{
            $("#posIdHex").val("");
        }
    });
    $("#posIdHex").bind('input propertychange',function(){
        var hexval = $("#posIdHex").val();
        if(hexval) {
            $("#posId").val(parseInt(hexval, 16));
        }else{
            $("#posId").val("");
        }
    });*/
});
