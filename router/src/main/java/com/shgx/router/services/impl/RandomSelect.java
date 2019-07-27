package com.shgx.router.services.impl;

import com.shgx.common.model.ProviderInfo;
import com.shgx.router.services.AbstractProviderSelect;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
@Service
public class RandomSelect extends AbstractProviderSelect {

    /**
     * 随机选取一个URL
     * @param url
     * @return
     */
    @Override
    public String select(String url){
        Optional<List<ProviderInfo>> infos = map.get(url);
        if(infos.isPresent()){
            List<ProviderInfo> infoList = infos.get();
            if(infoList.isEmpty()||infoList.size()==0){
                return null;
            }
            int size = infoList.size();
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int index = random.nextInt(size);
            return infoList.get(index).getUrl();
        }
        return null;
    }
}
