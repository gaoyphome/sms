package com.bmac.ffan.common.persistence.dao;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bmac.ffan.common.persistence.model.CcPosFileBlacklist;

/**
 * <p>
  * pos黑名单文件表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
public interface CcPosFileBlacklistMapper extends BaseMapper<CcPosFileBlacklist> {
	
	/**
	 * 获取中心黑白名单文件版本+1
	 * @return
	 */
	public Map<String, Object> getCenterFileListVersion();
	
}