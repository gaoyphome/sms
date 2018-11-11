package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcSysConfig;

/**
 * 系统参数管理Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 11:14:00
 */
public interface SysConfigDao {

List<Map<String, Object>> selectSysConfigs(
@Param("page") Page<CcSysConfig> page, 

	@Param("category") String category,
	
	@Param("paramKey") String paramKey,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
}
