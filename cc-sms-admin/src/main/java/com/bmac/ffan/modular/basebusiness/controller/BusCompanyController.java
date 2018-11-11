package com.bmac.ffan.modular.basebusiness.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.core.node.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.bmac.ffan.common.persistence.model.CcBusCompany;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.log.LogObjectHolder;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.dao.BusCompanyDao;
import com.bmac.ffan.modular.basebusiness.service.IBusCompanyService;
import com.bmac.ffan.modular.basebusiness.warpper.BusCompanyWarpper;

/**
 * 运营公司控制器
 *
 * @author xuzhanfu
 * @Date 2017-11-15 20:43:33
 */
@Controller
@RequestMapping("/busCompany")
public class BusCompanyController extends BaseController {

	private String PREFIX = "/basebusiness/busCompany/";

	@Autowired
	private IBusCompanyService busCompanyService;

	@Resource
	BusCompanyDao busCompanyDao;

	/**
	 * 跳转到运营公司首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "busCompany.html";
	}

	/**
	 * 跳转到添加运营公司
	 */
	@RequestMapping("/busCompany_add")
	public String busCompanyAdd() {
		//多数据源测试
//		busCompanyService.testdbs();
//		busCompanyService.testdbs1();
		return PREFIX + "busCompany_add.html";
	}

	/**
	 * 跳转到修改运营公司
	 */
	@RequestMapping("/busCompany_edit/{busCompanyId}")
	public String busCompanyUpdate(@PathVariable Integer busCompanyId, Model model) {
		CcBusCompany busCompany = busCompanyService.selectById(busCompanyId);
		model.addAttribute("busCompany", busCompany);
		LogObjectHolder.me().set(busCompany);
		return PREFIX + "busCompany_edit.html";
	}

	/**
	 * 获取运营公司列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) Integer companyId,
			@RequestParam(required = false) String companyName) {
		 Page<CcBusCompany> page = new PageFactory<CcBusCompany>().defaultPage();
		 List<Map<String, Object>> result = busCompanyDao.selectBusCompanys(page, companyId, companyName, page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcBusCompany>) new BusCompanyWarpper(result).warp());
	     return super.packForBT(page);
	}

	/**
	 * 新增运营公司
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(CcBusCompany busCompany) {
		busCompany.setUpdateTime(DateUtil.getAllMsTime());
		busCompany.setCreateTime(DateUtil.getAllMsTime());
		busCompanyService.insert(busCompany);
		return SUCCESS_TIP;
	}

	/**
	 * 删除运营公司
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer busCompanyId) {
		busCompanyService.deleteById(busCompanyId);
		return SUCCESS_TIP;
	}

	/**
	 * 获取部门的tree列表
	 */
	@RequestMapping(value = "/tree")
	@ResponseBody
	public List<ZTreeNode> tree() {
		List<ZTreeNode> tree = this.busCompanyDao.tree();

		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(true);
		zTreeNode.setId(Integer.parseInt(SysConstant.DEPTID_ALL_PRIVILEGES));
		zTreeNode.setName("北京市政交通一卡通");
		zTreeNode.setOpen(true);
		zTreeNode.setpId(Integer.parseInt(SysConstant.DEPTID_ALL_PRIVILEGES));
		tree.add(0,zTreeNode);
		//tree.add(ZTreeNode.createParent());
		return tree;
	}
	/**
	 * 修改运营公司
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(CcBusCompany busCompany) {
		busCompany.setUpdateTime(DateUtil.getAllMsTime());
		busCompanyService.updateById(busCompany);
		return SUCCESS_TIP;
	}

	/**
	 * 运营公司详情
	 */
	@RequestMapping(value = "/detail/{busCompanyId}")
	@ResponseBody
	public Object detail(@PathVariable("busCompanyId") Integer busCompanyId) {
		return busCompanyService.selectById(busCompanyId);
	}
}
