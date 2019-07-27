package com.shgx.provider.schedule;

import com.shgx.common.model.ProviderInfo;
import com.shgx.provider.annotation.MyLog;
import com.shgx.provider.service.CalculateWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * @author: guangxush
 * @create: 2019/07/24
 */
@Component
public class ServerInfoScheduleTask {

    private static ProviderInfo providerInfo = new ProviderInfo();
    private static String listenerUrl = "http://localhost:8080/router/listener/";

    @Autowired
    private RestTemplateBuilder builder;

    @Autowired
    private CalculateWeight calculateWeight;

    private ProviderInfo constructServerInfo(){
        providerInfo.setWeight(2);
        return providerInfo;
    }

    @Scheduled(fixedRate=2000)
    @MyLog(module="ServerInfoScheduleTask", method="returnProviderInfo")
    private String returnProviderInfo(){
        RestTemplate restTemplate = builder.build();
        // todo
        String weightInfo = calculateWeight.getProviderInfos();
        listenerUrl += weightInfo;
        ResponseEntity<String> result = restTemplate.getForEntity(listenerUrl, String.class);
        if(result.getStatusCode().equals(HttpStatus.OK)){
            return result.getBody();
        }else{
            return result.getStatusCode().toString();
        }
    }
}
