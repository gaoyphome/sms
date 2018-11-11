/**
 * 初始化鍟嗘埛POS淇℃伅详情对话框
 */
var MchntPosInfoDlg = {
    mchntPosInfoData : {}
};

/**
 * 清除数据
 */
MchntPosInfoDlg.clearData = function() {
    this.mchntPosInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MchntPosInfoDlg.set = function(key, val) {
    this.mchntPosInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MchntPosInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MchntPosInfoDlg.close = function() {
    parent.layer.close(window.parent.MchntPos.layerIndex);
}

/**
 * 收集数据
 */
MchntPosInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('posId');
	      this.set('mchntId');
	      this.set('mchntType');
	      this.set('installTime');
	      this.set('createTime');
}

/**
 * 提交添加
 */
MchntPosInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mchntPos/add", function(data){
        Feng.success("添加成功!");
        window.parent.MchntPos.table.refresh();
        MchntPosInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mchntPosInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MchntPosInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mchntPos/update", function(data){
        Feng.success("修改成功!");
        window.parent.MchntPos.table.refresh();
        MchntPosInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mchntPosInfoData);
    ajax.start();
}

$(function() {
    $("#mchntType").val($("#mchntTypeHidden").val());
});
