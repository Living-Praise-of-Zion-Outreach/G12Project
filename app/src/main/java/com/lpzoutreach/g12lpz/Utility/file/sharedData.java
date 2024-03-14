package com.lpzoutreach.g12lpz.Utility.file;

import android.content.SharedPreferences;
import android.content.Context;
public class sharedData {

public static final String DBNAME = "g12-lpz_4-7.db";

public static final String DBPATH = "/data/data/com.lpzoutreach.g12lpz/databases/";
public static final String DBFILEPATH = "/data/user/0/com.lpzoutreach.g12lpz/files/databases/";
public static final String SHARED_PREFS = "sharedPres";
public static final String USER_ID = "userID";
public static final String PROFILE_PHOTO = "PROFILE_PHOTO";
public static final String USERNAME = "USERNAME";
public static final String SYSTEM_ROLE = "SYSTEM_ROLE";
public static final String SYNC_PROFILE_DATA = "synchronize";

public static final int UPDATE_APP_ID = 3;


    public static void set_system_role(Context context, String role)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SYSTEM_ROLE,role );
        editor.apply();
    }

    public static String get_system_role(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SYSTEM_ROLE,"");
    }

    public static void set_username(Context context, String username)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME,username );
        editor.apply();
    }

    public static String get_username(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME,"");
    }

    public static void set_email_status(Context context, String status)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAILSTATUS",status );
        editor.apply();
    }

    public static String get_email_status(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("EMAILSTATUS","");
    }

    public static void set_password(Context context, String password)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PASSWORD",password );
        editor.apply();
    }

    public static String get_password(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("PASSWORD","");
    }


    public static void set_up_mode(Context context, String mode)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SETUPMODE",mode );
        editor.apply();
    }

    public static String get_setup_mode(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("SETUPMODE","");
    }

    public static void set_profile_photo(Context context, String profile_photo)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROFILE_PHOTO, profile_photo );
        editor.apply();
    }

    public static String get_profile_photo(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PROFILE_PHOTO,null);
    }

    public static void set_access_level_name(Context context, String accessName)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCESSNAME", accessName );
        editor.apply();
    }

    public static String get_access_level_name(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("ACCESSNAME",null);
    }

    public static void set_access_level_id(Context context, int accessID)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("ACCESSID", accessID );
        editor.apply();
    }

    public static int get_access_level_id(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("ACCESSID",0);
    }

    public static void set_userID(Context context, int userID)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID, userID);
        editor.apply();
    }

    public static int get_userID(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(USER_ID,0);
    }

    public static void set_email(Context context, String email)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", email);
        editor.apply();
    }

    public static String get_email(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("EMAIL","");
    }

    public static void set_profile_privacy(Context context, String privacy)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PROFILEPRIVACY", privacy);
        editor.apply();
    }

    public static String get_profile_privacy(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("PROFILEPRIVACY","");
    }


    public static void set_selected_churchID_set_up_mode(Context context, String churchID)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SELECTEDCHURCHID", churchID);
        editor.apply();
    }

    public static String get_selected_churchID_set_up_mode(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("SELECTEDCHURCHID","");
    }


    public static void set_Profile_Sync_Data(Context context, boolean values)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SYNC_PROFILE_DATA, values);
        editor.apply();
    }
    public static boolean is_Profile_Sync(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(SYNC_PROFILE_DATA,false);
    }


    public static void set_church_name(Context context, String church)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CHURCHNAME", church);
        editor.apply();
    }

    public static String get_church_name(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("CHURCHNAME","");
    }

    public static void set_network_leader_name(Context context, String networkleader)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NETWORKLEADER", networkleader);
        editor.apply();
    }

    public static String get_network_leader_name(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("NETWORKLEADER","");
    }

    public static void set_invited_by(Context context, String invitedBy)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("INVITEDBY", invitedBy);
        editor.apply();
    }

    public static String get_set_invited_by(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("INVITEDBY","");
    }

    public static void set_bible_book_no(Context context, int book_no)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BIBLE_BOOK_NO",book_no );
        editor.apply();
    }
    public static int get_bible_book_no(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("BIBLE_BOOK_NO",10);
    }
    public static void set_bible_chapter(Context context, int chapter)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BIBLE_CHAPTER",chapter );
        editor.apply();
    }
    public static int get_bible_chapter(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("BIBLE_CHAPTER",1);
    }

    public static void set_bible_verses(Context context, int verse_no)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BIBLE_VERSES",verse_no );
        editor.apply();
    }
    public static int get_bible_verses(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("BIBLE_VERSES",1);
    }
    public static void set_bible_version(Context context, String version)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("BIBLE_VERSION",version );
        editor.apply();
    }
    public static String get_bible_version(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("BIBLE_VERSION","NIV");
    }



    public static void set_bible_current_row_index_book(Context context, int row_index)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BIBLE_CURRENT_ROW_INDEX_BOOK",row_index );
        editor.apply();
    }
    public static int get_bible_current_row_index_book(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("BIBLE_CURRENT_ROW_INDEX_BOOK",0);
    }

    public static void set_bible_current_language(Context context, String language)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("BIBLE_CURRENT_LANGUAGE",language );
        editor.apply();
    }
    public static String get_bible_current_language(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("BIBLE_CURRENT_LANGUAGE","English");
    }

    public static void set_lifeclass_current_language(Context context, String language)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LIFECLASS_CURRENT_LANGUAGE",language );
        editor.apply();
    }
    public static String get_lifeclass_current_language(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString("LIFECLASS_CURRENT_LANGUAGE","English");
    }

}
