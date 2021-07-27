package com.whut.water;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

/**
 * TODO
 *
 * @author HUANGYAOHUI
 * @version 1.0
 @MapperScan扫描指定包下面的所有接口，在编译之后都会生成对应的实现类
 */
@SpringBootApplication
@MapperScan("com.whut.water.mapper")
public class WaterSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaterSpringBootApplication.class,args);
    }
}
