package com.bmac.ffan.modular.basebusiness.service.impl;

import com.bmac.ffan.modular.pos.service.IBusPosService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcBusInfo;
import com.bmac.ffan.common.persistence.dao.CcBusInfoMapper;
import com.bmac.ffan.modular.basebusiness.service.IBusInfoService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 车辆信息管理Service
 *
 * @author xuzhanfu
 * @Date 2017-11-18 14:24:33
 */
@Service
public class BusInfoServiceImpl extends ServiceImpl<CcBusInfoMapper, CcBusInfo> implements IBusInfoService {
    @Resource
    IBusPosService busPosService;

    @Transactional(readOnly = false)
    public boolean deleteBusInfo(Integer busInfoId){
        boolean result = false;
        CcBusInfo busInfo = this.selectById(busInfoId);
        if(busInfo != null){
            result = this.deleteById(busInfoId);
            Map map = new HashMap<>();
            map.put("bus_id",busInfo.getBusId());
            if(result){
                return  busPosService.deleteByMap(map);

            }
        }
        return false;
    }

}
