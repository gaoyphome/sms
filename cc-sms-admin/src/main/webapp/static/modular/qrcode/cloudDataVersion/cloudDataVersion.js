/**
 * 云卡数据版本管理初始化
 */
var CloudDataVersion = {
    id: "CloudDataVersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CloudDataVersion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '商户信息版本', field: 'merchantVersion', align: 'center', valign: 'middle'},
       	 {title: '运营参数版本', field: 'operateParamVersion', align: 'center', valign: 'middle'},
       	 {title: '可用卡类型版本号', field: 'cardtypeVersion', align: 'center', valign: 'middle'},
       	 {title: '黑名单版本号', field: 'blacklistVersion', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
CloudDataVersion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CloudDataVersion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加云卡数据版本
 */
CloudDataVersion.openAddCloudDataVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加云卡数据版本',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cloudDataVersion/cloudDataVersion_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看云卡数据版本详情
 */
CloudDataVersion.openCloudDataVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '云卡数据版本详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cloudDataVersion/cloudDataVersion_update/' + CloudDataVersion.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除云卡数据版本
 */
CloudDataVersion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cloudDataVersion/delete", function (data) {
            Feng.success("删除成功!");
            CloudDataVersion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cloudDataVersionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询云卡数据版本列表
 */
CloudDataVersion.search = function () {
    var queryData = {};
    CloudDataVersion.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
CloudDataVersion.formParams = function() {
    var queryData = {};
    return queryData;
}


$(function () {
    var defaultColunms = CloudDataVersion.initColumn();
    var table = new BSTable(CloudDataVersion.id, "/cloudDataVersion/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(CloudDataVersion.formParams());
    CloudDataVersion.table = table.init();
});
