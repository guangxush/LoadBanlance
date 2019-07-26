package com.shgx.server.schedule;

import com.shgx.common.annotation.SysLog;
import com.shgx.common.model.ProviderInfo;
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
    private static String url = "http://localhost:8080/router/listener/hello";

    @Autowired
    private RestTemplateBuilder builder;


    private ProviderInfo constructServerInfo(){
        providerInfo.setWeight(2);
        return providerInfo;
    }

    @Scheduled(fixedRate=3000)
    @SysLog("provider info....")
    private String returnProviderInfo(){
        System.out.println("hello.....");
        RestTemplate restTemplate = builder.build();
        // url+="hello";
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        if(result.getStatusCode().equals(HttpStatus.OK)){
            return result.getBody();
        }else{
            return result.getStatusCode().toString();
        }
    }
}
