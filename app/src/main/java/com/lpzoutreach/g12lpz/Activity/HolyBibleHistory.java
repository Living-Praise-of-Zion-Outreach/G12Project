package com.lpzoutreach.g12lpz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lpzoutreach.g12lpz.EventListener.elEvent;
import com.lpzoutreach.g12lpz.EventListener.elHandler;
import com.lpzoutreach.g12lpz.EventListener.elUtil;
import com.lpzoutreach.g12lpz.ListView.holyBibleHistoryListView;
import com.lpzoutreach.g12lpz.Models.HolyBibleModel;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.ads.utilAds;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperBible;
import com.lpzoutreach.g12lpz.databinding.ActivityBrowseEbookBinding;
import com.lpzoutreach.g12lpz.databinding.ActivityHolyBibleHistoryBinding;

import java.util.List;

public class HolyBibleHistory extends AppCompatActivity {
    ActivityHolyBibleHistoryBinding binding;
    private elUtil eventListenerUtil = elUtil.getInstance();
    private RewardedAd mRewardAd;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHolyBibleHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize(){
        setSupportActionBar(binding.toolbarHolyBibleHistory);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("History");
        setAds();
        reloadData();
        clickEvent();
    }

    private void setAds(){
        utilAds.initialize(HolyBibleHistory.this);
        utilAds.loadBanner(HolyBibleHistory.this,binding.adView,binding.llAdview);
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

    private void reloadData(){
        openHelperBible helper = new openHelperBible(getApplicationContext());
        List<HolyBibleModel> data = helper.get_bible_history_all(eventListenerUtil,"open-bible-history");
        holyBibleHistoryListView  adapter = new holyBibleHistoryListView(HolyBibleHistory.this,R.layout.lv_holy_bible_history,data);
        binding.lvHolyBibleHistory.setAdapter(adapter);
        binding.lvHolyBibleHistory.setDivider(null);
        if(data.size()>0){
            binding.clNoDataFoundHolyBibleHistory.setVisibility(View.GONE);
            binding.lvHolyBibleHistory.setVisibility(View.VISIBLE);
        }else{
            binding.clNoDataFoundHolyBibleHistory.setVisibility(View.VISIBLE);
            binding.lvHolyBibleHistory.setVisibility(View.GONE);
        }
    }

    private void clickEvent(){
        eventListenerUtil.addEventListener("open-bible-history", new elHandler() {
            @Override
            public void callback(elEvent event) {
                String[] data = event.getData().toString().split("~");
                sharedData.set_bible_book_no(getApplicationContext(), Integer.parseInt(data[0]));
                sharedData.set_bible_chapter(getApplicationContext(),Integer.parseInt(data[1]));
                sharedData.set_bible_verses(getApplicationContext(),Integer.parseInt(data[2]));
                sharedData.set_bible_version(getApplicationContext(),data[3].trim());
                Intent returnIntent = new Intent();
                setResult(78, returnIntent);
                finish();
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete_all_holy_bible_history:
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.MyDialogTheme);
                builder.setTitle(getResources().getString(R.string.delete_title_dialog))
                        .setCancelable(false)
                        .setMessage(getResources().getString(R.string.delete_all_history_content_dialog))
                        .setPositiveButton(getResources().getString(R.string.yes_button_dialog), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            openHelperBible helper = new openHelperBible(getApplicationContext());
                            helper.delete_bible_history();
                            Toast.makeText(getApplicationContext(),"You have successfully delete all history!", Toast.LENGTH_LONG).show();
                            reloadData();
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no_button_dialog), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_activity_holy_bible_history, menu);
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