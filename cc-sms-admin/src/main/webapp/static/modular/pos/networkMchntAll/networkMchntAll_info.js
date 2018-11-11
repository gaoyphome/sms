/**
 * 初始化鍟嗘埛淇℃伅详情对话框
 */
var NetworkMchntAllInfoDlg = {
    networkMchntAllInfoData : {}
};

/**
 * 清除数据
 */
NetworkMchntAllInfoDlg.clearData = function() {
    this.networkMchntAllInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkMchntAllInfoDlg.set = function(key, val) {
    this.networkMchntAllInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NetworkMchntAllInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NetworkMchntAllInfoDlg.close = function() {
    parent.layer.close(window.parent.NetworkMchntAll.layerIndex);
}

/**
 * 收集数据
 */
NetworkMchntAllInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('mchntId');
	      this.set('mchntname');
	      this.set('mchntcode');
	      this.set('mchntType');
	      this.set('fatherId');
	      this.set('lowSeason');
	      this.set('lowPrice');
	      this.set('midSeason');
	      this.set('midPrice');
	      this.set('telphone');
	      this.set('email');
	      this.set('contacts');
	      this.set('contactsMobile');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
NetworkMchntAllInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/networkMchntAll/add", function(data){
        Feng.success("添加成功!");
        window.parent.NetworkMchntAll.table.refresh();
        NetworkMchntAllInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.networkMchntAllInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
NetworkMchntAllInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/networkMchntAll/update", function(data){
        Feng.success("修改成功!");
        window.parent.NetworkMchntAll.table.refresh();
        NetworkMchntAllInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.networkMchntAllInfoData);
    ajax.start();
}

$(function() {
    $("#mchntType").val($("#mchntTypeHidden").val());
});
