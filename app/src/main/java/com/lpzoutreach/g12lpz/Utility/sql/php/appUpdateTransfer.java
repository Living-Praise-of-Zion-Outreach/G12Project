package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.WhatsNewListView;
import com.lpzoutreach.g12lpz.Models.NotifyAppUpdateModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperAppUpdate;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.toast.utilToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class appUpdateTransfer {

    public static void update_checker(Context context, FragmentManager fm, elUtil eventListener){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/app/update-checker";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        int latest_update = Integer.parseInt(response);
                        if(latest_update > sharedData.UPDATE_APP_ID){
                            //get_app_update(context,fm,eventListener);
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("action", "");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            utilToast.showErrorNoInternet(context);


        }
    }

    public  static void get_app_update(Context context, ListView listView, ConstraintLayout loading){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/app/get-update";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GOOGLE_AUTH",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);

                            List<NotifyAppUpdateModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                //Toast.makeText(context,obj.getString("appUpdateID"),Toast.LENGTH_LONG).show();
                                NotifyAppUpdateModel rows = new NotifyAppUpdateModel();
                                rows.setAppUpdateID(obj.getInt("appUpdateID"));
                                rows.setAppTitle(obj.getString("appTitle"));
                                rows.setAppMinRequirements(obj.getString("appMinRequirements"));
                                rows.setAppMaxRequirements(obj.getString("appMaxRequirements"));
                                rows.setAppDescription(obj.getString("appDescription"));
                                rows.setAppVersion(obj.getString("appVersion"));
                                rows.setAppPath(obj.getString("appPath"));
                                rows.setPublishReady(String.valueOf(obj.getInt("publishReady")));
                                rows.setAppDatePublished(obj.getString("appDatePublished"));
                                rows.setDtCreated(utilDate.getFromServer_DatePickerFormat(obj.getString("dtCreated")));
                                data.add(rows);
                            }

                            openHelperAppUpdate helper = new openHelperAppUpdate(context);
                            helper.delete();
                            helper.insert(data);

                            WhatsNewListView adapter= new WhatsNewListView(context,R.layout.lv_whats_new,data);
                            listView.setAdapter(adapter);
                            listView.setClickable(true);
                            listView.setDivider(null);

                            loading.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                        }

                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);

                openHelperAppUpdate helper = new openHelperAppUpdate(context);
                List<NotifyAppUpdateModel> data = helper.get_all();
                WhatsNewListView adapter= new WhatsNewListView(context,R.layout.lv_whats_new,data);
                listView.setAdapter(adapter);


            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("action", "");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            utilToast.showErrorNoInternet(context);
            loading.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            openHelperAppUpdate helper = new openHelperAppUpdate(context);
            List<NotifyAppUpdateModel> data = helper.get_all();
            WhatsNewListView adapter= new WhatsNewListView(context,R.layout.lv_whats_new,data);
            listView.setAdapter(adapter);
        }
    }

    public  static void get_app_update_eventListener(Context context, elUtil eventListenerUtil){
        openHelperAppUpdate helper = new openHelperAppUpdate(context);
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/app/get-update";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GOOGLE_AUTH",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);

                            List<NotifyAppUpdateModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                //Toast.makeText(context,obj.getString("appUpdateID"),Toast.LENGTH_LONG).show();
                                NotifyAppUpdateModel rows = new NotifyAppUpdateModel();
                                rows.setAppUpdateID(obj.getInt("appUpdateID"));
                                rows.setAppTitle(obj.getString("appTitle"));
                                rows.setAppMinRequirements(obj.getString("appMinRequirements"));
                                rows.setAppMaxRequirements(obj.getString("appMaxRequirements"));
                                rows.setAppDescription(obj.getString("appDescription"));
                                rows.setAppVersion(obj.getString("appVersion"));
                                rows.setAppPath(obj.getString("appPath"));
                                rows.setPublishReady(String.valueOf(obj.getInt("publishReady")));
                                rows.setAppDatePublished(obj.getString("appDatePublished"));
                                rows.setDtCreated(utilDate.getFromServer_DatePickerFormat(obj.getString("dtCreated")));
                                data.add(rows);
                            }

                            helper.delete();
                            helper.insert(data);

                            eventListenerUtil.myCallBack("reload-update",data);

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                List<NotifyAppUpdateModel> data = helper.get_all();
                eventListenerUtil.myCallBack("reload-update",data);

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("action", "");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            utilToast.showErrorNoInternet(context);
            List<NotifyAppUpdateModel> data = helper.get_all();
            eventListenerUtil.myCallBack("reload-update",data);
        }
    }



}
