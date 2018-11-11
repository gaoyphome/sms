/**
 * 初始化实体本地卡折扣设置 详情对话框
 */
var LocalCardDiscountInfoDlg = {
    localCardDiscountInfoData : {}
};

/**
 * 清除数据
 */
LocalCardDiscountInfoDlg.clearData = function() {
    this.localCardDiscountInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LocalCardDiscountInfoDlg.set = function(key, val) {
    this.localCardDiscountInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LocalCardDiscountInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LocalCardDiscountInfoDlg.close = function() {
    parent.layer.close(window.parent.LocalCardDiscount.layerIndex);
}

/**
 * 收集数据
 */
LocalCardDiscountInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('subCompanyId');
	      this.set('cardPhyType');
	      this.set('cardType');
	      this.set('cardAttr');
	      this.set('chargeMode');
	      this.set('maxConsume');
	      this.set('creditBalance');
	      this.set('minBalance');
	      this.set('maxBalance');
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
LocalCardDiscountInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/localCardDiscount/add", function(data){
        Feng.success("添加成功!");
        window.parent.LocalCardDiscount.table.refresh();
        LocalCardDiscountInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.localCardDiscountInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LocalCardDiscountInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/localCardDiscount/update", function(data){
        Feng.success("修改成功!");
        window.parent.LocalCardDiscount.table.refresh();
        LocalCardDiscountInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.localCardDiscountInfoData);
    ajax.start();
}


/**
 * 文件上传
 */
//异步文件上传(jquery)
LocalCardDiscountInfoDlg.importfileSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/localCardDiscount/importfile",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "subCompanyId": this.localCardDiscountInfoData['subCompanyId']
        },
        success: function (data, status) {
            var jdata = $.parseJSON(data.replace(/<.*?>/ig,""));
            if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success( jdata['message'] + "!");
                window.parent.LocalCardDiscount.table.refresh();
            }
            LocalCardDiscountInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        }
    });
    return false;
}

$(function() {

});
