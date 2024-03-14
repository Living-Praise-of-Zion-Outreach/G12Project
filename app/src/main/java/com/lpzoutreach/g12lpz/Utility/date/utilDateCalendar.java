package com.lpzoutreach.g12lpz.Utility.date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.lpzoutreach.g12lpz.Dialog.customDatePickerDialog;
import com.lpzoutreach.g12lpz.R;

import java.util.Calendar;

public class utilDateCalendar {

    static DatePickerDialog datePickerDialog;

    public static void set(Context context, EditText et){
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(context, R.style.CalendarDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                        et.setText(customDatePickerDialog.getMonthFormat(month+1)  + " " + dayofMonth + " " + year);
                    }
                },year,month,day);
                // set maximum date to be selected as today
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }


        });



    }

}
