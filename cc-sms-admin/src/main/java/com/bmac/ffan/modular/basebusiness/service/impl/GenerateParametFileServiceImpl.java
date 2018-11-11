package com.bmac.ffan.modular.basebusiness.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bmac.ffan.modular.basebusiness.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.constant.FileConstant;
import com.bmac.ffan.common.persistence.dao.CcCardDiscountVersionMapper;
import com.bmac.ffan.common.persistence.dao.CcCityCardFileMapper;
import com.bmac.ffan.common.persistence.dao.CcStationInfoMapper;
import com.bmac.ffan.common.persistence.model.CcBusCompany;
import com.bmac.ffan.common.persistence.model.CcCardDiscountVersion;
import com.bmac.ffan.common.persistence.model.CcCityCardFile;
import com.bmac.ffan.common.persistence.model.CcExchangeCardDiscount;
import com.bmac.ffan.common.persistence.model.CcLineInfo;
import com.bmac.ffan.common.persistence.model.CcLocalCardDiscount;
import com.bmac.ffan.common.persistence.model.CcMileageInfo;
import com.bmac.ffan.common.persistence.model.CcPriceInfo;
import com.bmac.ffan.common.persistence.model.CcStationInfo;
import com.bmac.ffan.common.persistence.model.ResultMap;
import com.bmac.ffan.modular.basebusiness.poixls.ParseLineInfo;
import com.bmac.ffan.modular.basebusiness.util.CRC16;
import com.bmac.ffan.modular.basebusiness.util.HexCodec;
import com.bmac.ffan.modular.basebusiness.util.StringUtil;
import com.bmac.ffan.modular.qrcode.service.ICardDiscountVersionService;

/**
 * 生成参数文件实现类
 * @author xiongrl
 *
 */
@Service
public class GenerateParametFileServiceImpl  extends ServiceImpl<CcCityCardFileMapper, CcCityCardFile> implements IGenerateParametFileService {
	
	private final static Logger logger = LoggerFactory.getLogger(GenerateParametFileServiceImpl.class);
	
	@Autowired
    private IBusCompanyService busCompanyService;
	@Autowired
	private ICardDiscountVersionService cardDiscountVersionService;
	@Autowired
	private ILineInfoService lineInfoService;
	@Autowired
	private IStationInfoService stationInfoService;
	@Autowired
	private IPriceInfoService priceInfoService;
	@Autowired
	private IMileageInfoService mileageInfoService;

	
	@Resource
    CcStationInfoMapper stationInfoMapper;
	@Resource
	CcCardDiscountVersionMapper cardDiscountVersionMapper;

	@Override
	public ResultMap generateLineParamFile(CcLineInfo lineInfo,List<CcStationInfo> stationInfos_up,List<CcPriceInfo> priceInfos_up,List<CcMileageInfo> mileageInfos_up ,List<CcStationInfo> stationInfos_down,List<CcPriceInfo> priceInfos_down,List<CcMileageInfo> mileageInfos_down){
		ParseLineInfo parseLineInfo = new ParseLineInfo();
		parseLineInfo.setCcLineInfo(lineInfo);
		parseLineInfo.setCcStationInfoList_up(stationInfos_up);
		parseLineInfo.setCcMileageInfo_up(mileageInfos_up.get(0));
		parseLineInfo.setCcPriceInfoList_up(priceInfos_up);

		parseLineInfo.setCcStationInfoList_down(stationInfos_down);
		parseLineInfo.setCcMileageInfo_down(mileageInfos_down.get(0));
		parseLineInfo.setCcPriceInfoList_down(priceInfos_down);

		return generateLineParamFile(parseLineInfo);
	}

