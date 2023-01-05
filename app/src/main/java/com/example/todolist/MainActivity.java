package com.example.todolist;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements onDeleteDialogListener {


    private ArrayList<ItemDataModel> itemList = new ArrayList<>();
    private final ArrayList<String> webList = new ArrayList<>();
    private RecyclerAdapter adapter;
    private boolean isShowDeleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webList.add("https://us.123rf.com/450wm/nadzin/nadzin2006/nadzin200600003/nadzin200600003.jpg?ver=6");
        webList.add("https://github.com/Zalezny");
        webList.add("https://avatars.githubusercontent.com/u/65240240?v=4");

        EditText item = findViewById(R.id.editText);
        Button add = findViewById(R.id.button);

        itemList = FileHelper.readData(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter = new RecyclerAdapter(itemList, MainActivity.this);
        recyclerView.setAdapter(adapter);

        // add on click 'enter'
        item.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                addAction(item);
                return true;
            }
            return false;
        });


        add.setOnClickListener(v -> addAction(item));

    }

    @Override
    public void onDeleteDialogResult(boolean isDeleted, int position) {
        ItemDataModel oldItem = itemList.get(position);
        ItemDataModel newItem = new ItemDataModel(oldItem.getId(), oldItem.getDate(), oldItem.getText(), true);
        itemList.remove(position);
        itemList.add(position, newItem);
        adapter.notifyItemChanged(position);
        FileHelper.writeData(itemList, getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int choiceItem = item.getItemId();

        if(choiceItem == R.id.sort_amount)
        {
            // sort of length
            Collections.sort(itemList, (o1, o2) -> o1.getText().length() - o2.getText().length());
            adapter.notifyDataSetChanged();
        }
        if (choiceItem == R.id.sort_date)
        {
            // sort of length
            Collections.sort(itemList, (o1, o2) -> {


                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.US);
                String date1String = o1.getDate();
                String date2String = o2.getDate();

                Date d1;
                Date d2;
                try {
                    d1 = format.parse(date1String);
                    d2 = format.parse(date2String);

                    if (d1 != null) {
                        return d1.compareTo(d2);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return -1;

            });
            adapter.notifyDataSetChanged();
        }
        if(choiceItem == R.id.show_hide_delete_item)
        {
            if(!isShowDeleted) {
                adapter.showDeleted(true);
                isShowDeleted = true;
            }
            else {
                adapter.showDeleted(false);
                isShowDeleted = false;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void addAction(EditText item) {
        if(item.getText().toString().isEmpty())
            return;
        String text;

        int id = itemList.size() + 1;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        String date = format.format(Calendar.getInstance().getTime());

        if(itemList.size() % 2 == 0) {
            text = item.getText().toString();

        }
        else {
            //random generator
            int index = new Random().nextInt(webList.size());
            text = webList.get(index);
        }

        item.setText("");
        itemList.add(new ItemDataModel(id, date, text, false));
        FileHelper.writeData(itemList, getApplicationContext());
        adapter.notifyItemInserted(itemList.size());
    }
}