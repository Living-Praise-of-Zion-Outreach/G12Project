package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;

public class openHelperChurch extends SQLiteOpenHelper {

    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private EvangelismModel data;
    private SQLiteDatabase database;
    private elUtil eventListenerUtil = elUtil.getInstance();

    public openHelperChurch(@Nullable Context context) {
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

    public void setCallBack(elUtil callBack){
        eventListenerUtil = callBack;
    }

    public void delete_church(){
        openDatabase();
        database.delete("church","userID="+sharedData.get_userID(context),null );
        closeDatabase();
    }

    public void insert(ChurchModel data){
        ContentValues cv = new ContentValues();
        cv.put("userID",data.getUser_ID());
        cv.put("about",data.getAbout());
        cv.put("church_parent",data.getChurch_parent());
        cv.put("church_name",data.getChurch_name());
        cv.put("date_founded",data.getDate_founded());
        cv.put("country",data.getCountry());
        cv.put("province",data.getProvince());
        cv.put("city",data.getMunicipality_city());
        cv.put("barangay",data.getBarangay());
        cv.put("address",data.getAddress());
        cv.put("zipcode", data.getZip_code());
        cv.put("url_photo", accessUrl.BASE_URL +  data.getUrl_logo());
        cv.put("url_cover_photo",accessUrl.BASE_URL +  data.getUrl_cover_photo());
        cv.put("email",data.getEmail());
        cv.put("mobile_no",data.getMobile_no());
        //Toast.makeText(context,data.getContent(),Toast.LENGTH_LONG).show();
        openDatabase();
        database.insert("church",null,cv);
        closeDatabase();
    }


    public boolean check(String id){
        openDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.rawQuery("SELECT * FROM church where userID="+id,null);
        cursor.moveToFirst();
        return cursor.getCount() > 0;
    }

    public ChurchModel get(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM church where userID="+id,null);
        cursor.moveToFirst();
        ChurchModel data = new ChurchModel();
        while(!cursor.isAfterLast()){
            data.setUser_ID(cursor.getInt(0));//userID
            data.setAbout(cursor.getString(1));//about
            data.setChurch_parent(cursor.getString(2));//church_parent
            data.setChurch_name(cursor.getString(3));//church_name
            data.setDate_founded(cursor.getString(4));//date_founded
            data.setCountry(cursor.getString(5));//country
            data.setProvince(cursor.getString(6));//province
            data.setMunicipality_city(cursor.getString(7)); //city
            data.setBarangay(cursor.getString(8)); //barangay
            data.setAddress(cursor.getString(9));  //address
            data.setZip_code(cursor.getString(10));  //zipcode
            data.setUrl_logo(cursor.getString(11));  //url_photo
            data.setUrl_cover_photo(cursor.getString(12)); //url_cover_photo
            data.setEmail(cursor.getString(13));//email
            data.setMobile_no(cursor.getString(14)); //mobile_no
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

}
