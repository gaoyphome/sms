package com.bmac.ffan.modular.basebusiness.service.impl;

import com.bmac.ffan.common.persistence.dao.CcMileageInfoMapper;
import com.bmac.ffan.common.persistence.model.*;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.dao.LineInfoDao;
import com.bmac.ffan.modular.basebusiness.poixls.ParseLineInfo;
import com.bmac.ffan.modular.basebusiness.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.dao.CcLineInfoMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 线路信息管理Service
 *
 * @author xuzhanfu
 * @Date 2017-11-18 15:10:39
 */
@Service
public class LineInfoServiceImpl extends ServiceImpl<CcLineInfoMapper, CcLineInfo> implements ILineInfoService {
    @Autowired
    private IStationInfoService stationInfoService;

    @Autowired
    private IPriceInfoService priceInfoService;

    @Autowired
    private IMileageInfoService mileageInfoService;
    @Autowired
    IGenerateParametFileService generateParametFileService;
    @Autowired
    IBusCompanyService busCompanyService;

    @Resource
    LineInfoDao lineInfoDao;

    @Override
    @Transactional(readOnly = false)
    public void importLineInfo(CcLineInfo ccLineInfo, List<CcStationInfo> ccStationInfoList, List<CcPriceInfo> ccPriceInfoList) {
        this.insert(ccLineInfo);
        stationInfoService.insertBatch(ccStationInfoList);
        priceInfoService.insertBatch(ccPriceInfoList);
    }

    @Override
    @Transactional(readOnly = false)
    public ResultMap updateLineInfo(CcLineInfo ccLineInfo) throws UnsupportedEncodingException {
        ccLineInfo.setUpdateTime(DateUtil.getAllMsTime());
        ccLineInfo.setPltLineVersion(ccLineInfo.getPltLineVersion()+1);

        Map map = new HashMap();
        map.put("line_id",ccLineInfo.getLineId());
        map.put("flag","1");//上行
        List<CcStationInfo> stationInfos_up = stationInfoService.selectByMap(map);

        map = new HashMap();
        map.put("line_id",ccLineInfo.getLineId());
        map.put("dir_flag","1");//上行
        List<CcPriceInfo> priceInfos_up = priceInfoService.selectByMap(map);
        List<CcMileageInfo> mileageInfos_up = mileageInfoService.selectByMap(map);

        map = new HashMap();
        map.put("line_id",ccLineInfo.getLineId());
        map.put("flag","0");//下行
        List<CcStationInfo> stationInfos_down = stationInfoService.selectByMap(map);

        map = new HashMap();
        map.put("line_id",ccLineInfo.getLineId());
        map.put("dir_flag","0");//下行
        List<CcPriceInfo> priceInfos_down = priceInfoService.selectByMap(map);
        List<CcMileageInfo> mileageInfos_down = mileageInfoService.selectByMap(map);

        ResultMap resultMap;
        resultMap = generateParametFileService.generateLineParamFile(ccLineInfo,stationInfos_up,priceInfos_up,mileageInfos_up,
                stationInfos_down,priceInfos_down,mileageInfos_down);
        if(!resultMap.isSuccess()){
            return resultMap;
        }
        this.updateById(ccLineInfo);
        return resultMap;
    }


    @Override
    @Transactional(readOnly = false)
    public void importLineInfo(ParseLineInfo parseLineInfo) {
        CcLineInfo ccLineInfo = parseLineInfo.getCcLineInfo();
        Integer maxversion = lineInfoDao.selectMaxVersion(ccLineInfo.getLineId());
        ccLineInfo.setPltLineVersion(maxversion == null ? 1 : maxversion + 1);

        List<CcStationInfo> ccStationInfoList = new ArrayList<>();
        ccStationInfoList.addAll(parseLineInfo.getCcStationInfoList_up());
        if(parseLineInfo.getCcStationInfoList_down().size() > 0){
        	ccStationInfoList.addAll(parseLineInfo.getCcStationInfoList_down());
        }

        List<CcPriceInfo> ccPriceInfoList = new ArrayList<>();
        ccPriceInfoList.addAll(parseLineInfo.getCcPriceInfoList_up());
        if(parseLineInfo.getCcPriceInfoList_down().size() > 0){
        	ccPriceInfoList.addAll(parseLineInfo.getCcPriceInfoList_down());
        }

        List<CcMileageInfo> ccMileageInfoList = new ArrayList<>();
        ccMileageInfoList.add(parseLineInfo.getCcMileageInfo_up());
        if(parseLineInfo.getCcMileageInfo_down().getLineId() != null){
        	ccMileageInfoList.add(parseLineInfo.getCcMileageInfo_down());
        }
        //插入前先删除
        Map map = new HashMap();
        map.put("line_id", ccLineInfo.getLineId());
        this.deleteByMap(map);
        mileageInfoService.deleteByMap(map);
        stationInfoService.deleteByMap(map);
        priceInfoService.deleteByMap(map);


        this.insert(ccLineInfo);
        busCompanyService.updateCompanys(parseLineInfo.getBusCompany(),parseLineInfo.getSubBusCompany());
        mileageInfoService.insertBatch(ccMileageInfoList);
        stationInfoService.insertBatch(ccStationInfoList);
        priceInfoService.insertBatch(ccPriceInfoList);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteLineInfoByLineId(CcLineInfo lineInfo) {
        this.deleteById(lineInfo.getId());
        Map map = new HashMap();
        map.put("line_id", lineInfo.getLineId());
        mileageInfoService.deleteByMap(map);
        stationInfoService.deleteByMap(map);
        priceInfoService.deleteByMap(map);
    }
}
