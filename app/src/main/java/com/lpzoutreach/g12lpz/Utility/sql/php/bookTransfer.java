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
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.bibleStoryBookListView;
import com.lpzoutreach.g12lpz.ListView.learningMaterialsListView;
import com.lpzoutreach.g12lpz.ListView.presentMaterialsListview;
import com.lpzoutreach.g12lpz.Models.AppAssetModel;
import com.lpzoutreach.g12lpz.Models.BookModel;
import com.lpzoutreach.g12lpz.Models.DiscipleLessonsModel;
import com.lpzoutreach.g12lpz.Models.PresentMaterialsModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressHorizontalBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperBook;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.lpzoutreach.g12lpz.Utility.toast.utilToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bookTransfer {
    public static void get_from_server_book_list(Context context, ListView list, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found,ConstraintLayout loading, String classification){
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        ll_no_data_found.setVisibility(View.GONE);

        if (utilNetwork.isConnected(context)) {

            get_from_local_book_list(context,list,eventListenerUtil,ll_no_data_found);
            loading.setVisibility(View.VISIBLE);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            List<BookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/material/book/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                BookModel row = new BookModel();
                                row.setBookID(obj.getInt("bookID"));
                                row.setBook_logo(obj.getString("book_logo"));
                                row.setBook_name(obj.getString("book_name"));
                                row.setBook_description(obj.getString("book_description"));
                                row.setBook_language(obj.getString("book_language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setBook_total_chapters(obj.getInt("book_total_chapters"));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            openHelperBook helper = new openHelperBook(context);
                            for(int i=0;i<data.size();i++){
                                if(helper.check_book(String.valueOf(data.get(i).getBookID())))
                                    data.get(i).setIs_file_downloaded(true);
                                else
                                    data.get(i).setIs_file_downloaded(false);
                            }

                            learningMaterialsListView arrayAdapter = new learningMaterialsListView(context, R.layout.lv_learning_materials, data);
                            list.setAdapter(arrayAdapter);
                            list.setClickable(true);
                            list.setDivider(null);

                            loading.setVisibility(View.GONE);

                            if(data.size()>0){
                                list.setVisibility(View.VISIBLE);
                                ll_no_data_found.setVisibility(View.GONE);
                            }else{
                                list.setVisibility(View.GONE);
                                ll_no_data_found.setVisibility(View.VISIBLE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    MyData.put("book_classification", classification);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            loading.setVisibility(View.GONE);
            utilToast.showErrorNoInternet(context);
            get_from_local_book_list(context,list,eventListenerUtil,ll_no_data_found);
        }
    }
    public static void get_from_local_book_list(Context context,ListView listView, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found){
        openHelperBook helper = new openHelperBook(context);
        List<BookModel> data =  helper.get_list_book("tools-2");

        for(int i=0;i<data.size();i++){
            data.get(i).setEventListenerUtil(eventListenerUtil);
        }

        learningMaterialsListView arrayAdapter = new learningMaterialsListView(context, R.layout.lv_learning_materials, data);
        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);
        listView.setDivider(null);

        if(data.size()>0){
            listView.setVisibility(View.VISIBLE);
            ll_no_data_found.setVisibility(View.GONE);
        }else{
            listView.setVisibility(View.GONE);
            if(!utilNetwork.isConnected(context)){
                ll_no_data_found.setVisibility(View.VISIBLE);
            }
        }

    }

    public static void download_book(Context context, String bookID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            BookModel data = new BookModel();

            String url = accessUrl.BASE_URL + "android/material/book/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_GET",response.toString());

                        try{

                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                data.setBookID(obj.getInt("bookID"));
                                data.setBook_logo(obj.getString("book_logo"));
                                data.setBook_name(obj.getString("book_name"));
                                data.setBook_description(obj.getString("book_description"));
                                data.setBook_language(obj.getString("book_language"));
                                data.setBook_classification(obj.getString("book_classification"));
                                data.setDtCreated(obj.getString("dtCreated"));
                                data.setBook_total_chapters(obj.getInt("book_total_chapters"));
                                data.setAndroid_folder(obj.getString("android_folder"));

                                openHelperBook helper = new openHelperBook(context);
                                helper.insert_book(data);
                                //eventListenerUtil.myCallBack("refresh","");

                                download_book_lessons(context,helper,bookID,progressHorizontalBar,eventListenerUtil,obj.getString("android_folder"));
                                //Toast.makeText(context,data.getBook_name(),Toast.LENGTH_LONG).show();

                            }



                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookID", bookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    private static void download_book_lessons(Context context, openHelperBook helper, String bookID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil, String folder){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/material/book/lessons/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_GET", response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                BookModel data = new BookModel();
                                data.setLessonsID(obj.getInt("lessonsID"));
                                data.setBookID(obj.getInt("bookID"));
                                data.setLessonNo(obj.getString("lessonNo"));
                                data.setLessonTitle(obj.getString("lessonTitle"));
                                data.setLessonSubtitle(obj.getString("lessonSubtitle"));
                                data.setContent(obj.getString("content"));
                                data.setContent_dark(obj.getString("content_dark"));
                                data.setDtCreated(obj.getString("dtCreated"));
                                data.setHtml_content(obj.getString("html_content"));
                                data.setStudy_the_word(obj.getString("study_the_word"));
                                data.setHtml_header(obj.getString("html_heading"));
                                data.setCreatedBy(String.valueOf(obj.getInt("createdBy")));
                                helper.insert_book_lessons(data);
                                //download_file_from_server_direct(context,data,helper,"material_book/"+folder+"/",i, j.length(),progressHorizontalBar,eventListenerUtil);
                            }
                            //
                            progressHorizontalBar.hide();
                            eventListenerUtil.myCallBack("refresh","");

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookID", bookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    private static void download_file_from_server_direct(Context context,BookModel data, openHelperBook helper, String folder,int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path(path);
                        download_file_from_server_content_dark(context,data,helper,folder,incrementor, max_record,progressHorizontalBar,eventListenerUtil);
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    private static void download_file_from_server_content_dark(Context context,BookModel data, openHelperBook helper, String folder, int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent_dark(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path_dark(path);
                        helper.insert_book_lessons(data);

                        if(incrementor==(max_record-1)){
                            progressHorizontalBar.hide();
                            eventListenerUtil.myCallBack("refresh","");
                        }
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    public static void sync_download_book_lessons(Context context, openHelperBook helper, String bookID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil, String folder){
        if (utilNetwork.isConnected(context)) {

            helper.delete_list_book_lesson(bookID);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/material/book/lessons/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_GET", response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                BookModel data = new BookModel();
                                data.setLessonsID(obj.getInt("lessonsID"));
                                data.setBookID(obj.getInt("bookID"));
                                data.setLessonNo(obj.getString("lessonNo"));
                                data.setLessonTitle(obj.getString("lessonTitle"));
                                data.setLessonSubtitle(obj.getString("lessonSubtitle"));
                                data.setContent(obj.getString("content"));
                                data.setContent_dark(obj.getString("content_dark"));
                                data.setDtCreated(obj.getString("dtCreated"));
                                data.setHtml_content(obj.getString("html_content"));
                                data.setStudy_the_word(obj.getString("study_the_word"));
                                data.setHtml_header(obj.getString("html_heading"));
                                data.setCreatedBy(String.valueOf(obj.getInt("createdBy")));
                                helper.insert_book_lessons(data);
                                //sync_download_file_from_server_direct(context,data,helper,"material_book/"+folder+"/",i, j.length(),progressHorizontalBar,eventListenerUtil);
                            }
                            //
                            progressHorizontalBar.hide();
                            eventListenerUtil.myCallBack("refresh-lessons","");

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookID", bookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    public static void get_from_server_life_class_book_list(Context context, ListView list, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found,ConstraintLayout loading, String classification){
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        ll_no_data_found.setVisibility(View.GONE);

        if (utilNetwork.isConnected(context)) {

            get_from_local_life_class_book_list(context,list,eventListenerUtil,ll_no_data_found);
            loading.setVisibility(View.VISIBLE);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            List<BookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/material/book/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                BookModel row = new BookModel();
                                row.setBookID(obj.getInt("bookID"));
                                row.setBook_logo(obj.getString("book_logo"));
                                row.setBook_name(obj.getString("book_name"));
                                row.setBook_description(obj.getString("book_description"));
                                row.setBook_language(obj.getString("book_language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setBook_total_chapters(obj.getInt("book_total_chapters"));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            openHelperBook helper = new openHelperBook(context);
                            for(int i=0;i<data.size();i++){
                                if(helper.check_book(String.valueOf(data.get(i).getBookID())))
                                    data.get(i).setIs_file_downloaded(true);
                                else
                                    data.get(i).setIs_file_downloaded(false);
                            }

                            learningMaterialsListView arrayAdapter = new learningMaterialsListView(context, R.layout.lv_learning_materials, data);
                            list.setAdapter(arrayAdapter);
                            list.setClickable(true);
                            list.setDivider(null);

                            loading.setVisibility(View.GONE);

                            if(data.size()>0){
                                list.setVisibility(View.VISIBLE);
                                ll_no_data_found.setVisibility(View.GONE);
                            }else{
                                list.setVisibility(View.GONE);
                                ll_no_data_found.setVisibility(View.VISIBLE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    MyData.put("book_classification", classification);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            loading.setVisibility(View.GONE);
            utilToast.showErrorNoInternet(context);
            get_from_local_life_class_book_list(context,list,eventListenerUtil,ll_no_data_found);
        }
    }
    public static void get_from_local_life_class_book_list(Context context,ListView listView, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found){
        openHelperBook helper = new openHelperBook(context);
        List<BookModel> data =  helper.get_list_book("tools-3");
        for(int i=0;i<data.size();i++){
            data.get(i).setEventListenerUtil(eventListenerUtil);
        }
        learningMaterialsListView arrayAdapter = new learningMaterialsListView(context, R.layout.lv_learning_materials, data);
        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);
        listView.setDivider(null);
        if(data.size()>0){
            listView.setVisibility(View.VISIBLE);
            ll_no_data_found.setVisibility(View.GONE);
        }else{
            listView.setVisibility(View.GONE);
            if(!utilNetwork.isConnected(context)) {
                ll_no_data_found.setVisibility(View.VISIBLE);
            }
        }
    }
    public static void download_life_class_book(Context context, String bookID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            BookModel data = new BookModel();

            String url = accessUrl.BASE_URL + "android/material/book/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_GET",response.toString());

                        try{

                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                data.setBookID(obj.getInt("bookID"));
                                data.setBook_logo(obj.getString("book_logo"));
                                data.setBook_name(obj.getString("book_name"));
                                data.setBook_description(obj.getString("book_description"));
                                data.setBook_language(obj.getString("book_language"));
                                data.setBook_classification(obj.getString("book_classification"));
                                data.setDtCreated(obj.getString("dtCreated"));
                                data.setBook_total_chapters(obj.getInt("book_total_chapters"));
                                data.setAndroid_folder(obj.getString("android_folder"));

                                openHelperBook helper = new openHelperBook(context);
                                helper.insert_book(data);
                                //eventListenerUtil.myCallBack("refresh","");
                                download_life_class_book_lessons(context,helper,bookID,progressHorizontalBar,eventListenerUtil,obj.getString("android_folder"));
                                //Toast.makeText(context,data.getBook_name(),Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookID", bookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    public static void download_life_class_assets(Context context, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil, String folder){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            AppAssetModel data = new AppAssetModel();
            String url = accessUrl.BASE_URL + "android/app/asset/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_GET",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                data.setAppAssetID(obj.getInt("appAssetID"));
                                data.setAssetLogo(obj.getString("assetLogo"));
                                data.setAssetName(obj.getString("assetName"));
                                data.setAssetType(obj.getString("assetType"));
                                data.setAssetDescription(obj.getString("assetDescription"));
                                data.setCreatedBy(obj.getInt("createdBy"));
                                data.setDtCreated(obj.getString("dtCreated"));
                                data.setDtUpdated(obj.getString("dtUpdated"));
                                download_asset_files_life_class(context,data, folder + "images/", i, j.length(), progressHorizontalBar, eventListenerUtil);
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("assetType", "lifeclass");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    private static void download_asset_files_life_class(Context context,AppAssetModel data, String folder, int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getAssetLogo(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        //data.setAndroid_content_path_dark(path);
                        //helper.insert_book_lessons(data);

                        if(incrementor==(max_record-1)){
                            progressHorizontalBar.hide();
                            eventListenerUtil.myCallBack("refresh","");
                            eventListenerUtil.myCallBack("refresh-lessons","");
                        }
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    private static void download_life_class_book_lessons(Context context, openHelperBook helper, String bookID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil, String folder){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            String url = accessUrl.BASE_URL + "android/material/book/disciple-lessons/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_GET", response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                DiscipleLessonsModel data = new DiscipleLessonsModel();
                                data.setLessonID(obj.getInt("lessonID"));
                                data.setBookID(obj.getInt("bookID"));
                                data.setLessonNo(obj.getInt("lessonNo"));
                                data.setBookType(obj.getString("bookType"));
                                data.setLessonTitle(obj.getString("lessonTitle"));
                                data.setMenu_title(obj.getString("menu_title"));
                                data.setImg_header(obj.getString("img_header"));
                                data.setContent_1(obj.getString("content_1"));
                                data.setContent_2(obj.getString("content_2"));
                                data.setContent_3(obj.getString("content_3"));
                                data.setContent_4(obj.getString("content_4"));
                                data.setStudy_the_word(obj.getString("study_the_word"));
                                data.setLanguage(obj.getString("language"));

                                helper.insert_disciple_lessons(data);

                                //download_file_from_server_direct_life_class(context,data,helper,"material_book/"+folder+"/",i, j.length(),progressHorizontalBar,eventListenerUtil);
                            }
                            //
                            progressHorizontalBar.hide();
                            //download_life_class_assets(context,progressHorizontalBar,eventListenerUtil, folder);
                            Toast.makeText(context,"You have successfully downloaded the lessons",Toast.LENGTH_LONG).show();

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookID", bookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    private static void download_file_from_server_direct_life_class(Context context,BookModel data, openHelperBook helper, String folder,int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path(path);
                        download_file_from_server_content_dark_life_class(context,data,helper,folder,incrementor, max_record,progressHorizontalBar,eventListenerUtil);
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    private static void download_file_from_server_content_dark_life_class(Context context,BookModel data, openHelperBook helper, String folder, int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent_dark(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path_dark(path);
                        helper.insert_book_lessons(data);
                        if(incrementor==(max_record-1)){
                            //progressHorizontalBar.hide();
                            //eventListenerUtil.myCallBack("refresh","");
                            download_life_class_assets(context,progressHorizontalBar,eventListenerUtil, folder);
                        }
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    public static void sync_download_book_lessons_life_class(Context context, openHelperBook helper, String bookID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil, String folder){
        if (utilNetwork.isConnected(context)) {

            helper.delete_list_disciple_lesson(bookID);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/material/book/disciple-lessons/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BOOK_GET", response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                DiscipleLessonsModel data = new DiscipleLessonsModel();
                                data.setLessonID(obj.getInt("lessonID"));
                                data.setBookID(obj.getInt("bookID"));
                                data.setLessonNo(obj.getInt("lessonNo"));
                                data.setBookType(obj.getString("bookType"));
                                data.setLessonTitle(obj.getString("lessonTitle"));
                                data.setMenu_title(obj.getString("menu_title"));
                                data.setImg_header(obj.getString("img_header"));
                                data.setContent_1(obj.getString("content_1"));
                                data.setContent_2(obj.getString("content_2"));
                                data.setContent_3(obj.getString("content_3"));
                                data.setContent_4(obj.getString("content_4"));
                                data.setStudy_the_word(obj.getString("study_the_word"));
                                data.setLanguage(obj.getString("language"));

                                helper.insert_disciple_lessons(data);

                                //sync_download_file_from_server_direct_life_class(context,data,helper,"material_book/"+folder+"/",i, j.length(),progressHorizontalBar,eventListenerUtil);
                            }
                            //

                            progressHorizontalBar.hide();
                            eventListenerUtil.myCallBack("refresh","");
                            eventListenerUtil.myCallBack("refresh-lessons","");
                            //Toast.makeText(context,"You have successfully updated the lessons!",Toast.LENGTH_LONG).show();

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookID", bookID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    private static void sync_download_file_from_server_direct_life_class(Context context,BookModel data, openHelperBook helper, String folder,int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path(path);
                        sync_download_file_from_server_content_dark_life_class(context,data,helper,folder,incrementor, max_record,progressHorizontalBar,eventListenerUtil);
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    private static void sync_download_file_from_server_content_dark_life_class(Context context,BookModel data, openHelperBook helper, String folder, int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent_dark(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path_dark(path);
                        helper.insert_book_lessons(data);

                        if(incrementor==(max_record-1)){
                            //progressHorizontalBar.hide();
                            //eventListenerUtil.myCallBack("refresh-lessons","");
                            download_life_class_assets(context,progressHorizontalBar,eventListenerUtil, folder);
                        }

                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    private static void sync_download_file_from_server_direct(Context context,BookModel data, openHelperBook helper, String folder,int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path(path);
                        sync_download_file_from_server_content_dark(context,data,helper,folder,incrementor, max_record,progressHorizontalBar,eventListenerUtil);
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    private static void sync_download_file_from_server_content_dark(Context context,BookModel data, openHelperBook helper, String folder, int incrementor, int max_record, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent_dark(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path_dark(path);
                        helper.insert_book_lessons(data);

                        if(incrementor==(max_record-1)){
                            progressHorizontalBar.hide();
                            eventListenerUtil.myCallBack("refresh-lessons","");
                        }

                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    /////////////////MATERIAL PRESENT SECTION/////////////////////////
    public static void get_from_server_present_list(Context context, ListView list, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found, ConstraintLayout loading){
        loading.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
        ll_no_data_found.setVisibility(View.GONE);

        if (utilNetwork.isConnected(context)) {


            get_from_local_present_list(context,list,eventListenerUtil,ll_no_data_found);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            List<PresentMaterialsModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/material/present/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("PRESENT_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                PresentMaterialsModel row = new PresentMaterialsModel();
                                row.setMaterialPresentID(obj.getInt("materialPresentID"));
                                row.setPresent_Logo(obj.getString("presentLogo"));
                                row.setPresentTitle(obj.getString("presentTitle"));
                                row.setPresentSubTitle(obj.getString("presentSubTitle"));
                                row.setPresentLanguage(obj.getString("presentLanguage"));
                                row.setPresentSlides(obj.getString("presentSlides"));
                                row.setContent(obj.getString("content"));
                                row.setContent_dark(obj.getString("content_dark"));
                                row.setAndroid_content_path(obj.getString("android_folder"));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            openHelperBook helper = new openHelperBook(context);
                            for(int i=0;i<data.size();i++){
                                if(helper.check_material_present(String.valueOf(data.get(i).getMaterialPresentID())))
                                    data.get(i).setIs_file_downloaded(true);
                                else
                                    data.get(i).setIs_file_downloaded(false);
                            }

                            presentMaterialsListview arrayAdapter = new presentMaterialsListview(context, R.layout.lv_learning_materials, data);
                            list.setAdapter(arrayAdapter);
                            list.setClickable(true);
                            list.setDivider(null);

                            loading.setVisibility(View.GONE);

                            if(data.size()>0){
                                list.setVisibility(View.VISIBLE);
                                ll_no_data_found.setVisibility(View.GONE);
                            }else{
                                list.setVisibility(View.GONE);
                                ll_no_data_found.setVisibility(View.VISIBLE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("userID", String.valueOf(sharedData.get_userID(context)));
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            loading.setVisibility(View.GONE);
            utilToast.showErrorNoInternet(context);
            get_from_local_present_list(context,list,eventListenerUtil,ll_no_data_found);
        }
    }
    public static void get_from_local_present_list(Context context,ListView listView, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found){
        openHelperBook helper = new openHelperBook(context);
        List<PresentMaterialsModel> data =  helper.get_list_present();

        for(int i=0;i<data.size();i++){
            data.get(i).setEventListenerUtil(eventListenerUtil);
        }

        presentMaterialsListview arrayAdapter = new presentMaterialsListview(context, R.layout.lv_learning_materials, data);
        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);
        listView.setDivider(null);

        if(data.size()>0){
            listView.setVisibility(View.VISIBLE);
            //ll_no_data_found.setVisibility(View.GONE);
        }else{
            listView.setVisibility(View.GONE);
            //ll_no_data_found.setVisibility(View.VISIBLE);
        }

    }
    public static void download_material_present(Context context, String materialPresentID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            PresentMaterialsModel data = new PresentMaterialsModel();

            String url = accessUrl.BASE_URL + "android/material/present/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {

                        Log.v("MATERIAL_PRESENT_GET",response.toString());
                        try{

                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                data.setMaterialPresentID(obj.getInt("materialPresentID"));
                                data.setPresent_Logo(obj.getString("presentLogo"));
                                data.setPresentTitle(obj.getString("presentTitle"));
                                data.setPresentSubTitle(obj.getString("presentSubTitle"));
                                data.setPresentLanguage(obj.getString("presentLanguage"));
                                data.setPresentSlides(String.valueOf(obj.getInt("presentSlides")));
                                data.setContent(obj.getString("content"));
                                data.setContent_dark(obj.getString("content_dark"));
                                data.setEventListenerUtil(eventListenerUtil);

                                String folderName = obj.getString("android_folder");
                                String[] content = obj.getString("content").split(",");
                                openHelperBook helper = new openHelperBook(context);

                                download_content_present_file_from_server_direct(context,data,helper,content,"material_present/"+folderName+"/",progressHorizontalBar,eventListenerUtil);

                            }
                            //helper.insert_book(data);
                            //eventListenerUtil.myCallBack("refresh","");
                            //download_book_lessons(context,helper,bookID,progressHorizontalBar,eventListenerUtil);
                            //Toast.makeText(context,data.getBook_name(),Toast.LENGTH_LONG).show();

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("materialPresentID", materialPresentID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    private static void download_content_present_file_from_server_direct(Context context,PresentMaterialsModel data, openHelperBook helper,String[] content, String folder, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil){
        int inc=0;
        String[] android_content_path = new String[content.length];
        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  content[inc],false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        String path = response.getDownloadedFile().getAbsolutePath();
                        android_content_path[inc] = path;
                        download_content_present_file_from_server_direct_below(context,data,helper,content,android_content_path,inc, folder, progressHorizontalBar,eventListenerUtil);
                        //download_file_from_server_content_dark(context,data,helper,folder);
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }
    private static void download_content_present_file_from_server_direct_below(Context context,PresentMaterialsModel data, openHelperBook helper,String[] content, String[] android_content_path, int inc,   String folder, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){
        int incrementor =inc + 1;
        if(incrementor<content.length){
            FileLoader.with(context)
                    .load(accessUrl.BASE_URL +  content[incrementor],false) //2nd parameter is optioal, pass true to force load from network
                    .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                    .asFile(new FileRequestListener<File>() {
                        @Override
                        public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                            progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),incrementor,7);
                            //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                            String path = response.getDownloadedFile().getAbsolutePath();
                            android_content_path[incrementor] = path;
                            download_content_present_file_from_server_direct_below(context,data,helper,content,android_content_path,incrementor,folder,progressHorizontalBar,eventListenerUtil);
                        }
                        @Override
                        public void onError(FileLoadRequest request, Throwable t) {
                            Log.v("ERROR DOWNLOAD",request.toString());
                        }
                    });
        }else{
            StringBuilder new_content= new StringBuilder();
            for(int i=0;i<android_content_path.length;i++){
                if(i==0)
                    new_content = new StringBuilder(android_content_path[i]);
                else
                    new_content.append(",").append(android_content_path[i]);
            }
            data.setAndroid_content_path(new_content.toString());
            helper.insert_material_present(data);
            eventListenerUtil.myCallBack("refresh","");
            progressHorizontalBar.hide();
        }


    }

    public static void get_from_server_bible_story_book_list(Context context, ListView list, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found, ConstraintLayout loading, ConstraintLayout content, String actionEvent){
        loading.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        ll_no_data_found.setVisibility(View.GONE);

        if (utilNetwork.isConnected(context)) {

            get_from_local_bible_story_list(context,list,eventListenerUtil,ll_no_data_found,content,actionEvent);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            List<BookModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/material/bible-story-book/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BIBLE_BOOK_LIST",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                BookModel row = new BookModel();
                                row.setLessonsID(obj.getInt("lessonsID"));
                                row.setLessonsLogo(obj.getString("lessonLogo"));
                                row.setLessonNo(obj.getString("lessonNo"));
                                row.setLessonTitle(obj.getString("lessonTitle"));
                                row.setLessonSubtitle(obj.getString("lessonSubtitle"));
                                row.setContent(obj.getString("content"));
                                row.setContent_dark(obj.getString("content_dark"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setCreatedBy(obj.getString("createdBy"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionEvent(actionEvent);
                                data.add(row);
                            }

                            openHelperBook helper = new openHelperBook(context);
                            for(int i=0;i<data.size();i++){
                                if(helper.check_lessons(String.valueOf(data.get(i).getLessonsID())))
                                    data.get(i).setIs_file_downloaded(true);
                                else
                                    data.get(i).setIs_file_downloaded(false);
                            }

                            bibleStoryBookListView arrayAdapter = new bibleStoryBookListView(context, R.layout.lv_bible_story_book, data);
                            list.setAdapter(arrayAdapter);
                            list.setClickable(true);
                            list.setDivider(null);
                            loading.setVisibility(View.GONE);

                            if(data.size()>0){
                                content.setVisibility(View.VISIBLE);
                                list.setVisibility(View.VISIBLE);
                                ll_no_data_found.setVisibility(View.GONE);
                            }else{
                                content.setVisibility(View.GONE);
                                list.setVisibility(View.GONE);
                                ll_no_data_found.setVisibility(View.VISIBLE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("bookID", "9");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            //IN CASE IF OFFLINE
            loading.setVisibility(View.GONE);
            ll_no_data_found.setVisibility(View.VISIBLE);
            utilToast.showErrorNoInternet(context);
            get_from_local_bible_story_list(context,list,eventListenerUtil,ll_no_data_found, content,actionEvent);
        }
    }

    public static void download_bible_story_book_lessons(Context context, String lessonID, ProgressHorizontalBar progressHorizontalBar,elUtil eventListenerUtil){
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL + "android/material/bible-story-book/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BIBLE_STORY_BOOK_GET",response.toString());
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                BookModel data = new BookModel();
                                data.setLessonsID(obj.getInt("lessonsID"));
                                data.setBookID(obj.getInt("bookID"));
                                data.setLessonsLogo(obj.getString("lessonLogo"));
                                data.setLessonNo(obj.getString("lessonNo"));
                                data.setLessonType(obj.getString("lessonType"));
                                data.setLessonTitle(obj.getString("lessonTitle"));
                                data.setLessonSubtitle(obj.getString("lessonSubtitle"));
                                data.setContent(obj.getString("content"));
                                data.setContent_dark(obj.getString("content_dark"));
                                data.setDtCreated(obj.getString("dtCreated"));
                                data.setCreatedBy(obj.getString("createdBy"));

                                openHelperBook helper = new openHelperBook(context);
                                //helper.insert_book_lessons(data);

                                //eventListenerUtil.myCallBack("refresh","");
                                download_pdf_file_from_server_bible_story_book(context,data,helper,"material_book/bible_story_book/", progressHorizontalBar,eventListenerUtil);

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("lessonID", lessonID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    private static void download_pdf_file_from_server_bible_story_book(Context context,BookModel data, openHelperBook helper, String folder, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil){

        FileLoader.with(context)
                .load(accessUrl.BASE_URL +  data.getContent(),false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(),50,100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroid_content_path_dark(path);
                        helper.insert_book_lessons(data);
                        progressHorizontalBar.hide();
                        eventListenerUtil.myCallBack("refresh-bible-story-book","");
                    }
                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        //Toast.makeText(context,request.toString(),Toast.LENGTH_LONG).show();
                        Log.v("ERROR DOWNLOAD",request.toString());
                    }
                });
    }

    public static void get_from_local_bible_story_list(Context context,ListView listView, elUtil eventListenerUtil, ConstraintLayout ll_no_data_found, ConstraintLayout content, String actionEvent){
        openHelperBook helper = new openHelperBook(context);
        List<BookModel> data =  helper.get_list_book_bible_story_lesson("9",eventListenerUtil);

        for(int i=0;i<data.size();i++){
            data.get(i).setEventListenerUtil(eventListenerUtil);
            data.get(i).setActionEvent(actionEvent);
        }

        bibleStoryBookListView arrayAdapter = new bibleStoryBookListView(context, R.layout.lv_bible_story_book, data);
        listView.setAdapter(arrayAdapter);
        listView.setClickable(true);
        listView.setDivider(null);

        if(data.size()>0){
            listView.setVisibility(View.VISIBLE);
            ll_no_data_found.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.GONE);
            content.setVisibility(View.GONE);
            ll_no_data_found.setVisibility(View.VISIBLE);
        }

    }


}
