package com.example.alarmclock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAlarm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_alarm);


        LinearLayout getTime = findViewById(R.id.getTimeLayout);
        LinearLayout getSelectedDays = findViewById(R.id.getSelectedDaysLayout);
        LinearLayout getRingtoneUri = findViewById(R.id.getRingtoneLayout);
        EditText getAlarmName = findViewById(R.id.getAlarmNameTextview);
        Button cancelButton= findViewById(R.id.cancelButton);
        Button saveButton= findViewById(R.id.saveButton);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
