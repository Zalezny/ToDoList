package com.example.todolist;

public class ItemDataModel {
    int id;
    String date;
    String text;
    boolean isDeleted;

    public ItemDataModel (int id, String date, String text, boolean isDeleted) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.isDeleted = isDeleted;
    }
}
