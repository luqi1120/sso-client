package com.ohaotian.ssoclientrest.config;

import com.ohaotian.ssoclientrest.config.WechatAccountConfig;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created: luQi
 * Date:2018-5-14 10:57
 */
@Component
public class WechatConfig {

    @Autowired
    private WechatAccountConfig accountConfig;

    @Bean
    public WxMpService wxOpenService() {
        WxMpServiceImpl wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(accountConfig.getOpenAppId());
        wxMpInMemoryConfigStorage.setSecret(accountConfig.getOpenAppSecret());
        return wxMpInMemoryConfigStorage;
    }

}
