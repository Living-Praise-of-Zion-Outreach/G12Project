package com.lpzoutreach.g12lpz.Utility.sql.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.BookModel;
import com.lpzoutreach.g12lpz.Models.DiscipleLessonsModel;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Models.LessonsModel;
import com.lpzoutreach.g12lpz.Models.PresentMaterialsModel;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.util.ArrayList;
import java.util.List;

public class openHelperBook extends SQLiteOpenHelper {

    public static final String DBNAME = sharedData.DBNAME;
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private EvangelismModel data;
    private SQLiteDatabase database;
    private elUtil eventListenerUtil = elUtil.getInstance();


    public openHelperBook(@Nullable Context context) {
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
    private ContentValues getContentValues(BookModel data){
        ContentValues cv = new ContentValues();
        cv.put("bookID",data.getBookID());
        cv.put("book_logo",data.getBook_logo());
        cv.put("book_name",data.getBook_name());
        cv.put("book_description",data.getBook_description());
        cv.put("book_language",data.getBook_language());
        cv.put("book_total_chapters",data.getBook_total_chapters());
        cv.put("dtCreated",data.getDtCreated());
        cv.put("userID",sharedData.get_userID(context));
        cv.put("android_folder",data.getAndroid_folder());
        cv.put("book_classification",data.getBook_classification());
        return cv;
    }

    public Boolean insert_book(BookModel data){
        long result=0;
        ContentValues cv = getContentValues(data);
        openDatabase();
        result = database.insert("material_book",null,cv);
        //Toast.makeText(context,"You have successfully added the record.",Toast.LENGTH_LONG).show();
        closeDatabase();
        if(result==-1) return false;
        else return true;
    }

    public void insert_book_lessons(BookModel data){
        ContentValues cv = new ContentValues();
        cv.put("lessonsID",data.getLessonsID());
        cv.put("bookID",data.getBookID());
        cv.put("lessonNo",data.getLessonNo());
        cv.put("lessonTitle",data.getLessonTitle());
        cv.put("lessonLogo",data.getLessonsLogo());
        cv.put("lessonSubtitle",data.getLessonSubtitle());
        cv.put("lessonType",data.getLessonType());
        cv.put("content",data.getContent());
        cv.put("content_dark",data.getContent_dark());
        cv.put("dtCreated",data.getDtCreated());
        cv.put("createdBy",data.getCreatedBy());
        cv.put("userID",sharedData.get_userID(context));
        cv.put("study_the_word",data.getStudy_the_word());
        cv.put("html_content",data.getHtml_content());
        cv.put("html_header",data.getHtml_header());
        openDatabase();
        database.insert("material_book_lessons",null,cv);

        closeDatabase();
    }

    public void insert_disciple_lessons(DiscipleLessonsModel data){
        ContentValues cv = new ContentValues();
        cv.put("lessonID",data.getLessonID());
        cv.put("userID",sharedData.get_userID(context));
        cv.put("bookID",data.getBookID());
        cv.put("lessonNo",data.getLessonNo());
        cv.put("bookType",data.getBookType());
        cv.put("lessonTitle",data.getLessonTitle());
        cv.put("menu_title",data.getMenu_title());
        cv.put("img_header",data.getImg_header());
        cv.put("content_1",data.getContent_1());
        cv.put("content_2",data.getContent_2());
        cv.put("content_3",data.getContent_3());
        cv.put("content_4",data.getContent_4());
        cv.put("study_the_word",data.getStudy_the_word());
        cv.put("language",data.getLanguage());

        openDatabase();
        database.insert("material_book_discipleship_lessons",null,cv);
        closeDatabase();
    }


    public BookModel get_book(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book where bookID="+id+" AND userID="+sharedData.get_userID(context)+" ORDER BY book_name",null);
        cursor.moveToFirst();

        BookModel data = new BookModel();
        while(!cursor.isAfterLast()){
            data.setBookID(cursor.getInt(0));//bookID
            data.setBook_logo(cursor.getString(1));//book_logo
            data.setBook_name(cursor.getString(2));//book_name
            data.setBook_description(cursor.getString(3));//book_description
            data.setBook_language(cursor.getString(4));//book_language
            data.setDtCreated(cursor.getString(5));//dtCreated
            data.setAndroid_folder(cursor.getString(8));//android folder
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public List<BookModel> get_list_book(String book_classification){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book WHERE userID="+sharedData.get_userID(context)+" AND book_classification='"+ book_classification+ "' ORDER BY book_name",null);
        cursor.moveToFirst();

        List<BookModel> data = new ArrayList<>();

        while(!cursor.isAfterLast()){
            BookModel row = new BookModel();
            row.setBookID(cursor.getInt(0));//bookID
            row.setBook_logo(cursor.getString(1));//book_logo
            row.setBook_name(cursor.getString(2));//book_name
            row.setBook_description(cursor.getString(3));//book_description
            row.setBook_language(cursor.getString(4));//book_language
            row.setDtCreated(cursor.getString(5));//dtCreated
            row.setBook_total_chapters(cursor.getInt(6)); //Total Chapters
            row.setIs_file_downloaded(true);
            data.add(row);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return data;

    }

    public List<PresentMaterialsModel> get_list_present(){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_present WHERE userID="+sharedData.get_userID(context)+" ORDER BY presentTitle",null);
        cursor.moveToFirst();

        List<PresentMaterialsModel> data = new ArrayList<>();

        while(!cursor.isAfterLast()){
            PresentMaterialsModel row = new PresentMaterialsModel();
            row.setMaterialPresentID(cursor.getInt(0));//materialPresentID
            row.setPresent_Logo(cursor.getString(1));//presentLogo
            row.setPresentTitle(cursor.getString(2));//presentTitle
            row.setPresentSubTitle(cursor.getString(3));//presentSubTitle
            row.setPresentLanguage(cursor.getString(4));//presentLanguage
            row.setPresentSlides(cursor.getString(5));//presentSlides
            row.setContent(cursor.getString(6)); //content
            row.setContent_dark(cursor.getString(6)); //content
            row.setIs_file_downloaded(true);
            data.add(row);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return data;

    }

    public void delete_list_book_lesson(String id){
        openDatabase();
        database.delete("material_book_lessons","bookID="+id+" AND userID="+sharedData.get_userID(context),null );
        closeDatabase();
    }
    public void delete_list_disciple_lesson(String id){
        openDatabase();
        database.delete("material_book_discipleship_lessons","bookID="+id+" AND userID="+sharedData.get_userID(context),null );
        closeDatabase();
    }
    public List<LessonsModel> get_list_book_lesson(String bookID,elUtil eventListenerUtil){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book_lessons where bookID="+bookID+" AND userID="+sharedData.get_userID(context)+" ORDER BY lessonsID",null);
        cursor.moveToFirst();

        List<LessonsModel> data = new ArrayList<>();

        while(!cursor.isAfterLast()){
            LessonsModel rows = new LessonsModel();
            rows.setLesson_id(cursor.getString(0)); // lessonsID
            rows.setLesson_number(cursor.getString(2)); //lessonNo
            rows.setLesson_name(cursor.getString(3));//lessonTitle
            rows.setLesson_subname(cursor.getString(4));//lessonSubtitle
            rows.setAndroid_content(cursor.getString(5));//content
            rows.setAndroid_content_dark(cursor.getString(6));//content_dark
            rows.setLessonLogo(cursor.getString(11)); //lessonLogo
            rows.setEventListenerUtil(eventListenerUtil);
            data.add(rows);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return data;

    }

    public List<DiscipleLessonsModel> get_list_disciple_lesson(String bookID,elUtil eventListenerUtil){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book_discipleship_lessons where bookID="+bookID+" AND userID="+sharedData.get_userID(context)+" AND language LIKE '"+sharedData.get_lifeclass_current_language(context)+"' ORDER BY lessonID",null);
        cursor.moveToFirst();

        List<DiscipleLessonsModel> data = new ArrayList<>();

        while(!cursor.isAfterLast()){
            DiscipleLessonsModel rows = new DiscipleLessonsModel();
            rows.setLessonID(cursor.getInt(0)); // lessonsID
            //userID
            rows.setBookID(cursor.getInt(2)); //bookID
            rows.setLessonNo(cursor.getInt(3)); //lessonNo
            rows.setBookType(cursor.getString(4)); //bookType
            rows.setLessonTitle(cursor.getString(5));  //lessonTitle
            rows.setMenu_title(cursor.getString(6)); //menu_title
            rows.setImg_header(cursor.getString(7));  //img_header
            rows.setContent_1(cursor.getString(8)); //content_1
            rows.setContent_2(cursor.getString(9));//content_2
            rows.setContent_3(cursor.getString(10)); //content_3
            rows.setContent_4(cursor.getString(11)); //content_4
            rows.setStudy_the_word(cursor.getString(12)); //study_the_word
            rows.setLanguage(cursor.getString(13)); //language
            rows.setEventListenerUtil(eventListenerUtil);
            data.add(rows);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return data;

    }

    public LessonsModel get_book_lesson(int lessonID){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book_lessons where  lessonsID = "+lessonID+" AND userID="+sharedData.get_userID(context)+" ",null);
        cursor.moveToFirst();
        LessonsModel rows = new LessonsModel();
        while(!cursor.isAfterLast()){
            rows.setLesson_number(cursor.getString(2)); //lessonNo
            rows.setLesson_name(cursor.getString(3));//lessonTitle
            rows.setLesson_subname(cursor.getString(4));//lessonSubtitle
            rows.setAndroid_content(cursor.getString(5));//content
            rows.setAndroid_content_dark(cursor.getString(6));//content_dark
            rows.setLessonLogo(cursor.getString(11)); //lessonLogo
            rows.setStudy_the_word(cursor.getString(12));//study_the_word
            rows.setHtml_content(cursor.getString(13));//html_content
            rows.setHtml_header(cursor.getString(14));//html_header
            rows.setWord_1("");
            rows.setWord_2("");
            rows.setWord_3("");
            rows.setWord_4("");
            rows.setWord_5("");
            rows.setWord_6("");
            rows.setWord_7("");
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return rows;

    }

    public DiscipleLessonsModel get_disciple_lesson(int lessonNo){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book_discipleship_lessons where  lessonNo = "+lessonNo+" AND userID="+sharedData.get_userID(context)+" AND language LIKE '"+ sharedData.get_lifeclass_current_language(context) +"'",null);
        cursor.moveToFirst();
        DiscipleLessonsModel rows = new DiscipleLessonsModel();
        while(!cursor.isAfterLast()){

            rows.setLessonID(cursor.getInt(0)); // lessonsID
            //userID
            rows.setBookID(cursor.getInt(2)); //bookID
            rows.setLessonNo(cursor.getInt(3)); //lessonNo
            rows.setBookType(cursor.getString(4)); //bookType
            rows.setLessonTitle(cursor.getString(5));  //lessonTitle
            rows.setMenu_title(cursor.getString(6)); //menu_title
            rows.setImg_header(cursor.getString(7));  //img_header
            rows.setContent_1(cursor.getString(8)); //content_1
            rows.setContent_2(cursor.getString(9));//content_2
            rows.setContent_3(cursor.getString(10)); //content_3
            rows.setContent_4(cursor.getString(11));
            rows.setStudy_the_word(cursor.getString(12)); //study_the_word
            rows.setLanguage(cursor.getString(13)); //language
            rows.setEventListenerUtil(eventListenerUtil);

            cursor.moveToNext();
        }

        if(cursor.getCount()==0){
            rows.setLanguage("");
        }

        cursor.close();
        closeDatabase();
        return rows;

    }

    public List<BookModel> get_list_book_bible_story_lesson(String bookID,elUtil eventListenerUtil){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book_lessons where bookID="+bookID+" AND userID="+sharedData.get_userID(context)+" ORDER BY lessonsID",null);
        cursor.moveToFirst();

        List<BookModel> data = new ArrayList<>();

        while(!cursor.isAfterLast()){
            BookModel rows = new BookModel();
            rows.setLessonsID(cursor.getInt(0)); //lessonsID
            rows.setBookID(cursor.getInt(1)); //bookID
            rows.setLessonNo(cursor.getString(2)); //lessonNo
            rows.setLessonTitle(cursor.getString(3));//lessonTitle
            rows.setLessonSubtitle(cursor.getString(4));//lessonSubtitle
            rows.setContent(cursor.getString(5));//content
            rows.setContent_dark(cursor.getString(6));//content_dark
            rows.setLessonsLogo(cursor.getString(11)); //lessonLogo
            rows.setEventListenerUtil(eventListenerUtil);
            rows.setIs_file_downloaded(true);
            data.add(rows);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return data;

    }

    public BookModel get_lesson_bible_story_lesson(String lessonsID){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book_lessons where lessonsID="+lessonsID+" AND userID="+sharedData.get_userID(context)+" ORDER BY lessonsID",null);
        cursor.moveToFirst();
        BookModel rows = new BookModel();
        while(!cursor.isAfterLast()){
            rows.setLessonsID(cursor.getInt(0)); //lessonsID
            rows.setBookID(cursor.getInt(1)); //bookID
            rows.setLessonNo(cursor.getString(2)); //lessonNo
            rows.setLessonTitle(cursor.getString(3));//lessonTitle
            rows.setLessonSubtitle(cursor.getString(4));//lessonSubtitle
            rows.setContent(cursor.getString(5));//content
            rows.setContent_dark(cursor.getString(6));//content_dark
            rows.setLessonsLogo(cursor.getString(11)); //lessonLogo
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return rows;
    }

    public boolean check_book(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book WHERE bookID="+id+" AND userID="+sharedData.get_userID(context)+" ORDER BY book_name",null);
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
    public boolean check_lessons(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_book_lessons WHERE lessonsID="+id+" AND userID="+sharedData.get_userID(context)+" ORDER BY lessonTitle",null);
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
    public boolean check_material_present(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_present WHERE materialPresentID="+id+ " AND userID="+sharedData.get_userID(context),null);
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
    public Boolean insert_material_present(PresentMaterialsModel data){
        long result=0;
        ContentValues cv = new ContentValues();
        cv.put("materialPresentID",data.getMaterialPresentID());
        cv.put("presentLogo",data.getPresent_Logo());
        cv.put("presentTitle",data.getPresentTitle());
        cv.put("presentSubTitle",data.getPresentSubTitle());
        cv.put("presentLanguage",data.getPresentLanguage());
        cv.put("presentSlides",data.getPresentSlides());
        cv.put("content",data.getAndroid_content_path());
        cv.put("content_dark","");
        cv.put("userID",sharedData.get_userID(context));
        openDatabase();
        result = database.insert("material_present",null,cv);
        //Toast.makeText(context,"You have successfully added the record.",Toast.LENGTH_LONG).show();
        closeDatabase();
        if(result==-1) return false;
        else return true;
    }

    public PresentMaterialsModel get_present(String id){
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM material_present WHERE materialPresentID="+id+ " AND userID=" + sharedData.get_userID(context),null);
        cursor.moveToFirst();

        PresentMaterialsModel data = new PresentMaterialsModel();

        while(!cursor.isAfterLast()){
            data.setMaterialPresentID(cursor.getInt(0));//materialPresentID
            data.setPresent_Logo(cursor.getString(1));//presentLogo
            data.setPresentTitle(cursor.getString(2));//presentTitle
            data.setPresentSubTitle(cursor.getString(3));//presentSubTitle
            data.setPresentLanguage(cursor.getString(4));//presentLanguage
            data.setPresentSlides(cursor.getString(5));//presentSlides
            data.setContent(cursor.getString(6)); //content
            data.setContent_dark(cursor.getString(6)); //content
            data.setIs_file_downloaded(true);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return data;

    }


}
