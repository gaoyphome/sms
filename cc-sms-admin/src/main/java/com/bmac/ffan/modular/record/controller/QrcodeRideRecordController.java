package com.bmac.ffan.modular.record.controller;

import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.persistence.model.CcQrcodeBlack;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.modular.record.dao.QrcodeRideRecordFat;
import com.bmac.ffan.modular.record.warpper.QrcodeRideRecordWarpper;
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
import com.bmac.ffan.modular.record.dao.QrcodeRideRecordDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideRecord;
import com.bmac.ffan.modular.record.service.IQrcodeRideRecordService;

/**
 * 二维码乘车记录控制器
 *
 * @author 工具生成
 * @Date 2017-12-01 15:34:29
 */
@Controller
@RequestMapping("/qrcodeRideRecord")
public class QrcodeRideRecordController extends BaseController {

    private String PREFIX = "/record/qrcodeRideRecord/";

    @Autowired
    private IQrcodeRideRecordService qrcodeRideRecordService;

	@Resource
	QrcodeRideRecordDao qrcodeRideRecordDao;
	
    /**
     * 跳转到二维码乘车记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeRideRecord.html";
    }

    /**
     * 跳转到添加二维码乘车记录
     */
    @RequestMapping("/qrcodeRideRecord_add")
    public String qrcodeRideRecordAdd() {
        return PREFIX + "qrcodeRideRecord_add.html";
    }

    /**
     * 跳转到修改二维码乘车记录
     */
    @RequestMapping("/qrcodeRideRecord_update/{qrcodeRideRecordId}")
    public String qrcodeRideRecordUpdate(@PathVariable Integer qrcodeRideRecordId, Model model) {
        CcQrcodeRideRecord qrcodeRideRecord = qrcodeRideRecordService.selectById(qrcodeRideRecordId);
        model.addAttribute("qrcodeRideRecord",qrcodeRideRecord);
        LogObjectHolder.me().set(qrcodeRideRecord);
        return PREFIX + "qrcodeRideRecord_edit.html";
    }

    /**
     * 跳转到二维码乘车记录详情
     */
    @RequestMapping("/qrcodeRideRecord_detail/{pltSsn}")
    public String qrcodeRideRecordDetail(@PathVariable String pltSsn, Model model) {
        Map map = new HashMap();
        map.put("plt_ssn",pltSsn);
        List<CcQrcodeRideRecord> list = qrcodeRideRecordService.selectByMap(map);
        CcQrcodeRideRecord qrcodeRideRecord = list.get(0);
        model.addAttribute("qrcodeRideRecord",qrcodeRideRecord);
        LogObjectHolder.me().set(qrcodeRideRecord);
        return PREFIX + "qrcodeRideRecord_detail.html";
    }

    /**
     * 获取二维码乘车记录列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String startTermTransDate,
    @RequestParam(required = false) String endTermTransTime,
    @RequestParam(required = false) String cardno,
    @RequestParam(required = false) String userPhone,
    @RequestParam(required = false) String markLineId,
    @RequestParam(required = false) String downLineId
    
    ) {
        //return qrcodeRideRecordService.selectList(null);
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        Page<QrcodeRideRecordFat> page = new PageFactory<QrcodeRideRecordFat>().defaultPage();
		 List<Map<String, Object>> result = qrcodeRideRecordDao.selectQrcodeRideRecords(page,
                 ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
                 startTermTransDate,
                 endTermTransTime,
                 cardno,
                 userPhone,
   		 markLineId,
   		 downLineId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<QrcodeRideRecordFat>) new QrcodeRideRecordWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增二维码乘车记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeRideRecord qrcodeRideRecord) {
        qrcodeRideRecordService.insert(qrcodeRideRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除二维码乘车记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer qrcodeRideRecordId) {
        qrcodeRideRecordService.deleteById(qrcodeRideRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码乘车记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeRideRecord qrcodeRideRecord) {
        qrcodeRideRecordService.updateById(qrcodeRideRecord);
        return SUCCESS_TIP;
    }

    /**
     * 二维码乘车记录详情
     */
    @RequestMapping(value = "/detail/{qrcodeRideRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeRideRecordId") Integer qrcodeRideRecordId) {
        return qrcodeRideRecordService.selectById(qrcodeRideRecordId);
    }
}
