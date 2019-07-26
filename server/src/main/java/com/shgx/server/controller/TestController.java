package com.shgx.server.controller;

import com.shgx.common.annotation.SysLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: guangxush
 * @create: 2019/07/26
 */
@RestController
public class TestController {

    @SysLog("hello")
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String sayHello() {
        System.out.println("world");
        return "hello spring boot";
    }
}
