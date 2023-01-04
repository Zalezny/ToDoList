package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileHelper {

    public static final String FILENAME = "listinfo.dat";

    public static void writeData(ArrayList<ItemDataModel> item, Context context) {
        Gson gson = new Gson();
        String jsonItem = gson.toJson(item);
        Intent intent = new Intent(context, DataService.class);
        intent.putExtra(context.getString(R.string.INTENT_ITEM_LIST_KEY), jsonItem);
        intent.setAction("ACTION_WRITE_DATA");
        context.startService(intent);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<ItemDataModel> readData(Context context) {

        SharedPreferences sharedPrefs = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String itemList = sharedPrefs.getString(
                context.getString(R.string.shared_preferences_item_list_key), "");

        if(itemList.isEmpty()) {
            Toast.makeText(context,"Zero save data", Toast.LENGTH_SHORT).show();
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        Type itemListType = new TypeToken<ArrayList<ItemDataModel>>(){}.getType();
        ArrayList<ItemDataModel> outputList = gson.fromJson(itemList, itemListType);
        return outputList;
    }
}
