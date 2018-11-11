/**
 * 初始化POS黑名升级管理表详情对话框
 */
var BlacklistVersionInfoDlg = {
    blacklistVersionInfoData : {}
};

/**
 * 清除数据
 */
BlacklistVersionInfoDlg.clearData = function() {
    this.blacklistVersionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlacklistVersionInfoDlg.set = function(key, val) {
    this.blacklistVersionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BlacklistVersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BlacklistVersionInfoDlg.close = function() {
    parent.layer.close(window.parent.BlacklistVersion.layerIndex);
}

/**
 * 收集数据
 */
BlacklistVersionInfoDlg.collectData = function() {
    this.set('filename');
    this.set('listVersion');
    this.set('fileId');
    this.set('content');
    this.set('type');
    this.set('lineId');
    this.set('posId');
    if('1:/W1' == this.get('filename')){
        this.set('listVersion', this.get('listVersionWrite'));
    }else if('1:/G1' == this.get('filename')){
        this.set('listVersion', this.get('listVersionBlack'));
    }else if('1:/G6' == this.get('filename')){
        this.set('listVersion', this.get('listVersionG6'));
    }else{
        alert("其他文件类型暂时不支持");
        throw err = new Error( '其他文件类型暂时不支持' );
    }
}

/**
 * 提交添加
 */
BlacklistVersionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blacklistVersion/add", function(data){
        if(data['code'] != 200){
            Feng.error("操作失败:" + data['message'] + "!");
        }else{
            Feng.success("添加成功!");
            window.parent.BlacklistVersion.table.refresh();
        }
        BlacklistVersionInfoDlg.close();
        /*
        Feng.success("添加成功!");
        window.parent.BlacklistVersion.table.refresh();
        BlacklistVersionInfoDlg.close();*/
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blacklistVersionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BlacklistVersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/blacklistVersion/update", function(data){
        Feng.success("修改成功!");
        window.parent.BlacklistVersion.table.refresh();
        BlacklistVersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.blacklistVersionInfoData);
    ajax.start();
}

BlacklistVersionInfoDlg.selectbound = function () {
    $('#filename').change(function () {
        if("1:/W1"==$(this).val()){
            $('#listVersionBlackDiv').attr("style","display:none;");
            $('#listVersionG6Div').attr("style","display:none;");
            $('#listVersionWriteDiv').attr("style","");
        }else if("1:/G1"==$(this).val()){
            $('#listVersionG6Div').attr("style","display:none;");
            $('#listVersionWriteDiv').attr("style","display:none;");
            $('#listVersionBlackDiv').attr("style","");
        }else if("1:/G6"==$(this).val()){
            $('#listVersionWriteDiv').attr("style","display:none;");
            $('#listVersionBlackDiv').attr("style","display:none;");
            $('#listVersionG6Div').attr("style","");
        }
    });

    $('#type').change(function () {
        if(0==$(this).val()){
            $('#lineIdDiv').attr("style","display:none;");
            $('#posIdDiv').attr("style","display:none;");
        }else if(1==$(this).val()){
            $('#posIdDiv').attr("style","display:none;");
            $('#lineIdDiv').attr("style","");
        }else if(2==$(this).val()){
            $('#lineIdDiv').attr("style","display:none;");
            $('#posIdDiv').attr("style","");
        }
    });
}

$(function() {
    BlacklistVersionInfoDlg.selectbound();
    $('#listVersionBlackDiv').attr("style","display:none;");
});
