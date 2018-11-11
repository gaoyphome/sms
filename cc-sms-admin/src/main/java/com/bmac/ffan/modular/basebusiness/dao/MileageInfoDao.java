package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcMileageInfo;

/**
 * 里程信息管理Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 16:48:11
 */
public interface MileageInfoDao {

List<Map<String, Object>> selectMileageInfos(
@Param("page") Page<CcMileageInfo> page, 

	@Param("lineId") String lineId,
	
	@Param("mileageValue") String mileageValue,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
List<Map<String, Object>>selectMileageInfoByLineId(
		@Param("lineId") String lineId,

		@Param("flag") String flag
);
}
