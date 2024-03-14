package com.lpzoutreach.g12lpz.Utility.themes;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Menu;

import com.google.android.material.textfield.TextInputLayout;
import com.lpzoutreach.g12lpz.R;
import androidx.core.content.ContextCompat;

public class themes {

    public static String NORMAL_COLOR_MODE="normal";
    public static String ERROR_COLOR_MODE="error";
    public static String APPROVED_COLOR_MODE="approved";

    public static boolean isNightMode(Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }

    public static void menuIconTintColor(Context context, Menu menu, int position){
        /*
        Drawable drawable = menu.getItem(position).getIcon();
        drawable = DrawableCompat.wrap(drawable);
        if(isNightMode(context))
            DrawableCompat.setTint(drawable, ContextCompat.getColor(context,R.color.white));
        else
            DrawableCompat.setTint(drawable, ContextCompat.getColor(context,R.color.white));
        menu.getItem(position).setIcon(drawable);
        */

    }

    public static int item_default_Background_Color(Context context){
        if(isNightMode(context))
            return ContextCompat.getColor(context, R.color.black);
        else
            return ContextCompat.getColor(context, R.color.white);
    }

    public static int item_selector_Background_Color(Context context){
        if(isNightMode(context))
            return ContextCompat.getColor(context, R.color.dark_divider);
        else
            return ContextCompat.getColor(context, R.color.white_divider);
    }

    public static void outlineColor(Context context, TextInputLayout textInputLayout, String colorMode){

        if(colorMode.equals(ERROR_COLOR_MODE)){
            textInputLayout.setBoxStrokeColor(context.getResources().getColor(R.color.error_color));
        }else if(colorMode.equals(APPROVED_COLOR_MODE)){
            textInputLayout.setBoxStrokeColor(context.getResources().getColor(R.color.green_color));
        }else{
            if(themes.isNightMode(context)) textInputLayout.setBoxStrokeColor(context.getResources().getColor(R.color.night_button));
            else textInputLayout.setBoxStrokeColor(context.getResources().getColor(R.color.primary));
            textInputLayout.setHelperText("");
        }
    }

    public static void outlineColorDisabled(Context context, TextInputLayout textInputLayout){
            if(themes.isNightMode(context)) textInputLayout.setBoxStrokeColor(context.getResources().getColor(R.color.black));
            else textInputLayout.setBoxStrokeColor(context.getResources().getColor(R.color.white));
            textInputLayout.setHelperText("");

    }

    public static int getDatePickerStyle(Context context)
    {
        if(themes.isNightMode(context)) return AlertDialog.THEME_HOLO_DARK;
        else return AlertDialog.THEME_HOLO_LIGHT;
    }

