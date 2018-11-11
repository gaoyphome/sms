package com.bmac.ffan.modular.pos.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcBusPos;

/**
 * 公交POS信息Dao
 *
 * @author 工具生成
 * @Date 2017-11-24 10:27:47
 */
public interface BusPosDao {

List<Map<String, Object>> selectBusPoss(
        @Param("page") Page<BusPosFat> page,

        @Param("companyId") String companyId,

        @Param("posId") String posId,

        @Param("plateNumber") String plateNumber,

        @Param("lineName") String lineName);

/**
* 更新长时间未通讯的终端
* @param intervalTime 间隔时长
* @return
*/
int updateNonCommunicationEquipment(@Param("intervalDateTime") String intervalDateTime);

String getLineIdByPosId(@Param("posId") String posId);
}
