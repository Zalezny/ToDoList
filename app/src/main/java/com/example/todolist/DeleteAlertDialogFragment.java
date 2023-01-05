package com.example.todolist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;



public class DeleteAlertDialogFragment extends DialogFragment {



    onDeleteDialogListener deleteDialogListener;

    @Override
    public void onAttach(@NonNull Context context) {
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