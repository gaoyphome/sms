/**
 * 初始化站点信息管理详情对话框
 */
var StationInfoInfoDlg = {
    stationInfoInfoData : {}
};

/**
 * 清除数据
 */
StationInfoInfoDlg.clearData = function() {
    this.stationInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationInfoInfoDlg.set = function(key, val) {
    this.stationInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StationInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StationInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.StationInfo.layerIndex);
}

/**
 * 收集数据
 */
StationInfoInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('lineId');
	      this.set('name');
	      this.set('flag');
	      this.set('num');
	      this.set('longitude');
	      this.set('latitude');
	      this.set('operaStationNum');
	      this.set('icStationNum');
	      this.set('icSubStationNum');
	      this.set('createTime');
	      this.set('updateTime');
}

/**
 * 提交添加
 */
StationInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stationInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.StationInfo.table.refresh();
        StationInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stationInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StationInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/stationInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.StationInfo.table.refresh();
        StationInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.stationInfoInfoData);
    ajax.start();
}

$(function() {

});
