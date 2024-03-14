package com.lpzoutreach.g12lpz.Utility.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;
import android.content.Context;
import com.lpzoutreach.g12lpz.R;

public class ProgressDialogBar {
    Context context;
    Dialog dialog;

    public ProgressDialogBar(Context context){
        this.context = context;
    }

    public void show(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView titleTextView = dialog.findViewById(R.id.dialog_loading_title);
        titleTextView.setText("Loading...");
        dialog.create();
        dialog.show();
    }

    public void show(String title){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView titleTextView = dialog.findViewById(R.id.dialog_loading_title);

        titleTextView.setText(title);

        dialog.create();
        dialog.show();
    }

    public void hide(){
        dialog.dismiss();
    }

}
