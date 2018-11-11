package com.bmac.ffan.modular.basebusiness.service;

import com.baomidou.mybatisplus.service.IService;
import com.bmac.ffan.common.persistence.model.*;
import com.bmac.ffan.modular.basebusiness.poixls.ParseLineInfo;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 线路信息管理Service
 *
 * @author xuzhanfu
 * @Date 2017-11-18 15:10:39
 */
public interface ILineInfoService  extends IService<CcLineInfo>{
    /**
     * 导入线路数据
     * @param ccLineInfo 线路信息
     * @param ccStationInfoList 站点信息列表
     * @param ccPriceInfoList 费率信息列表
     */
    void importLineInfo(CcLineInfo ccLineInfo, List<CcStationInfo> ccStationInfoList, List<CcPriceInfo> ccPriceInfoList);

    ResultMap updateLineInfo(CcLineInfo ccLineInfo) throws UnsupportedEncodingException;
    /**
     * 导入线路数据
     * @param parseLineInfo
     */
    void importLineInfo(ParseLineInfo parseLineInfo);

    /**
     * 删除线路信息，包括线路对应的站点和费率信息
     * @param lineInfo
     */
    void deleteLineInfoByLineId(CcLineInfo lineInfo);

}