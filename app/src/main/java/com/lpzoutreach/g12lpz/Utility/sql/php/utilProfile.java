package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.PreviewSetUpModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;

import java.util.HashMap;
import java.util.Map;

public class utilProfile {

    public static void set_up_mode_update(Context context, PreviewSetUpModel data){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/profile/setup-mode/update";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    //Toast.makeText(context, response,Toast.LENGTH_LONG).show();
                    sharedData.set_church_name(context,data.getChurchID()+"~"+data.getChurch_photo() + "~" +data.getChurch_name() + "~" + data.getChurch_address());
                    sharedData.set_network_leader_name(context,data.getUserID_network_leader()+"~"+data.getPhoto_network_leader() + "~" + data.getName_network_leader());
                    sharedData.set_invited_by(context,data.getUserID_invite() + "~" + data.getPhoto_invite() + "~" + data.getName_invite() + "~" + data.getNetwork_name_invite());
                }, error -> {
            Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("action",  "yes");
                MyData.put("userID",  String.valueOf(sharedData.get_userID(context)));
                MyData.put("churchID",   String.valueOf(data.getChurchID()));
                MyData.put("userID_leader",  String.valueOf(data.getUserID_network_leader()));
                MyData.put("userID_invite",  String.valueOf(data.getUserID_invite()));
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

    public static void change_email(Context context, String email, Button update, Button cancel, Button change, Button verify, TextView input, ProgressDialogBar progressDialogBar){

        //Toast.makeText(context,String.valueOf(sharedData.get_userID(context)),Toast.LENGTH_LONG).show();

        if(utilNetwork.isConnected(context)){

        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/profile/change-email";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    String[] resp = response.split("-");
                    if(resp[0].equals("success")){
                        sharedData.set_email(context,email);
                        Toast.makeText(context, resp[1],Toast.LENGTH_LONG).show();
                        cancel.setVisibility(View.GONE);
                        update.setVisibility(View.GONE);
                        verify.setVisibility(View.VISIBLE);
                        change.setVisibility(View.VISIBLE);
                        input.setFocusable(false);
                    }else{
                        Toast.makeText(context, resp[1],Toast.LENGTH_LONG).show();
                    }
                    progressDialogBar.hide();
                }, error -> {
            Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("action",  "yes");
                MyData.put("userID",String.valueOf(sharedData.get_userID(context)));
                MyData.put("email",  email);
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);

        }else{
            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }
    }

    public static void change_password(Context context, String email, EditText current_password, EditText new_password, EditText confirm_password, ProgressDialogBar progressDialogBar, elUtil eventListenerUtil,String userID){

        //Toast.makeText(context,String.valueOf(sharedData.get_userID(context)),Toast.LENGTH_LONG).show();

        if(utilNetwork.isConnected(context)){

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/profile/changepassword-direct";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("changepasswrd",response);

                        String[] resp = response.split("~");
                        if(resp[0].equals("success")){
                            Toast.makeText(context, resp[1],Toast.LENGTH_LONG).show();
                            if(current_password != null){
                                current_password.setText("");
                            }

                            new_password.setText("");
                            confirm_password.setText("");
                            eventListenerUtil.myCallBack("complete-change-password","");

                            progressDialogBar.hide();
                        }else{
                            Toast.makeText(context, resp[1],Toast.LENGTH_LONG).show();
                            progressDialogBar.hide();
                        }

                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                progressDialogBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("action",  "yes");
                    MyData.put("userID",userID);
                    MyData.put("email",  email);
                    if(current_password!=null){
                        MyData.put("password",  current_password.getText().toString());
                    }
                    MyData.put("newpassword",  new_password.getText().toString());
                    MyData.put("confirmpassword",  confirm_password.getText().toString());
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }
    }

    public static void change_privacy(Context context, String privacy, ProgressDialogBar progressDialogBar){

        if(utilNetwork.isConnected(context)){

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/profile/change_privacy_settings";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        String[] resp = response.split("~");
                        if(resp[0].equals("success")){
                            Toast.makeText(context, resp[1],Toast.LENGTH_LONG).show();
                            sharedData.set_profile_privacy(context,privacy);
                        }else{
                            Toast.makeText(context, resp[1],Toast.LENGTH_LONG).show();
                        }
                        progressDialogBar.hide();
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                progressDialogBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID",String.valueOf(sharedData.get_userID(context)));
                    MyData.put("privacy",  privacy);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);

        }else{
            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
            progressDialogBar.hide();
        }
    }


}
