package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.modular.pos.warpper.PosFileUpdateWarpper;
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
import com.bmac.ffan.modular.pos.dao.PosFileUpdateDao;
import com.bmac.ffan.common.persistence.model.CcPosFileUpdate;
import com.bmac.ffan.modular.pos.service.IPosFileUpdateService;

/**
 * POS黑名单文件表控制器
 *
 * @author 工具生成
 * @Date 2017-11-30 14:14:14
 */
@Controller
@RequestMapping("/posFileUpdate")
public class PosFileUpdateController extends BaseController {

    private String PREFIX = "/pos/posFileUpdate/";

    @Autowired
    private IPosFileUpdateService posFileUpdateService;

	@Resource
	PosFileUpdateDao posFileUpdateDao;
	
    /**
     * 跳转到POS黑名单文件表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "posFileUpdate.html";
    }

    /**
     * 跳转到添加POS黑名单文件表
     */
    @RequestMapping("/posFileUpdate_add")
    public String posFileUpdateAdd() {
        return PREFIX + "posFileUpdate_add.html";
    }

    /**
     * 跳转到修改POS黑名单文件表
     */
    @RequestMapping("/posFileUpdate_update/{posFileUpdateId}")
    public String posFileUpdateUpdate(@PathVariable Integer posFileUpdateId, Model model) {
        CcPosFileUpdate posFileUpdate = posFileUpdateService.selectById(posFileUpdateId);
        model.addAttribute("posFileUpdate",posFileUpdate);
        LogObjectHolder.me().set(posFileUpdate);
        return PREFIX + "posFileUpdate_edit.html";
    }

    /**
     * 获取POS黑名单文件表列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String id
    
    ) {
        //return posFileUpdateService.selectList(null);
        
        Page<CcPosFileUpdate> page = new PageFactory<CcPosFileUpdate>().defaultPage();
		 List<Map<String, Object>> result = posFileUpdateDao.selectPosFileUpdates(page, 
   		 id,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcPosFileUpdate>) new PosFileUpdateWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增POS黑名单文件表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CcPosFileUpdate posFileUpdate) {
        posFileUpdateDao.insertPosFileUpdate(posFileUpdate);
        //posFileUpdateService.insert(posFileUpdate);
        return SUCCESS_TIP;
    }

    /**
     * 删除POS黑名单文件表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer posFileUpdateId) {
        posFileUpdateService.deleteById(posFileUpdateId);
        return SUCCESS_TIP;
    }

    /**
     * 修改POS黑名单文件表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcPosFileUpdate posFileUpdate) {
        posFileUpdateService.updateById(posFileUpdate);
        return SUCCESS_TIP;
    }

    /**
     * POS黑名单文件表详情
     */
    @RequestMapping(value = "/detail/{posFileUpdateId}")
    @ResponseBody
    public Object detail(@PathVariable("posFileUpdateId") Integer posFileUpdateId) {
        return posFileUpdateService.selectById(posFileUpdateId);
    }
}
