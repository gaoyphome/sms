package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeDubious;

/**
 * 浜岀淮鐮佸彲鐤戜俊鎭�Dao
 *
 * @author 工具生成
 * @Date 2017-11-25 14:06:16
 */
public interface QrcodeDubiousDao {

List<Map<String, Object>> selectQrcodeDubiouss(
        @Param("page") Page<CcQrcodeDubious> page,

        @Param("vsirBmacno") String vsirBmacno,

        @Param("tokenId") String tokenId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
void insertQrcodeDubious (@Param("qrcodeDubious") CcQrcodeDubious qrcodeDubious);
}
