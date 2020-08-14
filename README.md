# HttpClientUtils
使用工厂模式获取HttpClient对象,提供三个方法，分别是sendPostByForm，sendPostByJson，sendPostByXml,SendHttpGet

#2020-8-14 14:47:25 新增方法
```java
public static <T>String sendHttpGet(String url, T t, int reSend);
```
用于解析对象格式的参数 
其中注解  @HttpIgnore 代表该对象内的这个属性不解析为网址参数