package com.lpzoutreach.g12lpz.Utility.sql.local;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.util.ArrayList;
import java.util.List;

public class openHelperEvangelism extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private EvangelismModel data;
    private SQLiteDatabase database;
    private elUtil eventListenerUtil = elUtil.getInstance();

    public openHelperEvangelism(@Nullable Context context) {
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

    public List<EvangelismModel>  populate_sync_evangelize(){
        int userID = sharedData.get_userID(context);

        String where = "";

        where = " createdBy = " + userID;

        List<EvangelismModel> data = new ArrayList<>();

        openDatabase();
        String sql = "SELECT * FROM profile_evangelize WHERE ";
        Cursor cursor = database.rawQuery(sql + where,null);
        cursor.moveToFirst();
        String value = null;
        while(!cursor.isAfterLast()){

            EvangelismModel row;
            row = new EvangelismModel();

            row.setEventListenerUtil(eventListenerUtil);

            row.setTempProfileID(cursor.getInt(0));//tempProfileID
            row.setUserID( cursor.getInt(1));//photoUrl
            row.setProfilePhotoPath(cursor.getString(2));//photoUrl
            row.setFirst_name(cursor.getString(3));//first_name
            row.setMiddle_name(cursor.getString(4));//middle_name
            row.setLast_name(cursor.getString(5));//last_name
            row.setDate_of_birth(cursor.getString(6));//date_of_birth
            row.setSex(cursor.getString(7));//sex
            row.setCivil_status(cursor.getString(8));//civil_status
            row.setLoc_country(cursor.getString(9));//loc_country
            row.setLoc_province(cursor.getString(10));//loc_province
            row.setLoc_city(cursor.getString(11));//loc_city
            row.setLoc_barangay(cursor.getString(12));//loc_barangay
            row.setLoc_address(cursor.getString(13));//loc_address
            row.setCon_mobile_no(cursor.getString(14));//con_mobile_no
            row.setEmail(cursor.getString(15));//email

            row.setIsEvangelize(cursor.getInt(16));//isEvangelize
            row.setEvangelizeDt(cursor.getString(17));//evangelizeDt
            row.setIsDrop(cursor.getInt(18));//isDrop
            row.setDropDt(cursor.getString(19));//dropDt
            row.setIsConsolidate(cursor.getInt(20));//isConsolidate
            row.setConsolidateDt(cursor.getString(21));//consolidateDt
            row.setEvangelizeType(cursor.getString(22));//evangelizeType

            row.setDelFlag(cursor.getInt(23));//delFlag
            row.setActionFlag(cursor.getInt(24));//actionFlag
            row.setCreatedBy(cursor.getInt(25));//createdBy
            row.setDtCreated(cursor.getString(26));//dtCreated
            row.setUpdatedBy(cursor.getInt(27));//updatedBy
            row.setDtUpdated(cursor.getString(28));//dtUpdated

            row.setPhotoBitmap(cursor.getString(29));
            row.setAndroidPhotoPath(cursor.getString(30));

            data.add(row);

            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public List<EvangelismModel>  get_list_evangelize(String name, String type){
        int userID = sharedData.get_userID(context);

        String where = "";

        where = " updatedBy= " +userID;

        where += " AND (first_name LIKE '"+name+"%' OR middle_name LIKE '"+name+"%' OR last_name LIKE '"+name+"%')";

        if(type.equals("Evangelized"))
            where += " AND isEvangelize=1 ";
        else if(type.equals("Drop"))
            where += " AND isDrop=1 ";
        else if(type.equals("Consolidated"))
            where += " AND isConsolidate=1 ";

        List<EvangelismModel> data = new ArrayList<>();

        openDatabase();
        String sql = "SELECT * FROM profile_evangelize WHERE ";
        String order = " ORDER BY last_name,first_name,middle_name";

        Cursor cursor = database.rawQuery(sql + where + order,null);
        cursor.moveToFirst();
        String value = null;
        while(!cursor.isAfterLast()){

            EvangelismModel row;
            row = new EvangelismModel();

            row.setEventListenerUtil(eventListenerUtil);

            row.setTempProfileID(cursor.getInt(0));//tempProfileID
            row.setUserID( cursor.getInt(1));//photoUrl
            row.setProfilePhotoPath(cursor.getString(2));//photoUrl
            row.setFirst_name(cursor.getString(3));//first_name
            row.setMiddle_name(cursor.getString(4));//middle_name
            row.setLast_name(cursor.getString(5));//last_name
            row.setDate_of_birth(cursor.getString(6));//date_of_birth
            row.setSex(cursor.getString(7));//sex
            row.setCivil_status(cursor.getString(8));//civil_status
            row.setLoc_country(cursor.getString(9));//loc_country
            row.setLoc_province(cursor.getString(10));//loc_province
            row.setLoc_city(cursor.getString(11));//loc_city
            row.setLoc_barangay(cursor.getString(12));//loc_barangay
            row.setLoc_address(cursor.getString(13));//loc_address
            row.setCon_mobile_no(cursor.getString(14));//con_mobile_no
            row.setEmail(cursor.getString(15));//email

            row.setIsEvangelize(cursor.getInt(16));//isEvangelize
            row.setEvangelizeDt(cursor.getString(17));//evangelizeDt
            row.setIsDrop(cursor.getInt(18));//isDrop
            row.setDropDt(cursor.getString(19));//dropDt
            row.setIsConsolidate(cursor.getInt(20));//isConsolidate
            row.setConsolidateDt(cursor.getString(21));//consolidateDt
            row.setEvangelizeType(cursor.getString(22));//evangelizeType

            row.setDelFlag(cursor.getInt(23));//delFlag
            row.setActionFlag(cursor.getInt(24));//actionFlag
            row.setCreatedBy(cursor.getInt(25));//createdBy
            row.setDtCreated(cursor.getString(26));//dtCreated
            row.setUpdatedBy(cursor.getInt(27));//updatedBy
            row.setDtUpdated(cursor.getString(28));//dtUpdated

            row.setPhotoBitmap(cursor.getString(29));
            row.setAndroidPhotoPath(cursor.getString(30));

            row.setEvangelizeID(cursor.getInt(31));//evangelizeID

            data.add(row);

            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    private ContentValues getContentValues(EvangelismModel data){
        ContentValues cv = new ContentValues();
        cv.put("profile_photo_path",data.getProfilePhotoPath());
        cv.put("photo_bitmap",data.getPhotoBitmap());
        cv.put("first_name",data.getFirst_name());
        cv.put("middle_name",data.getMiddle_name());
        cv.put("last_name",data.getLast_name());
        cv.put("sex",data.getSex());
        cv.put("date_of_birth",data.getDate_of_birth());
        cv.put("civil_status",data.getCivil_status());
        cv.put("loc_country",data.getLoc_country());
        cv.put("loc_province",data.getLoc_province());
        cv.put("loc_city",data.getLoc_city());
        cv.put("loc_barangay",data.getLoc_barangay());
        cv.put("loc_address",data.getLoc_address());
        cv.put("con_mobile_no",data.getCon_mobile_no());
        cv.put("email",data.getEmail());
        cv.put("isEvangelize",data.getIsEvangelize());
        cv.put("evangelizeDt",data.getEvangelizeDt());
        cv.put("isDrop",data.getIsDrop());
        cv.put(("dropDt"),data.getDropDt());
        cv.put("isConsolidate",data.getIsConsolidate());
        cv.put("consolidateDt",data.getConsolidateDt());
        cv.put("evangelizeType",data.getEvangelizeType());
        cv.put("android_photo_path",data.getAndroidPhotoPath());

        return cv;
    }

    public EvangelismModel select_data_consolidate_tracker(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM profile_evangelize where evangelizeID="+id,null);
        cursor.moveToFirst();

        EvangelismModel row;
        row = new EvangelismModel();

        while(!cursor.isAfterLast()){


            row.setEventListenerUtil(eventListenerUtil);

            row.setTempProfileID(cursor.getInt(0));//tempProfileID
            row.setUserID( cursor.getInt(1));//photoUrl
            row.setProfilePhotoPath(cursor.getString(2));//photoUrl
            row.setFirst_name(cursor.getString(3));//first_name
            row.setMiddle_name(cursor.getString(4));//middle_name
            row.setLast_name(cursor.getString(5));//last_name
            row.setDate_of_birth(cursor.getString(6));//date_of_birth
            row.setSex(cursor.getString(7));//sex
            row.setCivil_status(cursor.getString(8));//civil_status
            row.setLoc_country(cursor.getString(9));//loc_country
            row.setLoc_province(cursor.getString(10));//loc_province
            row.setLoc_city(cursor.getString(11));//loc_city
            row.setLoc_barangay(cursor.getString(12));//loc_barangay
            row.setLoc_address(cursor.getString(13));//loc_address
            row.setCon_mobile_no(cursor.getString(14));//con_mobile_no
            row.setEmail(cursor.getString(15));//email

            row.setIsEvangelize(cursor.getInt(16));//isEvangelize
            row.setEvangelizeDt(cursor.getString(17));//evangelizeDt
            row.setIsDrop(cursor.getInt(18));//isDrop
            row.setDropDt(cursor.getString(19));//dropDt
            row.setIsConsolidate(cursor.getInt(20));//isConsolidate
            row.setConsolidateDt(cursor.getString(21));//consolidateDt
            row.setEvangelizeType(cursor.getString(22));//evangelizeType

            row.setDelFlag(cursor.getInt(23));//delFlag
            row.setActionFlag(cursor.getInt(24));//actionFlag
            row.setCreatedBy(cursor.getInt(25));//createdBy
            row.setDtCreated(cursor.getString(26));//dtCreated
            row.setUpdatedBy(cursor.getInt(27));//updatedBy
            row.setDtUpdated(cursor.getString(28));//dtUpdated

            row.setPhotoBitmap(cursor.getString(29));
            row.setAndroidPhotoPath(cursor.getString(30));


            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return row;
    }
    public EvangelismModel select_data(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM profile_evangelize where tempProfileID="+id,null);
        cursor.moveToFirst();

        EvangelismModel row;
        row = new EvangelismModel();

        while(!cursor.isAfterLast()){


            row.setEventListenerUtil(eventListenerUtil);

            row.setTempProfileID(cursor.getInt(0));//tempProfileID
            row.setUserID( cursor.getInt(1));//photoUrl
            row.setProfilePhotoPath(cursor.getString(2));//photoUrl
            row.setFirst_name(cursor.getString(3));//first_name
            row.setMiddle_name(cursor.getString(4));//middle_name
            row.setLast_name(cursor.getString(5));//last_name
            row.setDate_of_birth(cursor.getString(6));//date_of_birth
            row.setSex(cursor.getString(7));//sex
            row.setCivil_status(cursor.getString(8));//civil_status
            row.setLoc_country(cursor.getString(9));//loc_country
            row.setLoc_province(cursor.getString(10));//loc_province
            row.setLoc_city(cursor.getString(11));//loc_city
            row.setLoc_barangay(cursor.getString(12));//loc_barangay
            row.setLoc_address(cursor.getString(13));//loc_address
            row.setCon_mobile_no(cursor.getString(14));//con_mobile_no
            row.setEmail(cursor.getString(15));//email

            row.setIsEvangelize(cursor.getInt(16));//isEvangelize
            row.setEvangelizeDt(cursor.getString(17));//evangelizeDt
            row.setIsDrop(cursor.getInt(18));//isDrop
            row.setDropDt(cursor.getString(19));//dropDt
            row.setIsConsolidate(cursor.getInt(20));//isConsolidate
            row.setConsolidateDt(cursor.getString(21));//consolidateDt
            row.setEvangelizeType(cursor.getString(22));//evangelizeType

            row.setDelFlag(cursor.getInt(23));//delFlag
            row.setActionFlag(cursor.getInt(24));//actionFlag
            row.setCreatedBy(cursor.getInt(25));//createdBy
            row.setDtCreated(cursor.getString(26));//dtCreated
            row.setUpdatedBy(cursor.getInt(27));//updatedBy
            row.setDtUpdated(cursor.getString(28));//dtUpdated

            row.setPhotoBitmap(cursor.getString(29));
            row.setAndroidPhotoPath(cursor.getString(30));


            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return row;
    }
    public Boolean insert(EvangelismModel data){

        int userID = sharedData.get_userID(context);
        long result=0;

        ContentValues cv = getContentValues(data);
        int tempProfileID = max_id("tempProfileID","profile_evangelize");

        cv.put("tempProfileID",tempProfileID+1);
        cv.put("userID",String.valueOf(userID));
        cv.put("dtCreated", utilDate.getCurrentDate_DatePickerFormat());
        cv.put("createdBy",String.valueOf(userID));
        cv.put("dtUpdated",utilDate.getCurrentDate_DatePickerFormat());
        cv.put("updatedBy",String.valueOf(userID));
        cv.put("evangelizeID",String.valueOf(tempProfileID) + String.valueOf(sharedData.get_userID(context)));

        openDatabase();

        result = database.insert("profile_evangelize",null,cv);
        //Toast.makeText(context,"You have successfully added the record.",Toast.LENGTH_LONG).show();

        closeDatabase();
        if(result==-1) return false;
        else return true;

    }

    public Boolean insert_from_server(EvangelismModel data){

        int userID = sharedData.get_userID(context);
        long result=0;

        ContentValues cv = getContentValues(data);

        cv.put("tempProfileID",data.getTempProfileID());
        cv.put("userID",String.valueOf(userID));
        cv.put("dtCreated", utilDate.getCurrentDate_DatePickerFormat());
        cv.put("createdBy",String.valueOf(userID));
        cv.put("dtUpdated",utilDate.getCurrentDate_DatePickerFormat());
        cv.put("updatedBy",String.valueOf(userID));
        cv.put("evangelizeID",String.valueOf(data.getEvangelizeID()));

        openDatabase();

        result = database.insert("profile_evangelize",null,cv);
        //Toast.makeText(context,"You have successfully added the record.",Toast.LENGTH_LONG).show();

        closeDatabase();
        if(result==-1) return false;
        else return true;

    }

    public boolean update(EvangelismModel data,String id){
        int userID = sharedData.get_userID(context);
        int tempProfileID = data.getTempProfileID();
        long result=0;

        ContentValues cv = getContentValues(data);

        cv.put("dtUpdated",utilDate.getCurrentDate_DatePickerFormat());
        cv.put("updatedBy",String.valueOf(userID));

        openDatabase();
        result = database.update("profile_evangelize",cv,"tempProfileID = " + id,null );
        //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

        closeDatabase();

        if(result==-1) return false;
        else return true;
    }

    public boolean delete(String id){
        long result=0;
        openDatabase();
        result = database.delete("profile_evangelize","tempProfileID="+id,null );
        closeDatabase();
        if(result==-1) return false;
        else return true;
    }

    public boolean delete_all(){
        int userID = sharedData.get_userID(context);
        long result=0;
        openDatabase();
        result = database.delete("profile_evangelize","userID="+userID,null );
        closeDatabase();
        if(result==-1) return false;
        else return true;
    }

    public int max_id(String col, String table){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT MAX("+col+") FROM " + table,null);
        cursor.moveToFirst();
        int id = 0;
        while(!cursor.isAfterLast()){
            id = cursor.getInt(0);
            cursor.moveToNext();
        }
        closeDatabase();
        cursor.close();
        return id;
    }

}
