package com.example.todolist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;



public class DeleteAlertDialogFragment extends DialogFragment {



    onDeleteDialogListener deleteDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            deleteDialogListener = (onDeleteDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement onDeleteDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int pos = args != null ? args.getInt(getString(R.string.ARGS_KEY_ALERT_DIALOG_FRAGMENT), 0) : 0;

        return new AlertDialog.Builder(requireContext())
            .setTitle("Delete")
            .setMessage("Do you want to delete this item from the list?")
            .setCancelable(false)
            .setNegativeButton("No", (dialog, which) -> dialog.cancel())
            .setPositiveButton("Yes", (dialog, which) -> {
                deleteDialogListener.onDeleteDialogResult(true, pos);
                dialog.dismiss();
            })
            .create();
    }
}