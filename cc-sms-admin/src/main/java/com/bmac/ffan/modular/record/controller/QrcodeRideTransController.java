package com.bmac.ffan.modular.record.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.persistence.model.CcLineInfo;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideRecord;
import com.bmac.ffan.config.properties.BmacProperties;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.httpclient.LsHttpClient;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.modular.basebusiness.service.ILineInfoService;
import com.bmac.ffan.modular.record.service.IQrcodeRideRecordService;
import com.bmac.ffan.modular.record.service.impl.QrcodeRideRecordServiceImpl;
import com.bmac.ffan.modular.record.warpper.QrcodeRideRecordBeanWarpper;
import com.bmac.ffan.modular.record.warpper.QrcodeRideTransWarpper;
import com.google.gson.Gson;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bmac.ffan.modular.record.dao.QrcodeRideTransDao;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideTrans;
import com.bmac.ffan.modular.record.service.IQrcodeRideTransService;

/**
 * 二维码乘车交易记录 控制器
 *
 * @author 工具生成
 * @Date 2017-12-01 19:55:20
 */
@Controller
@RequestMapping("/qrcodeRideTrans")
public class QrcodeRideTransController extends BaseController {

    private String PREFIX = "/record/qrcodeRideTrans/";

    @Autowired
    private IQrcodeRideTransService qrcodeRideTransService;

	@Resource
	QrcodeRideTransDao qrcodeRideTransDao;

	@Resource
    IQrcodeRideRecordService qrcodeRideRecordService;

	@Resource
    ILineInfoService lineInfoService;

    @Autowired
    BmacProperties bmacProperties;
    /**
     * 跳转到二维码乘车交易记录 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "qrcodeRideTrans.html";
    }

    /**
     * 跳转到添加二维码乘车交易记录
     */
    @RequestMapping("/qrcodeRideTrans_add")
    public String qrcodeRideTransAdd() {
        return PREFIX + "qrcodeRideTrans_add.html";
    }

    /**
     * 跳转到修改二维码乘车交易记录
     */
    @RequestMapping("/qrcodeRideTrans_detail/{pltSsn}")
    public String qrcodeRideTransDetail(@PathVariable String pltSsn, Model model) {
        Map map = new HashMap();
        map.put("plt_ssn",pltSsn);
        List<CcQrcodeRideTrans> list = qrcodeRideTransService.selectByMap(map);
        CcQrcodeRideTrans qrcodeRideTrans = list.get(0);
        model.addAttribute("qrcodeRideTrans",qrcodeRideTrans);
        LogObjectHolder.me().set(qrcodeRideTrans);
        return PREFIX + "qrcodeRideTrans_detail.html";
    }

    @RequestMapping("/qrcodeRideTrans_confirm/{id}")
    public String openRechargeDetail(@PathVariable String id, Model model){
        CcQrcodeRideTrans ccQrcodeRideTrans = qrcodeRideTransService.selectById(id);
        model.addAttribute("qrcodeRideTrans",ccQrcodeRideTrans);
        return PREFIX + "qrcodeRideTrans_confirm.html";
    }

