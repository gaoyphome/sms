/**
 * 公交POS固件信息管理初始化
 */
var BusPosFirmware = {
    id: "BusPosFirmwareTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusPosFirmware.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id',  visible: false, align: 'center', valign: 'middle'},
       	 {title: '版本号', field: 'firmVersion',  align: 'center', valign: 'middle'},
        {title: 'POS厂商', field: 'posVendor',  align: 'center', valign: 'middle'},
        {title: 'CRC', field: 'crc16',  align: 'center', valign: 'middle'},
        {title: '下载方式', field: 'contentType',  align: 'center', valign: 'middle'},
        {title: 'content', field: '文件内容', align: 'center', valign: 'middle',formatter:function (value, row, index) {
            var e = '<a href="/busPosFirmware/download?id='+row.id+'" mce_href="#1">下载</a> ';
            return e;
        }},
    ];
};

/**
 * 检查是否选中
 */
BusPosFirmware.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusPosFirmware.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公交POS固件信息
 */
BusPosFirmware.openAddBusPosFirmware = function () {
    var index = layer.open({
        type: 2,
        title: '添加公交POS固件信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busPosFirmware/busPosFirmware_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公交POS固件信息详情
 */
BusPosFirmware.openBusPosFirmwareDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公交POS固件信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busPosFirmware/busPosFirmware_update/' + BusPosFirmware.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公交POS固件信息
 */
BusPosFirmware.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busPosFirmware/delete", function (data) {
            Feng.success("删除成功!");
            BusPosFirmware.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busPosFirmwareId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公交POS固件信息列表
 */
BusPosFirmware.search = function () {
    var queryData = {};
		queryData['filename'] = $("#filename").val();
    BusPosFirmware.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
BusPosFirmware.formParams = function() {
    var queryData = {};
        queryData['firmVersion'] = $("#firmVersion").val();
    return queryData;
}


$(function () {
    var defaultColunms = BusPosFirmware.initColumn();
    var table = new BSTable(BusPosFirmware.id, "/busPosFirmware/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(BusPosFirmware.formParams());
    BusPosFirmware.table = table.init();
});
