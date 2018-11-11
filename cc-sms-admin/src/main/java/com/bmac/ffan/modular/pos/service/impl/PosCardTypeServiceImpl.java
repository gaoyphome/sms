package com.bmac.ffan.modular.pos.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcPosCardType;
import com.bmac.ffan.common.persistence.dao.CcPosCardTypeMapper;
import com.bmac.ffan.modular.pos.service.IPosCardTypeService;

/**
 * 可用卡类型Service
 *
 * @author xuzhanfu
 * @Date 2017-11-18 17:55:01
 */
@Service
public class PosCardTypeServiceImpl extends ServiceImpl<CcPosCardTypeMapper, CcPosCardType> implements IPosCardTypeService {


}
