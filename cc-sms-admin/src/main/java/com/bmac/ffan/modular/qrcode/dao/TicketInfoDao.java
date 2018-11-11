package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcTicketInfo;

/**
 * 濂楃エ淇℃伅Dao
 *
 * @author 工具生成
 * @Date 2017-11-27 09:12:25
 */
public interface TicketInfoDao {

List<Map<String, Object>> selectTicketInfos(
        @Param("page") Page<CcTicketInfo> page,

        @Param("ticketId") String ticketId,

        @Param("ticketName") String ticketName,

        @Param("userId") String userId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
