package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.pos.warpper.MchntPosWarpper;
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
import com.bmac.ffan.modular.pos.dao.MchntPosDao;
import com.bmac.ffan.common.persistence.model.CcMchntPos;
import com.bmac.ffan.modular.pos.service.IMchntPosService;

/**
 * 鍟嗘埛POS淇℃伅控制器
 *
 * @author 工具生成
 * @Date 2017-11-24 16:07:11
 */
@Controller
@RequestMapping("/mchntPos")
public class MchntPosController extends BaseController {

    private String PREFIX = "/pos/mchntPos/";

    @Autowired
    private IMchntPosService mchntPosService;

	@Resource
	MchntPosDao mchntPosDao;
	
    /**
     * 跳转到鍟嗘埛POS淇℃伅首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mchntPos.html";
    }

    /**
     * 跳转到添加鍟嗘埛POS淇℃伅
     */
    @RequestMapping("/mchntPos_add")
    public String mchntPosAdd() {
        return PREFIX + "mchntPos_add.html";
    }

    /**
     * 跳转到修改鍟嗘埛POS淇℃伅
     */
    @RequestMapping("/mchntPos_update/{mchntPosId}")
    public String mchntPosUpdate(@PathVariable Integer mchntPosId, Model model) {
        CcMchntPos mchntPos = mchntPosService.selectById(mchntPosId);
        model.addAttribute("mchntPos",mchntPos);
        LogObjectHolder.me().set(mchntPos);
        return PREFIX + "mchntPos_edit.html";
    }

    /**
     * 获取鍟嗘埛POS淇℃伅列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String mchntId,
    @RequestParam(required = false) String posId
    
    ) {
        //return mchntPosService.selectList(null);
        
        Page<CcMchntPos> page = new PageFactory<CcMchntPos>().defaultPage();
		 List<Map<String, Object>> result = mchntPosDao.selectMchntPoss(page, 
   		 mchntId,
   		 posId,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcMchntPos>) new MchntPosWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增鍟嗘埛POS淇℃伅
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcMchntPos mchntPos) {
        mchntPos.setCreateTime(DateUtil.getAllMsTime());
        mchntPosService.insert(mchntPos);
        return SUCCESS_TIP;
    }

    /**
     * 删除鍟嗘埛POS淇℃伅
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mchntPosId) {
        mchntPosService.deleteById(mchntPosId);
        return SUCCESS_TIP;
    }

    /**
     * 修改鍟嗘埛POS淇℃伅
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcMchntPos mchntPos) {
        mchntPosService.updateById(mchntPos);
        return SUCCESS_TIP;
    }

    /**
     * 鍟嗘埛POS淇℃伅详情
     */
    @RequestMapping(value = "/detail/{mchntPosId}")
    @ResponseBody
    public Object detail(@PathVariable("mchntPosId") Integer mchntPosId) {
        return mchntPosService.selectById(mchntPosId);
    }
}
