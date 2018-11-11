/**
 * 初始化实体卡版本号详情对话框
 */
var CardDiscountVersionInfoDlg = {
    cardDiscountVersionInfoData : {}
};

/**
 * 清除数据
 */
CardDiscountVersionInfoDlg.clearData = function() {
    this.cardDiscountVersionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CardDiscountVersionInfoDlg.set = function(key, val) {
    this.cardDiscountVersionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CardDiscountVersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CardDiscountVersionInfoDlg.close = function() {
    parent.layer.close(window.parent.CardDiscountVersion.layerIndex);
}

/**
 * 收集数据
 */
CardDiscountVersionInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('subCompanyId');
	      this.set('pltVersion');
	      this.set('createFlag');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
CardDiscountVersionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cardDiscountVersion/add", function(data){
        Feng.success("添加成功!");
        window.parent.CardDiscountVersion.table.refresh();
        CardDiscountVersionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cardDiscountVersionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CardDiscountVersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cardDiscountVersion/update", function(data){
        Feng.success("修改成功!");
        window.parent.CardDiscountVersion.table.refresh();
        CardDiscountVersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cardDiscountVersionInfoData);
    ajax.start();
}

$(function() {

});
