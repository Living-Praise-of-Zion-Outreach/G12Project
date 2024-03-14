package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.AuthModel;
import com.lpzoutreach.g12lpz.Models.WebResponseModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.toast.utilToast;

import java.util.HashMap;
import java.util.Map;

public class authTransfer {

    public static void google_in_auth(Context context, AuthModel data, ProgressDialogBar progressDialogBar, elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/auth/google/sign-in";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GOOGLE_AUTH",response.toString());
                                String[] obj = response.split("~");
                                sharedData.set_email_status(context,obj[0]);
                                sharedData.set_up_mode(context,obj[1]);
                                sharedData.set_userID(context,  Integer.parseInt(obj[2]));
                                sharedData.set_password(context,"");
                                sharedData.set_email(context, obj[3]);
                                sharedData.set_system_role(context, obj[4]);
                                sharedData.set_access_level_id(context, Integer.parseInt(obj[5]));

                                Log.v("GOOGLE",response);

                                if(obj[6].equals("")){
                                    sharedData.set_profile_photo(context, "");
                                }else{
                                    sharedData.set_profile_photo(context, obj[6]);
                                }

                                sharedData.set_username(context,"");
                                sharedData.set_profile_privacy(context, "");
                                //Toast.makeText(getApplicationContext(),"000-"+values[9],Toast.LENGTH_LONG).show();
                                eventListenerUtil.myCallBack("proceed-log-in","");
                        progressDialogBar.hide();
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                //eventListenerUtil.myCallBack("proceed-log-in","");
                progressDialogBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("google_id", data.getGoogle_id());
                    MyData.put("google_name",data.getGoogle_name());
                    MyData.put("google_email",data.getGoogle_email());
                    MyData.put("google_family_name",data.getGoogle_family_name());
                    MyData.put("google_given_name",data.getGoogle_given_name());
                    MyData.put("google_photo_url",data.getGoogle_photo_url());
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void check_email_valid(Context context, EditText email, ProgressDialogBar progressBar, elUtil eventListenerUtil){
        progressBar.show();
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            WebResponseModel row = new WebResponseModel();

            String url = accessUrl.BASE_URL + "android/check-email-valid";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_LIST",response.toString());
                        try{
                            progressBar.hide();
                            eventListenerUtil.myCallBack("send-code-email", response);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                //Toast.makeText(context, "asd" + error.toString(), Toast.LENGTH_LONG).show();
                utilToast.show(context,error.toString());
                progressBar.hide();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("email", email.getText().toString());
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            progressBar.hide();
        }
    }

}
