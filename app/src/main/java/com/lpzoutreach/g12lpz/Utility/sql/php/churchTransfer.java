package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.SearchChurchListView;
import com.lpzoutreach.g12lpz.ListView.membersChurchDetailsListView;
import com.lpzoutreach.g12lpz.Models.ChurchMembersModel;
import com.lpzoutreach.g12lpz.Models.ChurchModel;
import com.lpzoutreach.g12lpz.Models.ObjectModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperChurch;
import com.lpzoutreach.g12lpz.Utility.string.utilString;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.toast.utilToast;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class churchTransfer {

    public static void get_all_churches(Context context, AutoCompleteTextView actObject){
        if(utilNetwork.isConnected(context)){
            ArrayList<String> data = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "/android/church/get-all";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i =0; i < response.length(); i++)
                    {
                        try{
                            JSONObject obj = response.getJSONObject(i);
                            data.add(obj.getString("church_name"));
                        }catch (JSONException e){
                            Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
                    actObject.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,context.getResources().getString(R.string.system_error_no_internet_connection),Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
        }else{
            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    public static void syncChurch(Context context, ProgressDialogBar progressDialogBar){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/church/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_CHURCH",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                ChurchModel data = new ChurchModel();
                                data.setChurchID(obj.getInt("churchID"));
                                data.setPerson_founded_user_ID(obj.getInt("founderID"));
                                data.setUser_ID(sharedData.get_userID(context));
                                data.setAbout(obj.getString("about"));
                                data.setChurch_parent(obj.getString("churchParent"));
                                data.setChurch_name(obj.getString("church_name"));
                                data.setDate_founded(utilDate.getFromServer_DatePickerFormat(obj.getString("date_founded")));
                                data.setCountry(obj.getString("country"));
                                data.setProvince(obj.getString("province"));
                                data.setMunicipality_city(obj.getString("city"));
                                data.setBarangay(obj.getString("barangay"));
                                data.setAddress(obj.getString("address"));
                                data.setZip_code(obj.getString("zipcode"));
                                data.setUrl_logo(obj.getString("url_photo"));
                                data.setEmail(obj.getString("con_email"));
                                data.setMobile_no(obj.getString("con_mobile_no"));
                                data.setUrl_cover_photo(obj.getString("url_cover_photo"));

                                openHelperChurch helper = new openHelperChurch(context);
                                helper.delete_church();
                                helper.insert(data);

                            }

                            progressDialogBar.hide();

                        }catch (JSONException e) {
                            progressDialogBar.hide();
                            Toast.makeText(context,"Failed to fetch. No Church Data Existed",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                progressDialogBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("churchID", String.valueOf(sharedData.get_selected_churchID_set_up_mode(context)));
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void getCountMembers(Context context, elUtil eventListenerUtil, String churchID){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "/android/church/members/count";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("COUNT_MEMBERS_CHURCH",response.toString());
                        eventListenerUtil.myCallBack("getCountMembers", response);
                    }, error -> {
                eventListenerUtil.myCallBack("getCountMembers", "?");
                Log.v("ERRORINTERNET", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("churchID", churchID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            eventListenerUtil.myCallBack("getCountMembers", "?");
        }
    }

    public static void get_all_members(Context context,  ConstraintLayout no_data, ConstraintLayout no_internet, ConstraintLayout loading, ConstraintLayout content_data,ListView listView, String churchID, elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            loading.setVisibility(View.VISIBLE);
            no_internet.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            content_data.setVisibility(View.GONE);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            List<ChurchMembersModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/church/get/members";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_MEMBERS",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                ChurchMembersModel rows = new ChurchMembersModel();
                                rows.setChurchID(obj.getInt("churchID"));
                                rows.setChurch_name(obj.getString("church_name"));
                                rows.setUserID(obj.getInt("userID"));
                                rows.setFirst_name(obj.getString("first_name"));
                                rows.setMiddle_name(obj.getString("middle_name"));
                                rows.setLast_name(obj.getString("last_name"));
                                rows.setProfile_photo_path(obj.getString("profile_photo_path"));
                                rows.setAddress(obj.getString("loc_address"));
                                rows.setBarangay(obj.getString("loc_barangay"));
                                rows.setCity(obj.getString("loc_city"));
                                rows.setProvince(obj.getString("loc_province"));
                                rows.setChurch_position(obj.getString("_church_position"));
                                rows.setEventListenerUtil(eventListenerUtil);
                                data.add(rows);
                            }

                            membersChurchDetailsListView adapter = new membersChurchDetailsListView(context,R.layout.lv_members_church_details,data);
                            listView.setAdapter(adapter);

                            if(data.size()>0){
                                loading.setVisibility(View.GONE);
                                no_internet.setVisibility(View.GONE);
                                no_data.setVisibility(View.GONE);
                                content_data.setVisibility(View.VISIBLE);
                            }else{
                                loading.setVisibility(View.GONE);
                                no_internet.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                                content_data.setVisibility(View.GONE);
                            }


                        }catch (JSONException e) {
                            loading.setVisibility(View.GONE);
                            no_internet.setVisibility(View.GONE);
                            no_data.setVisibility(View.VISIBLE);
                            content_data.setVisibility(View.GONE);
                            Toast.makeText(context,"Failed to fetch. No Church Data Existed",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                no_internet.setVisibility(View.GONE);
                no_data.setVisibility(View.VISIBLE);
                content_data.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("churchID", churchID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            loading.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            content_data.setVisibility(View.GONE);
        }
    }

    public static void search_churches(Context context, elUtil eventListenerUtil,ListView listview, ConstraintLayout no_data,ConstraintLayout no_internet, ConstraintLayout loading, String searchValue){
        if(utilNetwork.isConnected(context)){
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/church/search"; // <----enter your post url here
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("SEARCH_CHURCHES",response);
                        loading.setVisibility(View.VISIBLE);
                        listview.setVisibility(View.GONE);
                        no_data.setVisibility(View.GONE);
                        no_internet.setVisibility(View.GONE);

                        List<ChurchModel> data = new ArrayList<>();
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                ChurchModel row = new ChurchModel();
                                row.setChurchID(obj.getInt("churchID"));
                                row.setUrl_logo(obj.getString("url_photo"));
                                row.setChurch_name(obj.getString("church_name"));
                                row.setAddress(obj.getString("address"));
                                row.setBarangay(obj.getString("barangay"));
                                row.setMunicipality_city(obj.getString("city"));
                                row.setProvince(obj.getString("province"));
                                row.setZip_code(obj.getString("zipcode"));
                                row.setCountry(obj.getString("country"));
                                row.setTotal_members(obj.getString("total_members"));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            SearchChurchListView arrayAdapter = new SearchChurchListView(context, R.layout.lv_search_church, data);
                            listview.setAdapter(arrayAdapter);
                            listview.setClickable(true);
                            listview.setDivider(null);

                            if(data.size()>0){
                                listview.setVisibility(View.VISIBLE);
                                no_data.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                                no_internet.setVisibility(View.GONE);
                            }else{
                                listview.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                                no_internet.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    }, error -> {
                Log.v("ERROR",error.toString());
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("action", "yes");
                    MyData.put("churchID",sharedData.get_selected_churchID_set_up_mode(context));
                    MyData.put("searchValue", searchValue);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            loading.setVisibility(View.GONE);
            listview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void getChurch(Context context, ConstraintLayout content, ConstraintLayout loading, ImageView logo, ImageView coverPhoto, TextView churchName, String churchID, elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/church/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_CHURCH",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                ChurchModel data = new ChurchModel();
                                data.setChurchID(obj.getInt("churchID"));
                                data.setPerson_founded_user_ID(obj.getInt("founderID"));
                                data.setUser_ID(sharedData.get_userID(context));
                                data.setAbout(obj.getString("about"));
                                data.setChurch_parent("");
                                data.setChurch_name(obj.getString("church_name"));
                                data.setDate_founded(utilDate.getFromServer_DatePickerFormat(obj.getString("date_founded")));
                                data.setCountry(obj.getString("country"));
                                data.setProvince(obj.getString("province"));
                                data.setMunicipality_city(obj.getString("city"));
                                data.setBarangay(obj.getString("barangay"));
                                data.setAddress(obj.getString("address"));
                                data.setZip_code(obj.getString("zipcode"));
                                data.setUrl_logo(obj.getString("url_photo"));
                                data.setUrl_cover_photo(obj.getString("url_cover_photo"));



                                churchName.setText(data.getChurch_name());

                                if(obj.getString("url_photo").isEmpty()){
                                    logo.setImageResource(R.drawable.default_photo);
                                }else{
                                    Picasso.get().load( accessUrl.BASE_URL +  obj.getString("url_photo"))
                                            .placeholder(context.getDrawable(R.drawable.default_photo))
                                            .error(context.getDrawable(R.drawable.default_photo))
                                            .into(logo);
                                }
                                if(obj.getString("url_cover_photo").isEmpty()){
                                    coverPhoto.setImageResource(R.drawable.default_photo);
                                }else{
                                    Picasso.get().load(accessUrl.BASE_URL + obj.getString("url_cover_photo"))
                                            .placeholder(context.getDrawable(R.drawable.default_photo))
                                            .error(context.getDrawable(R.drawable.default_photo))
                                            .into(coverPhoto);
                                }

                            }



                            eventListenerUtil.myCallBack("reloadCount","");

                        }catch (JSONException e) {
                            Toast.makeText(context,"Failed to fetch. No Church Data Existed",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("churchID", churchID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void getChurch(Context context, ObjectModel object, String churchID){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/church/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_CHURCH",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                object.getChu_about().setText(obj.getString("about"));
                                String address="";
                                address = utilString.setConcat(address, obj.getString("address"),",");
                                address = utilString.setConcat(address, obj.getString("barangay"),",");
                                address = utilString.setConcat(address, obj.getString("city"),",");
                                address = utilString.setConcat(address, obj.getString("province"),",");
                                address = utilString.setConcat(address, obj.getString("zipcode"),",");
                                address = utilString.setConcat(address, obj.getString("country"),",");

                                object.getChu_address().setText(address);
                                object.getChu_churchParent().setText(obj.getString("churchParent"));
                                object.getChu_mobile_no().setText(obj.getString("con_mobile_no"));
                                object.getChu_email().setText(obj.getString("con_email"));

                            }

                        }catch (JSONException e) {
                            Toast.makeText(context,"Failed to fetch. No Church Data Existed",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("churchID", churchID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void leaveChurch(Context context, elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "churchController/android_leave_church";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                        sharedData.set_selected_churchID_set_up_mode(context,"0");
                        openHelperChurch helper = new openHelperChurch(context);
                        helper.delete_church();
                        eventListenerUtil.myCallBack("leaveChurch","");
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
        }else{
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void joinChurch(Context context, elUtil eventListenerUtil, String churchID){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/church/join";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_CHURCH",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                ChurchModel data = new ChurchModel();
                                data.setChurchID(obj.getInt("churchID"));
                                data.setPerson_founded_user_ID(obj.getInt("founderID"));
                                data.setUser_ID(sharedData.get_userID(context));
                                data.setAbout(obj.getString("about"));
                                data.setChurch_parent(obj.getString("churchParent"));
                                data.setChurch_name(obj.getString("church_name"));
                                data.setDate_founded(utilDate.getFromServer_DatePickerFormat(obj.getString("date_founded")));
                                data.setCountry(obj.getString("country"));
                                data.setProvince(obj.getString("province"));
                                data.setMunicipality_city(obj.getString("city"));
                                data.setBarangay(obj.getString("barangay"));
                                data.setAddress(obj.getString("address"));
                                data.setZip_code(obj.getString("zipcode"));
                                data.setUrl_logo(obj.getString("url_photo"));
                                data.setEmail(obj.getString("con_email"));
                                data.setMobile_no(obj.getString("con_mobile_no"));
                                data.setUrl_cover_photo(obj.getString("url_cover_photo"));

                                openHelperChurch helper = new openHelperChurch(context);
                                helper.delete_church();
                                helper.insert(data);

                                sharedData.set_selected_churchID_set_up_mode(context,churchID);

                                getCountMembers_when_join(context,eventListenerUtil,churchID);

                            }



                        }catch (JSONException e) {
                            Toast.makeText(context,"Failed to fetch. " + e,Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    MyData.put("churchID", churchID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void getCountMembers_when_join(Context context, elUtil eventListenerUtil, String churchID){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/church/members/count";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("COUNT_MEMBERS_CHURCH",response.toString());
                        eventListenerUtil.myCallBack("joinChurch",response);
                    }, error -> {
                eventListenerUtil.myCallBack("joinChurch","?");
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("churchID", churchID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            eventListenerUtil.myCallBack("getCountMembers", "?");
        }
    }

}
