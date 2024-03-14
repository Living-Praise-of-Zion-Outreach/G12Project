package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.MembersG12NetworkListView;
import com.lpzoutreach.g12lpz.ListView.SearchPersonListView;
import com.lpzoutreach.g12lpz.Models.MembersG12NetworkModel;
import com.lpzoutreach.g12lpz.Models.PersonalInformationModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
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

public class membersTransfer {
    
    public static void get_profile_info(Context context, ProgressDialogBar progressDialogBar,MembersG12NetworkModel data){
        if(utilNetwork.isConnected(context)){
            progressDialogBar.show();

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/members/get-network-leader-info"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETLEADERINFO",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                data.getTv_name_network_leaner().setText(obj.getString("first_name") + " " + obj.getString("middle_name") + " " + obj.getString("last_name"));

                                if(obj.getString("networkName").equals("null")){
                                    data.getTv_organization_name_network().setText("- No Network Name -");
                                }else{
                                    data.getTv_organization_name_network().setText(obj.getString("networkName"));
                                }

                                data.getTv_count_members_network().setText(obj.getString("totalMembers"));
                                data.getTv_current_144_members_network6().setText(obj.getString("Total144Members"));
                                data.getTv_current_1789_members_network().setText(obj.getString("Total1789Members"));


                                if(!obj.getString("profile_photo_path").equals("")){

                                    String[] logo_checker = obj.getString("profile_photo_path").split("/");

                                    if(logo_checker[0].equals("assets")){

                                        if(!obj.getString("profile_photo_path").isEmpty()){
                                            Picasso.get().load(accessUrl.BASE_URL + obj.getString("profile_photo_path"))
                                                    .placeholder(context.getDrawable(R.drawable.default_photo))
                                                    .error(context.getDrawable(R.drawable.default_photo))
                                                    .into(data.getIv_network_leader_logo_network());
                                        }else{
                                            data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                        }
                                    }else{
                                        if(!obj.getString("profile_photo_path").isEmpty()){
                                            Picasso.get().load(obj.getString("profile_photo_path"))
                                                    .placeholder(context.getDrawable(R.drawable.default_photo))
                                                    .error(context.getDrawable(R.drawable.default_photo))
                                                    .into(data.getIv_network_leader_logo_network());
                                        }else{
                                            data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                        }
                                    }
                                }else{
                                    data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                }

                                if(!obj.getString("networkLogo").equals("")){
                                    if(!obj.getString("networkLogo").isEmpty()){
                                        Picasso.get().load(accessUrl.BASE_URL + obj.getString("networkLogo"))
                                                .placeholder(context.getDrawable(R.drawable.network_logo))
                                                .error(context.getDrawable(R.drawable.network_logo))
                                                .into(data.getIv_organization_logo_network());
                                    }else{
                                        data.getIv_network_leader_logo_network().setImageResource(R.drawable.network_logo);
                                    }
                                }else{
                                    data.getIv_network_leader_logo_network().setImageResource(R.drawable.network_logo);
                                }


                            }
                            progressDialogBar.hide();
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                progressDialogBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void get_network_leader_by_selected_userID(Context context, String userID, ProgressDialogBar progressDialogBar,MembersG12NetworkModel data){
        if(utilNetwork.isConnected(context)){
            progressDialogBar.show();

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/members/get-network-leader-info"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETLEADERINFO",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                data.getTv_name_network_leaner().setText(obj.getString("first_name") + " " + obj.getString("middle_name") + " " + obj.getString("last_name"));

                                if(obj.getString("networkName").equals("null")){
                                    data.getTv_organization_name_network().setText("- No Network Name -");
                                }else{
                                    data.getTv_organization_name_network().setText(obj.getString("networkName"));
                                }

                                data.getTv_count_members_network().setText(obj.getString("totalMembers"));
                                data.getTv_current_144_members_network6().setText(obj.getString("Total144Members"));
                                data.getTv_current_1789_members_network().setText(obj.getString("Total1789Members"));


                                if(!obj.getString("profile_photo_path").equals("")){

                                    String[] logo_checker = obj.getString("profile_photo_path").split("/");

                                    if(logo_checker[0].equals("assets")){

                                        if(!obj.getString("profile_photo_path").isEmpty()){
                                            Picasso.get().load(accessUrl.BASE_URL + obj.getString("profile_photo_path"))
                                                    .placeholder(context.getDrawable(R.drawable.default_photo))
                                                    .error(context.getDrawable(R.drawable.default_photo))
                                                    .into(data.getIv_network_leader_logo_network());
                                        }else{
                                            data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                        }
                                    }else{
                                        if(!obj.getString("profile_photo_path").isEmpty()){
                                            Picasso.get().load(obj.getString("profile_photo_path"))
                                                    .placeholder(context.getDrawable(R.drawable.default_photo))
                                                    .error(context.getDrawable(R.drawable.default_photo))
                                                    .into(data.getIv_network_leader_logo_network());
                                        }else{
                                            data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                        }
                                    }
                                }else{
                                    data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                }

                                if(!obj.getString("networkLogo").equals("")){
                                    if(!obj.getString("networkLogo").isEmpty()){
                                        Picasso.get().load(accessUrl.BASE_URL + obj.getString("networkLogo"))
                                                .placeholder(context.getDrawable(R.drawable.network_logo))
                                                .error(context.getDrawable(R.drawable.network_logo))
                                                .into(data.getIv_organization_logo_network());
                                    }else{
                                        data.getIv_network_leader_logo_network().setImageResource(R.drawable.network_logo);
                                    }
                                }else{
                                    data.getIv_network_leader_logo_network().setImageResource(R.drawable.network_logo);
                                }


                            }
                            progressDialogBar.hide();
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                progressDialogBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("userID", userID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    public static void get_network_leader(Context context, ProgressDialogBar progressDialogBar,MembersG12NetworkModel data){
        if(utilNetwork.isConnected(context)){
        progressDialogBar.show();

        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/members/get-network-leader-info"; // <----enter your post url here
        @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.v("GETLEADERINFO",response);
                    try{
                        JSONArray j= new JSONArray(response);
                        for (int i = 0; i < j.length(); i++) {
                            JSONObject obj = j.getJSONObject(i);

                            data.getTv_name_network_leaner().setText(obj.getString("first_name") + " " + obj.getString("middle_name") + " " + obj.getString("last_name"));

                            if(obj.getString("networkName").equals("null")){
                                data.getTv_organization_name_network().setText("- No Network Name -");
                            }else{
                                data.getTv_organization_name_network().setText(obj.getString("networkName"));
                            }

                            data.getTv_count_members_network().setText(obj.getString("totalMembers"));
                            data.getTv_current_144_members_network6().setText(obj.getString("Total144Members"));
                            data.getTv_current_1789_members_network().setText(obj.getString("Total1789Members"));


                            if(!obj.getString("profile_photo_path").equals("")){

                                String[] logo_checker = obj.getString("profile_photo_path").split("/");

                                if(logo_checker[0].equals("assets")){

                                    if(!obj.getString("profile_photo_path").isEmpty()){
                                        Picasso.get().load(accessUrl.BASE_URL + obj.getString("profile_photo_path"))
                                                .placeholder(context.getDrawable(R.drawable.default_photo))
                                                .error(context.getDrawable(R.drawable.default_photo))
                                                .into(data.getIv_network_leader_logo_network());
                                    }else{
                                        data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                    }
                                }else{
                                    if(!obj.getString("profile_photo_path").isEmpty()){
                                        Picasso.get().load(obj.getString("profile_photo_path"))
                                                .placeholder(context.getDrawable(R.drawable.default_photo))
                                                .error(context.getDrawable(R.drawable.default_photo))
                                                .into(data.getIv_network_leader_logo_network());
                                    }else{
                                        data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                                    }
                                }
                            }else{
                                data.getIv_network_leader_logo_network().setImageResource(R.drawable.default_photo);
                            }

                            if(!obj.getString("networkLogo").equals("")){
                                    if(!obj.getString("networkLogo").isEmpty()){
                                        Picasso.get().load(accessUrl.BASE_URL + obj.getString("networkLogo"))
                                                .placeholder(context.getDrawable(R.drawable.network_logo))
                                                .error(context.getDrawable(R.drawable.network_logo))
                                                .into(data.getIv_organization_logo_network());
                                    }else{
                                        data.getIv_network_leader_logo_network().setImageResource(R.drawable.network_logo);
                                    }
                            }else{
                                data.getIv_network_leader_logo_network().setImageResource(R.drawable.network_logo);
                            }


                        }
                        progressDialogBar.hide();
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
        }else{

        }
    }
    public static void get_network_members(Context context, ListView lv, ConstraintLayout no_data, ConstraintLayout loading, elUtil eventListenerUtil, String userID, String actionType){
        if(utilNetwork.isConnected(context)){

            loading.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/members/get-members"; // <----enter your post url here
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETMEMBERS",response);

                        List<MembersG12NetworkModel> data = new ArrayList<>();
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                MembersG12NetworkModel row = new MembersG12NetworkModel();
                                row.setUserID(obj.getInt("userID"));
                                row.setMember_logo(obj.getString("profile_photo_path"));
                                row.setFirst_name(obj.getString("first_name"));
                                row.setMiddle_name(obj.getString("middle_name"));
                                row.setLast_name(obj.getString("last_name"));
                                row.setOrganization_name(obj.getString("networkName"));
                                row.setPrimary_leaders(Integer.parseInt(obj.getString("totalPLMembers")));
                                row.set_144(Integer.parseInt(obj.getString("Total144Members")));
                                row.set_1789(Integer.parseInt(obj.getString("Total1789Members")));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionType(actionType);
                                data.add(row);
                            }

                            MembersG12NetworkListView arrayAdapter = new MembersG12NetworkListView(context, R.layout.lv_members_network, data);
                            lv.setAdapter(arrayAdapter);
                            lv.setClickable(true);
                            lv.setDivider(null);

                            if(data.size()>0){
                                lv.setVisibility(View.VISIBLE);
                                no_data.setVisibility(View.GONE);
                            }else{
                                lv.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }

                        loading.setVisibility(View.GONE);

                    }, error -> {

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();

                    if(!userID.equals("")){
                        MyData.put("userID", userID);
                    }else{
                        MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    }
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            no_data.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            lv.setVisibility(View.GONE);
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void add_network_leader(Context context, elUtil eventListenerUtil,String userID){
        if(utilNetwork.isConnected(context)){
            //Toast.makeText(context,userID,Toast.LENGTH_LONG).show();
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/members/network-leader/add"; // <----enter your post url here
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("ADDMEMBERS",response);
                        eventListenerUtil.myCallBack("addNetworkLeader","");
                    }, error -> {
                Log.v("ERROR",error.toString());
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    MyData.put("person_userID", userID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
        }
    }
    public static void remove_network_leader(Context context, elUtil eventListenerUtil,String userID){
        if(utilNetwork.isConnected(context)){
            //Toast.makeText(context,userID,Toast.LENGTH_LONG).show();
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/members/network-leader/remove"; // <----enter your post url here
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETMEMBERS",response);
                        eventListenerUtil.myCallBack("removeNetworkLeader","");
                    }, error -> {
                Log.v("ERROR",error.toString());
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("userID", userID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
        }
    }

    public static void search_members(Context context, elUtil eventListenerUtil,ListView listview, ConstraintLayout no_data,ConstraintLayout no_internet, ConstraintLayout loading, String searchValue){
        if(utilNetwork.isConnected(context)){
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/members/search"; // <----enter your post url here
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("SEARCH_MEMBERS",response);
                        loading.setVisibility(View.VISIBLE);
                        listview.setVisibility(View.GONE);
                        no_data.setVisibility(View.GONE);
                        no_internet.setVisibility(View.GONE);

                        List<PersonalInformationModel> data = new ArrayList<>();
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                PersonalInformationModel row = new PersonalInformationModel();
                                row.setUserID(obj.getInt("userID"));
                                row.setEmail(obj.getString("email"));
                                row.setProfile_photo(obj.getString("profile_photo_path"));
                                row.setFirst_name(obj.getString("first_name"));
                                row.setMiddle_name(obj.getString("middle_name"));
                                row.setLast_name(obj.getString("last_name"));
                                row.setChu_church_name(obj.getString("ChurchName"));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            SearchPersonListView arrayAdapter = new SearchPersonListView(context, R.layout.lv_search_person, data);
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
                            e.printStackTrace();
                        }

                    }, error -> {
                Log.v("ERROR",error.toString());
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("action", "yes");
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

}