	@Override
	public ResultMap generateLineParamFile(String lineId) throws UnsupportedEncodingException {
		Map map = new HashMap();
		map.put("line_id",lineId);
		List<CcLineInfo> lines = lineInfoService.selectByMap(map);
		CcLineInfo lineInfo = lines.get(0);
		lineInfo.setPltLineVersion(lineInfo.getPltLineVersion()+1);//版本号加一

		map = new HashMap();
		map.put("line_id",lineId);
		map.put("flag","1");//上行
		List<CcStationInfo> stationInfos_up = stationInfoService.selectByMap(map);

		map = new HashMap();
		map.put("line_id",lineId);
		map.put("dir_flag","1");//上行
		List<CcPriceInfo> priceInfos_up = priceInfoService.selectByMap(map);
		List<CcMileageInfo> mileageInfos_up = mileageInfoService.selectByMap(map);

		map = new HashMap();
		map.put("line_id",lineId);
		map.put("flag","0");//下行
		List<CcStationInfo> stationInfos_down = stationInfoService.selectByMap(map);

		map = new HashMap();
		map.put("line_id",lineId);
		map.put("dir_flag","0");//下行
		List<CcPriceInfo> priceInfos_down = priceInfoService.selectByMap(map);
		List<CcMileageInfo> mileageInfos_down = mileageInfoService.selectByMap(map);

		ParseLineInfo parseLineInfo = new ParseLineInfo();
		parseLineInfo.setCcLineInfo(lineInfo);
		parseLineInfo.setCcStationInfoList_up(stationInfos_up);
		parseLineInfo.setCcMileageInfo_up(mileageInfos_up.get(0));
		parseLineInfo.setCcPriceInfoList_up(priceInfos_up);

		parseLineInfo.setCcStationInfoList_down(stationInfos_down);
		parseLineInfo.setCcMileageInfo_down(mileageInfos_down.get(0));
		parseLineInfo.setCcPriceInfoList_down(priceInfos_down);

		return generateLineParamFile(parseLineInfo);
	}

