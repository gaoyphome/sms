package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.qrcode.warpper.QrcodeTokenInfoWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmac.ffan.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.qrcode.dao.QrcodeTokenInfoDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeTokenInfo;
import com.bmac.ffan.modular.qrcode.service.IQrcodeTokenInfoService;

/**
 * 浜岀淮鐮乀oken控制器
 *
 * @author 工具生成
 * @Date 2017-11-25 17:55:29
 */
@Controller
@RequestMapping("/qrcodeTokenInfo")
public class QrcodeTokenInfoController extends BaseController {

    private String PREFIX = "/qrcode/qrcodeTokenInfo/";

    @Autowired
    private IQrcodeTokenInfoService qrcodeTokenInfoService;

	@Resource
	QrcodeTokenInfoDao qrcodeTokenInfoDao;
	
    /**
     * 跳转到浜岀淮鐮乀oken首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeTokenInfo.html";
    }

    /**
     * 跳转到添加浜岀淮鐮乀oken
     */
    @RequestMapping("/qrcodeTokenInfo_add")
    public String qrcodeTokenInfoAdd() {
        return PREFIX + "qrcodeTokenInfo_add.html";
    }

    /**
     * 跳转到修改浜岀淮鐮乀oken
     */
    @RequestMapping("/qrcodeTokenInfo_update/{qrcodeTokenInfoId}")
    public String qrcodeTokenInfoUpdate(@PathVariable Integer qrcodeTokenInfoId, Model model) {
        CcQrcodeTokenInfo qrcodeTokenInfo = qrcodeTokenInfoService.selectById(qrcodeTokenInfoId);
        model.addAttribute("qrcodeTokenInfo",qrcodeTokenInfo);
        LogObjectHolder.me().set(qrcodeTokenInfo);
        return PREFIX + "qrcodeTokenInfo_edit.html";
    }
    /**
     * 跳转到修改浜岀淮鐮乀oken
     */
    @RequestMapping("/qrcodeTokenInfo_detail/{qrcodeTokenInfoId}")
    public String qrcodeTokenInfoDetail(@PathVariable Integer qrcodeTokenInfoId, Model model) {
        CcQrcodeTokenInfo qrcodeTokenInfo = qrcodeTokenInfoService.selectById(qrcodeTokenInfoId);
        model.addAttribute("qrcodeTokenInfo",qrcodeTokenInfo);
        LogObjectHolder.me().set(qrcodeTokenInfo);
        return PREFIX + "qrcodeTokenInfo_detail.html";
    }
    /**
     * 获取浜岀淮鐮乀oken列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String vsirBmacno,
    @RequestParam(required = false) String userPhone
    
    ) {
        //return qrcodeTokenInfoService.selectList(null);
        
        Page<CcQrcodeTokenInfo> page = new PageFactory<CcQrcodeTokenInfo>().defaultPage();
		 List<Map<String, Object>> result = qrcodeTokenInfoDao.selectQrcodeTokenInfos(page, 
   		 vsirBmacno,
         userPhone,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcQrcodeTokenInfo>) new QrcodeTokenInfoWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增浜岀淮鐮乀oken
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeTokenInfo qrcodeTokenInfo) {
        qrcodeTokenInfoService.insert(qrcodeTokenInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除浜岀淮鐮乀oken
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer qrcodeTokenInfoId) {
        qrcodeTokenInfoService.deleteById(qrcodeTokenInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改浜岀淮鐮乀oken
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeTokenInfo qrcodeTokenInfo) {
        qrcodeTokenInfoService.updateById(qrcodeTokenInfo);
        return SUCCESS_TIP;
    }

    /**
     * 浜岀淮鐮乀oken详情
     */
    @RequestMapping(value = "/detail/{qrcodeTokenInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeTokenInfoId") Integer qrcodeTokenInfoId) {
        return qrcodeTokenInfoService.selectById(qrcodeTokenInfoId);
    }
}
