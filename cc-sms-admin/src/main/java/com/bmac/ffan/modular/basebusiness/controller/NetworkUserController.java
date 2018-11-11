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
import com.bmac.ffan.modular.basebusiness.dao.NetworkUserDao;
import com.bmac.ffan.common.persistence.model.CcNetworkUser;
import com.bmac.ffan.modular.basebusiness.service.INetworkUserService;
import com.bmac.ffan.modular.basebusiness.warpper.NetworkUserWarpper;

/**
 * 用户信息控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 17:10:13
 */
@Controller
@RequestMapping("/networkUser")
public class NetworkUserController extends BaseController {

    private String PREFIX = "/basebusiness/networkUser/";

    @Autowired
    private INetworkUserService networkUserService;

	@Resource
	NetworkUserDao networkUserDao;
	
    /**
     * 跳转到用户信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "networkUser.html";
    }

    /**
     * 跳转到添加用户信息
     */
    @RequestMapping("/networkUser_add")
    public String networkUserAdd() {
        return PREFIX + "networkUser_add.html";
    }

    /**
     * 跳转到修改用户信息
     */
    @RequestMapping("/networkUser_update/{networkUserId}")
    public String networkUserUpdate(@PathVariable Integer networkUserId, Model model) {
        CcNetworkUser networkUser = networkUserService.selectById(networkUserId);
        model.addAttribute("networkUser",networkUser);
        LogObjectHolder.me().set(networkUser);
        return PREFIX + "networkUser_edit.html";
    }

    /**
     * 获取用户信息列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String username,
    @RequestParam(required = false) String mobile
    
    ) {
        //return networkUserService.selectList(null);
        
        Page<CcNetworkUser> page = new PageFactory<CcNetworkUser>().defaultPage();
		 List<Map<String, Object>> result = networkUserDao.selectNetworkUsers(page, 
   		 username,
   		 mobile,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcNetworkUser>) new NetworkUserWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增用户信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcNetworkUser networkUser) {
    	networkUser.setUpdateTime(DateUtil.getAllMsTime());
    	networkUser.setCreateTime(DateUtil.getAllMsTime());
        networkUserService.insert(networkUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer networkUserId) {
        networkUserService.deleteById(networkUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcNetworkUser networkUser) {
    	networkUser.setUpdateTime(DateUtil.getAllMsTime());
        networkUserService.updateById(networkUser);
        return SUCCESS_TIP;
    }

    /**
     * 用户信息详情
     */
    @RequestMapping(value = "/detail/{networkUserId}")
    @ResponseBody
    public Object detail(@PathVariable("networkUserId") Integer networkUserId) {
        return networkUserService.selectById(networkUserId);
    }
}
