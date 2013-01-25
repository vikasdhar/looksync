package com.app.DetectUSB;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
 
public class DetectUSB extends BroadcastReceiver
{ 
    private static final String TAG = "DetactUSB";
    @Override
    public void onReceive(Context context, Intent intent) {
    // TODO Auto-generated method stub
    if (intent.getAction().equalsIgnoreCase( "android.intent.action.UMS_CONNECTED"))
    {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.MAGENTA);
        textView.setTextColor(Color.BLUE);
        textView.setPadding(10,10,10,10);
        textView.setText("USB connected..........");
        Toast toastView = new Toast(context);
                toastView.setDuration(Toast.LENGTH_LONG);
        toastView.setGravity(Gravity.CENTER, 0,0);
        toastView.setView(textView);
                toastView.show();
        Log.i(TAG,"USB connected..");
    }
 
    if (intent.getAction().equalsIgnoreCase( "android.intent.action.UMS_DISCONNECTED"))
    {
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.MAGENTA);
        textView.setTextColor(Color.BLUE);
        textView.setPadding(10,10,10,10);
        textView.setText("USB Disconnected..........");
        Toast toastView = new Toast(context);
                toastView.setDuration(Toast.LENGTH_LONG);
        toastView.setGravity(Gravity.CENTER, 0,0);
        toastView.setView(textView);
                toastView.show();
    }
    } 
}
