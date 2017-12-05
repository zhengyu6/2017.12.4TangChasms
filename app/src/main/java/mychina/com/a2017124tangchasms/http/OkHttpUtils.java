package mychina.com.a2017124tangchasms.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import mychina.com.a2017124tangchasms.bean.BaseBean;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 张晓辉 on 2017/12/4.
 * 通过OkHttp封装的网络框架
 */

public class OkHttpUtils {
    String str;
    private static final String TAG = "OkHttpUtil";
    private static OkHttpUtils mOkHttpUtils = new OkHttpUtils();
    private OkHttpClient client;
    private final Gson gson;

    private OkHttpUtils() {
        //初始化工作
        //client = new OkHttpClient();
        //建造者模式：里面的设置方法都属于hook函数，就是钩子，可以人为控制对象的不同类型创建
        client = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                //拦截器：对要向服务器发送请求之前的一个处理：request
                .addInterceptor(new MyInterceptor())
                .build();
        gson = new Gson();
    }

    class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            long preTime = System.currentTimeMillis();
            Request request = chain.request();
            //封装完成之后再去做请求
            Response proceed = chain.proceed(request);
            long afterTime = System.currentTimeMillis();
            long costTime = afterTime - preTime;
            return proceed;
        }
    }

    public static OkHttpUtils getInstance() {
        return mOkHttpUtils;
    }

    //定义获得数据的方法  这个方法只返回String
    public String get(String url, HashMap<String, String> map) throws IOException {
        final Request request;
        //可以直接拼接url,也可以通过传入的map拼接url,map参数可以为空，也可以不为空
        if (map == null || map.isEmpty()) {
            request = new Request.Builder()
                    .url(url)
                    .get()
                    //通过header 判断是否是当前是不是我这个端，
                    // 但是不是最安全的做法，能够通过fiddler和charls直接抓到，
                    // 所以不是很安全
//                    .header("name", "我是呵呵呵")
                    .build();
        } else {
            String newUrl = getNewUrl(url, map);
            request = new Request.Builder()
                    .url(newUrl)
                    .get()
                    .build();
        }
        if (client == null || request == null) {
            throw new RuntimeException("client和request 不能为空！");
        }
        Call call = client.newCall(request);
        Response execute = call.execute();
        str = execute.body().string();
        return str;

    }

    private String getNewUrl(String oldUrl, HashMap<String, String> map) {
        if (map == null || map.isEmpty()) {
            return oldUrl;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(oldUrl + "?");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> en : entries) {
            sb.append(en.getKey() + "=" + en.getValue() + "&");
        }
        sb.deleteCharAt(sb.length() - 1);
        String newUrl = sb.toString();
        return newUrl;
    }

    public String postString1(String url, HashMap<String, String> map) throws IOException {
        Response response;
        RequestBody requestBody;
        if (url == null || url.equals("")) {
            throw new RuntimeException("网址不能为空");
        }
        //创建一个请求体
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        if (map != null && !map.isEmpty()) {
            String json = map2Json(map);
            System.out.print(json);
            requestBody = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            response = client.newCall(request).execute();
        } else {
            //非法参数异常
            throw new IllegalArgumentException("缺少参数");
        }
        return response.body().string();
    }

    public String postString(String url, HashMap<String, String> map) throws IOException {
        Response response;
        RequestBody requestBody;
        if (url == null || url.equals("")) {
            throw new RuntimeException("网址不能为空");
        }
        //创建一个请求体
//        MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        if (map != null && !map.isEmpty()) {
            //            String json=map2Json(map);
//            requestBody=RequestBody.create(JSON,json);
            FormBody.Builder builder = new FormBody.Builder();
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry<String, String> entry : entries
                    ) {
                builder.add(entry.getKey(), entry.getValue());
            }
            FormBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            response = client.newCall(request).execute();
        } else {
            //非法参数异常
            throw new IllegalArgumentException("缺少参数");
        }
        return response.body().string();
    }

    //通过map转成json串
    private String map2Json(HashMap<String, String> map) {
        String s = gson.toJson(map);
        return s;
    }
    public BaseBean postBean(String url,HashMap<String,String>map) throws IOException {
        String post = postString(url,map);
        BaseBean<Object>bean = gson.fromJson(post,BaseBean.class);
        return bean;
    }
}
