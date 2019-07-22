package com.shgx.router.services.impl;

import com.shgx.common.model.ProviderInfo;
import com.shgx.router.services.ProviderSelect;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
public class RandomSelect implements ProviderSelect {

    private ConcurrentHashMap<String, Optional<List<ProviderInfo>>> map = new ConcurrentHashMap<>();

    @Override
    public String select(String url){
        Optional<List<ProviderInfo>> infos = map.get(url);
        if(infos.isPresent()){
            List<ProviderInfo> infoList = infos.get();
            if(infoList.isEmpty()||infoList.size()==0){
                return null;
            }
            int weight = infoList.get(0).getWeight();
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

    @Override
    public boolean register(String url, Optional<List<ProviderInfo>> infos){
        try{
            map.put(url, infos);
        }catch (InternalError error){
            return false;
        }
        return true;
    }

    @Override
    public boolean unload(String url){
        try{
            if(map.get(url).isPresent()){
                map.remove(url);
            }else{
                return false;
            }
        }catch (InternalError error){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(String url, Optional<List<ProviderInfo>> infos){
        try{
            if(map.get(url).isPresent()){
                map.put(url, infos);
            }else{
                return false;
            }
        }catch (InternalError error){
            return false;
        }
        return true;
    }
}
