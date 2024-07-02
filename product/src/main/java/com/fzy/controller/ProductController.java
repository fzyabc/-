package com.fzy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ProductController {
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/index")
    public String index() {
        String result = restTemplate.getForObject("http://admin/index", String.class);
        return "获取到admin的信息:    "+result;
    }
}
