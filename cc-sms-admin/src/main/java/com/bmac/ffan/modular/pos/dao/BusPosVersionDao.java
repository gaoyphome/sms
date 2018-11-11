package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcBusPosVersion;

/**
 * 公交POS固件版本Dao
 *
 * @author 工具生成
 * @Date 2017-12-26 14:39:00
 */
public interface BusPosVersionDao {

List<Map<String, Object>> selectBusPosVersions(
        @Param("page") Page<CcBusPosVersion> page,

        @Param("companyId") String companyId,

        @Param("firmVersion") String firmVersion,

        @Param("posId") String posId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
