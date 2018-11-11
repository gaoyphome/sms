package com.bmac.ffan.modular.pos.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcPosFileUpdate;
import com.bmac.ffan.common.persistence.dao.CcPosFileUpdateMapper;
import com.bmac.ffan.modular.pos.service.IPosFileUpdateService;

/**
 * POS黑名单文件表Service
 *
 * @author xuzhanfu
 * @Date 2017-11-30 14:14:15
 */
@Service
public class PosFileUpdateServiceImpl extends ServiceImpl<CcPosFileUpdateMapper, CcPosFileUpdate> implements IPosFileUpdateService {


}
