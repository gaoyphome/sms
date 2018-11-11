/**
 * 初始化里程信息管理详情对话框
 */
var MileageInfoInfoDlg = {
    mileageInfoInfoData : {},
    validateFields: {
        lineId: {
            validators: {
                notEmpty: {
                    message: '线路编号不能为空'
                }
            }
        },
        dirFlag: {
            validators: {
                notEmpty: {
                    message: '上/下行不能为空'
                }
            }
        },
        mileageValue: {
            validators: {
                notEmpty: {
                    message: '里程不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
MileageInfoInfoDlg.clearData = function() {
    this.mileageInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MileageInfoInfoDlg.set = function(key, val) {
    this.mileageInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MileageInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MileageInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.MileageInfo.layerIndex);
}

/**
 * 收集数据
 */
MileageInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('lineId');
	      this.set('dirFlag');
	      this.set('mileageValue');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
MileageInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mileageInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.MileageInfo.table.refresh();
        MileageInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mileageInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MileageInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mileageInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.MileageInfo.table.refresh();
        MileageInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mileageInfoInfoData);
    ajax.start();
}
/**
 * 验证数据是否为空
 */
MileageInfoInfoDlg.validate = function () {
    $('#mileageInfoForm').data("bootstrapValidator").resetForm();
    $('#mileageInfoForm').bootstrapValidator('validate');
    return $("#mileageInfoForm").data('bootstrapValidator').isValid();
};

$(function() {
    Feng.initValidator("mileageInfoForm", MileageInfoInfoDlg.validateFields);
});
