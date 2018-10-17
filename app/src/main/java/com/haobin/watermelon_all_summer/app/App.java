package com.haobin.watermelon_all_summer.app;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.haobin.watermelon_all_summer.http.NetProviderImpl;
import com.squareup.leakcanary.LeakCanary;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Wenghaobin
 * on 2018/10/9
 * 全局Application
 */
public class App extends Application {

    private static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        XApi.registerProvider(new NetProviderImpl());

        ILFactory.getLoader().init(app);

        initLeakCanary();
        initTextSize();
    }

    /**
     *  引入内存泄漏的检测
     */
    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
