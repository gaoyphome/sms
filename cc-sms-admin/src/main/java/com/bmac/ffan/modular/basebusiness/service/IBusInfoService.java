package com.bmac.ffan.modular.basebusiness.service;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcBusInfo;

/**
 * 车辆信息管理Service
 *
 * @author xuzhanfu
 * @Date 2017-11-18 14:24:33
 */
public interface IBusInfoService extends IService<CcBusInfo>{
    public boolean deleteBusInfo(Integer busInfoId);
}