package com.lpzoutreach.g12lpz.Utility.validation;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.themes.themes;

public class utilValidation {

    public static void set_remove_error_field(Context context, EditText object,TextInputLayout binder){
        object.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                themes.outlineColor(context.getApplicationContext(),binder,themes.NORMAL_COLOR_MODE);
            }
        });
    }

    public static void set_error_field(Context context , EditText object, TextInputLayout binder, String response_text){
        object.requestFocus();
        binder.setHelperText(response_text);
        binder.setBoxStrokeColor(context.getResources().getColor(R.color.error_color));
    }

}
