package com.biubiu.util.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * HttpUtil
 *
 * @author wbbaijq
 */
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * 发送GET请求
     *
     * @param path url
     * @return string
     */
    public static String doGet(String path) {
        return doGet(path, null);
    }

    /**
     * 发送GET请求
     *
     * @param path  url
     * @param param 参数
     * @return string
     */
    public static String doGet(String path, Map<String, String> param) {
        return doGet(path, param, null);
    }

    /**
     * 发送GET请求
     *
     * @param path    url
     * @param param   参数
     * @param headers header
     * @return string
     */
    public static String doGet(String path, Map<String, String> param, Map<String, String> headers) {
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = wrapClient(path);
        // 创建uri
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(path);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            httpGet = new HttpGet(uri);
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }

            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw new RuntimeException("[发送Get请求错误：]" + e.getMessage());
        } finally {
            try {
                if (httpGet != null) {
                    httpGet.releaseConnection();
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * post
     *
     * @param url       url
     * @param jsonParam 请求参数 json格式
     * @return 返回结果
     */
    public static String doPost(String url, String jsonParam) {
        return doPost(url, jsonParam, null);
    }

    /**
     * post请求
     *
     * @param url       url
     * @param jsonParam 请求参数 json格式
     * @param headers   请求头
     * @return string 返回结果
     */
    public static String doPost(String url, String jsonParam, Map<String, String> headers) {
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = wrapClient(url);
        try {
            httpPost = new HttpPost(url);
            //addHeader，如果Header没有定义则添加，已定义则不变，setHeader会重新赋值
            httpPost.addHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            StringEntity entity = new StringEntity(jsonParam, StandardCharsets.UTF_8);
            entity.setContentType("text/json");
            entity.setContentEncoding(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
            httpPost.setEntity(entity);
            //是否有header
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 执行请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }

        } catch (Exception e) {
            throw new RuntimeException("[发送POST请求错误：]" + e.getMessage());
        } finally {
            try {
                if (httpPost != null) {
                    httpPost.releaseConnection();
                }
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    private static CloseableHttpClient wrapClient(String url) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        if (url.startsWith("https")) {
            client = getCloseableHttpsClients();
        }
        return client;
    }

    /**
     * 获取https协议请求对象
     *
     * @return CloseableHttpClient
     */
    private static CloseableHttpClient getCloseableHttpsClients() {
        // 采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);
        // 创建自定义的httpsclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();
        return client;
    }

    /**
     * 获取SSL套接字对象 重点重点：设置tls协议的版本
     *
     * @return SSLContext
     */
    private static SSLContext createIgnoreVerifySSL() {
        // 创建套接字对象
        SSLContext sslContext = null;
        try {
            //指定TLS版本
            sslContext = SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[创建套接字失败:] " + e.getMessage());
        }
        // 实现X509TrustManager接口，用于绕过验证
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        try {
            //初始化sslContext对象
            sslContext.init(null, new TrustManager[]{trustManager}, null);
        } catch (KeyManagementException e) {
            System.out.println("[初始化套接字失败:] " + e.getMessage());
        }
        return sslContext;
    }

}
