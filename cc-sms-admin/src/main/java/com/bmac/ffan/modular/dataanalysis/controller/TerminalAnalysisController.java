package com.bmac.ffan.modular.dataanalysis.controller;

import com.bmac.ffan.common.exception.BizExceptionEnum;
import com.bmac.ffan.common.exception.BussinessException;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.ToolUtil;
import com.bmac.ffan.modular.dataanalysis.service.ITerminalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: Buff
 * @Description: 终端分析
 * @Date: Created in 2018/3/12 15:28
 */

@Controller
@RequestMapping("/terminalanalysis")
public class TerminalAnalysisController extends BaseController {
    private String PREFIX = "/dataanalysis/terminalanalysis/";

    @Resource
    ITerminalAnalysisService terminalAnalysisService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "terminal.html";
    }

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
                return terminalAnalysisService.lineAdditionsByDay(graphStartTermTransDate, graphEndTermTransDate);
            }
            case 2:
            {
                return terminalAnalysisService.busAdditionsByDay(graphStartTermTransDate, graphEndTermTransDate);
            }
            case 3:
            {
                return terminalAnalysisService.posAdditionsByDay(graphStartTermTransDate, graphEndTermTransDate);
            }
            case 4:
            {
                return terminalAnalysisService.lineTotalByDay(graphStartTermTransDate, graphEndTermTransDate);
            }
            case 5://
            {
                return terminalAnalysisService.busTotalByDay(graphStartTermTransDate, graphEndTermTransDate);
            }
            case 6://
            {
                return terminalAnalysisService.posTotalByDay(graphStartTermTransDate, graphEndTermTransDate);
            }

            default:
            {
                return null;
            }
        }
    }
}
