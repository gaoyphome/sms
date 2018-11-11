package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcPosCardType;

/**
 * 可用卡类型Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 17:55:01
 */
public interface PosCardTypeDao {

List<Map<String, Object>> selectPosCardTypes(
@Param("page") Page<CcPosCardType> page, 

	@Param("mchntcode") String mchntcode,
	
	@Param("cardAttr") String cardAttr,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
}
