package com.bmac.ffan.modular.record.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeRideTransError;

/**
 * 乘车交易异常信息表Dao
 *
 * @author 工具生成
 * @Date 2018-03-15 10:30:04
 */
public interface QrcodeRideTransErrorDao {

List<Map<String, Object>> selectQrcodeRideTransErrors(
        @Param("page") Page<CcQrcodeRideTransError> page,

        @Param("cardno") String cardno,

        @Param("order_no") String order_no,

        @Param("startTermTransDate") String startTermTransDate,

        @Param("endTermTransTime") String endTermTransTime,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
