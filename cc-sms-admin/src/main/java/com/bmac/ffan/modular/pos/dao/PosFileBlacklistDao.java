package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcPosFileBlacklist;

/**
 * POS黑名单文件信息 Dao
 *
 * @author 工具生成
 * @Date 2017-11-30 09:22:35
 */
public interface PosFileBlacklistDao {

List<Map<String, Object>> selectPosFileBlacklists(
        @Param("page") Page<CcPosFileBlacklist> page,

        @Param("filename") String filename,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);

    int selectMaxVersion(@Param("filename") String fileName);
}
