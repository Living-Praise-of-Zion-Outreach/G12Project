package com.lpzoutreach.g12lpz.Utility.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.lpzoutreach.g12lpz.Utility.toast.utilToast;


public class utilNetwork {

    public static void volleyError(Context context, VolleyError error){
        String errorString="";
        if(error instanceof TimeoutError || error instanceof NoConnectionError)
        {
            errorString = "No internet or connection time out error";
            utilToast.showErrorNoInternet(context);
        }
        else if(error instanceof AuthFailureError)
            errorString = "Authentication failure error";
        else if(error instanceof ServerError)
            errorString = "Server connection error";
        else if(error instanceof NetworkError)
            errorString = "Network connection error";
        else if(error instanceof ParseError)
            errorString = "Network connection error";

    }

    public static boolean isConnected(Context context){
        boolean connected = false;
        try{
            ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        }catch (Exception e){
            Log.e("Connectivity Exception",e.getMessage());
        }
        return false;
    }


}
