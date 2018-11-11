package com.bmac.ffan.modular.record.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideTrans;

/**
 * 二维码乘车交易记录 Dao
 *
 * @author 工具生成
 * @Date 2017-12-01 19:55:21
 */
public interface QrcodeRideTransDao {
List<Map<String, Object>> selectQrcodeRideRecordByTrans(@Param("list") List<String> list);
List<Map<String, Object>> selectQrcodeRideTranss(
        @Param("page") Page<CcQrcodeRideTrans> page,

        @Param("companyId") String companyId,

        @Param("startTermTransDate") String startTermTransDate,

        @Param("endTermTransTime") String endTermTransTime,

        @Param("cardno") String cardno,

        @Param("userPhone") String userPhone,

        @Param("lineName") String lineName,

        @Param("state") String state,

        @Param("transState") String transState,

        @Param("tradeStatus") String tradeStatus,

        @Param("tradeTypeFlag") String tradeTypeFlag,

        @Param("posId") String posId,

        @Param("orderNo") String orderNo,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
