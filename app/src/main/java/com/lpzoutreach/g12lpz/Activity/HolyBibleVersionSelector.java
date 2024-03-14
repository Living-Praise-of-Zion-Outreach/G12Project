package com.lpzoutreach.g12lpz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.RecyclerView.holyBibleVersionSelectorRecyclerView;
import com.lpzoutreach.g12lpz.Utility.ads.utilAds;
import com.lpzoutreach.g12lpz.Utility.dialog.ProgressHorizontalBar;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperBible;
import com.lpzoutreach.g12lpz.Utility.sql.php.bibleTransfer;
import com.lpzoutreach.g12lpz.databinding.ActivityHolyBibleSelectorBinding;
import com.lpzoutreach.g12lpz.databinding.ActivityHolyBibleVersionSelectorBinding;

import java.util.ArrayList;

public class HolyBibleVersionSelector extends AppCompatActivity {
    private ActivityHolyBibleVersionSelectorBinding binding;
    private elUtil eventListenerUtil = elUtil.getInstance();

    private ProgressHorizontalBar progressHorizontalBar;
    private RewardedAd mRewardAd;
    private boolean blnLanguage;
    private InterstitialAd mInterstitialAd;
    private String searchValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize(){
        binding = ActivityHolyBibleVersionSelectorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbarBibleVersionSelector);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        blnLanguage = false;
        progressHorizontalBar = new ProgressHorizontalBar(HolyBibleVersionSelector.this);
        setTitle("Version");
        searchValue="";
        setAds();
        clickEvent();
        reloadData();
        //binding.pbLoadingHolyBibleVersionSelector.setVisibility(View.GONE);
        LinearLayoutManager manager = new LinearLayoutManager(HolyBibleVersionSelector.this);
        binding.rvListBibleVersionSelector.setLayoutManager(manager);
        setRecentlyUsedBibleVersion();
        refreshData();
    }


    private void setAds(){
        utilAds.initialize(HolyBibleVersionSelector.this);
        utilAds.loadBanner(HolyBibleVersionSelector.this,binding.adView,binding.llAdview);
        AdRequest adRequest = new AdRequest.Builder().build();
        if(utilAds.appType.equals(utilAds.defaultParameter)) {
            RewardedAd.load(this, utilAds.getRewardedID(getApplicationContext()),
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            mRewardAd = null;
                        }
                        @Override
                        public void onAdLoaded(@NonNull RewardedAd ad) {
                            mRewardAd = ad;
                            mRewardAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                }
                            });
                        }
                    });
            InterstitialAd.load(this,utilAds.getInterstitialAdID(getApplicationContext()), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;
                        }
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            mInterstitialAd = null;
                        }
                    });
        }
    }

    private void clickEvent(){
        eventListenerUtil.addEventListener("download-holy-bible", new elHandler() {
            @Override
            public void callback(elEvent event) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(HolyBibleVersionSelector.this, R.style.MyDialogTheme);
                builder.setTitle("Download Confirm")
                        .setCancelable(true)
                        .setMessage("Do you want to download this?")
                        .setPositiveButton(getResources().getString(R.string.yes_button_dialog), (dialog, which) -> {

                            if (mInterstitialAd != null) {
                                mInterstitialAd.show(HolyBibleVersionSelector.this);

                            } else {
                                //Toast.makeText(getApplicationContext(),"Failed to load an Ad",Toast.LENGTH_LONG).show();
                            }

                            if (mRewardAd != null) {
                                Activity activityContext = HolyBibleVersionSelector.this;
                                mRewardAd.show(activityContext, new OnUserEarnedRewardListener() {
                                    @Override
                                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                        progressHorizontalBar.show();
                                        bibleTransfer.download_holy_bible(getApplicationContext(),event.getData().toString(),progressHorizontalBar,eventListenerUtil);
                                        //Toast.makeText(getApplicationContext(),"earned",Toast.LENGTH_LONG).show();
                                        setAds();
                                    }
                                });
                            } else {
                                progressHorizontalBar.show();
                                bibleTransfer.download_holy_bible(getApplicationContext(),event.getData().toString(),progressHorizontalBar,eventListenerUtil);
                             }
                        })
                        .setNegativeButton(getResources().getString(R.string.no_button_dialog), (dialog, which) -> dialog.cancel()).show();
            }
        });
        eventListenerUtil.addEventListener("select-bible-version", new elHandler() {
            @Override
            public void callback(elEvent event) {
                String[] data = event.getData().toString().split("~");
                if(data[1].equals("")){
                    if(data[2].equals("asset")){
                        sharedData.set_bible_version(getApplicationContext(),data[0].trim());
                        Intent returnIntent = new Intent();
                        setResult(78, returnIntent);
                        finish();
                    }
                }else{
                    sharedData.set_bible_version(getApplicationContext(),data[0].trim());
                    Intent returnIntent = new Intent();
                    setResult(78, returnIntent);
                    finish();
                }
            }
        });
        eventListenerUtil.addEventListener("reload-bible-list", new elHandler() {
            @Override
            public void callback(elEvent event) {
                ArrayList<HolyBibleModel> server_data = (ArrayList<HolyBibleModel>) event.getData();
                openHelperBible helper = new openHelperBible(getApplicationContext());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HolyBibleVersionSelector.this);
                binding.rvListBibleVersionSelector.setLayoutManager(linearLayoutManager);

                for(int i= 0;i<server_data.size();i++){
                   if(helper.check_holy_bible(server_data.get(i).getHolyBibleID())){
                       server_data.get(i).setDownloaded(true);
                       if(server_data.get(i).getShortBibleName().equals(sharedData.get_bible_version(getApplicationContext()))){
                           server_data.get(i).setSelected(true);
                       }else{
                           server_data.get(i).setSelected(false);
                       }
                   }
                }

                holyBibleVersionSelectorRecyclerView adapter = new holyBibleVersionSelectorRecyclerView(HolyBibleVersionSelector.this, server_data);
                binding.rvListBibleVersionSelector.setAdapter(adapter);
                setLanguageHeading(server_data.size());
                //helper.get_all_bible_versions_recently_used(eventListenerUtil)

            }
        });
        eventListenerUtil.addEventListener("load-offline-bible-language", new elHandler() {
            @Override
            public void callback(elEvent event) {

                openHelperBible helper = new openHelperBible(HolyBibleVersionSelector.this);
                ArrayList<HolyBibleModel> data = helper.get_all_bible_versions(eventListenerUtil,"select-bible-version");

                holyBibleVersionSelectorRecyclerView adapter = new holyBibleVersionSelectorRecyclerView(HolyBibleVersionSelector.this, data);
                binding.rvListBibleVersionSelector.setAdapter(adapter);

                setLanguageHeading(data.size());
                //RELOAD ALL LANGUAGE LOCAL ONLY
                reloadAllLanguage((ArrayList<String>) event.getData());

            }
        });
        eventListenerUtil.addEventListener("reload-bible-language", new elHandler() {
            @Override
            public void callback(elEvent event) {
                reloadAllLanguage((ArrayList<String>) event.getData());
                setLanguageHeading(0);
                binding.pbLoadingVersionSelector.setVisibility(View.VISIBLE);
                bibleTransfer.get_from_server_holy_bible_list(HolyBibleVersionSelector.this, binding.pbLoadingVersionSelector, eventListenerUtil, "reload-bible-list","select-bible-version",searchValue );
            }
        });
        eventListenerUtil.addEventListener("complete-download-holy-bible", new elHandler() {
            @Override
            public void callback(elEvent event) {
                Toast.makeText(getApplicationContext(),"Download Completed!",Toast.LENGTH_LONG).show();
                //sharedData.set_bible_book_no(getApplicationContext(),10);
                //sharedData.set_bible_chapter(getApplicationContext(),1);
                sharedData.set_bible_version(getApplicationContext(),event.getData().toString());
                Intent returnIntent = new Intent();
                setResult(78, returnIntent);
                finish();
            }
        });

    }
    @SuppressLint("SetTextI18n")
    private void reloadData(){
        //progressDialogBar.show();
        bibleTransfer.get_from_server_bible_language(HolyBibleVersionSelector.this, binding.pbLoadingVersionSelector, eventListenerUtil, "reload-bible-language");
    }

    private void refreshData(){
        binding.refreshLayout.setOnRefreshListener(() -> {
            reloadData();
            binding.refreshLayout.setRefreshing(false);
        });
    }

    private void reloadAllLanguage(ArrayList<String> data){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(HolyBibleVersionSelector.this, android.R.layout.simple_spinner_dropdown_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerVersionSelector.setAdapter(adapter);
        binding.spinnerVersionSelector.setSelection(adapter.getPosition(sharedData.get_bible_current_language(getApplicationContext())));
        blnLanguage=false;

        binding.spinnerVersionSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                sharedData.set_bible_current_language(getApplicationContext(),value);
                if(blnLanguage){
                    bibleTransfer.get_from_server_bible_language(HolyBibleVersionSelector.this, binding.pbLoadingVersionSelector, eventListenerUtil, "reload-bible-language");
                }else{
                    blnLanguage=true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void setLanguageHeading(int total_language){
        binding.tvLanguageVersionSelector.setText(sharedData.get_bible_current_language(HolyBibleVersionSelector.this).toUpperCase() + " ("+total_language+")");
    }
    private void setRecentlyUsedBibleVersion(){
        openHelperHolyBible_Default helper_version = new openHelperHolyBible_Default(HolyBibleVersionSelector.this, holyBibleSelector.get_bible_version(getApplicationContext()));
        binding.tvTitleHolyBibleVersionSelector.setText(sharedData.get_bible_version(HolyBibleVersionSelector.this));
        binding.tvSubtitleHolyBibleVersionSelector.setText(helper_version.get_description());
    }


    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sort_order_holy_bible_selector:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_activity_holy_bible_version_selector, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity);
    }
}