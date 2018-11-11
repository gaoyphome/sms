/**
 * 二维码Token管理初始化
 */
var QrcodeTokenInfo = {
    id: "QrcodeTokenInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QrcodeTokenInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id',visible: false, align: 'center', valign: 'middle'},
       	 {title: '虚拟卡号', field: 'vsirBmacno', align: 'center', valign: 'middle'},
       	 {title: '生效时间', field: 'startTime',visible: false,  align: 'center', valign: 'middle'},
       	 {title: '卡号校验位', field: 'cardCheckDigit', visible: false, align: 'center', valign: 'middle'},
       	 {title: '结束时间', field: 'endTime',visible: false,  align: 'center', valign: 'middle'},
       	 {title: 'TokenId', field: 'tokenId', align: 'center', valign: 'middle'},
       	 {title: '卡类型', field: 'cardType', align: 'center', valign: 'middle'},
       	 {title: 'Token类型', field: 'tokenType', align: 'center', valign: 'middle'},
       	 {title: 'Token版本', field: 'tokenVersion', align: 'center', valign: 'middle'},
       	 {title: '密钥ID', field: 'tokenChumid', visible: false, align: 'center', valign: 'middle'},
       	 {title: 'Token密文', field: 'tokenEnc', visible: false, align: 'center', valign: 'middle'},
       	 {title: '脱机交易限额', field: 'dislocatNorm',visible: false,  align: 'center', valign: 'middle'},
       	 {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
       	 {title: '鉴别码', field: 'identification', visible: false, align: 'center', valign: 'middle'},
       	 {title: '更新时间', field: 'updateTime', align: 'center', valign: 'middle'},
       	 {title: '失效方式', field: 'expireType', visible: false, align: 'center', valign: 'middle'},
       	 {title: '失效时间', field: 'expireTime', visible: false,  align: 'center', valign: 'middle'},
       	 {title: '应用场景', field: 'space', visible: false, align: 'center', valign: 'middle'},
       	 {title: '手机号', field: 'userPhone', align: 'center', valign: 'middle'},
       	 {title: '二维码列表', field: 'qrcodeSesq', visible: false, align: 'center', valign: 'middle'},
       	 {title: '条形码列表', field: 'barSesq', visible: false, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
QrcodeTokenInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QrcodeTokenInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码Token
 */
QrcodeTokenInfo.openAddQrcodeTokenInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码Token',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/qrcodeTokenInfo/qrcodeTokenInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码Token详情
 */
QrcodeTokenInfo.openQrcodeTokenInfoDetail = function (detail) {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码Token详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + (detail ? '/qrcodeTokenInfo/qrcodeTokenInfo_detail/':'/qrcodeTokenInfo/qrcodeTokenInfo_update/') + QrcodeTokenInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码Token
 */
QrcodeTokenInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/qrcodeTokenInfo/delete", function (data) {
            Feng.success("删除成功!");
            QrcodeTokenInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("qrcodeTokenInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询二维码Token列表
 */
QrcodeTokenInfo.search = function () {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['userPhone'] = $("#userPhone").val();
    QrcodeTokenInfo.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
QrcodeTokenInfo.formParams = function() {
    var queryData = {};
		queryData['vsirBmacno'] = $("#vsirBmacno").val();
		queryData['userId'] = $("#userId").val();
    return queryData;
}


$(function () {
    var defaultColunms = QrcodeTokenInfo.initColumn();
    var table = new BSTable(QrcodeTokenInfo.id, "/qrcodeTokenInfo/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(QrcodeTokenInfo.formParams());
    QrcodeTokenInfo.table = table.init();
});
