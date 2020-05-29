package com.example.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("producer")
public interface ProducerDao {
    @RequestMapping(value = "/api/v1/demo/get")
    public String toProducer();
}
