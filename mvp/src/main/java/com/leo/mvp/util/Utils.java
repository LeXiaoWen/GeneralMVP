package com.leo.mvp.util;

/**
 * Created by Leo on 2017/5/4.
 */

import android.content.Context;

public class Utils {
    public static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can\'t instantiate me...");
    }

    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }
}

