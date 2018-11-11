package com.bmac.ffan.modular.pos.controller;

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
import com.bmac.ffan.modular.pos.dao.PosCardTypeDao;
import com.bmac.ffan.common.persistence.model.CcPosCardType;
import com.bmac.ffan.modular.pos.service.IPosCardTypeService;
import com.bmac.ffan.modular.pos.warpper.PosCardTypeWarpper;

/**
 * 可用卡类型控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 17:55:00
 */
@Controller
@RequestMapping("/posCardType")
public class PosCardTypeController extends BaseController {

    private String PREFIX = "/pos/posCardType/";

    @Autowired
    private IPosCardTypeService posCardTypeService;

	@Resource
	PosCardTypeDao posCardTypeDao;
	
    /**
     * 跳转到可用卡类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "posCardType.html";
    }

    /**
     * 跳转到添加可用卡类型
     */
    @RequestMapping("/posCardType_add")
    public String posCardTypeAdd() {
        return PREFIX + "posCardType_add.html";
    }

    /**
     * 跳转到修改可用卡类型
     */
    @RequestMapping("/posCardType_update/{posCardTypeId}")
    public String posCardTypeUpdate(@PathVariable Integer posCardTypeId, Model model) {
        CcPosCardType posCardType = posCardTypeService.selectById(posCardTypeId);
        model.addAttribute("posCardType",posCardType);
        LogObjectHolder.me().set(posCardType);
        return PREFIX + "posCardType_edit.html";
    }
    /**
     * 跳转到修改可用卡类型
     */
    @RequestMapping("/posCardType_detail/{posCardTypeId}")
    public String posCardTypeDetail(@PathVariable Integer posCardTypeId, Model model) {
        CcPosCardType posCardType = posCardTypeService.selectById(posCardTypeId);
        model.addAttribute("posCardType",posCardType);
        LogObjectHolder.me().set(posCardType);
        return PREFIX + "posCardType_detail.html";
    }
    /**
     * 获取可用卡类型列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String mchntcode,
    @RequestParam(required = false) String cardAttr
    
    ) {
        //return posCardTypeService.selectList(null);
        
        Page<CcPosCardType> page = new PageFactory<CcPosCardType>().defaultPage();
		 List<Map<String, Object>> result = posCardTypeDao.selectPosCardTypes(page, 
   		 mchntcode,
   		 cardAttr,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcPosCardType>) new PosCardTypeWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增可用卡类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcPosCardType posCardType) {
    	posCardType.setUpdateTime(DateUtil.getAllMsTime());
    	posCardType.setCreateTime(DateUtil.getAllMsTime());
        posCardTypeService.insert(posCardType);
        return SUCCESS_TIP;
    }

    /**
     * 删除可用卡类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer posCardTypeId) {
        posCardTypeService.deleteById(posCardTypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改可用卡类型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcPosCardType posCardType) {
    	posCardType.setUpdateTime(DateUtil.getAllMsTime());
        posCardTypeService.updateById(posCardType);
        return SUCCESS_TIP;
    }

    /**
     * 可用卡类型详情
     */
    @RequestMapping(value = "/detail/{posCardTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("posCardTypeId") Integer posCardTypeId) {
        return posCardTypeService.selectById(posCardTypeId);
    }
}
