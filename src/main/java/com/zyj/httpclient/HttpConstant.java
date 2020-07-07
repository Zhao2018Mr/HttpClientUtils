package com.zyj.httpclient;

/**
 * @author : zyj
 * @version : 1.0
 * @date : 2020-7-7 09:46:00
 */
public class HttpConstant {

    /**httpClient连接超时时间,单位毫秒 */
    public static final int CONNECT_TIMEOUT = 3*1000;

    /**httpClient请求获取数据的超时时间(即响应时间) 单位毫秒*/
    public static final int SOCKET_TIMEOUT = 10*1000;

    /**http连接池大小*/
    public static final int MAX_TOTAL = 10;

    /**分配给同一个route(路由)最大的并发连接数*/
    public static final int MAX_CONN_PER_ROUTE = 2;

    /**http连接是否是长连接*/
    public static final boolean IS_KEEP_ALIVE = false;

    /**调用接口失败默认重新调用次数*/
    public static final int REQ_TIMES = 3;

    /**utf-8编码*/
    public static final String UTF8_ENCODE = "UTF-8";

    /** application/json */
    public static final String APPLICATION_JSON = "application/json";

    /** text/xml */
    public static final String TEXT_XML = "text/xml";

}