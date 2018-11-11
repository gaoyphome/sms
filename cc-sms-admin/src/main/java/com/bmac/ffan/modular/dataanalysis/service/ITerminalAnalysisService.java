package com.bmac.ffan.modular.dataanalysis.service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/3/12 15:58
 */
public interface ITerminalAnalysisService {
    public List<Map<String, Object>> lineAdditionsByDay(String startDate, String endDate);

    public List<Map<String, Object>> lineTotalByDay(String startDate, String endDate);

    public List<Map<String, Object>> busAdditionsByDay(String startDate, String endDate);

    public List<Map<String, Object>> busTotalByDay(String startDate, String endDate);

    public List<Map<String, Object>> posAdditionsByDay(String startDate, String endDate);

    public List<Map<String, Object>> posTotalByDay(String startDate, String endDate);
}