    public static String get_css_profile_browse(Context context){

        if(isNightMode(context)){
            return "    <style>" +
                    "    *{font-family: Arial, Geneva, Helvetica, sans-serif; font-size:13px}" +
                    "    .text-default {color :#757575} " +
                    "    .text-primary { color: #168B8D;}" +
                    "    .text-secondary {color: #757575;}" +
                    "    .text-light-primary { color: #ffffff }" +
                    "    .font-italic { font-style: italic; } " +
                    "    .font-bold { font-weight: bold; } " +
                    "     body { width:100%; background-color: #0a1a29;margin: 0px; padding: 0px; height: 100%;} " +
                    "    .content{  padding: 0px 25px 25px 15px; } " +
                    "    .indent{margin-left:40px} " +
                    "    .j{text-align:justify;} " +
                    "    .p{text-align: justify;} " +
                    "    .headings{background-color: #D3D3D3;padding:8px} " +
                    "    .column { float: left; width: 50%; padding: 0px; }" +
                    "    .row:after {" + " content: \"\"; display: table; clear: both; }" +
                    "    .mb-0{ margin-bottom:0dp; }" +
                    "    .row{ padding-top:20px;margin-bottom:20px; }  " +
                    "    .mt-1 {margin-top:10dp;}" +
                    "    table{width: 100%;margin-left:30px}" +
                    "    .right-column{width: 70%;}" +
                    "    .left-column{width: 30%;}" +
                    "  </style>";

        }else{
            return "    <style>" +
                    "    *{font-family: Arial, Geneva, Helvetica, sans-serif; font-size:13px}" +
                    "    .text-default {color :#212121} " +
                    "    .text-primary { color: #006cb7;}" +
                    "    .text-secondary {color: #757575;}" +
                    "    .text-light-primary { color: #8b0000 }" +
                    "    .font-italic { font-style: italic; } " +
                    "    .font-bold { font-weight: bold; } " +
                    "     body { width:100%; background-color: ;margin: 0px; padding: 0px; height: 100%;} " +
                    "    .content{  padding: 0px 25px 25px 15px; } " +
                    "    .indent{margin-left:40px} " +
                    "    .j{text-align:justify;} " +
                    "    .p{text-align: justify;} " +
                    "    .headings{background-color: #D3D3D3;padding:8px} " +
                    "    .column { float: left; width: 50%; padding: 0px; }" +
                    "    .row:after {" + " content: \"\"; display: table; clear: both; }" +
                    "    .mb-0{ margin-bottom:0dp; }" +
                    "    .row{ padding-top:20px;margin-bottom:20px; }  " +
                    "    .mt-1 {margin-top:10dp;}" +
                    "  </style>";

        }



    }
    public static String get_css(Context context){

        if(isNightMode(context)){
             return "    <style>" +
                    "    *{font-family: Arial, Geneva, Helvetica, sans-serif; font-size:13px}" +
                    "    .text-default {color :#757575} " +
                    "    .text-primary { color: #168B8D;}" +
                    "    .text-secondary {color: #757575;}" +
                    "    .text-light-primary { color: #ffffff }" +
                    "    .font-italic { font-style: italic; } " +
                    "    .font-bold { font-weight: bold; } " +
                    "     body { width:100%; background-color: #0a1a29;margin: 0px; padding: 0px; height: 100%;} " +
                    "    .content{  padding: 0px 25px 25px 15px; } " +
                    "    .indent{margin-left:40px} " +
                    "    .j{text-align:justify;} " +
                    "    .p{text-align: justify;} " +
                    "    .headings{background-color: #D3D3D3;padding:8px} " +
                    "    .column { float: left; width: 50%; padding: 0px; }" +
                    "    .row:after {" + " content: \"\"; display: table; clear: both; }" +
                    "    .mb-0{ margin-bottom:0dp; }" +
                    "    .row{ padding-top:20px;margin-bottom:20px; }  " +
                    "    .mt-1 {margin-top:10dp;}" +
                     "    .indent-table{width: 100%;margin-left:30px}" +
                     "    .right-column{width: 70%;}" +
                     "    .left-column{width: 30%;}" +
                     "   img {border-radius: 50%; }" +
                    "  </style>";

        }else{
            return "    <style>" +
                    "    *{font-family: Arial, Geneva, Helvetica, sans-serif; font-size:13px}" +
                    "    .text-default {color :#212121} " +
                    "    .text-primary { color: #006cb7;}" +
                    "    .text-secondary {color: #757575;}" +
                    "    .text-light-primary { color: #8b0000 }" +
                    "    .font-italic { font-style: italic; } " +
                    "    .font-bold { font-weight: bold; } " +
                    "     body { width:100%; background-color: ;margin: 0px; padding: 0px; height: 100%;} " +
                    "    .content{  padding: 0px 25px 25px 15px; } " +
                    "    .indent{margin-left:40px} " +
                    "    .j{text-align:justify;} " +
                    "    .p{text-align: justify;} " +
                    "    .headings{background-color: #D3D3D3;padding:8px} " +
                    "    .column { float: left; width: 50%; padding: 0px; }" +
                    "    .row:after {" + " content: \"\"; display: table; clear: both; }" +
                    "    .mb-0{ margin-bottom:0dp; }" +
                    "    .row{ padding-top:20px;margin-bottom:20px; }  " +
                    "    .mt-1 {margin-top:10dp;}" +
                    "    table{width: 100%;margin-left:30px}" +
                    "    .right-column{width: 70%;}" +
                    "    .left-column{width: 30%;}" +
                    "   img {border-radius: 50%; }" +
                    "  </style>";

        }



    }

}
