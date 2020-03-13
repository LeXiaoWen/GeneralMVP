package com.leo.mvp.net;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.leo.mvp.util.Utils;
import com.leo.mvp.util.log.LogUtils;
import com.leo.mvp.util.toast.ToastUtils;

/**
 * Created by Leo on 2017/6/26.
 */

public class BaseApp extends Application {

    protected static Context context;
    protected static Handler handler;
    protected static int mainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Utils.init(getApplicationContext());
        LogUtils.init("leo");
        ToastUtils.init(false);
    }

    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }
}
