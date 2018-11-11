package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.qrcode.warpper.TicketDetailWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.TicketDetailDao;
import com.bmac.ffan.common.persistence.model.CcTicketDetail;
import com.bmac.ffan.modular.qrcode.service.ITicketDetailService;

/**
 * 濂楃エ璇︽儏控制器
 *
 * @author 工具生成
 * @Date 2017-11-27 09:45:48
 */
@Controller
@RequestMapping("/ticketDetail")
public class TicketDetailController extends BaseController {

    private String PREFIX = "/qrcode/ticketDetail/";

    @Autowired
    private ITicketDetailService ticketDetailService;

	@Resource
	TicketDetailDao ticketDetailDao;
	
    /**
     * 跳转到濂楃エ璇︽儏首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ticketDetail.html";
    }

    /**
     * 跳转到添加濂楃エ璇︽儏
     */
    @RequestMapping("/ticketDetail_add")
    public String ticketDetailAdd() {
        return PREFIX + "ticketDetail_add.html";
    }

    /**
     * 跳转到修改濂楃エ璇︽儏
     */
    @RequestMapping("/ticketDetail_update/{ticketDetailId}")
    public String ticketDetailUpdate(@PathVariable Integer ticketDetailId, Model model) {
        CcTicketDetail ticketDetail = ticketDetailService.selectById(ticketDetailId);
        model.addAttribute("ticketDetail",ticketDetail);
        LogObjectHolder.me().set(ticketDetail);
        return PREFIX + "ticketDetail_edit.html";
    }

    /**
     * 获取濂楃エ璇︽儏列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String ticketId,
    @RequestParam(required = false) String agentId,
    @RequestParam(required = false) String mchntId
    
    ) {
        //return ticketDetailService.selectList(null);
        
        Page<CcTicketDetail> page = new PageFactory<CcTicketDetail>().defaultPage();
		 List<Map<String, Object>> result = ticketDetailDao.selectTicketDetails(page, 
   		 ticketId,
   		 agentId,
   		 mchntId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcTicketDetail>) new TicketDetailWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增濂楃エ璇︽儏
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcTicketDetail ticketDetail) {
        ticketDetailService.insert(ticketDetail);
        return SUCCESS_TIP;
    }

    /**
     * 删除濂楃エ璇︽儏
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ticketDetailId) {
        ticketDetailService.deleteById(ticketDetailId);
        return SUCCESS_TIP;
    }

    /**
     * 修改濂楃エ璇︽儏
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcTicketDetail ticketDetail) {
        ticketDetailService.updateById(ticketDetail);
        return SUCCESS_TIP;
    }

    /**
     * 濂楃エ璇︽儏详情
     */
    @RequestMapping(value = "/detail/{ticketDetailId}")
    @ResponseBody
    public Object detail(@PathVariable("ticketDetailId") Integer ticketDetailId) {
        return ticketDetailService.selectById(ticketDetailId);
    }
}
