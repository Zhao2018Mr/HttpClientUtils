package com.zyj.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;

/**
 * @author : zyj
 * @version : 1.0
 * @date : 2020-7-7 09:40:06
 */
public class HttpClientBuilder {
    private static HttpClient httpClient;

    /**
     * 获取http短连接
     */
    public  synchronized HttpClient getHttpClient() {
        if (httpClient == null) {
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置请求超时时间
                    .setConnectTimeout(HttpConstant.CONNECT_TIMEOUT)
                    // 设置响应超时时间
                    .setSocketTimeout(HttpConstant.SOCKET_TIMEOUT).build();
            // 创建连接
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        }
        return httpClient;
    }
}