package com.bmac.ffan.modular.record.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.record.warpper.QrcodeRideTransErrorWarpper;
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
import com.bmac.ffan.modular.record.dao.QrcodeRideTransErrorDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideTransError;
import com.bmac.ffan.modular.record.service.IQrcodeRideTransErrorService;

/**
 * 乘车交易异常信息表控制器
 *
 * @author 工具生成
 * @Date 2018-03-15 10:30:04
 */
@Controller
@RequestMapping("/qrcodeRideTransError")
public class QrcodeRideTransErrorController extends BaseController {

    private String PREFIX = "/record/qrcodeRideTransError/";

    @Autowired
    private IQrcodeRideTransErrorService qrcodeRideTransErrorService;

	@Resource
	QrcodeRideTransErrorDao qrcodeRideTransErrorDao;
	
    /**
     * 跳转到乘车交易异常信息表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeRideTransError.html";
    }

    /**
     * 跳转到添加乘车交易异常信息表
     */
    @RequestMapping("/qrcodeRideTransError_add")
    public String qrcodeRideTransErrorAdd() {
        return PREFIX + "qrcodeRideTransError_add.html";
    }

    /**
     * 跳转到修改乘车交易异常信息表
     */
    @RequestMapping("/qrcodeRideTransError_update/{qrcodeRideTransErrorId}")
    public String qrcodeRideTransErrorUpdate(@PathVariable Integer qrcodeRideTransErrorId, Model model) {
        CcQrcodeRideTransError qrcodeRideTransError = qrcodeRideTransErrorService.selectById(qrcodeRideTransErrorId);
        model.addAttribute("qrcodeRideTransError",qrcodeRideTransError);
        LogObjectHolder.me().set(qrcodeRideTransError);
        return PREFIX + "qrcodeRideTransError_edit.html";
    }

    /**
     * 获取乘车交易异常信息表列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
            @RequestParam(required = false) String startTermTransDate,
            @RequestParam(required = false) String endTermTransTime,
    @RequestParam(required = false) String cardno,
    @RequestParam(required = false) String order_no
    
    ) {
        //return qrcodeRideTransErrorService.selectList(null);
        
        Page<CcQrcodeRideTransError> page = new PageFactory<CcQrcodeRideTransError>().defaultPage();
		 List<Map<String, Object>> result = qrcodeRideTransErrorDao.selectQrcodeRideTransErrors(page, 
   		 cardno,
   		 order_no,
                 startTermTransDate,
                 endTermTransTime,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcQrcodeRideTransError>) new QrcodeRideTransErrorWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增乘车交易异常信息表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeRideTransError qrcodeRideTransError) {
        qrcodeRideTransErrorService.insert(qrcodeRideTransError);
        return SUCCESS_TIP;
    }

    /**
     * 删除乘车交易异常信息表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer qrcodeRideTransErrorId) {
        qrcodeRideTransErrorService.deleteById(qrcodeRideTransErrorId);
        return SUCCESS_TIP;
    }

    /**
     * 修改乘车交易异常信息表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeRideTransError qrcodeRideTransError) {
        qrcodeRideTransErrorService.updateById(qrcodeRideTransError);
        return SUCCESS_TIP;
    }

    /**
     * 乘车交易异常信息表详情
     */
    @RequestMapping(value = "/detail/{qrcodeRideTransErrorId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeRideTransErrorId") Integer qrcodeRideTransErrorId) {
        return qrcodeRideTransErrorService.selectById(qrcodeRideTransErrorId);
    }
}
