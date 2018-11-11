/**
 * 初始化票价信息管理详情对话框
 */
var PriceInfoInfoDlg = {
    priceInfoInfoData : {},
    validateFields: {
        lineId: {
            validators: {
                notEmpty: {
                    message: '线路编号不能为空'
                }
            }
        },
        num: {
            validators: {
                notEmpty: {
                    message: '站点顺序号不能为空'
                }
            }
        },
        price: {
            validators: {
                notEmpty: {
                    message: '票价不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
PriceInfoInfoDlg.clearData = function() {
    this.priceInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PriceInfoInfoDlg.set = function(key, val) {
    this.priceInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PriceInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PriceInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.PriceInfo.layerIndex);
}

/**
 * 收集数据
 */
PriceInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('lineId');
	      this.set('dirFlag');
	      this.set('num');
	      this.set('price');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
PriceInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/priceInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.PriceInfo.table.refresh();
        PriceInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.priceInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PriceInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/priceInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.PriceInfo.table.refresh();
        PriceInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.priceInfoInfoData);
    ajax.start();
}
/**
 * 验证数据是否为空
 */
PriceInfoInfoDlg.validate = function () {
    $('#priceInfoForm').data("bootstrapValidator").resetForm();
    $('#priceInfoForm').bootstrapValidator('validate');
    return $("#priceInfoForm").data('bootstrapValidator').isValid();
};
$(function() {
    Feng.initValidator("priceInfoForm", PriceInfoInfoDlg.validateFields);
});
