/**
 * 初始化二维码可疑信息表详情对话框
 */
var QrcodeDubiousInfoDlg = {
    qrcodeDubiousInfoData : {}
};

/**
 * 清除数据
 */
QrcodeDubiousInfoDlg.clearData = function() {
    this.qrcodeDubiousInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeDubiousInfoDlg.set = function(key, val) {
    this.qrcodeDubiousInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeDubiousInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeDubiousInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeDubious.layerIndex);
}

/**
 * 收集数据
 */
QrcodeDubiousInfoDlg.collectData = function() {
	      this.set('vsirBmacno');
	      this.set('reason');
	      this.set('tokenId');
	      this.set('dubiousCount');
}

/**
 * 提交添加
 */
QrcodeDubiousInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeDubious/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeDubious.table.refresh();
        QrcodeDubiousInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeDubiousInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeDubiousInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeDubious/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeDubious.table.refresh();
        QrcodeDubiousInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeDubiousInfoData);
    ajax.start();
}

$(function() {

});
