/**
 * 初始化二维码折扣详情对话框
 */
var QrcodeDiscountInfoDlg = {
    qrcodeDiscountInfoData : {}
};

/**
 * 清除数据
 */
QrcodeDiscountInfoDlg.clearData = function() {
    this.qrcodeDiscountInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeDiscountInfoDlg.set = function(key, val) {
    this.qrcodeDiscountInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QrcodeDiscountInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QrcodeDiscountInfoDlg.close = function() {
    parent.layer.close(window.parent.QrcodeDiscount.layerIndex);
}

/**
 * 收集数据
 */
QrcodeDiscountInfoDlg.collectData = function() {
	      this.set('subCompanyId');
	      this.set('cardType');
	      this.set('cityInDiscount');
	      this.set('cityInPreferentialAmount');
	      this.set('cityOutDiscount');
	      this.set('maxPreferentialAmount');
	      this.set('cityOutPreferentialAmount');
}

/**
 * 提交添加
 */
QrcodeDiscountInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeDiscount/add", function(data){
        if(data['code'] != 200){
            Feng.error("操作失败:" + data['message'] + "!");
        }else{
            Feng.success("添加成功!");
        }
        window.parent.QrcodeDiscount.table.refresh();
        QrcodeDiscountInfoDlg.close();
    },function(data){
        if(data['code'] != 200){
            Feng.error("操作失败:" + data['message'] + "!");
        }else{
            Feng.success("添加成功!");
            window.parent.QrcodeDiscountInfoDlg.table.refresh();
        }
    });
    ajax.set(this.qrcodeDiscountInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QrcodeDiscountInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/qrcodeDiscount/update", function(data){
        Feng.success("修改成功!");
        window.parent.QrcodeDiscount.table.refresh();
        QrcodeDiscountInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.qrcodeDiscountInfoData);
    ajax.start();
}

$(function() {

});
