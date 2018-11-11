/**
 * 初始化POS文件详情对话框
 */
var PosFileInfoDlg = {
    posFileInfoData: {}
};

/**
 * 清除数据
 */
PosFileInfoDlg.clearData = function () {
    this.posFileInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosFileInfoDlg.set = function (key, val) {
    this.posFileInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosFileInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PosFileInfoDlg.close = function () {
    parent.layer.close(window.parent.PosFile.layerIndex);
}

/**
 * 收集数据
 */
PosFileInfoDlg.collectData = function () {
    this.set('id');
    this.set('filename');
    this.set('filesize');
    this.set('crc16');
    this.set('content');
}

/**
 * 提交添加
 */
PosFileInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posFile/add", function (data) {
        Feng.success("添加成功!");
        window.parent.PosFile.table.refresh();
        PosFileInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posFileInfoData);
    ajax.start();
}


/**
 * 文件上传
 */
//异步文件上传(jquery)
PosFileInfoDlg.uploadSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/posFile/add",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "filename": this.posFileInfoData['filename']

        },
        success: function (data, status) {
            Feng.success("添加成功!");
            window.parent.PosFile.table.refresh();
            PosFileInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        }
    });
    return false;
}

/**
 * 提交修改
 */
PosFileInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posFile/update", function (data) {
        Feng.success("修改成功!");
        window.parent.PosFile.table.refresh();
        PosFileInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posFileInfoData);
    ajax.start();
}

$(function () {

});
