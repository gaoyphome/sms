package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcPosVersion;

/**
 * POS固件版本Dao
 *
 * @author 工具生成
 * @Date 2017-11-24 15:21:00
 */
public interface PosVersionDao {

List<Map<String, Object>> selectPosVersions(
        @Param("page") Page<CcPosVersion> page,

        @Param("firmVersion") String firmVersion,

        @Param("mchntId") String mchntId,

        @Param("posId") String posId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
