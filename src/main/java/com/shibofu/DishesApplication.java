package com.shibofu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author potter.fu
 */
@SpringBootApplication
@MapperScan(value = "com.shibofu.*.dao")
public class DishesApplication {
    public static void main(String[] args) {
        SpringApplication.run(DishesApplication.class, args);
    }
}
