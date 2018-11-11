package com.bmac.ffan.modular.qrcode.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcQrcodeDiscount;

/**
 * 浜岀淮鐮佹姌鎵�Dao
 *
 * @author 工具生成
 * @Date 2017-11-24 17:26:30
 */
public interface QrcodeDiscountDao {

List<Map<String, Object>> selectQrcodeDiscounts(
        @Param("page") Page<CcQrcodeDiscount> page,

        @Param("subCompanyId") String subCompanyId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
    List<Map<String, Object>> selectQrcodeDiscountsById(

        @Param("subCompanyId") String subCompanyId);

}
