package com.bmac.ffan.modular.qrcode.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.persistence.model.CcQrcodeDiscount;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.qrcode.warpper.QrcodeDubiousWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.QrcodeDubiousDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeDubious;
import com.bmac.ffan.modular.qrcode.service.IQrcodeDubiousService;

/**
 * 二维码可疑信息表控制器
 *
 * @author 工具生成
 * @Date 2017-11-25 14:06:16
 */
@Controller
@RequestMapping("/qrcodeDubious")
public class QrcodeDubiousController extends BaseController {

    private String PREFIX = "/qrcode/qrcodeDubious/";

    @Autowired
    private IQrcodeDubiousService qrcodeDubiousService;

	@Resource
	QrcodeDubiousDao qrcodeDubiousDao;
	
    /**
     * 跳转到二维码可疑信息表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeDubious.html";
    }

    /**
     * 跳转到添加二维码可疑信息表
     */
    @RequestMapping("/qrcodeDubious_add")
    public String qrcodeDubiousAdd() {
        return PREFIX + "qrcodeDubious_add.html";
    }

    /**
     * 跳转到修改二维码可疑信息表
     */
    @RequestMapping("/qrcodeDubious_update")
    public String qrcodeDubiousUpdate( @RequestParam(required = true) String vsirBmacno,
                                       @RequestParam(required = true) String reason,
                                       @RequestParam(required = true) String tokenId,
                                        Model model) {
        Map map = new HashMap();
        map.put("vsir_bmacno",vsirBmacno);
        map.put("reason",reason);
        map.put("token_id",tokenId);
        List<CcQrcodeDubious> list = qrcodeDubiousService.selectByMap(map);
        CcQrcodeDubious qrcodeDubious = list.get(0);
        model.addAttribute("qrcodeDubious",qrcodeDubious);
        LogObjectHolder.me().set(qrcodeDubious);
        return PREFIX + "qrcodeDubious_edit.html";
    }

    /**
     * 获取二维码可疑信息表列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String vsirBmacno,
    @RequestParam(required = false) String tokenId
    
    ) {
        //return qrcodeDubiousService.selectList(null);
        
        Page<CcQrcodeDubious> page = new PageFactory<CcQrcodeDubious>().defaultPage();
		 List<Map<String, Object>> result = qrcodeDubiousDao.selectQrcodeDubiouss(page, 
   		 vsirBmacno,
   		 tokenId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcQrcodeDubious>) new QrcodeDubiousWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增二维码可疑信息表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeDubious qrcodeDubious) {
        qrcodeDubiousDao.insertQrcodeDubious(qrcodeDubious);
      //  qrcodeDubiousService.insert(qrcodeDubious);
        return SUCCESS_TIP;
    }

    /**
     * 删除二维码可疑信息表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String vsirBmacno,@RequestParam String reason,@RequestParam String tokenId) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("vsir_bmacno", vsirBmacno)
                .and().eq("reason", reason)
                .and().eq("token_id", tokenId);
        qrcodeDubiousService.delete(wrapper);
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码可疑信息表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeDubious qrcodeDubious) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("vsir_bmacno", qrcodeDubious.getVsirBmacno())
                .and().eq("reason", qrcodeDubious.getReason())
                .and().eq("token_id", qrcodeDubious.getTokenId());
        qrcodeDubiousService.update(qrcodeDubious, wrapper);
        return SUCCESS_TIP;
    }

    /**
     * 二维码可疑信息表详情
     */
    @RequestMapping(value = "/detail/{vsirBmacno}")
    @ResponseBody
    public Object detail(@PathVariable("vsirBmacno") String vsirBmacno) {
        Map map = new HashMap();
        map.put("vsir_bmacno",vsirBmacno);
        List<CcQrcodeDubious> list = qrcodeDubiousService.selectByMap(map);
        return list.get(0);
    }
}
