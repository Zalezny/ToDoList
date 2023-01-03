package com.example.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onDeleteDialogListener {
    private EditText item;


    private ArrayList<String> itemList = new ArrayList<>();


    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        item = findViewById(R.id.editText);
        Button add = findViewById(R.id.button);


        itemList = FileHelper.readData(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter = new RecyclerAdapter(itemList, MainActivity.this);
        recyclerView.setAdapter(adapter);


        add.setOnClickListener(v -> {
            String itemName = item.getText().toString();
            itemList.add(itemName);
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