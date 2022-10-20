package com.cc.natatorium.controller;

import com.cc.natatorium.service.testService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author caosen
 * @data 2022/10/7--11:37
 */
@RestController
@RequestMapping("/order")
public class testController {

    @Resource
    private testService testservice;

    @GetMapping("/info")
    public String test(){
        testservice.test();
        return "asdfasdf";
    }
}
