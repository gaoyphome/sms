package com.bmac.ffan.modular.qrcode.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.persistence.model.CcQrcodeDubious;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.qrcode.warpper.QrcodeSysComplementWarpper;
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
import com.bmac.ffan.modular.qrcode.dao.QrcodeSysComplementDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeSysComplement;
import com.bmac.ffan.modular.qrcode.service.IQrcodeSysComplementService;

/**
 * 用户补票控制器
 *
 * @author 工具生成
 * @Date 2017-11-25 16:17:30
 */
@Controller
@RequestMapping("/qrcodeSysComplement")
public class QrcodeSysComplementController extends BaseController {

    private String PREFIX = "/qrcode/qrcodeSysComplement/";

    @Autowired
    private IQrcodeSysComplementService qrcodeSysComplementService;

	@Resource
	QrcodeSysComplementDao qrcodeSysComplementDao;
	
    /**
     * 跳转到用户补票首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeSysComplement.html";
    }

    /**
     * 跳转到添加用户补票
     */
    @RequestMapping("/qrcodeSysComplement_add")
    public String qrcodeSysComplementAdd() {
        return PREFIX + "qrcodeSysComplement_add.html";
    }

    /**
     * 跳转到修改用户补票
     */
    @RequestMapping("/qrcodeSysComplement_update")
    public String qrcodeSysComplementUpdate(@RequestParam(required = true) String vsirBmacno,
                                            @RequestParam(required = true) String mouth,
                                            Model model) {
        Map map = new HashMap();
        map.put("vsir_bmacno",vsirBmacno);
        map.put("mouth",mouth);
        List<CcQrcodeSysComplement> list = qrcodeSysComplementService.selectByMap(map);
        CcQrcodeSysComplement qrcodeSysComplement = list.get(0);
        model.addAttribute("qrcodeSysComplement",qrcodeSysComplement);
        LogObjectHolder.me().set(qrcodeSysComplement);
        return PREFIX + "qrcodeSysComplement_edit.html";
    }

    /**
     * 获取用户补票列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String vsirBmacno,
    @RequestParam(required = false) String mouth
    
    ) {
        //return qrcodeSysComplementService.selectList(null);
        
        Page<CcQrcodeSysComplement> page = new PageFactory<CcQrcodeSysComplement>().defaultPage();
		 List<Map<String, Object>> result = qrcodeSysComplementDao.selectQrcodeSysComplements(page, 
   		 vsirBmacno,
   		 mouth,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcQrcodeSysComplement>) new QrcodeSysComplementWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增用户补票
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeSysComplement qrcodeSysComplement) {
        qrcodeSysComplementDao.insertQrcodeSysComplement(qrcodeSysComplement);
        //qrcodeSysComplementService.insert(qrcodeSysComplement);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户补票
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(required = true) String vsirBmacno,
                         @RequestParam(required = true) String mouth) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("vsir_bmacno", vsirBmacno)
                .and().eq("mouth", mouth);
        qrcodeSysComplementService.delete(wrapper);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户补票
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeSysComplement qrcodeSysComplement) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("vsir_bmacno", qrcodeSysComplement.getVsirBmacno())
                .and().eq("mouth", qrcodeSysComplement.getMouth());
        qrcodeSysComplementService.update(qrcodeSysComplement, wrapper);
        return SUCCESS_TIP;
    }

    /**
     * 用户补票详情
     */
    @RequestMapping(value = "/detail/{qrcodeSysComplementId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeSysComplementId") Integer qrcodeSysComplementId) {
        return qrcodeSysComplementService.selectById(qrcodeSysComplementId);
    }
}
