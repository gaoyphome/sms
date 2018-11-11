/**
 * 初始化公交POS固件版本详情对话框
 */
var BusPosVersionInfoDlg = {
    busPosVersionInfoData : {},
    validateFields: {
        posId: {
            validators: {
                regexp:{
                    regexp:/^[0-9A-Fa-f]*$/,
                    message: '只允许输入十六进制串'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '升级方式不能为空'
                }
            }
        },
        upgradeType: {
            validators: {
                notEmpty: {
                    message: '升级类型不能为空'
                }
            }
        },
        downStartTime: {
            validators: {
                notEmpty: {
                    message: '下载起始时间不能为空'
                }
            }
        },
        downEndTime: {
            validators: {
                notEmpty: {
                    message: '下载终止时间不能为空'
                }
            }
        },
        installStartTime: {
            validators: {
                notEmpty: {
                    message: '安装起始时间不能为空'
                }
            }
        },
        installEndTime: {
            validators: {
                notEmpty: {
                    message: '安装终止时间不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
BusPosVersionInfoDlg.clearData = function() {
    this.busPosVersionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusPosVersionInfoDlg.set = function(key, val) {
    this.busPosVersionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusPosVersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusPosVersionInfoDlg.close = function() {
    parent.layer.close(window.parent.BusPosVersion.layerIndex);
}

/**
 * 收集数据
 */
BusPosVersionInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('firmVersion');
	      this.set('filesize');
	      this.set('fileId');
	      this.set('content');
	      this.set('path');
	      this.set('type');
	      this.set('upgradeType');
	      this.set('lineId');
	      this.set('posId');
	      this.set('createTime');
	      this.set('updateTime');

        this.set('downStartTime');
        this.set('downEndTime');
        this.set('installStartTime');
        this.set('installEndTime');
}

/**
 * 提交添加
 */
BusPosVersionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busPosVersion/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusPosVersion.table.refresh();
        BusPosVersionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busPosVersionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusPosVersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busPosVersion/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusPosVersion.table.refresh();
        BusPosVersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busPosVersionInfoData);
    ajax.start();
}


/**
 * 文件上传
 */
//异步文件上传(jquery)
BusPosVersionInfoDlg.importfileSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/busPosVersion/add",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "firmVersion": this.busPosVersionInfoData['firmVersion'],
            "fileId": this.busPosVersionInfoData['fileId'],
            "content": this.busPosVersionInfoData['content'],
            "path": this.busPosVersionInfoData['path'],
            "type": this.busPosVersionInfoData['type'],
            "upgradeType": this.busPosVersionInfoData['upgradeType'],
            "lineId": this.busPosVersionInfoData['lineId'],
            "posId": this.busPosVersionInfoData['posId'],

            "downStartTime": this.busPosVersionInfoData['downStartTime'],
            "downEndTime": this.busPosVersionInfoData['downEndTime'],
            "installStartTime": this.busPosVersionInfoData['installStartTime'],
            "installEndTime": this.busPosVersionInfoData['installEndTime'],
        },
        success: function (data, status) {
            var jdata = $.parseJSON(data.replace(/<.*?>/ig,""));
            if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success("添加成功!");
                window.parent.BusPosVersion.table.refresh();
            }
            BusPosVersionInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data['message'] + "!");
        }
    });
    return false;
}

BusPosVersionInfoDlg.boundSelect = function(){
    $("#lineId").hide();
    $("#posId").hide();
    $('#upgradeType').change(function(){
        var key =$(this).children('option:selected').val();
        if(key == 2){
            $("#lineId").show();
            $("#posId").hide();
        }else if(key == 3){
            $("#lineId").hide();
            $("#posId").show();
        }
    });
}



$(function() {
    $("#type").val($("#typeH").val());
    $("#upgradeType").val($("#upgradeTypeH").val());
    Feng.initValidator("busPosVersionForm", BusPosVersionInfoDlg.validateFields);
    BusPosVersionInfoDlg.boundSelect();
});
