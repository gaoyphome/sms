package com.bmac.ffan.modular.qrcode.service;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcCardDiscountVersion;

/**
 * 瀹炰綋鍗＄増鏈�Service
 *
 * @author xuzhanfu
 * @Date 2017-11-27 13:54:46
 */
public interface ICardDiscountVersionService extends IService<CcCardDiscountVersion>{
    public boolean updateVersion(String subCompanyId);
}