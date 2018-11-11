package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcBusPosFirmware;

/**
 * 公交POS固件信息Dao
 *
 * @author 工具生成
 * @Date 2017-11-30 13:55:52
 */
public interface BusPosFirmwareDao {

List<Map<String, Object>> selectBusPosFirmwares(
        @Param("page") Page<CcBusPosFirmware> page,

        @Param("firmVersion") String firmVersion,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);

    List<Map<String, Object>>  selectAllBusPosFirmwares();
}
