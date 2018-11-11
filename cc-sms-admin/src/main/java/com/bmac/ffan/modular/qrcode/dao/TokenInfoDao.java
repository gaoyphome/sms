package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcTokenInfo;

/**
 * 濂楃エ浜岀淮鐮佷俊鎭�Dao
 *
 * @author 工具生成
 * @Date 2017-11-25 18:36:45
 */
public interface TokenInfoDao {

List<Map<String, Object>> selectTokenInfos(
        @Param("page") Page<CcTokenInfo> page,

        @Param("tokenId") String tokenId,

        @Param("userId") String userId,

        @Param("ticketId") String ticketId,

        @Param("posId") String posId,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
