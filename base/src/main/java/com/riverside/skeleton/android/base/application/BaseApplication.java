package com.riverside.skeleton.android.base.application;

import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.riverside.skeleton.android.base.activity.ActivityStaticMonitor;
import com.riverside.skeleton.android.base.utils.CollectInfo.AppVersionInfo;
import com.riverside.skeleton.android.base.utils.CollectInfo.CollectInfoHelper;
import com.riverside.skeleton.android.base.utils.CollectInfo.DisplayInfo;
import com.riverside.skeleton.android.base.utils.CollectInfo.OSInfo;
import com.riverside.skeleton.android.base.utils.CollectInfo.OSVersionInfo;
import com.riverside.skeleton.android.base.utils.CollectInfo.TelephonyInfo;
import com.riverside.skeleton.android.base.utils.CrashCallback;
import com.riverside.skeleton.android.util.log.SLog;
import com.zxy.recovery.core.Recovery;

/**
 * Application基类    1.0
 * <p>
 * b_e 2017/11/20
 */
public class BaseApplication extends MultiDexApplication {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        //保存Application单例对象
        instance = this;

        SLog.init(getApplicationContext());

        //设置收集信息
        CollectInfoHelper.init(instance);
        CollectInfoHelper.addInfoSource(
                AppVersionInfo.class
                , OSInfo.class
                , OSVersionInfo.class
                , TelephonyInfo.class
                , DisplayInfo.class
        );

        if (SLog.isDebug()) {
            //开启Android的严格模式
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        } else {
            SLog.setLogLevel(SLog.LEVEL_ERROR);
        }

        //启动防灾模式
        Recovery.getInstance()
                .debug(SLog.isDebug())
                .recoverInBackground(true)
                .recoverStack(SLog.isDebug())
                .callback(new CrashCallback(getApplicationContext()))
                .silent(true, Recovery.SilentMode.RESTART)
                .init(this);

        //启动Activity状态监控
        ActivityStaticMonitor.init(this);
    }

    /**
     * 取得Application单例对象
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return instance;
    }
}
