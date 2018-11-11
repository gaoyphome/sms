package com.bmac.ffan.modular.dataanalysis.service.impl;

import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.dataanalysis.dao.TerminalDao;
import com.bmac.ffan.modular.dataanalysis.service.ITerminalAnalysisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/3/12 15:58
 */
@Service
public class TerminalAnalysisServiceImpl implements ITerminalAnalysisService {
    @Resource
    private TerminalDao terminalDao;

    @Override
    public List<Map<String, Object>> lineAdditionsByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = terminalDao.selectLineNum(
                startDate.replace("-","")+"000000",
                endDate.replace("-","")+"235959");
        List<String> dayList = DateUtil.listOfDays(startDate,endDate);
        List<String> temp = new ArrayList<String>();

        /**
         * 查找出selectEcNum方法没统计出来的时间，也就是某天乘车码数为0的时间，添加到结果集中，并赋默认值
         */
        List<String> resultDayList = result.stream().map(map->map.get("createtime").toString()).collect(Collectors.toList());
        dayList.stream().forEach(d->{
            if(!resultDayList.contains(d)){
                temp.add(d);
            }
        });
        temp.stream().forEach(m->{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("createtime",m);
            map.put("dayadd",0);
            map.put("total",0);
            result.add(map);
        });
        List<Map<String, Object>> result_ = null;
        result_ = result.stream()
                .sorted(Comparator.comparing(map-> map.get("createtime").toString() ))
                .collect(Collectors.toList());
        return result_;
    }

    @Override
    public List<Map<String, Object>> busAdditionsByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = terminalDao.selectBusNum(
                startDate.replace("-","")+"000000",
                endDate.replace("-","")+"235959");
        List<String> dayList = DateUtil.listOfDays(startDate,endDate);
        List<String> temp = new ArrayList<String>();

        /**
         * 查找出selectEcNum方法没统计出来的时间，也就是某天乘车码数为0的时间，添加到结果集中，并赋默认值
         */
        List<String> resultDayList = result.stream().map(map->map.get("createtime").toString()).collect(Collectors.toList());
        dayList.stream().forEach(d->{
            if(!resultDayList.contains(d)){
                temp.add(d);
            }
        });
        temp.stream().forEach(m->{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("createtime",m);
            map.put("dayadd",0);
            map.put("total",0);
            result.add(map);
        });
        List<Map<String, Object>> result_ = null;
        result_ = result.stream()
                .sorted(Comparator.comparing(map-> map.get("createtime").toString() ))
                .collect(Collectors.toList());
        return result_;
    }

    @Override
    public List<Map<String, Object>> posAdditionsByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = terminalDao.selectPosNum(
                startDate.replace("-","")+"000000",
                endDate.replace("-","")+"235959");
        List<String> dayList = DateUtil.listOfDays(startDate,endDate);
        List<String> temp = new ArrayList<String>();

        /**
         * 查找出selectEcNum方法没统计出来的时间，也就是某天乘车码数为0的时间，添加到结果集中，并赋默认值
         */
        List<String> resultDayList = result.stream().map(map->map.get("createtime").toString()).collect(Collectors.toList());
        dayList.stream().forEach(d->{
            if(!resultDayList.contains(d)){
                temp.add(d);
            }
        });
        temp.stream().forEach(m->{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("createtime",m);
            map.put("dayadd",0);
            map.put("total",0);
            result.add(map);
        });
        List<Map<String, Object>> result_ = null;
        result_ = result.stream()
                .sorted(Comparator.comparing(map-> map.get("createtime").toString() ))
                .collect(Collectors.toList());
        return result_;
    }

    @Override
    public List<Map<String, Object>> lineTotalByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = lineAdditionsByDay(startDate, endDate);
        int total_startdate = terminalDao.selectLineNumCounts(startDate.replace("-","")+"000000");
        final int[] total_day = {total_startdate};
        result.stream().forEach(m->{
            int dayNum = Integer.parseInt(m.get("dayadd").toString());
            total_day[0] = total_day[0] + dayNum;
            m.put("total", total_day[0]);
        });
        return result;
    }

    @Override
    public List<Map<String, Object>> busTotalByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = busAdditionsByDay(startDate, endDate);
        int total_startdate = terminalDao.selectBusNumCounts(startDate.replace("-","")+"000000");
        final int[] total_day = {total_startdate};
        result.stream().forEach(m->{
            int dayNum = Integer.parseInt(m.get("dayadd").toString());
            total_day[0] = total_day[0] + dayNum;
            m.put("total", total_day[0]);
        });
        return result;
    }

    @Override
    public List<Map<String, Object>> posTotalByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = posAdditionsByDay(startDate, endDate);
        int total_startdate = terminalDao.selectPosNumCounts(startDate.replace("-","")+"000000");
        final int[] total_day = {total_startdate};
        result.stream().forEach(m->{
            int dayNum = Integer.parseInt(m.get("dayadd").toString());
            total_day[0] = total_day[0] + dayNum;
            m.put("total", total_day[0]);
        });
        return result;
    }
}
