package com.bmac.ffan.modular.basebusiness.service.impl;

import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.ratezf.RateRecordZ2;
import com.bmac.ffan.modular.basebusiness.ratezf.RateRecordZ3;
import com.bmac.ffan.modular.basebusiness.ratezf.RateZ3;
import com.bmac.ffan.modular.basebusiness.service.IGenerateParametFileService;
import com.bmac.ffan.modular.qrcode.service.ICardDiscountVersionService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcExchangeCardDiscount;
import com.bmac.ffan.common.persistence.dao.CcExchangeCardDiscountMapper;
import com.bmac.ffan.modular.basebusiness.service.IExchangeCardDiscountService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 实体互通卡折扣设置 Service
 *
 * @author xuzhanfu
 * @Date 2017-12-21 17:18:21
 */
@Service
public class ExchangeCardDiscountServiceImpl extends ServiceImpl<CcExchangeCardDiscountMapper, CcExchangeCardDiscount> implements IExchangeCardDiscountService {
    @Resource
    ICardDiscountVersionService cardDiscountVersionService;
    @Override
    public boolean importfile(RateZ3 rateZ3, String subCompanyId) {
        List<RateRecordZ3> recordZ3List = rateZ3.getRateRecordZ3List();
        List<CcExchangeCardDiscount> exchangeCardDiscountList =
                recordZ3List.stream().map(rrecord -> {
                    CcExchangeCardDiscount cecd = new CcExchangeCardDiscount();
                    cecd.setSubCompanyId(subCompanyId);
                    cecd.setIin(rrecord.getIssuerNo());
                    cecd.setCardType(rrecord.getLogicType());
                    cecd.setChargeMode(rrecord.getCostMode());
                    cecd.setCreditBalance(rrecord.getOverdrawMax());
                    cecd.setMaxConsume(rrecord.getMaxAmount());
                    cecd.setMaxBalance(rrecord.getMaxLimitOfCardMoney());
                    cecd.setMinBalance("0");//需要确认
                    cecd.setInRate(rrecord.getInRate());
                    cecd.setInLimit(rrecord.getInAmount());
                    cecd.setOutRate(rrecord.getOutRate());
                    cecd.setOutLimit(rrecord.getOutAmount());
                    cecd.setCreateTime(DateUtil.getAllMsTime());
                    cecd.setUpdateTime(DateUtil.getAllMsTime());
                    return cecd;
                }).collect(Collectors.toList());
        Map map = new HashMap<>();
        map.put("sub_company_id",subCompanyId);
        this.deleteByMap(map);
        this.insertBatch(exchangeCardDiscountList);
        return cardDiscountVersionService.updateVersion(subCompanyId);
    }
}
