package com.lpzoutreach.g12lpz.Dialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lpzoutreach.g12lpz.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;


public class utilDialog extends AppCompatDialogFragment {
    private View view;
    private Context context;
    private String title, content;
    private String response_name;
    static boolean response;
    private TextView tvContent;
    private elUtil eventHandler;

    public utilDialog(Context context, String content, String title, elUtil eventHandler, String response_name){
        this.title = title;
        this.context = context;
        this.content = content;
        this.eventHandler = eventHandler;
        this.response_name=response_name;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_confirmation_default,null);

        tvContent = view.findViewById(R.id.tv_dialog_confirmation_default);
        tvContent.setText(content);

        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.yes_button_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventHandler.myCallBack(response_name,null);
                    }
                })
                .setNegativeButton(context.getResources().getString(R.string.no_button_dialog), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        return builder.create();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }
}