	/**
	 * 生成折扣率文件 M5
	 * @param sub_company_id 子公司ID
	 * @param localCardDiscountList 本地卡费率
	 * @param exchageCardDiscountList 互通卡费率
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultMap generateCardDiscountFile(String sub_company_id, List<CcLocalCardDiscount> localCardDiscountList, List<CcExchangeCardDiscount> exchageCardDiscountList) throws UnsupportedEncodingException{
		ResultMap resultMap = new ResultMap(true);
		//折扣率合法性判断
		if(localCardDiscountList == null || localCardDiscountList.size()==0){
			return new ResultMap(false, "子公司ID为" +sub_company_id+ "本地卡折扣率不存在！");
		}
		if(exchageCardDiscountList == null || exchageCardDiscountList.size()==0){
			return new ResultMap(false, "子公司ID为" +sub_company_id+ "互通卡折扣率不存在！");
		}
		StringBuffer stringBuffer = new StringBuffer();
		Map map = new HashMap();
        map.put("sub_company_id",sub_company_id);
		//获取本地卡折扣率
        stringBuffer.append(generateLocalCardDiscount(localCardDiscountList));
		//获取互通卡折扣率
        stringBuffer.append(generateExchageCardDiscount(exchageCardDiscountList));
		
		String cardDiscountContent = String.format("%02x", 20)
				+ String.format("%04x", localCardDiscountList.size())
				+ String.format("%02x", 26)
				+ String.format("%04x", exchageCardDiscountList.size())
				+ stringBuffer.toString();
		
		int stringBufferSize = cardDiscountContent.length();
		
		CcCityCardFile cityCardFile= new CcCityCardFile();
		logger.debug("折扣率M5文件内容：" + cardDiscountContent);
		cityCardFile.setFilesize(stringBufferSize);
		cityCardFile.setContent(cardDiscountContent);
		cityCardFile.setFilename(FileConstant.FILE_MILES);
		List<CcCardDiscountVersion> cardDiscountVersionList = cardDiscountVersionService.selectByMap(map);
		CcCardDiscountVersion cardDiscountVersion = cardDiscountVersionList.get(0);
		cityCardFile.setVersion(cardDiscountVersion.getPltVersion());
		cityCardFile.setSubCompanyId(sub_company_id);
		//计算文件CRC
		cityCardFile.setCrc16(CRC16.CRC_16(HexCodec.hexDecode(cardDiscountContent)));
		//插入到表中
		boolean flag = insert(cityCardFile);
		if(flag){
			cardDiscountVersion.setCreateFlag(1);
			cardDiscountVersionMapper.updateAllColumnById(cardDiscountVersion);
		}
		return resultMap;
	}
	
	/**
	 * 生成本地卡折扣率信息
	 * @param localCardDiscountList
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String generateLocalCardDiscount(List<CcLocalCardDiscount> localCardDiscountList) throws UnsupportedEncodingException{
		StringBuffer stringBuffer = new StringBuffer();
		logger.info("获取本地卡折扣率信息");
		for (CcLocalCardDiscount localCardDiscount : localCardDiscountList) {
			//卡物理类型 1HEX
			String cardPhyType = localCardDiscount.getCardPhyType(); 
			stringBuffer.append(StringUtil.fillLeftZeroForByte(cardPhyType, 1));
			//卡逻辑类型 1HEX
			String cardType = localCardDiscount.getCardType().trim(); 
			stringBuffer.append(StringUtil.fillLeftZeroForByte(cardType, 1));
			//卡属性 1HEX
			String cardAttr = localCardDiscount.getCardAttr().trim();
			stringBuffer.append(StringUtil.fillLeftZeroForByte(cardAttr, 1));
			//收费模式 1HEX
			String chargeMode = localCardDiscount.getChargeMode();
			stringBuffer.append(StringUtil.fillLeftZeroForByte(chargeMode, 1));
			//卡内最小余额 2HEX 低位在前
			String minBalance = localCardDiscount.getMinBalance().trim();
			String minBalanceHex = StringUtil.bcdToHexAndFillZeroLeft(minBalance, 2);
			stringBuffer.append(StringUtil.heighToLow(minBalanceHex));
			//卡最大透支限额 2HEX 低位在前
			String creditBalance = localCardDiscount.getCreditBalance().trim();
			String creditBalanceHex = StringUtil.bcdToHexAndFillZeroLeft(creditBalance, 2);
			stringBuffer.append(StringUtil.heighToLow(creditBalanceHex));
			//卡内最大金额 4HEX 低位在前
			String maxBalance = localCardDiscount.getMaxBalance().trim();
			String maxBalanceHex = StringUtil.bcdToHexAndFillZeroLeft(maxBalance, 4);
			stringBuffer.append(StringUtil.heighToLow(maxBalanceHex));
			//最大扣款额 2HEX 低位在前
			String maxConsume = localCardDiscount.getMaxConsume().trim();
			String maxConsumeHex = StringUtil.bcdToHexAndFillZeroLeft(maxConsume, 2);
			stringBuffer.append(StringUtil.heighToLow(maxConsumeHex));
			//界内折扣率 1HEX
			String inRate = localCardDiscount.getInRate().trim();
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(inRate, 1));
			//界内优惠额度 2HEX 低位在前
			String inLimit = localCardDiscount.getInLimit().trim();
			String inLimitHex = StringUtil.bcdToHexAndFillZeroLeft(inLimit, 2);
			stringBuffer.append(StringUtil.heighToLow(inLimitHex));
			//界外折扣率 1HEX
			String outRate = localCardDiscount.getOutRate().trim();
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(outRate, 1));
			//界外优惠额度 2HEX 低位在前
			String outLimit = localCardDiscount.getOutLimit().trim();
			String outLimitHex = StringUtil.bcdToHexAndFillZeroLeft(outLimit, 2);
			stringBuffer.append(StringUtil.heighToLow(outLimitHex));
		}
		logger.debug("本地卡折扣率信息：" + stringBuffer.toString());
		return stringBuffer.toString();
	}
		
	/**
	 * 生成互通折扣率信息
	 * @param exchageCardDiscountList
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String generateExchageCardDiscount(List<CcExchangeCardDiscount> exchageCardDiscountList) throws UnsupportedEncodingException{
		StringBuffer stringBuffer = new StringBuffer();
		logger.info("获取互通卡折扣率信息");
		for (CcExchangeCardDiscount exchangeCardDiscount : exchageCardDiscountList) {
			//发行机构标识 8HEX
			String lin = exchangeCardDiscount.getIin().trim();
			stringBuffer.append(StringUtil.fillLeftZeroForByte(lin, 8));
			//卡逻辑类型 1HEX
			String cardType2 = exchangeCardDiscount.getCardType().trim();
			stringBuffer.append(StringUtil.fillLeftZeroForByte(cardType2, 1));
			//收费模式 1HEX
			String chargeMode = exchangeCardDiscount.getChargeMode();
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(chargeMode, 1));
			//卡最大透支限额 2HEX 低位在前
			String creditBalance2 = exchangeCardDiscount.getCreditBalance().trim();
			String creditBalance2Hex = StringUtil.bcdToHexAndFillZeroLeft(creditBalance2, 2);
			stringBuffer.append(StringUtil.heighToLow(creditBalance2Hex));
			//卡内最大金额 4HEX 低位在前
			String maxBalance2 = exchangeCardDiscount.getMaxBalance().trim();
			String maxBalance2Hex = StringUtil.bcdToHexAndFillZeroLeft(maxBalance2, 4);
			stringBuffer.append(StringUtil.heighToLow(maxBalance2Hex));
			//最大扣款额 2HEX 低位在前
			String maxConsume2 = exchangeCardDiscount.getMaxConsume().trim();
			String maxConsume2Hex = StringUtil.bcdToHexAndFillZeroLeft(maxConsume2, 2);
			stringBuffer.append(StringUtil.heighToLow(maxConsume2Hex));
			//界内折扣率 1HEX
			String inRate2 = exchangeCardDiscount.getInRate().trim();
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(inRate2, 1));
			//界内优惠额度 2HEX 低位在前
			String inLimit2 = exchangeCardDiscount.getInLimit().trim();
			String inLimit2Hex = StringUtil.bcdToHexAndFillZeroLeft(inLimit2, 2);
			stringBuffer.append(StringUtil.heighToLow(inLimit2Hex));
			//界外折扣率 1HEX
			String outRate2 = exchangeCardDiscount.getOutRate().trim();
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(outRate2, 1));
			//界外优惠额度 2HEX 低位在前
			String outLimit2 = exchangeCardDiscount.getOutLimit().trim();
			String outLimit2Hex = StringUtil.bcdToHexAndFillZeroLeft(outLimit2, 2);
			stringBuffer.append(StringUtil.heighToLow(outLimit2Hex));
			//最小余额 2HEX 低位在前
			String minBalance2 = exchangeCardDiscount.getMinBalance().trim();
			String minBalance2Hex  = "";
			if(StringUtils.isBlank(minBalance2)){
				minBalance2Hex= "0000";
			}else {
				minBalance2Hex = StringUtil.bcdToHexAndFillZeroLeft(minBalance2, 2);;
			}
			stringBuffer.append(StringUtil.heighToLow(minBalance2Hex));
		}
		logger.debug("互通卡折扣率信息：" + stringBuffer.toString());
		return stringBuffer.toString();
	}
	
	/**
	 * 生成二次发行信息文件 MP
	 * @param lineInfo 线路信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ResultMap generateDoubleReleaseFile(CcLineInfo lineInfo) throws UnsupportedEncodingException{
		ResultMap resultMap = new ResultMap(true);
		logger.info("开始生成二次发行信息文件 MP");
		//线路编号 HEX
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(lineInfo.getLineId(), 2));
		//线路名称
		String lineName = lineInfo.getName().trim();
		stringBuffer.append(StringUtil.stringtoHexAndFillZeroRight(lineName,16));
		//运营单位标识
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(lineInfo.getCompanyId(), 2));
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
        map.put("company_id",lineInfo.getCompanyId());
        List<CcBusCompany> list = busCompanyService.selectByMap(map);
        CcBusCompany busCompany = list.get(0);
		String companyName =  busCompany.getCompanyName().trim();
		stringBuffer.append(StringUtil.stringtoHexAndFillZeroRight(companyName, 40));
		String subCompanyId = lineInfo.getSubCompanyId().trim();
//		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(subCompanyId, 4));
		stringBuffer.append(StringUtil.fillLeftZeroForByte(subCompanyId, 4));
		map.put("company_id",subCompanyId);
		List<CcBusCompany> subList = busCompanyService.selectByMap(map);
        CcBusCompany subCompany = subList.get(0);
		String subCompanyName =  subCompany.getCompanyName().trim();
		stringBuffer.append(StringUtil.stringtoHexAndFillZeroRight(subCompanyName, 40));
		//线路属性 1
		String lineAttr = lineInfo.getLineAttr().trim();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(lineAttr, 1));
		//上行站点总数 1hex
		int stationsNumUp = lineInfo.getStationsNumUp();
		stringBuffer.append(String.format("%02x", stationsNumUp));
		//下行站点总数 1hex
		int stationsNumDn = lineInfo.getStationsNumDn();
		stringBuffer.append(String.format("%02x", stationsNumDn));
		//线路文件版本号 2hex
		String fileVersion = lineInfo.getFileVersion().trim();
		int i = fileVersion.lastIndexOf(".");
		if(i<=0){//如果版本号为0或者非a.b格式，则直接版本号补0凑2字节
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(fileVersion, 2));
		}else{
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(fileVersion.substring(0, i), 1));
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(fileVersion.substring(i+1), 1));
		}
		//换乘时间 2hex
		int exchangeTime = lineInfo.getExchangeTime();
		stringBuffer.append(String.format("%04x", exchangeTime));
		//基本票价 2hex
		int priceBase = lineInfo.getPriceBase();
		String priceBaseHex = String.format("%04x", priceBase);
		//高低位互换
		stringBuffer.append(StringUtil.heighToLow(priceBaseHex));
		//上行市界起点 1HEX
		String cityLeaveUp = lineInfo.getCityLeaveUp().trim();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(cityLeaveUp, 1));
		//下行市界起点 1HEX
		String cityLeaveDown = lineInfo.getCityLeaveDown().trim();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(cityLeaveDown, 1));
		//本地未定义卡处理模式 1HEX
		String localNoModel = lineInfo.getLocalNoMode().trim();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(localNoModel, 1));
		//异地未定义卡处理模式 1HEX
		String offsiteNoModel = lineInfo.getOffsiteNoModel().trim();
		if(StringUtils.isBlank(offsiteNoModel)){
			stringBuffer.append("00");
		}else {
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(offsiteNoModel, 1));
		}
		//车辆属性 1HEX
		String busAttr = lineInfo.getBusAttr().trim();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(busAttr, 1));
		//异地逃票补票模式 2HEX
		String offsiteTicketModel = lineInfo.getOffsiteTicketModel().trim();
		if(StringUtils.isBlank(offsiteTicketModel)){
			stringBuffer.append("0000");
		}else {
			stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(offsiteTicketModel, 2));
		}
		//上下车方向不同补票规则 1HEX
		String difTicketRule = lineInfo.getDifTicketRule().trim();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(difTicketRule, 1));
		//补票金额是否无折扣标识 2HEX
		String ticketDiscountFlag = lineInfo.getTicketDiscountFlag().trim();
		stringBuffer.append(StringUtil.bcdToHexAndFillZeroLeft(ticketDiscountFlag, 2));
		//功能开关 2HEX
		String functionSwitch = lineInfo.getFunctionSwitch().trim();
		stringBuffer.append(StringUtil.fillLeftZeroForByte(functionSwitch, 2));
		
		int stringBufferSize = stringBuffer.toString().length();
		logger.debug("二次发现费率MP文件内容："+stringBuffer.toString());
		CcCityCardFile cityCardFile= new CcCityCardFile();
		
		cityCardFile.setFilesize(stringBufferSize);
		cityCardFile.setLineId(lineInfo.getLineId());
		cityCardFile.setContent(stringBuffer.toString());
		cityCardFile.setFilename(FileConstant.FILE_HT_TWO_RELEASE);
		
		int pltFileVersion = lineInfo.getPltLineVersion();
		cityCardFile.setVersion(pltFileVersion);
		cityCardFile.setSubCompanyId(subCompanyId);
		//计算文件CRC
		cityCardFile.setCrc16(CRC16.CRC_16(HexCodec.hexDecode(stringBuffer.toString())));
		//插入到表中
		insert(cityCardFile);
		logger.info("生成二次发行信息文件 MP完成");
		return resultMap;
	}
	
	/**
	 * 生成票价文件 M4
	 * @param lineInfo 线路信息文件
	 * @param priceInfoList_up 上行票价
	 * @param priceInfoList_down 下行票价
	 * @return
	 */
	private ResultMap generatePriceFile(CcLineInfo lineInfo, List<CcPriceInfo> priceInfoList_up, List<CcPriceInfo> priceInfoList_down){
		ResultMap resultMap = new ResultMap(true);
		logger.info("开始生成票价文件 M4");
		String upPriceContent = "";
		String dnPriceContent = "";
		StringBuffer stringBuffer = new StringBuffer("02");
		Integer numDn = lineInfo.getStationsNumDn();
		upPriceContent = getPrcieContent(priceInfoList_up);
		stringBuffer.append(String.format("%04x", upPriceContent.length()/2));
		
		if(numDn == 0){
			 stringBuffer.append("000000");
			 stringBuffer.append(upPriceContent);
		}else {
			if(priceInfoList_down.size() > 0){
				dnPriceContent = getPrcieContent(priceInfoList_down);	
				stringBuffer.append("02");
				stringBuffer.append(String.format("%04x", dnPriceContent.length()/2));
				stringBuffer.append(upPriceContent);
				stringBuffer.append(dnPriceContent);
			}else{
				return new ResultMap(false,"下行票价不存在！");
			}
			 
		}
		CcCityCardFile cityCardFile= new CcCityCardFile();
		int stringBufferSize = stringBuffer.toString().length();
		cityCardFile.setFilesize(stringBufferSize);
		cityCardFile.setLineId(lineInfo.getLineId());
		cityCardFile.setContent(stringBuffer.toString());
		cityCardFile.setFilename(FileConstant.FILE_PRICE);
		
		int pltFileVersion = lineInfo.getPltLineVersion();
		cityCardFile.setVersion(pltFileVersion);
		cityCardFile.setSubCompanyId(lineInfo.getSubCompanyId());
		//计算文件CRC
		cityCardFile.setCrc16(CRC16.CRC_16(HexCodec.hexDecode(stringBuffer.toString())));
		//插入到表中
		insert(cityCardFile);
		logger.info("生成票价文件 M4完成");
		return resultMap;
	}
	
