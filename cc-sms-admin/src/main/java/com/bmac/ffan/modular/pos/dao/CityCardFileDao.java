package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcCityCardFile;

/**
 * 公交POS文件Dao
 *
 * @author 工具生成
 * @Date 2017-11-30 11:00:30
 */
public interface CityCardFileDao {

List<Map<String, Object>> selectCityCardFiles(
        @Param("page") Page<CcCityCardFile> page,

        @Param("filename") String filename,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
