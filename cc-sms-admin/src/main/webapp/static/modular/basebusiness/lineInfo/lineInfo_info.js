/**
 * 初始化线路信息管理详情对话框
 */
var LineInfoInfoDlg = {
    lineInfoInfoData : {},
    layerIndex: -1,
    importResponse: -1
};

/**
 * 清除数据
 */
LineInfoInfoDlg.clearData = function() {
    this.lineInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LineInfoInfoDlg.set = function(key, val) {
    this.lineInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LineInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LineInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.LineInfo.layerIndex);
}

LineInfoInfoDlg.closeSubmit = function() {
    parent.layer.close(window.parent.layerIndex);
}

/**
 * 收集数据
 */
LineInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('lineId');
	      this.set('fileId');
	      this.set('name');
	      this.set('companyId');
	      this.set('subCompanyId');
	      this.set('dateEnable');
	      this.set('localNoMode');
	      this.set('offsiteNoModel');
	      this.set('busAttr');
	      this.set('offsiteTicketModel');
	      this.set('difTicketRule');
	      this.set('ticketDiscountFlag');
	      this.set('functionSwitch');
	      this.set('cardIssuerNum');
	      this.set('lineAttr');
	      this.set('exchangeTime');
	      this.set('priceBase');
	      this.set('cityLeaveUp');
	      this.set('cityLeaveDown');
	      this.set('stationsNumUp');
	      this.set('stationsNumDn');
	      this.set('fileVersion');
	      this.set('pltLineVersion');
	      this.set('createFlag');
	      this.set('createTime');
	      this.set('updateTime');
	      this.set('lineType');
}

/**
 * 提交添加
 */
LineInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lineInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.LineInfo.table.refresh();
        LineInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lineInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LineInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lineInfo/update", function(data){
        if(data['code'] != 200){
            Feng.error("修改失败:" + data['message'] + "!");
        }else{
            Feng.success("修改成功!");
            window.parent.LineInfo.table.refresh();
        }
        LineInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lineInfoInfoData);
    ajax.start();
}

/**
 * 确认提交
 */
LineInfoInfoDlg.confirmSubmit = function () {
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lineInfo/confirm", function(data){
        if(data['code'] != 200){
            Feng.error("修改失败:" + data['message'] + "!");
        }else{
            Feng.success("修改成功!");
            window.parent.LineInfo.table.refresh();
        }
        LineInfoInfoDlg.closeSubmit();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lineInfoInfoData);
    ajax.start();
}

var param = {};

/**
 * 文件上传
 */
//异步文件上传(jquery)
LineInfoInfoDlg.importfileSubmitbak1 = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/lineInfo/importfile",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "filename": this.lineInfoInfoData['filename'],
            "lineType": this.lineInfoInfoData['lineType']
        },
        success: function (data, status) {
            var jdata = $.parseJSON(data.replace(/<.*?>/ig,""));
            if(jdata['code'] == 999){
                var index = parent.layer.open({
                    type: 2,
                    title: '添加线路信息管理',
                    area: ['800px', '420px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/lineInfo/lineInfo_confirm'
                });
                try{
                    param.tmp = index;
                    this.layerIndex = index;
                }catch (e){
                    alert(e);
                }

            }else if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success("添加成功!");
                window.parent.LineInfo.table.refresh();
            }
            LineInfoInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data['message'] + "!");
        }
    });
    return false;
}

/**
 * 文件上传
 */
//异步文件上传(jquery)
LineInfoInfoDlg.importfileSubmitBak = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/lineInfo/importfile",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "filename": this.lineInfoInfoData['filename'],
            "lineType": this.lineInfoInfoData['lineType']
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
                    window.parent.LineInfo.table.refresh();
                }
                LineInfoInfoDlg.close();

            // }
            // formatdata(data);
                /*Feng.success("添加成功!");
                window.parent.LineInfo.table.refresh();
                LineInfoInfoDlg.close();*/
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data['message'] + "!");
        }
    });
    return false;
}



LineInfoInfoDlg.importfileSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        async: false, // 设置同步，必定返回
        url: Feng.ctxPath + "/lineInfo/importfile",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "filename": this.lineInfoInfoData['filename'],
            "lineType": this.lineInfoInfoData['lineType']
        },
        success: function (data, status) {
            var jdata = $.parseJSON(data.replace(/<.*?>/ig,""));
            if(jdata['code'] == 999){
                LineInfoInfoDlg.importResponse = 1;
                LineInfoInfoDlg.openConfirm();
            }else if(jdata['code'] != 200){
                Feng.error("操作失败:" + jdata['message'] + "!");
            }else{
                Feng.success("添加成功!");
                window.parent.LineInfo.table.refresh();
            }
            LineInfoInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data['message'] + "!");
        }
    });
    /*if(LineInfoInfoDlg.importResponse = -1) {
        var index = parent.layer.open({
            type: 2,
            title: '添加线路信息管理',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/lineInfo/lineInfo_confirm'
        });
        window.parent.layerIndex = index;
    }*/
    return false;
}


LineInfoInfoDlg.openConfirm = function() {
    var index = parent.layer.open({
        type: 2,
        title: '添加线路信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lineInfo/lineInfo_confirm'
    });
    window.parent.layerIndex = index;
}

LineInfoInfoDlg.swithPrice = function(flag){
    if(flag){
        $("#prices-down").hide(1000);
        $("#prices-up").show(1000);
        $("#switchfalg").children("li").eq(0).addClass("active");
        $("#switchfalg").children("li").eq(1).removeClass("active");
    }else{
        $("#prices-up").hide(1000);
        $("#prices-down").show(1000);
        $("#switchfalg").children("li").eq(0).removeClass("active");
        $("#switchfalg").children("li").eq(1).addClass("active");
    }
}


$(function() {
    $("#localNoMode").val($("#localNoModeHidden").val());
    $("#offsiteNoModel").val($("#offsiteNoModelHidden").val());
    $("#busAttr").val($("#busAttrHidden").val());
    $("#offsiteTicketModel").val($("#offsiteTicketModelHidden").val());
    $("#difTicketRule").val($("#difTicketRuleHidden").val());
});
