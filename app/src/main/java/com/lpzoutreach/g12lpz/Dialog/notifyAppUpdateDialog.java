package com.lpzoutreach.g12lpz.Dialog;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.NotifyAppUpdateModel;
import com.lpzoutreach.g12lpz.R;

public class notifyAppUpdateDialog extends AppCompatDialogFragment {
    private Context context;
    private NotifyAppUpdateModel data;
    private View view;
    private elUtil eventListener;

    public notifyAppUpdateDialog(Context context, NotifyAppUpdateModel data,elUtil eventListener){
        this.context = context;
        this.data = data;
        this.eventListener = eventListener;
    }

    @SuppressLint("SetTextI18n")
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_notify_app_update,null);

        TextView tv_version_app_update = view.findViewById(R.id.tv_version_app_update);
        TextView tv_features_app_update = view.findViewById(R.id.tv_features_app_update);

        tv_version_app_update.setText(data.getAppTitle());
        tv_features_app_update.setText(data.getAppDescription());


        builder.setView(view)
                .setTitle("New Update Available")
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                            if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                                eventListener.myCallBack("update-app",data);
                            }
                        }
                    }
                });


        return builder.create();

    }





}
