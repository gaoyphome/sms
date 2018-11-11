/**
 * 实体互通卡折扣设置 管理初始化
 */
var ExchangeCardDiscount = {
    id: "ExchangeCardDiscountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ExchangeCardDiscount.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '运营分公司', field: 'subCompanyId',  align: 'center', valign: 'middle'},
       	 {title: '机构标识', field: 'iin',  align: 'center', valign: 'middle'},
       	 {title: '逻辑类型', field: 'cardType',  align: 'center', valign: 'middle'},
       	 {title: '收费模式', field: 'chargeMode',  align: 'center', valign: 'middle'},
       	 {title: '最大透支额度', field: 'creditBalance', align: 'center', valign: 'middle'},
       	 {title: '最大扣款额度', field: 'maxConsume', align: 'center', valign: 'middle'},
       	 {title: '最大余额限制', field: 'maxBalance', align: 'center', valign: 'middle'},
       	 {title: '最小余额限制', field: 'minBalance', align: 'center', valign: 'middle'},
       	 {title: '界内优惠率', field: 'inRate', align: 'center', valign: 'middle'},
       	 {title: '界内优惠额度', field: 'inLimit', align: 'center', valign: 'middle'},
       	 {title: '界外优惠率', field: 'outRate', align: 'center', valign: 'middle'},
       	 {title: '界外优惠额度', field: 'outLimit', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime',  align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
ExchangeCardDiscount.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ExchangeCardDiscount.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加实体互通卡折扣设置 
 */
ExchangeCardDiscount.openAddExchangeCardDiscount = function () {
    var index = layer.open({
        type: 2,
        title: '添加实体互通卡折扣设置 ',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/exchangeCardDiscount/exchangeCardDiscount_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看实体互通卡折扣设置 详情
 */
ExchangeCardDiscount.openExchangeCardDiscountDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '实体互通卡折扣设置 详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/exchangeCardDiscount/exchangeCardDiscount_update/' + ExchangeCardDiscount.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除实体互通卡折扣设置 
 */
ExchangeCardDiscount.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/exchangeCardDiscount/delete", function (data) {
            Feng.success("删除成功!");
            ExchangeCardDiscount.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("exchangeCardDiscountId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询实体互通卡折扣设置 列表
 */
ExchangeCardDiscount.search = function () {
    var queryData = {};
		queryData['subCompanyId'] = $("#subCompanyId").val();
    ExchangeCardDiscount.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
ExchangeCardDiscount.formParams = function() {
    var queryData = {};
		queryData['subCompanyId'] = $("#subCompanyId").val();
    return queryData;
}
/**
 * 打开文件导入页面
 */
ExchangeCardDiscount.openImport = function () {
    var index = layer.open({
        type: 2,
        title: '导入Z3文件',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/exchangeCardDiscount/exchangeCardDiscount_import'
    });
    this.layerIndex = index;
};
$(function () {
    var defaultColunms = ExchangeCardDiscount.initColumn();
    var table = new BSTable(ExchangeCardDiscount.id, "/exchangeCardDiscount/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(ExchangeCardDiscount.formParams());
    ExchangeCardDiscount.table = table.init();
});
