package com.lpzoutreach.g12lpz.Utility.adapter;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.lpzoutreach.g12lpz.R;

public class utilAutoCompleteTextView {

    public static void setList(Context context, AutoCompleteTextView obj,  String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, list);
        obj.setAdapter(adapter);
    }

}
