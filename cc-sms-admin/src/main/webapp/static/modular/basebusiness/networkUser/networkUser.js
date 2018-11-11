/**
 * 用户信息管理初始化
 */
var NetworkUser = {
    id: "NetworkUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NetworkUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '用户ID', field: 'userId', align: 'center', valign: 'middle'},
       	 {title: '用户名', field: 'username', align: 'center', valign: 'middle'},
       	 {title: '密码', field: 'password', align: 'center', valign: 'middle'},
       	 {title: '用户类型', field: 'userType', align: 'center', valign: 'middle'},
       	 {title: '微信昵称', field: 'nickname', align: 'center', valign: 'middle'},
       	 {title: '性别', field: 'sex', align: 'center', valign: 'middle'},
       	 {title: '生日', field: 'birthday', align: 'center', valign: 'middle'},
       	 {title: '手机号', field: 'mobile', align: 'center', valign: 'middle'},
       	 {title: '邮箱', field: 'email', align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
NetworkUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        NetworkUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户信息
 */
NetworkUser.openAddNetworkUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/networkUser/networkUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户信息详情
 */
NetworkUser.openNetworkUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/networkUser/networkUser_update/' + NetworkUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户信息
 */
NetworkUser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/networkUser/delete", function (data) {
            Feng.success("删除成功!");
            NetworkUser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("networkUserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户信息列表
 */
NetworkUser.search = function () {
    var queryData = {};
		queryData['username'] = $("#username").val();
		queryData['mobile'] = $("#mobile").val();
    NetworkUser.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
NetworkUser.formParams = function() {
    var queryData = {};
		queryData['username'] = $("#username").val();
		queryData['mobile'] = $("#mobile").val();
    return queryData;
}


$(function () {
    var defaultColunms = NetworkUser.initColumn();
    var table = new BSTable(NetworkUser.id, "/networkUser/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(NetworkUser.formParams());
    NetworkUser.table = table.init();
});
