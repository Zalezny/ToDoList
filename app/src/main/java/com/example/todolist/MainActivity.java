package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements onDeleteDialogListener {
    private EditText item;


    private ArrayList<String> itemList = new ArrayList<>();

    private ArrayList<String> webList = new ArrayList<>();

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webList.add("https://us.123rf.com/450wm/nadzin/nadzin2006/nadzin200600003/nadzin200600003.jpg?ver=6");
        webList.add("https://github.com/Zalezny");
        webList.add("https://avatars.githubusercontent.com/u/65240240?v=4");



        item = findViewById(R.id.editText);
        Button add = findViewById(R.id.button);


        itemList = FileHelper.readData(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter = new RecyclerAdapter(itemList, MainActivity.this);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(v -> {
            if(itemList.size() % 2 == 0) {
                String itemName = item.getText().toString();
                itemList.add(itemName);
            }
            else {
                //random generator
                int index = new Random().nextInt(webList.size());
                itemList.add(webList.get(index));
            }

            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onDeleteDialogResult(boolean isDeleted, int position) {
        itemList.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(itemList, getApplicationContext());
    }
}