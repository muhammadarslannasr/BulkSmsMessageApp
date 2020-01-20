package com.example.bulksmsmarketingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.bulksmsmarketingapp.MainActivity.phoneNumbersUse;

public class CounterActivity extends AppCompatActivity {
    int MY_PERMISSION_REQUEST_SEND_SMS = 1;
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    int smsCounter = 0;
    PendingIntent sentPI,deliveredPI;
    String data;
    Button stopButton;
    TextView counterText;
    BroadcastReceiver smsSentReceiver,smsDeliveredReceiver;
    Thread senderThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.mipmap.bar_icon_text);
        checkSystemWritePermission();
        data = "\n   @By MS@ ";
        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        if (getIntent().getStringExtra("name").compareTo("default") == 0) {
            sendMessage(getIntent().getStringExtra("text"));
        }

        stopButton = findViewById(R.id.stop_button);
        counterText = findViewById(R.id.counter_txt);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senderThread.interrupt();
                senderThread = null;
                startActivity(new Intent(CounterActivity.this, MainActivity.class));
                finish();
                System.exit(1);
            }
        });

    }

    void sendMessage(final String my_message){
        senderThread =  new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0 ; i<phoneNumbersUse.size(); i++) {
                    if (ContextCompat.checkSelfPermission(CounterActivity.this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CounterActivity.this, new String[]{Manifest.permission.SEND_SMS},
                                MY_PERMISSION_REQUEST_SEND_SMS);
                    } else {
                        try {
                            sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(phoneNumbersUse.get(i), null, my_message, sentPI, deliveredPI);
                    }
                }
            }
        };

        senderThread.start();
    }

    private boolean checkSystemWritePermission() {
        boolean retVal = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            retVal = Settings.System.canWrite(this);
            Log.d("Permission", "Can Write Settings: " + retVal);
            if(retVal){
                Toast.makeText(this, "Write allowed :-)", Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(this, "Write not allowed :-(", Toast.LENGTH_LONG).show();
            }
        }
        return retVal;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(smsDeliveredReceiver);
        unregisterReceiver(smsSentReceiver);
//        finish();
//        System.exit(1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "Sms sent!", Toast.LENGTH_SHORT).show();
                        smsCounter++;
                        counterText.setText(String.valueOf(smsCounter));
                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context, "Generic failure!", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(context, "No service!", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context, "NULL PDU!", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context, "Radio off!", Toast.LENGTH_SHORT).show();
                        break;
                }


            }
        };

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(context, "Sms delivered!", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(context, "Sms not delivered!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        registerReceiver(smsSentReceiver,new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver,new IntentFilter(DELIVERED));
    }
}
