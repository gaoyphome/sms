package com.bmac.ffan.modular.dataanalysis.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/3/12 15:59
 */
public interface TerminalDao {
    /**
     * 查询时间段内线路每日新增获取个数
     * @param graphStartTermTransDate
     * @param graphEndTermTransDate
     * @return
     */
    List<Map<String, Object>> selectLineNum(
            @Param("startTermTransDate") String graphStartTermTransDate,

            @Param("endTermTransDate") String graphEndTermTransDate
    );
    /**
     * 查询某时间点前线路总数
     * @param startTermTransDate
     * @return
     */
    int selectLineNumCounts(@Param("startTermTransDate") String startTermTransDate);

    /**
     * 查询时间段内车辆每日新增获取个数
     * @param graphStartTermTransDate
     * @param graphEndTermTransDate
     * @return
     */
    List<Map<String, Object>> selectBusNum(
            @Param("startTermTransDate") String graphStartTermTransDate,

            @Param("endTermTransDate") String graphEndTermTransDate
    );
    /**
     * 查询某时间点前线路总数
     * @param startTermTransDate
     * @return
     */
    int selectBusNumCounts(@Param("startTermTransDate") String startTermTransDate);
    /**
     * 查询时间段内POS每日新增获取个数
     * @param graphStartTermTransDate
     * @param graphEndTermTransDate
     * @return
     */
    List<Map<String, Object>> selectPosNum(
            @Param("startTermTransDate") String graphStartTermTransDate,

            @Param("endTermTransDate") String graphEndTermTransDate
    );
    /**
     * 查询某时间点前POS总数
     * @param startTermTransDate
     * @return
     */
    int selectPosNumCounts(@Param("startTermTransDate") String startTermTransDate);
}
