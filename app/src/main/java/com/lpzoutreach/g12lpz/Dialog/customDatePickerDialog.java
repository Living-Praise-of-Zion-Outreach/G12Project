package com.lpzoutreach.g12lpz.Dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.Context;

import com.lpzoutreach.g12lpz.Utility.themes.themes;

import java.util.Calendar;

public class customDatePickerDialog {

    private DatePickerDialog dialog;
    private EditText editext;
    private Context context;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    public customDatePickerDialog(Context context, EditText editext){
        this.context=context;
        this.editext = editext;
        initialize();
    }


    private void initialize()
    {
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                editext.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_DEVICE_DEFAULT_LIGHT;

        dialog = new DatePickerDialog(context, themes.getDatePickerStyle(context),dateSetListener,year,month,day);
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        editext.setText(getTodaysDate());
    }

    public void set_date(String date){
        String[] value = date.split(" ");
        int month = getMonthNumber(value[0]);
        if(month>0) month = month - 1;
        Calendar cal = Calendar.getInstance();
        dialog = new DatePickerDialog(context, themes.getDatePickerStyle(context),dateSetListener, Integer.valueOf(value[2]),  month, Integer.valueOf(value[1]));
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        dialog.show();
    }

    public void update_Date(int year, int month, int day){
        month = month + 1;
        editext.setText(makeDateString(day,month,year));
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        return makeDateString(day,month,year);
    }

    private String makeDateString(int day, int month, int year){
        return getMonthFormat(month) + " " + day + " " + year;
    }

    public static int getMonthNumber(String month){
        int value;
        switch(month) {
            case "Jan":
                value = 1;
                break;
            case "Feb":
                value = 2;
                break;
            case "Mar":
                value = 3;
                break;
            case "Apr":
                value = 4;
                break;
            case "May":
                value = 5;
                break;
            case "Jun":
                value = 6;
                break;
            case "Jul":
                value = 7;
                break;
            case "Aug":
                value = 8;
                break;
            case "Sep":
                value = 9;
                break;
            case "Oct":
                value = 10;
                break;
            case "Nov":
                value = 11;
                break;
            case "Dec":
                value = 12;
                break;
            default:
                value = 1;
                break;
        }
        return value;
    }

    public static String getMonthFormat(int month)
    {
        String value;
        switch(month) {
            case 1:
            value = "Jan";
            break;
            case 2:
            value = "Feb";
            break;
            case 3:
            value = "Mar";
            break;
            case 4:
            value = "Apr";
            break;
            case 5:
            value = "May";
            break;
            case 6:
            value = "Jun";
            break;
            case 7:
            value = "Jul";
            break;
            case 8:
            value = "Aug";
            break;
            case 9:
            value = "Sep";
            break;
            case 10:
            value = "Oct";
            break;
            case 11:
            value = "Nov";
            break;
            case 12:
            value = "Dec";
            break;
            default:
                value = "Jan";
                break;
        }
                return  value;
    }

    public void show(){

        Calendar cal = Calendar.getInstance();
        dialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        dialog.show();
    }

}
