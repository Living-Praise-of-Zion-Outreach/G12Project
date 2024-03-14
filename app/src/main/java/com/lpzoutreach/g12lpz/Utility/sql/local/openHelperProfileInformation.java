package com.lpzoutreach.g12lpz.Utility.sql.local;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import androidx.annotation.Nullable;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.FamilyBackgroundModel;
import com.lpzoutreach.g12lpz.Models.PersonalInformationModel;
import com.lpzoutreach.g12lpz.Models.UsersModel;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import java.util.ArrayList;
import java.util.List;

public class openHelperProfileInformation extends SQLiteOpenHelper
    {
        public static final String DBNAME = sharedData.DBNAME;
        public static final String DBPATH = sharedData.DBPATH;
        private Context context;
        private PersonalInformationModel data;
        private SQLiteDatabase database;

        public openHelperProfileInformation(@Nullable Context context) {
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

        public int get_userID(){
        int userID = sharedData.get_userID(context);
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT userID from profile where userID=" + userID,null);
        cursor.moveToFirst();
        int value = 0;
        while (!cursor.isAfterLast()){
            value = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return value;
    }

        public PersonalInformationModel get_Profile(){
            int userID = get_userID();
            PersonalInformationModel data;
            data = new PersonalInformationModel();

            openDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM profile WHERE userID=" + String.valueOf(userID),null);
            cursor.moveToFirst();
            String value = null;
            while(!cursor.isAfterLast()){

                data.setProfileID(cursor.getInt(0));//profileID
                data.setUserID(cursor.getInt(1));//userID
                data.setCuserID(cursor.getString(2));//cuserID
                data.setAbout(cursor.getString(3));//about
                data.setFirst_name(cursor.getString(4));//first_name
                data.setMiddle_name(cursor.getString(5));//middle_name
                data.setLast_name(cursor.getString(6));//last_name
                data.setName_ext(cursor.getString(7));//name_ext
                data.setNickname(cursor.getString(8));//nickname

                data.setDate_of_birth(cursor.getString(9));//date_of_birth
                data.setPlace_of_birth(cursor.getString(10));//place_of_birth
                data.setSex(cursor.getString(11));//sex
                data.setCivil_status(cursor.getString(12));//civil_status
                data.setOccupation(cursor.getString(13));//occupation

                data.setHeight(cursor.getString(14));//height
                data.setHeight_metric(cursor.getString(15));//height_metric
                data.setWeight(cursor.getString(16));//weight
                data.setWeight_metric(cursor.getString(17));//weight_metric
                data.setBlood_type(cursor.getString(18));//blood_type

                data.setLoc_country(cursor.getString(19));//loc_country
                data.setLoc_province(cursor.getString(20));//loc_province
                data.setLoc_city(cursor.getString(21));//loc_city
                data.setLoc_barangay(cursor.getString(22));//loc_barangay
                data.setLoc_address(cursor.getString(23));//loc_address
                data.setLoc_zipcode(cursor.getString(24));//loc_zipcode

                data.setCon_mobile_no(cursor.getString(25));//con_mobile_no
                data.setCon_tel_no(cursor.getString(26));//con_tel_no

                data.setEduc_elem(cursor.getString(27));//educ_elem
                data.setEduc_elem_year_graduated(cursor.getString(28)); //educ_elem_year_graduated
                data.setEduc_high_school(cursor.getString(29));  //educ_high_school
                data.setEduc_high_school_graduated(cursor.getString(30)); //educ_high_school_graduated
                data.setEduc_college(cursor.getString(31)); //educ_college
                data.setEduc_college_graduate(cursor.getString(32));//educ_college_graduate
                data.setEduc_attainment(cursor.getString(33));//educ_attainment
                data.setEduc_course(cursor.getString(34));//educ_course

                data.setSoc_facebook_url(cursor.getString(35));//soc_facebook_url
                data.setSoc_youtube_url(cursor.getString(36)); //soc_youtube_url
                data.setSoc_instagram_url(cursor.getString(37)); //soc_instagram_url
                data.setSoc_linkin_url(cursor.getString(38)); //soc_linkin_url
                data.setSoc_tiktok_url(cursor.getString(39)); //soc_tiktok_url
                data.setSoc_twitter_url(cursor.getString(40)); //soc_twitter_url
                data.setOcc_name_of_employer(cursor.getString(41));//occ_name_of_employer
                data.setOcc_occupation(cursor.getString(42));//occ_occupation
                data.setOcc_address(cursor.getString(43));//occ_address
                data.setPrivacy_settings(cursor.getString(44));//privacy_settings

                cursor.moveToNext();
            }

            cursor = database.rawQuery("SELECT * FROM users WHERE userID=" + String.valueOf(userID),null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                data.setProfile_photo(cursor.getString(6));
                data.setAndroid_photo_path(cursor.getString(7));
                data.setBitmap_photo(cursor.getString(8));
                cursor.moveToNext();
            }

            cursor.close();
            closeDatabase();
            return data;
        }

        private ContentValues setData(PersonalInformationModel data){

            ContentValues cv = new ContentValues();
            cv.put("profileID",data.getProfileID());
            cv.put("userID",data.getUserID());
            cv.put("cuserID",data.getCuserID());
            cv.put("about",data.getAbout());
            cv.put("first_name",data.getFirst_name());
            cv.put("middle_name",data.getMiddle_name());
            cv.put("last_name",data.getLast_name());
            cv.put("name_ext",data.getName_ext());
            cv.put("nickname",data.getNickname());
            cv.put("date_of_birth",data.getDate_of_birth());
            cv.put("place_of_birth",data.getPlace_of_birth());
            cv.put("sex",data.getSex());
            cv.put("civil_status",data.getCivil_status());
            cv.put("occupation",data.getOccupation());
            cv.put("height",data.getHeight());
            cv.put("height_metric",data.getHeight_metric());
            cv.put("weight",data.getWeight());
            cv.put("weight_metric",data.getWeight_metric());
            cv.put("blood_type",data.getBlood_type());
            //cv.put("chu_churchID",data.getChu_churchID());
            //cv.put("chu_church_name",data.getChu_church_name());
            //cv.put("chu_cellLeaderID",data.getChu_cellLeaderID());
            //cv.put("chu_cellLeader",data.getChu_cellLeader());
            //cv.put("chu_roleID",data.getChu_roleID());
            //cv.put("chu_roleName",data.getChu_roleName());
            cv.put("loc_country",data.getLoc_country());
            cv.put("loc_province",data.getLoc_province());
            cv.put("loc_city",data.getLoc_city());
            cv.put("loc_barangay",data.getLoc_barangay());
            cv.put("loc_address",data.getLoc_address());
            cv.put("loc_zipcode",data.getLoc_zipcode());
            cv.put("con_mobile_no",data.getCon_mobile_no());
            cv.put("con_tel_no",data.getCon_tel_no());
            cv.put("educ_elem",data.getEduc_elem());
            cv.put("educ_elem_year_graduated",data.getEduc_elem_year_graduated());
            cv.put("educ_high_school",data.getEduc_high_school());
            cv.put("educ_high_school_graduated",data.getEduc_high_school_graduated());
            cv.put("educ_college",data.getEduc_college());
            cv.put("educ_college_graduate",data.getEduc_college_graduate());
            cv.put("educ_attainment",data.getEduc_attainment());
            cv.put("educ_course",data.getEduc_course());
            cv.put("soc_facebook_url",data.getSoc_facebook_url());
            cv.put("soc_youtube_url",data.getSoc_youtube_url());
            cv.put("soc_instagram_url",data.getSoc_instagram_url());
            cv.put("soc_linkin_url",data.getSoc_linkin_url());
            cv.put("soc_tiktok_url",data.getSoc_tiktok_url());
            cv.put("soc_twitter_url",data.getSoc_twitter_url());
            cv.put("occ_name_of_employer",data.getOcc_name_of_employer());
            cv.put("occ_occupation",data.getOcc_occupation());
            cv.put("occ_address",data.getOcc_address());
            cv.put("privacy_settings",data.getPrivacy_settings());
            cv.put("dtUpdated",data.getDtUpdated());

            return cv;
        }
        public Boolean get_profile_info(PersonalInformationModel data){

            ContentValues cv = setData(data);

            long result = 0;
            int userID = get_userID();
            openDatabase();

            if(userID<=0){
                cv.put("userID", data.getUserID());
                result = database.insert("profile",null,cv);
            }
            else{
                result = database.update("profile",cv,"userID = " + String.valueOf(userID),null );
            }

            closeDatabase();
            if(result==-1){
                return false;
            }

            else {
                return true;
            }
        }

        public boolean update_basic_information(PersonalInformationModel data ){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();
            cv.put("about",data.getAbout());
            cv.put("nickname",data.getNickname());
            cv.put("first_name",data.getFirst_name());
            cv.put("middle_name",data.getMiddle_name());
            cv.put("last_name",data.getLast_name());
            cv.put("name_ext",data.getName_ext());
            cv.put("date_of_birth",data.getDate_of_birth());
            cv.put("place_of_birth",data.getPlace_of_birth());
            cv.put("sex",data.getSex());
            cv.put("civil_status",data.getCivil_status());

            cv.put("height",data.getHeight());
            cv.put("height_metric",data.getHeight_metric());
            cv.put("weight",data.getWeight());
            cv.put("weight_metric",data.getWeight_metric());

            cv.put("blood_type",data.getBlood_type());

            openDatabase();
            result = database.update("profile",cv,"userID = " + sharedData.get_userID(context),null );
            //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

            closeDatabase();

            if(result==-1) return false;
            else return true;
        }

        public boolean update_address_information(PersonalInformationModel data ){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();
            cv.put("loc_country",data.getLoc_country());
            cv.put("loc_province",data.getLoc_province());
            cv.put("loc_city",data.getLoc_city());
            cv.put("loc_barangay",data.getLoc_barangay());
            cv.put("loc_address",data.getLoc_address());
            cv.put("loc_zipcode",data.getLoc_zipcode());

            openDatabase();
            result = database.update("profile",cv,"userID = " + sharedData.get_userID(context),null );
            //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

            closeDatabase();

            if(result==-1) return false;
            else return true;
        }

        public boolean update_contact_information(PersonalInformationModel data ){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();
            cv.put("con_mobile_no",data.getCon_mobile_no());
            cv.put("con_tel_no",data.getCon_tel_no());

            openDatabase();
            result = database.update("profile",cv,"userID = " + sharedData.get_userID(context),null );
            //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

            closeDatabase();

            if(result==-1) return false;
            else return true;
        }

        public boolean update_social_information(PersonalInformationModel data ){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();
            cv.put("soc_facebook_url",data.getSoc_facebook_url());
            cv.put("soc_youtube_url",data.getSoc_youtube_url());
            cv.put("soc_instagram_url",data.getSoc_instagram_url());
            cv.put("soc_linkin_url",data.getSoc_linkin_url());
            cv.put("soc_tiktok_url",data.getSoc_tiktok_url());
            cv.put("soc_twitter_url",data.getSoc_twitter_url());

            openDatabase();
            result = database.update("profile",cv,"userID = " + sharedData.get_userID(context),null );
            //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

            closeDatabase();

            if(result==-1) return false;
            else return true;
        }

        public boolean update_education_information(PersonalInformationModel data ){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();

            cv.put("educ_attainment",data.getEduc_attainment());
            cv.put("educ_elem",data.getEduc_elem());
            cv.put("educ_elem_year_graduated",data.getEduc_elem_year_graduated());
            cv.put("educ_high_school", data.getEduc_high_school());
            cv.put("educ_high_school_graduated", data.getEduc_high_school_graduated());
            cv.put("educ_college", data.getEduc_college());
            cv.put("educ_college_graduate", data.getEduc_college_graduate());
            cv.put("educ_course", data.getEduc_course());

            openDatabase();
            result = database.update("profile",cv,"userID = " + sharedData.get_userID(context),null );
            //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

            closeDatabase();

            if(result==-1) return false;
            else return true;
        }

        public void update_work_information(PersonalInformationModel data ){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();

            cv.put("occ_name_of_employer",data.getOcc_name_of_employer());
            cv.put("occ_occupation",data.getOcc_occupation());
            cv.put("occ_address",data.getOcc_address());

            openDatabase();
            result = database.update("profile",cv,"userID = " + sharedData.get_userID(context),null );
            //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

            closeDatabase();


        }

        public boolean update_photo_information(PersonalInformationModel data ){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();
            cv.put("profile_photo_path",data.getProfile_photo());
            cv.put("android_photo_path",data.getAndroid_photo_path());
            cv.put("bitmap_photo",data.getBitmap_photo());

            openDatabase();
            result = database.update("users",cv,"userID = " + sharedData.get_userID(context),null );
            //Toast.makeText(context,"You have successfully updated the record.",Toast.LENGTH_LONG).show();

            closeDatabase();

            if(result==-1) return false;
            else return true;
        }

        private boolean check_user_if_exist(String id){
            openDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM users WHERE userID=" + id,null);
            cursor.moveToFirst();
            closeDatabase();
            if(cursor.getCount()>0){
                cursor.close();
                return true;
            }else{
                cursor.close();
                return false;
            }
        }

        public boolean offline_login(UsersModel data){

            openDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM users WHERE email='" + data.getEmail_address() + "' AND password '" + data.getPassword() + "'",null);
            cursor.moveToFirst();
            closeDatabase();
            if(cursor.getCount()>0){
                cursor.close();
                return true;
            }else{
                cursor.close();
                return false;
            }
            
        }
        public void insert_users(UsersModel data){

                int userID = data.getUserID();
                long result=0;

                ContentValues cv = new ContentValues();

                cv.put("userID",data.getUserID());
                cv.put("userCode", data.getUserCode());
                cv.put("userLevel",data.getUserLevel());
                cv.put("username",data.getUsername());
                cv.put("password",data.getPassword());
                cv.put("email",data.getEmail_address());
                cv.put("profile_photo_path",data.getProfile_photo_path());
                cv.put("android_photo_path",data.getAndroid_photo_path());
                cv.put("bitmap_photo",data.getBitmap_photo());

                if(!check_user_if_exist(String.valueOf(userID))){
                    cv.put("userID", data.getUserID());
                    openDatabase();
                    result = database.insert("users",null,cv);
                }else{
                    openDatabase();
                    result = database.update("users",cv,"userID = " + sharedData.get_userID(context),null );
                }
                closeDatabase();

        }

        public void insert_family(FamilyBackgroundModel data){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();

            cv.put("userID",userID);
            cv.put("profile_photo_path",data.getProfile_photo_path());
            cv.put("android_photo_path",data.getAndroid_photo_path());
            cv.put("photo_bitmap",data.getPhoto_bitmap());
            cv.put("relationship",data.getRelationship());
            cv.put("rel_profileID",0);
            cv.put("rel_name",data.getRel_name());
            cv.put("rel_age",data.getRel_age());
            cv.put("rel_birthdate",data.getRel_birthdate());
            cv.put("rel_occupation",data.getRel_occupation());
            cv.put("rel_contact_no",data.getRel_contact_no());
            cv.put("rel_condition",data.getRel_condition());
            cv.put("dtCreated",utilDate.getCurrentDateTime());

            openDatabase();

            database.insert("profile_family_background",null,cv);

            closeDatabase();

        }

        public void update_family(FamilyBackgroundModel data,String id){
            int userID = sharedData.get_userID(context);
            long result=0;

            ContentValues cv = new ContentValues();

            cv.put("userID",userID);
            cv.put("profile_photo_path",data.getProfile_photo_path());
            cv.put("android_photo_path",data.getAndroid_photo_path());
            cv.put("photo_bitmap",data.getPhoto_bitmap());
            cv.put("relationship",data.getRelationship());
            cv.put("rel_profileID",0);
            cv.put("rel_name",data.getRel_name());
            cv.put("rel_age",data.getRel_age());
            cv.put("rel_birthdate",data.getRel_birthdate());
            cv.put("rel_occupation",data.getRel_occupation());
            cv.put("rel_contact_no",data.getRel_contact_no());
            cv.put("rel_condition",data.getRel_condition());
            cv.put("dtCreated",utilDate.getCurrentDateTime());

            openDatabase();
            database.update("profile_family_background",cv,"familyBackgroundID = " + id,null );
            closeDatabase();

        }

        public boolean delete_family(String id){
            long result=0;
            openDatabase();
            result = database.delete("profile_family_background","familyBackgroundID="+id,null );
            closeDatabase();
            if(result==-1) return false;
            else return true;
        }

        public boolean delete_all_family(String id){
            long result=0;
            openDatabase();
            result = database.delete("profile_family_background","userID="+id,null );
            closeDatabase();
            if(result==-1) return false;
            else return true;
        }

        public FamilyBackgroundModel get_family_info(String familyBackgroundID){
            FamilyBackgroundModel data;
            data = new FamilyBackgroundModel();

            openDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM profile_family_background WHERE familyBackgroundID=" + String.valueOf(familyBackgroundID),null);
            cursor.moveToFirst();
            String value = null;
            while(!cursor.isAfterLast()){

                data.setFamilyBackgroundID(cursor.getInt(0)); //familyBackgroundID
                data.setUserID(cursor.getInt(1)); //userID
                data.setProfile_photo_path(cursor.getString(2)); //profile_photo_path
                data.setAndroid_photo_path(cursor.getString(3));  //android_photo_path
                data.setPhoto_bitmap(cursor.getString(4));  //photo_bitmap
                data.setRelationship(cursor.getString(5));  //relationship
                data.setRel_profileID(cursor.getInt(6));  //rel_profileID
                data.setRel_name(cursor.getString(7));  //rel_name
                data.setRel_age(cursor.getInt(8));  //rel_age
                data.setRel_birthdate(cursor.getString(9));  //rel_birthdate
                data.setRel_occupation(cursor.getString(10));  //rel_occupation
                data.setRel_contact_no(cursor.getString(11));  //rel_contact_no
                data.setRel_condition(cursor.getString(13));  //rel_condition
                data.setDtCreated(cursor.getString(14)); //dtCreated

                cursor.moveToNext();
            }
            cursor.close();
            closeDatabase();
            return data;
        }

        public int get_family_list_count(elUtil events,String searchName){
            int userID = get_userID();

            List<FamilyBackgroundModel> data = new ArrayList<>();
            String where = " AND (rel_name like '%"+searchName+"%' OR relationship like '%"+searchName+"%')";

            openDatabase();
            Cursor cursor = database.rawQuery("SELECT count(*) FROM profile_family_background WHERE userID=" + String.valueOf(userID) + where + " ORDER BY rel_name",null);
            cursor.moveToFirst();
            int total=0;
            while(!cursor.isAfterLast()) {
                total = cursor.getInt(0);
            cursor.moveToNext();
            }

            cursor.close();
            closeDatabase();



            return total;
        }
        public List<FamilyBackgroundModel> get_family_list(elUtil events,String searchName){
            int userID = get_userID();

            List<FamilyBackgroundModel> data = new ArrayList<>();
            String where = " AND (rel_name like '%"+searchName+"%' OR relationship like '%"+searchName+"%')";

            openDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM profile_family_background WHERE userID=" + String.valueOf(userID) + where + " ORDER BY rel_name",null);
            cursor.moveToFirst();
            String value = null;
            while(!cursor.isAfterLast()){

                FamilyBackgroundModel row = new FamilyBackgroundModel();

                row.setFamilyBackgroundID(cursor.getInt(0)); //familyBackgroundID
                row.setUserID(cursor.getInt(1)); //userID
                row.setProfile_photo_path(cursor.getString(2)); //profile_photo_path
                row.setAndroid_photo_path(cursor.getString(3));  //android_photo_path
                row.setPhoto_bitmap(cursor.getString(4));  //photo_bitmap
                row.setRelationship(cursor.getString(5));  //relationship
                row.setRel_profileID(cursor.getInt(6));  //rel_profileID
                row.setRel_name(cursor.getString(7));  //rel_name
                row.setRel_age(cursor.getInt(8));  //rel_age
                row.setRel_birthdate(cursor.getString(9));  //rel_birthdate
                row.setRel_occupation(cursor.getString(10));  //rel_occupation
                row.setRel_contact_no(cursor.getString(11));  //rel_contact_no
                row.setRel_condition(cursor.getString(13));  //rel_condition
                row.setDtCreated(cursor.getString(14)); //dtCreated
                row.setEventListenerUtil(events);

                data.add(row);

                cursor.moveToNext();
            }
            cursor.close();
            closeDatabase();
            return data;
        }

    }
