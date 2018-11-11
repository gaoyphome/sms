package com.bmac.ffan.modular.pos.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcBlacklistVersion;
import com.bmac.ffan.common.persistence.dao.CcBlacklistVersionMapper;
import com.bmac.ffan.modular.pos.service.IBlacklistVersionService;

/**
 * POS黑名升级管理表Service
 *
 * @author xuzhanfu
 * @Date 2018-01-29 17:08:49
 */
@Service
public class BlacklistVersionServiceImpl extends ServiceImpl<CcBlacklistVersionMapper, CcBlacklistVersion> implements IBlacklistVersionService {


}
