package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.persistence.model.CcBusInfo;
import com.bmac.ffan.common.persistence.model.CcLineInfo;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.service.IBusInfoService;
import com.bmac.ffan.modular.basebusiness.service.ILineInfoService;
import com.bmac.ffan.modular.pos.dao.BusPosFat;
import com.bmac.ffan.modular.pos.warpper.BusPosWarpper;
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
import com.bmac.ffan.modular.pos.dao.BusPosDao;
import com.bmac.ffan.common.persistence.model.CcBusPos;
import com.bmac.ffan.modular.pos.service.IBusPosService;

/**
 * 公交POS信息控制器
 *
 * @author 工具生成
 * @Date 2017-11-24 10:27:47
 */
@Controller
@RequestMapping("/busPos")
public class BusPosController extends BaseController {

    private String PREFIX = "/pos/busPos/";

    @Autowired
    private IBusPosService busPosService;

    @Autowired
    private IBusInfoService busInfoService;

    @Autowired
    private ILineInfoService lineInfoService;

	@Resource
	BusPosDao busPosDao;
	
    /**
     * 跳转到公交POS信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busPos.html";
    }

    /**
     * 跳转到添加公交POS信息
     */
    @RequestMapping("/busPos_add")
    public String busPosAdd() {
        return PREFIX + "busPos_add.html";
    }

    /**
     * 跳转到修改公交POS信息
     */
    @RequestMapping("/busPos_update/{busPosId}")
    public String busPosUpdate(@PathVariable Integer busPosId, Model model) {
        CcBusPos busPos = busPosService.selectById(busPosId);
        model.addAttribute("busPos",busPos);
        LogObjectHolder.me().set(busPos);
        return PREFIX + "busPos_edit.html";
    }
    /**
     * 跳转到修改公交POS信息
     */
    @RequestMapping("/busPos_detail/{busPosId}")
    public String busPosDetail(@PathVariable Integer busPosId, Model model) {
        CcBusPos busPos = busPosService.selectById(busPosId);
        model.addAttribute("busPos",busPos);
        LogObjectHolder.me().set(busPos);
        return PREFIX + "busPos_detail.html";
    }
    /**
     * 获取公交POS信息列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String posId,
    @RequestParam(required = false) String plateNumber,
    @RequestParam(required = false) String lineName

    ) {
        //return busPosService.selectList(null);
        if(posId == null || "".equals(posId.trim())){
            posId = null;
        }else{
            posId = Long.parseLong(posId,16)+"";
        }
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        Page<BusPosFat> page = new PageFactory<BusPosFat>().defaultPage();
		 List<Map<String, Object>> result = busPosDao.selectBusPoss(page,
                 ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
                 posId,
                 plateNumber,
                 lineName);
	     page.setRecords((List<BusPosFat>) new BusPosWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增公交POS信息
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcBusPos busPos) {
        busPos.setUpdateTime(DateUtil.getAllMsTime());
        busPos.setCreateTime(DateUtil.getAllMsTime());
      //POS信息表中查询POS信息是否存在
        @SuppressWarnings("rawtypes")
		Map posMap = new HashMap();
        posMap.put("pos_id", busPos.getPosId());
		@SuppressWarnings("rawtypes")
		List list = busPosService.selectByMap(posMap);
        if(list == null || list.size() == 0){
        	busPosService.insert(busPos);
        }else{
        	return new ErrorTip("新增公交POS失败,POS信息已存在！");
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除公交POS信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busPosId) {
        busPosService.deleteById(busPosId);
        return SUCCESS_TIP;
    }

    /**
     * 修改公交POS信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcBusPos busPos) throws Exception {
        Map map = new HashMap();
        map.put("bus_id",busPos.getBusId());
        List<CcBusInfo> busInfos = busInfoService.selectByMap(map);
        if(busInfos.isEmpty()){
            throw new Exception("车辆ID不存在，请存新选择车辆！");
        }

        map = new HashMap();
        map.put("line_id",busInfos.get(0).getLineId());
        List<CcLineInfo> lineInfos =  lineInfoService.selectByMap(map);
        if(lineInfos.isEmpty()){
            throw new Exception("该线路不存在["+busInfos.get(0).getLineId()+"]，请重新选择！");
        }else{
            Object userCompany = super.getSession().getAttribute("companyId");
            if(userCompany != null){
                if(!userCompany.toString().equals(lineInfos.get(0).getSubCompanyId())
                        && !userCompany.toString().equals(lineInfos.get(0).getSubCompanyId())
                        && !SysConstant.DEPTID_ALL_PRIVILEGES.equals(userCompany.toString())){
                    throw new Exception("当前用户帐号与线路所属运营公司不匹配！");
                }
            }else{
                ShiroKit.getSubject().logout();
                return null;
            }
        }

        CcBusPos busPos_ = busPosService.selectById(busPos.getId());
        busPos_.setBusId(busPos.getBusId());
        busPos.setUpdateTime(DateUtil.getAllMsTime());
        busPosService.updateById(busPos);
        return SUCCESS_TIP;
    }

    /**
     * 公交POS信息详情
     */
    @RequestMapping(value = "/detail/{busPosId}")
    @ResponseBody
    public Object detail(@PathVariable("busPosId") Integer busPosId) {
        return busPosService.selectById(busPosId);
    }
}
