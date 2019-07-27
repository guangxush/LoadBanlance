package com.shgx.customer.schedule;

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
public class RequestScheduleTask {

    private static String url = "http://localhost:8080/router/receiver/hello";

    @Autowired
    private RestTemplateBuilder builder;

    /**
     * 给路由器发送请求
     * @return
     */
    @Scheduled(fixedRate=10000)
    private String getFromServer(){
        RestTemplate restTemplate = builder.build();
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        if(result.getStatusCode().equals(HttpStatus.OK)){
            return result.getBody();
        }else{
            return result.getStatusCode().toString();
        }
    }
}
