/**
 * 初始化濂楃エ璇︽儏详情对话框
 */
var TicketDetailInfoDlg = {
    ticketDetailInfoData : {}
};

/**
 * 清除数据
 */
TicketDetailInfoDlg.clearData = function() {
    this.ticketDetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketDetailInfoDlg.set = function(key, val) {
    this.ticketDetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketDetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TicketDetailInfoDlg.close = function() {
    parent.layer.close(window.parent.TicketDetail.layerIndex);
}

/**
 * 收集数据
 */
TicketDetailInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('ticketId');
	      this.set('ntOrderid');
	      this.set('agentId');
	      this.set('mchntId');
	      this.set('spotId');
	      this.set('termId');
	      this.set('sesq');
	      this.set('totalCnt');
	      this.set('useCnt');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
TicketDetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketDetail/add", function(data){
        Feng.success("添加成功!");
        window.parent.TicketDetail.table.refresh();
        TicketDetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketDetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TicketDetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketDetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.TicketDetail.table.refresh();
        TicketDetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketDetailInfoData);
    ajax.start();
}

$(function() {

});
