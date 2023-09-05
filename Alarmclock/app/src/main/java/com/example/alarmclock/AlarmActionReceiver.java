package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class AlarmActionReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if ("SNOOZE_ALARM".equals(action)) {
            Uri alarmsound = Uri.parse(intent.getStringExtra("sound"));
            long id = intent.getLongExtra("id", -1);

            long snoozeTimeMillis = System.currentTimeMillis() + (1 * 60 * 1000); // 1 minute

            Intent snoozeIntent = new Intent(context, AlarmReceiver.class);
            snoozeIntent.putExtra("id", id);
            snoozeIntent.putExtra("sound", alarmsound);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, snoozeTimeMillis, pendingIntent);
            }
            Log.d("scheduled", "1 min");
        } else if ("DISMISS_ALARM".equals(action)) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                long notificationId = intent.getLongExtra("notificationId", -1);
                notificationManager.cancel((int) notificationId);
            }
        }
    }

}
