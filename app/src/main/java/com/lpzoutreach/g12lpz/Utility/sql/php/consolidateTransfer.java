package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.Models.ConsolidateModel;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperConsolidate;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class consolidateTransfer {

    public static void sync(Context context,ProgressDialogBar progressDialogBar){
        progressDialogBar.show();
        reset_data(context,progressDialogBar);
    }

    private static void reset_data(Context context, ProgressDialogBar progressDialogBar){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/consolidate/list/reset"; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.v("THIS IS TO RESET DATA",response.toString());
                    syn_now(context,progressDialogBar);
                }, error -> {

        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    private static void syn_now(Context context,ProgressDialogBar progressDialogBar){
        openHelperConsolidate openHelper = new openHelperConsolidate(context);
        List<ConsolidateModel> data = openHelper.get_all_list();
        if(data.size()>0){
            for(int i=0;i<data.size();i++){
                if(data.get(i).getTemp_profileID()>0){
                    row_add(context,data.get(i));
                }
                if(i==data.size()-1){
                    progressDialogBar.hide();
                    Toast.makeText(context,"You have successfully sync the data from server.",Toast.LENGTH_LONG).show();
                }
            }
        }else{
            progressDialogBar.hide();
        }
    }

    private static void row_add(Context context, ConsolidateModel data){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/consolidate/list/sync";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.v("ADD NEW RECORD->", response);
              }, error -> {
            Log.v("Error add new record->",error.toString());
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("consolidateID",String.valueOf(data.getConsolidateID()));
                MyData.put("linkedUserID",String.valueOf(data.getLinkedUserID()));
                MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                MyData.put("tempProfileID", String.valueOf(data.getTemp_profileID()));
                MyData.put("book_name", String.valueOf(data.getBook_name()));
                MyData.put("lessonNo",String.valueOf(data.getLessonNo()));
                MyData.put("lessonTitle", data.getLessonTitle());
                MyData.put("dtTrain", data.getDtTrain());
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }


    public static void download_Consolidate_List(Context context, ProgressDialogBar progressDialogBar){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            openHelperConsolidate shelper = new openHelperConsolidate(context);
            shelper.delete_all(String.valueOf(sharedData.get_userID(context)));
            List<ConsolidateModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/consolidate/list/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_CONSOLIDATE", response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                ConsolidateModel row = new ConsolidateModel();
                                row.setConsolidateID(obj.getInt("consolidateID"));
                                row.setLinkedUserID(obj.getInt("linkedUserID"));
                                row.setTemp_profileID(obj.getInt("tempProfileID"));
                                row.setUserID(obj.getInt("userID"));
                                row.setBook_name(obj.getString("book_name"));
                                row.setLessonNo(obj.getInt("lessonNo"));
                                row.setLessonTitle(obj.getString("lessonTitle"));
                                row.setDtTrain(utilDate.getFromServer_DatePickerFormat(obj.getString("dtTrain")));
                                data.add(row);
                            }

                            shelper.insert(data);

                            churchTransfer.syncChurch(context,progressDialogBar);
                            //progressDialogBar.hide();

                        }catch (JSONException e) {
                            progressDialogBar.hide();
                            e.printStackTrace();
                        }
                    }, error -> {
                progressDialogBar.hide();
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

}
