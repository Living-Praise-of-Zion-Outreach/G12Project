package com.lpzoutreach.g12lpz.Utility.sql.php;

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
import com.lpzoutreach.g12lpz.ListView.slidesLessonListView;
import com.lpzoutreach.g12lpz.ListView.slidesParentListView;
import com.lpzoutreach.g12lpz.Models.SlidesModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.toast.utilToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class slidesTransfer {


    public static void get_slides_parent_from_server(Context context, ListView list, ConstraintLayout no_data_found, ConstraintLayout loading, ConstraintLayout no_internet,elUtil eventListenerUtil, String actionEven){
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        no_data_found.setVisibility(View.GONE);
        no_internet.setVisibility(View.GONE);
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            List<SlidesModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/slides/group/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("SLIDES_PARENT_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                SlidesModel row = new SlidesModel();
                                row.setPptID(obj.getInt("pptID"));
                                row.setPpt_logo(obj.getString("ppt_logo"));
                                row.setPpt_name(obj.getString("ppt_name"));
                                row.setPpt_description(obj.getString("ppt_description"));
                                row.setPpt_total_lessons(obj.getString("ppt_total_lessons"));
                                row.setPpt_language(obj.getString("ppt_language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionEvent(actionEven);
                                data.add(row);
                            }


                            slidesParentListView arrayAdapter = new slidesParentListView(context, R.layout.lv_learning_materials, data);
                            list.setAdapter(arrayAdapter);
                            list.setClickable(true);
                            list.setDivider(null);

                            loading.setVisibility(View.GONE);

                            if(data.size()>0){
                                list.setVisibility(View.VISIBLE);
                                no_data_found.setVisibility(View.GONE);
                            }else{
                                list.setVisibility(View.GONE);
                                no_data_found.setVisibility(View.VISIBLE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                no_internet.setVisibility(View.VISIBLE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("action", "get all slides by parent name");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            loading.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void get_slides_lesson_from_server(Context context, ListView list, ConstraintLayout no_data_found, ConstraintLayout loading, ConstraintLayout no_internet,elUtil eventListenerUtil, String actionEvent, String pptID){
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        no_data_found.setVisibility(View.GONE);
        no_internet.setVisibility(View.GONE);
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            List<SlidesModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/slides/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("SLIDES_LESSON_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                SlidesModel row = new SlidesModel();
                                row.setPpt_lessonID(obj.getInt("ppt_lessonID"));
                                row.setPptID(obj.getInt("pptID"));
                                row.setPpt_lesson_logo(obj.getString("ppt_lesson_logo"));
                                row.setPpt_lesson_no(obj.getString("ppt_lesson_no"));
                                row.setPpt_lesson_name(obj.getString("ppt_lesson_name"));
                                row.setPpt_lesson_description(obj.getString("ppt_lesson_description"));
                                row.setPpt_url(obj.getString("ppt_url"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionEvent(actionEvent);
                                data.add(row);
                            }


                            slidesLessonListView arrayAdapter = new slidesLessonListView(context, R.layout.lv_learning_materials, data);
                            list.setAdapter(arrayAdapter);
                            list.setClickable(true);
                            list.setDivider(null);

                            loading.setVisibility(View.GONE);

                            if(data.size()>0){
                                list.setVisibility(View.VISIBLE);
                                no_data_found.setVisibility(View.GONE);
                            }else{
                                list.setVisibility(View.GONE);
                                no_data_found.setVisibility(View.VISIBLE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                no_internet.setVisibility(View.VISIBLE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("pptID", pptID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            loading.setVisibility(View.GONE);
            no_internet.setVisibility(View.VISIBLE);
            utilToast.showErrorNoInternet(context);
        }
    }

}
