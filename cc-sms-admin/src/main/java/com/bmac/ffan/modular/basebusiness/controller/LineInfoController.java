package com.bmac.ffan.modular.basebusiness.controller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.constant.SysConstant;
import com.bmac.ffan.common.exception.BussinessException;
import com.bmac.ffan.common.persistence.model.CcBusCompany;
import com.bmac.ffan.common.persistence.model.CcSysMenu;
import com.bmac.ffan.core.base.tips.ConfirmTip;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.shiro.ShiroKit;
import com.bmac.ffan.modular.basebusiness.service.IBusCompanyService;
import com.bmac.ffan.modular.basebusiness.service.impl.BusCompanyServiceImpl;
import com.bmac.ffan.modular.basebusiness.transfer.CompareInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.bmac.ffan.common.persistence.model.CcLineInfo;
import com.bmac.ffan.common.persistence.model.ResultMap;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.log.LogObjectHolder;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.dao.LineInfoDao;
import com.bmac.ffan.modular.basebusiness.dao.MileageInfoDao;
import com.bmac.ffan.modular.basebusiness.dao.PriceInfoDao;
import com.bmac.ffan.modular.basebusiness.dao.StationInfoDao;
import com.bmac.ffan.modular.basebusiness.poixls.ParseLineInfo;
import com.bmac.ffan.modular.basebusiness.poixls.ParseLineInfoException;
import com.bmac.ffan.modular.basebusiness.service.IGenerateParametFileService;
import com.bmac.ffan.modular.basebusiness.service.ILineInfoService;
import com.bmac.ffan.modular.basebusiness.transfer.StationPriceInfo;
import com.bmac.ffan.modular.basebusiness.warpper.LineInfoWarpper;

/**
 * 线路信息管理控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 15:10:39
 */
@Controller
@RequestMapping("/lineInfo")
public class LineInfoController extends BaseController {
    private static final  String PARSELINEINFO = "parseLineInfo";
    private static final  String COMPAREINFOS = "compareInfos";

    private String PREFIX = "/basebusiness/lineInfo/";
    @Autowired
    IGenerateParametFileService generateParametFileService;
    @Autowired
    ILineInfoService lineInfoService;
    @Resource
    StationInfoDao stationInfoDao;
    @Resource
    LineInfoDao lineInfoDao;
    @Resource
    PriceInfoDao priceInfoDao;
    @Resource
    MileageInfoDao mileageInfoDao;
    @Resource
    IBusCompanyService busCompanyService;

    /**
     * 跳转到线路信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "lineInfo.html";
    }

    /**
     * 跳转到添加线路信息管理
     */
    @RequestMapping("/lineInfo_add")
    public String lineInfoAdd() {
        return PREFIX + "lineInfo_add.html";
    }

    /**
     * 跳转到文件导入管理页面
     *
     * @return
     */
    @RequestMapping("/lineInfo_import")
    public String lineInfoImport() {
        return PREFIX + "lineInfo_import.html";
    }

    /**
     * 跳转到修改线路信息管理
     */
    @RequestMapping("/lineInfo_update/{lineInfoId}")
    public String lineInfoUpdate(@PathVariable Integer lineInfoId, Model model) {
        CcLineInfo lineInfo = lineInfoService.selectById(lineInfoId);
        model.addAttribute("lineInfo", lineInfo);
        LogObjectHolder.me().set(lineInfo);
        return PREFIX + "lineInfo_edit.html";
    }

    /**
     * 跳转到线路信息管详情理
     */
    @RequestMapping("/lineInfo_detail/{lineInfoId}")
    public String lineInfoDetail(@PathVariable Integer lineInfoId, Model model) {
        CcLineInfo lineInfo = lineInfoService.selectById(lineInfoId);
        model.addAttribute("lineInfo", lineInfo);
        LogObjectHolder.me().set(lineInfo);
        return PREFIX + "lineInfo_detail.html";
    }

