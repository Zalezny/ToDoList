package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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
        ArrayList<String> itemList = null;

        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {

            Toast.makeText(context,"The file " + FILENAME + " does not exist!", Toast.LENGTH_SHORT).show();

            itemList = new ArrayList<>();

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemList;
    }
}
