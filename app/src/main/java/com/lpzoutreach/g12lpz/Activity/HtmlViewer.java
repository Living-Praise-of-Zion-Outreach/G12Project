package com.lpzoutreach.g12lpz.Activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.appbar.AppBarLayout;
import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.LessonsModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperBible;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperBook;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.lpzoutreach.g12lpz.databinding.ActivityHtmlViewerBinding;


public class HtmlViewer extends AppCompatActivity {
    ActivityHtmlViewerBinding binding;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private static final int HIDE_THRESHOLD = 3;
    private int mScrolledDistance = 0;
    private boolean mControlsVisible=true;
    private String mContent,mContentDark;
    private elUtil eventListenerUtil = elUtil.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize()
    {
        binding = ActivityHtmlViewerBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarPdfViewer);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        load_html("");
        nested_scroll();
        result_activity();
        clickEvent();
    }

    private void result_activity(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==78){
                        Intent intent = result.getData();
                        if(intent!=null)
                        {
                        }
                    }
                });
    }

    private void clickEvent(){
        eventListenerUtil.addEventListener("refresh-simbanay-container", new elHandler() {
            @Override
            public void callback(elEvent event) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("lessonsID",getIntent().getStringExtra("lessonsID"));
                returnIntent.putExtra("lesson",getIntent().getStringExtra("lesson"));
                returnIntent.putExtra("content",mContent);
                returnIntent.putExtra("content_dark",mContentDark);
                setResult(78, returnIntent);
                finish();
            }
        });
    }

    boolean toogle = false;
    private void nested_scroll(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            binding.nsvHtmlViewer.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int l, int t, int oldl, int oldt) {
                    if(view.getScrollY() == 0){
                        if(!mControlsVisible){
                            showBottomBar();
                            mControlsVisible=true;
                        }
                    } else {
                        if(mScrolledDistance > HIDE_THRESHOLD && mControlsVisible){
                            hideBottomBar();
                            mControlsVisible=false;
                            mScrolledDistance = 0;
                        } else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible){
                            showBottomBar();
                            mControlsVisible=true;
                            mScrolledDistance = 0;
                        }
                    }
                    if((mControlsVisible && t - oldt > 30 ) || (!mControlsVisible && t - oldt < 30)){
                        mScrolledDistance += (t -oldt);
                    }
                }
            });
        }
    }

    private void scroll(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            binding.wvPdfViewer.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int l, int t, int oldl, int oldt) {
                    if(view.getScrollY() == 0){
                            if(!mControlsVisible){
                                showBottomBar();
                                //appBarLayoutDown(binding.ablHtmlViewer);
                                slide_down(binding.wvPdfViewer);
                                binding.toolbarPdfViewer.setVisibility(View.GONE);
                                mControlsVisible=true;
                            }
                    } else {
                        if(mScrolledDistance > HIDE_THRESHOLD && mControlsVisible){
                            hideBottomBar();
                            //appBarLayoutUp(binding.ablHtmlViewer);
                            slide_up(binding.wvPdfViewer);
                            mControlsVisible=false;
                            mScrolledDistance = 0;
                        } else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible){
                            showBottomBar();
                            //appBarLayoutDown(binding.ablHtmlViewer);
                            slide_down(binding.wvPdfViewer);
                            mControlsVisible=true;
                            mScrolledDistance = 0;
                        }
                    }
                    if((mControlsVisible && t - oldt > 10 ) || (!mControlsVisible && t - oldt < 10)){
                        mScrolledDistance += (t -oldt);
                    }
                }
            });
        }
    }
    private void slide_up(WebView view){
        //TranslateAnimation animate = new TranslateAnimation(0,0, view.getHeight(),0);
        //animate.setDuration(500);
        //animate.setFillAfter(true);
        //view.startAnimation(animate);
        //view.setVisibility(View.VISIBLE);
    }
    private void slide_down(WebView view){
        //TranslateAnimation animate = new TranslateAnimation(0,0, 0,view.getHeight());
        //animate.setDuration(500);
        //animate.setFillAfter(true);
        //view.startAnimation(animate);
        //view.setVisibility(View.GONE);
    }

    private void appBarLayoutUp(AppBarLayout view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.toolbar_slide_down);
        view.startAnimation(animation);
        view.setVisibility(View.GONE);
    }
    private void appBarLayoutDown(AppBarLayout view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        view.startAnimation(animation);
        view.setVisibility(View.VISIBLE);
    }

    private void hideBottomBar(){
        if(Build.VERSION.SDK_INT>11 && Build.VERSION.SDK_INT<19){
            View view = this.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        }else if(Build.VERSION.SDK_INT>-19){
            View decodeView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decodeView.setSystemUiVisibility(uiOptions);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(uiOptions);
        }

    }
    private void showBottomBar(){
        if(Build.VERSION.SDK_INT>11 && Build.VERSION.SDK_INT<19){
            View view = this.getWindow().getDecorView();
            view.setSystemUiVisibility(View.VISIBLE);
        }else if(Build.VERSION.SDK_INT>-19){
            View decodeView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decodeView.setSystemUiVisibility(uiOptions);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    @SuppressLint({"SetJavaScriptEnabled", "SdCardPath"})
    private void load_html(String reload){

        String topic = getIntent().getStringExtra("lesson");
        String content = getIntent().getStringExtra("content");
        mContent = content;

        String content_dark = getIntent().getStringExtra("content_dark");
        mContentDark = content_dark;

        setTitle(topic);

        WebSettings settings = binding.wvPdfViewer.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        //settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);

        binding.wvPdfViewer.setVerticalScrollBarEnabled(true);
        binding.wvPdfViewer.setHorizontalScrollBarEnabled(true);
        binding.wvPdfViewer.clearHistory();
        binding.wvPdfViewer.clearCache(true);
        binding.wvPdfViewer.clearFormData();



        //if(themes.isNightMode(getApplicationContext())){
            //Log.v("PATHLOCATION","file:///" + content_dark);
            //binding.wvPdfViewer.loadUrl("file:///" + content_dark);
        //}else{
            //binding.wvPdfViewer.loadUrl("file:///" + content);
        //}
        if(!reload.equals("")){
            binding.wvPdfViewer.reload();
        }

        if(themes.isNightMode(getApplicationContext())){
            binding.wvPdfViewer.loadUrl("file:///android_asset/files/" + content_dark);
        }else{
            binding.wvPdfViewer.loadUrl("file:///android_asset/files/" + content);
        }


        binding.wvPdfViewer.setWebViewClient( new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                binding.wvPdfViewer.setVisibility(View.GONE);
                super.onPageStarted(view, url, favicon);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                openHelperBook helper = new openHelperBook(getApplicationContext());
                    int lessonID = Integer.parseInt(getIntent().getStringExtra("lessonsID"));

                LessonsModel data = helper.get_book_lesson(lessonID);

                openHelperBible bibleHelper = new openHelperBible(getApplicationContext());
                    String listVersions = bibleHelper.get_all_bible_versions_by_string();

                openHelperHolyBible_Default verseHelper = new openHelperHolyBible_Default(getApplicationContext(),holyBibleSelector.get_bible_version(getApplicationContext()));

                binding.wvPdfViewer.setVisibility(View.VISIBLE);
                //Log.v("htmlCONTENT",data.getHtml_content());
                //binding.wvHolyBibleHome.loadUrl("javascript:document.getElementById('"+sharedData.get_bible_verses(requireContext())+"').scrollIntoView()");
                binding.wvPdfViewer.loadUrl(
                        "javascript:(function()" +
                                "{ loadData('" +
                                data.getHtml_header().replace("'","\"") + "','" +
                                data.getHtml_content().replace("'","\"") + "','" +
                                listVersions + "','" +
                                holyBibleSelector.get_long_name_bible_versions(getApplicationContext()) + "','" +
                                verseHelper.get_simbanay_verses(data.getStudy_the_word())+"'); })()");


                super.onPageFinished(view, url);
            }
        });

        binding.wvPdfViewer.addJavascriptInterface(new HtmlViewer.javascript_event(getApplicationContext(), eventListenerUtil),"javascript_event");

    }

    private static class javascript_event{
        private Context context;
        private elUtil eventListenerUtil;

        public javascript_event(Context context, elUtil eventListenerUtil) {
            this.context = context;
            this.eventListenerUtil = eventListenerUtil;
        }

        @JavascriptInterface
        public void getBibleVersion(String value){
            openHelperBible helper = new openHelperBible(context);
            String shortName = helper.get_short_name_versions_by_long_name(value);
            sharedData.set_bible_version(context,shortName);
            //Toast.makeText(context,value,Toast.LENGTH_LONG).show();
            eventListenerUtil.myCallBack("refresh-simbanay-container","");
        }

    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_person_consolidate_html_viewer:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_activity_html_viewer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //Intent returnIntent = new Intent();
        //setResult(78, returnIntent);
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }
}