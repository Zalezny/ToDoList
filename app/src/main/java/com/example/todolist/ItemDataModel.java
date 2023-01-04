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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
