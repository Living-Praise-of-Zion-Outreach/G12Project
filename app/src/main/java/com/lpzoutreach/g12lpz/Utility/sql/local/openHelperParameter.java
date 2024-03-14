package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class openHelperParameter  extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private SQLiteDatabase database;

    public openHelperParameter(@Nullable Context context) {
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


    public void set_parameterSpinnerLocal(Spinner obj, String search){
        ArrayList<String> data = new ArrayList<>();
        data.add("All");
        String sql = "SELECT default_name FROM ref_parameter WHERE class_name ='"+search+"' ORDER BY default_name ";

        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            if(!cursor.getString(0).toUpperCase().trim().equals(""))
                data.add(cursor.getString(0));
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        obj.setAdapter(adapter);

        cursor.close();
        closeDatabase();
    }
    public void set_autoCompleteTextListLocal(AutoCompleteTextView obj, String fieldName, String tableName){
        ArrayList<String> data = new ArrayList<>();
        String sql = "SELECT DISTINCT "+ fieldName +"  FROM " + tableName +  " ORDER BY " + fieldName;

        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            if(!cursor.getString(0).toUpperCase().trim().equals(""))
                data.add(cursor.getString(0).toUpperCase());
            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
        obj.setAdapter(adapter);

        cursor.close();
        closeDatabase();
    }
    public void set_autoCompleteTextList(AutoCompleteTextView obj, String className){
        ArrayList<String> data = new ArrayList<>();
        String sql = "SELECT default_name FROM ref_parameter WHERE class_name ='"+className+"' ORDER BY default_name";

        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            data.add(cursor.getString(0).toUpperCase());
            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
        obj.setAdapter(adapter);

        cursor.close();
        closeDatabase();

    }

    public Map<String,String> get_parameter_name(String classname){

        Map<String,String> map = new HashMap<>();

        String sql = "SELECT group_name, default_name FROM ref_parameter WHERE class_name ='"+classname+"' ORDER BY default_name";

        openDatabase();

        @SuppressLint("Recycle") Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            map.put(cursor.getString(0),cursor.getString(1)); //group_name => default_name
            map.put("key-" + cursor.getString(0),cursor.getString(0));
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();

        return map;

    }






}
