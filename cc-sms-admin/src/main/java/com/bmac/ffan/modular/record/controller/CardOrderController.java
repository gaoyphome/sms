package com.bmac.ffan.modular.record.controller;

import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideRecord;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.modular.record.warpper.CardOrderWarpper;
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
import com.bmac.ffan.modular.record.dao.CardOrderDao;
import com.bmac.ffan.common.persistence.model.CcCardOrder;
import com.bmac.ffan.modular.record.service.ICardOrderService;

/**
 * 实体卡刷卡交易 控制器
 *
 * @author 工具生成
 * @Date 2017-12-04 09:28:56
 */
@Controller
@RequestMapping("/cardOrder")
public class CardOrderController extends BaseController {

    private String PREFIX = "/record/cardOrder/";

    @Autowired
    private ICardOrderService cardOrderService;

	@Resource
	CardOrderDao cardOrderDao;
	
    /**
     * 跳转到实体卡刷卡交易 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cardOrder.html";
    }

    /**
     * 跳转到添加实体卡刷卡交易
     */
    @RequestMapping("/cardOrder_add")
    public String cardOrderAdd() {
        return PREFIX + "cardOrder_add.html";
    }

    /**
     * 跳转到修改实体卡刷卡交易
     */
    @RequestMapping("/cardOrder_update/{cardOrderId}")
    public String cardOrderUpdate(@PathVariable Integer cardOrderId, Model model) {
        CcCardOrder cardOrder = cardOrderService.selectById(cardOrderId);
        model.addAttribute("cardOrder",cardOrder);
        LogObjectHolder.me().set(cardOrder);
        return PREFIX + "cardOrder_edit.html";
    }
    /**
     * 跳转到二维码乘车记录详情
     */
    @RequestMapping("/cardOrder_detail/{pltSsn}")
    public String qrcodeRideRecordDetail(@PathVariable String pltSsn, Model model) {
        Map map = new HashMap();
        map.put("plt_ssn",pltSsn);
        List<CcCardOrder> list = cardOrderService.selectByMap(map);
        CcCardOrder cardOrder = list.get(0);
        model.addAttribute("cardOrder",cardOrder);
        LogObjectHolder.me().set(cardOrder);
        return PREFIX + "cardOrder_detail.html";
    }
    /**
     * 获取实体卡刷卡交易 列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String startTermTransDate,
    @RequestParam(required = false) String endTermTransTime,
    @RequestParam(required = false) String cardno,
    @RequestParam(required = false) String orderNo,
    @RequestParam(required = false) String markLineId,
    @RequestParam(required = false) String posId,
    @RequestParam(required = false) String downLineId
    
    ) {
        //return cardOrderService.selectList(null);
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        if(startTermTransDate != null){
            startTermTransDate = startTermTransDate.replace("-","");
        }
        if(endTermTransTime != null){
            endTermTransTime = endTermTransTime.replace("-","");
        }
        Page<CcCardOrder> page = new PageFactory<CcCardOrder>().defaultPage();
		 List<Map<String, Object>> result = cardOrderDao.selectCardOrders(page,
                 ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
                 startTermTransDate,
                 endTermTransTime,
                 cardno,
                 orderNo,
                 markLineId,
                 posId,
                 downLineId,
                 page.getOrderByField(), page.isAsc());
                 page.setRecords((List<CcCardOrder>) new CardOrderWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增实体卡刷卡交易
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcCardOrder cardOrder) {
        cardOrderService.insert(cardOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除实体卡刷卡交易
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cardOrderId) {
        cardOrderService.deleteById(cardOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改实体卡刷卡交易
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcCardOrder cardOrder) {
        cardOrderService.updateById(cardOrder);
        return SUCCESS_TIP;
    }

    /**
     * 实体卡刷卡交易 详情
     */
    @RequestMapping(value = "/detail/{cardOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("cardOrderId") Integer cardOrderId) {
        return cardOrderService.selectById(cardOrderId);
    }
}
