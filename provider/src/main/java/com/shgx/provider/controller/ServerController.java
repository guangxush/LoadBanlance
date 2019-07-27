package com.shgx.provider.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author: guangxush
 * @create: 2019/07/21
 */
@RestController
public class ServerController {

    @RequestMapping(path = "/receiver1/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String receiver1(@PathVariable("info") String info) {
        return info;
    }

    @RequestMapping(path = "/receiver2/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String receiver2(@PathVariable("info") String info) {
        return info;
    }

    @RequestMapping(path = "/receiver3/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String receiver3(@PathVariable("info") String info) {
        return info;
    }
}
