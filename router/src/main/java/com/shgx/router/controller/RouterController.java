package com.shgx.router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author: guangxush
 * @create: 2019/07/21
 */
@RestController
public class RouterController {

    @Autowired
    private RestTemplateBuilder builder;

    private String url = "http://localhost:8082/server/";

    @RequestMapping(path = "/{services}/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String router(@PathVariable("services") String services,
                         @PathVariable("info") String info){
        RestTemplate restTemplate = builder.build();
        url += services + "/" + info;
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        if(result.getStatusCode().equals(HttpStatus.OK)){
            return info;
        }else{
            return "error";
        }
    }
}
