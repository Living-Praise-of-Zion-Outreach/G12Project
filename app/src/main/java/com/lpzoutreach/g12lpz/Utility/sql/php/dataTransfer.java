package com.lpzoutreach.g12lpz.Utility.sql.php;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.Activity.SplashScreen;
import com.lpzoutreach.g12lpz.Models.FamilyBackgroundModel;
import com.lpzoutreach.g12lpz.Models.PersonalInformationModel;
import com.lpzoutreach.g12lpz.Models.UsersModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.object.dynamicObjects;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperProfileInformation;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dataTransfer {

    public static void getProfileInformationBrowse(Context context, String userID, WebView webView, ConstraintLayout no_signal, ConstraintLayout loading, RelativeLayout body, LinearLayout obj) {
        String url = accessUrl.BASE_URL + "android/profile/" + userID;
        if (utilNetwork.isConnected(context)) {

            body.setVisibility(View.GONE);
            no_signal.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject values = response.getJSONObject("data");
                        String response_text = response.getString("response");
                        if (response_text.equals("success")) {

                            dynamicObjects.create_title_label(context,obj,values.getString("about"),"About");

                            dynamicObjects.create_separator(context,obj);

                            String[] dateofbirth = values.getString("date_of_birth").split("-");
                            if(dateofbirth.length>2){
                                dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_calendar,utilDate.getFromServer_DatePickerFormat(values.getString("date_of_birth")),context.getString(R.string.hint_date_of_birth));
                            }else{
                                dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_calendar,values.getString("date_of_birth"),context.getString(R.string.hint_date_of_birth));
                            }

                            dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_place_of_birth, values.getString("place_of_birth"),context.getString(R.string.hint_place_of_birth));
                            dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_gender,values.getString("sex"),context.getString(R.string.hint_gender));
                            dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_family,values.getString("civil_status"),context.getString(R.string.hint_civil_status));
                            dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_height,values.getString("height") + " " + values.getString("height_metric"),context.getString(R.string.hint_height));
                            dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_weight,values.getString("weight") + " " + values.getString("weight_metric"),context.getString(R.string.hint_weight));
                            dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_blood_type,values.getString("blood_type"),context.getString(R.string.hint_blood_type));

                            String full_address = values.getString("loc_address")
                                    + ", " + values.getString("loc_barangay") +
                                    ", " + values.getString("loc_city") +
                                    ", " + values.getString("loc_province") +
                                    ", " + values.getString("loc_zipcode") +
                                    ", " + values.getString("loc_country");

                            dynamicObjects.create_separator(context,obj);
                            dynamicObjects.create_header(context,obj,"Permanent Address");
                            dynamicObjects.create_icon_title_label(context,obj,R.drawable.ic_icon_location,full_address,context.getString(R.string.hint_address));

                            getPersonById_Profile_Browse(context,userID,no_signal,loading,values,body,obj);


                        }
                    } catch (JSONException e) {
                        Log.v("Data", e.toString());
                        webView.setVisibility(View.GONE);
                        loading.setVisibility(View.GONE);
                        no_signal.setVisibility(View.VISIBLE);
                    }
                }
            }, error -> {
                Log.v("Data", error.toString());
                webView.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                no_signal.setVisibility(View.VISIBLE);
                utilNetwork.volleyError(context, error);
            });
            requestQueue.add(jsonObjectRequest);
        }else{
            webView.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            no_signal.setVisibility(View.VISIBLE);
        }
    }

    private static void getFamilyBackground_Profile_Browse(Context context,String userID,ConstraintLayout no_signal, ConstraintLayout loading, JSONObject values, RelativeLayout body, LinearLayout linearObject){
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/profile/family/get-family-list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_FAMILY",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);

                            dynamicObjects.create_separator(context,linearObject);
                            dynamicObjects.create_header(context,linearObject,"Family Background");

                            for (int i = 0; i < j.length(); i++) {
                              JSONObject obj = j.getJSONObject(i);
                              String photo_url= accessUrl.BASE_URL + obj.getString("profile_photo_path");
                              dynamicObjects.create_icon_title_label(context,linearObject,photo_url, obj.getString("rel_name"),obj.getString("relationship") );
                            }

                            dynamicObjects.create_separator(context,linearObject);
                            dynamicObjects.create_header(context,linearObject,"Contact Information");

                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_mobile_no, values.getString("con_mobile_no"),context.getString(R.string.hint_mobile_no) );
                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_tel, values.getString("con_tel_no"),context.getString(R.string.hint_tel_no) );

                            dynamicObjects.create_separator(context,linearObject);
                            dynamicObjects.create_header(context,linearObject,"Social Information");

                            if(!values.getString("soc_facebook_url").isEmpty())
                                dynamicObjects.create_icon_title_label_no_svg(context,linearObject,R.drawable.social_facebook, values.getString("soc_facebook_url"),context.getString(R.string.hint_facebook) );
                            if(!values.getString("soc_youtube_url").isEmpty())
                                dynamicObjects.create_icon_title_label_no_svg(context,linearObject,R.drawable.social_youtube, values.getString("soc_youtube_url"),context.getString(R.string.hint_youtube) );
                            if(!values.getString("soc_instagram_url").isEmpty())
                                dynamicObjects.create_icon_title_label_no_svg(context,linearObject,R.drawable.social_instagram, values.getString("soc_instagram_url"),context.getString(R.string.hint_instagram) );
                            if(!values.getString("soc_tiktok_url").isEmpty())
                                dynamicObjects.create_icon_title_label_no_svg(context,linearObject,R.drawable.social_tiktok, values.getString("soc_tiktok_url"),context.getString(R.string.hint_tiktok) );
                            if(!values.getString("soc_linkin_url").isEmpty())
                                dynamicObjects.create_icon_title_label_no_svg(context,linearObject,R.drawable.social_linkedin, values.getString("soc_linkin_url"),context.getString(R.string.hint_linkin) );
                            if(!values.getString("soc_twitter_url").isEmpty())
                                dynamicObjects.create_icon_title_label_no_svg(context,linearObject,R.drawable.social_twitter, values.getString("soc_twitter_url"),context.getString(R.string.hint_twitter) );

                            dynamicObjects.create_separator(context,linearObject);
                            dynamicObjects.create_header(context,linearObject,"Educational Background");

                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_edu_attain, values.getString("educ_attainment"),context.getString(R.string.hint_educational_attainment) );
                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_graduate, values.getString("educ_elem"),context.getResources().getString(R.string.hint_date_graduated) + " " +  utilDate.getFromServer_DatePickerFormat(values.getString("educ_elem_year_graduated")));
                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_graduate, values.getString("educ_high_school"),context.getResources().getString(R.string.hint_date_graduated) + " " +  utilDate.getFromServer_DatePickerFormat(values.getString("educ_high_school_graduated")));
                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_graduate, values.getString("educ_college"),context.getResources().getString(R.string.hint_date_graduated) + " " +  utilDate.getFromServer_DatePickerFormat(values.getString("educ_college_graduate")));
                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_course, values.getString("educ_course"),context.getResources().getString(R.string.hint_course));

                            dynamicObjects.create_separator(context,linearObject);
                            dynamicObjects.create_header(context,linearObject,"Work Background");

                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_person, values.getString("occ_name_of_employer"),context.getResources().getString(R.string.hint_name_of_employer));
                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_course, values.getString("occ_occupation"),context.getResources().getString(R.string.hint_occupation));
                            dynamicObjects.create_icon_title_label(context,linearObject,R.drawable.ic_icon_location, values.getString("occ_address"),context.getResources().getString(R.string.hint_employer_address));

                            body.setVisibility(View.VISIBLE);
                            no_signal.setVisibility(View.GONE);
                            loading.setVisibility(View.GONE);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", userID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
    }

    public static void getPersonById_Profile_Browse_MembersG12NtworkBrowser(Context context, String userID,TextView leader, TextView organization ){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        leader.setText("...");
        organization.setText("...");
        String url = accessUrl.BASE_URL + "android/members/get-person-info-from-id";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try{
                        JSONArray j= new JSONArray(response);
                        for (int i = 0; i < j.length(); i++) {
                            JSONObject obj = j.getJSONObject(i);
                            leader.setText(obj.getString("leader_name"));
                            organization.setText(obj.getString("networkName"));
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("userID", userID);
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }
    private static void getPersonById_Profile_Browse(Context context, String userID,ConstraintLayout no_signal, ConstraintLayout loading,JSONObject values,RelativeLayout body, LinearLayout linearObject ){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL + "android/members/get-person-info-from-id";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try{
                        JSONArray j= new JSONArray(response);

                        dynamicObjects.create_separator(context,linearObject);
                        dynamicObjects.create_header(context,linearObject,"Ministry Information");

                        for (int i = 0; i < j.length(); i++) {
                            JSONObject obj = j.getJSONObject(i);
                            dynamicObjects.create_icon_title_label(context,linearObject,obj.getString("church_photo"),obj.getString("church_name"), "Church Name");
                            dynamicObjects.create_icon_title_label(context,linearObject,obj.getString("networkLogo"),obj.getString("networkName"), "Network Name");
                            dynamicObjects.create_icon_title_label(context,linearObject,obj.getString("leader_photo"),obj.getString("leader_name"), "Network Leader");
                            dynamicObjects.create_icon_title_label(context,linearObject,obj.getString("invite_logo"),obj.getString("leader_name"), "Invited By");
                        }

                        getFamilyBackground_Profile_Browse(context,userID,no_signal,loading,values,body, linearObject);


                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("userID", userID);
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }


    public static void getProfileBrowse(Context context, String userID, ImageView logo, TextView name, TextView nickname,  ProgressDialogBar progressDialogBar) {
        String url = accessUrl.BASE_URL + "android/profile/" + userID;
            if (utilNetwork.isConnected(context)) {
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject values = response.getJSONObject("data");
                            String response_text = response.getString("response");
                            if (response_text.equals("success")) {
                                String[] logo_checker = values.getString("profile_photo_path").split("/");
                                if(logo_checker[0].equals("assets")){
                                    if(!values.getString("profile_photo_path").isEmpty()){
                                        Picasso.get().load(accessUrl.BASE_URL + values.getString("profile_photo_path"))
                                                .placeholder(context.getDrawable(R.drawable.default_photo))
                                                .error(context.getDrawable(R.drawable.default_photo))
                                                .into(logo);
                                    }else{
                                        logo.setImageResource(R.drawable.default_photo);
                                    }
                                }else{
                                    if(!values.getString("profile_photo_path").isEmpty()){
                                        Picasso.get().load(values.getString("profile_photo_path"))
                                                .placeholder(context.getDrawable(R.drawable.default_photo))
                                                .error(context.getDrawable(R.drawable.default_photo))
                                                .into(logo);
                                    }else{
                                        logo.setImageResource(R.drawable.default_photo);
                                    }
                                }

                              name.setText(values.getString("first_name") + " " + values.getString("middle_name") + " " + values.getString("last_name") + " " + values.getString("name_ext"));
                              nickname.setText(values.getString("nickname"));
                              progressDialogBar.hide();
                            }
                        } catch (JSONException e) {
                            Log.v("Data", e.toString());
                            Toast.makeText(context, "JsonError" + e, Toast.LENGTH_LONG).show();
                            progressDialogBar.hide();
                            Intent intent = new Intent(context, SplashScreen.class);
                            context.startActivity(intent);
                        }
                    }
                }, error -> {
                    Log.v("Data", error.toString());
                    utilNetwork.volleyError(context, error);
                });
                requestQueue.add(jsonObjectRequest);
            }else{
                progressDialogBar.hide();
            }
    }

    public static void syncProfileInformation(Context context,ProgressDialogBar progressDialogBar) {

        String url = accessUrl.BASE_URL + "android/profile/" + sharedData.get_userID(context);

        //Log.v("URL", url);

        if (!sharedData.is_Profile_Sync(context)) {

            if (utilNetwork.isConnected(context)) {

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.v("CONVERSION", response.toString());

                            JSONObject values = response.getJSONObject("data");
                            String response_text = response.getString("response");

                            if (response_text.equals("success")) {

                                PersonalInformationModel data = new PersonalInformationModel();

                                data.setProfileID(values.getInt("profileID"));
                                data.setUserID(values.getInt("userID"));
                                data.setCuserID(values.getString("cuserID"));
                                data.setAbout(values.getString("about"));
                                data.setFirst_name(values.getString("first_name"));
                                data.setMiddle_name(values.getString("middle_name"));
                                data.setLast_name(values.getString("last_name"));
                                data.setName_ext(values.getString("name_ext"));
                                data.setNickname(values.getString("nickname"));
                                data.setDate_of_birth(values.getString("date_of_birth"));
                                data.setPlace_of_birth(values.getString("place_of_birth"));
                                data.setSex(values.getString("sex"));
                                data.setCivil_status(values.getString("civil_status"));
                                data.setOccupation(values.getString("occupation"));
                                data.setHeight(String.valueOf(values.getDouble("height")));
                                data.setHeight_metric(values.getString("height_metric"));
                                data.setWeight(String.valueOf(values.getDouble("weight")));
                                data.setWeight_metric(values.getString("weight_metric"));
                                data.setBlood_type(values.getString("blood_type"));
                                //data.setChu_churchID(values.getInt("chu_churchID"));
                                //data.setChu_church_name(values.getString("chu_church_name"));
                                //data.setChu_cellLeaderID(values.getInt("chu_cellLeaderID"));
                                //data.setChu_cellLeader(values.getString("chu_cellLeader"));
                                //data.setChu_roleID(values.getInt("chu_roleID"));
                                //data.setChu_roleName(values.getString("chu_roleName"));
                                data.setLoc_country(values.getString("loc_country"));
                                data.setLoc_province(values.getString("loc_province"));
                                data.setLoc_city(values.getString("loc_city"));
                                data.setLoc_barangay(values.getString("loc_barangay"));
                                data.setLoc_address(values.getString("loc_address"));
                                data.setLoc_zipcode(values.getString("loc_zipcode"));
                                data.setCon_mobile_no(values.getString("con_mobile_no"));
                                data.setCon_tel_no(values.getString("con_tel_no"));
                                data.setEduc_elem(values.getString("educ_elem"));
                                data.setEduc_elem_year_graduated(utilDate.getFromServer_DatePickerFormat(values.getString("educ_elem_year_graduated")));
                                data.setEduc_high_school(values.getString("educ_high_school"));
                                data.setEduc_high_school_graduated(utilDate.getFromServer_DatePickerFormat(values.getString("educ_high_school_graduated")));
                                data.setEduc_college(values.getString("educ_college"));
                                data.setEduc_college_graduate(utilDate.getFromServer_DatePickerFormat(values.getString("educ_college_graduate")));
                                data.setEduc_attainment(values.getString("educ_attainment"));
                                data.setEduc_course(values.getString("educ_course"));
                                data.setSoc_facebook_url(values.getString("soc_facebook_url"));
                                data.setSoc_youtube_url(values.getString("soc_youtube_url"));
                                data.setSoc_instagram_url(values.getString("soc_instagram_url"));
                                data.setSoc_linkin_url(values.getString("soc_linkin_url"));
                                data.setSoc_tiktok_url(values.getString("soc_tiktok_url"));
                                data.setSoc_twitter_url(values.getString("soc_twitter_url"));
                                data.setOcc_name_of_employer(values.getString("occ_name_of_employer"));
                                data.setOcc_occupation(values.getString("occ_occupation"));
                                data.setOcc_address(values.getString("occ_address"));
                                data.setPrivacy_settings(values.getString("privacy_settings"));
                                data.setDtUpdated(values.getString("dtUpdated"));

                                openHelperProfileInformation sqlHelper = new openHelperProfileInformation(context);
                                Boolean result = sqlHelper.get_profile_info(data);

                                sharedData.set_Profile_Sync_Data(context, true);
                                sharedData.set_selected_churchID_set_up_mode(context,values.getString("_churchID"));
                                //sharedData.set_profile_photo(context, accessUrl.BASE_URL + values.getString("profile_photo_path"));
                                //Toast.makeText(context, "Sync Completed!", Toast.LENGTH_LONG).show();

                                UsersModel users = new UsersModel();
                                users.setUserID(values.getInt("userID"));
                                users.setUserCode(values.getString("cuserID"));
                                users.setUsername("");
                                users.setPassword(sharedData.get_password(context));
                                users.setUserLevel(values.getString("accessLevelID"));
                                users.setEmail_address(values.getString("email"));
                                users.setProfile_photo_path(values.getString("profile_photo_path"));
                                users.setAndroid_photo_path("");
                                users.setBitmap_photo("");

                                sqlHelper.insert_users(users);

                                //SYN FAMILY BACKGROUND//
                                syncFamilyBackground(context,progressDialogBar);

                            }
                        } catch (JSONException e) {
                            Log.v("Data", e.toString());
                            Toast.makeText(context, "JsonError" + e, Toast.LENGTH_LONG).show();
                            progressDialogBar.hide();


                            //Intent intent = new Intent(context, SplashScreen.class);
                            //context.startActivity(intent);

                        }
                    }
                }, error -> {
                    Log.v("Data", error.toString());
                    utilNetwork.volleyError(context, error);
                });
                requestQueue.add(jsonObjectRequest);
            }else{
                progressDialogBar.hide();
            }
        }else{
            progressDialogBar.hide();
        }

    }

    public static void syncProfileInformation_post(Context context,ProgressDialogBar progressDialogBar) {

        String url = accessUrl.BASE_URL + "android/profile/" + sharedData.get_userID(context);

        //Log.v("URL", url);

        if (!sharedData.is_Profile_Sync(context)) {

            if (utilNetwork.isConnected(context)) {

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.v("CONVERSION", response.toString());

                            JSONObject values = response.getJSONObject("data");
                            String response_text = response.getString("response");

                            if (response_text.equals("success")) {

                                PersonalInformationModel data = new PersonalInformationModel();

                                data.setProfileID(values.getInt("profileID"));
                                data.setUserID(values.getInt("userID"));
                                data.setCuserID(values.getString("cuserID"));
                                data.setAbout(values.getString("about"));
                                data.setFirst_name(values.getString("first_name"));
                                data.setMiddle_name(values.getString("middle_name"));
                                data.setLast_name(values.getString("last_name"));
                                data.setName_ext(values.getString("name_ext"));
                                data.setNickname(values.getString("nickname"));
                                data.setDate_of_birth(values.getString("date_of_birth"));
                                data.setPlace_of_birth(values.getString("place_of_birth"));
                                data.setSex(values.getString("sex"));
                                data.setCivil_status(values.getString("civil_status"));
                                data.setOccupation(values.getString("occupation"));
                                data.setHeight(String.valueOf(values.getDouble("height")));
                                data.setHeight_metric(values.getString("height_metric"));
                                data.setWeight(String.valueOf(values.getDouble("weight")));
                                data.setWeight_metric(values.getString("weight_metric"));
                                data.setBlood_type(values.getString("blood_type"));
                                //data.setChu_churchID(values.getInt("chu_churchID"));
                                //data.setChu_church_name(values.getString("chu_church_name"));
                                //data.setChu_cellLeaderID(values.getInt("chu_cellLeaderID"));
                                //data.setChu_cellLeader(values.getString("chu_cellLeader"));
                                //data.setChu_roleID(values.getInt("chu_roleID"));
                                //data.setChu_roleName(values.getString("chu_roleName"));
                                data.setLoc_country(values.getString("loc_country"));
                                data.setLoc_province(values.getString("loc_province"));
                                data.setLoc_city(values.getString("loc_city"));
                                data.setLoc_barangay(values.getString("loc_barangay"));
                                data.setLoc_address(values.getString("loc_address"));
                                data.setLoc_zipcode(values.getString("loc_zipcode"));
                                data.setCon_mobile_no(values.getString("con_mobile_no"));
                                data.setCon_tel_no(values.getString("con_tel_no"));
                                data.setEduc_elem(values.getString("educ_elem"));
                                data.setEduc_elem_year_graduated(utilDate.getFromServer_DatePickerFormat(values.getString("educ_elem_year_graduated")));
                                data.setEduc_high_school(values.getString("educ_high_school"));
                                data.setEduc_high_school_graduated(utilDate.getFromServer_DatePickerFormat(values.getString("educ_high_school_graduated")));
                                data.setEduc_college(values.getString("educ_college"));
                                data.setEduc_college_graduate(utilDate.getFromServer_DatePickerFormat(values.getString("educ_college_graduate")));
                                data.setEduc_attainment(values.getString("educ_attainment"));
                                data.setEduc_course(values.getString("educ_course"));
                                data.setSoc_facebook_url(values.getString("soc_facebook_url"));
                                data.setSoc_youtube_url(values.getString("soc_youtube_url"));
                                data.setSoc_instagram_url(values.getString("soc_instagram_url"));
                                data.setSoc_linkin_url(values.getString("soc_linkin_url"));
                                data.setSoc_tiktok_url(values.getString("soc_tiktok_url"));
                                data.setSoc_twitter_url(values.getString("soc_twitter_url"));
                                data.setOcc_name_of_employer(values.getString("occ_name_of_employer"));
                                data.setOcc_occupation(values.getString("occ_occupation"));
                                data.setOcc_address(values.getString("occ_address"));
                                data.setPrivacy_settings(values.getString("privacy_settings"));
                                data.setDtUpdated(values.getString("dtUpdated"));

                                openHelperProfileInformation sqlHelper = new openHelperProfileInformation(context);
                                Boolean result = sqlHelper.get_profile_info(data);

                                sharedData.set_Profile_Sync_Data(context, true);
                                //sharedData.set_profile_photo(context, accessUrl.BASE_URL + values.getString("profile_photo_path"));
                                //Toast.makeText(context, "Sync Completed!", Toast.LENGTH_LONG).show();

                                UsersModel users = new UsersModel();
                                users.setUserID(values.getInt("userID"));
                                users.setUserCode(values.getString("cuserID"));
                                users.setUsername("");
                                users.setPassword("");
                                users.setUserLevel(values.getString("accessLevelID"));
                                users.setEmail_address(values.getString("email"));
                                users.setProfile_photo_path(values.getString("profile_photo_path"));
                                users.setAndroid_photo_path("");
                                users.setBitmap_photo("");

                                sqlHelper.insert_users(users);

                                //SYN FAMILY BACKGROUND//
                                syncFamilyBackground(context,progressDialogBar);

                            }
                        } catch (JSONException e) {
                            Log.v("Data", e.toString());
                            Toast.makeText(context, "JsonError" + e, Toast.LENGTH_LONG).show();
                            progressDialogBar.hide();
                        }
                    }
                }, error -> {
                    Log.v("Data", error.toString());
                    utilNetwork.volleyError(context, error);
                });
                requestQueue.add(jsonObjectRequest);
            }else{
                progressDialogBar.hide();
            }
        }else{
            progressDialogBar.hide();
        }

    }

    public static void syncFamilyBackground(Context context, ProgressDialogBar progressDialogBar){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            openHelperProfileInformation shelper = new openHelperProfileInformation(context);
            shelper.delete_all_family(String.valueOf(sharedData.get_userID(context)));

            String url = accessUrl.BASE_URL + "android/profile/family/get-family-list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_FAMILY",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                //Toast.makeText(context,obj.getString("familyBackgroundID"),Toast.LENGTH_LONG).show();
                                FamilyBackgroundModel data = new FamilyBackgroundModel();
                                data.setFamilyBackgroundID( Integer.parseInt(obj.getString("familyBackgroundID")));
                                data.setRel_profileID(Integer.parseInt(obj.getString("profileID")));
                                data.setProfile_photo_path(accessUrl.BASE_URL + obj.getString("profile_photo_path"));
                                data.setRelationship(obj.getString("relationship"));
                                data.setRel_profileID(Integer.parseInt(obj.getString("rel_profileID")));
                                data.setRel_name(obj.getString("rel_name"));
                                data.setRel_age(Integer.parseInt(obj.getString("rel_age")));
                                data.setRel_birthdate(obj.getString("rel_birthdate"));
                                data.setRel_occupation(obj.getString("rel_occupation"));
                                data.setRel_contact_no(obj.getString("rel_contact_no"));
                                data.setRel_status(obj.getString("rel_status"));
                                data.setRel_condition(obj.getString("rel_condition"));
                                data.setPhoto_bitmap("");
                                data.setAndroid_photo_path("");
                                data.setDtCreated(obj.getString("dtCreated"));

                                shelper.insert_family(data);
                            }

                            evangelizeTransfer.download_Evangelized_List(context,progressDialogBar);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void update_ProfileInformation_to_Server(Context context, ProgressDialogBar progressDialogBar) {

        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            openHelperProfileInformation openHelper = new openHelperProfileInformation(context);
            PersonalInformationModel data = openHelper.get_Profile();

            Log.v("BITMAP",data.getBitmap_photo());

            String url = accessUrl.BASE_URL + "android/profile/sync_profile";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        String[] resp = response.split("-");
                        Log.v("QUERY", response);
                        if (resp[0].equals("success")) {
                            //Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        }
                        //THIS IS TO UPDATE AND SYNCHRONIZE THE FAMILY MEMBERS INSIDE OF PROFILE
                        //update_FamilyBackground_to_Server(context,openHelper, progressDialogBar);
                        delete_bitmap_raw_profile(context);
                        delete_FamilyBackground_to_Server(context,openHelper, progressDialogBar);
                        //progressDialogBar.hide();
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                progressDialogBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    MyData.put("about", data.getAbout());
                    MyData.put("first_name", data.getFirst_name());
                    MyData.put("middle_name", data.getMiddle_name());
                    MyData.put("last_name", data.getLast_name());
                    MyData.put("name_ext", data.getName_ext());
                    MyData.put("nickname", data.getNickname());
                    MyData.put("date_of_birth", data.getDate_of_birth());
                    MyData.put("place_of_birth", data.getPlace_of_birth());
                    MyData.put("sex", data.getSex());
                    MyData.put("civil_status", data.getCivil_status());
                    MyData.put("occupation", data.getOccupation());
                    MyData.put("height", data.getHeight());
                    MyData.put("height_metric", data.getHeight_metric());
                    MyData.put("weight", data.getWeight());
                    MyData.put("weight_metric", data.getWeight_metric());
                    MyData.put("blood_type", data.getBlood_type());
                    MyData.put("loc_country", data.getLoc_country());
                    MyData.put("loc_province", data.getLoc_province());
                    MyData.put("loc_city", data.getLoc_city());
                    MyData.put("loc_barangay", data.getLoc_barangay());
                    MyData.put("loc_address", data.getLoc_address());
                    MyData.put("loc_zipcode", data.getLoc_zipcode());
                    MyData.put("con_mobile_no", data.getCon_mobile_no());
                    MyData.put("con_tel_no", data.getCon_tel_no());
                    MyData.put("educ_elem", data.getEduc_elem());
                    MyData.put("educ_elem_year_graduated", data.getEduc_elem_year_graduated());
                    MyData.put("educ_high_school", data.getEduc_high_school());
                    MyData.put("educ_high_school_graduated", data.getEduc_high_school_graduated());
                    MyData.put("educ_college", data.getEduc_college());
                    MyData.put("educ_college_graduate", data.getEduc_college_graduate());
                    MyData.put("educ_attainment", data.getEduc_attainment());
                    MyData.put("educ_course", data.getEduc_course());
                    MyData.put("soc_facebook_url", data.getSoc_facebook_url());
                    MyData.put("soc_youtube_url", data.getSoc_youtube_url());
                    MyData.put("soc_instagram_url", data.getSoc_instagram_url());
                    MyData.put("soc_linkin_url", data.getSoc_linkin_url());
                    MyData.put("soc_tiktok_url", data.getSoc_tiktok_url());
                    MyData.put("soc_twitter_url", data.getSoc_twitter_url());
                    MyData.put("occ_name_of_employer", data.getOcc_name_of_employer());
                    MyData.put("occ_occupation", data.getOcc_occupation());
                    MyData.put("occ_address", data.getOcc_address());
                    MyData.put("privacy_settings", data.getPrivacy_settings());
                    MyData.put("updatedBY", String.valueOf(sharedData.get_userID(context)));
                    MyData.put("dtUpdated", utilDate.getCurrentDate_ServerFormat());
                    MyData.put("profile_photo_path",data.getProfile_photo());
                    MyData.put("android_photo_path",data.getAndroid_photo_path());
                    MyData.put("bitmap_photo",data.getBitmap_photo());
                    MyData.put("cuserID",data.getCuserID());
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }
    }

    private static void delete_FamilyBackground_to_Server(Context context,openHelperProfileInformation openHelper, ProgressDialogBar progressDialogBar ){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/profile/family/delete_sync_profile";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {

                        Log.v("DELETE-FAMILY",response);
                        //progressDialogBar.hide();
                        update_FamilyBackground_to_Server(context,openHelper, progressDialogBar);
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }
    }
    private static void update_FamilyBackground_to_Server(Context context, openHelperProfileInformation openHelper, ProgressDialogBar progressDialogBar) {
        if (utilNetwork.isConnected(context)) {

            if(openHelper.get_family_list_count(null,"")>0) {
                List<FamilyBackgroundModel> data = openHelper.get_family_list(null, "");

                for (int i = 0; i < data.size(); i++) {

                    final int row_index = i;

                    RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

                    String url = accessUrl.BASE_URL + "android/profile/family/sync_profile";
                    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                            response -> {
                                progressDialogBar.hide();
                                Log.v("FAMILY", response);
                            }, error -> {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialogBar.hide();
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> MyData = new HashMap<>();
                            MyData.put("familyBackgroundID", String.valueOf(data.get(row_index).getFamilyBackgroundID()));
                            MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                            MyData.put("relationship", data.get(row_index).getRelationship());
                            MyData.put("rel_name", data.get(row_index).getRel_name());
                            MyData.put("rel_age", String.valueOf(data.get(row_index).getRel_age()));
                            MyData.put("rel_birthdate", data.get(row_index).getRel_birthdate());
                            MyData.put("rel_occupation", data.get(row_index).getRel_occupation());
                            MyData.put("rel_contact_no", data.get(row_index).getRel_contact_no());
                            MyData.put("rel_condition", data.get(row_index).getRel_condition());
                            MyData.put("profile_photo_path", data.get(row_index).getProfile_photo_path());
                            MyData.put("raw_photo_bitmap", data.get(row_index).getPhoto_bitmap());
                            return MyData;
                        }
                    };
                    MyRequestQueue.add(MyStringRequest);

                    if(i==data.size()-1){
                        Toast.makeText(context,"You have successfully synced your profile.",Toast.LENGTH_LONG).show();
                    }

                }

            }




        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }


        }

    private static void delete_bitmap_raw_profile(Context context){
        PersonalInformationModel datax = new PersonalInformationModel();
        datax.setBitmap_photo("");
        //Toast.makeText(getContext(),img_local_source, Toast.LENGTH_LONG).show();
        openHelperProfileInformation open = new openHelperProfileInformation(context);
        open.update_photo_information(datax);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private static void setWebView_Profile_Browse(Context context, WebView wv, StringBuilder data){

        WebSettings settings = wv.getSettings();
        //settings.setBuiltInZoomControls(false);

        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        //settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        wv.setVerticalScrollBarEnabled(false);
        wv.setHorizontalScrollBarEnabled(false);


        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        wv.setHorizontalScrollBarEnabled(false);
        wv.setOnTouchListener(new View.OnTouchListener() {
            float m_downX;
            public boolean onTouch(View v, @SuppressLint("ClickableViewAccessibility") MotionEvent event) {

                if (event.getPointerCount() > 1) {
                    //Multi touch detected
                    return true;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // save the x
                        m_downX = event.getX();
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        // set x so that it doesn't move
                        event.setLocation(m_downX, event.getY());
                        break;
                    }

                }
                return false;
            }
        });

        StringBuilder content = new StringBuilder();

        content.append("<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">");
        //content.append("<LINK href=\"bootstrap.min.css\" type=\"text/css\" rel=\"stylesheet\"/>");
        content.append(themes.get_css(context));
        content.append("</head> <body>");
        content.append("<div>");

        content.append(data);

        content.append("</div></body></html>");

        wv.loadDataWithBaseURL("file:///android_asset/files/css",content.toString(),"text/html","utf-8",null);

    }

}