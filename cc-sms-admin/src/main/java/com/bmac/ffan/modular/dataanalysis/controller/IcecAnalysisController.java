package com.bmac.ffan.modular.dataanalysis.controller;

import com.bmac.ffan.common.exception.BizExceptionEnum;
import com.bmac.ffan.common.exception.BussinessException;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;
import com.bmac.ffan.modular.dataanalysis.dao.IcecDao;
import com.bmac.ffan.modular.dataanalysis.service.IDataanalysisService;
import com.bmac.ffan.modular.dataanalysis.warpper.IcecAnalysisWarpper;
import com.bmac.ffan.modular.record.warpper.QrcodeRideRecordWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/14 10:20
 */
@Controller
@RequestMapping("/icec")
public class IcecAnalysisController extends BaseController {
    private String PREFIX = "/dataanalysis/icec/";

    @Resource
    IDataanalysisService dataanalysisService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "icec.html";
    }
    /**
     *
     */
    @RequestMapping("/graph")
    @ResponseBody
    public Object graph(
            @RequestParam(required = false) int graphDataType,
            @RequestParam(required = false) int graphLastday,
            @RequestParam(required = false) String graphStartTermTransDate,
            @RequestParam(required = false) String graphEndTermTransDate
    ) {
        if (ToolUtil.isOneEmpty(graphDataType, graphStartTermTransDate, graphEndTermTransDate)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        switch (graphDataType){
            case 1:
            {
                return null;
            }
            case 2:
            {
                return null;
            }
            case 3:
            {
                return null;
            }
            case 4:
            {
                return null;
            }
            case 5://电子卡每日新增刷卡笔数
            {
                return null;
            }
            case 6://乘车码每日新增获取个数
            {
                return this.dataanalysisService.ecardTokenAdditionsByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            case 7:
            {
                return this.dataanalysisService.ecardTransAdditionsByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            case 8:
            {
                return this.dataanalysisService.icTransAdditionsByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            case 9:
            {
                return this.dataanalysisService.icecTransAdditionsByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            case 10:
            {
                return null;
            }
            case 11:
            {
                return null;
            }
            case 12:
            {
                return null;
            }
            case 13://电子卡总刷卡数
            {
                return null;
            }
            case 14://乘车码总获取数
            {
                return this.dataanalysisService.ecardTokenTotalByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            case 15:
            {
                return this.dataanalysisService.ecardTransTotalByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            case 16:
            {
                return this.dataanalysisService.icTransTotalByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            case 17:
            {
                return this.dataanalysisService.icecTransTotalByDay(graphStartTermTransDate,graphEndTermTransDate);
            }
            default:
            {
                return null;
            }
        }
    }

    @RequestMapping("/report")
    public String report() {
        return "json:json";
    }
}
