package com.bmac.ffan.modular.basebusiness.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmac.ffan.core.log.LogObjectHolder;
import com.bmac.ffan.core.util.DateUtil;

import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.basebusiness.dao.PriceInfoDao;
import com.bmac.ffan.common.persistence.model.CcPriceInfo;
import com.bmac.ffan.modular.basebusiness.service.IPriceInfoService;
import com.bmac.ffan.modular.basebusiness.warpper.PriceInfoWarpper;

/**
 * 票价信息管理控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 16:23:15
 */
@Controller
@RequestMapping("/priceInfo")
public class PriceInfoController extends BaseController {

    private String PREFIX = "/basebusiness/priceInfo/";

    @Autowired
    private IPriceInfoService priceInfoService;

	@Resource
	PriceInfoDao priceInfoDao;
	
    /**
     * 跳转到票价信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "priceInfo.html";
    }

    /**
     * 跳转到添加票价信息管理
     */
    @RequestMapping("/priceInfo_add")
    public String priceInfoAdd() {
        return PREFIX + "priceInfo_add.html";
    }

    /**
     * 跳转到修改票价信息管理
     */
    @RequestMapping("/priceInfo_update/{priceInfoId}")
    public String priceInfoUpdate(@PathVariable Integer priceInfoId, Model model) {
        CcPriceInfo priceInfo = priceInfoService.selectById(priceInfoId);
        model.addAttribute("priceInfo",priceInfo);
        LogObjectHolder.me().set(priceInfo);
        return PREFIX + "priceInfo_edit.html";
    }

    /**
     * 获取票价信息管理列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String lineId,
    @RequestParam(required = false) String price
    
    ) {
        //return priceInfoService.selectList(null);
        
        Page<CcPriceInfo> page = new PageFactory<CcPriceInfo>().defaultPage();
		 List<Map<String, Object>> result = priceInfoDao.selectPriceInfos(page, 
   		 lineId,
   		 price,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcPriceInfo>) new PriceInfoWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增票价信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcPriceInfo priceInfo) {
    	priceInfo.setUpdateTime(DateUtil.getAllMsTime());
    	priceInfo.setCreateTime(DateUtil.getAllMsTime());
        priceInfoService.insert(priceInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除票价信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer priceInfoId) {
        priceInfoService.deleteById(priceInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改票价信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcPriceInfo priceInfo) {
    	priceInfo.setUpdateTime(DateUtil.getAllMsTime());
        priceInfoService.updateById(priceInfo);
        return SUCCESS_TIP;
    }

    /**
     * 票价信息管理详情
     */
    @RequestMapping(value = "/detail/{priceInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("priceInfoId") Integer priceInfoId) {
        return priceInfoService.selectById(priceInfoId);
    }
}
