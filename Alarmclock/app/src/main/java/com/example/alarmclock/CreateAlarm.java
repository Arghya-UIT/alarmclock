package com.example.alarmclock;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.Locale;

public class CreateAlarm extends AppCompatActivity {

    private LinearLayoutCompat getTimeLayout;
    private LinearLayoutCompat getSelectedDaysLayout;
    private LinearLayoutCompat getRingtoneLayout;
    private EditText getAlarmNameTextview;
    private TextView pickedTime;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_alarm);

        // Initialize UI elements
        pickedTime = findViewById(R.id.pickedTime);
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


        getTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
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

    private void showTimePickerDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dailouge_time_picker, null);

        TimePicker timePicker = dialogView.findViewById(R.id.timePicker);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour, minute;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            hour = timePicker.getHour();
                            minute = timePicker.getMinute();
                        } else {
                            hour = timePicker.getCurrentHour();
                            minute = timePicker.getCurrentMinute();
                        }

                        // Get AM/PM selection
                        int amPm = hour >= 12 ? 1 : 0;

                        // Convert to 12-hour format if needed
                        if (hour > 12) {
                            hour -= 12;
                        } else if (hour == 0) {
                            hour = 12;
                        }

                        // Handle the selected time (hour, minute, amPm) here
                        String amPmText = amPm == 1 ? "PM" : "AM";
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d %s", hour, minute, amPmText);
                        // Assuming you have a TextView for displaying the selected time
                        pickedTime.setText(selectedTime);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
