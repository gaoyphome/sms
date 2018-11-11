/**
 * 初始化运营公司详情对话框
 */
var BusCompanyInfoDlg = {
    busCompanyInfoData : {},
    validateFields: {
        companyId: {
            validators: {
                notEmpty: {
                    message: '公司编码不能为空'
                }
            }
        },
        companyName: {
            validators: {
                notEmpty: {
                    message: '公司名称不能为空'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '公司类型不能为空'
                }
            }
        },
        parentCompanyId: {
            validators: {
                notEmpty: {
                    message: '父公司编号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
BusCompanyInfoDlg.clearData = function() {
    this.busCompanyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusCompanyInfoDlg.set = function(key, val) {
    this.busCompanyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusCompanyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusCompanyInfoDlg.close = function() {
    parent.layer.close(window.parent.BusCompany.layerIndex);
}

/**
 * 收集数据
 */
BusCompanyInfoDlg.collectData = function() {
    this.set('id').set('companyId').set('companyName').set('type').set('parentCompanyId');
}

/**
 * 提交添加
 */
BusCompanyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busCompany/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusCompany.table.refresh();
        BusCompanyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busCompanyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusCompanyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busCompany/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusCompany.table.refresh();
        BusCompanyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busCompanyInfoData);
    ajax.start();
}

/**
 * 验证数据是否为空
 */
BusCompanyInfoDlg.validate = function () {
    $('#busCompanyFrom').data("bootstrapValidator").resetForm();
    $('#busCompanyFrom').bootstrapValidator('validate');
    return $("#busCompanyFrom").data('bootstrapValidator').isValid();
};

$(function() {
	$("#type").val($("#busCompanytype").val());
    Feng.initValidator("busCompanyFrom", BusCompanyInfoDlg.validateFields);
});
