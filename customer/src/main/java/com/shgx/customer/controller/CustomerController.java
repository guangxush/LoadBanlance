package com.shgx.customer.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author: guangxush
 * @create: 2019/07/21
 */
@RestController
public class CustomerController {

    @RequestMapping(path = "/send/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String send(@PathVariable("info") String info){
        return info;
    }
}
