package com.leo.generalmvp;

import com.leo.mvp.net.Api;
import com.leo.mvp.net.BaseApp;

/**
 * created by Leo on 2020/3/13 11 : 45
 */


public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        Api.init(getApplicationContext(),"");
    }
}
