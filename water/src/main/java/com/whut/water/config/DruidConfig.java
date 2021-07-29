package com.whut.water.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 德鲁伊配置类
 */
@Slf4j
@Configuration
public class DruidConfig {

    /**
     * 注入druid数据源
     *  @ConfigurationProperties(prefix = "spring.datasource")表示获取yml配置文件
     * 前缀为spring.datasource的所有属性值
     * 被@Bean注解的方法被AnnotationConfigWebApplicationContext类扫描，将方法
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource creatDruidDateSourse(){
        return new DruidDataSource();
    }


    /**
     * 向Spring容器注入Druid监控。通过StatViewServlet通过ServletRegistrationBean注入到Spring容器。
     * StatViewServlet用于Druid监控，展示的统计信息
     * @return ServletRegistrationBean表示Servlet的注册类
     */
    @Bean
    public ServletRegistrationBean createStatViewServlet() {
        if (log.isInfoEnabled()) {
            log.info("create StatViewServlet");
        }
        ServletRegistrationBean<StatViewServlet> servlet =
                new ServletRegistrationBean<>(
                        new StatViewServlet(), "/druid/*");
        // 设置初始化参数，登录的key都是固定的
        Map<String,String> initParamsMap =  new ConcurrentHashMap<>();
        initParamsMap.put("loginUsername","admin");
        initParamsMap.put("loginPassword","admin");
        // 白名单，允许所有ip地址登录
        initParamsMap.put("allow","");
        // 将初始化参数注入到bean中
        servlet.setInitParameters(initParamsMap);
        return servlet;
    }



}
