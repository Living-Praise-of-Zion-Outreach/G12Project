package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.EbookModel;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.util.ArrayList;
import java.util.List;

public class openHelperEBook extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private EvangelismModel data;
    private SQLiteDatabase database;
    private elUtil eventListenerUtil = elUtil.getInstance();
    public openHelperEBook(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
        utilDatabase.checkDatabase(context,DBNAME);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {super.onOpen(db);}
    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    public void openDatabase(){
        String dbPath = DBPATH + DBNAME;
        if(database !=null && database.isOpen()){
            return;
        }
        database = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase() {
        if(database!=null){
            database.close();
        }
    }

    public boolean insert(EbookModel data){
        int userID = sharedData.get_userID(context);
        openDatabase();
            ContentValues cv = new ContentValues();
            cv.put("ebookID", data.getEbookID());
            cv.put("userID", userID);
            cv.put("bookAuthorLogo",data.getAuthorLogo());
            cv.put("bookAuthor",data.getBookAuthor());
            cv.put("bookCoverUrl",data.getBookCoverUrl());
            cv.put("bookName",data.getBookName());
            cv.put("bookTagLine",data.getBookTagLine());
            cv.put("bookShortDescription",data.getBookShortDescription());
            cv.put("bookCategory",data.getBookCategory());
            cv.put("bookTag",data.getBookTag());
            cv.put("bookGenre",data.getBookGenre());
            cv.put("bookUrlPath","");
            cv.put("bookYearReleased","");
            cv.put("language",data.getLanguage());
            cv.put("androidPath",data.getAndroidPhotoPath());
            cv.put("info_header",data.getInfo_header());
            cv.put("info_body",data.getInfo_body());
            cv.put("info_url",data.getInfo_url());
            cv.put("info_url_type",data.getInfo_url_type());
            cv.put("add_to_favorite",data.getAdd_to_favorite());
            cv.put("createdBy",data.getCreatedBy());
            cv.put("dtCreated",data.getDtCreated());
            database.insert("ebook",null,cv);
        closeDatabase();
        return true;
    }

    public void delete(String id){
        openDatabase();
        database.delete("ebook","ebookID="+id+" AND userID="+sharedData.get_userID(context),null );
        closeDatabase();
    }

    public EbookModel get(String id){
            openDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM ebook where ebookID="+id+" AND userID="+sharedData.get_userID(context)+" ",null);
            cursor.moveToFirst();
            EbookModel data = new EbookModel();
            while(!cursor.isAfterLast()){
                data.setEbookID(cursor.getInt(0)); //ebookID
                //userID
                data.setAuthorLogo(cursor.getString(2));  //bookAuthorLogo
                data.setBookAuthor(cursor.getString(3)); //bookAuthor
                data.setBookCoverUrl(cursor.getString(4));  //bookCoverUrl
                data.setBookName(cursor.getString(5));  //bookName
                data.setBookTagLine(cursor.getString(6));  //bookTagLine
                data.setBookShortDescription(cursor.getString(7));  //bookShortDescription
                data.setBookCategory(cursor.getString(8));  //bookCategory
                data.setBookTag(cursor.getString(9)); //bookTag
                data.setBookGenre(cursor.getString(10));  //bookGenre
                data.setBookUrlPath(cursor.getString(11));  //bookUrlPath
                  //bookYearReleased
                data.setLanguage(cursor.getString(13)); //language
                data.setAndroidPhotoPath(cursor.getString(14)); //androidPath
                data.setInfo_header(cursor.getString(15)); //info_header
                data.setInfo_body(cursor.getString(16)); //info_body
                data.setInfo_url(cursor.getString(17));  //info_url
                data.setInfo_url_type(cursor.getString(18));  //info_url_type
                data.setAdd_to_favorite(cursor.getInt(19)); //add_to_favorite
                data.setCreatedBy(cursor.getInt(20)); //createdBy
                data.setDtCreated(cursor.getString(21));  //dtCreated
                cursor.moveToNext();
            }
            cursor.close();
            closeDatabase();
            return data;
    }

    public List<EbookModel> get_list_my_library(elUtil eventListenerUtil ,String actionEvent){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ebook where userID="+sharedData.get_userID(context)+" AND androidPath!=''",null);
        cursor.moveToFirst();
        List<EbookModel> data = new ArrayList<>();
        while(!cursor.isAfterLast()){
            EbookModel row = new EbookModel();
            row.setEbookID(cursor.getInt(0)); //ebookID
            //userID
            row.setAuthorLogo(cursor.getString(2));  //bookAuthorLogo
            row.setBookAuthor(cursor.getString(3)); //bookAuthor
            row.setBookCoverUrl(cursor.getString(4));  //bookCoverUrl
            row.setBookName(cursor.getString(5));  //bookName
            row.setBookTagLine(cursor.getString(6));  //bookTagLine
            row.setBookShortDescription(cursor.getString(7));  //bookShortDescription
            row.setBookCategory(cursor.getString(8));  //bookCategory
            row.setBookTag(cursor.getString(9)); //bookTag
            row.setBookGenre(cursor.getString(10));  //bookGenre
            row.setBookUrlPath(cursor.getString(11));  //bookUrlPath
            //bookYearReleased
            row.setLanguage(cursor.getString(13)); //language
            row.setAndroidPhotoPath(cursor.getString(14)); //androidPath
            row.setInfo_header(cursor.getString(15)); //info_header
            row.setInfo_body(cursor.getString(16)); //info_body
            row.setInfo_url(cursor.getString(17));  //info_url
            row.setInfo_url_type(cursor.getString(18));  //info_url_type
            row.setAdd_to_favorite(cursor.getInt(19)); //add_to_favorite
            row.setCreatedBy(cursor.getInt(20)); //createdBy
            row.setDtCreated(cursor.getString(21));  //dtCreated
            row.setEventListener(eventListenerUtil);
            row.setActionEvent(actionEvent);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }
    public List<EbookModel> get_list_my_favorite_library(elUtil eventListenerUtil ,String actionEvent){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ebook where userID="+sharedData.get_userID(context)+" AND add_to_favorite=1",null);
        cursor.moveToFirst();
        List<EbookModel> data = new ArrayList<>();
        while(!cursor.isAfterLast()){
            EbookModel row = new EbookModel();
            row.setEbookID(cursor.getInt(0)); //ebookID
            //userID
            row.setAuthorLogo(cursor.getString(2));  //bookAuthorLogo
            row.setBookAuthor(cursor.getString(3)); //bookAuthor
            row.setBookCoverUrl(cursor.getString(4));  //bookCoverUrl
            row.setBookName(cursor.getString(5));  //bookName
            row.setBookTagLine(cursor.getString(6));  //bookTagLine
            row.setBookShortDescription(cursor.getString(7));  //bookShortDescription
            row.setBookCategory(cursor.getString(8));  //bookCategory
            row.setBookTag(cursor.getString(9)); //bookTag
            row.setBookGenre(cursor.getString(10));  //bookGenre
            row.setBookUrlPath(cursor.getString(11));  //bookUrlPath
            //bookYearReleased
            row.setLanguage(cursor.getString(13)); //language
            row.setAndroidPhotoPath(cursor.getString(14)); //androidPath

            if(row.getAndroidPhotoPath().equals("")){
                row.setContentData(false);
            }else{
                row.setContentData(true);
            }

            row.setInfo_header(cursor.getString(15)); //info_header
            row.setInfo_body(cursor.getString(16)); //info_body
            row.setInfo_url(cursor.getString(17));  //info_url
            row.setInfo_url_type(cursor.getString(18));  //info_url_type
            row.setAdd_to_favorite(cursor.getInt(19)); //add_to_favorite
            row.setCreatedBy(cursor.getInt(20)); //createdBy
            row.setDtCreated(cursor.getString(21));  //dtCreated
            row.setEventListener(eventListenerUtil);
            row.setActionEvent(actionEvent);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public boolean check(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM ebook WHERE ebookID="+id+" AND userID="+sharedData.get_userID(context)+" ",null);
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



}
