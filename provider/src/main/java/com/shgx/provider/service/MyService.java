package com.shgx.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: guangxush
 * @create: 2019/07/27
 */
@Service
public class MyService {

    @Autowired
    private CalculateWeight calculateWeight;

    public String serviceOne(String info){
        Date start = new Date();
        // doSomething
        Date end = new Date();
        calculateWeight.calculateTime(start, end);
        return "serviceOne"+info;
    }

    public String serviceTwo(String info){
        Date start = new Date();
        // doSomething
        Date end = new Date();
        calculateWeight.calculateTime(start, end);
        return "serviceTwo"+info;
    }

    public String serviceThree(String info){
        Date start = new Date();
        // doSomething
        Date end = new Date();
        calculateWeight.calculateTime(start, end);
        return "serviceThree"+info;
    }
}
