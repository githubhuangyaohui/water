package com.whut.water.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login",
                        "/**/*.js",                  //js静态资源
                        "/**/*.css",                  //css静态资源
                        "/**/*.png",                  //js静态资源
                        "/**/*.jpg"                   //js静态资源
                );
    }
}