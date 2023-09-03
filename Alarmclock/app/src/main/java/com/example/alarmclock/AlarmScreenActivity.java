package com.example.alarmclock;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmScreenActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_screen);

        TextView alarmName=findViewById(R.id.alarmName);
        Button snoozeBtn=findViewById(R.id.snoozeBtn);
        Button dismissBtn=findViewById(R.id.dismissBtn);

    }

}
