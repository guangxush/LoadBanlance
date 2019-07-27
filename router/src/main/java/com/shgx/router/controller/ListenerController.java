package com.shgx.router.controller;

import com.alibaba.fastjson.JSON;
import com.shgx.common.model.ProviderInfo;
import com.shgx.router.services.impl.WeightSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author: guangxush
 * @create: 2019/07/25
 */
@RestController
public class ListenerController {

    @Autowired
    private WeightSelect weightSelect;

    @RequestMapping(path = "/listener/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String listener(@PathVariable("info") String info){
        return info;
    }

    /**
     * 服务注册
     * @param info
     * @return
     */
    @RequestMapping(path = "/register/(url)/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String register(@PathVariable("url") String url, @PathVariable("info") String info){
        Optional<List<ProviderInfo>> providerInfos = (Optional<List<ProviderInfo>>) JSON.parse(info);
        weightSelect.register(url, providerInfos);
        return "Register success!";
    }

    /**
     * 服务注销
     * @param url
     * @return
     */
    @RequestMapping(path = "/unload/{url}", method = RequestMethod.GET)
    @ResponseBody
    public String unload(@PathVariable("url") String url){
        weightSelect.unload(url);
        return "Unload success!";
    }

    /**
     * 心跳检测
     * @param url
     * @return
     */
    @RequestMapping(path = "/alive/{url}/{info}", method = RequestMethod.GET)
    @ResponseBody
    public String isAlive(@PathVariable("url") String url, @PathVariable("info") String info){
        // todo 超过时间的服务删除掉
        Optional<List<ProviderInfo>> providerInfos = (Optional<List<ProviderInfo>>) JSON.parse(info);
        weightSelect.update(url, providerInfos);
        return "Update success!";
    }
}
