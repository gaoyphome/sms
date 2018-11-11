/**
 * 初始化二维码Token详情对话框
 */
var QrcodeTokenInfoInfoDlg = {
    qrcodeTokenInfoInfoData : {}
};

/**
 * 清除数据
 */
QrcodeTokenInfoInfoDlg.clearData = function() {
    this.qrcodeTokenInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeTokenInfoInfoDlg.set = function(key, val) {
    this.qrcodeTokenInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeTokenInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeTokenInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeTokenInfo.layerIndex);
}

/**
 * 收集数据
 */
QrcodeTokenInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('vsirBmacno');
	      this.set('startTime');
	      this.set('cardCheckDigit');
	      this.set('endTime');
	      this.set('tokenId');
	      this.set('cardType');
	      this.set('tokenType');
	      this.set('tokenVersion');
	      this.set('tokenChumid');
	      this.set('tokenEnc');
	      this.set('dislocatNorm');
	      this.set('createTime');
	      this.set('identification');
	      this.set('updateTime');
	      this.set('expireType');
	      this.set('expireTime');
	      this.set('space');
	      this.set('userPhone');
	      this.set('qrcodeSesq');
	      this.set('barSesq');
}

/**
 * 提交添加
 */
QrcodeTokenInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeTokenInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeTokenInfo.table.refresh();
        QrcodeTokenInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeTokenInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeTokenInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeTokenInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeTokenInfo.table.refresh();
        QrcodeTokenInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeTokenInfoInfoData);
    ajax.start();
}

$(function() {

});
