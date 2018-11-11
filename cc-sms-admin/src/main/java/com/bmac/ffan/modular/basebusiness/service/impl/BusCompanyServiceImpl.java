package com.bmac.ffan.modular.basebusiness.service.impl;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.constant.DSEnum;
import com.bmac.ffan.core.mutidatasource.annotion.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.dao.CcBusCompanyMapper;
import com.bmac.ffan.common.persistence.model.CcBusCompany;
import com.bmac.ffan.modular.basebusiness.service.IBusCompanyService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 运营公司Service
 *
 * @author xuzhanfu
 * @Date 2017-11-15 20:43:34
 */
@Service
public class BusCompanyServiceImpl extends ServiceImpl<CcBusCompanyMapper, CcBusCompany> implements IBusCompanyService {
    @Autowired
    private CcBusCompanyMapper busCompanyMapper;

    @Override
    @Transactional(readOnly = false)
    public void updateCompanys(CcBusCompany busCompany, CcBusCompany subBusCompany) {
        //更新父公司信息
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("company_id", busCompany.getCompanyId());
        this.update(busCompany, wrapper);
        //更新子公司信息
        wrapper = new EntityWrapper();
        wrapper.eq("company_id", subBusCompany.getCompanyId());
        this.update(subBusCompany, wrapper);
    }

    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_GUNS)
    public void testdbs1() {

        System.out.println("---1配置DBS-------->"+busCompanyMapper.selectCount(null));
        System.out.println("---2配置DBS-------->"+this.selectCount(null));
    }

    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public void testdbs() {
        System.out.println("---1默认DBS-------->"+busCompanyMapper.selectCount(null));
        System.out.println("---2默认DBS-------->"+this.selectCount(null));
    }
}
