package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeBlack;

/**
 * 浜岀淮鐮佹姌鎵�Dao
 *
 * @author 工具生成
 * @Date 2017-11-25 11:36:22
 */
public interface QrcodeBlackDao {

List<Map<String, Object>> selectQrcodeBlacks(
        @Param("page") Page<CcQrcodeBlack> page,

        @Param("tokenId") String tokenId,

        @Param("vsirBmacno") String vsirBmacno,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
