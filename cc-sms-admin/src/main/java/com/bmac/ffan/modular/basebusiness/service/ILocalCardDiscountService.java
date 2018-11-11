package com.bmac.ffan.modular.basebusiness.service;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;
import com.bmac.ffan.modular.basebusiness.ratezf.RateZ2;

/**
 * 实体本地卡折扣设置 Service
 *
 * @author xuzhanfu
 * @Date 2017-12-21 17:10:23
 */
public interface ILocalCardDiscountService extends IService<CcLocalCardDiscount>{
    public boolean importfile(RateZ2 rateZ2, String subCompanyId);
}