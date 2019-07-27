package com.shgx.router.services.impl;

import com.shgx.common.model.ProviderInfo;
import com.shgx.router.services.AbstractProviderSelect;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
@Service
public class WeightSelect extends AbstractProviderSelect {


    @Override
    public String select(String url){
        Optional<List<ProviderInfo>> infos = map.get(url);
        if(infos.isPresent()){
            List<ProviderInfo> infoList = infos.get();
            if(infoList.isEmpty()||infoList.size()==0){
                return null;
            }
            double weight = infoList.get(0).getWeight();
            String maxUrl = infoList.get(0).getUrl();
            for(ProviderInfo info: infoList){
                if(info.getWeight()>weight){
                    maxUrl = info.getUrl();
                }
            }
            return maxUrl;
        }
        return null;
    }
}
