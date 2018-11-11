package com.bmac.ffan.modular.pos.service.impl;

import com.bmac.ffan.modular.pos.service.IBusPosFirmwareService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcBusPosVersion;
import com.bmac.ffan.common.persistence.dao.CcBusPosVersionMapper;
import com.bmac.ffan.modular.pos.service.IBusPosVersionService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 公交POS固件版本Service
 *
 * @author xuzhanfu
 * @Date 2017-12-26 14:39:00
 */
@Service
public class BusPosVersionServiceImpl extends ServiceImpl<CcBusPosVersionMapper, CcBusPosVersion> implements IBusPosVersionService {
    @Resource
    IBusPosFirmwareService busPosFirmwareService;

    @Override
    @Transactional(readOnly = false)
    public boolean deletePosVersion(CcBusPosVersion busPosVersion) {
        boolean res1 = this.deleteById(busPosVersion.getId());
        Map map = new HashMap();
        map.put("firm_version", busPosVersion.getFirmVersion());
        map.put("pos_id",busPosVersion.getPosId());
        boolean res2 = busPosFirmwareService.deleteByMap(map);
        return (res1 && res2);
    }
}
