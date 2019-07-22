package com.shgx.router.services;


import com.shgx.common.model.ProviderInfo;

import java.util.List;
import java.util.Optional;

/**
 * @author: guangxush
 * @create: 2019/07/21
 */
public interface ProviderSelect {

    /**
     * 选择合适的负载
     * @param url
     * @return
     */
    String select(String url);

    /**
     * 注册负载
     * @param url
     * @param infos
     * @return
     */
    boolean register(String url, Optional<List<ProviderInfo>> infos);

    /**
     * 卸载负载
     * @param url
     * @return
     */
    boolean unload(String url);

    /**
     * 更新负载
     * @param url
     * @param infos
     * @return
     */
    boolean update(String url, Optional<List<ProviderInfo>> infos);
}
