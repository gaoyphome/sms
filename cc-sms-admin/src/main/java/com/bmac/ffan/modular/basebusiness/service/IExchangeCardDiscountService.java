package com.bmac.ffan.modular.basebusiness.service;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcExchangeCardDiscount;
import com.bmac.ffan.modular.basebusiness.ratezf.RateZ2;
import com.bmac.ffan.modular.basebusiness.ratezf.RateZ3;

/**
 * 实体互通卡折扣设置 Service
 *
 * @author xuzhanfu
 * @Date 2017-12-21 17:18:21
 */
public interface IExchangeCardDiscountService extends IService<CcExchangeCardDiscount>{
    public boolean importfile(RateZ3 rateZ3, String subCompanyId);
}