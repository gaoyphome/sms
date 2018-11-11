/**
 * 初始化濂楃エ浜岀淮鐮佷俊鎭�详情对话框
 */
var TokenInfoInfoDlg = {
    tokenInfoInfoData : {}
};

/**
 * 清除数据
 */
TokenInfoInfoDlg.clearData = function() {
    this.tokenInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TokenInfoInfoDlg.set = function(key, val) {
    this.tokenInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TokenInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TokenInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.TokenInfo.layerIndex);
}

/**
 * 收集数据
 */
TokenInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('tokenId');
	      this.set('userId');
	      this.set('tokenType');
	      this.set('startTime');
	      this.set('endTime');
	      this.set('space');
	      this.set('tokenLevel');
	      this.set('ticketId');
	      this.set('ntOrderid');
	      this.set('ticketType');
	      this.set('tokenInfo');
	      this.set('tokenEnc');
	      this.set('state');
	      this.set('useTime');
	      this.set('posId');
	      this.set('expireType');
	      this.set('expireTime');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
TokenInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tokenInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.TokenInfo.table.refresh();
        TokenInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tokenInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TokenInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tokenInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.TokenInfo.table.refresh();
        TokenInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tokenInfoInfoData);
    ajax.start();
}

$(function() {

});
