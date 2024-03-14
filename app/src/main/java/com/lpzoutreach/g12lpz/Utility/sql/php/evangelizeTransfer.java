package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.Models.EvangelismModel;
import com.lpzoutreach.g12lpz.Utility.date.utilDate;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperEvangelism;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class evangelizeTransfer {

    public static void sync(Context context,ProgressDialogBar progressDialogBar){
        progressDialogBar.show();
        reset_data(context,progressDialogBar);
    }

    public static void sync_continuous(Context context,ProgressDialogBar progressDialogBar){
        progressDialogBar.show();
        reset_data_continuous(context,progressDialogBar);
    }

    private static void syn_now(Context context,ProgressDialogBar progressDialogBar){
        openHelperEvangelism openHelper = new openHelperEvangelism(context);
        List<EvangelismModel> data = openHelper.populate_sync_evangelize();
        if(data.size()>0){
            for(int i=0;i<data.size();i++){
                row_add(context,data.get(i));
                if(i==data.size()-1){
                    progressDialogBar.hide();
                    Toast.makeText(context,"You have successfully sync the data from server.",Toast.LENGTH_LONG).show();
                }
            }
        }else{
            progressDialogBar.hide();
        }
    }

    private static void syn_now_continuous(Context context,ProgressDialogBar progressDialogBar){
        openHelperEvangelism openHelper = new openHelperEvangelism(context);
        List<EvangelismModel> data = openHelper.populate_sync_evangelize();
        if(data.size()>0){
            for(int i=0;i<data.size();i++){
                row_add(context,data.get(i));
                if(i==data.size()-1){
                    progressDialogBar.hide();
                    consolidateTransfer.sync(context,progressDialogBar);
               }
            }
        }else{
            progressDialogBar.hide();
        }
    }

    private static void reset_data(Context context, ProgressDialogBar progressDialogBar){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/mission/individual/reset/sync"; // <----enter your post url here
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

    private static void reset_data_continuous(Context context, ProgressDialogBar progressDialogBar){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/mission/individual/reset/sync"; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.v("THIS IS TO RESET DATA",response.toString());
                    syn_now_continuous(context,progressDialogBar);
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

    private static void row_add(Context context, EvangelismModel data){

        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/mission/individual/sync";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.v("ADD NEW RECORD->",response.toString());
                    //Toast.makeText(context,response.toString(),Toast.LENGTH_LONG).show();
                    //Toast.makeText(context,String.valueOf(data.getIsConsolidate()),Toast.LENGTH_LONG).show();
                }, error -> {
                    Log.v("Error add new record->",error.toString());
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("action","Read");
                MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                MyData.put("temp_profileID", String.valueOf(data.getTempProfileID()));
                MyData.put("profile_photo_path", String.valueOf(data.getProfilePhotoPath()));
                MyData.put("raw_photo_bitmap",String.valueOf(data.getPhotoBitmap()));
                MyData.put("first_name", data.getFirst_name());
                MyData.put("middle_name", data.getMiddle_name());
                MyData.put("last_name", data.getLast_name());
                MyData.put("sex",data.getSex());

                MyData.put("date_of_birth", data.getDate_of_birth());
                MyData.put("civil_status", data.getCivil_status());
                MyData.put("loc_country", data.getLoc_country());
                MyData.put("loc_province", data.getLoc_province());
                MyData.put("loc_city", data.getLoc_city());
                MyData.put("loc_barangay", data.getLoc_barangay());
                MyData.put("loc_address", data.getLoc_address());

                MyData.put("con_mobile_no", data.getCon_mobile_no());
                MyData.put("email", data.getEmail());
                MyData.put("isEvangelize", String.valueOf(data.getIsEvangelize()));
                MyData.put("evangelizeDt", data.getEvangelizeDt());
                MyData.put("isDrop", String.valueOf(data.getIsDrop()));
                MyData.put("dropDt", data.getDropDt());
                MyData.put("isConsolidate", String.valueOf(data.getIsConsolidate()));

                MyData.put("consolidateDt", String.valueOf(data.getConsolidateDt()));
                MyData.put("delFlag", String.valueOf(data.getDelFlag()));
                MyData.put("actionFlag", String.valueOf(data.getActionFlag()));
                MyData.put("createdBy", String.valueOf(data.getCreatedBy()));
                MyData.put("dtCreated", String.valueOf(data.getDtCreated()));
                MyData.put("updatedBy", String.valueOf(data.getUpdatedBy()));
                MyData.put("dtUpdated", String.valueOf(data.getDtUpdated()));

                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    public static void download_Evangelized_List(Context context, ProgressDialogBar progressDialogBar){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            openHelperEvangelism shelper = new openHelperEvangelism(context);
            shelper.delete_all();

            String url = accessUrl.BASE_URL + "android/mission/evangelism/get-evangelize-list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("LIST_EVANGELIZED",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                EvangelismModel data = new EvangelismModel();
                                data.setTempProfileID(obj.getInt("temp_profileID"));
                                data.setUserID(obj.getInt("userID"));
                                data.setProfilePhotoPath( accessUrl.BASE_URL + obj.getString("profile_photo_path"));
                                data.setFirst_name(obj.getString("first_name"));
                                data.setMiddle_name(obj.getString("middle_name"));
                                data.setLast_name(obj.getString("last_name"));
                                data.setDate_of_birth(utilDate.getFromServer_DatePickerFormat(obj.getString("date_of_birth")));
                                data.setSex(obj.getString("sex"));
                                data.setCivil_status(obj.getString("civil_status"));
                                data.setLoc_country(obj.getString("loc_country"));
                                data.setLoc_province(obj.getString("loc_province"));
                                data.setLoc_country(obj.getString("loc_city"));
                                data.setLoc_barangay(obj.getString("loc_barangay"));
                                data.setLoc_address(obj.getString("loc_address"));
                                data.setCon_mobile_no(obj.getString("con_mobile_no"));
                                data.setEmail(obj.getString("email"));
                                data.setIsEvangelize(obj.getInt("isEvangelize"));
                                data.setEvangelizeDt(utilDate.getFromServer_DatePickerFormat(obj.getString("evangelizeDt")));
                                data.setIsDrop(obj.getInt("isDrop"));
                                data.setDropDt(utilDate.getFromServer_DatePickerFormat(obj.getString("dropDt")));
                                data.setIsConsolidate(obj.getInt("isConsolidate"));
                                data.setConsolidateDt(utilDate.getFromServer_DatePickerFormat(obj.getString("consolidateDt")));
                                data.setEvangelizeType(obj.getString("evangelizeType"));
                                data.setDelFlag(obj.getInt("delFlag"));
                                data.setActionFlag(obj.getInt("actionFlag"));
                                data.setCreatedBy(obj.getInt("createdBy"));
                                data.setDtCreated(obj.getString("dtCreated"));
                                data.setUpdatedBy(obj.getInt("updatedBy"));
                                data.setDtUpdated(obj.getString("dtUpdated"));
                                data.setAndroidPhotoPath("");
                                data.setPhotoBitmap("");
                                data.setEvangelizeID(obj.getInt("evangelizeID"));

                                shelper.insert_from_server(data);

                            }

                            consolidateTransfer.download_Consolidate_List(context,progressDialogBar);

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
