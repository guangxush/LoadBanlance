package com.shgx.router.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author: guangxush
 * @create: 2019/07/25
 */
@RestController
public class ListenerController {

    @RequestMapping(path = "/listener/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String listener(@PathVariable("info") String info){
        return info;
    }
}
