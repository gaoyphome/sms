/**
 * 初始化用户补票情对话框
 */
var QrcodeSysComplementInfoDlg = {
    qrcodeSysComplementInfoData : {}
};

/**
 * 清除数据
 */
QrcodeSysComplementInfoDlg.clearData = function() {
    this.qrcodeSysComplementInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeSysComplementInfoDlg.set = function(key, val) {
    this.qrcodeSysComplementInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeSysComplementInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeSysComplementInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeSysComplement.layerIndex);
}

/**
 * 收集数据
 */
QrcodeSysComplementInfoDlg.collectData = function() {
	      this.set('vsirBmacno');
	      this.set('mouth');
	      this.set('overComplementCount');
	      this.set('sysComplementCount');
	      this.set('userComplementCount');
}

/**
 * 提交添加
 */
QrcodeSysComplementInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeSysComplement/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeSysComplement.table.refresh();
        QrcodeSysComplementInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeSysComplementInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeSysComplementInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeSysComplement/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeSysComplement.table.refresh();
        QrcodeSysComplementInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeSysComplementInfoData);
    ajax.start();
}

$(function() {

});
