package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeTokenInfo;

/**
 * 浜岀淮鐮乀okenDao
 *
 * @author 工具生成
 * @Date 2017-11-25 17:55:29
 */
public interface QrcodeTokenInfoDao {

List<Map<String, Object>> selectQrcodeTokenInfos(
        @Param("page") Page<CcQrcodeTokenInfo> page,

        @Param("vsirBmacno") String vsirBmacno,

        @Param("userPhone") String userPhone,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
