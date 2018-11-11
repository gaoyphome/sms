package com.bmac.ffan.modular.basebusiness.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.dao.CcLocalCardDiscountMapper;
import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.ratezf.RateRecordZ2;
import com.bmac.ffan.modular.basebusiness.ratezf.RateZ2;
import com.bmac.ffan.modular.basebusiness.service.ILocalCardDiscountService;
import com.bmac.ffan.modular.qrcode.dao.CardDiscountVersionDao;
import com.bmac.ffan.modular.qrcode.service.ICardDiscountVersionService;

/**
 * 实体本地卡折扣设置 Service
 *
 * @author xuzhanfu
 * @Date 2017-12-21 17:10:23
 */
@Service
public class LocalCardDiscountServiceImpl extends ServiceImpl<CcLocalCardDiscountMapper, CcLocalCardDiscount> implements ILocalCardDiscountService {
    @Resource
    CardDiscountVersionDao cardDiscountVersionDao;

    @Resource
    ICardDiscountVersionService cardDiscountVersionService;

    @Override
    public boolean importfile(RateZ2 rateZ2, String subCompanyId) {
        List<RateRecordZ2> recordZ2List = rateZ2.getRateRecordZ2List();
        List<CcLocalCardDiscount> localCardDiscountList = recordZ2List.stream().map(rrecord -> {
            CcLocalCardDiscount clcd = new CcLocalCardDiscount();
            clcd.setSubCompanyId(subCompanyId);
            clcd.setCardPhyType(rrecord.getPhysicType());
            clcd.setCardType(rrecord.getLogicType());
            clcd.setCardAttr(rrecord.getCardAttr());
            clcd.setChargeMode(rrecord.getCostMode());
            clcd.setMaxConsume(rrecord.getMaxAmount());
            clcd.setCreditBalance(rrecord.getOverdrawMax());
            clcd.setMinBalance(rrecord.getMinLimitOfCardMoney());
            clcd.setMaxBalance(rrecord.getMaxLimitOfCardMoney());
            clcd.setInRate(rrecord.getInRate());
            clcd.setInLimit(rrecord.getInAmount());
            clcd.setOutRate(rrecord.getOutRate());
            clcd.setOutLimit(rrecord.getOutAmount());
            clcd.setCreateTime(DateUtil.getAllMsTime());
            clcd.setUpdateTime(DateUtil.getAllMsTime());
            return clcd;
        }).collect(Collectors.toList());
        Map map = new HashMap<>();
        map.put("sub_company_id",subCompanyId);
        this.deleteByMap(map);
        this.insertBatch(localCardDiscountList);
        return cardDiscountVersionService.updateVersion(subCompanyId);
    }
}
