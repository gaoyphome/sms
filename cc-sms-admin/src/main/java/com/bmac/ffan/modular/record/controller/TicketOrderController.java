package com.bmac.ffan.modular.record.controller;

import com.bmac.ffan.common.persistence.model.CcCardOrder;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.record.warpper.TicketOrderWarpper;
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
import com.bmac.ffan.modular.record.dao.TicketOrderDao;
import com.bmac.ffan.common.persistence.model.CcTicketOrder;
import com.bmac.ffan.modular.record.service.ITicketOrderService;

/**
 * 套票订单 控制器
 *
 * @author 工具生成
 * @Date 2017-12-04 11:03:32
 */
@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController extends BaseController {

    private String PREFIX = "/record/ticketOrder/";

    @Autowired
    private ITicketOrderService ticketOrderService;

	@Resource
	TicketOrderDao ticketOrderDao;
	
    /**
     * 跳转到套票订单 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ticketOrder.html";
    }

    /**
     * 跳转到添加套票订单
     */
    @RequestMapping("/ticketOrder_add")
    public String ticketOrderAdd() {
        return PREFIX + "ticketOrder_add.html";
    }

    /**
     * 跳转到修改套票订单
     */
    @RequestMapping("/ticketOrder_update/{ticketOrderId}")
    public String ticketOrderUpdate(@PathVariable Integer ticketOrderId, Model model) {
        CcTicketOrder ticketOrder = ticketOrderService.selectById(ticketOrderId);
        model.addAttribute("ticketOrder",ticketOrder);
        LogObjectHolder.me().set(ticketOrder);
        return PREFIX + "ticketOrder_edit.html";
    }

    @RequestMapping("/ticketOrder_detail/{pltSsn}")
    public String ticketOrderDetail(@PathVariable String pltSsn, Model model) {
        Map map = new HashMap();
        map.put("plt_ssn",pltSsn);
        List<CcTicketOrder> list = ticketOrderService.selectByMap(map);
        CcTicketOrder ticketOrder = list.get(0);
        model.addAttribute("ticketOrder",ticketOrder);
        LogObjectHolder.me().set(ticketOrder);
        return PREFIX + "ticketOrder_detail.html";
    }

    /**
     * 获取套票订单 列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String termTransDate,
    @RequestParam(required = false) String termTransTime,
    @RequestParam(required = false) String cardno,
    @RequestParam(required = false) String orderNo,
    @RequestParam(required = false) String termId,
    @RequestParam(required = false) String productCode
    
    ) {
        //return ticketOrderService.selectList(null);
        
        Page<CcTicketOrder> page = new PageFactory<CcTicketOrder>().defaultPage();
		 List<Map<String, Object>> result = ticketOrderDao.selectTicketOrders(page, 
   		 termTransDate,
   		 termTransTime,
   		 cardno,
   		 orderNo,
   		 termId,
   		 productCode,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcTicketOrder>) new TicketOrderWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增套票订单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcTicketOrder ticketOrder) {
        ticketOrderService.insert(ticketOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除套票订单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ticketOrderId) {
        ticketOrderService.deleteById(ticketOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改套票订单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcTicketOrder ticketOrder) {
        ticketOrderService.updateById(ticketOrder);
        return SUCCESS_TIP;
    }

    /**
     * 套票订单 详情
     */
    @RequestMapping(value = "/detail/{ticketOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("ticketOrderId") Integer ticketOrderId) {
        return ticketOrderService.selectById(ticketOrderId);
    }
}
