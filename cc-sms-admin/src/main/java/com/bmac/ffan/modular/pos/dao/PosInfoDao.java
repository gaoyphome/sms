package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcPosInfo;

/**
 * POS信息Dao
 *
 * @author 工具生成
 * @Date 2017-11-21 10:44:44
 */
public interface PosInfoDao {

List<Map<String, Object>> selectPosInfos(
@Param("page") Page<CcPosInfo> page, 

	@Param("posId") String posId,
	
	@Param("samId") String samId,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
}
