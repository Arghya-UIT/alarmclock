package com.example.alarmclock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class CreateAlarm extends AppCompatActivity {

    private LinearLayoutCompat getTimeLayout;
    private LinearLayoutCompat getSelectedDaysLayout;
    private LinearLayoutCompat getRingtoneLayout;
    private EditText getAlarmNameTextview;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_alarm);

        // Initialize UI elements
        getTimeLayout = findViewById(R.id.getTimeLayout);
        getSelectedDaysLayout = findViewById(R.id.getSelectedDaysLayout);
        getRingtoneLayout = findViewById(R.id.getRingtoneLayout);
        getAlarmNameTextview = findViewById(R.id.getAlarmNameTextview);
        cancelButton = findViewById(R.id.cancelButton);
        saveButton = findViewById(R.id.saveButton);

        // Set click listeners for buttons
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle cancel button click
                finish(); // Close the activity
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle save button click

                finish(); // Close the activity
            }
        });
    }
}
