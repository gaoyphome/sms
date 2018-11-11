package com.bmac.ffan.modular.qrcode.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bmac.ffan.common.persistence.model.CcQrcodeDiscount;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.qrcode.warpper.QrcodeBlackWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.QrcodeBlackDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeBlack;
import com.bmac.ffan.modular.qrcode.service.IQrcodeBlackService;

/**
 * 二维码黑名单控制器
 *
 * @author 工具生成
 * @Date 2017-11-25 11:36:22
 */
@Controller
@RequestMapping("/qrcodeBlack")
public class QrcodeBlackController extends BaseController {

    private String PREFIX = "/qrcode/qrcodeBlack/";

    @Autowired
    private IQrcodeBlackService qrcodeBlackService;

	@Resource
	QrcodeBlackDao qrcodeBlackDao;
	
    /**
     * 跳转到二维码黑名单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeBlack.html";
    }

    /**
     * 跳转到添加二维码黑名单
     */
    @RequestMapping("/qrcodeBlack_add")
    public String qrcodeBlackAdd() {
        return PREFIX + "qrcodeBlack_add.html";
    }

    /**
     * 跳转到修改二维码黑名单
     */
    @RequestMapping("/qrcodeBlack_update/{tokenId}")
    public String qrcodeBlackUpdate(@PathVariable String tokenId, Model model) {
        Map map = new HashMap();
        map.put("token_id",tokenId);
        List<CcQrcodeBlack> list = qrcodeBlackService.selectByMap(map);
        CcQrcodeBlack qrcodeBlack = list.get(0);
        model.addAttribute("qrcodeBlack",qrcodeBlack);
        LogObjectHolder.me().set(qrcodeBlack);
        return PREFIX + "qrcodeBlack_edit.html";
    }

    /**
     * 获取二维码黑名单列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String tokenId,
    @RequestParam(required = false) String vsirBmacno
    
    ) {
        //return qrcodeBlackService.selectList(null);
        
        Page<CcQrcodeBlack> page = new PageFactory<CcQrcodeBlack>().defaultPage();
		 List<Map<String, Object>> result = qrcodeBlackDao.selectQrcodeBlacks(page, 
   		 tokenId,
   		 vsirBmacno,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcQrcodeBlack>) new QrcodeBlackWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增二维码黑名单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeBlack qrcodeBlack) {
        qrcodeBlack.setCreateTime(DateUtil.getAllMsTime());
        qrcodeBlackService.insert(qrcodeBlack);
        return SUCCESS_TIP;
    }

    /**
     * 删除二维码黑名单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String tokenId) {
        qrcodeBlackService.delete(new EntityWrapper<CcQrcodeBlack>().eq("token_id", tokenId));
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码黑名单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeBlack qrcodeBlack) {
        qrcodeBlackService.update(qrcodeBlack, new EntityWrapper<CcQrcodeBlack>().eq("token_id", qrcodeBlack.getTokenId()));
        return SUCCESS_TIP;
    }

    /**
     * 二维码黑名单详情
     */
    @RequestMapping(value = "/detail/{qrcodeBlackId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeBlackId") Integer qrcodeBlackId) {
        return qrcodeBlackService.selectById(qrcodeBlackId);
    }
}
