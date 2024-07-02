package com.fzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.fzy.mapper")
@EnableFeignClients
@EnableCaching
@EnableTransactionManagement
public class ProductApp
{
    public static void main( String[] args )
    {

        SpringApplication.run(ProductApp.class);
    }
}
