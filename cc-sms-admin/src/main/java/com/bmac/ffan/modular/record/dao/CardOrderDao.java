package com.bmac.ffan.modular.record.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcCardOrder;

/**
 * 实体卡刷卡交易 Dao
 *
 * @author 工具生成
 * @Date 2017-12-04 09:28:57
 */
public interface CardOrderDao {

List<Map<String, Object>> selectCardOrders(
        @Param("page") Page<CcCardOrder> page,

        @Param("companyId") String companyId,

        @Param("startTermTransDate") String startTermTransDate,

        @Param("endTermTransTime") String endTermTransTime,

        @Param("cardno") String cardno,

        @Param("orderNo") String orderNo,

        @Param("markLineId") String markLineId,

        @Param("posId") String posId,

        @Param("downLineId") String downLineId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