	/**
	 * 生成线路站点信息文件 M3
	 * @param lineInfo 线路信息
	 * @param mileages_up 上行里程list
	 * @param mileages_down 下行里程list
	 * @param stationInfoList_up 上行站点
	 * @param stationInfoList_down 下行站点
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private ResultMap generateLineMileageFile(CcLineInfo lineInfo, String [] mileages_up, String [] mileages_down, List<CcStationInfo> stationInfoList_up, List<CcStationInfo> stationInfoList_down) throws UnsupportedEncodingException{
		ResultMap resultMap = new ResultMap(true);
		logger.info("开始生成线路站点信息文件 M3");
		StringBuffer stringBuffer = new StringBuffer(String.format("%02x", 22));
		stringBuffer.append(String.format("%02x", stationInfoList_up.size()));
		//判断是否有下行站点信息
		if(stationInfoList_down == null || stationInfoList_down.size() == 0){
			stringBuffer.append("0000");
			String upStationBody = getMileageAndStationInfo(stationInfoList_up, mileages_up);
			stringBuffer.append(upStationBody);
		}else{
			stringBuffer.append(String.format("%02x", 22));
			stringBuffer.append(String.format("%02x", stationInfoList_down.size()));
			String upStationBody = getMileageAndStationInfo(stationInfoList_up, mileages_up);
			stringBuffer.append(upStationBody);
			String downStationBody = getMileageAndStationInfo(stationInfoList_down, mileages_down);
			stringBuffer.append(downStationBody);
		}
		
		CcCityCardFile cityCardFile= new CcCityCardFile();
		int stringBufferSize = stringBuffer.toString().length();
		cityCardFile.setFilesize(stringBufferSize);
		cityCardFile.setLineId(lineInfo.getLineId());
		cityCardFile.setContent(stringBuffer.toString());
		cityCardFile.setFilename(FileConstant.FILE_LINESITE);
		
		int pltFileVersion = lineInfo.getPltLineVersion();
		cityCardFile.setVersion(pltFileVersion);
		cityCardFile.setSubCompanyId(lineInfo.getSubCompanyId());
		//计算文件CRC
		cityCardFile.setCrc16(CRC16.CRC_16(HexCodec.hexDecode(stringBuffer.toString())));
		//插入到表中
		insert(cityCardFile);
		logger.info("生成线路站点信息文件 M3完成");
		return resultMap;
	}
	
	/**
	 * 拼装站点里程信息
	 * @param stationInfoList 站点list
	 * @param mileages 里程信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private String getMileageAndStationInfo(List<CcStationInfo> stationInfoList,String [] mileages) throws UnsupportedEncodingException{
		StringBuffer stringBuffer = new StringBuffer();
		logger.info("拼装线路站点信息");
		
		for (int i = 0; i <=  mileages.length; i++) {
			CcStationInfo stationInfo = stationInfoList.get(i);
			stringBuffer.append(String.format("%02x", stationInfo.getNum()));
			if(i==0){
				stringBuffer.append("0000");
			}else {
				String mileageHex = StringUtil.bcdToHexAndFillZeroLeft(mileages[i-1], 2);
				//高低位互换
				stringBuffer.append(StringUtil.heighToLow(mileageHex));
			}
			String stationName = stationInfo.getName().trim();
			//如果站点名称大于9汉字
			stationName = stationName.length()>9?stationName.substring(0, 9):stationName;
			stringBuffer.append(StringUtil.stringtoHexAndFillZeroRight(stationName, 19));
		}
		logger.info("拼装线路站点信息完成");
		return stringBuffer.toString();
	}
	
	/**
	 * 获取票价内容
	 * @param priceInfoList
	 * @return
	 */
	private String getPrcieContent(List<CcPriceInfo> priceInfoList) {
		StringBuffer stringBuffer = new StringBuffer();
		logger.info("拼装票价信息内容");
		for (int i = 0; i < priceInfoList.size(); i++) {
			for (int j = 0; j <= i; j++) {
				int price = Integer.parseInt((priceInfoList.get(j).getPrice().split(",")[i-j]));
				String priceHex = String.format("%04x", price);
				//高低位互换
				stringBuffer.append(StringUtil.heighToLow(priceHex));
			}
		}
		logger.info("拼装票价信息内容完成");
		return stringBuffer.toString();
	}



