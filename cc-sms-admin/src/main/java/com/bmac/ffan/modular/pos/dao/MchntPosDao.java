package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcMchntPos;

/**
 * 鍟嗘埛POS淇℃伅Dao
 *
 * @author 工具生成
 * @Date 2017-11-24 16:07:11
 */
public interface MchntPosDao {

List<Map<String, Object>> selectMchntPoss(
        @Param("page") Page<CcMchntPos> page,

        @Param("mchntId") String mchntId,

        @Param("posId") String posId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
