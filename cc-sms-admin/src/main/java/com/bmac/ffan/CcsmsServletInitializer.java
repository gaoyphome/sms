package com.bmac.ffan;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Guns Web程序启动类
 *
 * @author xuzhanfu
 * @date 2017-05-21 9:43
 */
public class CcsmsServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CcsmsApplication.class);
    }

}
