package com.shgx.provider.service;

import com.alibaba.fastjson.JSON;
import com.shgx.common.model.ProviderInfo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guangxush
 * @create: 2019/07/27
 */
@Service
public class CalculateWeight {

    private static ConcurrentHashMap<String, ProviderInfo> providerInfos = new ConcurrentHashMap<>(6);
    private static double timeWeight = 0.2;
    private static double gpuWeight = 0.3;
    private static double threadsCountWeight = 0.5;

    private static long time = 0;
    private static long gpu = 0;
    private static long threadsCount = 0;

    private static List<ProviderInfo> providerInfoList = new LinkedList<>();


    public void initProviderInfo(String url) {
        if (!providerInfos.containsKey(url)) {
            ProviderInfo providerInfo = new ProviderInfo();
            providerInfo.setUrl(url);
            providerInfo.setWeight(1);
            providerInfos.put(url, providerInfo);
        } else {
            ProviderInfo providerInfo = providerInfos.get(url);
            providerInfo.setWeight(1);
        }
    }

    public String getProviderInfos(){
        providerInfoList.clear();
        Iterator iterator = providerInfos.entrySet().iterator();
        while(iterator.hasNext()){
            ProviderInfo providerInfo = (ProviderInfo) iterator.next();
            providerInfoList.add(providerInfo);
        }
        return JSON.toJSONString(providerInfoList);
    }

    /**
     * 计算服务器性能
     *
     * @param url
     * @return
     */
    public String getProviderInfo(String url) {
        double weight = time * timeWeight + gpu * gpuWeight + threadsCount * threadsCountWeight;
        if (!providerInfos.containsKey(url)) {
            initProviderInfo(url);
        } else {
            ProviderInfo providerInfo = providerInfos.get(url);
            providerInfo.setWeight(weight);
        }
        return String.valueOf(JSON.toJSON(providerInfos));
    }

    /**
     * 计算间隔时间
     *
     * @param start
     * @param end
     */
    public void calculateTime(Date start, Date end) {
        time = (end.getTime() - start.getTime()) / 1000;
    }

    /**
     * 计算GPU性能
     *
     */
    public void getGpu() {
        //doSomething
        gpu =  1;
    }

    /**
     * 获取当前服务器最大活跃线程数
     *
     */
    public void threadCount() {
        //doSomething
        threadsCount = 12;
    }
}
