package com.example.prashant.demoapp.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.prashant.demoapp.Messages;
import com.example.prashant.demoapp.SmsConfig;
import com.example.prashant.demoapp.SmsListner;

/**
 * Created by prashant on 5/4/2016.
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {
    private SmsConfig config;
    private SmsListner smsListner;

    public SmsBroadcastReceiver(SmsConfig config, SmsListner smsListner) {
        this.config = config;
        this.smsListner=smsListner;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            // pdu's are array because sms contain max 160 characters and after that next pdu created. So we have to iterate all of them
            Object[] sms = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage = null;
            // Iterate over pdus
            for (Object object : sms) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = bundle.getString("format");
                    smsMessage = SmsMessage.createFromPdu((byte[]) object, format);
                } else {
                    smsMessage = SmsMessage.createFromPdu((byte[]) object);
                }
            }
            //this will update the UI with message
            if (smsMessage!=null && smsMessage.getOriginatingAddress()!=null && smsMessage.getOriginatingAddress().equalsIgnoreCase(config.getSender())) {
                smsListner.onSmsReceived(new Messages(smsMessage));
            }
        }
    }

}
