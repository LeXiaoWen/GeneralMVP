package com.leo.mvp.net;

import com.leo.mvp.util.data.AppUtils;

import java.io.File;

import okhttp3.Cache;

/**
 * Created by Leo on 2017/12/15.
 */

public class HttpCache {
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(AppUtils.getContext().getCacheDir().getAbsolutePath() + File
                .separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
