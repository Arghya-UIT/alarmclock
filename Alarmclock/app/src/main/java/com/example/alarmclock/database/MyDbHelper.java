package com.example.alarmclock.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VIRSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY,"
                + Params.ALARM_NAME + " TEXT,"
                + Params.TIME_FOR_DISPLAY + " TEXT,"
                + Params.TIME_FOR_STORE + " TEXT,"
                + Params.SELECTED_DAYS + " TEXT,"
                + Params.URI + " TEXT,"
                + Params.STATUS + " TEXT"
                + ")";

        Log.d("db---arghya", "query " + create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Implement if needed
    }

    public void addTask(AlarmModel alarmModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // ... Set other values as needed

        // Convert the set of selected days to a JSON string

        values.put(Params.ALARM_NAME, alarmModel.getAlarm_name());
        values.put(Params.TIME_FOR_DISPLAY, alarmModel.getTime_for_display());
        values.put(String.valueOf(Params.TIME_FOR_STORE), alarmModel.getTime_for_store());
        values.put(Params.SELECTED_DAYS, alarmModel.getSelectedDays());  // Store the JSON string
        values.put(Params.URI, alarmModel.getRingtone_uri());
        values.put(Params.STATUS,alarmModel.getStatus());

        long newRowId =db.insert(Params.TABLE_NAME, null, values);
        db.close();
        if (newRowId != -1) {
            Log.d("dbarghya", "Alarm added successfully. ID: " + newRowId);
            Log.d("dbarghya", "Alarm Details: " + alarmModel.toString());
        } else {
            Log.d("dbarghya", "Failed to add alarm to the database.");
        }
    }

    public ArrayList<AlarmModel> fetchTask() {
        ArrayList<AlarmModel> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                AlarmModel alarmModel = new AlarmModel();
                alarmModel.setId(cursor.getInt(0));
                alarmModel.setAlarm_name(cursor.getString(1));
                alarmModel.setTime_for_display(cursor.getString(2));
                alarmModel.setTime_for_store(cursor.getString(3));
                alarmModel.setRingtone_uri(cursor.getString(5));
                alarmModel.setSelectedDays(cursor.getString(4));
                alarmModel.setStatus(cursor.getString(6));

                taskList.add(alarmModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;
    }



}