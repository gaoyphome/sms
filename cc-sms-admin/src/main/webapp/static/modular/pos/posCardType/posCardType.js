/**
 * 可用卡类型管理初始化
 */
var PosCardType = {
    id: "PosCardTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PosCardType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '商户简码', field: 'mchntcode', align: 'center', valign: 'middle'},
       	 {title: '物理类型', field: 'cardPhyType', align: 'center', valign: 'middle'},
       	 {title: '逻辑类型', field: 'cardType', align: 'center', valign: 'middle'},
       	 {title: '卡类型名称', field: 'cardAttr', align: 'center', valign: 'middle'},
       	 {title: '扣款模式', field: 'costType', align: 'center', valign: 'middle'},
       	 {title: '最小余额限制', field: 'minBalance',visible: false, align: 'center', valign: 'middle'},
       	 {title: '最大透支额度', field: 'creditBalance',visible: false, align: 'center', valign: 'middle'},
       	 {title: '最大余额限制', field: 'maxBalance',visible: false, align: 'center', valign: 'middle'},
       	 {title: '最大扣款额度', field: 'maxConsume',visible: false, align: 'center', valign: 'middle'},
       	 {title: '界内优惠率', field: 'inRate',visible: false, align: 'center', valign: 'middle'},
       	 {title: '界内优惠额度', field: 'inLimit',visible: false, align: 'center', valign: 'middle'},
       	 {title: '界外优惠率', field: 'outRate',visible: false, align: 'center', valign: 'middle'},
       	 {title: '界外优惠额度', field: 'outLimit',visible: false, align: 'center', valign: 'middle'},
       	 {title: '备注', field: 'reserved', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
PosCardType.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PosCardType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加可用卡类型
 */
PosCardType.openAddPosCardType = function () {
    var index = layer.open({
        type: 2,
        title: '添加可用卡类型',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/posCardType/posCardType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看可用卡类型详情
 */
PosCardType.openPosCardTypeDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '可用卡类型详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ? '/posCardType/posCardType_detail/' : '/posCardType/posCardType_update/') + PosCardType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除可用卡类型
 */
PosCardType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/posCardType/delete", function (data) {
            Feng.success("删除成功!");
            PosCardType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("posCardTypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询可用卡类型列表
 */
PosCardType.search = function () {
    var queryData = {};
		queryData['mchntcode'] = $("#mchntcode").val();
		queryData['cardAttr'] = $("#cardAttr").val();
    PosCardType.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PosCardType.formParams = function() {
    var queryData = {};
		queryData['mchntcode'] = $("#mchntcode").val();
		queryData['cardAttr'] = $("#cardAttr").val();
    return queryData;
}


$(function () {
    var defaultColunms = PosCardType.initColumn();
    var table = new BSTable(PosCardType.id, "/posCardType/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PosCardType.formParams());
    PosCardType.table = table.init();
});
