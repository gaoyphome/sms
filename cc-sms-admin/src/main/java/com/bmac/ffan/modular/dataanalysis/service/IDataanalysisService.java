package com.bmac.ffan.modular.dataanalysis.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/18 14:25
 */
public interface IDataanalysisService {
    public List<Map<String, Object>> ecardTokenAdditionsByDay(String startDate, String endDate);

    public List<Map<String, Object>> ecardTokenTotalByDay(String startDate, String endDate);

    public List<Map<String, Object>> ecardTransAdditionsByDay(String startDate, String endDate);

    public List<Map<String, Object>> ecardTransTotalByDay(String startDate, String endDate);

    public List<Map<String, Object>> icTransAdditionsByDay(String startDate, String endDate);

    public List<Map<String, Object>> icTransTotalByDay(String startDate, String endDate);

    public List<Map<String, Object>> icecTransAdditionsByDay(String startDate, String endDate);

    public List<Map<String, Object>> icecTransTotalByDay(String startDate, String endDate);
}
