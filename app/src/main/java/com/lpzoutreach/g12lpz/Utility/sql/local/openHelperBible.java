package com.lpzoutreach.g12lpz.Utility.sql.local;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.Models.MnemonicVerseModel;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.util.ArrayList;
import java.util.List;

public class openHelperBible extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private SQLiteDatabase database;
    public openHelperBible(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
        assert context != null;
        utilDatabase.checkDatabase(context,DBNAME);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {super.onOpen(db);}
    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    private void openDatabase(){
        String dbPath = DBPATH + DBNAME;
        if(database !=null && database.isOpen()){
            return;
        }
        database = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    private void closeDatabase() {
        if(database!=null){
            database.close();
        }
    }

    public void delete(){
        openDatabase();
        database.delete("bible_mnemonic_verses","",null );
        closeDatabase();
    }

    public void insert(List<MnemonicVerseModel> data){
        openDatabase();
        for(int i=0;i<data.size();i++){
            ContentValues cv = new ContentValues();
            cv.put("minemonicVerseID",data.get(i).getMinemonicVerseID());
            cv.put("groupVerseID",data.get(i).getGroupVerseID());
            cv.put("verseTitle",data.get(i).getVerseTitle());
            cv.put("verseReference",data.get(i).getVerseReference());
            cv.put("verse",data.get(i).getVerse());
            cv.put("tag",data.get(i).getTag());
            cv.put("bibleVersion",data.get(i).getBibleVersion());
            cv.put("bibleVersionText",data.get(i).getBibleVersionText());
            cv.put("language",data.get(i).getLanguage());
            cv.put("dtCreated",data.get(i).getDtCreated());
            cv.put("publishedBy",data.get(i).getPublishedBy());
            database.insert("bible_mnemonic_verses",null,cv);
        }
        closeDatabase();
    }
    public void insert_holy_bible(HolyBibleModel data){
        openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("holyBibleID",data.getHolyBibleID());
            cv.put("shortBibleName",data.getShortBibleName());
            cv.put("longBibleName",data.getLongBibleName());
            cv.put("description",data.getDescription());
            cv.put("copyright",data.getCopyright());
            cv.put("language",data.getLanguage());
            cv.put("bibleType","asset");
            cv.put("androidPath",data.getAndroidPath());
            database.insert("holy_bible",null,cv);
        closeDatabase();
    }

    public List<MnemonicVerseModel> get_all(String searchPerson, String version, String language, elUtil eventListenerUtil){
        openDatabase();
        List<MnemonicVerseModel> data = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM bible_mnemonic_verses WHERE bibleVersion LIKE '"+ version+"%' and language LIKE '"+language+"%' AND ( tag LIKE '%"+searchPerson+"%' or verse LIKE '%"+searchPerson+"%' ) LIMIT 0,25",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            MnemonicVerseModel row = new MnemonicVerseModel();
            row.setMinemonicVerseID(cursor.getInt(0)); //minemonicVerseID
            row.setGroupVerseID(cursor.getInt(1));  //groupVerseID
            row.setVerseTitle(cursor.getString(2));  //verseTitle
            row.setVerseReference(cursor.getString(3));  //verseReference
            row.setVerse(cursor.getString(4));  //verse
            row.setTag(cursor.getString(5));  //tag
            row.setBibleVersion(cursor.getString(6));  //bibleVersion
            row.setBibleVersionText(cursor.getString(7));  //bibleVersionText
            row.setLanguage(cursor.getString(8));  //language
            row.setDtCreated(cursor.getString(9));  //dtCreated
            row.setPublishedBy(cursor.getString(10)); //publishedBy
            row.setEventListenerUtil(eventListenerUtil);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public void insert_bible_history(HolyBibleModel data){
        openDatabase();
        int userID = sharedData.get_userID(context);
        ContentValues cv = new ContentValues();
        cv.put("userID",userID);
        cv.put("book_no",data.getBook_number());
        cv.put("book_name",data.getLong_name());
        cv.put("chapter",data.getChapter());
        cv.put("verse",data.getVerse());
        cv.put("version",data.getBookVersion());
        cv.put("dtCreated", utilDate.getCurrentDateTime());
        database.insert("holy_bible_history",null,cv);
        closeDatabase();
    }
    public List<HolyBibleModel> get_bible_history_all(elUtil eventListenerUtil, String actionEvent){
        openDatabase();
        List<HolyBibleModel> data = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM holy_bible_history WHERE userID =" + sharedData.get_userID(context) + " ORDER BY bibleHistoryID DESC",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            HolyBibleModel row = new HolyBibleModel();
            row.setBook_number(cursor.getInt(2));
            row.setLong_name(cursor.getString(3));
            row.setChapter(cursor.getInt(4));
            row.setVerse(cursor.getInt(5));
            row.setBookVersion(cursor.getString(6));
            row.setEventListenerUtil(eventListenerUtil);
            row.setActionEvent(actionEvent);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    @SuppressLint("Recycle")
    public void delete_bible_history(){
        openDatabase();
        database.delete("holy_bible_history","userID="+sharedData.get_userID(context),null );
        closeDatabase();
    }

    public boolean check_holy_bible(int id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM holy_bible WHERE holyBibleID="+id,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            closeDatabase();
            cursor.close();
            return true;
        }else{
            closeDatabase();
            cursor.close();
            return false;
        }
    }

    public ArrayList<HolyBibleModel> get_all_bible_versions_recently_used(elUtil eventListenerUtil, String actionEvent){
        openDatabase();
        ArrayList<HolyBibleModel> data = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM holy_bible_version_history ORDER BY holyBibleVersionHistoryID DESC",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            HolyBibleModel row = new HolyBibleModel();
            row.setHolyBibleID(cursor.getInt(1)); //holyBibleID
            row.setShortBibleName(cursor.getString(2)); //shortBibleName

            if(cursor.getString(2).equals(sharedData.get_bible_version(context))){
                row.setSelected(true);
            }else{
                row.setSelected(false);
            }

            row.setDownloaded(true);

            row.setLongBibleName(cursor.getString(3));//longBibleName
            row.setLanguage(cursor.getString(4));//language
            row.setEventListenerUtil(eventListenerUtil);
            row.setActionEvent(actionEvent);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public ArrayList<HolyBibleModel> get_all_bible_versions(elUtil eventListenerUtil, String actionEvent){
        openDatabase();
        ArrayList<HolyBibleModel> data = new ArrayList<>();
        String language = sharedData.get_bible_current_language(context);
        Cursor cursor = database.rawQuery("SELECT * FROM holy_bible WHERE language LIKE '"+language+"' ORDER BY language ASC",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            HolyBibleModel row = new HolyBibleModel();
            row.setHolyBibleID(cursor.getInt(0)); //holyBibleID
            row.setShortBibleName(cursor.getString(1)); //shortBibleName

            if(cursor.getString(1).equals(sharedData.get_bible_version(context))){
                row.setSelected(true);
            }else{
                row.setSelected(false);
            }
            if(cursor.getString(6).equals("asset") || !cursor.getString(6).equals("")){
                row.setDownloaded(true);
            }else{
                row.setDownloaded(false);
            }

            row.setLongBibleName(cursor.getString(2));//longBibleName
            row.setDescription(cursor.getString(3));//description
            row.setCopyright(cursor.getString(4));//copyright
            row.setAndroidPath(cursor.getString(5));//androidPath
            row.setBibleType(cursor.getString(6));//bibleType
            row.setLanguage(cursor.getString(7));//language
            row.setEventListenerUtil(eventListenerUtil);
            row.setActionEvent(actionEvent);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public ArrayList<String> get_all_bible_language(){
        openDatabase();
        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT distinct language FROM holy_bible ORDER BY language ",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            data.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public String get_all_bible_versions_by_string(){
        openDatabase();
        StringBuilder data= new StringBuilder();
        Cursor cursor = database.rawQuery("SELECT * FROM holy_bible ORDER BY longBibleName ASC",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            //row.setLongBibleName(cursor.getString(2));//longBibleName
            if(data.toString().equals(""))
                data = new StringBuilder(cursor.getString(2));
            else
                data.append("~").append(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data.toString();
    }

    public String get_short_name_versions_by_long_name(String value){
        openDatabase();
        StringBuilder data= new StringBuilder();
        Cursor cursor = database.rawQuery("SELECT * FROM holy_bible WHERE longBibleName LIKE '"+value+"'",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
                data.append(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data.toString();
    }


}
