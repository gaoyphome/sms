package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcExchangeCardDiscount;

/**
 * 实体互通卡折扣设置 Dao
 *
 * @author 工具生成
 * @Date 2017-12-21 17:18:21
 */
public interface ExchangeCardDiscountDao {

List<Map<String, Object>> selectExchangeCardDiscounts(
        @Param("page") Page<CcExchangeCardDiscount> page,

        @Param("companyId") String companyId,

        @Param("subCompanyId") String subCompanyId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
