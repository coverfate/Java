/**   
 * 
 * @Title: CoverHttp.java 
 * @Prject: HcDBCrawl
 * @Package: com.hc.utils.http 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2015��11��11�� ����9:41:48 
 * @version: V1.0   
 */
package com.cover.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @ClassName: CoverHttp
 * @Description: TODO
 * @author: chenchao
 * @date: 2015年11月11日 上午10:21:31
 */
public class CFHttpUtil {
	private static PoolingHttpClientConnectionManager cm;
	private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
	public static BasicCookieStore cookieStore = new BasicCookieStore();

	static {
		cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
	}

	public static CloseableHttpClient getHttpClient() {
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).build();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).setUserAgent(userAgent)
				.setDefaultRequestConfig(globalConfig).setDefaultCookieStore(cookieStore).build();
		// logger.info("[HttpClient]:===========" + httpClient +
		// "=============");
		return httpClient;
	}

	public static CloseableHttpClient getHttpsClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	public static String getJsonString(String urlPath) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	public static String getJsonString(String urlPath, Map<String, String> headInfo) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		for (String key : headInfo.keySet()) {
			connection.addRequestProperty(key, headInfo.get(key));
		}
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	public static String getJsonString(String urlPath, Proxy proxy) throws Exception {
		if (proxy == null)
			return getJsonString(urlPath);
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
		connection.setConnectTimeout(3000);
		connection.setReadTimeout(3000);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		// ��Ӧ���ַ�����ת��
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	public static String getJsonString1(String urlPath, Proxy proxy) throws Exception {
		if (proxy == null)
			return getJsonString(urlPath);
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
		connection.setReadTimeout(2000);
		connection.setConnectTimeout(2000);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	public static String getJsonString1(String urlPath, Proxy proxy, Map<String, String> headInfo) throws Exception {
		if (proxy == null)
			return getJsonString(urlPath, headInfo);
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
		for (String key : headInfo.keySet()) {
			connection.addRequestProperty(key, headInfo.get(key));
		}
		connection.setReadTimeout(2000);
		connection.setConnectTimeout(2000);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	public static String getJsonStringZyyxRecord(String urlPath, Proxy proxy) throws Exception {
		URL url = new URL(urlPath);
		if (proxy == null)
			return getJsonString(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
		connection.addRequestProperty("Content-Type", "text/html;charset=UTF-8");
		connection.addRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		connection.addRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
		connection.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.6,en;q=0.4");
		connection.addRequestProperty("Connection", "keep-alive");
		connection.addRequestProperty("Host", "www.998fund.com");
		connection.setReadTimeout(1000 * 30);
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}
}
