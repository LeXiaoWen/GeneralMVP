package com.leo.mvp.util.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Leo on 2017/6/26.
 */

public class BusUtils {
    public BusUtils() {
    }

    public static void postMessage(Object event) {
        EventBus.getDefault().post(event);
    }
}
