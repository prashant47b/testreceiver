package com.example.prashant.demoapp;

/**
 * Created by prashant on 5/5/2016.
 */
public class SmsConfig {
    public String sender;
    public String content;

    public SmsConfig() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
