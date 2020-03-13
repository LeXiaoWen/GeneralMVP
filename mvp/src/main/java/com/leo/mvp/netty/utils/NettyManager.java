package com.leo.mvp.netty.utils;

import com.leo.mvp.netty.NettyClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理socket连接
 *
 * created by Leo on 2019/4/27 20 : 36
 */


public class NettyManager {
    public Map<String, NettyClient> mNettyClients = new HashMap();
    private static volatile NettyManager mNettyManager ;
    private NettyManager(){}
    public static NettyManager getInstance(){
        synchronized (NettyManager.class) {

            if (mNettyManager == null) {
                synchronized (NettyManager.class){
                    if (mNettyManager == null){
                        mNettyManager = new NettyManager();
                    }
                }

            }
            return mNettyManager;
        }
    }


    public NettyClient getClientByTag(String tag){
        return mNettyClients.get(tag);
    }

    public void addNettyClient(String tag, NettyClient client) {
        if (client == null) {
            return;
        }
        mNettyClients.put(tag,client);
    }

    public void removeNettyClient(String tag, NettyClient client) {
        if (client == null) {
            return;
        }
        client.disconnect();
        client.setListener(null);
        mNettyClients.remove(tag);
    }

    public Map<String, NettyClient> getClientMap(){
        return mNettyClients;
    }
}
