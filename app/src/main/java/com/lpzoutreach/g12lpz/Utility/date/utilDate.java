package com.lpzoutreach.g12lpz.Utility.date;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.lpzoutreach.g12lpz.Dialog.customDatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class utilDate {

    private customDatePickerDialog cDatePicker;

    public static String getCurrentDateTime(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd h:m:s", Locale.getDefault());
        return df.format(c);
    }

    public static String getCurrentDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault());
        return df.format(c);
    }

    public static String getCurrentDate_ServerFormat(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return df.format(c);
    }

    public static String getCurrentDate_DatePickerFormat(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault());
        String newValue = df.format(c);
        String[] date  = newValue.split("-");
        return (date[1] + " " + date[2] + " " + date[0]);
    }

    public static String getFromServer_DatePickerFormat(String data)  {
        String[] month = {"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        String[] rawDate = data.split(" ");

        String[] date  = rawDate[0].split("-");

        if(date.length>1){
            //2000-01-01
            return (month[Integer.parseInt(date[1])] + " " + date[2] + " " + date[0]);
        }else {
            return data;
        }


    }

    String date_format;

    public void set_date_picker(EditText object, Context frm){

        if(!object.equals("")){
            String[] date  = getCurrentDate().split("-");
            object.setText(date[1] + " " + date[2] + " " + date[0]);
        }

        object.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String[] temp_date_founded = object.getText().toString().split(" ");
                if(temp_date_founded.length>2){
                    date_format = temp_date_founded[2] + "-" + customDatePickerDialog.getMonthNumber(temp_date_founded[0]) + "-" + temp_date_founded[1];
                }
            }
        });
        cDatePicker = new customDatePickerDialog(frm,object);
        object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cDatePicker.set_date(object.getText().toString());
            }}
        );
    }

}
