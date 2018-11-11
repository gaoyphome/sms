package com.bmac.ffan.modular.pos.controller;

import com.bmac.ffan.common.persistence.model.CcPosFile;
import com.bmac.ffan.config.properties.BmacProperties;
import com.bmac.ffan.core.base.controller.BaseController;
import com.bmac.ffan.core.base.tips.ErrorTip;
import com.bmac.ffan.core.util.ByteUtil;
import com.bmac.ffan.core.util.CRCUtil;
import com.bmac.ffan.core.util.IOUtil;
import com.bmac.ffan.core.util.ToolUtil;
import com.bmac.ffan.modular.basebusiness.util.CRC16;
import com.bmac.ffan.modular.pos.warpper.BusPosFirmwareWarpper;
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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.bmac.ffan.modular.pos.dao.BusPosFirmwareDao;
import com.bmac.ffan.common.persistence.model.CcBusPosFirmware;
import com.bmac.ffan.modular.pos.service.IBusPosFirmwareService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公交POS固件信息控制器
 *
 * @author 工具生成
 * @Date 2017-11-30 13:55:51
 */
@Controller
@RequestMapping("/busPosFirmware")
public class BusPosFirmwareController extends BaseController {

    private String PREFIX = "/pos/busPosFirmware/";
    @Autowired
    BmacProperties bmacProperties;

    @Autowired
    private IBusPosFirmwareService busPosFirmwareService;

	@Resource
	BusPosFirmwareDao busPosFirmwareDao;
	
    /**
     * 跳转到公交POS固件信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "busPosFirmware.html";
    }

    /**
     * 跳转到添加公交POS固件信息
     */
    @RequestMapping("/busPosFirmware_add")
    public String busPosFirmwareAdd() {
        return PREFIX + "busPosFirmware_add.html";
    }

    /**
     * 跳转到修改公交POS固件信息
     */
    @RequestMapping("/busPosFirmware_update/{busPosFirmwareId}")
    public String busPosFirmwareUpdate(@PathVariable Integer busPosFirmwareId, Model model) {
        CcBusPosFirmware busPosFirmware = busPosFirmwareService.selectById(busPosFirmwareId);
        model.addAttribute("busPosFirmware",busPosFirmware);
        LogObjectHolder.me().set(busPosFirmware);
        return PREFIX + "busPosFirmware_edit.html";
    }


    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downLoad(@RequestParam(required = false) String id, HttpServletResponse res) {
        CcBusPosFirmware ccBusPosFirmware = busPosFirmwareService.selectById(id);
        String content = ccBusPosFirmware.getContent();
        if(ToolUtil.isEmpty(content)){
            return;
        }
        InputStream bis = IOUtils.toInputStream(content, StandardCharsets.UTF_8);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + "dddddddd");
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
     * 获取公交POS固件信息列表
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(
    
    @RequestParam(required = false) String firmVersion
    
    ) {
        //return busPosFirmwareService.selectList(null);
        
        Page<CcBusPosFirmware> page = new PageFactory<CcBusPosFirmware>().defaultPage();
		 List<Map<String, Object>> result = busPosFirmwareDao.selectBusPosFirmwares(page, 
   		 firmVersion,
		 page.getOrderByField(), page.isAsc());
	     page.setRecords((List<CcBusPosFirmware>) new BusPosFirmwareWarpper(result).warp());
	     return super.packForBT(page);
	     
    }

    /**
     * 新增公交POS固件信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestParam("filecontent") MultipartFile fileField,
                      @RequestParam(value = "firmVersion", required = false) String firmVersion,
                      @RequestParam(value = "contentType", required = false) String contentType,
                      @RequestParam(value = "posVendor", required = false) String posVendor,
                      HttpServletRequest request, Model model) {
        try {
            String name  = fileField.getOriginalFilename();
            //文件名需要匹配*_
            if(!Pattern.compile("[A-Za-z0-9]+_[0-9]{4}.+[apk|bin]$").matcher(name).matches()){
                return new ErrorTip("文件名检验失败(正则表达式:[A-Za-z0-9]+_[0-9]{4}.+[apk|bin]$)");
            }
            Map map = new HashMap();
            map.put("firm_version",firmVersion);
            List list = busPosFirmwareService.selectByMap(map);
            if(!list.isEmpty()){
                return new ErrorTip("版本号已存在");
            }
            // 原始文件名
            //String name = fileField.getOriginalFilename();
            long size = fileField.getSize();
            CcBusPosFirmware ccBusPosFirmware = new CcBusPosFirmware();
            ccBusPosFirmware.setContentType(contentType);
            ccBusPosFirmware.setFirmVersion(firmVersion);
            ccBusPosFirmware.setPosVendor(posVendor);
            //检验码是否需要转成16进制表示?待定

            InputStream inputStream = fileField.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1 ) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            String result = "";
            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
            byte[] data = IOUtil.getByteArray(stream1);
            String content = NumberStringUtil.bytesToHexString(data);
            String crc = CRC16.CRC_16(data);


           /* byte[] tempbytes = new byte[1024];
            int byteread = 0;
            while ((byteread = stream1.read(tempbytes,0, tempbytes.length)) != -1) {
                byte[] temp =  new byte[byteread];
                System.arraycopy(tempbytes,0,temp,0,byteread);
                result += ByteUtil.byte2hex(temp);
            }*/
            stream1.close();
        //    String result = IOUtils.toString(stream1, StandardCharsets.UTF_8);
            ccBusPosFirmware.setCrc16(crc);

            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());


            if("01".equals(contentType)){
                String filepath = bmacProperties.getBusposfirmwarePath()+name;
                File file = new File(filepath);
                if(!file.exists()){
                    file.createNewFile();
                }
                OutputStream os = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer_ = new byte[1024];
                while ((bytesRead = stream2.read(buffer_, 0, 1024)) != -1) {
                    os.write(buffer_, 0, bytesRead);
                }
                os.close();
                inputStream.close();
                stream2.close();
                String fileurl = bmacProperties.getBusposfirmwareUrl()+name;
                ccBusPosFirmware.setContent(fileurl);
            }else{
                ccBusPosFirmware.setContent(content);
            }
            busPosFirmwareService.insert(ccBusPosFirmware);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ErrorTip("文件处理失败！");
        }finally {

        }

        return SUCCESS_TIP;
    }

    /**
     * 删除公交POS固件信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer busPosFirmwareId) {
        busPosFirmwareService.deleteById(busPosFirmwareId);
        return SUCCESS_TIP;
    }

    /**
     * 修改公交POS固件信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CcBusPosFirmware busPosFirmware) {
        busPosFirmwareService.updateById(busPosFirmware);
        return SUCCESS_TIP;
    }

    /**
     * 公交POS固件信息详情
     */
    @RequestMapping(value = "/detail/{busPosFirmwareId}")
    @ResponseBody
    public Object detail(@PathVariable("busPosFirmwareId") Integer busPosFirmwareId) {
        return busPosFirmwareService.selectById(busPosFirmwareId);
    }
}
