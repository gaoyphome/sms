package com.bmac.ffan.modular.basebusiness.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.CcCityCardFile;
import com.bmac.ffan.common.persistence.model.CcExchangeCardDiscount;
import com.bmac.ffan.common.persistence.model.CcLineInfo;
import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;
import com.bmac.ffan.common.persistence.model.CcMileageInfo;
import com.bmac.ffan.common.persistence.model.CcPriceInfo;
import com.bmac.ffan.common.persistence.model.CcStationInfo;
import com.bmac.ffan.common.persistence.model.ResultMap;
import com.bmac.ffan.modular.basebusiness.poixls.ParseLineInfo;

/**
 * 
 * @author xiongrl
 *
 */
public interface IGenerateParametFileService  extends IService<CcCityCardFile> {
	
	/**
	 * 生成线路参数文件\费率文件（M3/M4/M5/MP）
	 * @param lineInfo 线路信息
	 * @param stationInfos_up 上行站点信息
	 * @param priceInfos_up 上行票价信息
	 * @param mileageInfos_up 上行里程信息
	 * @param stationInfos_down 下行站点信息
	 * @param priceInfos_down 下行票价信息
	 * @param mileageInfos_down 下行里程信息
	 * @return
	 */
	public ResultMap generateLineParamFile(CcLineInfo lineInfo,List<CcStationInfo> stationInfos_up,List<CcPriceInfo> priceInfos_up,List<CcMileageInfo> mileageInfos_up ,List<CcStationInfo> stationInfos_down,List<CcPriceInfo> priceInfos_down,List<CcMileageInfo> mileageInfos_down);
	/**
	 * 生成线路参数文件\费率文件（M3/M4/M5/MP）
	 * @param lineId
	 * @return
	 */
	public ResultMap generateLineParamFile(String lineId) throws UnsupportedEncodingException;
	/**
	 * 生成线路参数文件\费率文件（M3/M4/M5/MP）
	 * @param parseLineInfo
	 * @return
	 */
	public ResultMap generateLineParamFile(ParseLineInfo parseLineInfo) throws UnsupportedEncodingException;
	
	/**
	 * 生成折扣率文件 M5
	 * @param sub_company_id 子公司ID
	 * @param localCardDiscountList 本地卡费率
	 * @param exchageCardDiscountList 互通卡费率
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultMap generateCardDiscountFile(String sub_company_id, List<CcLocalCardDiscount> localCardDiscountList, List<CcExchangeCardDiscount> exchageCardDiscountList) throws UnsupportedEncodingException;
}
