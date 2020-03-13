package com.leo.mvp.net;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 切换线程
 * Created by Leo on 2017/7/23.
 */

public class RxSchedulers {


    /**
     * Schedulers.computation: 适合运行在密集计算的操作，大多数异步操作符使用该调度器。
     * Schedulers.io:适合运行I/0和阻塞操作.
     * Schedulers.single:适合需要单一线程的操作
     * Schedulers.trampoline: 适合需要顺序运行的操作
     *
     * @author Leo
     * created at 2020/3/12 11:56 PM
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
