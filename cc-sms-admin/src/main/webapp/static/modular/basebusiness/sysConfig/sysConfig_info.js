/**
 * 初始化系统参数管理详情对话框
 */
var SysConfigInfoDlg = {
    sysConfigInfoData : {}
};

/**
 * 清除数据
 */
SysConfigInfoDlg.clearData = function() {
    this.sysConfigInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysConfigInfoDlg.set = function(key, val) {
    this.sysConfigInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysConfigInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SysConfigInfoDlg.close = function() {
    parent.layer.close(window.parent.SysConfig.layerIndex);
}

/**
 * 收集数据
 */
SysConfigInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('category');
	      this.set('paramKey');
	      this.set('paramValue');
	      this.set('remark');
}

/**
 * 提交添加
 */
SysConfigInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sysConfig/add", function(data){
        Feng.success("添加成功!");
        window.parent.SysConfig.table.refresh();
        SysConfigInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysConfigInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SysConfigInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sysConfig/update", function(data){
        Feng.success("修改成功!");
        window.parent.SysConfig.table.refresh();
        SysConfigInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysConfigInfoData);
    ajax.start();
}

$(function() {

});
