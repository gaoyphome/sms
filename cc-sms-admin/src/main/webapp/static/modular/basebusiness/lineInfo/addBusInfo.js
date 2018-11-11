
/**
 * 初始化车辆信息管理详情对话框
 */
var BusInfoInfoDlg = {
    busInfoInfoData : {},
    validateFields: {
        busId: {
            validators: {
                notEmpty: {
                    message: '车辆编号不能为空'
                },
                regexp:{
                    regexp: /^[0-9A-Fa-f]*$/,
                    message: '只允许输入数字'
                }
            }
        },
        plateNumber: {
            validators: {
                notEmpty: {
                    message: '车牌号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
BusInfoInfoDlg.clearData = function() {
    this.busInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusInfoInfoDlg.set = function(key, val) {
    this.busInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.BusInfo.layerIndex);
}

/**
 * 收集数据
 */
BusInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('lineId');
	      this.set('busId');
	      this.set('plateNumber');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
BusInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busInfo/add", function(data){
        Feng.success("添加成功!");
        BusInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busInfoInfoData);
    ajax.start();
    parent.layer.close(window.parent.LineInfo.layerIndex);
}
/**
 * 验证数据是否为空
 */
BusInfoInfoDlg.validate = function () {
    $('#lineInfoAddBusForm').data("bootstrapValidator").resetForm();
    $('#lineInfoAddBusForm').bootstrapValidator('validate');
    return $("#lineInfoAddBusForm").data('bootstrapValidator').isValid();
};
$(function() {

    Feng.initValidator("lineInfoAddBusForm", BusInfoInfoDlg.validateFields);
});
