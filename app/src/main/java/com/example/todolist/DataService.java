package com.example.todolist;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        switch (intent.getAction()) {
            case "ACTION_WRITE_DATA":
                ArrayList<String> itemList = (ArrayList<String>) intent.getSerializableExtra(
                                getString(R.string.INTENT_ITEM_LIST_KEY));
                writeData(itemList, getApplicationContext());
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

    private void writeData(ArrayList<String> item, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(item);
            oas.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

