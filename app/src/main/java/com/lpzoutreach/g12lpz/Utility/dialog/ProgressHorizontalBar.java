package com.lpzoutreach.g12lpz.Utility.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.themes.themes;

public class ProgressHorizontalBar {
    Context context;
    Dialog dialog;
    TextView title;
    ProgressBar pb;

    public ProgressHorizontalBar(Context context){
        this.context = context;
    }

    public void show(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_bar_loading);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        title = dialog.findViewById(R.id.title_bar_loading);
        pb = dialog.findViewById(R.id.pb_bar_loading);

        if(themes.isNightMode(context)){
            pb.setBackground(context.getResources().getDrawable(R.color._fifth));
            pb.setIndeterminateTintList(ColorStateList.valueOf(context.getResources().getColor(R.color._seven)));
        }


        title.setText("Downloading...");
        dialog.create();
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void setProgress(String title, double value, double total_value){

        double tot =  (value/total_value) * 100;

        this.title.setText("Downloaded File: " + title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.pb.setProgress((int) tot,true);
        }else{
            this.pb.setProgress((int) tot);
        }
    }

    public void hide(){
        dialog.dismiss();
    }


}
