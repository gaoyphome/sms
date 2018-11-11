package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.common.persistence.model.CcBusPos;
import com.bmac.ffan.common.persistence.model.CcPosFileBlacklist;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.modular.basebusiness.service.ILineInfoService;
import com.bmac.ffan.modular.pos.dao.BusPosDao;
import com.bmac.ffan.modular.pos.service.IBusPosService;
import com.bmac.ffan.modular.pos.service.IPosFileBlacklistService;
import com.bmac.ffan.modular.pos.warpper.BlacklistVersionWarpper;
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
import com.bmac.ffan.modular.pos.dao.BlacklistVersionDao;
import com.bmac.ffan.common.persistence.model.CcBlacklistVersion;
import com.bmac.ffan.modular.pos.service.IBlacklistVersionService;

/**
 * POS黑名升级管理表控制器
 *
 * @author 工具生成
 * @Date 2018-01-29 17:08:49
 */
@Controller
@RequestMapping("/blacklistVersion")
public class BlacklistVersionController extends BaseController {

    private String PREFIX = "/pos/blacklistVersion/";

    @Autowired
    private IBlacklistVersionService blacklistVersionService;

	@Resource
	BlacklistVersionDao blacklistVersionDao;

    @Resource
    BusPosDao busPosDao;

    @Autowired
	IPosFileBlacklistService posFileBlacklistService;

    @Autowired
    ILineInfoService lineInfoService;

    @Autowired
    IBusPosService busPosService;
	
    /**
     * 跳转到POS黑名升级管理表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "blacklistVersion.html";
    }

    /**
     * 跳转到添加POS黑名升级管理表
     */
    @RequestMapping("/blacklistVersion_add")
    public String blacklistVersionAdd(Model model) {
        Map map = new HashMap();
        map.put("filename","1:/G1");
        List<CcPosFileBlacklist> listG = posFileBlacklistService.selectByMap(map);

        map = new HashMap();
        map.put("filename","1:/W1");
        List<CcPosFileBlacklist> listW = posFileBlacklistService.selectByMap(map);

        map = new HashMap();
        map.put("filename","1:/G6");
        List<CcPosFileBlacklist> listG6 = posFileBlacklistService.selectByMap(map);

        model.addAttribute("listG",listG);
        LogObjectHolder.me().set(listG);

        model.addAttribute("listW",listW);
        LogObjectHolder.me().set(listW);

        model.addAttribute("listG6",listG6);
        LogObjectHolder.me().set(listG6);

        return PREFIX + "blacklistVersion_add.html";
    }

    /**
     * 跳转到修改POS黑名升级管理表
     */
    @RequestMapping("/blacklistVersion_update/{blacklistVersionId}")
    public String blacklistVersionUpdate(@PathVariable Integer blacklistVersionId, Model model) {
        CcBlacklistVersion blacklistVersion = blacklistVersionService.selectById(blacklistVersionId);
        model.addAttribute("blacklistVersion",blacklistVersion);
        LogObjectHolder.me().set(blacklistVersion);
        return PREFIX + "blacklistVersion_edit.html";
    }

    /**
     * 获取POS黑名升级管理表列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String listVersion
    
    ) {
        //return blacklistVersionService.selectList(null);
        
        Page<CcBlacklistVersion> page = new PageFactory<CcBlacklistVersion>().defaultPage();
		 List<Map<String, Object>> result = blacklistVersionDao.selectBlacklistVersions(page, 
   		 listVersion,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcBlacklistVersion>) new BlacklistVersionWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增POS黑名升级管理表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcBlacklistVersion blacklistVersion) {
        int listVersion = Integer.parseInt(blacklistVersion.getListVersion());
        String filename = blacklistVersion.getFilename();

        Integer maxVersion_all = blacklistVersionDao.selectMaxVersion(filename,"0",null,null);

            if("0".equals(blacklistVersion.getType())){//所有POS机
                if(maxVersion_all == null){
                    Map map = new HashMap();
                    map.put("filename",filename);
                    map.put("version",listVersion);
                    List<CcPosFileBlacklist> posFileBlacklists = posFileBlacklistService.selectByMap(map);
                    blacklistVersion.setFileId(posFileBlacklists.get(0).getId());
                    blacklistVersionService.insert(blacklistVersion);
                    return SUCCESS_TIP;
                }
                if(listVersion <= maxVersion_all.intValue()){
                    return new ErrorTip("当前更新的白名单版本号小于等于平台最大版本号:"+maxVersion_all);
                }
            }else if("1".equals(blacklistVersion.getType())){//特定线路
                /**
                 * 确认线路是否存在
                 */
                Map map = new HashMap();
                map.put("line_id", blacklistVersion.getLineId());
                List lines = lineInfoService.selectByMap(map);
                if(lines.isEmpty()){
                    return new ErrorTip("当前线路不存在");
                }

