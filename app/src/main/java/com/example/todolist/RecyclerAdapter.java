package com.example.todolist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private final ArrayList<String> itemList;
    private final Context ctx;

    public RecyclerAdapter(ArrayList<String> itemList, Context ctx) {
        this.itemList = itemList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String currentItem = itemList.get(position);
        if(!isUrl(currentItem)) {
            holder.textItem.setVisibility(View.VISIBLE);
            holder.webView.setVisibility(View.GONE);
            holder.textItem.setText(currentItem);
        }
        else {
            holder.textItem.setVisibility(View.GONE);
            holder.webView.setVisibility(View.VISIBLE);

            holder.webView.setWebViewClient(new WebViewClient());
            holder.webView.setInitialScale(90);
            holder.webView.loadUrl(currentItem);
        }


        holder.deleteItem.setOnClickListener(v -> {

            DeleteAlertDialogFragment alertDialogFr = new DeleteAlertDialogFragment();

            Bundle args = new Bundle();
            args.putInt(ctx.getString(R.string.ARGS_KEY_ALERT_DIALOG_FRAGMENT), position);

            alertDialogFr.setArguments(args);
            alertDialogFr.show(((AppCompatActivity)ctx).getSupportFragmentManager(),
                    ctx.getString(R.string.DELETE_ALERT_DIALOG_FRAGMENT_TAG));
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView textItem;
        private final ImageView deleteItem;
        private final WebView webView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textItem = itemView.findViewById(R.id.textItem);
            deleteItem = itemView.findViewById(R.id.deleteItem);
            webView = itemView.findViewById(R.id.webView);

        }
    }

    private boolean isUrl(String url)
    {

        try {
            new URL(url).toURI();
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }

}
