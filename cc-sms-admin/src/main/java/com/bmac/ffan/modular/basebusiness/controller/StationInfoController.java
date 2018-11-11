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
import com.bmac.ffan.modular.basebusiness.dao.StationInfoDao;
import com.bmac.ffan.common.persistence.model.CcStationInfo;
import com.bmac.ffan.modular.basebusiness.service.IStationInfoService;
import com.bmac.ffan.modular.basebusiness.warpper.StationInfoWarpper;

/**
 * 站点信息管理控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 16:00:30
 */
@Controller
@RequestMapping("/stationInfo")
public class StationInfoController extends BaseController {

    private String PREFIX = "/basebusiness/stationInfo/";

    @Autowired
    private IStationInfoService stationInfoService;

	@Resource
	StationInfoDao stationInfoDao;
	
    /**
     * 跳转到站点信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "stationInfo.html";
    }

    /**
     * 跳转到添加站点信息管理
     */
    @RequestMapping("/stationInfo_add")
    public String stationInfoAdd() {
        return PREFIX + "stationInfo_add.html";
    }

    /**
     * 跳转到修改站点信息管理
     */
    @RequestMapping("/stationInfo_update/{stationInfoId}")
    public String stationInfoUpdate(@PathVariable Integer stationInfoId, Model model) {
        CcStationInfo stationInfo = stationInfoService.selectById(stationInfoId);
        model.addAttribute("stationInfo",stationInfo);
        LogObjectHolder.me().set(stationInfo);
        return PREFIX + "stationInfo_edit.html";
    }

    /**
     * 获取站点信息管理列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String lineId
    
    ) {
        //return stationInfoService.selectList(null);
        
        Page<CcStationInfo> page = new PageFactory<CcStationInfo>().defaultPage();
		 List<Map<String, Object>> result = stationInfoDao.selectStationInfos(page, 
   		 name,
   		 lineId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcStationInfo>) new StationInfoWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增站点信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcStationInfo stationInfo) {
    	stationInfo.setUpdateTime(DateUtil.getAllMsTime());
    	stationInfo.setCreateTime(DateUtil.getAllMsTime());
        stationInfoService.insert(stationInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除站点信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer stationInfoId) {
        stationInfoService.deleteById(stationInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改站点信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcStationInfo stationInfo) {
    	stationInfo.setUpdateTime(DateUtil.getAllMsTime());
        stationInfoService.updateById(stationInfo);
        return SUCCESS_TIP;
    }

    /**
     * 站点信息管理详情
     */
    @RequestMapping(value = "/detail/{stationInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("stationInfoId") Integer stationInfoId) {
        return stationInfoService.selectById(stationInfoId);
    }
}
