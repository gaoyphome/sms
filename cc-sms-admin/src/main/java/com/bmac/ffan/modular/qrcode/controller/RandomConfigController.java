package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.qrcode.warpper.RandomConfigWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.RandomConfigDao;
import com.bmac.ffan.common.persistence.model.CcRandomConfig;
import com.bmac.ffan.modular.qrcode.service.IRandomConfigService;

/**
 * 随机数索引控制器
 *
 * @author 工具生成
 * @Date 2017-11-27 10:17:05
 */
@Controller
@RequestMapping("/randomConfig")
public class RandomConfigController extends BaseController {

    private String PREFIX = "/qrcode/randomConfig/";

    @Autowired
    private IRandomConfigService randomConfigService;

	@Resource
	RandomConfigDao randomConfigDao;
	
    /**
     * 跳转到随机数索引首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "randomConfig.html";
    }

    /**
     * 跳转到添加随机数索引
     */
    @RequestMapping("/randomConfig_add")
    public String randomConfigAdd() {
        return PREFIX + "randomConfig_add.html";
    }

    /**
     * 跳转到修改随机数索引
     */
    @RequestMapping("/randomConfig_update/{randomConfigId}")
    public String randomConfigUpdate(@PathVariable Integer randomConfigId, Model model) {
        CcRandomConfig randomConfig = randomConfigService.selectById(randomConfigId);
        model.addAttribute("randomConfig",randomConfig);
        LogObjectHolder.me().set(randomConfig);
        return PREFIX + "randomConfig_edit.html";
    }

    /**
     * 获取随机数索引列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String currIndex
    
    ) {
        //return randomConfigService.selectList(null);
        
        Page<CcRandomConfig> page = new PageFactory<CcRandomConfig>().defaultPage();
		 List<Map<String, Object>> result = randomConfigDao.selectRandomConfigs(page, 
   		 currIndex,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcRandomConfig>) new RandomConfigWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增随机数索引
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcRandomConfig randomConfig) {
        randomConfig.setUpdateTime(DateUtil.getAllMsTime());
        randomConfigService.insert(randomConfig);
        return SUCCESS_TIP;
    }

    /**
     * 删除随机数索引
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long randomConfigId) {
        randomConfigService.deleteById(randomConfigId);
        return SUCCESS_TIP;
    }

    /**
     * 修改随机数索引
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcRandomConfig randomConfig) {
        randomConfig.setUpdateTime(DateUtil.getAllMsTime());
        randomConfigService.updateById(randomConfig);
        return SUCCESS_TIP;
    }

    /**
     * 随机数索引详情
     */
    @RequestMapping(value = "/detail/{randomConfigId}")
    @ResponseBody
    public Object detail(@PathVariable("randomConfigId") Long randomConfigId) {
        return randomConfigService.selectById(randomConfigId);
    }
}
