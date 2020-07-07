package com.zyj.httpclient;

public class Main {

    public static void main(String[] args) {
        String str = HttpClientUtil.sendHttpGet("http://api.map.baidu.com/telematics/v3/weather?location=北京&output=json&ak=lsTdWpHKKx2j4m1LhfDDXUgW",3);
        System.out.println(str);
    }

}
