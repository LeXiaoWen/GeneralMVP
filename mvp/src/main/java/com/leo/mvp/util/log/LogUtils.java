package com.leo.mvp.util.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import androidx.annotation.Nullable;

/**
 *
 * 进一步对logger封装
 * Created by Leo on 2017/6/26.
 */

public class LogUtils {
    public static final String TAG = "leo";
    private static int methodCount = 1;
    private static int methodOffset = 1;

    private LogUtils() {
        throw new UnsupportedOperationException("u can\'t instantiate me...");
    }

    /**
    * 需要在application中初始化
    *
    *@author Leo
    *created at 2017/6/26 下午9:51
    */
    public static void init(@Nullable String tag) {


        if (tag == null){
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(0)         // (Optional) How many method line to show. Default 2
                    .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                    .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                    .tag(TAG)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                    .build();

            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        }else {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(0)         // (Optional) How many method line to show. Default 2
                    .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                    .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                    .tag(tag)   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                    .build();

            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        }


    }

    public static void d(Object obj) {
        if(DebugUtil.isDebug) {
            Logger.d(obj);
        }

    }

    public static void d(String message, Object... args) {
        if(DebugUtil.isDebug) {
            Logger.d(message, args);
        }

    }

    public static void e(String message, Object... args) {
        if(DebugUtil.isDebug) {
            Logger.e(message, args);
        }

    }

    public static void w(String message, Object... args) {
        if(DebugUtil.isDebug) {
            Logger.w(message, args);
        }

    }

    public static void v(String message, Object... args) {
        if(DebugUtil.isDebug) {
            Logger.v(message, args);
        }

    }

    public static void wtf(String message, Object... args) {
        if(DebugUtil.isDebug) {
            Logger.wtf(message, args);
        }

    }

    public static void json(String message) {
        if(DebugUtil.isDebug) {
            Logger.json(message);
        }

    }

    public static void xml(String message) {
        if(DebugUtil.isDebug) {
            Logger.xml(message);
        }

    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if(DebugUtil.isDebug) {
            Logger.log(priority, tag, message, throwable);
        }

    }
}
