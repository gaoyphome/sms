package com.bmac.ffan.modular.dataanalysis.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/14 17:46
 */
public interface IcecDao {
    /**
     * 查询时间段内乘车码每日新增获取个数
     * @param graphStartTermTransDate
     * @param graphEndTermTransDate
     * @return
     */
    List<Map<String, Object>>  selectEcNum(
            @Param("startTermTransDate") String graphStartTermTransDate,

            @Param("endTermTransDate") String graphEndTermTransDate
    );

    /**
     * 查询某时间点前的所有乘车码总获取数
     * @param startTermTransDate
     * @return
     */
    int selectEcNumCounts(@Param("startTermTransDate") String startTermTransDate);


    /**
     * 查询时间段内电子卡每日新增刷卡笔数
     * @param graphStartTermTransDate
     * @param graphEndTermTransDate
     * @return
     */
    List<Map<String, Object>>  selectEcTransNum(
            @Param("startTermTransDate") String graphStartTermTransDate,

            @Param("endTermTransDate") String graphEndTermTransDate
    );

    /**
     * 查询某时间点前电子卡总刷卡数
     * @param startTermTransDate
     * @return
     */
    int selectEcTransNumCounts(@Param("startTermTransDate") String startTermTransDate);

    /**
     * 查询时间段内IC卡每日新增刷卡笔数
     * @param graphStartTermTransDate
     * @param graphEndTermTransDate
     * @return
     */
    List<Map<String, Object>>  selectIcTransNum(
            @Param("startTermTransDate") String graphStartTermTransDate,

            @Param("endTermTransDate") String graphEndTermTransDate
    );

    /**
     * 查询某时间点前IC卡总刷卡数
     * @param startTermTransDate
     * @return
     */
    int selectIcTransNumCounts(@Param("startTermTransDate") String startTermTransDate);

}
