package com.lpzoutreach.g12lpz.Utility.themes;
import android.text.Editable;
import android.view.View;
import android.text.TextWatcher;

import com.lpzoutreach.g12lpz.R;

public class outlineColorTextWatcher implements TextWatcher {
    private View view;

    private outlineColorTextWatcher(View view){
        this.view = view;
    }

    public outlineColorTextWatcher() {
        super();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
