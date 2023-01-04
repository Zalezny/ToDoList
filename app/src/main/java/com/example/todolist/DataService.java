package com.example.todolist;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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


        switch (intent.getAction()) {
            case "ACTION_WRITE_DATA":
                String itemListJson = (String) intent.getStringExtra(
                                getString(R.string.INTENT_ITEM_LIST_KEY));
                writeData(itemListJson);

                stopSelf();
                break;


        }

        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private final String FILENAME = "listinfo.dat";

    private void writeData(String itemJson) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(getString(R.string.shared_preferences_item_list_key), itemJson).apply();

    }

}

