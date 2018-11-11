package com.bmac.ffan.modular.record.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcCardOrder;
import com.bmac.ffan.common.persistence.dao.CcCardOrderMapper;
import com.bmac.ffan.modular.record.service.ICardOrderService;

/**
 * 实体卡刷卡交易 Service
 *
 * @author xuzhanfu
 * @Date 2017-12-04 09:28:57
 */
@Service
public class CardOrderServiceImpl extends ServiceImpl<CcCardOrderMapper, CcCardOrder> implements ICardOrderService {


}
