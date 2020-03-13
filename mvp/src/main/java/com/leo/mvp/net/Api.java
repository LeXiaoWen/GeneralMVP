package com.leo.mvp.net;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leo on 2017/7/9.
 */

public class Api {
    public Retrofit retrofit;
    public ApiService service;
    public static Context context;

    private static String url;

    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    private static class SingletonHolderUrl {
        private static final Api INSTANCE = new Api();
    }


    public static Api getInstance() {
        if (context == null)
            throw new RuntimeException("需要init Api");
        if (url != null)
            return SingletonHolderUrl.INSTANCE;
        return SingletonHolder.INSTANCE;
    }


    public static void init(Context con) {
        context = con;
    }

    public static void init(Context con, String url) {
        context = con;
        Api.url = url;
    }


    //构造方法私有
    private Api() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(context.getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(7676, TimeUnit.MILLISECONDS)
                .connectTimeout(7676, TimeUnit.MILLISECONDS)
//                .addInterceptor(headInterceptor)
                .addInterceptor(logInterceptor)
//                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.url == null ? Url.baseUrl : Api.url)//设置基础的请求地址
                .build();
        service = retrofit.create(ApiService.class);
    }


    public <T> T create(final Class<T> service) {
        T t = retrofit.create(service);
        return t;
    }


}