    /**
     * 重新扣款
     */
    @RequestMapping("/recharge")
    @ResponseBody
    public Object recharge(CcQrcodeRideTrans qrcodeRideTrans) throws Exception {
        Map map = new HashMap();
        map.put("plt_ssn",qrcodeRideTrans.getPltSsn());
        CcQrcodeRideTrans ccQrcodeRideTrans = qrcodeRideTransService.selectById(qrcodeRideTrans.getId());
        if(ccQrcodeRideTrans == null ){
            return new ErrorTip("该记录不存在");
        }

        if(!("6".equals(ccQrcodeRideTrans.getState()) ||
                ("0".equals(ccQrcodeRideTrans.getState()) && "1".equals(ccQrcodeRideTrans.getTransState())))){
            return new ErrorTip("该记录状态不允许重新扣款");
        }

        try {
            String result = LsHttpClient.sendHttpForPost(bmacProperties.getBnUrl() + ccQrcodeRideTrans.getPltSsn(), "");
            Gson gson = new Gson();
            Map map_result = gson.fromJson(result, HashMap.class);
            if (!"200".equals(map_result.get("status").toString()) && !"200.0".equals(map_result.get("status").toString())) {
                return new ErrorTip(map_result.get("message").toString());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ErrorTip("发送扣款请求异常");
        }
        return SUCCESS_TIP;
    }

    /**
     * 获取二维码乘车交易记录 列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    @RequestParam(required = false) String startTermTransDate,
    @RequestParam(required = false) String endTermTransTime,
    @RequestParam(required = false) String cardno,
    @RequestParam(required = false) String userPhone,
    @RequestParam(required = false) String lineName,
    @RequestParam(required = false) String state,
    @RequestParam(required = false) String transState,
    @RequestParam(required = false) String tradeStatus,
    @RequestParam(required = false) String tradeTypeFlag,
    @RequestParam(required = false) String posId,
    @RequestParam(required = false) String orderNo
    ) {
        //return qrcodeRideTransService.selectList(null);
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
            ShiroKit.getSubject().logout();
            return null;
        }
        Page<CcQrcodeRideTrans> page = new PageFactory<CcQrcodeRideTrans>().defaultPage();
		 List<Map<String, Object>> result = qrcodeRideTransDao.selectQrcodeRideTranss(page,
                 ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
                 startTermTransDate,
                 endTermTransTime,
                 cardno,
   		         userPhone,
                 lineName,
		         state,
                 transState,
                 tradeStatus,
                 tradeTypeFlag,
		         posId,
                 orderNo,
		 page.getOrderByField(), page.isAsc());
        if(!result.isEmpty()) {
            List<String> pltSsns = result.stream().map(e -> e.get("pltSsn").toString()).collect(Collectors.toList());
            List<Map<String, Object>> records = qrcodeRideTransDao.selectQrcodeRideRecordByTrans(pltSsns);
            QrcodeRideRecordBeanWarpper warpper = new QrcodeRideRecordBeanWarpper();
            for (Map<String, Object> trans : result) {
                for (Map<String, Object> record : records) {
                    if (trans.get("pltSsn").toString().equals(record.get("transPltSsn").toString())) {
                        Object time = trans.get("markInfoTime");
                        if (time == null) {//上车
                            trans.put("markInfoTime", record.get("recordMarkTime"));
                            trans.put("markInfoName", record.get("recordMarkStationName"));
                            trans.put("markInfoType", record.get("recordTradeTypeFlag"));
                            Object recordMarkStationName = record.get("recordMarkStationName");
                            Object recordTradeTypeFlag = record.get("recordTradeTypeFlag");
                            trans.put("markInfo", (recordMarkStationName == null ? "" : recordMarkStationName.toString()) + "-" + warpper.warptradeTypeFlag(recordTradeTypeFlag == null ? "" :recordTradeTypeFlag.toString()));
                        } else {
                            if (time.toString().compareTo(record.get("transPltSsn").toString()) < 0) {//trans已保存上车信息，record下车
                                trans.put("downInfoTime", record.get("recordMarkTime"));
                                trans.put("downInfoName", record.get("recordMarkStationName"));
                                trans.put("downInfoType", record.get("recordTradeTypeFlag"));
                                Object recordMarkStationName = record.get("recordMarkStationName");
                                Object recordTradeTypeFlag = record.get("recordTradeTypeFlag");
                                trans.put("downInfo", recordMarkStationName == null ? "" : recordMarkStationName.toString() + "-" + warpper.warptradeTypeFlag(recordTradeTypeFlag == null ? "" :recordTradeTypeFlag.toString()));
                            } else {
                                Object mit = trans.get("markInfoTime");
                                Object min = trans.get("markInfoName");
                                Object mity = trans.get("markInfoType");

                                trans.put("markInfoTime", record.get("recordMarkTime"));
                                trans.put("markInfoName", record.get("recordMarkStationName"));
                                trans.put("markInfoType", record.get("recordTradeTypeFlag"));

                                trans.put("downInfoTime", mit);
                                trans.put("downInfoName", min);
                                trans.put("markInfoType", mity);

                                Object recordMarkStationName = record.get("recordMarkStationName");
                                Object recordTradeTypeFlag = record.get("recordTradeTypeFlag");
                                trans.put("markInfo", (recordMarkStationName == null ? "" : recordMarkStationName.toString()) + "-" + warpper.warptradeTypeFlag(recordTradeTypeFlag == null ? "" :recordTradeTypeFlag.toString()));
                                trans.put("downInfo", (min == null ? "" : min.toString()) + "-" + warpper.warptradeTypeFlag(mity == null ? "" : mity.toString()));
                            }
                        }
                    }
                }
            }
        }
	     page.setRecords((List<CcQrcodeRideTrans>) new QrcodeRideTransWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增二维码乘车交易记录
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcQrcodeRideTrans qrcodeRideTrans) {
        qrcodeRideTransService.insert(qrcodeRideTrans);
        return SUCCESS_TIP;
    }

    /**
     * 删除二维码乘车交易记录
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer qrcodeRideTransId) {
        qrcodeRideTransService.deleteById(qrcodeRideTransId);
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码乘车交易记录
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcQrcodeRideTrans qrcodeRideTrans) {
        qrcodeRideTransService.updateById(qrcodeRideTrans);
        return SUCCESS_TIP;
    }

    /**
     * 二维码乘车交易记录 详情
     */
    @RequestMapping(value = "/detail/{qrcodeRideTransId}")
    @ResponseBody
    public Object detail(@PathVariable("qrcodeRideTransId") Integer qrcodeRideTransId) {
        return qrcodeRideTransService.selectById(qrcodeRideTransId);
    }

    /**
     * 二维码乘车记录详情
     */
    @RequestMapping(value = "/recordDetail/{qrcodeRideTransId}")
    public String recordDetail(@PathVariable("qrcodeRideTransId") Integer id, Model model) {

        CcQrcodeRideTrans qrcodeRideTrans = qrcodeRideTransService.selectById(id);
        Wrapper<CcQrcodeRideRecord> wrapper = new EntityWrapper<CcQrcodeRideRecord>();
        wrapper.eq("plt_ssn",qrcodeRideTrans.getPltSsn()).or().eq("plt_ssn",qrcodeRideTrans.getReqSsn()).orderBy("mark_time");
        List<CcQrcodeRideRecord> ccQrcodeRideRecordList = qrcodeRideRecordService.selectList(wrapper);

        /**
         * 获取线路信息是否是固定票价
         */
        Wrapper<CcLineInfo> wrapper_lineInfo = new EntityWrapper<CcLineInfo>();
        wrapper_lineInfo.eq("line_id",qrcodeRideTrans.getMarkLineId());
        List<CcLineInfo> lineInfos = lineInfoService.selectList(wrapper_lineInfo);
        QrcodeRideRecordBeanWarpper warpper = new QrcodeRideRecordBeanWarpper();
        if(!lineInfos.isEmpty() && !ccQrcodeRideRecordList.isEmpty()){
            CcLineInfo lineInfo = lineInfos.get(0);
            /*0 - 折返型固定票价线路
            1 - 折返型分段票价线路
            2 - 环型固定票价线路
            3 - 环线分段票价线路)*/
            if("0".equals(lineInfo.getLineAttr()) || "2".equals(lineInfo.getLineAttr())){
                    if(ccQrcodeRideRecordList.size() == 1){
                        model.addAttribute("isSingle",true);
                        model.addAttribute("recordStart",warpper.warpBean(ccQrcodeRideRecordList.get(0)));
                        model.addAttribute("recordEnd",warpper.warpBean(new CcQrcodeRideRecord()));
                    }
            }else{
                if(ccQrcodeRideRecordList.size() == 2){
                    model.addAttribute("isSingle",false);
                    model.addAttribute("recordStart",warpper.warpBean(ccQrcodeRideRecordList.get(0)));
                    model.addAttribute("recordEnd",warpper.warpBean(ccQrcodeRideRecordList.get(1)));
                }
            }
        }
        return PREFIX + "qrcodeRideTrans_recordDetail.html";
    }

}
