package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Models.NotifyAppUpdateModel;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.util.ArrayList;
import java.util.List;

public class openHelperAppUpdate extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private EvangelismModel data;
    private SQLiteDatabase database;
    private elUtil eventListenerUtil = elUtil.getInstance();

    public openHelperAppUpdate(@Nullable Context context) {
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

    public void delete(){
        openDatabase();
        database.delete("app_system_update","",null );
        closeDatabase();
    }

    public void insert(List<NotifyAppUpdateModel> data){
        openDatabase();
        for(int i=0;i<data.size();i++){
            ContentValues cv = new ContentValues();
            cv.put("appUpdateID",data.get(i).getAppUpdateID());
            cv.put("appTitle",data.get(i).getAppTitle());
            cv.put("appMinRequirements",data.get(i).getAppMinRequirements());
            cv.put("appMaxRequirements",data.get(i).getAppMaxRequirements());
            cv.put("appDescription",data.get(i).getAppDescription());
            cv.put("appVersion",data.get(i).getAppVersion());
            cv.put("appPath",data.get(i).getAppPath());
            cv.put("publishReady",data.get(i).getPublishReady());
            cv.put("appDatePublished",data.get(i).getAppDatePublished());
            cv.put("dtCreated",data.get(i).getDtCreated());
            database.insert("app_system_update",null,cv);
        }
        closeDatabase();
    }

    public List<NotifyAppUpdateModel> get_all(){
        openDatabase();
        List<NotifyAppUpdateModel> data = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM app_system_update",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            NotifyAppUpdateModel row = new NotifyAppUpdateModel();
            row.setAppUpdateID(cursor.getInt(0)); //appUpdateID
            row.setAppTitle(cursor.getString(1));  //appTitle
            row.setAppMinRequirements(cursor.getString(2));  //appMinRequirements
            row.setAppMaxRequirements(cursor.getString(3));  //appMaxRequirements
            row.setAppDescription(cursor.getString(4));  //appDescription
            row.setAppVersion(cursor.getString(5));  //appVersion
            row.setAppPath(cursor.getString(6));  //appPath
            row.setPublishReady(cursor.getString(7));  //publishReady
            row.setAppDatePublished(cursor.getString(8));  //appDatePublished
            row.setDtCreated(cursor.getString(9));  //dtCreated
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

}
