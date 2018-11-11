package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.persistence.model.CcBusPos;
import com.bmac.ffan.common.persistence.model.CcBusPosFirmware;
import com.bmac.ffan.common.persistence.model.CcPosVersion;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.core.util.ByteUtil;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.pos.dao.BusPosFirmwareDao;
import com.bmac.ffan.modular.pos.service.IBusPosFirmwareService;
import com.bmac.ffan.modular.pos.service.IBusPosService;
import com.bmac.ffan.modular.pos.warpper.BusPosFirmwareWarpper;
import com.bmac.ffan.modular.pos.warpper.BusPosVersionWarpper;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.bmac.ffan.modular.pos.dao.BusPosVersionDao;
import com.bmac.ffan.common.persistence.model.CcBusPosVersion;
import com.bmac.ffan.modular.pos.service.IBusPosVersionService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公交POS固件版本控制器
 *
 * @author 工具生成
 * @Date 2017-12-26 14:39:00
 */
@Controller
@RequestMapping("/busPosVersion")
public class BusPosVersionController extends BaseController {

    private String PREFIX = "/pos/busPosVersion/";

    @Autowired
    private IBusPosVersionService busPosVersionService;
    @Resource
    IBusPosFirmwareService busPosFirmwareService;

    @Resource
    IBusPosService busPosService;
	@Resource
	BusPosVersionDao busPosVersionDao;
    @Resource
    BusPosFirmwareDao busPosFirmwareDao;
    /**
     * 跳转到公交POS固件版本首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busPosVersion.html";
    }

    /**
     * 跳转到添加公交POS固件版本
     */
    @RequestMapping("/busPosVersion_add")
    public String busPosVersionAdd(Model model) {
        List<Map<String, Object>> firmwaresList = busPosFirmwareDao.selectAllBusPosFirmwares();
        model.addAttribute("firmwaresList",(List<CcBusPosFirmware>) new BusPosFirmwareWarpper(firmwaresList).warp());
        return PREFIX + "busPosVersion_add.html";
    }

    /**
     * 跳转到修改公交POS固件版本
     */
    @RequestMapping("/busPosVersion_update/{busPosVersionId}")
    public String busPosVersionUpdate(@PathVariable Integer busPosVersionId, Model model) {
        CcBusPosVersion busPosVersion = busPosVersionService.selectById(busPosVersionId);
        model.addAttribute("busPosVersion",busPosVersion);
        LogObjectHolder.me().set(busPosVersion);
        return PREFIX + "busPosVersion_edit.html";
    }

    /**
     * 获取公交POS固件版本列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String firmVersion,
    @RequestParam(required = false) String posId
    
    ) {
        //return busPosVersionService.selectList(null);
        if(Pattern.compile("^[0-9a-fA-F]+$").matcher(posId).matches()){
            posId = Long.parseLong(posId, 16)+"";
        }
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        Page<CcBusPosVersion> page = new PageFactory<CcBusPosVersion>().defaultPage();
		 List<Map<String, Object>> result = busPosVersionDao.selectBusPosVersions(page,
         ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
   		 firmVersion,
         posId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcBusPosVersion>) new BusPosVersionWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增公交POS固件版本
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @Transactional(readOnly = false)
    public Object add(@RequestParam("content") String content,
                      @RequestParam("type") String type,
                      @RequestParam("upgradeType") String upgradeType,
                      @RequestParam("lineId") String lineId,
                      @RequestParam("posId") String posId,
                      @RequestParam("downStartTime") String downStartTime,
                      @RequestParam("downEndTime") String downEndTime,
                      @RequestParam("installStartTime") String installStartTime,
                      @RequestParam("installEndTime") String installEndTime,
                      @RequestParam("firmVersion") String firmVersion
                      ) throws IOException {

        CcBusPosFirmware ccBusPosFirmware = busPosFirmwareService.selectById(firmVersion);
        if(ccBusPosFirmware == null){
            return new ErrorTip("固件版本不存在！");
        }

        Map map = new HashMap();
        map.put("pos_id",Long.parseLong(posId,16));
        List poslist = busPosService.selectByMap(map);
        if(poslist == null || poslist.isEmpty()){
            return new ErrorTip("POS-ID不存在！");
        }

        CcBusPos ccBusPos = (CcBusPos) poslist.get(0);
        if(!ccBusPos.getPosVendor().equals(ccBusPosFirmware.getPosVendor())){
            return new ErrorTip("POS厂商与固件厂商不对应！");
        }


        map.clear();
        map.put("file_id",firmVersion);
        map.put("pos_id",posId);
        List list = busPosVersionService.selectByMap(map);
        if(!list.isEmpty()){
            return new ErrorTip("POS-ID对应的固件版本已存在！");
        }
        /*
        //posId(posId)
        map = new HashMap();
        map.put("firm_version", firmVersion);
        List<CcBusPosFirmware>  ccBusPosFirmwares = busPosFirmwareService.selectByMap(map);
        ccBusPosFirmwares = busPosFirmwareService.selectByMap(map);
        //    CcBusPosFirmware busPosFirmware = ccBusPosFirmwares.get(0);
        */

        CcBusPosVersion busPosVersion = new CcBusPosVersion();
        busPosVersion.setFirmVersion(ccBusPosFirmware.getFirmVersion());
        busPosVersion.setFileId(ccBusPosFirmware.getId());
        busPosVersion.setContent(content);
        busPosVersion.setPath("");
        busPosVersion.setType(type);
        busPosVersion.setUpgradeType(upgradeType);
        busPosVersion.setLineId(lineId);
        busPosVersion.setPosId(Long.parseLong(posId,16)+"");
        busPosVersion.setUpdateTime(DateUtil.getAllMsTime());
        busPosVersion.setCreateTime(DateUtil.getAllMsTime());
        busPosVersion.setDownStartTime(downStartTime.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
        busPosVersion.setDownEndTime(downEndTime.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
        busPosVersion.setInstallStartTime(installStartTime.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
        busPosVersion.setInstallEndTime(installEndTime.replaceAll("-","").replaceAll(":","").replaceAll(" ",""));
        boolean dbresult = busPosVersionService.insert(busPosVersion);

        if(dbresult) {
            return SUCCESS_TIP;
        }
        return new ErrorTip("新增公交POS版本失败");
    }


    /**
     * 删除公交POS固件版本
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busPosVersionId) {
        CcBusPosVersion busPosVersion = busPosVersionService.selectById(busPosVersionId);
        if(busPosVersionService.deletePosVersion(busPosVersion)){
            return SUCCESS_TIP;
        }else{
            return new ErrorTip("删除公交POS版本失败");
        }
    }

    /**
     * 修改公交POS固件版本
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcBusPosVersion busPosVersion) {
        busPosVersionService.updateById(busPosVersion);
        return SUCCESS_TIP;
    }

    /**
     * 公交POS固件版本详情
     */
    @RequestMapping(value = "/detail/{busPosVersionId}")
    @ResponseBody
    public Object detail(@PathVariable("busPosVersionId") Integer busPosVersionId) {
        return busPosVersionService.selectById(busPosVersionId);
    }
}
