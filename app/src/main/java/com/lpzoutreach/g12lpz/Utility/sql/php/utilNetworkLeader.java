package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.searchNameListView;
import com.lpzoutreach.g12lpz.Models.PersonalInformationModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class utilNetworkLeader {

    public static void do_get_list_network_leader(Context context, ListView lv, String churchID, String name, elUtil event){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/profile/search-network-leader"; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {

                    ArrayList<PersonalInformationModel> data = new ArrayList<>();

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            PersonalInformationModel rows = new PersonalInformationModel();
                            rows.setProfileID(Integer.parseInt(obj.getString("userID")));
                            rows.setProfile_photo(accessUrl.BASE_URL + obj.getString("profile_photo_path"));
                            rows.setFirst_name(obj.getString("first_name"));
                            rows.setMiddle_name(obj.getString("middle_name"));
                            rows.setLast_name(obj.getString("last_name"));
                            rows.setChu_church_name(obj.getString("church_name"));
                            rows.setNetwork_name(obj.getString("networkName"));
                            rows.setHandler(event);
                            data.add(rows);
                        }

                        searchNameListView arrayAdapter = new searchNameListView(context, R.layout.lv_search_name,data);
                        lv.setAdapter(arrayAdapter);
                        lv.setClickable(true);
                        lv.setDivider(null);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    Log.v("ABLE",response);
                    /*

                    for (int i =0; i < response.length(); i++)
                    {
                        try{
                            JSONObject obj = response.getJSONObject(i);

                            PersonalInformationModel rows = new PersonalInformationModel();
                            rows.setProfile_photo(accessUrl.BASE_URL + obj.getString("profile_photo_path"));
                            rows.setFirst_name(obj.getString("first_name"));
                            rows.setMiddle_name(obj.getString("middle_name"));
                            rows.setLast_name(obj.getString("last_name"));
                            data.add(rows);

                        }catch (JSONException e){
                            Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                        }
                    }

                    searchNameListView arrayAdapter = new searchNameListView(context, R.layout.lv_evangelize,data);
                    lv.setAdapter(arrayAdapter);
                    lv.setClickable(true);
                    lv.setDivider(null);


                */


                }, error -> {
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("churchID", churchID);
                MyData.put("name", name);
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    public static void get_list_network_leaders(Context context, ListView lv, String churchID, String name){
        if(utilNetwork.isConnected(context)){
            ArrayList<PersonalInformationModel> data = new ArrayList<>();

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/profile/search-network-leader";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i =0; i < response.length(); i++)
                    {
                        try{
                            JSONObject obj = response.getJSONObject(i);

                            PersonalInformationModel rows = new PersonalInformationModel();
                            rows.setProfile_photo(accessUrl.BASE_URL + obj.getString("profile_photo_path"));
                            rows.setFirst_name(obj.getString("first_name"));
                            rows.setMiddle_name(obj.getString("middle_name"));
                            rows.setLast_name(obj.getString("last_name"));
                            rows.setChu_church_name(obj.getString("church_name"));
                            rows.setNetwork_name(obj.getString("networkName"));
                            data.add(rows);

                        }catch (JSONException e){
                            Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                        }
                    }

                    searchNameListView arrayAdapter = new searchNameListView(context, R.layout.lv_evangelize,data);
                    lv.setAdapter(arrayAdapter);
                    lv.setClickable(true);
                    lv.setDivider(null);

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

}