                Integer maxVersion_line = blacklistVersionDao.selectMaxVersion(filename,"1",blacklistVersion.getLineId(),null);
                if(maxVersion_line == null){//如果为NULL，则说明该线路没有配置过白名单版本号，则需要其版本大于类型为ALL（type==0）的最大版本
                    if(maxVersion_all == null){
                        map.clear();
                        map.put("filename",filename);
                        map.put("version",listVersion);
                        List<CcPosFileBlacklist> posFileBlacklists = posFileBlacklistService.selectByMap(map);
                        blacklistVersion.setFileId(posFileBlacklists.get(0).getId());
                //        blacklistVersion.setPosId(Integer.valueOf(blacklistVersion.getPosId(),16)+"");
                        blacklistVersionService.insert(blacklistVersion);
                        return SUCCESS_TIP;
                    }else {
                        if (listVersion <= maxVersion_all) {
                            return new ErrorTip("当前线路更新的白名单版本号小于等于平台(ALL)最大版本号:" + maxVersion_all);
                        }
                    }
                }else{
                    if(listVersion <= maxVersion_line.intValue()){
                        return new ErrorTip("当前线路更新的白名单版本号小于等于平台(线路)最大版本号:"+maxVersion_all);
                    }
                }
            }else if("2".equals(blacklistVersion.getType())){//特定POS机
                /**
                 * 确认POS是否存在
                 */
                Map map = new HashMap();
                map.put("pos_id", Long.parseLong(blacklistVersion.getPosId(), 16)+"");
                List<CcBusPos> poss = busPosService.selectByMap(map);
                if(poss.isEmpty()){
                    return new ErrorTip("当前POS-ID不存在");
                }
                /**
                 * 查找POS所在线路ID
                 */
                String lineId = busPosDao.getLineIdByPosId(poss.get(0).getPosId());
                System.out.println("---------->"+lineId);
                Integer maxVersion_pos = blacklistVersionDao.selectMaxVersion(filename,"2",null,blacklistVersion.getPosId());
                if(maxVersion_pos == null){//如果为NULL,则说明该POS没有配置过白名单版本号，则需要其版本大于类型为线路(type==2)的最大版本
                    Integer maxVersion_line = blacklistVersionDao.selectMaxVersion(filename,"2",lineId,null);
                    if(maxVersion_line == null){//如果为NULL，则说明POS所在线路没有配置过白名单版本号，则需要其版本大于类型为线路（type==1）的最大版本
                        if(maxVersion_all == null){
                            map.clear();
                            map.put("filename",filename);
                            map.put("version",listVersion);
                            List<CcPosFileBlacklist> posFileBlacklists = posFileBlacklistService.selectByMap(map);
                            blacklistVersion.setFileId(posFileBlacklists.get(0).getId());
                            blacklistVersion.setPosId(Integer.valueOf(blacklistVersion.getPosId(),16)+"");
                            blacklistVersionService.insert(blacklistVersion);
                            return SUCCESS_TIP;
                        }else {
                            if (listVersion <= maxVersion_all.intValue()) {
                                return new ErrorTip("当前POS更新的白名单版本号小于等于平台(ALL)最大版本号:" + maxVersion_all);
                            }
                        }
                    }else{
                        if(listVersion <= maxVersion_line.intValue()){
                            return new ErrorTip("当前POS更新的白名单版本号小于等于平台(线路)最大版本号:"+maxVersion_all);
                        }
                    }
                }else{
                    if(listVersion <= maxVersion_pos.intValue()){
                        return new ErrorTip("当前POS更新的白名单版本号小于等于平台(POS)最大版本号:"+maxVersion_all);
                    }
                }

                blacklistVersion.setPosId(Integer.valueOf(blacklistVersion.getPosId(),16)+"");
            }

        /**
         * 设置文件索引ID
         */
        Map map = new HashMap();
        map.put("filename",filename);
        map.put("version",listVersion);
        List<CcPosFileBlacklist> posFileBlacklists = posFileBlacklistService.selectByMap(map);
        blacklistVersion.setFileId(posFileBlacklists.get(0).getId());

        blacklistVersionService.insert(blacklistVersion);
        return SUCCESS_TIP;
    }

    /**
     * 删除POS黑名升级管理表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer blacklistVersionId) {
        blacklistVersionService.deleteById(blacklistVersionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改POS黑名升级管理表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcBlacklistVersion blacklistVersion) {
        blacklistVersionService.updateById(blacklistVersion);
        return SUCCESS_TIP;
    }

    /**
     * POS黑名升级管理表详情
     */
    @RequestMapping(value = "/detail/{blacklistVersionId}")
    @ResponseBody
    public Object detail(@PathVariable("blacklistVersionId") Integer blacklistVersionId) {
        return blacklistVersionService.selectById(blacklistVersionId);
    }
}