    /**
     * 跳转到站点情理
     */
    @RequestMapping("/lineInfo_stationdetail/{lineInfoId}")
    public String lineInfoStationDetail(@PathVariable String lineInfoId, Model model) {
        CcLineInfo lineInfo = lineInfoService.selectById(lineInfoId);

        List<Map<String, Object>> stationInfos1 = stationInfoDao.selectStationInfosByLineId(
                lineInfo.getLineId(), String.valueOf(1));//上行
        List<Map<String, Object>> stationInfos0 = stationInfoDao.selectStationInfosByLineId(
                lineInfo.getLineId(), String.valueOf(0));//下行

        List<Map<String, Object>> mileageInfo1 = mileageInfoDao.selectMileageInfoByLineId(
                lineInfo.getLineId(), String.valueOf(1));
        List<Map<String, Object>> mileageInfo0 = mileageInfoDao.selectMileageInfoByLineId(
                lineInfo.getLineId(), String.valueOf(0));

        //将对应的公里数附加在站点信息后，首站没有公里数
        if(mileageInfo1 != null && !mileageInfo1.isEmpty()){
            String[] m1 = mileageInfo1.get(0).get("mileageValue").toString().split(",");
            int size1 = stationInfos1.size();
            if(size1 == (m1.length + 1)){
                for(int i=1; i<size1; i++){
                    stationInfos1.get(i).put("mileage",formatPrice(m1[i-1]));
                }
            }
        }

        if(mileageInfo0 != null && !mileageInfo0.isEmpty()){
            String[] m0 = mileageInfo0.get(0).get("mileageValue").toString().split(",");
            int size0 = stationInfos0.size();
            if(size0 == (m0.length + 1)){
                for(int i=1; i<size0; i++){
                    stationInfos0.get(i).put("mileage",formatPrice(m0[i-1]));
                }
            }
        }

        model.addAttribute("stationInfos0", stationInfos0);
        LogObjectHolder.me().set(stationInfos0);

        model.addAttribute("stationInfos1", stationInfos1);
        LogObjectHolder.me().set(stationInfos1);

        return PREFIX + "lineInfo_stationdetail.html";
    }

    @RequestMapping("/lineInfo_pricedetail/{lineInfoId}")
    public String lineInfoPriceDetail_(@PathVariable String lineInfoId, Model model){
        //上行数据
        CcLineInfo lineInfo = lineInfoService.selectById(lineInfoId);
        List<Map<String, Object>> stationprices = stationInfoDao.selectStationPriceByLineId(lineInfo.getLineId(), String.valueOf(1));
        List<StationPriceInfo> upstationPriceInfoList = warpperStationPrice(stationprices);
        model.addAttribute("upstationPriceInfoList", upstationPriceInfoList);
        LogObjectHolder.me().set(upstationPriceInfoList);
        //下行数据
        stationprices = stationInfoDao.selectStationPriceByLineId(lineInfo.getLineId(), String.valueOf(0));
        List<StationPriceInfo> downstationPriceInfoList = warpperStationPrice(stationprices);
        model.addAttribute("downstationPriceInfoList", downstationPriceInfoList);
        LogObjectHolder.me().set(downstationPriceInfoList);

        return PREFIX + "lineInfo_pricedetail.html";
    }

    private List<StationPriceInfo> warpperStationPrice(List<Map<String, Object>> stationprices){
        List<StationPriceInfo> upstationPriceInfoList = new ArrayList<StationPriceInfo>();
        for (int i=0; i<stationprices.size(); i++){
            StationPriceInfo stationPriceInfo = new StationPriceInfo();
            stationPriceInfo.setStationname(stationprices.get(i).get("name").toString());
            stationPriceInfo.setNum(stationprices.get(i).get("num").toString());
            upstationPriceInfoList.add(stationPriceInfo);
        }
        for(int i=0; i< stationprices.size(); i++) {
            Object obj = stationprices.get(i).get("price");
            if (obj != null) {
                String[] prices = obj.toString().split(",");
                boolean isfirst = true;
                for (int j = 0; j < prices.length; j++) {
                    upstationPriceInfoList.get(j + i + 1).getPrices().add(formatPrice(prices[j]));
                }
            }
        }
        return upstationPriceInfoList;
    }

