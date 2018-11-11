package com.bmac.ffan.modular.pos.service;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcBusPosVersion;

/**
 * 公交POS固件版本Service
 *
 * @author xuzhanfu
 * @Date 2017-12-26 14:39:00
 */
public interface IBusPosVersionService extends IService<CcBusPosVersion>{
    public boolean deletePosVersion(CcBusPosVersion busPosVersion);
}