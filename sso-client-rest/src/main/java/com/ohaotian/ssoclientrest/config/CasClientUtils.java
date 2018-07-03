package com.ohaotian.ssoclientrest.config;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class CasClientUtils {

	// 登录服务器地址
	private static final String CAS_SERVER_PATH = "http://127.0.0.1:9000";

	// 登录地址的token
	private static final String GET_TOKEN_URL = CAS_SERVER_PATH + "/v1/tickets";

	// 目标返回的服务器的url
	private static final String TAGET_URL = "http://yellowcong.com:8888/";

	public static void main(String[] args) {

		String tgt = getTGT("yellowcong", "doubi");
//		String tgt = getTGT("root", "root");

		System.out.println("TGT={}" + tgt);

		String st = getST(tgt, TAGET_URL);

		System.out.println("ST={}" + st);

	}

	/**
	 * 获取TGT
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private static String getTGT(String username, String password) {

		try {
			CookieStore httpCookieStore = new BasicCookieStore();

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(GET_TOKEN_URL);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("password", password));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);

			Header headerLocation = response.getFirstHeader("Location");
			Header headerLocations = response.getFirstHeader("location");
			String location = headerLocation == null ? null : headerLocation.getValue();

			System.out.println("Location={}" + location);

			if (location != null) {
				return location.substring(location.lastIndexOf("/") + 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取ST
	 * 
	 * @param tgt
	 * @param clentURL
	 * @return
	 */
	private static String getST(String tgt, String clentURL) {

		try {

			CloseableHttpClient client = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(GET_TOKEN_URL + "/" + tgt);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("service", clentURL));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);
			String st = readResponse(response);
			return st == null ? null : (st == "" ? null : st);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 读取 response body 内容为字符串
	 * 
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedOperationException 
	 */
	private static String readResponse(HttpResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String result = new String();
		String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		return result;
	}

	
	/**
	 * 创建模拟客户端(针对 https 客户端禁用 SSL 验证)
	 * @param cookieStore
	 * @return
	 * @throws Exception
	 */
	private static CloseableHttpClient createHttpClientWithNoSsl(CookieStore cookieStore) throws Exception {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
				// don't check
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
				// don't check
			}
		} };

		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(null, trustAllCerts, null);
		LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(ctx);
		return HttpClients.custom().setSSLSocketFactory(sslSocketFactory)
				.setDefaultCookieStore(cookieStore == null ? new BasicCookieStore() : cookieStore).build();
	}

}
