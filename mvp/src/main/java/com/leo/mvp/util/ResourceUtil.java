package com.leo.mvp.util;


import android.content.Context;
import android.os.Build;

import androidx.annotation.ColorRes;


/**
* 资源获取相关工具类
*用于获取颜色,字符串,尺寸等资源
*@author Leo
*created at 2016/5/16 下午10:08
*/
public class ResourceUtil {
    /**
     * 通过传入的colorID获取颜色值
     *
     * @param context 上下文
     * @param colorId 资源id
     * @return
     */
    public static int getColor(Context context, @ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(colorId);
        } else {
            return context.getResources().getColor(colorId);
        }
    }
}
