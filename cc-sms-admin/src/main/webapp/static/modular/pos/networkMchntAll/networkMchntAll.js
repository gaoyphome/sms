/**
 * 商户信息管理初始化
 */
var NetworkMchntAll = {
    id: "NetworkMchntAllTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NetworkMchntAll.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '商户ID', field: 'mchntId', align: 'center', valign: 'middle'},
       	 {title: '商户名', field: 'mchntname', align: 'center', valign: 'middle'},
       	 {title: '商户简码', field: 'mchntcode', align: 'center', valign: 'middle'},
       	 {title: '商户类型', field: 'mchntType', align: 'center', valign: 'middle'},
       	 {title: '上级商户ID', field: 'fatherId', align: 'center', valign: 'middle'},
       	 {title: '淡季时间范围', field: 'lowSeason',visible: false, align: 'center', valign: 'middle'},
       	 {title: '淡季票价', field: 'lowPrice', visible: false, align: 'center', valign: 'middle'},
       	 {title: '旺季时间范围', field: 'midSeason', visible: false, align: 'center', valign: 'middle'},
       	 {title: '旺季票价', field: 'midPrice', visible: false, align: 'center', valign: 'middle'},
       	 {title: '固定电话', field: 'telphone', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'Email', field: 'email', visible: false, align: 'center', valign: 'middle'},
       	 {title: '联系人', field: 'contacts', align: 'center', valign: 'middle'},
       	 {title: '联系人电话', field: 'contactsMobile', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
NetworkMchntAll.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        NetworkMchntAll.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加商户信息
 */
NetworkMchntAll.openAddNetworkMchntAll = function () {
    var index = layer.open({
        type: 2,
        title: '添加商户信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/networkMchntAll/networkMchntAll_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看商户信息详情
 */
NetworkMchntAll.openNetworkMchntAllDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商户信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ? '/networkMchntAll/networkMchntAll_detail/' : '/networkMchntAll/networkMchntAll_update/') + NetworkMchntAll.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除商户信息
 */
NetworkMchntAll.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/networkMchntAll/delete", function (data) {
            Feng.success("删除成功!");
            NetworkMchntAll.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("networkMchntAllId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询商户信息列表
 */
NetworkMchntAll.search = function () {
    var queryData = {};
		queryData['mchntId'] = $("#mchntId").val();
		queryData['mchntname'] = $("#mchntname").val();
		queryData['contacts'] = $("#contacts").val();
    NetworkMchntAll.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
NetworkMchntAll.formParams = function() {
    var queryData = {};
		queryData['mchntId'] = $("#mchntId").val();
		queryData['mchntname'] = $("#mchntname").val();
		queryData['contacts'] = $("#contacts").val();
    return queryData;
}


$(function () {
    var defaultColunms = NetworkMchntAll.initColumn();
    var table = new BSTable(NetworkMchntAll.id, "/networkMchntAll/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(NetworkMchntAll.formParams());
    NetworkMchntAll.table = table.init();
});
