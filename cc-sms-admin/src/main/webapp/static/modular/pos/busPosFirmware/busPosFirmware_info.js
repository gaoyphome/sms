/**
 * 初始化公交POS固件信息详情对话框
 */
var BusPosFirmwareInfoDlg = {
    busPosFirmwareInfoData : {},
    validateFields: {
        firmVersion: {
            validators: {
                notEmpty: {
                    message: '版本号不能为空'
                },
                regexp:{
                    regexp:/^[0-9A-Fa-f]{4}$/,
                    message: '只允许输入十六进制串,且长度必须为4'
                }
            }
        },
        filecontent: {
            validators: {
                notEmpty: {
                    message: '版本号不能为空'
                }
            }
        }
    }
};

/**
 * 验证数据是否为空
 */
BusPosFirmwareInfoDlg.validate = function () {
    $('#busPosFirmwareFrom').data("bootstrapValidator").resetForm();
    $('#busPosFirmwareFrom').bootstrapValidator('validate');
    return $("#busPosFirmwareFrom").data('bootstrapValidator').isValid();
};
/**
 * 清除数据
 */
BusPosFirmwareInfoDlg.clearData = function() {
    this.busPosFirmwareInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusPosFirmwareInfoDlg.set = function(key, val) {
    this.busPosFirmwareInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusPosFirmwareInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusPosFirmwareInfoDlg.close = function() {
    parent.layer.close(window.parent.BusPosFirmware.layerIndex);
}

/**
 * 收集数据
 */
BusPosFirmwareInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('firmVersion');
          this.set('contentType');
          this.set('posVendor');
	      this.set('content');
}

/**
 * 提交添加
 */
BusPosFirmwareInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busPosFirmware/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusPosFirmware.table.refresh();
        BusPosFirmwareInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busPosFirmwareInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusPosFirmwareInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busPosFirmware/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusPosFirmware.table.refresh();
        BusPosFirmwareInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busPosFirmwareInfoData);
    ajax.start();
}

/**
 * 文件上传
 */
//异步文件上传(jquery)
BusPosFirmwareInfoDlg.uploadSubmit = function () {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    $.ajaxFileUpload({
        url: Feng.ctxPath + "/busPosFirmware/add",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "contentType": this.busPosFirmwareInfoData['contentType'],
            "firmVersion": this.busPosFirmwareInfoData['firmVersion'],
            "posVendor": this.busPosFirmwareInfoData['posVendor'],

        },
        success: function (data, status) {
            // var formatdata = function (data) {

            var jdata = $.parseJSON(data.replace(/<.*?>/ig,""));
            //data = data.substring(5, data.length-6);
            //var jdata = JSON.parse(data);
            if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success("添加成功!");
                window.parent.BusPosFirmware.table.refresh();
            }
            BusPosFirmwareInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data['message'] + "!");
        }
    });
    return false;
}

$(function() {
    Feng.initValidator("busPosFirmwareFrom", BusPosFirmwareInfoDlg.validateFields);
});
