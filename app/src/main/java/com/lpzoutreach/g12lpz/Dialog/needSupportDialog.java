package com.lpzoutreach.g12lpz.Dialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.lpzoutreach.g12lpz.BuildConfig;
import com.lpzoutreach.g12lpz.R;

public class needSupportDialog extends AppCompatDialogFragment {
    private Context context;
    private View view;

    private RadioGroup rg;

    public needSupportDialog(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_need_support, null);

        rg = view.findViewById(R.id.rg_message_type_need_support);

        builder.setView(view)
         .setPositiveButton(context.getResources().getString(R.string.ok_button_dialog), (dialog, which) -> set_action())
         .setCancelable(true);
        return builder.create();

    }

    private void set_action(){
        String messageType = ((RadioButton) view.findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
        selectorIntent.setData(Uri.parse("mailto:"));

        String versionName = BuildConfig.VERSION_NAME;

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sylvster129@mail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "G12 Lpz System v" + versionName+ " - " + messageType);

        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;

        String pre_body = "------------------------\n* Please do not delete this important device information\n------------------------\n";
        String post_body = "\n------------------------\n\n";

        String body = pre_body + "Brand: " + Build.BRAND + "\n" + "Model: " + Build.MODEL + "\n" + "Android SDK: " + sdkVersion + " (" + release +")"  + post_body;
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.setSelector( selectorIntent );

        context.startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }

}
