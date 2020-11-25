package com.sign_in.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname FilePathConfig
 * @Description TODO
 * @Date 2020/8/6 16:10
 * @Created by wgg
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {


    @Value("${file.path}")
    public String path;

    @Value("${file.address}")
    public String address;

    public static final String BannerURL="http://localhost:8081/file/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(path).addResourceLocations("file:" + address);
    }

}
