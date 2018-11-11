package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcLineInfo;

/**
 * 线路信息管理Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 15:10:39
 */
public interface LineInfoDao {

List<Map<String, Object>> selectLineInfos(
@Param("page") Page<CcLineInfo> page,

	@Param("companyId") String companyId,

	@Param("name") String name,
	
	@Param("lineId") String lineId,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);

Integer selectMaxVersion(@Param("lineId") String lineId);
}
