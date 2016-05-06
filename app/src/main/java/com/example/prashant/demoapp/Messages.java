package com.example.prashant.demoapp;

import android.telephony.SmsMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by prashant on 5/5/2016.
 */
public class Messages {
    public SmsMessage smsMessage;

    public Messages(SmsMessage smsMessage) {
        this.smsMessage = smsMessage;
    }

    public String getOtp() {
        String message = "";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(smsMessage.getMessageBody());
        if(m.find()) {
            System.out.println(m.group());
            message+="\n"+m.group();
        }
        return message;
    }

    public SmsMessage getSmsMessage() {
        return smsMessage;
    }
}
