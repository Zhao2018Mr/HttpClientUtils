package com.zyj.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import java.net.URISyntaxException;

/**
 * @author : zyj
 * @version : 1.0
 * @date : 2020-7-7 09:40:06
 */
public class HttpClientFactory {


    private static HttpClientFactory instance = null;

    private HttpClientFactory()
    {
    }

    public synchronized static HttpClientFactory getInstance()
    {
        if (instance == null)
        {
            instance = new HttpClientFactory();
        }
        return instance;
    }


    public synchronized HttpClient getHttpClient()
    {
        HttpClient httpClient = null;
        if (HttpConstant.IS_KEEP_ALIVE)
        {
            //获取长连接
            httpClient = new KeepAliveHttpClientBuilder().getKeepAliveHttpClient();
        } else
        {
            // 获取短连接
            httpClient = new HttpClientBuilder().getHttpClient();
        }
        return httpClient;
    }

    public HttpPost httpPost(String httpUrl)
    {
        HttpPost httpPost = null;
        httpPost = new HttpPost(httpUrl);
        if (HttpConstant.IS_KEEP_ALIVE)
        {
            // 设置为长连接，服务端判断有此参数就不关闭连接。
            httpPost.setHeader("Connection", "Keep-Alive");
        }
        return httpPost;
    }

    public HttpGet httpGet(String httpUrl)
    {
        HttpGet httpGet = null;
        URIBuilder uriBuilder = null;
        // 通过httpget方式来实现我们的get请求
        try {
            uriBuilder = new URIBuilder(httpUrl);
            httpGet = new HttpGet(uriBuilder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (HttpConstant.IS_KEEP_ALIVE)
        {
            // 设置为长连接，服务端判断有此参数就不关闭连接。
            httpGet.setHeader("Connection", "Keep-Alive");
        }
        return httpGet;
    }



}