/**
 * 初始化实体互通卡折扣设置 详情对话框
 */
var ExchangeCardDiscountInfoDlg = {
    exchangeCardDiscountInfoData : {}
};

/**
 * 清除数据
 */
ExchangeCardDiscountInfoDlg.clearData = function() {
    this.exchangeCardDiscountInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExchangeCardDiscountInfoDlg.set = function(key, val) {
    this.exchangeCardDiscountInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ExchangeCardDiscountInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ExchangeCardDiscountInfoDlg.close = function() {
    parent.layer.close(window.parent.ExchangeCardDiscount.layerIndex);
}

/**
 * 收集数据
 */
ExchangeCardDiscountInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('subCompanyId');
	      this.set('iin');
	      this.set('cardType');
	      this.set('chargeMode');
	      this.set('creditBalance');
	      this.set('maxConsume');
	      this.set('maxBalance');
	      this.set('minBalance');
	      this.set('inRate');
	      this.set('inLimit');
	      this.set('outRate');
	      this.set('outLimit');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
ExchangeCardDiscountInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/exchangeCardDiscount/add", function(data){
        Feng.success("添加成功!");
        window.parent.ExchangeCardDiscount.table.refresh();
        ExchangeCardDiscountInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.exchangeCardDiscountInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ExchangeCardDiscountInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/exchangeCardDiscount/update", function(data){
        Feng.success("修改成功!");
        window.parent.ExchangeCardDiscount.table.refresh();
        ExchangeCardDiscountInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.exchangeCardDiscountInfoData);
    ajax.start();
}


/**
 * 文件上传
 */
//异步文件上传(jquery)
ExchangeCardDiscountInfoDlg.importfileSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/exchangeCardDiscount/importfile",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "subCompanyId": this.exchangeCardDiscountInfoData['subCompanyId']
        },
        success: function (data, status) {
            var jdata = $.parseJSON(data.replace(/<.*?>/ig,""));
            if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success(jdata['message'] + "!");
                window.parent.ExchangeCardDiscount.table.refresh();
            }
            ExchangeCardDiscountInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        }
    });
    return false;
}

$(function() {

});
