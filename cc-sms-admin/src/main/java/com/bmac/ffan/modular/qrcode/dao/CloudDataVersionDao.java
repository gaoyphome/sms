package com.bmac.ffan.modular.qrcode.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CloudDataVersion;

/**
 * 浜戝崱鏁版嵁鐗堟湰Dao
 *
 * @author 工具生成
 * @Date 2017-11-27 11:19:37
 */
public interface CloudDataVersionDao {

List<Map<String, Object>> selectCloudDataVersions(
        @Param("page") Page<CloudDataVersion> page,

        @Param("orderByField") String orderByField,

        @Param("isAsc") boolean isAsc);
}
