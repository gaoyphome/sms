package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.util.CRCUtil;
import com.bmac.ffan.core.util.ToolUtil;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.bmac.ffan.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;

import com.bmac.ffan.common.constant.factory.PageFactory;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.bmac.ffan.modular.pos.dao.PosFileDao;
import com.bmac.ffan.common.persistence.model.CcPosFile;
import com.bmac.ffan.modular.pos.service.IPosFileService;
import com.bmac.ffan.modular.pos.warpper.PosFileWarpper;

/**
 * POS文件控制器
 *
 * @author 工具生成
 * @Date 2017-11-18 18:24:03
 */
@Controller
@RequestMapping("/posFile")
public class PosFileController extends BaseController {

    private String PREFIX = "/pos/posFile/";

    @Autowired
    private IPosFileService posFileService;

    @Resource
    PosFileDao posFileDao;

    /**
     * 跳转到POS文件首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "posFile.html";
    }

    /**
     * 跳转到添加POS文件
     */
    @RequestMapping("/posFile_add")
    public String posFileAdd() {
        return PREFIX + "posFile_add.html";
    }

    /**
     * 跳转到修改POS文件
     */
    @RequestMapping("/posFile_update/{posFileId}")
    public String posFileUpdate(@PathVariable Integer posFileId, Model model) {
        CcPosFile posFile = posFileService.selectById(posFileId);
        model.addAttribute("posFile", posFile);
        LogObjectHolder.me().set(posFile);
        return PREFIX + "posFile_edit.html";
    }

    /**
     * 获取POS文件列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(

            @RequestParam(required = false) String filename

    ) {
        //return posFileService.selectList(null);

        Page<CcPosFile> page = new PageFactory<CcPosFile>().defaultPage();
        List<Map<String, Object>> result = posFileDao.selectPosFiles(page,
                filename,
                page.getOrderByField(), page.isAsc());
        page.setRecords((List<CcPosFile>) new PosFileWarpper(result).warp());
        return super.packForBT(page);

    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downLoad(@RequestParam(required = false) String id, HttpServletResponse res) {
        CcPosFile ccPosFile = posFileService.selectById(id);
        String content = ccPosFile.getContent();
        if(ToolUtil.isEmpty(content)){
            return;
        }
        InputStream bis = IOUtils.toInputStream(content, StandardCharsets.UTF_8);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + ccPosFile.getFilename());
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
     * 新增POS文件

     @RequestMapping(value = "/add", method = RequestMethod.POST)
     @ResponseBody public Object add(CcPosFile posFile, @RequestParam("content") MultipartFile content) {
     System.out.println(content.getName());
     posFileService.insert(posFile);
     return SUCCESS_TIP;
     }
     */

    /**
     * @RequestMapping(value = "/add", method = RequestMethod.POST)
     * @ResponseBody public Object add(HttpServletRequest request) {
     * if (request instanceof MultipartHttpServletRequest) {
     * MultipartFile file =  ((MultipartHttpServletRequest) request).getFile("content");
     * if(file != null) {
     * System.out.println(file.getName());
     * }
     * }
     * //posFileService.insert(posFile);
     * return SUCCESS_TIP;
     * }
     **/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestParam("filecontent") MultipartFile fileField,
                      @RequestParam(value = "filename", required = false) String filename,
                      @RequestParam(value = "crc16", required = false) String crc16,
                      HttpServletRequest request, Model model) {
        try {
            // 原始文件名
            //String name = fileField.getOriginalFilename();
            long size = fileField.getSize();
            InputStream inputStream = fileField.getInputStream();
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            CcPosFile posFile = new CcPosFile();
            posFile.setFilesize(new Long(size).intValue());
            posFile.setFilename(filename);
            //检验码是否需要转成16进制表示?待定
            posFile.setCrc16(CRCUtil.do_crc(result.getBytes("UTF-8"))+"");
            posFile.setContent(result);
            posFileService.insert(posFile);
        } catch (IOException e) {
            return ERROR;
        }

        return SUCCESS_TIP;
    }


    /**
     * 删除POS文件
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer posFileId) {
        posFileService.deleteById(posFileId);
        return SUCCESS_TIP;
    }

    /**
     * 修改POS文件
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcPosFile posFile) {
        posFileService.updateById(posFile);
        return SUCCESS_TIP;
    }

    /**
     * POS文件详情
     */
    @RequestMapping(value = "/detail/{posFileId}")
    @ResponseBody
    public Object detail(@PathVariable("posFileId") Integer posFileId) {
        return posFileService.selectById(posFileId);
    }
}
