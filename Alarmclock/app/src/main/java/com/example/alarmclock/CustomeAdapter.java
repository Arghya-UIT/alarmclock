package com.example.alarmclock;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarmclock.database.AlarmModel;

import java.util.List;

public class CustomeAdapter extends ArrayAdapter<AlarmModel> {
    private List<AlarmModel> alarmList;
    TextView alarmName, showTime, am_pm, seletedDays, editBtn;
    Switch toggleBtn;

    public CustomeAdapter(Context context, List<AlarmModel> alarmList) {
        super(context, R.layout.custome_adapter_design, alarmList);
        this.alarmList = alarmList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.custome_adapter_design, parent, false);
        }

        alarmName = view.findViewById(R.id.alarmName);
        showTime = view.findViewById(R.id.showTime);
        am_pm = view.findViewById(R.id.am_pm);
        seletedDays = view.findViewById(R.id.selectedDays);
        toggleBtn = view.findViewById(R.id.alarmOnOffBtn);


        // Get the current contact from the list
        AlarmModel alarm = alarmList.get(position);


        alarmName.setText(alarm.getAlarm_name());
        showTime.setText(alarm.getTime_for_display());
        seletedDays.setText(alarm.getSelectedDays());
        if ("1".equals(alarm.getStatus())) {
            toggleBtn.setChecked(true);  // Turn the switch on
        } else {
            toggleBtn.setChecked(false); // Turn the switch off
        }

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Open EditTaskActivity with the task ID
                        Intent editIntent = new Intent(getContext(), EditTaskActivity.class);
                        editIntent.putExtra("task_id", alarm.getId()); // Pass the task ID
                        getContext().startActivity(editIntent);
                    }
                });
                Toast.makeText(getContext(), "Image clicked at position: " + position, Toast.LENGTH_SHORT).show();
                Log.d("this---db id", " " + alarm.getId());
            }
        });


        return view;
    }
}