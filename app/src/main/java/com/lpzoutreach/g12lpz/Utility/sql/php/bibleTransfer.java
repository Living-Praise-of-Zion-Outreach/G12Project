package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.lpzoutreach.g12lpz.ListView.mnemonicVerseListView;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.Models.MnemonicVerseModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressDialogBar;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressHorizontalBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperBible;
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

public class bibleTransfer {

    public static void get_from_server_mnemonic_verse_list(Context context, ListView list, ConstraintLayout loading, ConstraintLayout ll_no_data_found, elUtil eventListenerUtil, String searchValue, String version, String language, String searchLimit) {
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            loading.setVisibility(View.VISIBLE);
            ll_no_data_found.setVisibility(View.GONE);
            list.setVisibility(View.GONE);

            List<MnemonicVerseModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/bible/mnemonic-verse/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BIBLE_MNEMONIC_VERSE_LIST", response.toString());
                        try {
                            JSONArray j = new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                MnemonicVerseModel row = new MnemonicVerseModel();
                                row.setMinemonicVerseID(obj.getInt("minemonicVerseID"));
                                row.setGroupVerseID(obj.getInt("groupVerseID"));
                                row.setVerseTitle(obj.getString("verseTitle"));
                                row.setVerseReference(obj.getString("verseReference"));
                                row.setVerse(obj.getString("verse"));
                                row.setTag(obj.getString("tag"));
                                row.setBibleVersion(obj.getString("bibleVersion"));
                                row.setBibleVersionText(obj.getString("bibleVersionText"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setPublishedBy(obj.getString("publishedBy"));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            mnemonicVerseListView adapter = new mnemonicVerseListView(context, R.layout.lv_mnemonic_verse, data);
                            list.setAdapter(adapter);

                            if (data.size() > 0) {
                                list.setVisibility(View.VISIBLE);
                                ll_no_data_found.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                            } else {
                                list.setVisibility(View.GONE);
                                ll_no_data_found.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("searchValue", searchValue);
                    MyData.put("version", version);
                    MyData.put("language", language);
                    MyData.put("limit", searchLimit);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        } else {
            //IN CASE IF OFFLINE
            //utilToast.showErrorNoInternet(context);
            loading.setVisibility(View.GONE);

            openHelperBible bible = new openHelperBible(context);
            List<MnemonicVerseModel> data = bible.get_all(searchValue, version, language, eventListenerUtil);
            mnemonicVerseListView adapter = new mnemonicVerseListView(context, R.layout.lv_mnemonic_verse, data);
            list.setAdapter(adapter);
            if (data.size() == 0) {
                ll_no_data_found.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
            } else {
                ll_no_data_found.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
            }

        }
    }

    public static void download_from_server_mnemonic_verse(Context context, ProgressDialogBar progressDialogBar) {
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            progressDialogBar.show();

            List<MnemonicVerseModel> data = new ArrayList<>();

            String url = accessUrl.BASE_URL + "android/bible/mnemonic-verse/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("BIBLE_MNEMONIC_VERSE_LIST", response.toString());
                        try {
                            JSONArray j = new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                MnemonicVerseModel row = new MnemonicVerseModel();
                                row.setMinemonicVerseID(obj.getInt("minemonicVerseID"));
                                row.setGroupVerseID(obj.getInt("groupVerseID"));
                                row.setVerseTitle(obj.getString("verseTitle"));
                                row.setVerseReference(obj.getString("verseReference"));
                                row.setVerse(obj.getString("verse"));
                                row.setTag(obj.getString("tag"));
                                row.setBibleVersion(obj.getString("bibleVersion"));
                                row.setBibleVersionText(obj.getString("bibleVersionText"));
                                row.setLanguage(obj.getString("language"));
                                row.setDtCreated(obj.getString("dtCreated"));
                                row.setPublishedBy(obj.getString("publishedBy"));
                                data.add(row);
                            }

                            openHelperBible bible = new openHelperBible(context);
                            bible.delete();
                            bible.insert(data);
                            progressDialogBar.hide();

                            Toast.makeText(context, "You have successfully downloaded the data!", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("searchValue", "");
                    MyData.put("version", "");
                    MyData.put("language", "");
                    MyData.put("limit", "");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        } else {
            //IN CASE IF OFFLINE
            utilToast.showErrorNoInternet(context);
        }
    }

    public static void get_from_server_bible_language(Context context, ProgressBar progressBar, elUtil eventListenerUtil, String actionEvent) {
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            ArrayList<String> data = new ArrayList<>();
            String url = accessUrl.BASE_URL + "android/bible/list/language";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try {
                            JSONArray j = new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                data.add(obj.getString("language"));
                            }
                            eventListenerUtil.myCallBack(actionEvent, data);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                openHelperBible helper = new openHelperBible(context);
                ArrayList<String> value = helper.get_all_bible_language();
                eventListenerUtil.myCallBack("load-offline-bible-language", value);
                progressBar.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("action", "get");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        } else {
            //IN CASE IF OFFLINE
            //utilToast.showErrorNoInternet(context);
            //Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
            openHelperBible helper = new openHelperBible(context);
            ArrayList<String> value = helper.get_all_bible_language();
            eventListenerUtil.myCallBack("load-offline-bible-language", value);
            progressBar.setVisibility(View.GONE);
        }

    }

    public static void get_from_server_holy_bible_list(Context context, ProgressBar progressDialogBar, elUtil eventListenerUtil, String actionEvent, String itemActionEvent, String searchValue) {
        if (utilNetwork.isConnected(context)) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            ArrayList<HolyBibleModel> data = new ArrayList<>();
            String url = accessUrl.BASE_URL + "android/bible/list";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try {
                            JSONArray j = new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                HolyBibleModel row = new HolyBibleModel();
                                row.setHolyBibleID(obj.getInt("holyBibleID"));
                                row.setShortBibleName(obj.getString("shortBibleName"));
                                row.setLongBibleName(obj.getString("longBibleName"));
                                row.setDescription(obj.getString("description"));
                                row.setCopyright(obj.getString("copyright"));
                                row.setLanguage(obj.getString("language"));
                                row.setBibleUrl(obj.getString("bibleUrl"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionEvent(itemActionEvent);
                                row.setDownloaded(false);
                                data.add(row);
                            }
                            eventListenerUtil.myCallBack(actionEvent, data);
                            progressDialogBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                progressDialogBar.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("action", "get");
                    MyData.put("language", sharedData.get_bible_current_language(context));
                    MyData.put("searchValue", searchValue);
                    MyData.put("limit", "");
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        } else {
            //IN CASE IF OFFLINE
            //utilToast.showErrorNoInternet(context);
            utilToast.showErrorNoInternet(context);
            progressDialogBar.setVisibility(View.GONE);
        }

    }

    public static void download_holy_bible(Context context, String holyBibleID, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil) {
        if (utilNetwork.isConnected(context)) {

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);

            HolyBibleModel data = new HolyBibleModel();

            String url = accessUrl.BASE_URL + "android/bible/get";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try {
                            Log.v("HOLYBIBLE",response);
                            JSONArray j = new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                data.setHolyBibleID(obj.getInt("holyBibleID"));
                                data.setShortBibleName(obj.getString("shortBibleName"));
                                data.setLongBibleName(obj.getString("longBibleName"));
                                data.setDescription(obj.getString("description"));
                                data.setCopyright(obj.getString("copyright"));
                                data.setLanguage(obj.getString("language"));
                                data.setBibleUrl(obj.getString("bibleUrl"));


                                openHelperBible helper = new openHelperBible(context);

                                //eventListenerUtil.myCallBack("refresh","");
                                download_sql_database_from_server_direct(context, data, helper, "databases/", progressHorizontalBar, eventListenerUtil);
                                //Toast.makeText(context,data.getBook_name(),Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<>();
                    MyData.put("holyBibleID", holyBibleID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    private static void download_sql_database_from_server_direct(Context context, HolyBibleModel data, openHelperBible helper, String folder, ProgressHorizontalBar progressHorizontalBar, elUtil eventListenerUtil) {
        FileLoader.with(context)
                .load(accessUrl.BASE_URL + data.getBibleUrl(), false) //2nd parameter is optioal, pass true to force load from network
                .fromDirectory(folder, FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {
                        //String path = "file:///storage/emulated/0/Android" + response.getDownloadedFile().getPath().replace("user/0/","");
                        progressHorizontalBar.setProgress(response.getDownloadedFile().getName(), 50, 100);
                        String path = response.getDownloadedFile().getAbsolutePath();
                        data.setAndroidPath(path);

                        helper.insert_holy_bible(data);
                        eventListenerUtil.myCallBack("complete-download-holy-bible", data.getShortBibleName());
                        progressHorizontalBar.hide();
                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Log.v("ERROR DOWNLOAD", request.toString());
                    }
                });

     }

}
