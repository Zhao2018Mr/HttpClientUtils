package com.zyj.httpclient;

import com.sun.xml.internal.ws.policy.PolicyMapUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public HttpGet httpGet(String httpUrl, Map<String,String> map)
    {
        HttpGet httpGet = null;
        URIBuilder uriBuilder = null;
        // 通过httpget方式来实现我们的get请求
        try {
            uriBuilder = new URIBuilder(httpUrl);
            if(map!=null){
                List<NameValuePair> list = new LinkedList<NameValuePair>();
                for(Map.Entry<String, String> entry : map.entrySet()){
                    BasicNameValuePair param = new BasicNameValuePair(entry.getKey(), entry.getValue());
                    list.add(param);
                }
                uriBuilder.addParameters(list);
            }
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