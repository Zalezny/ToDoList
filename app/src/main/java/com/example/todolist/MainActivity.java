package com.example.todolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onDeleteDialogListener {
    EditText item;
    Button add;
    ListView listView;

    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);

        itemList = FileHelper.readData(this);


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, itemList);

        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(v -> {
            String itemName = item.getText().toString();
            itemList.add(itemName);
            item.setText("");
            FileHelper.writeData(itemList, getApplicationContext());
            arrayAdapter.notifyDataSetChanged();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

            DeleteAlertDialogFragment alertDialogFr = new DeleteAlertDialogFragment();

            Bundle args = new Bundle();
            args.putInt(getString(R.string.ARGS_KEY_ALERT_DIALOG_FRAGMENT), position);

            alertDialogFr.setArguments(args);
            alertDialogFr.show(getSupportFragmentManager(), getString(R.string.DELETE_ALERT_DIALOG_FRAGMENT_TAG));

        });



    }

    @Override
    public void onDeleteDialogResult(boolean isDeleted, int position) {
        itemList.remove(position);
        arrayAdapter.notifyDataSetChanged();
        FileHelper.writeData(itemList, getApplicationContext());
    }
}