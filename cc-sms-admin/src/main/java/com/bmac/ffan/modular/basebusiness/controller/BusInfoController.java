package com.bmac.ffan.modular.basebusiness.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.modular.basebusiness.dao.BusInfoFat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.bmac.ffan.common.persistence.model.CcBusInfo;
import com.bmac.ffan.common.persistence.model.CcLineInfo;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.log.LogObjectHolder;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.dao.BusInfoDao;
import com.bmac.ffan.modular.basebusiness.service.IBusInfoService;
import com.bmac.ffan.modular.basebusiness.service.ILineInfoService;
import com.bmac.ffan.modular.basebusiness.warpper.BusInfoWarpper;

/**
 * 车辆信息管理控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 14:24:33
 */
@Controller
@RequestMapping("/busInfo")
public class BusInfoController extends BaseController {

    private String PREFIX = "/basebusiness/busInfo/";

    @Autowired
    private IBusInfoService busInfoService;
    @Autowired
    private ILineInfoService lineInfoService;
	@Resource
	BusInfoDao busInfoDao;
	
    /**
     * 跳转到车辆信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busInfo.html";
    }

    /**
     * 跳转到添加车辆信息管理
     */
    @RequestMapping("/busInfo_add")
    public String busInfoAdd() {
        return PREFIX + "busInfo_add.html";
    }

    /**
     * 跳转到修改车辆信息管理
     */
    @RequestMapping("/busInfo_update/{busInfoId}")
    public String busInfoUpdate(@PathVariable Integer busInfoId, Model model) {
        CcBusInfo busInfo = busInfoService.selectById(busInfoId);
        model.addAttribute("busInfo",busInfo);
        LogObjectHolder.me().set(busInfo);
        return PREFIX + "busInfo_edit.html";
    }

    /**
     * 获取车辆信息管理列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    @RequestParam(required = false) String lineId,
    @RequestParam(required = false) String lineName,
    @RequestParam(required = false) String plateNumber
    
    ) {
        //return busInfoService.selectList(null);

        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        Page<BusInfoFat> page = new PageFactory<BusInfoFat>().defaultPage();
        List<Map<String, Object>> result = busInfoDao.selectBusInfos(page,
                ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
   		    lineId,
                lineName,
             plateNumber,
             page.getOrderByField(), page.isAsc());
             page.setRecords((List<BusInfoFat>) new BusInfoWarpper(result).warp());
             return super.packForBT(page);
	     
    }

    /**
     * 新增车辆信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcBusInfo busInfo) throws Exception {
        /**
         * 检查车辆是否已存在
         */
        Map map = new HashMap();
        map.put("bus_id", busInfo.getBusId());
        List<CcBusInfo> busInfos = busInfoService.selectByMap(map);
        if (!busInfos.isEmpty()){
            throw new Exception("车辆已绑定指定线路["+busInfos.get(0).getLineId()+"]，请重新选择！");
        }
        /**
         * 检查线路所属公司是否与登录帐号匹配
         */
        map = new HashMap();
        map.put("line_id",busInfo.getLineId());
        List<CcLineInfo> lineInfos =  lineInfoService.selectByMap(map);
        if(lineInfos.isEmpty()){
            throw new Exception("该线路不存在["+busInfo.getLineId()+"]，请重新选择！");
        }else{
            Object userCompany = super.getSession().getAttribute("companyId");
            if(userCompany != null){
                if(!userCompany.toString().equals(lineInfos.get(0).getCompanyId())
                        && !userCompany.toString().equals(lineInfos.get(0).getSubCompanyId())
                        && !SysConstant.DEPTID_ALL_PRIVILEGES.equals(userCompany.toString())){
                    throw new Exception("当前用户帐号与线路所属运营公司不匹配！");
                }
            }else{
                ShiroKit.getSubject().logout();
                return null;
            }
        }


    	busInfo.setUpdateTime(DateUtil.getAllMsTime());
    	busInfo.setCreateTime(DateUtil.getAllMsTime());
        busInfoService.insert(busInfo);
        return SUCCESS_TIP;
    }
    /**
     * 新增POS信息
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/busInfo_addpos/{ccBusId}" )
    public String addpos(@PathVariable("ccBusId") Integer ccBusId, Model model) {
        CcBusInfo busInfo = busInfoService.selectById(ccBusId);
        Map map = new HashMap();
        map.put("line_id",busInfo.getLineId());
        List list = lineInfoService.selectByMap(map);

        model.addAttribute("busId",busInfo.getBusId());
        LogObjectHolder.me().set(busInfo.getBusId());
        if(!list.isEmpty()) {
            CcLineInfo ccLineInfo = (CcLineInfo) list.get(0);
            model.addAttribute("lineId", ccLineInfo.getLineId());
            LogObjectHolder.me().set(ccLineInfo.getLineId());
            model.addAttribute("lineName", ccLineInfo.getName());
            LogObjectHolder.me().set(ccLineInfo.getName());
        }
        return PREFIX + "busInfo_addpos.html";
    }
    /**
     * 删除车辆信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busInfoId) {
        if(busInfoService.deleteBusInfo(busInfoId)) {
            return SUCCESS_TIP;
        }else{
            return new ErrorTip("删除车辆信息失败!");
        }
    }

    /**
     * 修改车辆信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcBusInfo busInfo) throws Exception {
        /**
         * 检查线路所属公司是否与登录帐号匹配
         */
        Map map = new HashMap();
        map.put("line_id",busInfo.getLineId());
        List<CcLineInfo> lineInfos =  lineInfoService.selectByMap(map);
        if(lineInfos.isEmpty()){
            throw new Exception("该线路不存在["+busInfo.getLineId()+"]，请重新选择！");
        }else{
            Object userCompany = super.getSession().getAttribute("companyId");
            if(userCompany != null){
                if(!userCompany.toString().equals(lineInfos.get(0).getCompanyId())
                        && !userCompany.toString().equals(lineInfos.get(0).getSubCompanyId())
                        && !SysConstant.DEPTID_ALL_PRIVILEGES.equals(userCompany.toString())){
                    throw new Exception("当前用户帐号与线路所属运营公司不匹配！");
                }
            }else{
                ShiroKit.getSubject().logout();
                return new ErrorTip("连接异常，请重新登录");
            }
        }
    	busInfo.setUpdateTime(DateUtil.getAllMsTime());
        busInfoService.updateById(busInfo);
        return SUCCESS_TIP;
    }

    /**
     * 车辆信息管理详情
     */
    @RequestMapping(value = "/detail/{busInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("busInfoId") Integer busInfoId) {
        return busInfoService.selectById(busInfoId);
    }
}
