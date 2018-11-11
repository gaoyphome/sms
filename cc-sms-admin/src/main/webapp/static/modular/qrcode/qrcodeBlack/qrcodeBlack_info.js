/**
 * 初始化二维码黑名单详情对话框
 */
var QrcodeBlackInfoDlg = {
    qrcodeBlackInfoData : {}
};

/**
 * 清除数据
 */
QrcodeBlackInfoDlg.clearData = function() {
    this.qrcodeBlackInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeBlackInfoDlg.set = function(key, val) {
    this.qrcodeBlackInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeBlackInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeBlackInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeBlack.layerIndex);
}

/**
 * 收集数据
 */
QrcodeBlackInfoDlg.collectData = function() {
	      this.set('tokenId');
	      this.set('vsirBmacno');
	      this.set('reason');
	      this.set('createTime');
	      this.set('blackCount');
	      this.set('operatePerson');
	      this.set('operatePersonId');
	      this.set('state');
	      this.set('flag');
	      this.set('failTime');
}

/**
 * 提交添加
 */
QrcodeBlackInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeBlack/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeBlack.table.refresh();
        QrcodeBlackInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeBlackInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeBlackInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeBlack/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeBlack.table.refresh();
        QrcodeBlackInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeBlackInfoData);
    ajax.start();
}

$(function() {

});
