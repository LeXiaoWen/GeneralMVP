package com.leo.mvp.util.toast;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.leo.mvp.util.Utils;

/**
 * Created by Leo on 2017/6/26.
 */

public class ToastUtils {
    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static boolean isJumpWhenMore;

    private ToastUtils() {
        throw new UnsupportedOperationException("u can\'t instantiate me...");
    }

    public static void init(boolean isJumpWhenMore) {
        ToastUtils.isJumpWhenMore = isJumpWhenMore;
    }

    public static void showShortToastSafe(final CharSequence text) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(text, 0);
            }
        });
    }

    public static void showShortToastSafe(final int resId) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 0);
            }
        });
    }

    public static void showShortToastSafe(final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 0, args);
            }
        });
    }

    public static void showShortToastSafe(final String format, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(format, 0, args);
            }
        });
    }

    public static void showLongToastSafe(final CharSequence text) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(text, 1);
            }
        });
    }

    public static void showLongToastSafe(final int resId) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 1);
            }
        });
    }

    public static void showLongToastSafe(final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 1, args);
            }
        });
    }

    public static void showLongToastSafe(final String format, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(format, 1, args);
            }
        });
    }

    public static void showShortToast(CharSequence text) {
        showToast(text, 0);
    }

    public static void showShortToast(int resId) {
        showToast(resId, 0);
    }

    public static void showShortToast(int resId, Object... args) {
        showToast(resId, 0, args);
    }

    public static void showShortToast(String format, Object... args) {
        showToast(format, 0, args);
    }

    public static void showLongToast(CharSequence text) {
        showToast(text, 1);
    }

    public static void showLongToast(int resId) {
        showToast(resId, 1);
    }

    public static void showLongToast(int resId, Object... args) {
        showToast(resId, 1, args);
    }

    public static void showLongToast(String format, Object... args) {
        showToast(format, 1, args);
    }

    private static void showToast(CharSequence text, int duration) {
        if(isJumpWhenMore) {
            cancelToast();
        }

        if(sToast == null) {
            sToast = Toast.makeText(Utils.context, text, duration);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }

        sToast.show();
    }

    private static void showToast(int resId, int duration) {
        showToast(Utils.context.getResources().getText(resId).toString(), duration);
    }

    private static void showToast(int resId, int duration, Object... args) {
        showToast(String.format(Utils.context.getResources().getString(resId), args), duration);
    }

    private static void showToast(String format, int duration, Object... args) {
        showToast(String.format(format, args), duration);
    }

    public static void cancelToast() {
        if(sToast != null) {
            sToast.cancel();
            sToast = null;
        }

    }
}
