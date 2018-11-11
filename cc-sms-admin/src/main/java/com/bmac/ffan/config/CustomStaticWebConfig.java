/**
 * Copyright &copy; 2015-2020 Zaitong All rights reserved.
 */
package com.bmac.ffan.config;

import com.bmac.ffan.config.properties.BmacProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 　自定义静态下载文件
 *
 * @author xuzhanfu (QQ:7357288)
 * @version v1.0.0
 * @since 2018/2/3 下午3:28
 */
@Configuration
public class CustomStaticWebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    BmacProperties bmacProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + bmacProperties.getBusposfirmwarePath());
    }

}
