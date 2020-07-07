package com.zyj.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

/**
 * @author : zyj
 * @version : 1.0
 * @date : 2020-7-7 09:40:06
 */
public class KeepAliveHttpClientBuilder {

    private static HttpClient httpClient;

    /**
     * 获取http长连接
     */
    public synchronized HttpClient getKeepAliveHttpClient() {
        if (httpClient == null) {
            LayeredConnectionSocketFactory sslsf = null;
            try {
                sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory>create().register("https", sslsf)
                    .register("http", new PlainConnectionSocketFactory()).build();
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            cm.setMaxTotal(HttpConstant.MAX_TOTAL);
            cm.setDefaultMaxPerRoute(HttpConstant.MAX_CONN_PER_ROUTE);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(HttpConstant.CONNECT_TIMEOUT)
                    .setSocketTimeout(HttpConstant.SOCKET_TIMEOUT).build();
            // 创建连接
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(cm).build();
        }

        return httpClient;
    }
}