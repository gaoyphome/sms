package com.bmac.ffan.common.persistence.dao;

import com.bmac.ffan.common.persistence.model.CloudDataVersion;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-11-24
 */
public interface CloudDataVersionMapper extends BaseMapper<CloudDataVersion> {
	 /**
     * 获取最大黑名单版本
     * @return
     */
    Map<String, Object> getMaxBlackVersion();
}