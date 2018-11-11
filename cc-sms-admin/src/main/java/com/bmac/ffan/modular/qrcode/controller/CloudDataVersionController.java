package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.qrcode.warpper.CloudDataVersionWarpper;
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
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.qrcode.dao.CloudDataVersionDao;
import com.bmac.ffan.common.persistence.model.CloudDataVersion;
import com.bmac.ffan.modular.qrcode.service.ICloudDataVersionService;

/**
 * 云卡数据版本控制器
 *
 * @author 工具生成
 * @Date 2017-11-27 11:19:37
 */
@Controller
@RequestMapping("/cloudDataVersion")
public class CloudDataVersionController extends BaseController {

    private String PREFIX = "/qrcode/cloudDataVersion/";

    @Autowired
    private ICloudDataVersionService cloudDataVersionService;

	@Resource
	CloudDataVersionDao cloudDataVersionDao;
	
    /**
     * 跳转到云卡数据版本首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cloudDataVersion.html";
    }

    /**
     * 跳转到添加云卡数据版本
     */
    @RequestMapping("/cloudDataVersion_add")
    public String cloudDataVersionAdd() {
        return PREFIX + "cloudDataVersion_add.html";
    }

    /**
     * 跳转到修改云卡数据版本
     */
    @RequestMapping("/cloudDataVersion_update/{cloudDataVersionId}")
    public String cloudDataVersionUpdate(@PathVariable Integer cloudDataVersionId, Model model) {
        CloudDataVersion cloudDataVersion = cloudDataVersionService.selectById(cloudDataVersionId);
        model.addAttribute("cloudDataVersion",cloudDataVersion);
        LogObjectHolder.me().set(cloudDataVersion);
        return PREFIX + "cloudDataVersion_edit.html";
    }

    /**
     * 获取云卡数据版本列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    
    ) {
        //return cloudDataVersionService.selectList(null);
        
        Page<CloudDataVersion> page = new PageFactory<CloudDataVersion>().defaultPage();
		 List<Map<String, Object>> result = cloudDataVersionDao.selectCloudDataVersions(page, 
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CloudDataVersion>) new CloudDataVersionWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增云卡数据版本
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CloudDataVersion cloudDataVersion) {
        cloudDataVersionService.insert(cloudDataVersion);
        return SUCCESS_TIP;
    }

    /**
     * 删除云卡数据版本
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cloudDataVersionId) {
        cloudDataVersionService.deleteById(cloudDataVersionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改云卡数据版本
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CloudDataVersion cloudDataVersion) {
        cloudDataVersionService.updateById(cloudDataVersion);
        return SUCCESS_TIP;
    }

    /**
     * 云卡数据版本详情
     */
    @RequestMapping(value = "/detail/{cloudDataVersionId}")
    @ResponseBody
    public Object detail(@PathVariable("cloudDataVersionId") Integer cloudDataVersionId) {
        return cloudDataVersionService.selectById(cloudDataVersionId);
    }
}
