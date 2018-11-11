/**
 * 商户POS信息管理初始化
 */
var MchntPos = {
    id: "MchntPosTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MchntPos.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'POS机ID', field: 'posId', align: 'center', valign: 'middle'},
       	 {title: '商户ID', field: 'mchntId', align: 'center', valign: 'middle'},
       	 {title: '商户类型', field: 'mchntType', align: 'center', valign: 'middle'},
       	 {title: 'POS机安装时间', field: 'installTime', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
MchntPos.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MchntPos.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加商户POS信息
 */
MchntPos.openAddMchntPos = function () {
    var index = layer.open({
        type: 2,
        title: '添加商户POS信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mchntPos/mchntPos_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看商户POS信息详情
 */
MchntPos.openMchntPosDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商户POS信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mchntPos/mchntPos_update/' + MchntPos.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除商户POS信息
 */
MchntPos.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mchntPos/delete", function (data) {
            Feng.success("删除成功!");
            MchntPos.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mchntPosId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询商户POS信息列表
 */
MchntPos.search = function () {
    var queryData = {};
		queryData['mchntId'] = $("#mchntId").val();
		queryData['posId'] = $("#posId").val();
    MchntPos.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
MchntPos.formParams = function() {
    var queryData = {};
		queryData['mchntId'] = $("#mchntId").val();
		queryData['posId'] = $("#posId").val();
    return queryData;
}


$(function () {
    var defaultColunms = MchntPos.initColumn();
    var table = new BSTable(MchntPos.id, "/mchntPos/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(MchntPos.formParams());
    MchntPos.table = table.init();
});
