package com.bawei.wangliyang.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.bawei.wangliyang.Api;
import com.bawei.wangliyang.R;
import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/*
 * 功能：工具类
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public class NetUtil {

    private static final String HTTPURL = "http://blog.zhaoliang5156.cn/";
    private final Api api;
    //使用静态内部类创建netutil
    private static final class SingleHolder{
        private static final NetUtil netutil = new NetUtil();
    }

    public static NetUtil getInstance() {
        return SingleHolder.netutil;
    }

    public NetUtil() {
        //创建日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建okhttp
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }
    //设置get方法
    public Api getApi() {
        return api;
    }
    //创建图片请求方法
    public void getPho(String url, ImageView imageView){
        Glide.with(imageView)
                .load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imageView);
    }
    //创建判断网络方法
    public boolean HasNext(Context context){
        ConnectivityManager systemService = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if (activeNetworkInfo!=null){
            return true;
        }else {
            return false;
        }
    }
}
