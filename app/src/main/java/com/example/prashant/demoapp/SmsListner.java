package com.example.prashant.demoapp;

import android.telephony.SmsMessage;

/**
 * Created by prashant on 5/5/2016.
 */
public interface SmsListner {
    void onSmsReceived(Messages smsMessage);
}
