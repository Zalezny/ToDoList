package com.example.todolist;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class DataService extends Service {

    SharedPreferences sharedPrefs;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPrefs = getApplicationContext().getSharedPreferences(
                getApplicationContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        if ("ACTION_WRITE_DATA".equals(intent.getAction())) {
            String itemListJson = intent.getStringExtra(
                    getString(R.string.INTENT_ITEM_LIST_KEY));
            writeData(itemListJson);
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void writeData(String itemJson) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(getString(R.string.shared_preferences_item_list_key), itemJson).apply();

    }

}

