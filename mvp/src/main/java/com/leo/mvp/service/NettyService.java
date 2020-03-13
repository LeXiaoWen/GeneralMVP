package com.leo.mvp.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;
import com.leo.mvp.netty.ClientIdleStateTrigger;
import com.leo.mvp.netty.NettyClient;
import com.leo.mvp.netty.utils.NettyManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.netty.channel.Channel;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;


/**
 * socket本地注册的service
 * @author Leo
 * created at 2019/4/14 6:04 PM
 */
public abstract class NettyService extends Service {
    private static Gson mGson = new Gson();
    private NetworkReceiver receiver;
    public static final String TAG = NettyService.class.getName();
    private List<Integer> ports = new ArrayList<>();


    public NettyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new NetworkReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        Map<String, NettyClient> clientMap = NettyManager.getInstance().getClientMap();
        for (NettyClient client : clientMap.values()) {
            client.setReconnectNum(0);
            client.disconnect();
        }
    }


//    private void notifyData(int type, String messageHolder) {
//        final Stack<Activity> activities = ActivityManager.getInstance().getActivities();
//        for (Activity activity : activities) {
//            if (activity == null || activity.isFinishing()) {
//                continue;
//            }
//            Message message = Message.obtain();
//            message.what = type;
//            message.obj = messageHolder;
//            if (activity instanceof NettyActivity) {
//                ((NettyActivity) activity).getHandler().sendMessage(message);
//            }
//        }
//    }
//    private void notifyVisionData(int type, String messageHolder) {
//        final Stack<Activity> activities = ActivityManager.getInstance().getActivities();
//        for (Activity activity : activities) {
//            if (activity == null || activity.isFinishing()) {
//                continue;
//            }
//            Message message = Message.obtain();
//            message.what = type;
//            message.obj = messageHolder;
//            if (activity instanceof NettyActivity) {
//                ((NettyActivity) activity).getVisionHandler().sendMessage(message);
//            }
//        }
//    }


    public class NetworkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
                        || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                    Log.e(TAG, "connecting ...");
                }
            }
        }
    }
    private void ping(Channel channel) {
//        int second = Math.max(1, random.nextInt(baseRandom));
//        System.out.println("next heart beat will send after " + second + "s.");
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
        }, 2, TimeUnit.SECONDS);

        future.addListener((GenericFutureListener) future1 -> {
            if (future1.isSuccess()) {
                ping(channel);
            }
        });
    }

}
