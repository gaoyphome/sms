package com.bmac.ffan.modular.pos.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcPosInfo;
import com.bmac.ffan.common.persistence.dao.CcPosInfoMapper;
import com.bmac.ffan.modular.pos.service.IPosInfoService;

/**
 * POS信息Service
 *
 * @author xuzhanfu
 * @Date 2017-11-21 10:44:44
 */
@Service
public class PosInfoServiceImpl extends ServiceImpl<CcPosInfoMapper, CcPosInfo> implements IPosInfoService {


}