	/**
	 * 生成线路关联参数文件（M3\M4\MP）
	 * @throws UnsupportedEncodingException 
	 */
	public ResultMap generateLineParamFile(ParseLineInfo parseLineInfo){
		logger.info("生成线路关联参数文件");
		//参数校验
		ResultMap resultMap = checkParseLineInfo(parseLineInfo);
		if(!resultMap.isSuccess()){
			logger.error("生成线路关联参数文件数据校验未通过：" + resultMap.getMsg());
			return resultMap;
		}
		CcLineInfo lineInfo = parseLineInfo.getCcLineInfo();
		//上下行站点信息
		List<CcStationInfo> stationInfoList_up = parseLineInfo.getCcStationInfoList_up();
		List<CcStationInfo> stationInfoList_down = parseLineInfo.getCcStationInfoList_down();
		//上下行票价信息
		List<CcPriceInfo> priceInfoList_up = parseLineInfo.getCcPriceInfoList_up();
		List<CcPriceInfo> priceInfoList_down = parseLineInfo.getCcPriceInfoList_down();
		//上下行里程信息
		CcMileageInfo upMileageInfo = parseLineInfo.getCcMileageInfo_up();
		CcMileageInfo dwonMileageInfo = parseLineInfo.getCcMileageInfo_down();
		String [] mileage_up = upMileageInfo.getMileageValue().split(",");
		String [] mileage_down = null;
		if(dwonMileageInfo.getMileageValue() != null){
			mileage_down = dwonMileageInfo.getMileageValue().split(",");
		}
		
		try {
			//二次发行信息文件
			resultMap = generateDoubleReleaseFile(lineInfo);
			if(!resultMap.isSuccess()){
				return resultMap;
			}
			//站点公里数文件
			resultMap = generateLineMileageFile(lineInfo, mileage_up, mileage_down, stationInfoList_up, stationInfoList_down);
			//票价信息文件
			resultMap = generatePriceFile(lineInfo, priceInfoList_up, priceInfoList_down);
			if(!resultMap.isSuccess()){
				return resultMap;
			}
			//更新文件生成标识
			lineInfo.setCreateFlag(1);
			lineInfoService.updateById(lineInfo);
		} catch (Exception e) {
			logger.error("生成参数文件出现异常：",e);
			e.printStackTrace();
			return  new ResultMap(false,"生成参数文件出现异常");
		}
		
		logger.info("生成线路关联参数文件完成");
		return resultMap;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public ResultMap checkParseLineInfo(ParseLineInfo parseLineInfo){
		ResultMap resultMap = new ResultMap(true);
		CcLineInfo lineInfo = parseLineInfo.getCcLineInfo();
		//上下行站点信息
		List<CcStationInfo> stationInfoList_up = parseLineInfo.getCcStationInfoList_up();
		List<CcStationInfo> stationInfoList_down = parseLineInfo.getCcStationInfoList_down();
		//上下行票价信息
		List<CcPriceInfo> priceInfoList_up = parseLineInfo.getCcPriceInfoList_up();
		List<CcPriceInfo> priceInfoList_down = parseLineInfo.getCcPriceInfoList_down();
		//上下行里程信息
		CcMileageInfo upMileageInfo = parseLineInfo.getCcMileageInfo_up();
		CcMileageInfo dwonMileageInfo = parseLineInfo.getCcMileageInfo_down();
		
		String company_id = lineInfo.getCompanyId();
		String sub_company_id = lineInfo.getSubCompanyId();
		Map map = new HashMap();
		map.put("company_id",company_id);
        //线路信息判断
        if(lineInfo == null){
        	return new ResultMap(false, "线路信息未配置！");
        }
        //公司ID判断
        List<CcBusCompany> busCompanyList = busCompanyService.selectByMap(map);
        if(busCompanyList == null){
        	return new ResultMap(false, "公司ID"+company_id+"信息未配置！");
        }
        //子公司ID判断
        map.put("company_id",sub_company_id);
        List<CcBusCompany> subCompanyList = busCompanyService.selectByMap(map);
        if(subCompanyList == null){
        	return new ResultMap(false, "子公司ID"+sub_company_id+"息未配置！");
        }
		//上行站点规则判断
		if(stationInfoList_up == null || stationInfoList_up.size()==0){
			return new ResultMap(false, "上行站点信息不能为空！");
		}
		if(priceInfoList_up == null || priceInfoList_up.size()==0){
			return new ResultMap(false, "上行票价不能为空！");
		}
		if(upMileageInfo == null || upMileageInfo.getMileageValue()== null){
			return new ResultMap(false, "上行里程不能为空！");
		}
		
		String line_attr = lineInfo.getLineAttr();//线路属性：00H 非环行单一票制 01H非环行分段票制 02H环行单一票制 03H环行分段票制
		if(StringUtils.isNotBlank(line_attr)){
			if("0".equals(line_attr) || "1".equals(line_attr)){
				
				if(stationInfoList_down == null || stationInfoList_down.size() == 0){
					return new ResultMap(false, "非环形线路下行站点未配置！");
				}
				
				if(dwonMileageInfo.getMileageValue() == null){
					return new ResultMap(false, "非环形线路下行里程未配置！");
				}
				
				if(priceInfoList_down == null || priceInfoList_down.size() == 0){
					return new ResultMap(false, "非环形线路下行票价未配置！");
				}
			}
		}else{
			return new ResultMap(false, "线路属性不能为空！");
		}
		return resultMap;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(String.format("%04x", 200));
//		System.out.println(CRC16.CRC_16(HexCodec.hexDecode("1400161a0027010101106400D007A08601001027320000500000010601106400D007A08601001027320000500000020101106400D007A08601001027320000500000020301106400D007A08601001027320000500000020501106400D007A08601001027320000500000020601106400D007A086010010273200005000000210011100000000A08601001027000000000000021201113200D007A08601001027190000500000021301113200D007A08601001027190000500000021401106400D007A08601001027320000500000021501106400D007A08601001027320000500000021601106400D007A086010010273200005000000251011100000000A086010010270000000000000252011100000000A086010010270000000000000253011100000000A086010010270000000000000254011100000000A086010010270000000000000255011100000000A086010010270000000000000256011100000000A08601001027000000000000050101106400D007A08601001027320000500000050601106400D007A08601001027320000500000051201113200D007A08601001027190000500000051301113200D007A0860100102719000050000000083010ffffffff01100000A08601001027320000500000000001013120ffffffff01100000A08601001027320000500000000001023140ffffffff01100000A08601001027320000500000000001092410ffffffff01100000A08601001027320000500000000001131121ffffffff01100000A08601001027320000500000000001154540ffffffff01100000A08601001027320000500000000001182420ffffffff01100000A08601001027320000500000000001213630ffffffff01100000A08601001027320000500000000001273020ffffffff01100000A08601001027320000500000000001294950ffffffff01100000A08601001027320000500000000001303160ffffffff01100000A08601001027320000500000000001321920ffffffff01100000A08601001027320000500000000001333970ffffffff01100000A08601001027320000500000000001384730ffffffff01100000A08601001027320000500000000001396900ffffffff01100000A08601001027320000500000000001403040ffffffff01100000A08601001027320000500000000001413060ffffffff01100000A08601001027320000500000000001423050ffffffff01100000A08601001027320000500000000001433180ffffffff01100000A08601001027320000500000000001443110ffffffff01100000A08601001027320000500000000001453080ffffffff01100000A08601001027320000500000000001463030ffffffff01100000A08601001027320000500000000001473070ffffffff01100000A08601001027320000500000000001482450ffffffff01100000A08601001027320000500000000001513950ffffffff01100000A08601001027320000500000000001523990ffffffff01100000A08601001027320000500000000001533940ffffffff01100000A08601001027320000500000000001544010ffffffff01100000A08601001027320000500000000001554050ffffffff01100000A08601001027320000500000000001581270ffffffff01100000A08601001027320000500000000001656900ffffffff01100000A08601001027320000500000000001691210ffffffff01100000A08601001027320000500000000001701340ffffffff01100000A08601001027320000500000000001711380ffffffff01100000A08601001027320000500000000001721460ffffffff01100000A08601001027320000500000000001751410ffffffff01100000A08601001027320000500000000001761430ffffffff01100000A08601001027320000500000000001967950ffffffff01100000A08601001027320000500000000001984520ffffffff01100000A086010010273200005000000000")));
//		System.out.println(CRC16.CRC_16(HexCodec.hexDecode("300A0140C1052F313A2F4D330000000000000000000000000000000000000000000000000000000000000000000000")));
//		System.out.println(CRC16.CRC_16(HexCodec.hexDecode("1605165010000B5D8CCFAB8DFC3D7B5EAB1B100000000000000027800D3F4BBA8D4B0D2BBC0EF000000000000000000039600CBABD0C7C7C5B6AB000000000000000000000004f000B9E3D1F4B4F3BDD60000000000000000000000057201D0C7B9E2D3B0CAD3D4B0B1B1C3C50000000000050000D0C7B9E2D3B0CAD3D4B0B1B1C3C50000000000045000B9E3D1F4B4F3BDD6000000000000000000000003aa00CBABD0C7C7C5B6AB000000000000000000000002c800D3F4BBA8D4B0D2BBC0EF000000000000000000017201B5D8CCFAB8DFC3D7B5EAB1B100000000000000")));//
	}
}
