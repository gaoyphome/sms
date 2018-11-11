package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcPosFile;

/**
 * POS文件Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 18:24:04
 */
public interface PosFileDao {

List<Map<String, Object>> selectPosFiles(
@Param("page") Page<CcPosFile> page, 

	@Param("filename") String filename,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
}
