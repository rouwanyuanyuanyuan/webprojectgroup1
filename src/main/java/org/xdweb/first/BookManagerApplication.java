package org.xdweb.first;

import lombok.extern.slf4j.Slf4j;
import org.xdweb.first.utils.PathUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value = "org.xdweb.first.mapper")
@Slf4j
public class BookManagerApplication {

    public static void main(String[] args) {
        log.info("启动图书管理系统");
        System.out.println("Project Path: " + PathUtils.getClassLoadRootPath());
        SpringApplication.run(BookManagerApplication.class, args);
    }

}
