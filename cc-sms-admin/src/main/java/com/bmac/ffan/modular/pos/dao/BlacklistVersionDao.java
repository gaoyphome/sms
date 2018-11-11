package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcBlacklistVersion;

/**
 * POS黑名升级管理表Dao
 *
 * @author 工具生成
 * @Date 2018-01-29 17:08:49
 */
public interface BlacklistVersionDao {

List<Map<String, Object>> selectBlacklistVersions(
        @Param("page") Page<CcBlacklistVersion> page,

        @Param("listVersion") String listVersion,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);

Integer selectMaxVersion(@Param("filename") String filename,
                         @Param("type") String type,
                         @Param("lineId") String lineId,
                         @Param("posId") String posId);
}
