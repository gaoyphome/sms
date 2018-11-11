/**
 * 线路信息管理初始化
 */
var LineInfo = {
    id: "LineInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 车辆信息管理初始化
 */
var BusInfo = {
    id: "BusInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};
/**
 * 初始化表格的列
 */
LineInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '线路编号', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: '文件编号', field: 'fileId', align: 'center', valign: 'middle'},
       	 {title: '线路名称', field: 'name', align: 'center', valign: 'middle'},
       	 {title: '公司编号', field: 'companyId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '分公司编号', field: 'subCompanyId', visible: false, align: 'center', valign: 'middle'},
       	 {title: '生效日期', field: 'dateEnable', align: 'center', valign: 'middle'},
       	 {title: '本地未定义卡', field: 'localNoMode', visible: false, align: 'center', valign: 'middle'},
       	 {title: '异地未定义卡', field: 'offsiteNoModel', visible: false, align: 'center', valign: 'middle'},
       	 {title: '车辆属性', field: 'busAttr', visible: false, align: 'center', valign: 'middle'},
       	 {title: '异地逃补模式', field: 'offsiteTicketModel', visible: false, align: 'center', valign: 'middle'},
       	 {title: '方向不同补票', field: 'difTicketRule', visible: false, align: 'center', valign: 'middle'},
       	 {title: '补票折扣', field: 'ticketDiscountFlag', visible: false, align: 'center', valign: 'middle'},
       	 {title: '功能开关', field: 'functionSwitch', visible: false, align: 'center', valign: 'middle'},
       	 {title: '卡号', field: 'cardIssuerNum', visible: false, align: 'center', valign: 'middle'},
       	 {title: '线路属性', field: 'lineAttr', visible: false, align: 'center', valign: 'middle'},
       	 {title: '换乘时限', field: 'exchangeTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '基本票价', field: 'priceBase', align: 'center', valign: 'middle'},
       	 {title: '上行市界起点', field: 'cityLeaveUp', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下行市界起点', field: 'cityLeaveDown', visible: false, align: 'center', valign: 'middle'},
       	 {title: '上行站点数量', field: 'stationsNumUp', visible: false, align: 'center', valign: 'middle'},
       	 {title: '下行站点数量', field: 'stationsNumDn', visible: false, align: 'center', valign: 'middle'},
       	 {title: '格式版本号', field: 'fileVersion', align: 'center', valign: 'middle'},
       	 {title: '线路版本号', field: 'pltLineVersion', align: 'center', valign: 'middle'},
       	 {title: '是否生成', field: 'createFlag', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
LineInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LineInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加线路信息管理
 */
LineInfo.openAddLineInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加线路信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lineInfo/lineInfo_add'
    });
    this.layerIndex = index;
};

LineInfo.openImport = function () {
    var index = layer.open({
        type: 2,
        title: '导入线路信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lineInfo/lineInfo_import'
    });
    this.layerIndex = index;
};

/**
 * 打开查看线路信息管理详情
 */
LineInfo.openLineInfoDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '线路信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ? '/lineInfo/lineInfo_detail/':'/lineInfo/lineInfo_update/') + LineInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

LineInfo.openStationInfo = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '站点详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath +  '/lineInfo/lineInfo_stationdetail/' + LineInfo.seItem.id
        });
        this.layerIndex = index;
    }
}

LineInfo.openPriceInfo = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '费率详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath +  '/lineInfo/lineInfo_pricedetail/' + LineInfo.seItem.id
        });
        this.layerIndex = index;
    }
}
/**
 * 删除线路信息管理
 */
LineInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/lineInfo/delete", function (data) {
            Feng.success("删除成功!");
            LineInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("lineInfoId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 新增车辆信息
 */
LineInfo.addBus = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '新增车辆信息',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/lineInfo/addbus/' + LineInfo.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 查询线路信息管理列表
 */
LineInfo.search = function () {
    var queryData = {};
		queryData['name'] = $("#name").val();
		queryData['lineId'] = $("#lineId").val();
    LineInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
LineInfo.formParams = function() {
    var queryData = {};
		queryData['name'] = $("#name").val();
		queryData['lineId'] = $("#lineId").val();
    return queryData;
}

LineInfo.downloadfile = function () {
    var $form = $('<form method="GET"></form>');
    $form.attr('action', '/lineInfo/downloadfile');
    $form.appendTo($('body'));
    $form.submit();
}

$(function () {
    var defaultColunms = LineInfo.initColumn();
    var table = new BSTable(LineInfo.id, "/lineInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(LineInfo.formParams());
    BusInfo.table = table.init();
    LineInfo.table = table.init();
});
