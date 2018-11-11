package com.bmac.ffan.modular.basebusiness.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmac.ffan.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.bmac.ffan.modular.basebusiness.dao.SysConfigDao;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.bmac.ffan.common.persistence.model.CcSysConfig;
import com.bmac.ffan.modular.basebusiness.service.ISysConfigService;
import com.bmac.ffan.modular.basebusiness.warpper.SysConfigWarpper;

/**
 * 系统参数管理控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 11:14:00
 */
@Controller
@RequestMapping("/sysConfig")
public class SysConfigController extends BaseController {

	private String PREFIX = "/basebusiness/sysConfig/";

	@Autowired
	private ISysConfigService sysConfigService;

	@Resource
	SysConfigDao sysConfigDao;

	/**
	 * 跳转到系统参数管理首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "sysConfig.html";
	}

	/**
	 * 跳转到添加系统参数管理
	 */
	@RequestMapping("/sysConfig_add")
	public String sysConfigAdd() {
		return PREFIX + "sysConfig_add.html";
	}

	/**
	 * 跳转到修改系统参数管理
	 */
	@RequestMapping("/sysConfig_update/{sysConfigId}")
	public String sysConfigUpdate(@PathVariable Integer sysConfigId, Model model) {
		CcSysConfig sysConfig = sysConfigService.selectById(sysConfigId);
		model.addAttribute("sysConfig", sysConfig);
		LogObjectHolder.me().set(sysConfig);
		return PREFIX + "sysConfig_edit.html";
	}

	/**
	 * 获取系统参数管理列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String category,
			@RequestParam(required = false) String paramKey) {

		Page<CcSysConfig> page = new PageFactory<CcSysConfig>().defaultPage();
		List<Map<String, Object>> result = sysConfigDao.selectSysConfigs(page, category, paramKey,
				page.getOrderByField(), page.isAsc());
		page.setRecords((List<CcSysConfig>) new SysConfigWarpper(result).warp());
		return super.packForBT(page);

	}

	/**
	 * 新增系统参数管理
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(CcSysConfig sysConfig) {
		sysConfigService.insert(sysConfig);
		return SUCCESS_TIP;
	}

	/**
	 * 删除系统参数管理
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Long sysConfigId) {
		sysConfigService.deleteById(sysConfigId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改系统参数管理
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(CcSysConfig sysConfig) {
		sysConfigService.updateById(sysConfig);
		return SUCCESS_TIP;
	}

	/**
	 * 系统参数管理详情
	 */
	@RequestMapping(value = "/detail/{sysConfigId}")
	@ResponseBody
	public Object detail(@PathVariable("sysConfigId") Integer sysConfigId) {
		return sysConfigService.selectById(sysConfigId);
	}
}
