package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeToken;

/**
 * 铏氭嫙鍗″彿Dao
 *
 * @author 工具生成
 * @Date 2017-11-25 17:33:32
 */
public interface QrcodeTokenDao {

List<Map<String, Object>> selectQrcodeTokens(
        @Param("page") Page<CcQrcodeToken> page,

        @Param("vsirBmacno") String vsirBmacno,

        @Param("userId") String userId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
