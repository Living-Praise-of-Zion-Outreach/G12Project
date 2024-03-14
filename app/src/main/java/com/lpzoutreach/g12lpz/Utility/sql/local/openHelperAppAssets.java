package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.AppAssetModel;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

public class openHelperAppAssets extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private EvangelismModel data;
    private SQLiteDatabase database;
    private elUtil eventListenerUtil = elUtil.getInstance();

    public openHelperAppAssets(@Nullable Context context) {
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

    public int count_all_app_assets(){
        long result=0;
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT count(*) FROM app_assets",null);
        cursor.moveToFirst(); closeDatabase();
        int total=0;
        while(!cursor.isAfterLast()){
            total = cursor.getInt(0);
            cursor.moveToNext();
        }
        closeDatabase();
        cursor.close();
        return total;
    }

    public AppAssetModel get_app_assets(String asset_name){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM app_assets where assetName='"+asset_name+"'",null);
        cursor.moveToFirst();
        AppAssetModel data = new AppAssetModel();
        while(!cursor.isAfterLast()){
            data.setAppAssetID(cursor.getInt(0));//appAssetID
            data.setAssetLogo(cursor.getString(1)); //assetLogo
            data.setAssetName(cursor.getString(2));  //assetName
            data.setAssetType(cursor.getString(3));  //assetType
            data.setAssetDescription(cursor.getString(4)); //assetDescription
            data.setCreatedBy(cursor.getInt(5));//createdBy
            data.setDtCreated(cursor.getString(6)); //dtCreated
            data.setDtUpdated(cursor.getString(7));  //dtUpdated
            data.setUpdatedBy(cursor.getInt(8));  //updatedBy
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public void delete_app_assets(){
        openDatabase();
        database.delete("app_assets","",null );
        closeDatabase();
    }

    public Boolean insert_app_assets(AppAssetModel data){
        long result=0;
        ContentValues cv = new ContentValues();

        cv.put("appAssetID",data.getAppAssetID());
        cv.put("assetLogo",data.getAndroid_asset_logo_path());
        cv.put("assetName",data.getAssetName());
        cv.put("assetType",data.getAssetType());
        cv.put("assetDescription",data.getAssetDescription());
        cv.put("createdBy",data.getCreatedBy());
        cv.put("dtCreated",data.getDtCreated());
        cv.put("dtUpdated",data.getDtUpdated());
        cv.put("updatedBy",data.getUpdatedBy());

        openDatabase();
        result = database.insert("app_assets",null,cv);
        //Toast.makeText(context,"You have successfully added the record.",Toast.LENGTH_LONG).show();
        closeDatabase();
        if(result==-1) return false;
        else return true;
    }
}
