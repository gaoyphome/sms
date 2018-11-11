/**
 * 初始化POS信息详情对话框
 */
var PosInfoInfoDlg = {
    posInfoInfoData : {}
};

/**
 * 清除数据
 */
PosInfoInfoDlg.clearData = function() {
    this.posInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosInfoInfoDlg.set = function(key, val) {
    this.posInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PosInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.PosInfo.layerIndex);
}

/**
 * 收集数据
 */
PosInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('posId');
	      this.set('samId');
	      this.set('posCode');
	      this.set('posDesc');
	      this.set('longitude');
	      this.set('latitude');
	      this.set('locDesc');
	      this.set('state');
	      this.set('version');
	      this.set('firmVersion');
	      this.set('blacklistVersion');
	      this.set('termVersion');
	      this.set('cardtypeVersion');
	      this.set('reason');
	      this.set('createTime');
	      this.set('updateTime');
	      this.set('lineVersion');
	      this.set('incrBlackVer');
	      this.set('qrBlackVer');
	      this.set('incQrBlackVer');
	      this.set('htBlackVer');
	      this.set('htIncBlackVer');
	      this.set('priVer');
	      this.set('milesVer');
	      this.set('htWhiteVer');
}

/**
 * 提交添加
 */
PosInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.PosInfo.table.refresh();
        PosInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PosInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.PosInfo.table.refresh();
        PosInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posInfoInfoData);
    ajax.start();
}

$(function() {

});
