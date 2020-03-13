package com.leo.mvp.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
* 
*
*@author Leo
*created at 2019/4/14 6:07 PM
*/
public abstract class FutureListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            success();
        } else {
            error();
        }
    }

    public abstract void success();

    public abstract void error();
}
