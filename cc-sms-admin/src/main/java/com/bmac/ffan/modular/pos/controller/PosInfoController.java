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
import com.bmac.ffan.modular.pos.dao.PosInfoDao;
import com.bmac.ffan.common.persistence.model.CcPosInfo;
import com.bmac.ffan.modular.pos.service.IPosInfoService;
import com.bmac.ffan.modular.pos.warpper.PosInfoWarpper;

/**
 * POS信息控制器
 *
 * @author 工具生成
 * @Date 2017-11-21 10:44:44
 */
@Controller
@RequestMapping("/posInfo")
public class PosInfoController extends BaseController {

    private String PREFIX = "/pos/posInfo/";

    @Autowired
    private IPosInfoService posInfoService;

	@Resource
	PosInfoDao posInfoDao;
	
    /**
     * 跳转到POS信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "posInfo.html";
    }

    /**
     * 跳转到添加POS信息
     */
    @RequestMapping("/posInfo_add")
    public String posInfoAdd() {
        return PREFIX + "posInfo_add.html";
    }

    /**
     * 跳转到修改POS信息
     */
    @RequestMapping("/posInfo_update/{posInfoId}")
    public String posInfoUpdate(@PathVariable Integer posInfoId, Model model) {
        CcPosInfo posInfo = posInfoService.selectById(posInfoId);
        model.addAttribute("posInfo",posInfo);
        LogObjectHolder.me().set(posInfo);
        return PREFIX + "posInfo_edit.html";
    }

    /**
     * 跳转到修改POS信息
     */
    @RequestMapping("/posInfo_detail/{posInfoId}")
    public String posInfoDetail(@PathVariable Integer posInfoId, Model model) {
        CcPosInfo posInfo = posInfoService.selectById(posInfoId);
        model.addAttribute("posInfo",posInfo);
        LogObjectHolder.me().set(posInfo);
        return PREFIX + "posInfo_detail.html";
    }

    /**
     * 获取POS信息列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String posId,
    @RequestParam(required = false) String samId
    
    ) {
        //return posInfoService.selectList(null);
        
        Page<CcPosInfo> page = new PageFactory<CcPosInfo>().defaultPage();
		 List<Map<String, Object>> result = posInfoDao.selectPosInfos(page, 
   		 posId,
   		 samId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcPosInfo>) new PosInfoWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增POS信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcPosInfo posInfo) {
    	posInfo.setUpdateTime(DateUtil.getAllMsTime());
    	posInfo.setCreateTime(DateUtil.getAllMsTime());
        posInfoService.insert(posInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除POS信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer posInfoId) {
        posInfoService.deleteById(posInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改POS信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcPosInfo posInfo) {
    	posInfo.setUpdateTime(DateUtil.getAllMsTime());
        posInfoService.updateById(posInfo);
        return SUCCESS_TIP;
    }

    /**
     * POS信息详情
     */
    @RequestMapping(value = "/detail/{posInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("posInfoId") Integer posInfoId) {
        return posInfoService.selectById(posInfoId);
    }
}
