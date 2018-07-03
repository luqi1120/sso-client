package com.ohaotian.ssoclientrest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created: luQi
 * Date:2018-5-7 14:54
 */
@Component
public class CasServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(CasServerConfig.class);

//    @Value("${cas.serviceUrl}")
//    private String CAS_SERVER_PATH;
//
//    @Value("${cas.clientUrl}")
//    private String TAGET_URL;
//
//    @Value("${cas.tgtUrl}")
//    private String GET_TOKEN_URL;

    public static void main(String [] args) throws Exception {


        String username ="zhangsan";
        String password ="c4ca4238a0b923820dcc509a6f75849b";
//        String username ="root";
//        String password ="root";
//		String password ="63a9f0ea7bb98050796b649e85481845";


        CasServerConfig utils = CasServerConfig.getInstance();

        String st = utils.getSt(username, password);
        System.out.println("st = {}" + st);
    }

    //登录服务器地址
    private static final String  CAS_SERVER_PATH = "http://yellowcong.com:9000";

    //登录地址的token
    private static final String  GET_TOKEN_URL = CAS_SERVER_PATH + "/v1/tickets";

    //目标返回的服务器的url
    //反斜杠一定要注意了
    private static final String TAGET_URL = "http://yellowcong.com:8888/";

    private static CasServerConfig casServerConfig = null;

    private CasServerConfig() {
    }


    public static CasServerConfig getInstance() {
        if (casServerConfig == null) {
            synchronized (CasServerConfig.class) {
                if (casServerConfig == null) {
                    casServerConfig = new CasServerConfig();
                }
            }
        }
        return casServerConfig;
    }

    /**
     * 通过用户名密码获取 service ticket
     *
     * @param username
     * @param password
     * @return
     */
    public String getSt(String username, String password) {
        String tgt = casServerConfig.getTGT(username, password);

        if (StringUtils.isEmpty(tgt)) {
            return "";
        }
        return casServerConfig.getST(tgt);
    }


    /**
     * 获取tgt
     *
     * @param username
     * @param password
     * @return
     */
    private String getTGT(String username, String password) {
        String tgt = "";
        OutputStreamWriter out = null;
        BufferedWriter writer = null;
        HttpURLConnection conn = null;
        try {
            // 获取tgt
            conn = (HttpURLConnection) openConn(GET_TOKEN_URL);
            String param = "username=" + URLEncoder.encode(username, "UTF-8");
            param += "&password" + "=" + URLEncoder.encode(password, "UTF-8");

            out = new OutputStreamWriter(conn.getOutputStream());
            writer = new BufferedWriter(out);
            //添加参数到目标服务器
            writer.write(param);
            writer.flush();
            writer.close();
            out.close();

            //获取token
            tgt = conn.getHeaderField("location");

            logger.info("location:tgt={}", tgt);

            if (tgt != null && conn.getResponseCode() == 201) {
                tgt = tgt.substring(tgt.lastIndexOf("/") + 1);
                logger.info("201:tgt={}" + tgt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tgt;
    }

    /**
     * 通过post来获取连接
     *
     * @param get_token_url
     * @return
     * @throws Exception
     */
    private URLConnection openConn(String get_token_url) throws Exception {

        // 1. 得到访问地址的URL
        URL url = new URL(get_token_url);

        // 2. 得到网络访问对象java.net.HttpURLConnection
        HttpURLConnection hsu = (HttpURLConnection) url.openConnection();

        // 3. 设置请求参数（过期时间，输入、输出流、访问方式），以流的形式进行连接
        // 设置是否向HttpURLConnection输出
        hsu.setDoInput(true);
        // 设置是否从httpUrlConnection读入
        hsu.setDoOutput(true);
        // 设置请求方式
        hsu.setRequestMethod("POST");
//        hsu.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//        hsu.connect();
        return hsu;
    }

    /**
     * 根据票据获取st
     * @param tgt
     * @return
     */
    private String getST(String tgt) {

        String serviceTicket = "";
        OutputStreamWriter out = null;
        BufferedWriter writer = null;
        HttpURLConnection conn = null;

        try {

            //第一步，获取到tgt
            conn = (HttpURLConnection) openConn(GET_TOKEN_URL + "/" + tgt);

            //需要访问的目标网站
            String param = "service=" + URLEncoder.encode(TAGET_URL, "utf-8");

            out = new OutputStreamWriter(conn.getOutputStream());
            writer = new BufferedWriter(out);
            //添加参数到目标服务器
            writer.write(param);
            writer.flush();
            writer.close();
            out.close();

            //获取返回的ticket票据
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                serviceTicket = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serviceTicket;
    }


}
