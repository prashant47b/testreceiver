package com.example.prashant.demoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prashant.demoapp.Network.EventStatus;
import com.example.prashant.demoapp.Network.SmsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e("id", android_id);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SmsConfig smsConfig=new SmsConfig();
        smsConfig.setSender("9806012254");
        SmsSingleton.getInstance().init(this, smsConfig).register(new SmsListner() {
            @Override
            public void onSmsReceived(Messages messages) {
                Toast.makeText(MainActivity.this, "Message Received", Toast.LENGTH_SHORT).show();
                String smsMessageStr = "SMS From: " + messages.getSmsMessage().getOriginatingAddress() + "\n"
                        + "Subject :" + messages.getSmsMessage().getMessageBody();
               text.setText("Your Otp :"+messages.getOtp());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SmsSingleton.getInstance().unregister();
    }

    @Subscribe
    public void onEvent(EventStatus eventStatus) {
        progressBar.setProgress(eventStatus.getProgress());
    }

    @OnClick(R.id.button)
    public void clicked(View view) {
        Intent intent = new Intent(this, ServiceUtils.class);
        intent.setData(Uri.parse("http://www.pdf-archive.com/2011/02/27/dummy/dummy.pdf"));
        startService(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
