package com.bmac.ffan.modular.qrcode.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.qrcode.warpper.TokenInfoWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmac.ffan.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import com.bmac.ffan.common.constant.factory.PageFactory;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.qrcode.dao.TokenInfoDao;
import com.bmac.ffan.common.persistence.model.CcTokenInfo;
import com.bmac.ffan.modular.qrcode.service.ITokenInfoService;

/**
 * 套票二维码信息控制器
 *
 * @author 工具生成
 * @Date 2017-11-25 18:36:45
 */
@Controller
@RequestMapping("/tokenInfo")
public class TokenInfoController extends BaseController {

    private String PREFIX = "/qrcode/tokenInfo/";

    @Autowired
    private ITokenInfoService tokenInfoService;

	@Resource
	TokenInfoDao tokenInfoDao;
	
    /**
     * 跳转到套票二维码信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tokenInfo.html";
    }

    /**
     * 跳转到添加套票二维码信息
     */
    @RequestMapping("/tokenInfo_add")
    public String tokenInfoAdd() {
        return PREFIX + "tokenInfo_add.html";
    }

    /**
     * 跳转到修改套票二维码信息
     */
    @RequestMapping("/tokenInfo_update/{tokenInfoId}")
    public String tokenInfoUpdate(@PathVariable Integer tokenInfoId, Model model) {
        CcTokenInfo tokenInfo = tokenInfoService.selectById(tokenInfoId);
        model.addAttribute("tokenInfo",tokenInfo);
        LogObjectHolder.me().set(tokenInfo);
        return PREFIX + "tokenInfo_edit.html";
    }
    @RequestMapping("/tokenInfo_detail/{tokenInfoId}")
    public String tokenInfoDetail(@PathVariable Integer tokenInfoId, Model model) {
        CcTokenInfo tokenInfo = tokenInfoService.selectById(tokenInfoId);
        model.addAttribute("tokenInfo",tokenInfo);
        LogObjectHolder.me().set(tokenInfo);
        return PREFIX + "tokenInfo_detail.html";
    }
    /**
     * 获取套票二维码信息列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String tokenId,
    @RequestParam(required = false) String userId,
    @RequestParam(required = false) String ticketId,
    @RequestParam(required = false) String posId
    
    ) {
        //return tokenInfoService.selectList(null);
        
        Page<CcTokenInfo> page = new PageFactory<CcTokenInfo>().defaultPage();
		 List<Map<String, Object>> result = tokenInfoDao.selectTokenInfos(page, 
   		 tokenId,
   		 userId,
   		 ticketId,
   		 posId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcTokenInfo>) new TokenInfoWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增套票二维码信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcTokenInfo tokenInfo) {
        tokenInfoService.insert(tokenInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除套票二维码信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tokenInfoId) {
        tokenInfoService.deleteById(tokenInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改套票二维码信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcTokenInfo tokenInfo) {
        tokenInfoService.updateById(tokenInfo);
        return SUCCESS_TIP;
    }

    /**
     * 套票二维码信息详情
     */
    @RequestMapping(value = "/detail/{tokenInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("tokenInfoId") Integer tokenInfoId) {
        return tokenInfoService.selectById(tokenInfoId);
    }
}
