/**
 * 初始化POS固件版本详情对话框
 */
var PosVersionInfoDlg = {
    posVersionInfoData : {}
};

/**
 * 清除数据
 */
PosVersionInfoDlg.clearData = function() {
    this.posVersionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosVersionInfoDlg.set = function(key, val) {
    this.posVersionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosVersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PosVersionInfoDlg.close = function() {
    parent.layer.close(window.parent.PosVersion.layerIndex);
}

/**
 * 收集数据
 */
PosVersionInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('firmVersion');
	      this.set('filesize');
	      this.set('fileId');
	      this.set('content');
	      this.set('path');
	      this.set('type');
	      this.set('upgradeType');
	      this.set('mchntId');
	      this.set('posId');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
PosVersionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posVersion/add", function(data){
        Feng.success("添加成功!");
        window.parent.PosVersion.table.refresh();
        PosVersionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posVersionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PosVersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posVersion/update", function(data){
        Feng.success("修改成功!");
        window.parent.PosVersion.table.refresh();
        PosVersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posVersionInfoData);
    ajax.start();
}


/**
 * 文件上传
 */
//异步文件上传(jquery)
PosVersionInfoDlg.importfileSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/posVersion/add",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "firmVersion": this.posVersionInfoData['firmVersion'],
            "fileId": this.posVersionInfoData['fileId'],
            "content": this.posVersionInfoData['content'],
            "path": this.posVersionInfoData['path'],
            "type": this.posVersionInfoData['type'],
            "upgradeType": this.posVersionInfoData['upgradeType'],
            "mchntId": this.posVersionInfoData['mchntId'],
            "posId": this.posVersionInfoData['posId'],
        },
        success: function (data, status) {
            // var formatdata = function (data) {
            data = data.substring(5, data.length-6);
            var jdata = JSON.parse(data);
            if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success("添加成功!");
                window.parent.PosVersion.table.refresh();
            }
            PosVersionInfoDlg.close();
            // }
            // formatdata(data);
            /*Feng.success("添加成功!");
            window.parent.LineInfo.table.refresh();
            LineInfoInfoDlg.close();*/
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data['message'] + "!");
        }
    });
    return false;
}

$(function() {
    $("#type").val($("#typeHidden").val());
    $("#upgradeType").val($("#upgradeTypeHidden").val());
});
