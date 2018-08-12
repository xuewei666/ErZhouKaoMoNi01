package com.bwei.erzhoukaomoni.data;

import android.app.Application;

import com.bwei.imageloaderlibrary.utils.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Jack Lee on 2018/8/11.
 * name:Juck Lee
 */

public class App extends Application {
    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();


        app = this;

        ImageLoaderConfiguration configuration = ImageLoaderUtils.getConfiguration(this);
        ImageLoader.getInstance().init(configuration);
        CrashReport.initCrashReport(getApplicationContext(),"sds4f5ds4fsd",false);
    }
    public static App getInstance(){

        return app;
    }
}
