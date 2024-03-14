package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.Models.StudentRecordTracker;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

public class openHelperLifeClass extends SQLiteOpenHelper {
    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private StudentRecordTracker data;
    private SQLiteDatabase database;

    public openHelperLifeClass(@Nullable Context context) {
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

    public StudentRecordTracker get_student_record_tracker(String book_name, int lesson_no){
        int userID = sharedData.get_userID(context);
        openDatabase();
        String sql = "SELECT * FROM student_record_tracker WHERE userID=" + userID + " AND book_name = '"+book_name+"' AND lessonNo=" + lesson_no;
        Cursor cursor = database.rawQuery(sql,null);
        StudentRecordTracker row = new StudentRecordTracker();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            row.setUserID(cursor.getInt(0));//userID
            row.setBook_name(cursor.getString(1));//book_name
            row.setLessonNo(cursor.getInt(2)); //lessonNo
            row.setLessonGuideLeaderID(cursor.getInt(3)); //lessonGuideLeaderID
            row.setNetworkLeaderID(cursor.getInt(4)); //networkLeaderID
            row.setEnrollmentType(cursor.getString(5)); //enrollmentType
            row.setAnswer_1(cursor.getString(6));  //answer_1
            row.setAnswer_2(cursor.getString(7));  //answer_2
            row.setAnswer_3(cursor.getString(8));  //answer_3
            row.setAnswer_4(cursor.getString(9));  //answer_4
            row.setAnswer_5(cursor.getString(10));  //answer_5
            row.setAnswer_6(cursor.getString(11));  //answer_6
            row.setAnswer_7(cursor.getString(12));  //answer_7
            row.setAnswer_8(cursor.getString(13));  //answer_8
            row.setAnswer_9(cursor.getString(14));  //answer_9
            row.setStudy(cursor.getString(15)); //study
            row.setOrder(cursor.getInt(16)); //order
            row.setDtCreated(cursor.getString(17)); //dtCreated
            row.setCreatedBy(cursor.getInt(18)); //createdBy
            cursor.moveToNext();
        }

        if(cursor.getCount()==0) {
            row.setUserID(0);//userID
            row.setBook_name(book_name);//book_name
            row.setLessonNo(lesson_no); //lessonNo
            row.setLessonGuideLeaderID(0); //lessonGuideLeaderID
            row.setNetworkLeaderID(0); //networkLeaderID
            row.setEnrollmentType(""); //enrollmentType
            row.setAnswer_1("");  //answer_1
            row.setAnswer_2("");  //answer_2
            row.setAnswer_3("");  //answer_3
            row.setAnswer_4("");  //answer_4
            row.setAnswer_5("");  //answer_5
            row.setAnswer_6("");  //answer_6
            row.setAnswer_7("");  //answer_7
            row.setAnswer_8("");  //answer_8
            row.setAnswer_9("");  //answer_9
            row.setStudy(""); //study
            row.setOrder(0); //order
            row.setDtCreated(""); //dtCreated
            row.setCreatedBy(0); //createdBy
        }

        cursor.close();
        closeDatabase();
        return row;
    }

    public void delete_student_record_tracker(String book_name, int lesson_no){
        int userID = sharedData.get_userID(context);
        openDatabase();
        String sql = "DELETE FROM student_record_tracker WHERE userID=" + userID + " AND book_name = '"+book_name+"' AND lessonNo=" + lesson_no;
        Cursor cursor = database.rawQuery(sql,null);
        cursor.close();
        closeDatabase();
    }

    public Boolean insert_student_record_tracker(StudentRecordTracker data){
        //delete record first//
        delete_student_record_tracker(data.getBook_name(),data.getLessonNo());
        long result=0;
        ContentValues cv = new ContentValues();
        cv.put("userID",data.getUserID());
        cv.put("book_name",data.getBook_name());
        cv.put("lessonNo",data.getLessonNo());
        cv.put("lessonGuideLeaderID",data.getLessonGuideLeaderID());
        cv.put("networkLeaderID",data.getNetworkLeaderID());
        cv.put("enrollmentType",data.getEnrollmentType());
        cv.put("answer_1",data.getAnswer_1());
        cv.put("answer_2",data.getAnswer_2());
        cv.put("answer_3",data.getAnswer_3());
        cv.put("answer_4",data.getAnswer_4());
        cv.put("answer_5",data.getAnswer_5());
        cv.put("answer_6",data.getAnswer_6());
        cv.put("answer_7",data.getAnswer_7());
        cv.put("answer_8",data.getAnswer_8());
        cv.put("answer_9",data.getAnswer_9());
        cv.put("study",data.getStudy());
        //cv.put("order",0);
        //cv.put("dtCreated",data.getDtCreated());
        //cv.put("createdBy",data.getCreatedBy());

        Log.v("INSERT_LIFECLASS",cv.toString());
        openDatabase();
        result = database.insert("student_record_tracker",null,cv);
        closeDatabase();
        return result != -1;
    }

}
