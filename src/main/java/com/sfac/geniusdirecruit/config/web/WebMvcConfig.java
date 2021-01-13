package com.sfac.geniusdirecruit.config.web;

import com.sfac.geniusdirecruit.interceptor.RequestViewMappingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Author: yzs
 * @Date: 2021/1/11 20:02
 * 概要：
 * XXXXX
 */
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private RequestViewMappingInterceptor requestViewMappingInterceptor;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewMappingInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String systemName = System.getProperty("os.name");
        if (systemName.toLowerCase().startsWith("win")) {
            registry.addResourceHandler(resourceConfigBean.getResourcePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocalPathForWindow());
        } else  {
            registry.addResourceHandler(resourceConfigBean.getResourcePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocalPathForLinux());
        }
    }
}
