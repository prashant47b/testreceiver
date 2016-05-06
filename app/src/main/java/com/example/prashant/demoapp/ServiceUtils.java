package com.example.prashant.demoapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.webkit.URLUtil;

import com.example.prashant.demoapp.Network.EventStatus;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by prashant on 5/2/2016.
 */
public class ServiceUtils extends IntentService {

    public int waiting=0;

    public ServiceUtils() {
        super("ServiceUtils");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        waiting--;
        Log.e("No of task Remaining",""+waiting);
        startDownload(intent.getDataString());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        waiting++;
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload(String url) {
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            int total = urlConnection.getContentLength();
            byte[] buffer = new byte[1024];
            int count = 0;
            int completed = 0;
            File file = new File(Environment.getExternalStorageDirectory() +"/"+ URLUtil.guessFileName(url, null, null));
            FileOutputStream outputStream = new FileOutputStream(file);
            while ((count = inputStream.read(buffer)) != -1) {
                completed += count;
                publishprogress((completed * 100 / total));
                outputStream.write(buffer, 0, count);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void publishprogress(int progress) {
//        Intent intent = new Intent("com.example.prashant.demoapp.download_status");
//        intent.putExtra("status", progress);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        EventStatus eventStatus=new EventStatus(progress);
        EventBus.getDefault().post(eventStatus);
    }

}
