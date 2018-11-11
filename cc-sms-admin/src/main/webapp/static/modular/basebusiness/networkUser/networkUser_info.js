/**
 * 初始化用户信息详情对话框
 */
var NetworkUserInfoDlg = {
    networkUserInfoData : {},
    validateFields: {
        userId: {
            validators: {
                notEmpty: {
                    message: '用户ID不能为空'
                }
            }
        }
    }

};

/**
 * 清除数据
 */
NetworkUserInfoDlg.clearData = function() {
    this.networkUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkUserInfoDlg.set = function(key, val) {
    this.networkUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NetworkUserInfoDlg.close = function() {
    parent.layer.close(window.parent.NetworkUser.layerIndex);
}

/**
 * 收集数据
 */
NetworkUserInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('userId');
	      this.set('username');
	      this.set('password');
	      this.set('userType');
	      this.set('nickname');
	      this.set('sex');
	      this.set('birthday');
	      this.set('mobile');
	      this.set('email');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
NetworkUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/networkUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.NetworkUser.table.refresh();
        NetworkUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.networkUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NetworkUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/networkUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.NetworkUser.table.refresh();
        NetworkUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.networkUserInfoData);
    ajax.start();
}
/**
 * 验证数据是否为空
 */
NetworkUserInfoDlg.validate = function () {
    $('#networkUserInfoForm').data("bootstrapValidator").resetForm();
    $('#networkUserInfoForm').bootstrapValidator('validate');
    return $("#networkUserInfoForm").data('bootstrapValidator').isValid();
};
$(function() {

    Feng.initValidator("networkUserInfoForm", NetworkUserInfoDlg.validateFields);
});
