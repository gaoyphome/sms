/**
 * POS文件管理初始化
 */
var PosFile = {
    id: "PosFileTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PosFile.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
       	 {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
       	 {title: '文件名', field: 'filename', align: 'center', valign: 'middle'},
       	 {title: '文件尺寸', field: 'filesize', align: 'center', valign: 'middle'},
       	 {title: 'CRC16', field: 'crc16', align: 'center', valign: 'middle'},
       	 {title: '文件内容', field: '#', align: 'center', valign: 'middle', 
       		formatter:function(value,row,index){    
                var e = '<a href="/posFile/download?id='+row.id+'" mce_href="#1">下载</a> ';    
               // var d = '<a href="#" mce_href="#" onclick="onDeleteClick(\''+ row.id +'\')">删除</a> ';
                return e;    
            } }
    ];
};

PosFile.download = function(id){
	var url = "/posFile/download";
	var params = "";
	$.ajax({
	    url: url,
	    type: "GET",
	    data: params,
	    success: function(response, status, request){
	      var disp = request.getResponseHeader('Content-Disposition');
	        if (disp && disp.search('attachment') != -1) {
	        var form = $('<form method="GET" action="' + url + '">');
	        $.each(params, function(k, v) {
	            form.append($('<input type="hidden" name="' + k +
	                '" value="' + v + '">'));
	            });
	        form.appendTo('body').submit().remove();
	        return;
	      }
	      if(response.status == "ok"){
	         showResult(response.data);
	      }else{
	         showError('Error: ', response.msg);
	      }
	    }
	})
}


/**
 * 检查是否选中
 */
PosFile.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PosFile.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加POS文件
 */
PosFile.openAddPosFile = function () {
    var index = layer.open({
        type: 2,
        title: '添加POS文件',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/posFile/posFile_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看POS文件详情
 */
PosFile.openPosFileDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'POS文件详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/posFile/posFile_update/' + PosFile.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除POS文件
 */
PosFile.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/posFile/delete", function (data) {
            Feng.success("删除成功!");
            PosFile.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("posFileId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询POS文件列表
 */
PosFile.search = function () {
    var queryData = {};
		queryData['filename'] = $("#filename").val();
    PosFile.table.refresh({query: queryData});
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PosFile.formParams = function() {
    var queryData = {};
		queryData['filename'] = $("#filename").val();
    return queryData;
}


$(function () {
    var defaultColunms = PosFile.initColumn();
    var table = new BSTable(PosFile.id, "/posFile/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PosFile.formParams());
    PosFile.table = table.init();
});
