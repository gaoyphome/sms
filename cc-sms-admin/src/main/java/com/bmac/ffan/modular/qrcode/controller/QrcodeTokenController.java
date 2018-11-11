package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.qrcode.warpper.QrcodeTokenWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.QrcodeTokenDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeToken;
import com.bmac.ffan.modular.qrcode.service.IQrcodeTokenService;

/**
 * 铏氭嫙鍗″彿控制器
 *
 * @author 工具生成
 * @Date 2017-11-25 17:33:32
 */
@Controller
@RequestMapping("/qrcodeToken")
public class QrcodeTokenController extends BaseController {

    private String PREFIX = "/qrcode/qrcodeToken/";

    @Autowired
    private IQrcodeTokenService qrcodeTokenService;

	@Resource
	QrcodeTokenDao qrcodeTokenDao;
	
    /**
     * 跳转到铏氭嫙鍗″彿首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeToken.html";
    }

    /**
     * 跳转到添加铏氭嫙鍗″彿
     */
    @RequestMapping("/qrcodeToken_add")
    public String qrcodeTokenAdd() {
        return PREFIX + "qrcodeToken_add.html";
    }

    /**
     * 跳转到修改铏氭嫙鍗″彿
     */
    @RequestMapping("/qrcodeToken_update/{qrcodeTokenId}")
    public String qrcodeTokenUpdate(@PathVariable Integer qrcodeTokenId, Model model) {
        CcQrcodeToken qrcodeToken = qrcodeTokenService.selectById(qrcodeTokenId);
        model.addAttribute("qrcodeToken",qrcodeToken);
        LogObjectHolder.me().set(qrcodeToken);
        return PREFIX + "qrcodeToken_edit.html";
    }

    /**
     * 获取铏氭嫙鍗″彿列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String vsirBmacno,
    @RequestParam(required = false) String userId
    
    ) {
        //return qrcodeTokenService.selectList(null);
        
        Page<CcQrcodeToken> page = new PageFactory<CcQrcodeToken>().defaultPage();
		 List<Map<String, Object>> result = qrcodeTokenDao.selectQrcodeTokens(page, 
   		 vsirBmacno,
   		 userId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcQrcodeToken>) new QrcodeTokenWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增铏氭嫙鍗″彿
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeToken qrcodeToken) {
        qrcodeToken.setUpdateTime(DateUtil.getAllMsTime());
        qrcodeToken.setCreateTime(DateUtil.getAllMsTime());
        qrcodeTokenService.insert(qrcodeToken);
        return SUCCESS_TIP;
    }

    /**
     * 删除铏氭嫙鍗″彿
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer qrcodeTokenId) {
        qrcodeTokenService.deleteById(qrcodeTokenId);
        return SUCCESS_TIP;
    }

    /**
     * 修改铏氭嫙鍗″彿
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeToken qrcodeToken) {
        qrcodeToken.setUpdateTime(DateUtil.getAllMsTime());
        qrcodeTokenService.updateById(qrcodeToken);
        return SUCCESS_TIP;
    }

    /**
     * 铏氭嫙鍗″彿详情
     */
    @RequestMapping(value = "/detail/{qrcodeTokenId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeTokenId") Integer qrcodeTokenId) {
        return qrcodeTokenService.selectById(qrcodeTokenId);
    }
}
