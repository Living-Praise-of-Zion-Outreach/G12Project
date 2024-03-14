package com.lpzoutreach.g12lpz.Utility.sql.php;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.system.accessVariable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class utilSpatial {

    public static void get_spatial_country(Context context,AutoCompleteTextView actObject){
        if(utilNetwork.isConnected(context)){
            ArrayList<String> data = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = "https://www.lpzoutreach.com/android/spatial/country/province/city/barangay";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i =0; i < response.length(); i++)
                    {
                        try{
                            JSONObject obj = response.getJSONObject(i);
                            data.add(obj.getString("NAME_0").toUpperCase());
                        }catch (JSONException e){
                            Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
                    actObject.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,context.getResources().getString(R.string.system_error_no_internet_connection),Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
        }else{
            //Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    public static void get_spatial_province(Context context,AutoCompleteTextView actObject,String filtered){
        if(utilNetwork.isConnected(context)){
            ArrayList<String> data = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = "https://www.lpzoutreach.com/android/spatial/"+filtered+"/province/city/barangay";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i =0; i < response.length(); i++)
                    {
                        try{
                            JSONObject obj = response.getJSONObject(i);
                            data.add(obj.getString("NAME_1").toUpperCase());
                        }catch (JSONException e){
                            Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
                    actObject.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,context.getResources().getString(R.string.system_error_no_internet_connection),Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
        }else{
            //Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    public static void get_spatial_city(Context context,AutoCompleteTextView actObject,String country,String province){
        if(utilNetwork.isConnected(context)){
            ArrayList<String> data = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = "https://www.lpzoutreach.com/android/spatial/"+country+"/"+province+"/city/barangay";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i =0; i < response.length(); i++)
                    {
                        try{
                            JSONObject obj = response.getJSONObject(i);
                            data.add(obj.getString("NAME_2").toUpperCase());
                        }catch (JSONException e){
                            Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
                    actObject.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,context.getResources().getString(R.string.system_error_no_internet_connection),Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
        }else{
            //Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    public static void get_spatial_barangay(Context context,AutoCompleteTextView actObject,String country,String province, String city){
        if(utilNetwork.isConnected(context)){
            ArrayList<String> data = new ArrayList<>();
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String url = "https://www.lpzoutreach.com/android/spatial/"+country+"/"+province+"/"+city+"/barangay";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i =0; i < response.length(); i++)
                    {
                        try{
                            JSONObject obj = response.getJSONObject(i);
                            data.add(obj.getString("NAME_3").toUpperCase());
                        }catch (JSONException e){
                            Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
                    actObject.setAdapter(adapter);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,context.getResources().getString(R.string.system_error_no_internet_connection),Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(jsonArrayRequest);
        }else{
            //Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }


    public static void getSpatial(Context context, String name_0, String name_1, String name_2, AutoCompleteTextView actObject){

        if(utilNetwork.isConnected(context)){

        ArrayList<String> data = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = accessUrl.SPATIAL_LOOK_UP + "?" + accessVariable.SPATIAL_NAME_0 + "=" + name_0 + "&&" + accessVariable.SPATIAL_NAME_1 + "=" + name_1 + "&&" + accessVariable.SPATIAL_NAME_2 + "=" + name_2;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i =0; i < response.length(); i++)
                {
                    try{
                        JSONObject obj = response.getJSONObject(i);
                        data.add(obj.getString(accessVariable.SPATIAL_NAME));
                    }catch (JSONException e){
                        Toast.makeText(context, context.getResources().getString(R.string.system_error_json_Error), Toast.LENGTH_LONG).show();
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_drop_down_item, data);
                actObject.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,context.getResources().getString(R.string.system_error_no_internet_connection),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

        }
    }

}
