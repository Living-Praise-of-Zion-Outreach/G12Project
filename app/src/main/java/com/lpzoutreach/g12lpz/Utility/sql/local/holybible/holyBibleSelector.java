package com.lpzoutreach.g12lpz.Utility.sql.local.holybible;
import android.content.Context;

import com.lpzoutreach.g12lpz.Utility.file.sharedData;

public class holyBibleSelector {
    public static String get_bible_version(Context context){
        String version="";
        switch (sharedData.get_bible_version(context)){
            case "NIV":
                version = openHelperHolyBible_Default.NIV;
                break;
            case "RCPV":
                version = openHelperHolyBible_Default.RCPV;
                break;
            case "ASND":
                version = openHelperHolyBible_Default.ASND;
                break;
            default:
                version = sharedData.get_bible_version(context) + ".SQLite3";
                break;
        }
        return version;
    }

    public static String get_title_action_bar(Context context){
        openHelperHolyBible_Default helper = new openHelperHolyBible_Default(context, holyBibleSelector.get_bible_version(context));
        if(sharedData.get_bible_chapter(context)==0){
            return "Introduction";
        }else{
            return helper.get_book_name_by_string() + " " + sharedData.get_bible_chapter(context) + " (" + sharedData.get_bible_version(context) + ")";
        }
   }

   public static String get_long_name_bible_versions(Context context){
       openHelperHolyBible_Default helper = new openHelperHolyBible_Default(context, holyBibleSelector.get_bible_version(context));
       return helper.get_description();
   }

}
