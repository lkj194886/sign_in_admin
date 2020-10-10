package com.sign_in.code.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Classname CorsFilterConfig
 * @Description TODO
 * @Date 2020/8/6 13:23
 * @Created by wgg
 */
@Configuration
public class CorsFilterConfig {
    public CorsConfiguration  buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}
