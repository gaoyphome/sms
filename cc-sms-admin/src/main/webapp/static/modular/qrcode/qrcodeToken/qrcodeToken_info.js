/**
 * 虚拟卡号表详情对话框
 */
var QrcodeTokenInfoDlg = {
    qrcodeTokenInfoData : {}
};

/**
 * 清除数据
 */
QrcodeTokenInfoDlg.clearData = function() {
    this.qrcodeTokenInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeTokenInfoDlg.set = function(key, val) {
    this.qrcodeTokenInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeTokenInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeTokenInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeToken.layerIndex);
}

/**
 * 收集数据
 */
QrcodeTokenInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('vsirBmacno');
	      this.set('userId');
	      this.set('createTime');
	      this.set('updateTime');
	      this.set('cardCheckDigit');
	      this.set('thirdInstid');
	      this.set('thirdChannelid');
}

/**
 * 提交添加
 */
QrcodeTokenInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeToken/add", function(data){
        Feng.success("添加成功!");
        window.parent.QrcodeToken.table.refresh();
        QrcodeTokenInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeTokenInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeTokenInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeToken/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeToken.table.refresh();
        QrcodeTokenInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeTokenInfoData);
    ajax.start();
}

$(function() {

});
