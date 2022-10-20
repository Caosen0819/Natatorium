package com.cc.natatorium.service.Impl;

import com.cc.natatorium.service.testService;
import org.springframework.stereotype.Service;

/**
 * @Author caosen
 * @data 2022/10/7--11:38
 */
@Service
public class testServiceImpl implements testService {

//    @Resource
//    private adminRepository adminReposity;
    @Override
    public void test() {
        System.out.println("sdafasdf");
    }
}
