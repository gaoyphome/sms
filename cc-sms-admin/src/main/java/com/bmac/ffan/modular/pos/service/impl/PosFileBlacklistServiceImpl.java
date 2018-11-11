package com.bmac.ffan.modular.pos.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcPosFileBlacklist;
import com.bmac.ffan.common.persistence.dao.CcPosFileBlacklistMapper;
import com.bmac.ffan.modular.pos.service.IPosFileBlacklistService;

/**
 * POS黑名单文件信息 Service
 *
 * @author xuzhanfu
 * @Date 2017-11-30 09:22:35
 */
@Service
public class PosFileBlacklistServiceImpl extends ServiceImpl<CcPosFileBlacklistMapper, CcPosFileBlacklist> implements IPosFileBlacklistService {


}
