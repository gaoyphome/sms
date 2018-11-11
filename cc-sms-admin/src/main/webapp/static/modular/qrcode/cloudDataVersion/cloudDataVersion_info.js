/**
 * 初始化云卡数据版本详情对话框
 */
var CloudDataVersionInfoDlg = {
    cloudDataVersionInfoData : {}
};

/**
 * 清除数据
 */
CloudDataVersionInfoDlg.clearData = function() {
    this.cloudDataVersionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CloudDataVersionInfoDlg.set = function(key, val) {
    this.cloudDataVersionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CloudDataVersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CloudDataVersionInfoDlg.close = function() {
    parent.layer.close(window.parent.CloudDataVersion.layerIndex);
}

/**
 * 收集数据
 */
CloudDataVersionInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('merchantVersion');
	      this.set('operateParamVersion');
	      this.set('cardtypeVersion');
	      this.set('blacklistVersion');
}

/**
 * 提交添加
 */
CloudDataVersionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cloudDataVersion/add", function(data){
        Feng.success("添加成功!");
        window.parent.CloudDataVersion.table.refresh();
        CloudDataVersionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cloudDataVersionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CloudDataVersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cloudDataVersion/update", function(data){
        Feng.success("修改成功!");
        window.parent.CloudDataVersion.table.refresh();
        CloudDataVersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cloudDataVersionInfoData);
    ajax.start();
}

$(function() {

});
