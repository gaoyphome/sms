package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.common.persistence.model.CcBusPosFirmware;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.pos.service.IBusPosFirmwareService;
import com.bmac.ffan.modular.pos.warpper.PosVersionWarpper;
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
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.pos.dao.PosVersionDao;
import com.bmac.ffan.common.persistence.model.CcPosVersion;
import com.bmac.ffan.modular.pos.service.IPosVersionService;
import org.springframework.web.multipart.MultipartFile;

/**
 * POS固件版本控制器
 *
 * @author 工具生成
 * @Date 2017-11-24 15:21:00
 */
@Controller
@RequestMapping("/posVersion")
public class PosVersionController extends BaseController {

    private String PREFIX = "/pos/posVersion/";

    @Autowired
    private IPosVersionService posVersionService;

	@Resource
	PosVersionDao posVersionDao;

	@Resource
    IBusPosFirmwareService busPosFirmwareService;
    /**
     * 跳转到POS固件版本首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "posVersion.html";
    }

    /**
     * 跳转到添加POS固件版本
     */
    @RequestMapping("/posVersion_add")
    public String posVersionAdd() {
        return PREFIX + "posVersion_add.html";
    }

    /**
     * 跳转到修改POS固件版本
     */
    @RequestMapping("/posVersion_update/{posVersionId}")
    public String posVersionUpdate(@PathVariable Integer posVersionId, Model model) {
        CcPosVersion posVersion = posVersionService.selectById(posVersionId);
        model.addAttribute("posVersion",posVersion);
        LogObjectHolder.me().set(posVersion);
        return PREFIX + "posVersion_edit.html";
    }

    /**
     * 跳转到修改POS固件版本
     */
    @RequestMapping("/posVersion_detail/{posVersionId}")
    public String posVersionDetail(@PathVariable Integer posVersionId, Model model) {
        CcPosVersion posVersion = posVersionService.selectById(posVersionId);
        model.addAttribute("posVersion",posVersion);
        LogObjectHolder.me().set(posVersion);
        return PREFIX + "posVersion_detail.html";
    }
    /**
     * 获取POS固件版本列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String firmVersion,
    @RequestParam(required = false) String mchntId,
    @RequestParam(required = false) String posId
    
    ) {
        //return posVersionService.selectList(null);
        
        Page<CcPosVersion> page = new PageFactory<CcPosVersion>().defaultPage();
		 List<Map<String, Object>> result = posVersionDao.selectPosVersions(page, 
   		 firmVersion,
   		 mchntId,
   		 posId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcPosVersion>) new PosVersionWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增POS固件版本
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @Transactional(readOnly = false)
    public Object add(@RequestParam("filecontent") MultipartFile fileField,
                      @RequestParam("firmVersion") String firmVersion,
                      @RequestParam("fileId") Integer fileId,
                      @RequestParam("content") String content,
                      @RequestParam("path") String path,
                      @RequestParam("type") String type,
                      @RequestParam("upgradeType") String upgradeType,
                      @RequestParam("mchntId") String mchntId,
                      @RequestParam("posId") String posId) throws Exception {
        System.out.println("文件长度："+fileField.getSize());
        long size = fileField.getSize();
        InputStream inputStream = fileField.getInputStream();
        String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        CcPosVersion posVersion = new CcPosVersion();
        posVersion.setFirmVersion(firmVersion);
        posVersion.setFileId(fileId);
        posVersion.setContent(content);
        posVersion.setPath(path);
        posVersion.setType(type);
        posVersion.setUpgradeType(upgradeType);
        posVersion.setMchntId(mchntId);
        posVersion.setPosId(posId);
        posVersion.setFilesize(new Long(result.length()/2).intValue());
        posVersion.setUpdateTime(DateUtil.getAllMsTime());
        posVersion.setCreateTime(DateUtil.getAllMsTime());
        boolean dbresult = posVersionService.insert(posVersion);
        if (dbresult){
            CcBusPosFirmware bpf = new CcBusPosFirmware();
            bpf.setFirmVersion(firmVersion);
            bpf.setContent(result);
            dbresult = busPosFirmwareService.insert(bpf);
            if(dbresult){
                return SUCCESS_TIP;
            }
        }
        throw new Exception("上传POS固件版本失败！");
    }

    /**
     * 删除POS固件版本
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer posVersionId) {
        posVersionService.deleteById(posVersionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改POS固件版本
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcPosVersion posVersion) {
        posVersion.setUpdateTime(DateUtil.getAllMsTime());
        posVersionService.updateById(posVersion);
        return SUCCESS_TIP;
    }

    /**
     * POS固件版本详情
     */
    @RequestMapping(value = "/detail/{posVersionId}")
    @ResponseBody
    public Object detail(@PathVariable("posVersionId") Integer posVersionId) {
        return posVersionService.selectById(posVersionId);
    }
}
