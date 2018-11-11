package com.bmac.ffan.modular.dataanalysis.service.impl;

import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.dataanalysis.dao.IcecDao;
import com.bmac.ffan.modular.dataanalysis.service.IDataanalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/18 14:32
 */

@Service
//@Transactional
public class DataanalysisServiceImpl implements IDataanalysisService {

    @Resource
    private IcecDao icecDao;

    /**
     * 乘车码第天新增数量
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List<Map<String, Object>> ecardTokenAdditionsByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = icecDao.selectEcNum(
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

    /**
     * 乘车码总数递增情况
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public List<Map<String, Object>> ecardTokenTotalByDay(String startDate, String endDate) {
        List<Map<String, Object>> result = ecardTokenAdditionsByDay(startDate, endDate);
        int total_startdate = icecDao.selectEcNumCounts(startDate.replace("-","")+"000000");
        final int[] total_day = {total_startdate};
        result.stream().forEach(m->{
            int dayNum = Integer.parseInt(m.get("dayadd").toString());
            total_day[0] = total_day[0] + dayNum;
            m.put("total", total_day[0]);
        });
        return result;
    }

    public List<Map<String, Object>> ecardTransAdditionsByDay(String startDate, String endDate){
        List<Map<String, Object>> result = icecDao.selectEcTransNum(
                startDate.replace("-","")+"000000",
                endDate.replace("-","")+"235959");
        List<String> dayList = DateUtil.listOfDays(startDate,endDate);
        List<String> temp = new ArrayList<String>();

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

    public List<Map<String, Object>> ecardTransTotalByDay(String startDate, String endDate){
        List<Map<String, Object>> result = ecardTransAdditionsByDay(startDate, endDate);
        int total_startdate = icecDao.selectEcTransNumCounts(startDate.replace("-","")+"000000");
        final int[] total_day = {total_startdate};
        result.stream().forEach(m->{
            int dayNum = Integer.parseInt(m.get("dayadd").toString());
            total_day[0] = total_day[0] + dayNum;
            m.put("total", total_day[0]);
        });
        return result;
    }


    public List<Map<String, Object>> icTransAdditionsByDay(String startDate, String endDate){
        List<Map<String, Object>> result = icecDao.selectIcTransNum(
                startDate.replace("-","")+"000000",
                endDate.replace("-","")+"235959");
        List<String> dayList = DateUtil.listOfDays(startDate,endDate);
        List<String> temp = new ArrayList<String>();


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

    public List<Map<String, Object>> icTransTotalByDay(String startDate, String endDate){
        List<Map<String, Object>> result = icTransAdditionsByDay(startDate, endDate);
        int total_startdate = icecDao.selectIcTransNumCounts(startDate.replace("-","")+"000000");
        final int[] total_day = {total_startdate};
        result.stream().forEach(m->{
            int dayNum = Integer.parseInt(m.get("dayadd").toString());
            total_day[0] = total_day[0] + dayNum;
            m.put("total", total_day[0]);
        });
        return result;
    }

    /**
     * IC和电子卡总刷卡数
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Map<String, Object>> icecTransAdditionsByDay(String startDate, String endDate){
        //默认两个List的排序是一致的
        List<Map<String, Object>> icList = icTransAdditionsByDay(startDate, endDate);
        List<Map<String, Object>> ecList = ecardTransAdditionsByDay(startDate, endDate);
        if(icList.size() == ecList.size()){
            int size = icList.size();
            for(int i=0; i<size; i++){
                Map<String, Object> icmap = icList.get(i);
                Map<String, Object> ecmap = ecList.get(i);
                String icdayadd = icmap.get("dayadd").toString();
                String ecdayadd = ecmap.get("dayadd").toString();
                int icec = Integer.parseInt(icdayadd)+Integer.parseInt(ecdayadd);
                icmap.put("dayadd", icec);
            }
            return icList;
        }
        return null;
    }

    public List<Map<String, Object>> icecTransTotalByDay(String startDate, String endDate){
    //默认两个List的排序是一致的
        List<Map<String, Object>> icList = icTransTotalByDay(startDate, endDate);
        List<Map<String, Object>> ecList = ecardTransTotalByDay(startDate, endDate);
        if(icList.size() == ecList.size()){
            int size = icList.size();
            for(int i=0; i<size; i++){
                Map<String, Object> icmap = icList.get(i);
                Map<String, Object> ecmap = ecList.get(i);
                String ictotal = icmap.get("total").toString();
                String ectotal = ecmap.get("total").toString();
                int icec = Integer.parseInt(ictotal)+Integer.parseInt(ectotal);
                icmap.put("total", icec);
            }
            return icList;
        }
        return null;
    }
}
