package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.common.persistence.model.CcPosFile;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.util.CRCUtil;
import com.bmac.ffan.core.util.IOUtil;
import com.bmac.ffan.core.util.ToolUtil;
import com.bmac.ffan.modular.pos.warpper.PosFileBlacklistWarpper;
import com.bmac.ffan.scheme.util.NumberStringUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmac.ffan.core.log.LogObjectHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmac.ffan.common.constant.factory.PageFactory;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bmac.ffan.modular.basebusiness.util.CRC16;
import com.bmac.ffan.modular.basebusiness.util.HexCodec;
import com.bmac.ffan.modular.pos.dao.PosFileBlacklistDao;
import com.bmac.ffan.common.persistence.model.CcPosFileBlacklist;
import com.bmac.ffan.modular.pos.service.IPosFileBlacklistService;
import org.springframework.web.multipart.MultipartFile;

/**
 * POS黑名单文件 控制器
 *
 * @author 工具生成
 * @Date 2017-11-30 09:22:34
 */
@Controller
@RequestMapping("/posFileBlacklist")
public class PosFileBlacklistController extends BaseController {

    private String PREFIX = "/pos/posFileBlacklist/";

    @Autowired
    private IPosFileBlacklistService posFileBlacklistService;

	@Resource
	PosFileBlacklistDao posFileBlacklistDao;
	
    /**
     * 跳转到POS黑名单文件 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "posFileBlacklist.html";
    }

    /**
     * 跳转到添加POS黑名单文件 
     */
    @RequestMapping("/posFileBlacklist_add")
    public String posFileBlacklistAdd() {
        return PREFIX + "posFileBlacklist_add.html";
    }

    /**
     * 跳转到修改POS黑名单文件 
     */
    @RequestMapping("/posFileBlacklist_update/{posFileBlacklistId}")
    public String posFileBlacklistUpdate(@PathVariable Integer posFileBlacklistId, Model model) {
        CcPosFileBlacklist posFileBlacklist = posFileBlacklistService.selectById(posFileBlacklistId);
        model.addAttribute("posFileBlacklist",posFileBlacklist);
        LogObjectHolder.me().set(posFileBlacklist);
        return PREFIX + "posFileBlacklist_edit.html";
    }

    /**
     * 获取POS黑名单文件 列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String filename
    
    ) {
        //return posFileBlacklistService.selectList(null);
        
        Page<CcPosFileBlacklist> page = new PageFactory<CcPosFileBlacklist>().defaultPage();
		 List<Map<String, Object>> result = posFileBlacklistDao.selectPosFileBlacklists(page, 
   		 filename,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcPosFileBlacklist>) new PosFileBlacklistWarpper(result).warp());
	     return super.packForBT(page);
	     
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downLoad(@RequestParam(required = false) String id, HttpServletResponse res) {
        CcPosFileBlacklist ccPosFileBlacklist = posFileBlacklistService.selectById(id);
        String content = ccPosFileBlacklist.getContent();
        if(ToolUtil.isEmpty(content)){
            return;
        }
        InputStream bis = IOUtils.toInputStream(content, StandardCharsets.UTF_8);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + ccPosFileBlacklist.getFilename());
        byte[] buff = new byte[1024];
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, i);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 新增POS黑名单文件 
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestParam("filecontent") MultipartFile fileField,
                      @RequestParam(value = "filename", required = false) String filename,
                      @RequestParam(value = "version", required = false) Integer version,
                      HttpServletRequest request, Model model) {
        try {
            // 原始文件名
            String name = fileField.getOriginalFilename();
            if(!name.endsWith("MP")){
                return new ErrorTip("白名单文件名需要以MP结尾！");
            }
            long size = fileField.getSize();
            InputStream inputStream = fileField.getInputStream();
            byte[] data = IOUtil.getByteArray(inputStream);
            String content = NumberStringUtil.bytesToHexString(data);
            String crc = CRC16.CRC_16(data);
        //    String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            CcPosFileBlacklist ccPosFileBlacklist = new CcPosFileBlacklist();
            ccPosFileBlacklist.setFilename(filename);
            ccPosFileBlacklist.setFilesize(data.length);
            //检验码是否需要转成16进制表示?待定
            ccPosFileBlacklist.setCrc16(crc);
            ccPosFileBlacklist.setContent(content);
            Integer integer = posFileBlacklistDao.selectMaxVersion(filename);
            ccPosFileBlacklist.setVersion(integer+1);
            posFileBlacklistService.insert(ccPosFileBlacklist);
        } catch (IOException e) {
            return ERROR;
        }

        return SUCCESS_TIP;
    }


    public Object add(CcPosFileBlacklist posFileBlacklist) {
        posFileBlacklistService.insert(posFileBlacklist);
        return SUCCESS_TIP;
    }

    /**
     * 删除POS黑名单文件 
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer posFileBlacklistId) {
        posFileBlacklistService.deleteById(posFileBlacklistId);
        return SUCCESS_TIP;
    }

    /**
     * 修改POS黑名单文件 
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcPosFileBlacklist posFileBlacklist) {
        posFileBlacklistService.updateById(posFileBlacklist);
        return SUCCESS_TIP;
    }

    /**
     * POS黑名单文件 详情
     */
    @RequestMapping(value = "/detail/{posFileBlacklistId}")
    @ResponseBody
    public Object detail(@PathVariable("posFileBlacklistId") Integer posFileBlacklistId) {
        return posFileBlacklistService.selectById(posFileBlacklistId);
    }
}
