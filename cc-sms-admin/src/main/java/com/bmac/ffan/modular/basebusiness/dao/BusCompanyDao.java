package com.bmac.ffan.modular.basebusiness.dao;

import java.util.List;
import java.util.Map;

import com.bmac.ffan.core.node.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcBusCompany;

/**
 * 运营公司管理
 * @author wangshengjun
 *
 */
public interface BusCompanyDao {
	
	/**
	 * 查询运营公司
	 * @param page
	 * @param companyId
	 * @param companyName
	 * @param orderByField
	 * @param isAsc
	 * @return
	 */
	List<Map<String, Object>> selectBusCompanys(@Param("page") Page<CcBusCompany> page, @Param("companyId") Integer companyId, @Param("companyName") String companyName, @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);

	List<ZTreeNode> tree();
}
