/**
 * 初始化濂楃エ淇℃伅详情对话框
 */
var TicketInfoInfoDlg = {
    ticketInfoInfoData : {}
};

/**
 * 清除数据
 */
TicketInfoInfoDlg.clearData = function() {
    this.ticketInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketInfoInfoDlg.set = function(key, val) {
    this.ticketInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TicketInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.TicketInfo.layerIndex);
}

/**
 * 收集数据
 */
TicketInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('ticketId');
	      this.set('userId');
	      this.set('ticketName');
	      this.set('ticketType');
	      this.set('ticketDesc');
	      this.set('cardno');
	      this.set('ntOrderid');
	      this.set('saleDate');
	      this.set('expireType');
	      this.set('startDate');
	      this.set('endDate');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
TicketInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.TicketInfo.table.refresh();
        TicketInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TicketInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.TicketInfo.table.refresh();
        TicketInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketInfoInfoData);
    ajax.start();
}

$(function() {

});
