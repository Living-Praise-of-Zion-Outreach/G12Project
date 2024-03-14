package com.lpzoutreach.g12lpz.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.ads.utilAds;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.holyBibleSelector;
import com.lpzoutreach.g12lpz.Utility.sql.local.holybible.openHelperHolyBible_Default;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.lpzoutreach.g12lpz.databinding.ActivityChurchDetailsBinding;
import com.lpzoutreach.g12lpz.databinding.ActivityHolyBibleCopyrightBinding;

public class HolyBibleCopyright extends AppCompatActivity {
    ActivityHolyBibleCopyrightBinding binding;
    private RewardedAd mRewardAd;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHolyBibleCopyrightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        setSupportActionBar(binding.toolbarHolyBibleCopyright);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Copyright");
        loadData();
        setAds();
    }

    private void setAds(){
        utilAds.initialize(HolyBibleCopyright.this);
        utilAds.loadBanner(HolyBibleCopyright.this,binding.adView,binding.llAdview);
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
                            mInterstitialAd.show(HolyBibleCopyright.this);
                        }
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            mInterstitialAd = null;
                        }
                    });
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadData(){
        WebSettings settings = binding.wvContentHolyBibleCopyright.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        //settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        binding.wvContentHolyBibleCopyright.setVerticalScrollBarEnabled(true);
        binding.wvContentHolyBibleCopyright.setHorizontalScrollBarEnabled(true);

        if(themes.isNightMode(getApplicationContext())){
            binding.wvContentHolyBibleCopyright.loadUrl("file:///android_asset/files/bible/holy_bible_text_dark.html");
        }else{
            binding.wvContentHolyBibleCopyright.loadUrl("file:///android_asset/files/bible/holy_bible_text.html");
        }

        binding.wvContentHolyBibleCopyright.setWebViewClient( new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                binding.wvContentHolyBibleCopyright.setVisibility(View.GONE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                binding.wvContentHolyBibleCopyright.setVisibility(View.VISIBLE);
                openHelperHolyBible_Default helper = new openHelperHolyBible_Default(getApplicationContext(), holyBibleSelector.get_bible_version(getApplicationContext()));
                String content = helper.get_copyright();

                content = content.replace("'","`");
                String footer = "";
                int selectedVerseHighlight = sharedData.get_bible_verses(getApplicationContext());
                int fontSize = 14;
                int lineHeight = 25;
                binding.wvContentHolyBibleCopyright.loadUrl(
                        "javascript:(function()" +
                                "{ loadData('" +
                                content + "','" +
                                footer + "'," +
                                selectedVerseHighlight + "," +
                                fontSize + "," +
                                lineHeight+"); })()");
                binding.wvContentHolyBibleCopyright.loadUrl(
                        "javascript:(function()" +
                                "{ hide_learn_more(); })()");
                //binding.wvHolyBibleHome.loadUrl("javascript:document.getElementById('"+sharedData.get_bible_verses(requireContext())+"').scrollIntoView()");
                super.onPageFinished(view, url);

            }
        });

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