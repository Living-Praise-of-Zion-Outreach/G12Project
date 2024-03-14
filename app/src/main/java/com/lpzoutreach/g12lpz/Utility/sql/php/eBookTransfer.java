package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.AppBarLayout;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.EbookModel;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressHorizontalBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperEBook;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.toast.utilToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class eBookTransfer {

    public static void get_ebook_list(Context context, ConstraintLayout ll_no_data_found, ConstraintLayout loading, ConstraintLayout no_internet,  ConstraintLayout content, EbookModel filter){
        content.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        no_internet.setVisibility(View.GONE);
        ll_no_data_found.setVisibility(View.GONE);

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setEventListener(filter.getEventListener());
                                row.setActionEvent(filter.getActionEvent());
                                row.setContentData(true);
                                data.add(row);
                            }

                            content.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);

                            filter.getEventListener().myCallBack("load-ebook-recyclerview", data);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                //Toast.makeText(context, "asd" + error.toString(), Toast.LENGTH_LONG).show();
                utilToast.show(context,error.toString());

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookName", filter.getBookName());
                    MyData.put("orderType", filter.getOrderType());
                    MyData.put("limit", filter.getLimit());
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            no_internet.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
    public static void get_ebook_continous_list(Context context, ProgressBar progressBar, EbookModel filter){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setEventListener(filter.getEventListener());
                                row.setActionEvent(filter.getActionEvent());
                                row.setContentData(true);
                                data.add(row);
                            }

                            progressBar.setVisibility(View.GONE);
                            filter.getEventListener().myCallBack("load-ebook-recyclerview", data);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                //Toast.makeText(context, "asd" + error.toString(), Toast.LENGTH_LONG).show();
                utilToast.show(context,error.toString());
                progressBar.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("ebookID", String.valueOf(filter.getEbookID()));
                    MyData.put("bookName", filter.getBookName());
                    MyData.put("orderType", filter.getOrderType());
                    MyData.put("limit", filter.getLimit());
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            progressBar.setVisibility(View.GONE);
        }
    }
    public static void get_ebook(Context context, ConstraintLayout loading, ConstraintLayout no_internet, AppBarLayout header, NestedScrollView content, elUtil eventListenerUtil, String ebookID, String actionEvent){
        loading.setVisibility(View.VISIBLE);
        no_internet.setVisibility(View.GONE);
        header.setVisibility(View.GONE);
        content.setVisibility(View.GONE);

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            EbookModel row = new EbookModel();

            String url = accessUrl.BASE_URL + "android/ebook/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_GET",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setAuthorLogo(obj.getString("bookAuthorLogo"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setInfo_header(obj.getString("info_header"));
                                row.setInfo_body(obj.getString("info_body"));
                                row.setInfo_url_type(obj.getString("info_url_type"));
                                row.setInfo_url(obj.getString("info_url"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                            }

                            eventListenerUtil.myCallBack(actionEvent, row);
                            header.setVisibility(View.VISIBLE);
                            content.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);

                        }catch (JSONException e) {
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("ebookID", ebookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            eventListenerUtil.myCallBack(actionEvent,null);
            utilToast.showErrorNoInternet(context);
            no_internet.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }


    public static void add_historical_analytics(Context context, TextView views, TextView favorites, TextView downloads, String ebookID, String type, String action){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/ebook/history/analytics";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_GET",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                if(views!=null){
                                    views.setText(obj.getString("view"));
                                }
                                if(favorites !=null){
                                    favorites.setText(obj.getString("favorite"));
                                }
                                if(downloads!=null){
                                    downloads.setText(obj.getString("download"));
                                }
                            }
                        }catch (JSONException e) {
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("ebookID", ebookID);
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    MyData.put("type", type);
                    MyData.put("action", action);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void add_to_favorite(Context context, ProgressDialogBar progressHorizontalBar, String ebookID, elUtil eventListenerUtil, String androidPath){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            EbookModel row = new EbookModel();

            String url = accessUrl.BASE_URL + "android/ebook/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_GET",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setAuthorLogo(obj.getString("bookAuthorLogo"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setInfo_header(obj.getString("info_header"));
                                row.setInfo_body(obj.getString("info_body"));
                                row.setInfo_url_type(obj.getString("info_url_type"));
                                row.setInfo_url(obj.getString("info_url"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setAdd_to_favorite(1);
                                row.setAndroidPhotoPath(androidPath);
                            }
                            openHelperEBook helper = new openHelperEBook(context);
                            helper.delete(String.valueOf(row.getEbookID()));
                            helper.insert(row);
                            if(progressHorizontalBar!=null){
                                progressHorizontalBar.hide();
                            }
                            eventListenerUtil.myCallBack("add-trigger-favorite-event",ebookID);

                        }catch (JSONException e) {
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("ebookID", ebookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
        }
    }




    public static void remove_to_favorite(Context context, ProgressDialogBar progressHorizontalBar, String ebookID, elUtil eventListenerUtil, String androidPath){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            EbookModel row = new EbookModel();

            String url = accessUrl.BASE_URL + "android/ebook/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_GET",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setAuthorLogo(obj.getString("bookAuthorLogo"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setInfo_header(obj.getString("info_header"));
                                row.setInfo_body(obj.getString("info_body"));
                                row.setInfo_url_type(obj.getString("info_url_type"));
                                row.setInfo_url(obj.getString("info_url"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setAdd_to_favorite(0);
                                row.setAndroidPhotoPath(androidPath);
                            }
                            openHelperEBook helper = new openHelperEBook(context);
                            helper.delete(String.valueOf(row.getEbookID()));
                            helper.insert(row);
                            if(progressHorizontalBar!=null){
                                progressHorizontalBar.hide();
                            }

                            eventListenerUtil.myCallBack("remove-trigger-favorite-event","");

                        }catch (JSONException e) {
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("ebookID", ebookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void download_ebook(Context context, ProgressHorizontalBar progressHorizontalBar, String ebookID, elUtil eventListenerUtil, int add_to_favorite){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            EbookModel row = new EbookModel();

            String url = accessUrl.BASE_URL + "android/ebook/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_GET",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setAuthorLogo(obj.getString("bookAuthorLogo"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setInfo_header(obj.getString("info_header"));
                                row.setInfo_body(obj.getString("info_body"));
                                row.setInfo_url_type(obj.getString("info_url_type"));
                                row.setInfo_url(obj.getString("info_url"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setAdd_to_favorite(add_to_favorite);
                            }

                            openHelperEBook helper = new openHelperEBook(context);
                            helper.delete(String.valueOf(row.getEbookID()));

                            download_file_ebook(context,row,helper,"material_book/ebook/", progressHorizontalBar,eventListenerUtil);


                        }catch (JSONException e) {
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("ebookID", ebookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
        }
    }

    private static void download_file_ebook(Context context, EbookModel data, openHelperEBook helper, String folder, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){

        String[] strPath = data.getBookUrlPath().split("/");
        String xPath =  "/data/user/0/com.lpzoutreach.g12project/files/material_book/ebook/" + strPath[strPath.length-1];
        File file = new File(xPath);
        file.delete();
        if(file.exists()){
            try {
                file.getCanonicalFile().delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(file.exists()){
                context.deleteFile(file.getName());
            }
        }

        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getBookUrlPath(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroidPhotoPath(path);
                        helper.insert(data);
                        progressHorizontalBar.hide();
                        eventListenerUtil.myCallBack("download-complete-ebook","");
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        //Toast.makeText(context,request.toString(),Toast.LENGTH_LONG).show();
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                }
                );
    }

    public static void get_similar_ebook_list(Context context, String Booktag, elUtil eventListener, String actionEvent, int eBookID){

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/similar/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("SIMILAREBOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setEventListener(eventListener);
                                row.setActionEvent(actionEvent);
                                row.setContentData(true);
                                data.add(row);
                            }
                            eventListener.myCallBack("load-similar-books",data);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("ebookID",String.valueOf(eBookID));
                    MyData.put("bookName", "");
                    MyData.put("bookTag", Booktag);
                    MyData.put("bookCategory", "");
                    MyData.put("bookAuthor", "");
                    MyData.put("language", "");
                    MyData.put("bookGenre", "");
                    MyData.put("limit", " LIMIT 0,10");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
        }
    }



    public static void get_count_ebook_list(Context context,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            List<EbookModel> data = new ArrayList<>();
            String url = accessUrl.BASE_URL + "android/ebook/count/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("EBOOK_LIST",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                eventListenerUtil.myCallBack("count-all-ebook", obj.getInt("total"));
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                eventListenerUtil.myCallBack("count-all-ebook", "?");
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookName", "all-books");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            eventListenerUtil.myCallBack("count-all-ebook", "?");
        }
    }

    public static void get_recently_added_ebook_list(Context context,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/top/10-list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("RECENTLY_ADDED_EBOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setEventListener(eventListenerUtil);
                                row.setContentData(true);
                                data.add(row);
                            }

                            eventListenerUtil.myCallBack("load-top-10-recently-added-ebooks", data);

                        }catch (JSONException e) {
                            eventListenerUtil.myCallBack("load-top-10-recently-added-ebooks", null);
                            e.printStackTrace();
                        }
                    }, error -> {
                eventListenerUtil.myCallBack("load-top-10-recently-added-ebooks", null);
                utilToast.show(context,error.toString());

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("type", "dtCreated");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            eventListenerUtil.myCallBack("load-top-10-recently-added-ebooks", null);
        }
    }

    public static void get_most_viewed_ebook_list(Context context,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/top/10-list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("RECENTLY_ADDED_EBOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setEventListener(eventListenerUtil);
                                row.setContentData(true);
                                data.add(row);
                            }

                            eventListenerUtil.myCallBack("load-top-10-most-viewed-ebooks", data);

                        }catch (JSONException e) {
                            eventListenerUtil.myCallBack("load-top-10-most-viewed-ebooks", null);
                            e.printStackTrace();
                        }
                    }, error -> {
                eventListenerUtil.myCallBack("load-top-10-recently-added-ebooks", null);
                utilToast.show(context,error.toString());

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("type", "view");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            eventListenerUtil.myCallBack("load-top-10-most-viewed-ebooks", null);
        }
    }

    public static void get_most_recommended_ebook_list(Context context,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/top/10-list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("RECENTLY_ADDED_EBOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setEventListener(eventListenerUtil);
                                row.setContentData(true);
                                data.add(row);
                            }

                            eventListenerUtil.myCallBack("load-top-10-most-recommended-ebooks", data);

                        }catch (JSONException e) {
                            eventListenerUtil.myCallBack("load-top-10-most-recommended-ebooks", null);
                            e.printStackTrace();
                        }
                    }, error -> {
                eventListenerUtil.myCallBack("load-top-10-most-added-ebooks", null);
                utilToast.show(context,error.toString());

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("type", "favorite");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            eventListenerUtil.myCallBack("load-top-10-most-recommended-ebooks", null);
        }
    }

    public static void get_most_choice_ebook_list(Context context,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/top/10-list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("RECENTLY_ADDED_EBOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setEventListener(eventListenerUtil);
                                row.setContentData(true);
                                data.add(row);
                            }

                            eventListenerUtil.myCallBack("load-top-10-most-choice-ebooks", data);

                        }catch (JSONException e) {
                            eventListenerUtil.myCallBack("load-top-10-most-choice-ebooks", null);
                            e.printStackTrace();
                        }
                    }, error -> {
                eventListenerUtil.myCallBack("load-top-10-most-choice-ebooks", null);
                utilToast.show(context,error.toString());

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("type", "download");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            eventListenerUtil.myCallBack("load-top-10-most-choice-ebooks", null);
        }
    }


    public static void get_ebook_list_by_author(Context context, ConstraintLayout loading, ConstraintLayout no_internet,  ConstraintLayout content, String bookAuthor, elUtil eventListenerUtil, String actionEvent){
        content.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        no_internet.setVisibility(View.GONE);

        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            ArrayList<EbookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/ebook/get/author-books";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("AUTHOR_EBOOK_LIST",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                EbookModel row = new EbookModel();
                                row.setEbookID(obj.getInt("ebookID"));
                                row.setBookCoverUrl(obj.getString("bookCoverUrl"));
                                row.setBookName(obj.getString("bookName"));
                                row.setBookTagLine(obj.getString("bookTagLine"));
                                row.setBookShortDescription(obj.getString("bookShortDescription"));
                                row.setBookCategory(obj.getString("bookCategory"));
                                row.setBookTag(obj.getString("bookTag"));
                                row.setBookGenre(obj.getString("bookGenre"));
                                row.setBookUrlPath(obj.getString("bookUrlPath"));
                                row.setBookAuthor(obj.getString("bookAuthor"));
                                row.setAuthorLogo(obj.getString("bookAuthorLogo"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getInt("createdBy"));
                                row.setViews(obj.getString("view"));
                                row.setFavorites(obj.getString("favorite"));
                                row.setDownloads(obj.getString("download"));
                                row.setEventListener(eventListenerUtil);
                                row.setActionEvent(actionEvent);
                                row.setContentData(true);
                                data.add(row);
                            }

                            content.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);

                            eventListenerUtil.myCallBack("get-list-ebooks-by-author", data);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                //Toast.makeText(context, "asd" + error.toString(), Toast.LENGTH_LONG).show();
                utilToast.show(context,error.toString());

            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookAuthor", bookAuthor);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
            no_internet.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }

}
