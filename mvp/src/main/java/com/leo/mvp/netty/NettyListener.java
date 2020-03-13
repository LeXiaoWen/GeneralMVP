package com.leo.mvp.netty;


import io.netty.channel.Channel;

/**
* netty 状态
*
*@author Leo
*created at 2019/5/2 11:53 AM
*/

public interface NettyListener {

    byte STATUS_CONNECT_SUCCESS = 1;

    byte STATUS_CONNECT_CLOSED = 2;

    byte STATUS_CONNECT_ERROR = 0;


    /**
     * 对消息的处理
     */
    void onMessageResponse(String messageHolder);

    /**
     * 当服务状态发生变化时触发
     */
    void onServiceStatusConnectChanged(int statusCode);

    /**
     * 发送心跳
     * @param channel
     */
    void onServiceHeart(Channel channel);
}
