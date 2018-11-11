package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcPriceInfo;

/**
 * 票价信息管理Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 16:23:16
 */
public interface PriceInfoDao {

    List<Map<String, Object>> selectPriceInfos(
            @Param("page") Page<CcPriceInfo> page,

            @Param("lineId") String lineId,

            @Param("price") String price,

            @Param("orderByField") String orderByField,

            @Param("isAsc") boolean isAsc);

    List<Map<String, Object>> selectPriceInfoByLineId(
            @Param("lineId") String lineId,

            @Param("dirFlag") String dirFlag);
}


