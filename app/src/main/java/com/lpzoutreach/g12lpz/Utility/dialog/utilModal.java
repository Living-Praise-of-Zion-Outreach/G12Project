package com.lpzoutreach.g12lpz.Utility.dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
public class utilModal {
    public static void notify(Context context, String title, String content){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
        builder.setTitle(title)
                .setCancelable(false)
                .setMessage(content)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }
}
