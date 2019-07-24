package com.shgx.server.schedule;

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
@Slg4j
public class ServerInfoScheduleTask {

    private static ProviderInfo providerInfo = new ProviderInfo();
    private static final String url = "";

    @Autowired
    private RestTemplateBuilder builder;


    private ProviderInfo constructServerInfo(){
        providerInfo.setWeight(2);
        return providerInfo;
    }

    @Scheduled(cron = "0/5 * * * * ?")
    private void returnProviderInfo(){
        RestTemplate restTemplate = builder.build();
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
        if(result.getStatusCode().equals(HttpStatus.OK)){
            System.out.println();
        }else{
            System.out.println();
        }
    }
}
