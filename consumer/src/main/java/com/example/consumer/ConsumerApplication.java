package com.example.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableFeignClients
@RefreshScope
@EnableHystrix
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Autowired
    ProducerDao producerDao;

    @Value("${test}")
    private String test;

    //调用producer微服务
    @RequestMapping(value = "/api/v1/demo/get")
    @HystrixCommand(fallbackMethod = "errorMsg")
    public String toProducer(){
        return producerDao.toProducer();
    }

    //断路转发
    public String errorMsg(){
        return "error!!";
    }
    @RequestMapping(value = "hello")
    public String Hello(){
        return test;
    }
}
