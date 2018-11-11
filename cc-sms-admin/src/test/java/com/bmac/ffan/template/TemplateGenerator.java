package com.bmac.ffan.template;

import com.bmac.ffan.common.persistence.model.*;
import com.bmac.ffan.core.template.config.ContextConfig;
import com.bmac.ffan.core.template.engine.SimpleTemplateEngine;
import com.bmac.ffan.core.template.engine.base.GunsTemplateEngine;

import java.io.IOException;
import java.util.Arrays;

/**
 * 测试Guns模板引擎
 *
 * @author xuzhanfu
 * @date 2017-05-09 20:27
 */
public class TemplateGenerator {

    public static void main(String[] args) throws IOException {
        ContextConfig contextConfig = new ContextConfig();
        contextConfig.setBizChName("交易信息");
        contextConfig.setBizEnName("nfcTransDetail");
        contextConfig.setModuleName("transdetail");
        contextConfig.setProjectPath("C:/Users/vicya/Desktop/TMP/guns-admin");
        contextConfig.setEntityName("TNfcTransdetail");
        contextConfig.setClazz(TNfcTransdetail.class);
        contextConfig.setTableName("T_NFC_TRANSDETAIL");
        contextConfig.setSearchFields(Arrays.asList("ORDERID","ORDERDATE","MERID","GOODSID","BANKID","CHNLID","MOBILEID","PAYSTATE","TRANSTATE","MERCUSTID"));
        //contextConfig.setAddPageSwitch(false);
        //contextConfig.setEditPageSwitch(false);

        GunsTemplateEngine gunsTemplateEngine = new SimpleTemplateEngine();
        gunsTemplateEngine.setContextConfig(contextConfig);
        gunsTemplateEngine.start();
    }

}
