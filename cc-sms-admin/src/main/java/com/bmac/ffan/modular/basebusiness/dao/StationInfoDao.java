package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcStationInfo;

/**
 * 站点信息管理Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 16:00:30
 */
public interface StationInfoDao {

	List<Map<String, Object>> selectStationInfos(
			@Param("page") Page<CcStationInfo> page,

			@Param("name") String name,

			@Param("lineId") String lineId,

			@Param("orderByField") String orderByField,

			@Param("isAsc") boolean isAsc);

	List<Map<String, Object>> selectStationInfosByLineId(
			@Param("lineId") String lineId,

			@Param("flag") String flag);

	List<Map<String, Object>> selectStationPriceByLineId(
			@Param("lineId") String lineId,

			@Param("flag") String flag
	);
}