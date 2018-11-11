package com.bmac.ffan.modular.record.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideRecord;

/**
 * 二维码乘车记录Dao
 *
 * @author 工具生成
 * @Date 2017-12-01 15:34:29
 */
public interface QrcodeRideRecordDao {

List<Map<String, Object>> selectQrcodeRideRecords(
        @Param("page") Page<QrcodeRideRecordFat> page,

        @Param("companyId") String companyId,

        @Param("startTermTransDate") String startTermTransDate,

        @Param("endTermTransTime") String endTermTransTime,

        @Param("cardno") String cardno,

        @Param("userPhone") String userPhone,

        @Param("markLineId") String markLineId,

        @Param("downLineId") String downLineId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