    /**
     * 获取线路信息管理列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(

            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lineId

    ) throws Exception {

        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId == null ){
           ShiroKit.getSubject().logout();
           return null;
        }
        Page<CcLineInfo> page = new PageFactory<CcLineInfo>().defaultPage();
        System.out.println("---运营公司ID---->"+((super.getSession().getAttribute("companyId") != null ) ? super.getSession().getAttribute("companyId").toString() : null));
        List<Map<String, Object>> result = lineInfoDao.selectLineInfos(page,
                ((SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())) ? null : companyId.toString()),
                name,
                lineId,
                page.getOrderByField(), page.isAsc());
        page.setRecords((List<CcLineInfo>) new LineInfoWarpper(result).warp());

        return super.packForBT(page);

    }

    @RequestMapping(value = "/importfile", method = RequestMethod.POST)
    @ResponseBody
    public Object importfile(@RequestParam("filecontent") MultipartFile fileField,
                             @RequestParam("lineType") Integer lineType,
             Model model) throws Exception {

        //long size = fileField.getSize();
        InputStream inputStream = fileField.getInputStream();
        ParseLineInfo parseLineInfo = new ParseLineInfo(lineType);
        parseLineInfo.parseXLS(inputStream);
        Object companyId = super.getSession().getAttribute("companyId");
        if(companyId != null){//会话可能过期，需要重新登录
            if(!companyId.toString() .equals(parseLineInfo.getCcLineInfo().getCompanyId())
                    && !companyId.toString().equals(parseLineInfo.getCcLineInfo().getSubCompanyId())
                    && !SysConstant.DEPTID_ALL_PRIVILEGES.equals(companyId.toString())){
                return new ErrorTip("当前帐号所属公司与线路所属公司不匹配！");
            }

        }else{
            ShiroKit.getSubject().logout();
            return new ErrorTip("连接异常，请重新登录");
        }

        List<CompareInfo> compareInfos = new ArrayList<CompareInfo>();
        Map map = new HashMap();
        map.put("line_id",parseLineInfo.getCcLineInfo().getLineId());
        List<CcLineInfo> lineInfos = lineInfoService.selectByMap(map);
        if(!lineInfos.isEmpty()){//线路存在一定要提示确认信息
            List<CompareInfo> compareLineInfos = checkLineInfo(parseLineInfo, lineInfos.get(0));
            List<CompareInfo> compareCompany = checkCompany(parseLineInfo);
            compareInfos.addAll(compareLineInfos);
            compareInfos.addAll(compareCompany);
            super.getSession().setAttribute(PARSELINEINFO ,parseLineInfo);
            super.getSession().setAttribute(COMPAREINFOS,compareInfos);
            return new ConfirmTip("当前线路线编号已存在，请确认！");
        }else{//如果线路不存在，且运营公司信息正确，则直接导入,否则提示确认框
            List<CompareInfo> compareCompany = checkCompany(parseLineInfo);
            if(compareCompany.isEmpty()){
                lineInfoService.importLineInfo(parseLineInfo);
                ResultMap resultMap = generateParametFileService.generateLineParamFile(parseLineInfo);
                if(!resultMap.isSuccess()){
                    //如果操作失败
                    return ERROR;
                }
            }else{
                compareInfos.addAll(compareCompany);
                super.getSession().setAttribute(PARSELINEINFO ,parseLineInfo);
                super.getSession().setAttribute(COMPAREINFOS,compareInfos);
                return new ConfirmTip("当前线路线编号已存在，请确认！");
            }

        }
        return SUCCESS_TIP;
    }

    /**
     * 检查运营公司信息
     * @param parseLineInfo
     * @return
     */
    private List<CompareInfo> checkCompany(ParseLineInfo parseLineInfo) throws Exception {
        List<CompareInfo> compareInfos = new ArrayList<CompareInfo>();
        Wrapper<CcBusCompany> wrapper = new EntityWrapper<CcBusCompany>();
        wrapper.eq("company_id",parseLineInfo.getSubBusCompany().getCompanyId());
        List<CcBusCompany> busCompanies =  busCompanyService.selectList(wrapper);
        if(!busCompanies.isEmpty()){
            if(!busCompanies.get(0).getCompanyName().equals(parseLineInfo.getSubBusCompany().getCompanyName())){
                compareInfos.add(new CompareInfo("分公司名称",busCompanies.get(0).getCompanyName(),parseLineInfo.getSubBusCompany().getCompanyName()));
            }

            if(!busCompanies.get(0).getParentCompanyId().equals(parseLineInfo.getBusCompany().getCompanyId())){
                throw new ParseLineInfoException("分公司ID与父公司ID不匹配，请核实！");
            }
        }else{
            throw new ParseLineInfoException("分公司名数据不存在，请核实！");
        }

        wrapper = new EntityWrapper<CcBusCompany>();
        wrapper.eq("company_id",parseLineInfo.getBusCompany().getCompanyId());
        busCompanies = busCompanyService.selectList(wrapper);
        if(!busCompanies.isEmpty()){
            if(!busCompanies.get(0).getCompanyName().equals(parseLineInfo.getBusCompany().getCompanyName())){
                compareInfos.add(new CompareInfo("客运企业名称",busCompanies.get(0).getCompanyName(),parseLineInfo.getBusCompany().getCompanyName()));
            }
        }else{
            throw new ParseLineInfoException("运营单位数据不存在，请核实！");
        }
        return compareInfos;
    }
    /**
     * 检查线路是否重复，以及部分重复字段
     * @param parseLineInfo
     * @return
     */
    private List<CompareInfo> checkLineInfo(ParseLineInfo parseLineInfo, CcLineInfo lineInfo) throws Exception {
        List<CompareInfo> compareInfos = new ArrayList<CompareInfo>();

            if(!lineInfo.getName().equals(parseLineInfo.getCcLineInfo().getName())){
                compareInfos.add(new CompareInfo("线路名称",lineInfo.getName(),parseLineInfo.getCcLineInfo().getName()));
            }
            if(!lineInfo.getCompanyId().equals(parseLineInfo.getCcLineInfo().getCompanyId())){
                //compareInfos.add(new CompareInfo("运营单位标识",lineInfo.getCompanyId(),parseLineInfo.getCcLineInfo().getCompanyId()));
                throw new ParseLineInfoException("运营单位标识与有的不一致，请核实");
            }
      /*      if(!lineInfo.getCompanyId().equals(parseLineInfo.getCcLineInfo().getCompanyId())){
                compareInfos.add(new CompareInfo("客运企业名称",lineInfo.getCompanyId(),parseLineInfo.getCcLineInfo().getCompanyId()));
            }*/
            if(!lineInfo.getSubCompanyId().equals(parseLineInfo.getCcLineInfo().getSubCompanyId())){
                //compareInfos.add(new CompareInfo("所属运营分公司代码",lineInfo.getSubCompanyId(),parseLineInfo.getCcLineInfo().getSubCompanyId()));
                throw new ParseLineInfoException("所属运营分公司代码与有的不一致，请核实");
            }
            if(!lineInfo.getLineAttr().equals(parseLineInfo.getCcLineInfo().getLineAttr())){
                compareInfos.add(new CompareInfo("线路类型",lineInfo.getLineAttr(),parseLineInfo.getCcLineInfo().getLineAttr()));
            }
            if(!lineInfo.getStationsNumUp().equals(parseLineInfo.getCcLineInfo().getStationsNumUp())){
                compareInfos.add(new CompareInfo("上行(环形)站点总数量",lineInfo.getStationsNumUp()+"",parseLineInfo.getCcLineInfo().getStationsNumUp()+""));
            }
            if(!lineInfo.getStationsNumDn().equals(parseLineInfo.getCcLineInfo().getStationsNumDn())){
                compareInfos.add(new CompareInfo("下行站点总数量",lineInfo.getStationsNumDn()+"",parseLineInfo.getCcLineInfo().getStationsNumDn()+""));
            }
            if(!lineInfo.getFileVersion().equals(parseLineInfo.getCcLineInfo().getFileVersion())){
                compareInfos.add(new CompareInfo("线路文件格式版本号",lineInfo.getFileVersion(),parseLineInfo.getCcLineInfo().getFileVersion()));
            }
            if(!lineInfo.getExchangeTime().equals(parseLineInfo.getCcLineInfo().getExchangeTime())){
                compareInfos.add(new CompareInfo("换乘时限(分钟)",lineInfo.getExchangeTime()+"",parseLineInfo.getCcLineInfo().getExchangeTime()+""));
            }
            if(!lineInfo.getPriceBase().equals(parseLineInfo.getCcLineInfo().getPriceBase())){
                compareInfos.add(new CompareInfo("基本票价(元)",lineInfo.getPriceBase()+"",parseLineInfo.getCcLineInfo().getPriceBase()+""));
            }
            if(!lineInfo.getCityLeaveUp().equals(parseLineInfo.getCcLineInfo().getCityLeaveUp())){
                compareInfos.add(new CompareInfo("上行市界起点",lineInfo.getCityLeaveUp()+"",parseLineInfo.getCcLineInfo().getCityLeaveUp()+""));
            }
            if(!lineInfo.getCityLeaveDown().equals(parseLineInfo.getCcLineInfo().getCityLeaveDown())){
                compareInfos.add(new CompareInfo("下行市界起点",lineInfo.getCityLeaveDown()+"",parseLineInfo.getCcLineInfo().getCityLeaveDown()+""));
            }
            if(!lineInfo.getLocalNoMode().equals(parseLineInfo.getCcLineInfo().getLocalNoMode())){
                compareInfos.add(new CompareInfo("本地未定义卡类型处理模式",lineInfo.getLocalNoMode()+"",parseLineInfo.getCcLineInfo().getLocalNoMode()+""));
            }
            if(!lineInfo.getOffsiteNoModel().equals(parseLineInfo.getCcLineInfo().getOffsiteNoModel())){
                compareInfos.add(new CompareInfo("异地未定义卡类型处理模式",lineInfo.getOffsiteNoModel()+"",parseLineInfo.getCcLineInfo().getOffsiteNoModel()+""));
            }
            if(!lineInfo.getBusAttr().equals(parseLineInfo.getCcLineInfo().getBusAttr())){
                compareInfos.add(new CompareInfo("车辆属性",lineInfo.getBusAttr()+"",parseLineInfo.getCcLineInfo().getBusAttr()+""));
            }
            if(!lineInfo.getOffsiteTicketModel().equals(parseLineInfo.getCcLineInfo().getOffsiteTicketModel())){
                compareInfos.add(new CompareInfo("异地逃票补票模式",lineInfo.getOffsiteTicketModel()+"",parseLineInfo.getCcLineInfo().getOffsiteTicketModel()+""));
            }
            if(!lineInfo.getDifTicketRule().equals(parseLineInfo.getCcLineInfo().getDifTicketRule())){
                compareInfos.add(new CompareInfo("上下车方向不同补票规则",lineInfo.getDifTicketRule()+"",parseLineInfo.getCcLineInfo().getDifTicketRule()+""));
            }
            if(!lineInfo.getTicketDiscountFlag().equals(parseLineInfo.getCcLineInfo().getTicketDiscountFlag())){
                compareInfos.add(new CompareInfo("补票金额是否无折扣标志",lineInfo.getTicketDiscountFlag()+"",parseLineInfo.getCcLineInfo().getTicketDiscountFlag()+""));
            }
            if(!lineInfo.getFunctionSwitch().equals(parseLineInfo.getCcLineInfo().getFunctionSwitch())){
                compareInfos.add(new CompareInfo("功能开关",lineInfo.getFunctionSwitch()+"",parseLineInfo.getCcLineInfo().getFunctionSwitch()+""));
            }
        return compareInfos;
    }
    /**
     * 线路导入确认
     */
    @RequestMapping(value = "/lineInfo_confirm")
    public String lineInfoConfirm(Model model){
        List<CompareInfo> compareInfos = (List<CompareInfo>) super.getSession().getAttribute(COMPAREINFOS);
        model.addAttribute("compareInfos",compareInfos);
        super.getSession().removeAttribute(COMPAREINFOS);
        return PREFIX + "lineInfo_confirm.html";
    }

