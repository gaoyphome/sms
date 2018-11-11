package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcTicketDetail;

/**
 * 濂楃エ璇︽儏Dao
 *
 * @author 工具生成
 * @Date 2017-11-27 09:45:48
 */
public interface TicketDetailDao {

List<Map<String, Object>> selectTicketDetails(
        @Param("page") Page<CcTicketDetail> page,

        @Param("ticketId") String ticketId,

        @Param("agentId") String agentId,

        @Param("mchntId") String mchntId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
