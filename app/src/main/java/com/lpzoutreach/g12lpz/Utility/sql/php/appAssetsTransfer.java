package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;
import com.lpzoutreach.g12lpz.Models.AppAssetModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.image.utilImage;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperAppAssets;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class appAssetsTransfer {

    public static void download_app_assets(Context context, Handler mHandler, Runnable ChangeActivity, Runnable ExitActivity, ImageView logo, TextView title, TextView subtitle, ProgressBar progressBar){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);


            String url = accessUrl.BASE_URL + "android/app/asset/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("APP_ASSET_GET", response);
                        try{
                            JSONArray j= new JSONArray(response);

                            openHelperAppAssets helper = new openHelperAppAssets(context);
                            List<AppAssetModel> data = new ArrayList<>();

                            if(helper.count_all_app_assets()==j.length()){
                                default_splash_screens(context,mHandler,ChangeActivity,logo,title,subtitle);
                               // Toast.makeText(context,"Existing",Toast.LENGTH_LONG).show();
                            }else{

                                progressBar.setVisibility(View.VISIBLE);

                                helper.delete_app_assets();
                                String folderName = "app_assets";
                                for (int i = 0; i < j.length(); i++) {
                                    JSONObject obj = j.getJSONObject(i);
                                    AppAssetModel rows = new AppAssetModel();
                                    rows.setAppAssetID(obj.getInt("appAssetID"));
                                    rows.setAssetLogo(obj.getString("assetLogo"));
                                    rows.setAssetName(obj.getString("assetName"));
                                    rows.setAssetType(obj.getString("assetType"));
                                    rows.setAssetDescription(obj.getString("assetDescription"));
                                    rows.setCreatedBy(obj.getInt("createdBy"));
                                    rows.setDtCreated(obj.getString("dtCreated"));
                                    rows.setDtUpdated(obj.getString("dtUpdated"));
                                    rows.setUpdatedBy(obj.getInt("updatedBy"));
                                    data.add(rows);
                                }
                                download_app_assets_file_from_server_direct(context,data,helper,folderName+"/",mHandler,ChangeActivity,ExitActivity,logo,title,subtitle,progressBar );

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
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
            mHandler.postDelayed(ExitActivity,3000);
            default_splash_screens(context,mHandler,ChangeActivity,logo,title,subtitle);
        }
    }

    private static void download_app_assets_file_from_server_direct(Context context,List<AppAssetModel> data, openHelperAppAssets helper, String folder, Handler mHandler, Runnable ChangeActivity, Runnable ExitActivity, ImageView logo, TextView title, TextView subtitle, ProgressBar progressBar){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.get(0).getAssetLogo(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        title.setText("Downloading Resources");
                        subtitle.setText(response.getDownloadedFile().getName());
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.get(0).setAndroid_asset_logo_path(path);
                        helper.insert_app_assets(data.get(0));
                        download_app_assets_file_from_server_direct_below(context,data,helper,0,folder, mHandler,ChangeActivity,ExitActivity,logo,title,subtitle,progressBar);

                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }

    private static void download_app_assets_file_from_server_direct_below(Context context,List<AppAssetModel> data, openHelperAppAssets helper,int incrementor, String folder, Handler mHandler, Runnable ChangeActivity, Runnable ExitActivity, ImageView logo, TextView title, TextView subtitle, ProgressBar progressBar){
        int inc =incrementor + 1;
        if(incrementor<data.size()){
            FileLoader.with(context)
                    .load(accessUrl.BASE_URL +  data.get(inc).getAssetLogo(),false) //2nd parameter is optioal, pass true to force load from network
                    .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                    .asFile(new FileRequestListener<File>() {
                        @Override
                        public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                            title.setText("Downloading Resources");
                            subtitle.setText(response.getDownloadedFile().getName());
                            String path = response.getDownloadedFile().getAbsolutePath();
                            data.get(inc).setAndroid_asset_logo_path(path);
                            helper.insert_app_assets(data.get(inc));
                            download_app_assets_file_from_server_direct_below(context,data,helper,inc,folder, mHandler,ChangeActivity,ExitActivity,logo,title,subtitle,progressBar);
                        }
                        @Override
                        public void onError(FileLoadRequest request, Throwable t) {
                            AppAssetModel logo_helper = helper.get_app_assets("g12_logo_text");
                            utilImage.src_path(logo,logo_helper.getAssetLogo());
                            progressBar.setVisibility(View.GONE);
                            title.setText(context.getResources().getString(R.string.powered_by));
                            subtitle.setText(context.getResources().getString(R.string.powered_by_desc));
                            mHandler.postDelayed(ChangeActivity,3000);
                         }
                    });
        }else{
            AppAssetModel logo_helper = helper.get_app_assets("g12_logo_text");
            utilImage.src_path(logo,logo_helper.getAssetLogo());
           // Toast.makeText(context,"Finish",Toast.LENGTH_LONG).show();
            title.setText(context.getResources().getString(R.string.powered_by));
            subtitle.setText(context.getResources().getString(R.string.powered_by_desc));
            mHandler.postDelayed(ChangeActivity,3000);
        }


    }

    private static void default_splash_screens(Context context,Handler mHandler, Runnable ChangeActivity,ImageView logo, TextView title, TextView subtitle ){
        openHelperAppAssets helper = new openHelperAppAssets(context);
        AppAssetModel logo_helper = helper.get_app_assets("g12_logo_text");
        File imgFile = new  File(logo_helper.getAssetLogo());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            logo.setImageBitmap(myBitmap);
        }
        mHandler.postDelayed(ChangeActivity,3000);
        title.setText(context.getResources().getString(R.string.powered_by));
        subtitle.setText(context.getResources().getString(R.string.powered_by_desc));
    }


}
