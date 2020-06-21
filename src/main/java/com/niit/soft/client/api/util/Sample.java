package com.niit.soft.client.api.util;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @ClassName Sample
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/20 12:02 下午
 * @Version 1.0
 **/
public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "20501151";
    public static final String API_KEY = "MKMYRWIUsEsZETRyon0qRQ66";
    public static final String SECRET_KEY = "PrgHGo0m3wQUYpd1r11mwRWYIWK8yULX";

    public static void main(String[] args) {
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
//        String text = "百度是一家高科技公司";
//        JSONObject res = client.lexer(text, null);
//        System.out.println(res.toString(2));

        String text = "苹果是一家伟大的公司";

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        JSONObject res = client.sentimentClassify(text, options);
        System.out.println(res.toString(2));

    }

    public void sample(AipNlp client) {
        String text = "苹果是一家伟大的公司";

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        JSONObject res = client.sentimentClassify(text, options);
        System.out.println(res.toString(2));

    }
}

