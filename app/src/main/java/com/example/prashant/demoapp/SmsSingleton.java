package com.example.prashant.demoapp;

import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;

import com.example.prashant.demoapp.provider.SmsBroadcastReceiver;

/**
 * Created by prashant on 5/5/2016.
 */
public class SmsSingleton {
    public static SmsSingleton INSTANCE;
    private Context context;
    private SmsConfig config;
    private SmsBroadcastReceiver smsBroadcastReceiver;

    public SmsSingleton() {
    }

    public static SmsSingleton getInstance(){
        if (INSTANCE==null){
            INSTANCE=new SmsSingleton();
        }
        return INSTANCE;
    }

    public SmsSingleton init(Context context,SmsConfig config){
        this.context=context;
        this.config=config;
        return INSTANCE;
    }

    public void register(SmsListner smsListner){
        try {
            IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            intentFilter.setPriority(999);
            smsBroadcastReceiver = new SmsBroadcastReceiver(config,smsListner);
            context.registerReceiver(smsBroadcastReceiver, intentFilter);
        }catch (Exception e){
            if (config==null){
                Log.e("Initialize error","SmsSingleton is not initialized properly . Initialze it with init method");
            }
        }
    }

    public void unregister(){
        context.unregisterReceiver(smsBroadcastReceiver);
    }

}
