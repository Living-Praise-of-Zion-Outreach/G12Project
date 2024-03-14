package com.lpzoutreach.g12lpz.Fragments;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lpzoutreach.g12lpz.Activity.HolyBibleCopyright;
import com.lpzoutreach.g12lpz.Activity.HolyBibleSelector;
import com.lpzoutreach.g12lpz.Activity.HolyBibleVersionSelector;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.RecyclerView.HolyBibleHomeRecyclerView;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.lpzoutreach.g12lpz.databinding.FragmentHolyBibleHomeBinding;

import java.util.ArrayList;

public class HolyBibleHomeFragment extends Fragment {
    FragmentHolyBibleHomeBinding binding;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private FloatingActionButton fabLeft,fabRight;
    private elUtil eventListenerUtil;
    private int mScrolledDistance = 0;
    private boolean mControlsVisible=true;
    private int total_chapters;
    private static final int HIDE_THRESHOLD = 3;

    public HolyBibleHomeFragment(elUtil eventListenerUtil, FloatingActionButton fabLeft, FloatingActionButton fabRight) {
        // Required empty public constructor
        this.eventListenerUtil = eventListenerUtil;
        this.fabLeft = fabLeft;
        this.fabRight = fabRight;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {setHasOptionsMenu(true);super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHolyBibleHomeBinding.inflate(inflater,container,false);
        initialize();
        return binding.getRoot();
    }

    private void initialize(){

        reloadVerses();
        nested_scroll();
        clickEvent();
        resultLauncher();
    }

    private void resultLauncher(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==78){
                        Intent intent = result.getData();
                        if(intent!=null)
                        {
                            reloadVerses();
                            eventListenerUtil.myCallBack("refresh-bible-title","");
                        }
                    }
                });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void reloadVerses(){
        //LinearLayoutManager manager = new LinearLayoutManager(requireContext());
        //binding.rvListHolyBibleHome.setLayoutManager(manager);


        //openHelperHolyBible_Default helper = new openHelperHolyBible_Default(requireContext(), holyBibleSelector.get_bible_version(requireContext()));
        //sharedData.set_bible_version(requireContext(),"KJ2");
        openHelperHolyBible_Default helper = new openHelperHolyBible_Default(requireContext(), holyBibleSelector.get_bible_version(requireContext()));

        ArrayList<HolyBibleModel> data = helper.get_verse_text_per_chapter();
        total_chapters = helper.get_total_chapters();

        //HolyBibleHomeRecyclerView adapter = new HolyBibleHomeRecyclerView(requireContext(), data);
        //binding.rvListHolyBibleHome.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //binding.tvFooterInfoHolyBible.setText(Html.fromHtml(helper.get_info(), Html.FROM_HTML_MODE_COMPACT));
        }
        else {
            //binding.tvFooterInfoHolyBible.setText(helper.get_info());
        }

        WebSettings settings = binding.wvHolyBibleHome.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        //settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        binding.wvHolyBibleHome.setVerticalScrollBarEnabled(true);
        binding.wvHolyBibleHome.setHorizontalScrollBarEnabled(true);

        if(themes.isNightMode(requireContext())){
            binding.wvHolyBibleHome.loadUrl("file:///android_asset/files/bible/holy_bible_text_dark.html");
        }else{
            binding.wvHolyBibleHome.loadUrl("file:///android_asset/files/bible/holy_bible_text.html");
        }

        binding.wvHolyBibleHome.setWebViewClient( new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                binding.wvHolyBibleHome.setVisibility(View.GONE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                binding.wvHolyBibleHome.setVisibility(View.VISIBLE);
                openHelperHolyBible_Default helper = new openHelperHolyBible_Default(requireContext(),holyBibleSelector.get_bible_version(requireContext()));
                String content = helper.get_list_verse_web_view();

                Log.d("DEBUG",content);

                String footer = helper.get_info();
                int selectedVerseHighlight = sharedData.get_bible_verses(requireContext());
                int fontSize = 14;
                int lineHeight = 30;
                binding.wvHolyBibleHome.loadUrl(
                        "javascript:(function()" +
                                "{ loadData(`" +
                                content + "`,'" +
                                footer + "'," +
                                selectedVerseHighlight + "," +
                                fontSize + "," +
                                lineHeight+"); })()");
                //binding.wvHolyBibleHome.loadUrl("javascript:document.getElementById('"+sharedData.get_bible_verses(requireContext())+"').scrollIntoView()");
                super.onPageFinished(view, url);
            }
        });

        binding.wvHolyBibleHome.addJavascriptInterface(new javascript_event(requireContext(), binding.nsvHolyBibleHome,eventListenerUtil),"javascript_event");
    }

    private static class javascript_event{
        private Context context;
        private NestedScrollView nestedScrollView;
        Handler mHandler = new Handler();
        String values;
        Runnable mScrollDown;
        Runnable scrollTos;
        private elUtil eventListenerUtil;
        public javascript_event(Context context,NestedScrollView nestedScrollView, elUtil eventListenerUtil){
            this.context = context;
            this.eventListenerUtil = eventListenerUtil;
            this.nestedScrollView = nestedScrollView;
            scrollTos = () -> {
                double yPos = Double.parseDouble(values);
                if(sharedData.get_bible_verses(context)>=1 && sharedData.get_bible_verses(context)<=7){
                  yPos = yPos * 1.3;
                }else if(sharedData.get_bible_verses(context)>=8 && sharedData.get_bible_verses(context)<=13){
                    yPos = yPos * 2;
                }else if(sharedData.get_bible_verses(context)>=14 && sharedData.get_bible_verses(context)<=30){
                    yPos = yPos * 2.5;
                }else if(sharedData.get_bible_verses(context)>=31){
                    yPos = yPos * 3.0;
                }
                int yD = (int) yPos;
                //THIS IS HOW TO ANIMATE SLIDES NESTEDSCROLLVIEW
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(nestedScrollView, "scrollY", 0, yD).setDuration(500);
                objectAnimator.start();
            };
        }

        @JavascriptInterface
        public void getXYPos(String Y){
            values = Y;
            mScrollDown = () -> mHandler.postDelayed(scrollTos, 300);
            mScrollDown.run();
        }

        @JavascriptInterface
        public void openCopyRight(){
            Intent intent = new Intent(context, HolyBibleCopyright.class);
            context.startActivity(intent);
        }

        @JavascriptInterface
        public void getSelectedVerse(String value){
            eventListenerUtil.myCallBack("open-color-palette",value);
        }
    }

    private void clickEvent(){
        mControlsVisible=false;
        mScrolledDistance = 0;
        fabLeft.setOnClickListener(view -> {
            mControlsVisible=false;
            mScrolledDistance = 0;
            int currentChapter = sharedData.get_bible_chapter(requireContext());
            currentChapter-=1;
            if(currentChapter<0){
                sharedData.set_bible_chapter(requireContext(),total_chapters);
            }else{
                sharedData.set_bible_chapter(requireContext(),currentChapter);
            }
            sharedData.set_bible_verses(requireContext(),0);
            reloadVerses();
            eventListenerUtil.myCallBack("refresh-bible-title","");
        });
        fabRight.setOnClickListener(view -> {
            mControlsVisible=false;
            mScrolledDistance = 0;
            int currentChapter = sharedData.get_bible_chapter(requireContext());
            currentChapter+=1;
            if(currentChapter>total_chapters){
                sharedData.set_bible_chapter(requireContext(),0);
            }else{
                sharedData.set_bible_chapter(requireContext(),currentChapter);
            }
            sharedData.set_bible_verses(requireContext(),0);
            reloadVerses();
            eventListenerUtil.myCallBack("refresh-bible-title","");
        });
    }

    private void nested_scroll(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.nsvHolyBibleHome.setOnScrollChangeListener((View.OnScrollChangeListener) (view, l, t, oldl, oldt) -> {
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
            });
        }
    }
    private void showBottomBar(){
        eventListenerUtil.myCallBack("show-navigation-bottom","");
    }
    private void hideBottomBar(){
        eventListenerUtil.myCallBack("hide-navigation-bottom","");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_holy_bible,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_search_holy_bible:
                intent = new Intent(requireContext(), HolyBibleSelector.class);
                activityResultLauncher.launch(intent);
                requireActivity().overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
                break;
            case R.id.menu_search_bible_version_holy_bible:
                intent = new Intent(requireContext(), HolyBibleVersionSelector.class);
                activityResultLauncher.launch(intent);
                requireActivity().overridePendingTransition(R.anim.slide_in_right_activity,R.anim.slide_out_left_activity);
                break;
            default:
                break;
        }
        return false;
    }

}