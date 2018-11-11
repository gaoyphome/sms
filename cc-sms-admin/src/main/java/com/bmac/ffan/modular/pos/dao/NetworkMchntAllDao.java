package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcNetworkMchntAll;

/**
 * 鍟嗘埛淇℃伅Dao
 *
 * @author 工具生成
 * @Date 2017-11-24 16:33:11
 */
public interface NetworkMchntAllDao {

List<Map<String, Object>> selectNetworkMchntAlls(
        @Param("page") Page<CcNetworkMchntAll> page,

        @Param("mchntId") String mchntId,

        @Param("mchntname") String mchntname,

        @Param("contacts") String contacts,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
