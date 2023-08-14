package com.example.alarmclock;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.Arrays;
import java.util.Locale;

public class CreateAlarm extends AppCompatActivity {

    private LinearLayoutCompat getTimeLayout;
    private LinearLayoutCompat getSelectedDaysLayout;
    private LinearLayoutCompat getRingtoneLayout;
    private EditText getAlarmNameTextview;
    private TextView pickedTime;
    private TextView pickedRingTone;
    private Button cancelButton;
    private Button saveButton;


    private static final int PICK_RINGTONE_REQUEST = 1;
    private Uri ringtoneUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_alarm);

        // Initialize UI elements
        pickedTime = findViewById(R.id.pickedTime);
        pickedRingTone = findViewById(R.id.pickedRingTone);
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


        getSelectedDaysLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateSelectorDialog();
            }
        });

        getRingtoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickRingtone();
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

    private void showDateSelectorDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dailouge_select_days, null);

        final CheckBox sundayCheckBox = dialogView.findViewById(R.id.sunday);
        final CheckBox mondayCheckBox = dialogView.findViewById(R.id.monday);
        final CheckBox tuesdayCheckBox = dialogView.findViewById(R.id.tuesday);
        final CheckBox wednesdayCheckBox = dialogView.findViewById(R.id.wednesday);
        final CheckBox thursdayCheckBox = dialogView.findViewById(R.id.thursday);
        final CheckBox fridayCheckBox = dialogView.findViewById(R.id.friday);
        final CheckBox saturdayCheckBox = dialogView.findViewById(R.id.saturday);

        // Initialize an array to keep track of selected days
        final boolean[] selectedDays = new boolean[7];
        Arrays.fill(selectedDays, true); // Initially, all days are selected

        // Set checked state for the CheckBoxes based on the array
        sundayCheckBox.setChecked(selectedDays[0]);
        mondayCheckBox.setChecked(selectedDays[1]);
        tuesdayCheckBox.setChecked(selectedDays[2]);
        wednesdayCheckBox.setChecked(selectedDays[3]);
        thursdayCheckBox.setChecked(selectedDays[4]);
        fridayCheckBox.setChecked(selectedDays[5]);
        saturdayCheckBox.setChecked(selectedDays[6]);


        // Create the AlertDialog.Builder instance and set the view
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Select Days");

        // Set positive button click listener
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Update the selectedDays array based on CheckBox states
                selectedDays[0] = sundayCheckBox.isChecked();
                selectedDays[1] = mondayCheckBox.isChecked();
                selectedDays[2] = tuesdayCheckBox.isChecked();
                selectedDays[3] = wednesdayCheckBox.isChecked();
                selectedDays[4] = thursdayCheckBox.isChecked();
                selectedDays[5] = fridayCheckBox.isChecked();
                selectedDays[6] = saturdayCheckBox.isChecked();


//                for (int i = 0; i < selectedDays.length; i++) {
//                    Log.d("Selected Days", "Day " + i + ": " + selectedDays[i]);
//                }

                // Handle the selectedDays array as needed
                // For example, you can pass it to another method or store it
            }
        });

        // Set negative button click listener
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the Cancel button click or dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }


    private void showTimePickerDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dailouge_time_picker, null);

        TimePicker timePicker = dialogView.findViewById(R.id.timePicker);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView).setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void pickRingtone() {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        startActivityForResult(intent, PICK_RINGTONE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_RINGTONE_REQUEST && resultCode == RESULT_OK) {
             ringtoneUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
//             Log.d("uri tone ",".. "+ringtoneUri);

            if (ringtoneUri != null) {
                Ringtone ringtone = RingtoneManager.getRingtone(this, ringtoneUri);
                String ringtoneName = ringtone.getTitle(this);
                pickedRingTone.setText(ringtoneName);
            }
        }
    }

}
