package com.lpzoutreach.g12lpz.Utility.sql.php;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.Models.RegisterModel;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;

import java.util.HashMap;
import java.util.Map;

public class registerTransfer {

    public static void register(Context context, RegisterModel data, ProgressDialogBar progressDialogBar){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.BASE_URL +  "android/create"; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.v("Create Account ->", response.toString());

                    String[] values = response.split("-");

                    Toast.makeText(context,response,Toast.LENGTH_LONG).show();
                    progressDialogBar.hide();
                }, error -> {
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("first_name", data.getFirst_name());
                MyData.put("middle_name", data.getMiddle_name());
                MyData.put("last_name", data.getLast_name());
                MyData.put("email", data.getEmail());
                MyData.put("password", data.getPassword());
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }

}
