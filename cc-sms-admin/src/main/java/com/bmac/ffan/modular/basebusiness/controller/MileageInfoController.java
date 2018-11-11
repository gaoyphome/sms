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
import com.bmac.ffan.modular.basebusiness.dao.MileageInfoDao;
import com.bmac.ffan.common.persistence.model.CcMileageInfo;
import com.bmac.ffan.modular.basebusiness.service.IMileageInfoService;
import com.bmac.ffan.modular.basebusiness.warpper.MileageInfoWarpper;

/**
 * 里程信息管理控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 16:48:11
 */
@Controller
@RequestMapping("/mileageInfo")
public class MileageInfoController extends BaseController {

    private String PREFIX = "/basebusiness/mileageInfo/";

    @Autowired
    private IMileageInfoService mileageInfoService;

	@Resource
	MileageInfoDao mileageInfoDao;
	
    /**
     * 跳转到里程信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mileageInfo.html";
    }

    /**
     * 跳转到添加里程信息管理
     */
    @RequestMapping("/mileageInfo_add")
    public String mileageInfoAdd() {
        return PREFIX + "mileageInfo_add.html";
    }

    /**
     * 跳转到修改里程信息管理
     */
    @RequestMapping("/mileageInfo_update/{mileageInfoId}")
    public String mileageInfoUpdate(@PathVariable Integer mileageInfoId, Model model) {
        CcMileageInfo mileageInfo = mileageInfoService.selectById(mileageInfoId);
        model.addAttribute("mileageInfo",mileageInfo);
        LogObjectHolder.me().set(mileageInfo);
        return PREFIX + "mileageInfo_edit.html";
    }

    /**
     * 获取里程信息管理列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String lineId,
    @RequestParam(required = false) String mileageValue
    
    ) {
        //return mileageInfoService.selectList(null);
        
        Page<CcMileageInfo> page = new PageFactory<CcMileageInfo>().defaultPage();
		 List<Map<String, Object>> result = mileageInfoDao.selectMileageInfos(page, 
   		 lineId,
   		 mileageValue,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcMileageInfo>) new MileageInfoWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增里程信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcMileageInfo mileageInfo) {
    	mileageInfo.setUpdateTime(DateUtil.getAllMsTime());
    	mileageInfo.setCreateTime(DateUtil.getAllMsTime());
        mileageInfoService.insert(mileageInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除里程信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mileageInfoId) {
        mileageInfoService.deleteById(mileageInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改里程信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcMileageInfo mileageInfo) {
    	mileageInfo.setUpdateTime(DateUtil.getAllMsTime());
        mileageInfoService.updateById(mileageInfo);
        return SUCCESS_TIP;
    }

    /**
     * 里程信息管理详情
     */
    @RequestMapping(value = "/detail/{mileageInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("mileageInfoId") Integer mileageInfoId) {
        return mileageInfoService.selectById(mileageInfoId);
    }
}
