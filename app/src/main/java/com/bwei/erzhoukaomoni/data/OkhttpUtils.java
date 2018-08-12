package com.bwei.erzhoukaomoni.data;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Jack Lee on 2018/8/11.
 * name:Juck Lee
 */

public class OkhttpUtils {
    static  OkhttpUtils okhttpUtils;
    OkHttpClient okHttpClient;
    private  PersistentCookieJar cookieJar;

    private  OkhttpUtils(){
        if (null == okHttpClient) {
            synchronized (OkhttpUtils.class) {
                if (null == okHttpClient) {
                    // okHttpClient = new OkHttpClient();
                    cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance()));
                    okHttpClient = new OkHttpClient.Builder().cookieJar(cookieJar).build();
                }
            }
        }

    }

    public  static OkhttpUtils getInstance(){
        if (null == okhttpUtils){
            synchronized (OkhttpUtils.class){
                if (null == okhttpUtils){
                     okhttpUtils = new OkhttpUtils();

                }
            }

        }

        return okhttpUtils;
    }
    public void post(String urlstring, FormBody formBody, Callback callback){
        Request request = new Request.Builder().method("POST", formBody).url(urlstring).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void get(String geturl, Callback callback){
        Request request = new Request.Builder().url(geturl).build();
        okHttpClient.newCall(request).enqueue(callback);


    }
}
