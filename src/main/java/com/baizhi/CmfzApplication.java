package com.baizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author miion
 * @create 2019-08-13 15:11
 */
@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class CmfzApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class, args);
    }
}
