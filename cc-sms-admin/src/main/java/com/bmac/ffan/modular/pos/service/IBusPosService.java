package com.bmac.ffan.modular.pos.service;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcBusPos;

/**
 * 公交POS信息Service
 *
 * @author xuzhanfu
 * @Date 2017-11-24 10:27:47
 */
public interface IBusPosService extends IService<CcBusPos>{
	public int updateNonCommunicationEquipment(String intervalTime);
}