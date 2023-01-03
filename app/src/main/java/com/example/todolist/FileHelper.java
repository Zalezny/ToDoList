package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHelper {

    public static final String FILENAME = "listinfo.dat";

    public static void writeData(ArrayList<String> item, Context context) {
        Intent intent = new Intent(context, DataService.class);
        intent.putExtra(context.getString(R.string.INTENT_ITEM_LIST_KEY), item);
        intent.setAction("ACTION_WRITE_DATA");
        context.startService(intent);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<String> readData(Context context) {

        SharedPreferences sharedPrefs = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String itemList = sharedPrefs.getString(
                context.getString(R.string.shared_preferences_item_list_key), "");

        if(itemList.isEmpty()) {
            Toast.makeText(context,"Zero save data", Toast.LENGTH_SHORT).show();
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        ArrayList<String> outputList = gson.fromJson(itemList, ArrayList.class);
        return outputList;
    }
}
