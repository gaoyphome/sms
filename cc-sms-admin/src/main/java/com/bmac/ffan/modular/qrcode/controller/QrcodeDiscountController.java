package com.bmac.ffan.modular.qrcode.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.exception.BussinessException;
import com.bmac.ffan.common.persistence.model.CcBusCompany;
import com.bmac.ffan.common.persistence.model.Dict;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.modular.basebusiness.service.IBusCompanyService;
import com.bmac.ffan.modular.qrcode.warpper.QrcodeDiscountWarpper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.qrcode.dao.QrcodeDiscountDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeDiscount;
import com.bmac.ffan.modular.qrcode.service.IQrcodeDiscountService;

/**
 * 二维码折扣控制器
 *
 * @author 工具生成
 * @Date 2017-11-24 17:26:30
 */
@Controller
@RequestMapping("/qrcodeDiscount")
public class QrcodeDiscountController extends BaseController {

    private String PREFIX = "/qrcode/qrcodeDiscount/";

    @Autowired
    private IQrcodeDiscountService qrcodeDiscountService;

	@Resource
	QrcodeDiscountDao qrcodeDiscountDao;

	@Autowired
    private IBusCompanyService busCompanyService;
	
    /**
     * 跳转到二维码折扣首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeDiscount.html";
    }

    /**
     * 跳转到添加二维码折扣
     */
    @RequestMapping("/qrcodeDiscount_add")
    public String qrcodeDiscountAdd(Model model) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("type","1");//运营公司类型为子公司
        List<CcBusCompany> busCompanies = busCompanyService.selectList(wrapper);
        model.addAttribute("busCompanies",busCompanies);
        return PREFIX + "qrcodeDiscount_add.html";
    }

    /**
     * 跳转到修改二维码折扣
     */
    @RequestMapping("/qrcodeDiscount_update/{subCompanyId}")
    public String qrcodeDiscountUpdate(@PathVariable String subCompanyId, Model model) {

        List<Map<String, Object>> result = qrcodeDiscountDao.selectQrcodeDiscountsById(subCompanyId);
        Map<String, Object> map = result.get(0);
        CcQrcodeDiscount qrcodeDiscount = new CcQrcodeDiscount();
        qrcodeDiscount.setCardType(map.get("cardType").toString());
        qrcodeDiscount.setCityInDiscount(map.get("cityInDiscount").toString());
        qrcodeDiscount.setCityInPreferentialAmount(map.get("cityInPreferentialAmount").toString());
        qrcodeDiscount.setCityOutDiscount(map.get("cityOutDiscount").toString());
        qrcodeDiscount.setCityOutPreferentialAmount(map.get("cityOutPreferentialAmount").toString());
        qrcodeDiscount.setMaxPreferentialAmount(map.get("maxPreferentialAmount").toString());
        qrcodeDiscount.setSubCompanyId(map.get("subCompanyId").toString());
        model.addAttribute("qrcodeDiscount",qrcodeDiscount);
        LogObjectHolder.me().set(qrcodeDiscount);
        return PREFIX + "qrcodeDiscount_edit.html";
    }

    /**
     * 获取二维码折扣列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String subCompanyId
    
    ) {
        //return qrcodeDiscountService.selectList(null);
        
        Page<CcQrcodeDiscount> page = new PageFactory<CcQrcodeDiscount>().defaultPage();
		 List<Map<String, Object>> result = qrcodeDiscountDao.selectQrcodeDiscounts(page, 
   		 subCompanyId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcQrcodeDiscount>) new QrcodeDiscountWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增二维码折扣
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeDiscount qrcodeDiscount) {
        List<Map<String, Object>> result = qrcodeDiscountDao.selectQrcodeDiscountsById(qrcodeDiscount.getSubCompanyId());
        if(result.isEmpty()) {
            qrcodeDiscountService.insert(qrcodeDiscount);
            return SUCCESS_TIP;
        }else {
            return new ErrorTip("该运营公司的二维码折扣率已配置，请检查！");
        }
    }

    /**
     * 删除二维码折扣
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer subCompanyId) {
        qrcodeDiscountService.delete(new EntityWrapper<CcQrcodeDiscount>().eq("sub_company_id", subCompanyId));
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码折扣
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeDiscount qrcodeDiscount) {
        qrcodeDiscountService.update(qrcodeDiscount, new EntityWrapper<CcQrcodeDiscount>().eq("sub_company_id", qrcodeDiscount.getSubCompanyId()));
        return SUCCESS_TIP;
    }

    /**
     * 二维码折扣详情
     */
    @RequestMapping(value = "/detail/{subCompanyId}")
    @ResponseBody
    public Object detail(@PathVariable("subCompanyId") String subCompanyId) {
        Map map = new HashMap();
        map.put("sub_company_id",subCompanyId);
        List<CcQrcodeDiscount> list = qrcodeDiscountService.selectByMap(map);
        return list.get(0);
        //return qrcodeDiscountService.selectById(subCompanyId);
    }
}
