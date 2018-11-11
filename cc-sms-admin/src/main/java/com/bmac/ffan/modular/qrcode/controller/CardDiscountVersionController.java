package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.qrcode.warpper.CardDiscountVersionWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.CardDiscountVersionDao;
import com.bmac.ffan.common.persistence.model.CcCardDiscountVersion;
import com.bmac.ffan.modular.qrcode.service.ICardDiscountVersionService;

/**
 * 实体卡版本号控制器
 *
 * @author 工具生成
 * @Date 2017-11-27 13:54:46
 */
@Controller
@RequestMapping("/cardDiscountVersion")
public class CardDiscountVersionController extends BaseController {

    private String PREFIX = "/qrcode/cardDiscountVersion/";

    @Autowired
    private ICardDiscountVersionService cardDiscountVersionService;

	@Resource
	CardDiscountVersionDao cardDiscountVersionDao;
	
    /**
     * 跳转到实体卡版本号首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cardDiscountVersion.html";
    }

    /**
     * 跳转到添加实体卡版本号
     */
    @RequestMapping("/cardDiscountVersion_add")
    public String cardDiscountVersionAdd() {
        return PREFIX + "cardDiscountVersion_add.html";
    }

    /**
     * 跳转到修改实体卡版本号
     */
    @RequestMapping("/cardDiscountVersion_update/{cardDiscountVersionId}")
    public String cardDiscountVersionUpdate(@PathVariable Integer cardDiscountVersionId, Model model) {
        CcCardDiscountVersion cardDiscountVersion = cardDiscountVersionService.selectById(cardDiscountVersionId);
        model.addAttribute("cardDiscountVersion",cardDiscountVersion);
        LogObjectHolder.me().set(cardDiscountVersion);
        return PREFIX + "cardDiscountVersion_edit.html";
    }

    /**
     * 获取实体卡版本号列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String subCompanyId
    
    ) {
        //return cardDiscountVersionService.selectList(null);
        
        Page<CcCardDiscountVersion> page = new PageFactory<CcCardDiscountVersion>().defaultPage();
		 List<Map<String, Object>> result = cardDiscountVersionDao.selectCardDiscountVersions(page, 
   		 subCompanyId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcCardDiscountVersion>) new CardDiscountVersionWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增实体卡版本号
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcCardDiscountVersion cardDiscountVersion) {
        cardDiscountVersion.setUpdateTime(DateUtil.getAllMsTime());
        cardDiscountVersion.setCreateTime(DateUtil.getAllMsTime());
        cardDiscountVersionService.insert(cardDiscountVersion);
        return SUCCESS_TIP;
    }

    /**
     * 删除实体卡版本号
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cardDiscountVersionId) {
        cardDiscountVersionService.deleteById(cardDiscountVersionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改实体卡版本号
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcCardDiscountVersion cardDiscountVersion) {
        cardDiscountVersion.setUpdateTime(DateUtil.getAllMsTime());
        cardDiscountVersionService.updateById(cardDiscountVersion);
        return SUCCESS_TIP;
    }

    /**
     * 实体卡版本号详情
     */
    @RequestMapping(value = "/detail/{cardDiscountVersionId}")
    @ResponseBody
    public Object detail(@PathVariable("cardDiscountVersionId") Integer cardDiscountVersionId) {
        return cardDiscountVersionService.selectById(cardDiscountVersionId);
    }
}
