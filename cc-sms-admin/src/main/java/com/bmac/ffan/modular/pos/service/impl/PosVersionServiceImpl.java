package com.bmac.ffan.modular.pos.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcPosVersion;
import com.bmac.ffan.common.persistence.dao.CcPosVersionMapper;
import com.bmac.ffan.modular.pos.service.IPosVersionService;

/**
 * POS固件版本Service
 *
 * @author xuzhanfu
 * @Date 2017-11-24 15:21:00
 */
@Service
public class PosVersionServiceImpl extends ServiceImpl<CcPosVersionMapper, CcPosVersion> implements IPosVersionService {


}
