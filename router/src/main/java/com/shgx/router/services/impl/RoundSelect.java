package com.shgx.router.services.impl;

import com.shgx.common.model.ProviderInfo;
import com.shgx.router.services.AbstractProviderSelect;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
@Service
public class RoundSelect extends AbstractProviderSelect {

    private AtomicInteger number;

    @Override
    public String select(String url){
        Optional<List<ProviderInfo>> infos = map.get(url);
        if(infos.isPresent()){
            List<ProviderInfo> infoList = infos.get();
            if(infoList.isEmpty()||infoList.size()==0){
                return null;
            }
            int size = infoList.size();
            int index = 0;
            if(number.get()>size){
                index = 0;
                number.set(index);
            }else{
                index = number.get()+1;
                number.getAndIncrement();
            }
            return infoList.get(index).getUrl();
        }
        return null;
    }
}
