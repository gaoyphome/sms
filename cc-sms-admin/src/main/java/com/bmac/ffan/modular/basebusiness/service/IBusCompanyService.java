package com.bmac.ffan.modular.basebusiness.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcBusCompany;

/**
 * 运营公司Service
 *
 * @author xuzhanfu
 * @Date 2017-11-15 20:43:34
 */
public interface IBusCompanyService extends IService<CcBusCompany>{
	public void updateCompanys(CcBusCompany busCompany, CcBusCompany subBusCompany);

	public void testdbs1();

	public void testdbs();
}