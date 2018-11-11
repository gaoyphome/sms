/**
 * 公交POS文件 管理初始化
 */
var CityCardFile = {
    id: "CityCardFileTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CityCardFile.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '文件名', field: 'filename', align: 'center', valign: 'middle'},
       	 {title: '文件大小', field: 'filesize', align: 'center', valign: 'middle'},
       	 {title: 'CRC16', field: 'crc16', align: 'center', valign: 'middle'},
       	 {title: '文件版本', field: 'version', align: 'center', valign: 'middle'},
       	 {title: '线路ID', field: 'lineId', align: 'center', valign: 'middle'},
       	 {title: '子运营公司ID', field: 'subCompanyId', align: 'center', valign: 'middle'},
        {title: 'content', field: '文件内容', align: 'center', valign: 'middle',formatter:function (value, row, index) {
            var e = '<a href="/cityCardFile/download?id='+row.id+'" mce_href="#1">下载</a> ';
            return e;
        }},
    ];
};

/**
 * 检查是否选中
 */
CityCardFile.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CityCardFile.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加公交POS文件 
 */
CityCardFile.openAddCityCardFile = function () {
    var index = layer.open({
        type: 2,
        title: '添加公交POS文件 ',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cityCardFile/cityCardFile_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看公交POS文件 详情
 */
CityCardFile.openCityCardFileDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '公交POS文件 详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cityCardFile/cityCardFile_update/' + CityCardFile.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除公交POS文件 
 */
CityCardFile.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/cityCardFile/delete", function (data) {
            Feng.success("删除成功!");
            CityCardFile.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("cityCardFileId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询公交POS文件 列表
 */
CityCardFile.search = function () {
    var queryData = {};
		queryData['filename'] = $("#filename").val();
    CityCardFile.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
CityCardFile.formParams = function() {
    var queryData = {};
		queryData['filename'] = $("#filename").val();
    return queryData;
}


$(function () {
    var defaultColunms = CityCardFile.initColumn();
    var table = new BSTable(CityCardFile.id, "/cityCardFile/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(CityCardFile.formParams());
    CityCardFile.table = table.init();
});
