/**
 * 初始化公交POS文件详情对话框
 */
var CityCardFileInfoDlg = {
    cityCardFileInfoData : {}
};

/**
 * 清除数据
 */
CityCardFileInfoDlg.clearData = function() {
    this.cityCardFileInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CityCardFileInfoDlg.set = function(key, val) {
    this.cityCardFileInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CityCardFileInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CityCardFileInfoDlg.close = function() {
    parent.layer.close(window.parent.CityCardFile.layerIndex);
}

/**
 * 收集数据
 */
CityCardFileInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('filename');
	      this.set('filesize');
	      this.set('crc16');
	      this.set('content');
	      this.set('version');
	      this.set('lineId');
	      this.set('subCompanyId');
}

/**
 * 提交添加
 */
CityCardFileInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cityCardFile/add", function(data){
        Feng.success("添加成功!");
        window.parent.CityCardFile.table.refresh();
        CityCardFileInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cityCardFileInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CityCardFileInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cityCardFile/update", function(data){
        Feng.success("修改成功!");
        window.parent.CityCardFile.table.refresh();
        CityCardFileInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.cityCardFileInfoData);
    ajax.start();
}

/**
* 文件上传
*/
//异步文件上传(jquery)
CityCardFileInfoDlg.uploadSubmit = function () {

    this.clearData();
    this.collectData();

    $.ajaxFileUpload({
        url: Feng.ctxPath + "/cityCardFile/add",   //submit to UploadFileServlet
        secureuri: false,
        fileElementId: 'filecontent',
        dataType: 'JSON', //or json xml whatever you like~
        data: {//加入的文本参数
            "filename": this.cityCardFileInfoData['filename'],
            "version": this.cityCardFileInfoData['version'],
            "lineId": this.cityCardFileInfoData['lineId'],
            "subCompanyId": this.cityCardFileInfoData['subCompanyId']
        },
        success: function (data, status) {
            Feng.success("添加成功!");
            window.parent.CityCardFile.table.refresh();
            CityCardFileInfoDlg.close();
        },
        error: function (data, status, e) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        }
    });
    return false;
}

$(function() {

});
