package com.example.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.alarmclock.database.AlarmModel;
import com.example.alarmclock.database.MyDbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AlarmModel> alarmaList;
    private ArrayAdapter<AlarmModel> adapter;
    private MyDbHelper dbHelper;
    private int  MAKE_NEW_ALARM_ACTIVITY =1;
    private int ADD_TASK_REQUEST_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView collaspedText = findViewById(R.id.collapsed_text);
        FloatingActionButton addNewAlarmBtn= findViewById(R.id.addNewAlarmBtn);

        collaspedText.setText(formattedDate);
        toolbar.setTitle("active alarms");
        setSupportActionBar(toolbar);
        fetchContact();

        addNewAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an explicit intent to open the NewAlarmActivity
                Intent intent = new Intent(MainActivity.this, CreateAlarm.class);
                startActivity(intent);
            }
        });
        dbHelper = new MyDbHelper(this);
        alarmaList = new ArrayList<>();
        adapter = new CustomeAdapter(this, alarmaList);


        ListView contactListView = findViewById(R.id.alarmListListview);
        contactListView.setAdapter(adapter);

        fetchContact();

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchContact();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }

    private void fetchContact() {
        alarmaList.clear();
        alarmaList.addAll(dbHelper.fetchTask());
        adapter.notifyDataSetChanged();
    }



}