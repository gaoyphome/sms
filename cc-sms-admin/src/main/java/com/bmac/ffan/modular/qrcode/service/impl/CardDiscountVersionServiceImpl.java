package com.bmac.ffan.modular.qrcode.service.impl;

import com.bmac.ffan.core.util.DateUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.model.CcCardDiscountVersion;
import com.bmac.ffan.common.persistence.dao.CcCardDiscountVersionMapper;
import com.bmac.ffan.modular.qrcode.service.ICardDiscountVersionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 瀹炰綋鍗＄増鏈�Service
 *
 * @author xuzhanfu
 * @Date 2017-11-27 13:54:47
 */
@Service
public class CardDiscountVersionServiceImpl extends ServiceImpl<CcCardDiscountVersionMapper, CcCardDiscountVersion> implements ICardDiscountVersionService {

    public boolean updateVersion(String subCompanyId){
        int newVersion = 0;
        CcCardDiscountVersion ccdv = null;

        Map map = new HashMap<>();
        map.put("sub_company_id", subCompanyId);
        List<CcCardDiscountVersion> cardDiscountVersionList = this.selectByMap(map);
        if(!cardDiscountVersionList.isEmpty()){
            ccdv = cardDiscountVersionList.get(0);
            newVersion = ccdv.getPltVersion().intValue() + 1;
            ccdv.setPltVersion(newVersion);
            ccdv.setUpdateTime(DateUtil.getAllMsTime());
        }else{
            ccdv = new CcCardDiscountVersion();
            ccdv.setSubCompanyId(subCompanyId);
            ccdv.setPltVersion(0);
            ccdv.setCreateFlag(0);
            ccdv.setCreateTime(DateUtil.getAllMsTime());
            ccdv.setUpdateTime(DateUtil.getAllMsTime());
        }
        return this.insertOrUpdate(ccdv);
    }

}
