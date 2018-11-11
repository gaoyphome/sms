package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.pos.warpper.NetworkMchntAllWarpper;
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
import com.bmac.ffan.modular.pos.dao.NetworkMchntAllDao;
import com.bmac.ffan.common.persistence.model.CcNetworkMchntAll;
import com.bmac.ffan.modular.pos.service.INetworkMchntAllService;

/**
 * 商户信息控制器
 *
 * @author 工具生成
 * @Date 2017-11-24 16:33:10
 */
@Controller
@RequestMapping("/networkMchntAll")
public class NetworkMchntAllController extends BaseController {

    private String PREFIX = "/pos/networkMchntAll/";

    @Autowired
    private INetworkMchntAllService networkMchntAllService;

	@Resource
	NetworkMchntAllDao networkMchntAllDao;
	
    /**
     * 跳转到商户信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "networkMchntAll.html";
    }

    /**
     * 跳转到添加商户信息
     */
    @RequestMapping("/networkMchntAll_add")
    public String networkMchntAllAdd() {
        return PREFIX + "networkMchntAll_add.html";
    }

    /**
     * 跳转到修改商户信息
     */
    @RequestMapping("/networkMchntAll_update/{networkMchntAllId}")
    public String networkMchntAllUpdate(@PathVariable Integer networkMchntAllId, Model model) {
        CcNetworkMchntAll networkMchntAll = networkMchntAllService.selectById(networkMchntAllId);
        model.addAttribute("networkMchntAll",networkMchntAll);
        LogObjectHolder.me().set(networkMchntAll);
        return PREFIX + "networkMchntAll_edit.html";
    }
    /**
     * 跳转到修改商户信息
     */
    @RequestMapping("/networkMchntAll_detail/{networkMchntAllId}")
    public String networkMchntAllDetail(@PathVariable Integer networkMchntAllId, Model model) {
        CcNetworkMchntAll networkMchntAll = networkMchntAllService.selectById(networkMchntAllId);
        model.addAttribute("networkMchntAll",networkMchntAll);
        LogObjectHolder.me().set(networkMchntAll);
        return PREFIX + "networkMchntAll_detail.html";
    }
    /**
     * 获取商户信息列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String mchntId,
    @RequestParam(required = false) String mchntname,
    @RequestParam(required = false) String contacts
    
    ) {
        //return networkMchntAllService.selectList(null);
        
        Page<CcNetworkMchntAll> page = new PageFactory<CcNetworkMchntAll>().defaultPage();
		 List<Map<String, Object>> result = networkMchntAllDao.selectNetworkMchntAlls(page, 
   		 mchntId,
   		 mchntname,
   		 contacts,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcNetworkMchntAll>) new NetworkMchntAllWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增商户信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcNetworkMchntAll networkMchntAll) {
        networkMchntAll.setUpdateTime(DateUtil.getAllMsTime());
        networkMchntAll.setCreateTime(DateUtil.getAllMsTime());
        networkMchntAllService.insert(networkMchntAll);
        return SUCCESS_TIP;
    }

    /**
     * 删除商户信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer networkMchntAllId) {
        networkMchntAllService.deleteById(networkMchntAllId);
        return SUCCESS_TIP;
    }

    /**
     * 修改商户信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcNetworkMchntAll networkMchntAll) {
        networkMchntAll.setUpdateTime(DateUtil.getAllMsTime());
        networkMchntAllService.updateById(networkMchntAll);
        return SUCCESS_TIP;
    }

    /**
     * 商户信息详情
     */
    @RequestMapping(value = "/detail/{networkMchntAllId}")
    @ResponseBody
    public Object detail(@PathVariable("networkMchntAllId") Integer networkMchntAllId) {
        return networkMchntAllService.selectById(networkMchntAllId);
    }
}
