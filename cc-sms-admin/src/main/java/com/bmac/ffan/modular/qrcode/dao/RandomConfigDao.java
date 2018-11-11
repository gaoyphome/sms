package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcRandomConfig;

/**
 * 闅忔満鏁扮储寮�Dao
 *
 * @author 工具生成
 * @Date 2017-11-27 10:17:05
 */
public interface RandomConfigDao {

List<Map<String, Object>> selectRandomConfigs(
        @Param("page") Page<CcRandomConfig> page,

        @Param("currIndex") String currIndex,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
