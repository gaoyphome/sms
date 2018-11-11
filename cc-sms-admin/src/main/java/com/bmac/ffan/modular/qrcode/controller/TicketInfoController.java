package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.qrcode.warpper.TicketInfoWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.TicketInfoDao;
import com.bmac.ffan.common.persistence.model.CcTicketInfo;
import com.bmac.ffan.modular.qrcode.service.ITicketInfoService;

/**
 * 濂楃エ淇℃伅控制器
 *
 * @author 工具生成
 * @Date 2017-11-27 09:12:24
 */
@Controller
@RequestMapping("/ticketInfo")
public class TicketInfoController extends BaseController {

    private String PREFIX = "/qrcode/ticketInfo/";

    @Autowired
    private ITicketInfoService ticketInfoService;

	@Resource
	TicketInfoDao ticketInfoDao;
	
    /**
     * 跳转到濂楃エ淇℃伅首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ticketInfo.html";
    }

    /**
     * 跳转到添加濂楃エ淇℃伅
     */
    @RequestMapping("/ticketInfo_add")
    public String ticketInfoAdd() {
        return PREFIX + "ticketInfo_add.html";
    }

    /**
     * 跳转到修改濂楃エ淇℃伅
     */
    @RequestMapping("/ticketInfo_update/{ticketInfoId}")
    public String ticketInfoUpdate(@PathVariable Integer ticketInfoId, Model model) {
        CcTicketInfo ticketInfo = ticketInfoService.selectById(ticketInfoId);
        model.addAttribute("ticketInfo",ticketInfo);
        LogObjectHolder.me().set(ticketInfo);
        return PREFIX + "ticketInfo_edit.html";
    }

    /**
     * 获取濂楃エ淇℃伅列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String ticketId,
    @RequestParam(required = false) String ticketName,
    @RequestParam(required = false) String userId
    
    ) {
        //return ticketInfoService.selectList(null);
        
        Page<CcTicketInfo> page = new PageFactory<CcTicketInfo>().defaultPage();
		 List<Map<String, Object>> result = ticketInfoDao.selectTicketInfos(page, 
   		 ticketId,
   		 ticketName,
   		 userId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcTicketInfo>) new TicketInfoWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增濂楃エ淇℃伅
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcTicketInfo ticketInfo) {
        ticketInfoService.insert(ticketInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除濂楃エ淇℃伅
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ticketInfoId) {
        ticketInfoService.deleteById(ticketInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改濂楃エ淇℃伅
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcTicketInfo ticketInfo) {
        ticketInfoService.updateById(ticketInfo);
        return SUCCESS_TIP;
    }

    /**
     * 濂楃エ淇℃伅详情
     */
    @RequestMapping(value = "/detail/{ticketInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("ticketInfoId") Integer ticketInfoId) {
        return ticketInfoService.selectById(ticketInfoId);
    }
}
