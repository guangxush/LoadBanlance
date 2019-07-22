package com.shgx.common.model;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
public class ProviderInfo {
    private String url;
    private Integer weight;
    private Integer resources;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getResources() {
        return resources;
    }

    public void setResources(Integer resources) {
        this.resources = resources;
    }
}
