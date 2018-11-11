package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcCardDiscountVersion;

/**
 * 瀹炰綋鍗＄増鏈�Dao
 *
 * @author 工具生成
 * @Date 2017-11-27 13:54:46
 */
public interface CardDiscountVersionDao {

List<Map<String, Object>> selectCardDiscountVersions(
        @Param("page") Page<CcCardDiscountVersion> page,

        @Param("subCompanyId") String subCompanyId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);

Integer selectMaxVersion(@Param("subCompanyId") String subCompanyId);
}
