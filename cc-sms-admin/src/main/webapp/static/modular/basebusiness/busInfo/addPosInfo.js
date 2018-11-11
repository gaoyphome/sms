/**
 * 初始化公交POS信息详情对话框
 */
var BusPosInfoDlg = {
    busPosInfoData : {},
    validateFields: {
        posId :{
            validators: {
                notEmpty: {
                    message: 'POS编号不能为空'
                },
				regexp:{
                	regexp: /^[0-9]*$/,
					message: '只允许输入数字'
				}
            }
		},
        posIdHex :{
            validators: {
                notEmpty: {
                    message: 'POS编号不能为空'
                },
				regexp:{
					regexp:/^[0-9A-Fa-f]*$/,
					message: '只允许输入十六进制串'
				}
			}
        }
	}
};

/**
 * 清除数据
 */
BusPosInfoDlg.clearData = function() {
    this.busPosInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusPosInfoDlg.set = function(key, val) {
    this.busPosInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusPosInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusPosInfoDlg.close = function() {
    parent.layer.close(window.parent.BusPos.layerIndex);
}

/**
 * 收集数据
 */
BusPosInfoDlg.collectData = function() {
	      this.set('id');
	      this.set('posVendor');
	      this.set('busId');
	      this.set('posId');
	      this.set('samId');
	      this.set('state');
	      this.set('firmVersion');
	      this.set('reason');
	      this.set('remark');
	      this.set('createTime');
	      this.set('updateTime');
	      this.set('heartbeatTime');
	      this.set('lineVersion');
	      this.set('incrBlackVer');
	      this.set('qrBlackVer');
	      this.set('incQrBlackVer');
	      this.set('htBlackVer');
	      this.set('htIncBlackVer');
	      this.set('priVer');
	      this.set('milesVer');
	      this.set('htWhiteVer');
	      this.set('htTwoVer');
}

/**
 * 提交添加
 */
BusPosInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busPos/add", function(data){
    	    if(data['code'] != 200){
    	        Feng.error("操作失败:" + data['message'] + "!");
    	    }else{
    	        Feng.success("添加成功!");
    	        window.parent.BusInfo.table.refresh();
    	    }
        BusPosInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busPosInfoData);
    ajax.start();
    parent.layer.close(window.parent.BusInfo.layerIndex);
}

$(function() {
    Feng.initValidator("busInfoAddPosForm", BusPosInfoDlg.validateFields);
});
