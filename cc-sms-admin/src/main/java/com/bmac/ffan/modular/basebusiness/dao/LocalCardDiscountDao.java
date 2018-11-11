package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;

/**
 * 实体本地卡折扣设置 Dao
 *
 * @author 工具生成
 * @Date 2017-12-21 17:10:23
 */
public interface LocalCardDiscountDao {

List<Map<String, Object>> selectLocalCardDiscounts(
@Param("page") Page<CcLocalCardDiscount> page,

	@Param("companyId") String companyId,

	@Param("subCompanyId") String subCompanyId,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
}
