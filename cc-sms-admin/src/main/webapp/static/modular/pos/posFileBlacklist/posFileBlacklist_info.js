/**
 * 初始化POS黑名单文件信息 详情对话框
 */
var PosFileBlacklistInfoDlg = {
    posFileBlacklistInfoData : {}
};

/**
 * 清除数据
 */
PosFileBlacklistInfoDlg.clearData = function() {
    this.posFileBlacklistInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosFileBlacklistInfoDlg.set = function(key, val) {
    this.posFileBlacklistInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosFileBlacklistInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PosFileBlacklistInfoDlg.close = function() {
    parent.layer.close(window.parent.PosFileBlacklist.layerIndex);
}

/**
 * 收集数据
 */
PosFileBlacklistInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('filename');
	      this.set('filesize');
	      this.set('crc16');
	      this.set('content');
	      this.set('version');
}

/**
 * 提交添加
 */
PosFileBlacklistInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posFileBlacklist/add", function(data){
        if(data['code'] != 200){
            Feng.error("操作失败:" + data['message'] + "!");
        }else{
            Feng.success("添加成功!");
            window.parent.PosFileBlacklist.table.refresh();
        }
        PosFileBlacklistInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posFileBlacklistInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PosFileBlacklistInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posFileBlacklist/update", function(data){
        Feng.success("修改成功!");
        window.parent.PosFileBlacklist.table.refresh();
        PosFileBlacklistInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posFileBlacklistInfoData);
    ajax.start();
}

/**
 * 文件上传
 */
//异步文件上传(jquery)
PosFileBlacklistInfoDlg.uploadSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/posFileBlacklist/add",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "filename": this.posFileBlacklistInfoData['filename'],
            "version": this.posFileBlacklistInfoData['version']
        },
        success: function (data, status) {
            var jdata = $.parseJSON(data.replace(/<.*?>/ig,""));

            if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success("添加成功!");
                window.parent.PosFileBlacklist.table.refresh();
            }
            PosFileBlacklistInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        }
    });
    return false;
}

$(function() {

});
