package com.lpzoutreach.g12lpz.Utility.toast;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import com.lpzoutreach.g12lpz.R;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class utilToast {

    public static void show( Context context, String text){

        String[] data = text.split("\\.");
        if(data[data.length-1].contains("TimeoutError")){
            showTimeOutError(context);
        }else{
            Toast.makeText(context,text,Toast.LENGTH_LONG).show();
        }

    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    public static void showTimeOutError(Context context){
        Activity activity = (Activity) context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, activity.findViewById(R.id.ll_root_toast));
        Toast toast = new Toast(activity.getApplicationContext());
        ImageView ivIcon = layout.findViewById(R.id.iv_icon_toast);
        TextView tvContent = layout.findViewById(R.id.tv_content_toast);
        ivIcon.setVisibility(View.VISIBLE);
        ivIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_icon_no_internet));
        tvContent.setText("Time Out Error! There was a problem with your internet connection.");
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setView(layout);
        toast.show();
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    public static void showErrorNoInternet(Context context){
        Activity activity = (Activity) context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, activity.findViewById(R.id.ll_root_toast));
        Toast toast = new Toast(activity.getApplicationContext());
        ImageView ivIcon = layout.findViewById(R.id.iv_icon_toast);
        TextView tvContent = layout.findViewById(R.id.tv_content_toast);
        ivIcon.setVisibility(View.VISIBLE);
        ivIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_icon_no_internet));
        tvContent.setText("No Internet Connection.");
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setView(layout);
        toast.show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static void showSuccess(Context context, String content){
        Activity activity = (Activity) context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, activity.findViewById(R.id.ll_root_toast));
        Toast toast = new Toast(activity.getApplicationContext());
        ImageView ivIcon = layout.findViewById(R.id.iv_icon_toast);
        TextView tvContent = layout.findViewById(R.id.tv_content_toast);
        ivIcon.setVisibility(View.VISIBLE);
        ivIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_icon_check));
        tvContent.setText(content);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setView(layout);
        toast.show();
    }

    public static void showWarning(Context context, String text){
        Activity activity = (Activity) context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup) activity.findViewById(R.id.ll_root_toast));
        Toast toast = new Toast(activity.getApplicationContext());

        ImageView ivIcon = layout.findViewById(R.id.iv_icon_toast);
        TextView tvContent = layout.findViewById(R.id.tv_content_toast);
        ivIcon.setVisibility(View.VISIBLE);
        ivIcon.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_icon_warning));
        tvContent.setText(text);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setView(layout);
        toast.show();
    }


}
