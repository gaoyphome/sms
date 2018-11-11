package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcBusInfo;

/**
 * 车辆信息管理Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 14:31:28
 */
public interface BusInfoDao {

List<Map<String, Object>> selectBusInfos(
	@Param("page") Page<BusInfoFat> page,

	@Param("companyId") String companyId,

	@Param("lineId") String lineId,

	@Param("lineName") String lineName,
	
	@Param("plateNumber") String plateNumber,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
}
