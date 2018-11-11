package com.bmac.ffan.modular.record.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcTicketOrder;

/**
 * 套票订单 Dao
 *
 * @author 工具生成
 * @Date 2017-12-04 11:03:32
 */
public interface TicketOrderDao {

List<Map<String, Object>> selectTicketOrders(
        @Param("page") Page<CcTicketOrder> page,

        @Param("startTermTransDate") String startTermTransDate,

        @Param("endTermTransTime") String endTermTransTime,

        @Param("cardno") String cardno,

        @Param("orderNo") String orderNo,

        @Param("termId") String termId,

        @Param("productCode") String productCode,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
