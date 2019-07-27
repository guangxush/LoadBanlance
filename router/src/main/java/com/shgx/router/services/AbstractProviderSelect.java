package com.shgx.router.services;

import com.shgx.common.model.ProviderInfo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
public abstract class AbstractProviderSelect implements ProviderSelect{

    protected static ConcurrentHashMap<String, Optional<List<ProviderInfo>>> map = new ConcurrentHashMap<>();

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
