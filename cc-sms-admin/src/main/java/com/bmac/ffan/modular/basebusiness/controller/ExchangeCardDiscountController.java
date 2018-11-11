package com.bmac.ffan.modular.basebusiness.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.persistence.model.CcBusCompany;
import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;
import com.bmac.ffan.common.persistence.model.ResultMap;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.base.tips.SuccessTip;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.modular.basebusiness.ratezf.ParseZf;
import com.bmac.ffan.modular.basebusiness.service.IBusCompanyService;
import com.bmac.ffan.modular.basebusiness.service.IGenerateParametFileService;
import com.bmac.ffan.modular.basebusiness.service.ILocalCardDiscountService;
import com.bmac.ffan.modular.basebusiness.warpper.ExchangeCardDiscountWarpper;
import com.bmac.ffan.modular.qrcode.service.ICardDiscountVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmac.ffan.core.log.LogObjectHolder;

import javax.annotation.Resource;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.basebusiness.dao.ExchangeCardDiscountDao;
import com.bmac.ffan.common.persistence.model.CcExchangeCardDiscount;
import com.bmac.ffan.modular.basebusiness.service.IExchangeCardDiscountService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 实体互通卡折扣设置 控制器
 *
 * @author 工具生成
 * @Date 2017-12-21 17:18:21
 */
@Controller
@RequestMapping("/exchangeCardDiscount")
public class ExchangeCardDiscountController extends BaseController {

    private String PREFIX = "/basebusiness/exchangeCardDiscount/";
    @Resource
    IGenerateParametFileService generateParametFileService;
    @Autowired
    private ILocalCardDiscountService localCardDiscountService;
    @Autowired
    private IExchangeCardDiscountService exchangeCardDiscountService;

    @Autowired
    IBusCompanyService busCompanyService;

	@Resource
	ExchangeCardDiscountDao exchangeCardDiscountDao;
    @Resource
    ICardDiscountVersionService cardDiscountVersionService;
    /**
     * 跳转到实体互通卡折扣设置 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "exchangeCardDiscount.html";
    }

    /**
     * 跳转到添加实体互通卡折扣设置 
     */
    @RequestMapping("/exchangeCardDiscount_add")
    public String exchangeCardDiscountAdd(Model model) {
        Map map = new HashMap();
        map.put("type","1");
        List<CcBusCompany> ccBusCompanies = busCompanyService.selectByMap(map);
        model.addAttribute("ccBusCompanies",ccBusCompanies);
        LogObjectHolder.me().set(ccBusCompanies);
        return PREFIX + "exchangeCardDiscount_add.html";
    }

    /**
     * 跳转到修改实体互通卡折扣设置 
     */
    @RequestMapping("/exchangeCardDiscount_update/{exchangeCardDiscountId}")
    public String exchangeCardDiscountUpdate(@PathVariable Integer exchangeCardDiscountId, Model model) {
        CcExchangeCardDiscount exchangeCardDiscount = exchangeCardDiscountService.selectById(exchangeCardDiscountId);
        model.addAttribute("exchangeCardDiscount",exchangeCardDiscount);
        LogObjectHolder.me().set(exchangeCardDiscount);
        return PREFIX + "exchangeCardDiscount_edit.html";
    }

    /**
     * 获取实体互通卡折扣设置 列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String subCompanyId
    
    ) {
        //return exchangeCardDiscountService.selectList(null);
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        Page<CcExchangeCardDiscount> page = new PageFactory<CcExchangeCardDiscount>().defaultPage();
		 List<Map<String, Object>> result = exchangeCardDiscountDao.selectExchangeCardDiscounts(page,
                 ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
   		 subCompanyId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcExchangeCardDiscount>) new ExchangeCardDiscountWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增实体互通卡折扣设置 
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcExchangeCardDiscount exchangeCardDiscount) {
        Map map = new HashMap();
        map.put("sub_company_id",exchangeCardDiscount.getSubCompanyId());
        map.put("iin",exchangeCardDiscount.getIin());
        map.put("card_type",exchangeCardDiscount.getCardType());
        List list = exchangeCardDiscountService.selectByMap(map);
        if(!list.isEmpty()){//如果存在，则不允许修改
            return ERROR;
        }
        exchangeCardDiscountService.insert(exchangeCardDiscount);
        if(!cardDiscountVersionService.updateVersion(exchangeCardDiscount.getSubCompanyId())){
            return ERROR;
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除实体互通卡折扣设置 
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer exchangeCardDiscountId) {
        exchangeCardDiscountService.deleteById(exchangeCardDiscountId);
        return SUCCESS_TIP;
    }

    /**
     * 修改实体互通卡折扣设置 
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcExchangeCardDiscount exchangeCardDiscount) {
        exchangeCardDiscountService.updateById(exchangeCardDiscount);
        return SUCCESS_TIP;
    }

    /**
     * 实体互通卡折扣设置 详情
     */
    @RequestMapping(value = "/detail/{exchangeCardDiscountId}")
    @ResponseBody
    public Object detail(@PathVariable("exchangeCardDiscountId") Integer exchangeCardDiscountId) {
        return exchangeCardDiscountService.selectById(exchangeCardDiscountId);
    }

    /**
     * Z3文件导入页面初始化
     * @return
     */
    @RequestMapping("/exchangeCardDiscount_import")
    public String exchangeCardDiscountImport(Model model) {
        Object companyId = super.getSession().getAttribute("companyId");
        Wrapper wrapper = new EntityWrapper<CcBusCompany>();
        if(companyId != null ){
            if(!SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) {
                wrapper.where("(company_id = {0} or parent_company_id = {0}) ",  companyId.toString());
            }
        }else{
            ShiroKit.getSubject().logout();
            return REDIRECT + "/login.html";
        }
        wrapper.eq("type",1);
        List<CcBusCompany> ccBusCompanies = busCompanyService.selectList(wrapper);
        model.addAttribute("ccBusCompanies",ccBusCompanies);
        LogObjectHolder.me().set(ccBusCompanies);
        return PREFIX + "exchangeCardDiscount_import.html";
    }

    /**
     * 文件导入
     * @param fileField
     * @param subCompanyId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importfile", method = RequestMethod.POST)
    @ResponseBody
    public Object importfile(@RequestParam("filecontent") MultipartFile fileField,
                             @RequestParam("subCompanyId") String subCompanyId) throws IOException {

        InputStream inputStream = fileField.getInputStream();
        ParseZf zf  = new ParseZf(inputStream, ParseZf.ZFType.Z3);
        exchangeCardDiscountService.importfile(zf.getRateZ3(), subCompanyId);
        Map map = new HashMap();
        map.put("sub_company_id",subCompanyId);
        List<CcLocalCardDiscount> localCardDiscountList = localCardDiscountService.selectByMap(map);
        List<CcExchangeCardDiscount> exchangeCardDiscountList = exchangeCardDiscountService.selectByMap(map);
        if(localCardDiscountList.isEmpty()){
            return new SuccessTip("Z3文件导入成功，请继续导入Z2文件");
        }
        ResultMap resultMap = generateParametFileService.generateCardDiscountFile(subCompanyId, localCardDiscountList, exchangeCardDiscountList);
        if(!resultMap.isSuccess()){
            return new ErrorTip(resultMap.getMsg());
        }
        return SUCCESS_TIP;
    }
}