    @RequestMapping(value = "/downloadfile" , method = RequestMethod.GET)
    public void downloadfile(HttpServletResponse res) throws FileNotFoundException {
//        File file = ResourceUtils.getFile("classpath:linetemplate/lineInfo.xls");
//        InputStream bis = new FileInputStream(file);
        InputStream bis = getClass().getClassLoader().getResourceAsStream("linetemplate/lineInfo.xls");
        //   InputStream bis = IOUtils.toInputStream("1111", StandardCharsets.UTF_8);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=lineInfo.xls");
        byte[] buff = new byte[1024];
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, i);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    /**
     * 确认提交
     * @return
     */
    @RequestMapping(value = "/confirm")
    @ResponseBody
    public Object lineInfoConfirmSubmit() throws UnsupportedEncodingException {
        ParseLineInfo parseLineInfo = (ParseLineInfo) super.getSession().getAttribute(PARSELINEINFO);
        super.getSession().removeAttribute(PARSELINEINFO);
        lineInfoService.importLineInfo(parseLineInfo);
        ResultMap resultMap = generateParametFileService.generateLineParamFile(parseLineInfo);
        if(!resultMap.isSuccess()){
            //如果操作失败
            return ERROR;
        }
        return SUCCESS_TIP;
    }
    /**
     * 新增线路信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcLineInfo lineInfo) {
        lineInfo.setUpdateTime(DateUtil.getAllMsTime());
        lineInfo.setCreateTime(DateUtil.getAllMsTime());
        lineInfoService.insert(lineInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除线路信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer lineInfoId) {
        CcLineInfo lineInfo = lineInfoService.selectById(lineInfoId);
        lineInfoService.deleteLineInfoByLineId(lineInfo);
        return SUCCESS_TIP;
    }

    /**
     * 修改线路信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcLineInfo lineInfo) throws UnsupportedEncodingException {

        ResultMap resultMap = lineInfoService.updateLineInfo(lineInfo);
        /*lineInfo.setUpdateTime(DateUtil.getAllMsTime());
        lineInfo.setPltLineVersion(lineInfo.getPltLineVersion()+1);
        lineInfoService.updateById(lineInfo);

        ResultMap resultMap = generateParametFileService.generateLineParamFile(lineInfo.getLineId());*/
        if(!resultMap.isSuccess()){
            //如果操作失败
            return ERROR;
        }
        return SUCCESS_TIP;
    }

    /**
     * 线路信息管理详情
     */
    @RequestMapping(value = "/detail/{lineInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("lineInfoId") Integer lineInfoId) {
        return lineInfoService.selectById(lineInfoId);
    }
    /**
     * 线路信息管理详情
     */
    @RequestMapping(value = "/addbus/{lineInfoId}")
    public Object addBus(@PathVariable("lineInfoId") Integer lineInfoId, Model model){
        CcLineInfo ccLineInfo = lineInfoService.selectById(lineInfoId);
        model.addAttribute("lineId", ccLineInfo.getLineId());
        LogObjectHolder.me().set(ccLineInfo.getLineId());
        model.addAttribute("lineName", ccLineInfo.getName());
        LogObjectHolder.me().set(ccLineInfo.getName());
        return PREFIX + "lineInfo_addbus.html";
    }

    /**
     * eg:
     * 1230-->12.3
     * 1200-->12
     * 1203-->12.03
     * 价格和公里数都可以使用该方法
     * @param price
     * @return
     */
    private static String formatPrice(String price) {
        String str = price;
        if(str == null){
            return "";
        }else if (str.length() > 2) {
            str = new StringBuffer(str).insert(str.length() - 2, ".").toString();
        }else if(str.length() == 2){
            str = new StringBuffer("0."+ str).toString();
        }else if(str.length() == 1){
            str = new StringBuffer("0.0"+str).toString();
        }else{
            return str;
        }
        if (str.endsWith("00")) {
            str = str.substring(0, str.length() - 3);
        }else if (str.endsWith("0")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
}
