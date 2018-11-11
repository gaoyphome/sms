/**
 * 初始化可用卡类型详情对话框
 */
var PosCardTypeInfoDlg = {
    posCardTypeInfoData : {}
};

/**
 * 清除数据
 */
PosCardTypeInfoDlg.clearData = function() {
    this.posCardTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosCardTypeInfoDlg.set = function(key, val) {
    this.posCardTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PosCardTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PosCardTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.PosCardType.layerIndex);
}

/**
 * 收集数据
 */
PosCardTypeInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('mchntcode');
	      this.set('cardPhyType');
	      this.set('cardType');
	      this.set('cardAttr');
	      this.set('costType');
	      this.set('minBalance');
	      this.set('creditBalance');
	      this.set('maxBalance');
	      this.set('maxConsume');
	      this.set('inRate');
	      this.set('inLimit');
	      this.set('outRate');
	      this.set('outLimit');
	      this.set('reserved');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
PosCardTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posCardType/add", function(data){
        Feng.success("添加成功!");
        window.parent.PosCardType.table.refresh();
        PosCardTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posCardTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PosCardTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/posCardType/update", function(data){
        Feng.success("修改成功!");
        window.parent.PosCardType.table.refresh();
        PosCardTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.posCardTypeInfoData);
    ajax.start();
}

$(function() {

});
