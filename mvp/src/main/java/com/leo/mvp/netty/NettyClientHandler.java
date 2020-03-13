package com.leo.mvp.netty;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;


/**
* netty
*
*@author Leo
*created at 2019/5/2 11:52 AM
*/
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {
    private static final String TAG = NettyClientHandler.class.getName();
    private NettyListener listener;
    private Gson mGson = new Gson();
    private Random random = new Random();
    private int baseRandom = 8;

    public NettyClientHandler(NettyListener listener) {
        this.listener = listener;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyClient.getInstance().setConnectStatus(true);
        listener.onServiceStatusConnectChanged(NettyListener.STATUS_CONNECT_SUCCESS);
        listener.onServiceHeart(ctx.channel());
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyClient.getInstance().setConnectStatus(false);
        listener.onServiceStatusConnectChanged(NettyListener.STATUS_CONNECT_CLOSED);
//        NettyClient.getInstance().reconnect();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String byteBuf) throws Exception {
        Log.e(TAG, "thread == " + Thread.currentThread().getName());
        Log.e(TAG, "来自服务器的消息 ====》" + byteBuf);
        listener.onMessageResponse(byteBuf);

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    private void ping(Channel channel) {
        int second = Math.max(1, random.nextInt(baseRandom));
        System.out.println("next heart beat will send after " + second + "s.");
        ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                if (channel.isActive()) {
                    System.out.println("sending heart beat to the server...");
                    channel.writeAndFlush(ClientIdleStateTrigger.HEART_BEAT);
                } else {
                    System.err.println("The connection had broken, cancel the task that will send a heart beat.");
                    channel.closeFuture();
                    throw new RuntimeException();
                }
            }
        }, 500, TimeUnit.MILLISECONDS);

        future.addListener((GenericFutureListener) future1 -> {
            if (future1.isSuccess()) {
                ping(channel);
            }
        });
    }
}
