package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcPosFileUpdate;

/**
 * POS黑名单文件表Dao
 *
 * @author 工具生成
 * @Date 2017-11-30 14:14:15
 */
public interface PosFileUpdateDao {

List<Map<String, Object>> selectPosFileUpdates(
        @Param("page") Page<CcPosFileUpdate> page,

        @Param("id") String id,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);

    void insertPosFileUpdate (@Param("posFileUpdate") CcPosFileUpdate posFileUpdate);
}
