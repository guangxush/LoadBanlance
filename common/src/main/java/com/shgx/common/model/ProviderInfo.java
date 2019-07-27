package com.shgx.common.model;

import java.util.Date;

/**
 * @author: guangxush
 * @create: 2019/07/22
 */
public class ProviderInfo {
    private String url;
    private Double weight;
    private Integer resources;
    private Date date;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getResources() {
        return resources;
    }

    public void setResources(Integer resources) {
        this.resources = resources;
    }
}
