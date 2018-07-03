package com.ohaotian.ssoclientrest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created: luQi
 * Date:2018-5-14 10:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * 开放平台appID
     */
    private String openAppId;

    /**
     * appsecret
     */
    private String openAppSecret;

    /**
     *授权作用域
     */
    private String scope;

}
