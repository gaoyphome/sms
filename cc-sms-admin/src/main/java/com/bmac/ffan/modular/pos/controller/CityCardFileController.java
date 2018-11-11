package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.common.persistence.model.CcPosFileBlacklist;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.CRCUtil;
import com.bmac.ffan.core.util.ToolUtil;
import com.bmac.ffan.modular.pos.warpper.CityCardFileWarpper;
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
import java.util.List;
import java.util.Map;
import com.bmac.ffan.modular.pos.dao.CityCardFileDao;
import com.bmac.ffan.common.persistence.model.CcCityCardFile;
import com.bmac.ffan.modular.pos.service.ICityCardFileService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公交POS文件 控制器
 *
 * @author 工具生成
 * @Date 2017-11-30 11:00:29
 */
@Controller
@RequestMapping("/cityCardFile")
public class CityCardFileController extends BaseController {

    private String PREFIX = "/pos/cityCardFile/";

    @Autowired
    private ICityCardFileService cityCardFileService;

	@Resource
	CityCardFileDao cityCardFileDao;
	
    /**
     * 跳转到公交POS文件 首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cityCardFile.html";
    }

    /**
     * 跳转到添加公交POS文件 
     */
    @RequestMapping("/cityCardFile_add")
    public String cityCardFileAdd() {
        return PREFIX + "cityCardFile_add.html";
    }

    /**
     * 跳转到修改公交POS文件 
     */
    @RequestMapping("/cityCardFile_update/{cityCardFileId}")
    public String cityCardFileUpdate(@PathVariable Integer cityCardFileId, Model model) {
        CcCityCardFile cityCardFile = cityCardFileService.selectById(cityCardFileId);
        model.addAttribute("cityCardFile",cityCardFile);
        LogObjectHolder.me().set(cityCardFile);
        return PREFIX + "cityCardFile_edit.html";
    }

    /**
     * 获取公交POS文件 列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String filename
    
    ) {
        //return cityCardFileService.selectList(null);
        
        Page<CcCityCardFile> page = new PageFactory<CcCityCardFile>().defaultPage();
		 List<Map<String, Object>> result = cityCardFileDao.selectCityCardFiles(page, 
   		 filename,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcCityCardFile>) new CityCardFileWarpper(result).warp());
	     return super.packForBT(page);
	     
    }
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downLoad(@RequestParam(required = false) String id, HttpServletResponse res) {
        CcCityCardFile ccCityCardFile = cityCardFileService.selectById(id);
        String content = ccCityCardFile.getContent();
        if(ToolUtil.isEmpty(content)){
            return;
        }
        InputStream bis = IOUtils.toInputStream(content, StandardCharsets.UTF_8);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + ccCityCardFile.getFilename());
        byte[] buff = new byte[1024];
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
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
     * 新增公交POS文件 
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestParam("filecontent") MultipartFile fileField,
                      @RequestParam(value = "filename", required = false) String filename,
                      @RequestParam(value = "version", required = false) Integer version,
                      @RequestParam(value = "lineId", required = false) String lineId,
                      @RequestParam(value = "subCompanyId", required = false) String subCompanyId,
                      HttpServletRequest request, Model model) {
        try {
            // 原始文件名
            //String name = fileField.getOriginalFilename();
            long size = fileField.getSize();
            InputStream inputStream = fileField.getInputStream();
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            CcCityCardFile ccCityCardFile = new CcCityCardFile();
            ccCityCardFile.setFilename(filename);
            ccCityCardFile.setVersion(version);
            ccCityCardFile.setLineId(lineId);
            ccCityCardFile.setSubCompanyId(subCompanyId);
            //检验码是否需要转成16进制表示?待定
            ccCityCardFile.setCrc16(CRCUtil.do_crc(result.getBytes("UTF-8"))+"");
            ccCityCardFile.setContent(result);
            ccCityCardFile.setFilesize(new Long(size).intValue());
            cityCardFileService.insert(ccCityCardFile);
        } catch (IOException e) {
            return ERROR;
        }

        return SUCCESS_TIP;
    }


    /**
     * 删除公交POS文件 
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer cityCardFileId) {
        cityCardFileService.deleteById(cityCardFileId);
        return SUCCESS_TIP;
    }

    /**
     * 修改公交POS文件 
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcCityCardFile cityCardFile) {
        cityCardFileService.updateById(cityCardFile);
        return SUCCESS_TIP;
    }

    /**
     * 公交POS文件 详情
     */
    @RequestMapping(value = "/detail/{cityCardFileId}")
    @ResponseBody
    public Object detail(@PathVariable("cityCardFileId") Integer cityCardFileId) {
        return cityCardFileService.selectById(cityCardFileId);
    }
}
