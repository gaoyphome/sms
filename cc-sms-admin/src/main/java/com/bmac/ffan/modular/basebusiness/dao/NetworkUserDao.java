package com.bmac.ffan.modular.basebusiness.dao;

import com.bmac.ffan.core.base.controller.BaseController;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.plugins.Page;
import com.bmac.ffan.common.persistence.model.CcNetworkUser;

/**
 * 用户信息Dao
 *
 * @author 工具生成
 * @Date 2017-11-18 17:10:13
 */
public interface NetworkUserDao {

List<Map<String, Object>> selectNetworkUsers(
@Param("page") Page<CcNetworkUser> page, 

	@Param("username") String username,
	
	@Param("mobile") String mobile,
	
	@Param("orderByField") String orderByField, 

	@Param("isAsc") boolean isAsc);
}
