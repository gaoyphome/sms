/**
 * 初始化POS黑名单文件表详情对话框
 */
var PosFileUpdateInfoDlg = {
    posFileUpdateInfoData : {}
};

/**
 * 清除数据
 */
PosFileUpdateInfoDlg.clearData = function() {
    this.posFileUpdateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosFileUpdateInfoDlg.set = function(key, val) {
    this.posFileUpdateInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosFileUpdateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PosFileUpdateInfoDlg.close = function() {
    parent.layer.close(window.parent.PosFileUpdate.layerIndex);
}

/**
 * 收集数据
 */
PosFileUpdateInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('lineVersion');
	      this.set('linefile');
	      this.set('incrBlacklistFile');
	      this.set('qrBlacklistFile');
	      this.set('qrIncrBlacklistFile');
	      this.set('htBlacklistFile');
	      this.set('htIncrBlacklistFile');
	      this.set('htWhitelist');
	      this.set('availableCardFile');
	      this.set('admissionFile');
	      this.set('mileageFile');
}

/**
 * 提交添加
 */
PosFileUpdateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posFileUpdate/add", function(data){
        Feng.success("添加成功!");
        window.parent.PosFileUpdate.table.refresh();
        PosFileUpdateInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posFileUpdateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PosFileUpdateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posFileUpdate/update", function(data){
        Feng.success("修改成功!");
        window.parent.PosFileUpdate.table.refresh();
        PosFileUpdateInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posFileUpdateInfoData);
    ajax.start();
}

$(function() {

});
