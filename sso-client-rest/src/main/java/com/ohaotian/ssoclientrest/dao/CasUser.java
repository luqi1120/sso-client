package com.ohaotian.ssoclientrest.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created: luQi
 * Date:2018-4-27 10:09
 */
public class CasUser {

    @JsonProperty("id")
    private String username;

    @JsonProperty("@class")
    // 需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
    private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";

    @JsonProperty("attributes")
    private Map<String, Object> attributes = new HashMap<String,Object>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
