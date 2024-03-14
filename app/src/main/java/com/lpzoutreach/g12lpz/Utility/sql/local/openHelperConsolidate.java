package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.ConsolidateModel;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.util.ArrayList;
import java.util.List;

public class openHelperConsolidate extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private EvangelismModel data;
    private SQLiteDatabase database;
    private elUtil eventListenerUtil = elUtil.getInstance();

    public openHelperConsolidate(@Nullable Context context) {
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

    public void delete_all(String id){
        openDatabase();
        try{
            database.delete("profile_consolidate", "userID=?",new String[]{id});
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        closeDatabase();
    }

    public boolean delete(String id, String type){
        long result=0;
        openDatabase();
        try{
            database.delete("profile_consolidate", "tempProfileID=? AND book_name=?",new String[]{id,type});
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        closeDatabase();
        if(result==-1) return false;
        else return true;
    }

    public boolean delete_evangelizeID(String id){
        long result=0;
        openDatabase();
        try{
            database.delete("profile_consolidate", "evangelizeID=? ",new String[]{id});
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        closeDatabase();
        if(result==-1) return false;
        else return true;
    }

    public boolean insert(List<ConsolidateModel> data){
        int userID = sharedData.get_userID(context);
        long result=0;
        openDatabase();
        for(int i=0;i<data.size();i++){
            ContentValues cv = new ContentValues();
            String id = data.get(i).getTemp_profileID() + String.valueOf(userID) + data.get(i).getLessonNo();
            cv.put("consolidateID", id);
            cv.put("linkedUserID",data.get(i).getLinkedUserID());
            cv.put("tempProfileID",data.get(i).getTemp_profileID());
            cv.put("userID",userID);
            cv.put("book_name",data.get(i).getBook_name());

            cv.put("lessonNo",data.get(i).getLessonNo());
            cv.put("lessonTitle",data.get(i).getLessonTitle());
            cv.put("dtTrain",data.get(i).getDtTrain());
            database.insert("profile_consolidate",null,cv);
        }
        closeDatabase();
        return true;
    }

    public int get_count(String id, String type){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM profile_consolidate where tempProfileID="+id+ " AND book_name='"+type+"'",null);
        cursor.moveToFirst();

        int total = cursor.getCount();
        cursor.close();
        closeDatabase();

        return total;
    }

    public List<ConsolidateModel> get_list(String id, String type){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM profile_consolidate where tempProfileID="+id+ " AND book_name='"+type+"'",null);
        cursor.moveToFirst();
        List<ConsolidateModel> data = new ArrayList<>();
        while(!cursor.isAfterLast()){
            ConsolidateModel row = new ConsolidateModel();
            row.setConsolidateID(cursor.getInt(0));//consolidateID
            row.setLinkedUserID(cursor.getInt(1));//linkedUserID
            row.setTemp_profileID(cursor.getInt(2));//tempProfileID
            row.setUserID(cursor.getInt(3));//userID
            row.setLessonNo(cursor.getInt(4));//book_name
            row.setLessonNo(cursor.getInt(5));//lessonNo
            row.setLessonTitle(cursor.getString(6));//lessonTitle
            row.setDtTrain(cursor.getString(7));//dtTrain
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public List<ConsolidateModel> get_all_list(){
        int userID = sharedData.get_userID(context);
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM profile_consolidate where userID="+userID,null);
        cursor.moveToFirst();
        List<ConsolidateModel> data = new ArrayList<>();
        while(!cursor.isAfterLast()){
            ConsolidateModel row = new ConsolidateModel();
            row.setConsolidateID(cursor.getInt(0));//consolidateID
            row.setLinkedUserID(cursor.getInt(1));//linkedUserID
            row.setTemp_profileID(cursor.getInt(2));//tempProfileID
            row.setUserID(cursor.getInt(3));//userID
            row.setBook_name(cursor.getString(4));//book_name
            row.setLessonNo(cursor.getInt(5));//lessonNo
            row.setLessonTitle(cursor.getString(6));//lessonTitle
            row.setDtTrain(cursor.getString(7));//dtTrain
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }


}
