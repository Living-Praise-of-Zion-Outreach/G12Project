package com.lpzoutreach.g12lpz.Utility.sql.php;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.chordsLyricsAlbumListView;
import com.lpzoutreach.g12lpz.ListView.chordsLyricsArtistListView;
import com.lpzoutreach.g12lpz.ListView.chordsLyricsListView;
import com.lpzoutreach.g12lpz.ListView.songArtistAlbumListView;
import com.lpzoutreach.g12lpz.Models.MusicModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;
import com.lpzoutreach.g12lpz.Utility.string.utilFormatter;
import com.lpzoutreach.g12lpz.Utility.system.accessUrl;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.bryanderidder.themedtogglebuttongroup.ThemedToggleButtonGroup;

public class musicTransfer {

    public static void get_all_songs(Context context, ListView listView, ConstraintLayout loading, ConstraintLayout no_data, ConstraintLayout no_internet, ThemedToggleButtonGroup groupButton, elUtil eventListenerUtil, String searchValue, String limit){
        if(utilNetwork.isConnected(context)){
            loading.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            no_internet.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            groupButton.setEnabled(false);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/song/all"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETSONGS",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            List<MusicModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                MusicModel row = new MusicModel();
                                row.setSongID(obj.getInt("songID"));
                                row.setSongTitle(obj.getString("songTitle"));
                                row.setAlbumPhoto(obj.getString("albumPhoto"));
                                row.setAlbumYearReleased(obj.getString("yearReleased"));
                                row.setAlbumName(obj.getString("albumName"));
                                row.setArtistName(obj.getString("artistName"));
                                row.setSongType(obj.getString("songType"));
                                row.setViews(utilFormatter.getIntegerWithComma(obj.getString("views")));
                                row.setDownload(utilFormatter.getIntegerWithComma(obj.getString("download")));
                                row.setFavorite(utilFormatter.getIntegerWithComma(obj.getString("favorite")));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            chordsLyricsListView adapter = new chordsLyricsListView(context, R.layout.lv_chords_lyrics, data);
                            listView.setAdapter(adapter);
                            listView.setDivider(null);

                            groupButton.setEnabled(true);

                            if(data.size()>0){
                                listView.setVisibility(View.VISIBLE);
                                no_data.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                            }else{
                                listView.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                no_data.setVisibility(View.GONE);
                no_internet.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("searchValue", searchValue);
                    MyData.put("limit", limit);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    public static void get_all_albums(Context context, ListView listView, ConstraintLayout loading, ConstraintLayout no_data, ConstraintLayout no_internet, elUtil eventListenerUtil, String actionEvent, String searchValue, String limit){
        if(utilNetwork.isConnected(context)){
            loading.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            no_internet.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);



            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/album/all"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETSONGS",response);

                        try{
                            JSONArray j= new JSONArray(response);
                            List<MusicModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                MusicModel row = new MusicModel();
                                row.setAlbumID(obj.getInt("albumID"));
                                row.setAlbumPhoto(obj.getString("albumPhoto"));
                                row.setAlbumYearReleased(obj.getString("yearReleased"));
                                row.setAlbumName(obj.getString("albumName"));
                                row.setArtistName(obj.getString("artistName"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionEvent(actionEvent);
                                data.add(row);
                            }

                            chordsLyricsAlbumListView adapter = new chordsLyricsAlbumListView(context, R.layout.lv_chords_lyrics_album, data);
                            listView.setAdapter(adapter);
                            listView.setDivider(null);

                            if(data.size()>0){

                                listView.setVisibility(View.VISIBLE);
                                no_data.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                            }else{
                                listView.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                no_data.setVisibility(View.GONE);
                no_internet.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("searchValue", searchValue);
                    MyData.put("limit", limit);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }
    public static void get_all_artists(Context context, ListView listView, ConstraintLayout loading, ConstraintLayout no_data, ConstraintLayout no_internet, elUtil eventListenerUtil, String searchValue, String limit){
        if(utilNetwork.isConnected(context)){
            loading.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            no_internet.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/artist/all"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETSONGS",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            List<MusicModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);

                                MusicModel row = new MusicModel();
                                row.setArtistID(obj.getInt("artistID"));
                                row.setArtistPhoto(obj.getString("artistPhoto"));
                                row.setArtistName(obj.getString("artistName"));
                                row.setArtistType(obj.getString("artistType"));
                                row.setEventListenerUtil(eventListenerUtil);
                                data.add(row);
                            }

                            chordsLyricsArtistListView adapter = new chordsLyricsArtistListView(context, R.layout.lv_chords_lyrics_artist, data);
                            listView.setAdapter(adapter);
                            listView.setDivider(null);

                            if(data.size()>0){
                                listView.setVisibility(View.VISIBLE);
                                no_data.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                            }else{
                                listView.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                no_data.setVisibility(View.GONE);
                no_internet.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("searchValue", searchValue);
                    MyData.put("limit", limit);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void get_artists_info(Context context, ListView listView, ConstraintLayout content,ConstraintLayout no_data, ConstraintLayout loading, ConstraintLayout no_internet, elUtil eventListenerUtil, String artistID, String actionEvent, ImageView logo,  TextView biography, TextView urlPath){
        if(utilNetwork.isConnected(context)){

            content.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            no_internet.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/artist/get-info"; // <----enter your post url here
            @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"}) StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETSONGS",response);
                        try{
                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                if(obj.getString("artistPhoto").isEmpty()){
                                    logo.setImageResource(R.drawable.default_photo);
                                }else{
                                    Picasso.get().load(accessUrl.BASE_URL + obj.getString("artistPhoto"))
                                            .placeholder(context.getDrawable(R.drawable.default_photo))
                                            .error(context.getDrawable(R.drawable.default_photo))
                                            .into(logo);
                                }
                                biography.setText(obj.getString("biography"));
                                urlPath.setText(obj.getString("urlSite"));
                            }
                            get_albums_by_artistID(context,listView,content,loading, no_data, no_internet,eventListenerUtil,artistID,actionEvent);
                            //content.setVisibility(View.VISIBLE);
                            //loading.setVisibility(View.GONE);
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            content.setVisibility(View.GONE);
                            listView.setVisibility(View.GONE);
                            no_internet.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                content.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                no_internet.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("artistID", artistID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void get_albums_by_artistID(Context context, ListView listView,ConstraintLayout content, ConstraintLayout loading,  ConstraintLayout no_data, ConstraintLayout no_internet, elUtil eventListenerUtil, String artistID, String actionEvent){
        if(utilNetwork.isConnected(context)){

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/album/get/list"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {

                        try{
                            JSONArray j= new JSONArray(response);
                            List<MusicModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                MusicModel row = new MusicModel();
                                row.setAlbumID(obj.getInt("albumID"));
                                row.setAlbumPhoto(obj.getString("albumPhoto"));
                                row.setAlbumYearReleased(obj.getString("yearReleased"));
                                row.setAlbumName(obj.getString("albumName"));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionEvent(actionEvent);
                                data.add(row);
                            }

                            songArtistAlbumListView adapter = new songArtistAlbumListView(context, R.layout.lv_chords_lyrics_album, data);
                            listView.setAdapter(adapter);
                            listView.setDivider(null);
                            content.setVisibility(View.VISIBLE);

                            if(data.size()>0){
                                listView.setVisibility(View.VISIBLE);
                                no_data.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                            }else{
                                listView.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                no_data.setVisibility(View.GONE);
                no_internet.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("artistID", artistID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void get_album_info(Context context, ListView listView, ConstraintLayout content,ConstraintLayout no_data, ConstraintLayout loading, ConstraintLayout no_internet, elUtil eventListenerUtil, String albumID, String actionEvent, ImageView logo,  TextView title, TextView subtitle, TextView subsubTitle, String artistName){
        if(utilNetwork.isConnected(context)){

            content.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            no_internet.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/album/get-info"; // <----enter your post url here
            @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"}) StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.v("GETSONGS",response);
                        try{

                            JSONArray j= new JSONArray(response);
                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                if(obj.getString("albumPhoto").isEmpty()){
                                    logo.setImageResource(R.drawable.default_photo);
                                }else{
                                    Picasso.get().load(accessUrl.BASE_URL + obj.getString("albumPhoto"))
                                            .placeholder(context.getDrawable(R.drawable.default_photo))
                                            .error(context.getDrawable(R.drawable.default_photo))
                                            .into(logo);
                                }
                                title.setText(obj.getString("albumName"));
                                subtitle.setText(artistName);
                                subsubTitle.setText(obj.getString("yearReleased"));
                            }
                            get_song_by_albumID(context,listView,content,loading, no_data, no_internet,eventListenerUtil,albumID,actionEvent);
                            //content.setVisibility(View.VISIBLE);
                            //loading.setVisibility(View.GONE);
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                            content.setVisibility(View.GONE);
                            listView.setVisibility(View.GONE);
                            no_internet.setVisibility(View.VISIBLE);
                            loading.setVisibility(View.GONE);
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                content.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
                no_internet.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("albumID", albumID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void get_song_by_albumID(Context context, ListView listView,ConstraintLayout content, ConstraintLayout loading,  ConstraintLayout no_data, ConstraintLayout no_internet, elUtil eventListenerUtil, String albumID, String actionEvent){
        if(utilNetwork.isConnected(context)){

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/song/get/list"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try{
                            JSONArray j= new JSONArray(response);
                            List<MusicModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                MusicModel row = new MusicModel();
                                row.setSongID(obj.getInt("songID"));
                                row.setSongTitle(obj.getString("songTitle"));
                                row.setAlbumPhoto(obj.getString("albumPhoto"));
                                row.setAlbumYearReleased(obj.getString("yearReleased"));
                                row.setAlbumName(obj.getString("albumName"));
                                row.setArtistName(obj.getString("artistName"));
                                row.setSongType(obj.getString("songType"));
                                row.setViews(utilFormatter.getIntegerWithComma(obj.getString("views")));
                                row.setDownload(utilFormatter.getIntegerWithComma(obj.getString("download")));
                                row.setFavorite(utilFormatter.getIntegerWithComma(obj.getString("favorite")));
                                row.setEventListenerUtil(eventListenerUtil);
                                row.setActionEvent(actionEvent);
                                data.add(row);
                            }

                            chordsLyricsListView adapter = new chordsLyricsListView(context, R.layout.lv_chords_lyrics, data);
                            listView.setAdapter(adapter);
                            listView.setDivider(null);
                            content.setVisibility(View.VISIBLE);

                            if(data.size()>0){
                                listView.setVisibility(View.VISIBLE);
                                no_data.setVisibility(View.GONE);
                                loading.setVisibility(View.GONE);
                            }else{
                                listView.setVisibility(View.GONE);
                                no_data.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                no_data.setVisibility(View.GONE);
                no_internet.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("albumID", albumID);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }
    }

    public static void get_all_youtube_videos_from_song(Context context, WebView content, ConstraintLayout loading,  String songID, String category, elUtil eventListener, String actionType, String listActionType){
        if(utilNetwork.isConnected(context)){

            RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
            String url = accessUrl.BASE_URL +  "android/song/youtube-video/list"; // <----enter your post url here
            @SuppressLint("SetTextI18n") StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try{
                            //Toast.makeText(context,response,Toast.LENGTH_LONG).show();

                            JSONArray j= new JSONArray(response);
                            List<MusicModel> data = new ArrayList<>();

                            for (int i = 0; i < j.length(); i++) {
                                JSONObject obj = j.getJSONObject(i);
                                MusicModel row = new MusicModel();
                                row.setSongID(obj.getInt("songID"));
                                row.setVideo_Category(obj.getString("videoCategory"));
                                row.setVideo_youtubeID(obj.getString("youtubeID"));
                                row.setVideo_youtubeThumbnails(obj.getString("youtubeThumbnails"));
                                row.setVideo_youtubeTitle(obj.getString("youtubeTitle"));
                                row.setVideo_youtubeChannel(obj.getString("youtubeChannel"));
                                row.setEventListenerUtil(eventListener);
                                row.setActionEvent(listActionType);
                                data.add(row);
                            }

                            eventListener.myCallBack(actionType, data);
                            loading.setVisibility(View.GONE);
                            content.setVisibility(View.VISIBLE);

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }, error -> {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
                content.setVisibility(View.VISIBLE);
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("songID", songID);
                    MyData.put("category",category);
                    return MyData;
                }
            };
            MyRequestQueue.add(MyStringRequest);
        }else{
            content.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }



}
