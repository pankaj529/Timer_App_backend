package com.example.timer_app_backend;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final  String SendInNotificationID = "SendInNotificationID";
    public static final  String SendInNotificationName = "Notification";
    private  NotificationManager mManager;




    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel(){

        NotificationChannel channel = new NotificationChannel(SendInNotificationID,SendInNotificationName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManger().createNotificationChannel(channel);




    }

    public NotificationManager getManger(){

        if(mManager == null){

            mManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        }

        return mManager;

    }


    public NotificationCompat.Builder getSendNotification(String title, long timerNotify,long timerinSec) {

      Intent resultIntent = new Intent(this,MainActivity.class);
      PendingIntent resultPendingIntent = PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);



        return new NotificationCompat.Builder(getApplicationContext(), SendInNotificationID)
                .setContentTitle(title)
                .setContentText("the timer have " +timerNotify +" minutes" +timerinSec +" second left")
                .setSmallIcon(R.drawable.ic_one)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);




    }

}
