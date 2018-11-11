package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.common.persistence.model.CcQrcodeDubious;
import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeSysComplement;

/**
 * 鐢ㄦ埛琛ョエDao
 *
 * @author 工具生成
 * @Date 2017-11-25 16:17:30
 */
public interface QrcodeSysComplementDao {

List<Map<String, Object>> selectQrcodeSysComplements(
        @Param("page") Page<CcQrcodeSysComplement> page,

        @Param("vsirBmacno") String vsirBmacno,

        @Param("mouth") String mouth,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
void insertQrcodeSysComplement(@Param("qrcodeSysComplement") CcQrcodeSysComplement qrcodeSysComplement);
}
