package com.bmac.ffan.modular.pos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcBusPos;
import com.bmac.ffan.common.persistence.dao.CcBusPosMapper;
import com.bmac.ffan.modular.pos.dao.BusPosDao;
import com.bmac.ffan.modular.pos.service.IBusPosService;
import com.bmac.ffan.modular.system.dao.MenuDao;

/**
 * 公交POS信息Service
 *
 * @author xuzhanfu
 * @Date 2017-11-24 10:27:47
 */
@Service
public class BusPosServiceImpl extends ServiceImpl<CcBusPosMapper, CcBusPos> implements IBusPosService {

    @Resource
    BusPosDao busPosDao;
    
    /**
     * 更新长时间未通讯的终端
     * @param intervalTime 间隔时长
     * @return
     */
	public int updateNonCommunicationEquipment(String intervalDateTime){
		return busPosDao.updateNonCommunicationEquipment(intervalDateTime);
	}
}
