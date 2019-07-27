package com.shgx.provider.controller;

import com.shgx.provider.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: guangxush
 * @create: 2019/07/21
 */
@RestController
public class ServerController {

    @Autowired
    private MyService myService;

    @RequestMapping(path = "/receiver1/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String receiver1(@PathVariable("info") String info) {
        return myService.serviceOne(info);
    }

    @RequestMapping(path = "/receiver2/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String receiver2(@PathVariable("info") String info) {
        return myService.serviceTwo(info);
    }

    @RequestMapping(path = "/receiver3/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String receiver3(@PathVariable("info") String info) {
        return myService.serviceThree(info);
    }
}
