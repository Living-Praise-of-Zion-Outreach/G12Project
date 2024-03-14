package com.lpzoutreach.g12lpz.Utility.sql.local.holybible;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.utilDatabase;
import java.util.ArrayList;

public class openHelperHolyBible_Default extends SQLiteOpenHelper {
    //HOLY BIBLE - NEW INTERNATIONAL VERSION//
    private final String DBNAME;
    public static final String NIV = "holy-bible-niv.db";
    //public static final String NIV = "CEBFB.SQLite3";
    public static final String ASND = "holy-bible-asnd.db";
    public static final String RCPV = "holy-bible-rcpv-1.1.db";
    public static final String DBPATH = sharedData.DBPATH;
    private Context context;
    private SQLiteDatabase database;
    public openHelperHolyBible_Default(@Nullable Context context, String bible_version_name) {
        super(context, bible_version_name, null, 1);
        this.context = context;
        this.DBNAME = bible_version_name;
        assert context != null;
        if(DBNAME.equals(NIV) || DBNAME.equals(ASND) || DBNAME.equals(RCPV)){
            utilDatabase.checkDatabase(context,DBNAME);
        }else{
            utilDatabase.checkDatabase_other_database(context,DBNAME);
        }

    }

    @Override
    public void onOpen(SQLiteDatabase db) {super.onOpen(db);}
    @Override
    public void onCreate(SQLiteDatabase db) {}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    private void openDatabase(){
        String dbPath;
            dbPath = DBPATH + DBNAME;
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

    public HolyBibleModel get_book_name(String book_no){
        openDatabase();
        HolyBibleModel row = new HolyBibleModel();
        Cursor cursor = database.rawQuery("SELECT * from books where book_number=" + book_no,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            row.setBook_number(cursor.getInt(0));//book_number
            row.setShort_name(cursor.getString(1));//short_name
            row.setLong_name(cursor.getString(2));//long_name
            row.setBook_color(cursor.getString(3));//book_color
            row.setSorting_order(cursor.getInt(4));//sorting_order
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return row;
    }
    public HolyBibleModel get_book_name_no_init(String book_no){
        HolyBibleModel row = new HolyBibleModel();
        Cursor cursor = database.rawQuery("SELECT * from books where book_number=" + book_no,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            row.setBook_number(cursor.getInt(0));//book_number
            row.setShort_name(cursor.getString(1));//short_name
            row.setLong_name(cursor.getString(2));//long_name
            row.setBook_color(cursor.getString(3));//book_color
            row.setSorting_order(cursor.getInt(4));//sorting_order
            cursor.moveToNext();
        }
        cursor.close();
        return row;
    }

    public String get_simbanay_verses(String verses){
            StringBuilder data = new StringBuilder();
            HolyBibleModel book_prop;
            openDatabase();
            Cursor cursor = null;
        if(!verses.equals("")) {
            //SPLITTER//
            String[] list = verses.split("/");
            for (int i = 0; i < list.length; i++) {
                String[] book = list[i].split("~");

                String[] chapter = book[1].split(":");
                book_prop = get_book_name_no_init(String.valueOf(book[0]));

                //THIS IS HEADING VERSE
                data.append("<p class='j' style=\"line-height:30px\"><b>").append(book_prop.getLong_name()).append(" ").append(book[1]).append("</b></p>            <div id=\"word-1\" class=\"verse-box\">");

                String[] num_verses = chapter[1].split("-");
                String verse_from = "";
                String verse_to = "";
                if (num_verses.length > 1) {
                    verse_from = num_verses[0];
                    verse_to = num_verses[1];
                } else {
                    verse_from = num_verses[0];
                    verse_to = num_verses[0];
                }

                int index = 0;
                cursor = database.rawQuery("SELECT * from verses where book_number=" + book[0] + " AND chapter=" + chapter[0] + " AND verse >= " + verse_from + " AND verse <= " + verse_to, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    data.append("<p class=\"j\">").append("<i class=\"\">").append("<span class=\"highlight-verse\">");

                    if (index == 0) {
                        data.append("&nbsp;<sup class=\"text-sm\">").append(cursor.getString(2)).append("</sup>&nbsp;").append(cursor.getString(3).replace("<pb/>", ""));
                        index = 1;
                    } else {
                        data.append("&nbsp;<sup class=\"text-sm\">").append(cursor.getString(2)).append("</sup>&nbsp;").append(cursor.getString(3).replace("<pb/>", ""));
                    }
                    data.append("</span></i></p>");
                    cursor.moveToNext();
                }

                data.append("</div>");

            }

            assert cursor != null;
            cursor.close();
        }

        closeDatabase();
        return data.toString().replace("'","\"");
    }

    public ArrayList<HolyBibleModel> get_list_book_name(String orderType, elUtil eventListenerUtil, String actionEvent, String searchValue){
        String _order="";
        String _where=" WHERE long_name LIKE '%"+searchValue+"%'";

        if(orderType.equals("asc"))
            _order = " ORDER BY long_name ASC";
        else if(orderType.equals("desc"))
            _order = " ORDER BY long_name DESC";
        else
            _order = " ORDER BY sorting_order ASC";

        ArrayList<HolyBibleModel> data = new ArrayList<>();
        openDatabase();

        Cursor cursor = database.rawQuery("SELECT * from books " + _where + _order,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            HolyBibleModel row = new HolyBibleModel();
            row.setBook_number(cursor.getInt(0));//book_number
            row.setShort_name(cursor.getString(1));//short_name
            row.setLong_name(cursor.getString(2));//long_name

            row.setSelected(cursor.getInt(0) == sharedData.get_bible_book_no(context));

            row.setBook_color(cursor.getString(3));//book_color
            row.setSorting_order(cursor.getInt(4));//sorting_order
            row.setEventListenerUtil(eventListenerUtil);
            row.setActionEvent(actionEvent);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public ArrayList<HolyBibleModel> get_list_chapter(elUtil eventListenerUtil, String actionEvent){
        ArrayList<HolyBibleModel> data = new ArrayList<>();
        int book_no = sharedData.get_bible_book_no(context);
        openDatabase();

        //I addded additional introduction
        HolyBibleModel row = new HolyBibleModel();
        row.setChapter(0);
        if(0 == sharedData.get_bible_chapter(context))
            row.setSelected(true);
        else
            row.setSelected(false);
        row.setIntroduction(true);
        row.setEventListenerUtil(eventListenerUtil);
        row.setActionEvent(actionEvent);
        data.add(row);

        Cursor cursor = database.rawQuery("SELECT distinct chapter from verses where book_number="+ book_no + " order by chapter" ,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            row = new HolyBibleModel();

            row.setChapter(cursor.getInt(0));//chapter
            if(cursor.getInt(0) == sharedData.get_bible_chapter(context))
                row.setSelected(true);
            else
                row.setSelected(false);

            row.setEventListenerUtil(eventListenerUtil);
            row.setActionEvent(actionEvent);
            row.setIntroduction(false);
            data.add(row);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public ArrayList<HolyBibleModel> get_list_verse(elUtil eventListenerUtil, String actionEvent){
        ArrayList<HolyBibleModel> data = new ArrayList<>();
        int book_no = sharedData.get_bible_book_no(context);
        int chapter = sharedData.get_bible_chapter(context);
        openDatabase();

        Cursor cursor = database.rawQuery("SELECT * from verses where book_number="+ book_no + " AND chapter="+chapter+" order by verse" ,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            HolyBibleModel rows = new HolyBibleModel();
            rows.setVerse(cursor.getInt(2));
            rows.setText(cursor.getString(3));
            rows.setSelected(cursor.getInt(2) == sharedData.get_bible_verses(context));
            rows.setEventListenerUtil(eventListenerUtil);
            rows.setActionEvent(actionEvent);
            rows.setIntroduction(false);
            data.add(rows);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    @SuppressLint("Recycle")
    public String get_list_verse_web_view(){
        StringBuilder data = new StringBuilder();
        int book_no = sharedData.get_bible_book_no(context);
        int chapter = sharedData.get_bible_chapter(context);
        int header=0;
        int index=0;
        Cursor cursor = null;
        openDatabase();

        if(chapter==0) {
            cursor = database.rawQuery("SELECT * from introductions where book_number=" + book_no, null);
            cursor.moveToFirst();
            if(cursor.getCount()>0){
                while (!cursor.isAfterLast()) {
                    data.append(cursor.getString(1).replace("'","").replace("(","").replace(")","").replace("<a href","<a style=\"display:none\" href"));
                    cursor.moveToNext();
                }
            }else{
                cursor = database.rawQuery("SELECT * from introductions where book_number=0", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    data.append(cursor.getString(1).replace("'","").replace("(","").replace(")","").replace("<a href","<a style=\"display:none\" href"));
                    cursor.moveToNext();
                }
            }

        }else{

            cursor = database.rawQuery("SELECT * from verses where book_number="+ book_no + " AND chapter="+chapter+" order by verse" ,null);
            cursor.moveToFirst();
            int p_index = 0;
            while(!cursor.isAfterLast()){

                String add_story = get_story(cursor.getInt(2));
                if(!add_story.equals(""))
                {
                    add_story.replace("<J>","<i>").
                            replace("</J>","</i>").
                            replace("'","").
                            replace("“","\"").
                            replace("’","").
                            replace("’”","").
                            replace("`","").
                            replaceAll("‘","").
                            replaceAll("“‘","");
                    if(index==0){
                        data.append("<b>").append(add_story).append("&nbsp;</b>");
                        header=1;
                    }else{
                        if(header==0)
                        {
                            data.append("<br><b>").append(add_story).append("&nbsp;</b>");
                            header=1;
                        }else {
                            data.append("<br><b>").append(add_story).append("&nbsp;</b>");
                        }
                    }
                }

                if(cursor.getString(3) == null){
                    //IN CASE IF THE VERSE TEXT IS NULL
                    data.append("<span id=\"sp-").append(cursor.getInt(2)).append("\" onclick=\"selectText(this)\"><sup class=\"text-sm\">").append(cursor.getInt(2)).append("</sup></span>");
                }else{
                    String  text = cursor.getString(3).replaceAll("<J>","<i>");
                            text = text.replaceAll("</J>","</i>");
                            text = text.replaceAll("'","`");
                            text = text.replaceAll("“","\"");
                            text = text.replaceAll("’”","");
                            text = text.replaceAll("`","");
                            text = text.replaceAll("‘","");
                            text = text.replaceAll("“‘","");
                    String[] pb = text.split("<pb/>");
                    if(pb.length>1){
                        if(index==0 && header==0){
                            data.append("<span id=\"sp-").append(cursor.getInt(2)).append("\" onclick=\"selectText(this)\"><sup class=\"text-sm\">").append(cursor.getInt(2)).append("</sup> ").append(text).append(" &nbsp;</span>");
                            p_index+=1;
                        }else {
                            data.append("<span id=\"sp-").append(cursor.getInt(2)).append("\" onclick=\"selectText(this)\"><br><sup class=\"text-sm\">").append(cursor.getInt(2)).append("</sup> ").append(text).append(" &nbsp;</span>");
                            p_index+=1;
                        }
                    }else{
                        if(header==1){
                            data.append("<span id=\"sp-").append(cursor.getInt(2)).append("\" onclick=\"selectText(this)\"><br><sup class=\"text-sm\">").append(cursor.getInt(2)).append("</sup> ").append(text).append(" &nbsp;</span>");
                            p_index+=1;
                            header=0;
                        }else{
                            data.append("<span id=\"sp-").append(cursor.getInt(2)).append("\" onclick=\"selectText(this)\"><sup class=\"text-sm\">").append(cursor.getInt(2)).append("</sup> ").append(text).append(" &nbsp;</span>");
                            p_index+=1;
                        }
                    }
                }
                index=1;
                cursor.moveToNext();
            }
        }

        cursor.close();
        closeDatabase();

        //Log.v("HTMLWEBVIEW",data.toString());

        return data.toString();
    }

    private String get_story(int verse){
        String data = "";
        int book_no = sharedData.get_bible_book_no(context);
        int chapter = sharedData.get_bible_chapter(context);
        openDatabase();

        Cursor cursor = database.rawQuery("SELECT title from stories where book_number="+ book_no + " AND chapter="+chapter+" AND verse="+verse+" order by order_if_several" ,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            data = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return data;
    }

    public int get_total_chapters(){
        int book_no = sharedData.get_bible_book_no(context);
        int total=0;
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT distinct chapter from verses where book_number=" + book_no + "  ",null);
        cursor.moveToFirst();
        total = cursor.getCount();
        cursor.close();
        closeDatabase();
        return total;
    }

    public String get_book_name_by_string(){
        openDatabase();
        int book_no=sharedData.get_bible_book_no(context);
        String book_name="";
        Cursor cursor = database.rawQuery("SELECT * from books where book_number=" + book_no,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            book_name = cursor.getString(2);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return book_name;
    }

    public String get_chapter_all_verses_life_class(String book_no, String chapter){
        HolyBibleModel book_name = get_book_name(book_no);

        openDatabase();
        StringBuilder value= new StringBuilder();
        Cursor cursor = database.rawQuery("SELECT * from verses where book_number=" + book_no + " AND chapter=" + chapter,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
                value.append("<sup class=\"text-sm\">").append(cursor.getString(2)).append("</sup> ").append(cursor.getString(3)).append("<br><br>");
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return value.toString();
    }

    public ArrayList<HolyBibleModel> get_verse_text_per_chapter(){
        int book_no = sharedData.get_bible_book_no(context);
        int chapter = sharedData.get_bible_chapter(context);
        ArrayList<HolyBibleModel> data = new ArrayList<>();

        Log.v("QUERY","SELECT * from verses where book_number=" + book_no + " AND chapter= "+chapter + " ORDER BY verse");

        Cursor cursor = null;
        openDatabase();
            cursor = database.rawQuery("SELECT * from verses where book_number=" + book_no + " AND chapter= "+chapter + " ORDER BY verse",null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                HolyBibleModel row = new HolyBibleModel();
                row.setBook_number(cursor.getInt(0));
                row.setChapter(cursor.getInt(1));
                row.setVerse(cursor.getInt(2));
                row.setText(cursor.getString(3));
                data.add(row);
                cursor.moveToNext();
            }

        cursor.close();
        closeDatabase();
        return data;
    }

    public String get_info(){
        String info="";
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * from info",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(0).equals("detailed_info"))
                info = cursor.getString(1);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return info;
    }

    public String get_description(){
        String info="";
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * from info",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(0).equals("description"))
                info = cursor.getString(1);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return info;
    }

    public String get_copyright(){
        String info="";
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * from introductions where book_number=0",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            info = cursor.getString(1);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return info;
    }




}
