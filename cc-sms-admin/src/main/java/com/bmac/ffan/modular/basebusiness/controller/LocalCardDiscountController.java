package com.bmac.ffan.modular.basebusiness.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.persistence.model.CcBusCompany;
import com.bmac.ffan.common.persistence.model.CcExchangeCardDiscount;
import com.bmac.ffan.common.persistence.model.ResultMap;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.base.tips.SuccessTip;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.modular.basebusiness.ratezf.ParseZf;
import com.bmac.ffan.modular.basebusiness.service.IBusCompanyService;
import com.bmac.ffan.modular.basebusiness.service.IExchangeCardDiscountService;
import com.bmac.ffan.modular.basebusiness.service.IGenerateParametFileService;
import com.bmac.ffan.modular.basebusiness.warpper.LocalCardDiscountWarpper;
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
import com.bmac.ffan.modular.basebusiness.dao.LocalCardDiscountDao;
import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;
import com.bmac.ffan.modular.basebusiness.service.ILocalCardDiscountService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 实体本地卡折扣设置 控制器
 *
 * @author 工具生成
 * @Date 2017-12-21 17:10:23
 */
@Controller
@RequestMapping("/localCardDiscount")
public class LocalCardDiscountController extends BaseController {

    private String PREFIX = "/basebusiness/localCardDiscount/";

    @Autowired
    private ILocalCardDiscountService localCardDiscountService;
    @Autowired
    private IExchangeCardDiscountService exchangeCardDiscountService;
    @Resource
    IGenerateParametFileService generateParametFileService;
    @Autowired
    IBusCompanyService busCompanyService;

    @Resource
	LocalCardDiscountDao localCardDiscountDao;

    @Resource
    ICardDiscountVersionService cardDiscountVersionService;
    /**
     * 跳转到实体本地卡折扣设置 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "localCardDiscount.html";
    }

    /**
     * 跳转到添加实体本地卡折扣设置 
     */
    @RequestMapping("/localCardDiscount_add")
    public String localCardDiscountAdd(Model model) {
        Map map = new HashMap();
        map.put("type","1");
        List<CcBusCompany> ccBusCompanies = busCompanyService.selectByMap(map);
        model.addAttribute("ccBusCompanies",ccBusCompanies);
        LogObjectHolder.me().set(ccBusCompanies);
        return PREFIX + "localCardDiscount_add.html";
    }

    /**
     * Z2文件导入页面初始化
     * @return
     */
    @RequestMapping("/localCardDiscount_import")
    public String localCardDiscountImport(Model model) {
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
        //List<CcBusCompany> ccBusCompanies = busCompanyService.selectByMap(map);
        model.addAttribute("ccBusCompanies",ccBusCompanies);
        LogObjectHolder.me().set(ccBusCompanies);
        return PREFIX + "localCardDiscount_import.html";
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
        ParseZf zf  = new ParseZf(inputStream, ParseZf.ZFType.Z2);
        localCardDiscountService.importfile(zf.getRateZ2(), subCompanyId);
        Map map = new HashMap();
        map.put("sub_company_id",subCompanyId);
        List<CcLocalCardDiscount> localCardDiscountList = localCardDiscountService.selectByMap(map);
        List<CcExchangeCardDiscount> exchangeCardDiscountList = exchangeCardDiscountService.selectByMap(map);
        if(exchangeCardDiscountList.isEmpty()){
            return new SuccessTip("Z2文件导入成功，请继续导入Z3文件");
        }
        ResultMap resultMap = generateParametFileService.generateCardDiscountFile(subCompanyId, localCardDiscountList, exchangeCardDiscountList);
        if(!resultMap.isSuccess()){
            return new ErrorTip(resultMap.getMsg());
        }
        return SUCCESS_TIP;
    }
    /**
     * 跳转到修改实体本地卡折扣设置 
     */
    @RequestMapping("/localCardDiscount_update/{localCardDiscountId}")
    public String localCardDiscountUpdate(@PathVariable Integer localCardDiscountId, Model model) {
        CcLocalCardDiscount localCardDiscount = localCardDiscountService.selectById(localCardDiscountId);
        model.addAttribute("localCardDiscount",localCardDiscount);
        LogObjectHolder.me().set(localCardDiscount);

        Map map = new HashMap();
        map.put("type","1");
        List<CcBusCompany> ccBusCompanies = busCompanyService.selectByMap(map);
        model.addAttribute("ccBusCompanies",ccBusCompanies);
        LogObjectHolder.me().set(ccBusCompanies);
        return PREFIX + "localCardDiscount_edit.html";
    }

    /**
     * 获取实体本地卡折扣设置 列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String subCompanyId
    
    ) {
        //return localCardDiscountService.selectList(null);
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        Page<CcLocalCardDiscount> page = new PageFactory<CcLocalCardDiscount>().defaultPage();
		 List<Map<String, Object>> result = localCardDiscountDao.selectLocalCardDiscounts(page,
                 ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
   		 subCompanyId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcLocalCardDiscount>) new LocalCardDiscountWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增实体本地卡折扣设置 
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcLocalCardDiscount localCardDiscount) {
        Map map = new HashMap();
        map.put("sub_company_id",localCardDiscount.getSubCompanyId());
        map.put("card_phy_type",localCardDiscount.getCardPhyType());
        map.put("card_attr",localCardDiscount.getCardAttr());
        map.put("card_type",localCardDiscount.getCardType());
        List list = localCardDiscountService.selectByMap(map);
        if(!list.isEmpty()){//如果存在，则不允许修改
            return ERROR;
        }

        localCardDiscountService.insert(localCardDiscount);
        if(!cardDiscountVersionService.updateVersion(localCardDiscount.getSubCompanyId())){
            return ERROR;
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除实体本地卡折扣设置 
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer localCardDiscountId) {
        localCardDiscountService.deleteById(localCardDiscountId);
        return SUCCESS_TIP;
    }

    /**
     * 修改实体本地卡折扣设置 
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcLocalCardDiscount localCardDiscount) {
        localCardDiscountService.updateById(localCardDiscount);
        return SUCCESS_TIP;
    }

    /**
     * 实体本地卡折扣设置 详情
     */
    @RequestMapping(value = "/detail/{localCardDiscountId}")
    @ResponseBody
    public Object detail(@PathVariable("localCardDiscountId") Integer localCardDiscountId) {
        return localCardDiscountService.selectById(localCardDiscountId);
    }
}
