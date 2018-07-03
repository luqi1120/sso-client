package com.ohaotian.ssoclientrest.controller;

import com.ohaotian.ssoclientrest.config.WechatAccountConfig;
import com.ohaotian.ssoclientrest.utils.CheckoutUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created: luQi
 * Date:2018-5-12 22:28
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    /**
     * 接口配置信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/WXtest")
    public void wxTest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println(111);
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 登录
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/test")
    public String index(Model model) throws Exception {
        String oauthUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

        // 回调地址
        String redirect_uri = URLEncoder.encode("https://391fffa2.ngrok.io/wechat/qrUserInfo", "utf-8"); ;
        oauthUrl =  oauthUrl.replace("APPID",wechatAccountConfig.getOpenAppId()).replace("REDIRECT_URI",redirect_uri).replace("SCOPE",wechatAccountConfig.getScope());
        model.addAttribute("name","luQi");
        model.addAttribute("oauthUrl",oauthUrl);

        logger.info("微信地址oauthUrl={}", oauthUrl);
        return "index";
    }


    /**
     * 获取access_token,openID
     * @param code
     * @param returnUrl
     * @param model
     * @return
     */
    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code")String code,
                             @RequestParam("state")String returnUrl,
                             Model model) {

        logger.info("回调地址,code={},state={}", code, returnUrl);

        //1.通过code获取access_token
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (Exception e) {
            logger.error("微信网页授权={}", e);
            e.printStackTrace();
        }

        //2.通过access_token和openid获取用户信息
        logger.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
        String openId = wxMpOAuth2AccessToken.getOpenId();
        logger.info("openId={}", openId);

        // 数据库中查询微信号是否绑定平台账号  begin
        // 尚未绑定账号 end
        model.addAttribute("wxMpOAuth2AccessToken", wxMpOAuth2AccessToken);
        model.addAttribute("openId", openId);
        return "result";
    }


    @RequestMapping("/login")
    public String index1(Model model) throws Exception {
        String redirect_uri = URLEncoder.encode("https://391fffa2.ngrok.io/wechat/qrUserInfo", "utf-8"); ;
        model.addAttribute("name","luQi");
        model.addAttribute("appid",wechatAccountConfig.getOpenAppId());
        model.addAttribute("scope",wechatAccountConfig.getOpenAppSecret());
        model.addAttribute("redirect_uri",redirect_uri);
        return "login";
    }


}
