package com.lpzoutreach.g12lpz.Utility.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.internet.utilNetwork;

public class utilAds {

    //2 CHOICES : testing | live
    private static final String adType = "testing";
    public static String appType = "premium";
    public static String defaultParameter = "premium";

    public static void initialize(Context context){
        if(appType.equals(defaultParameter)){
            if(utilNetwork.isConnected(context)){
                MobileAds.initialize(context, initializationStatus -> {
                    //Toast.makeText(context,initializationStatus.toString(),Toast.LENGTH_LONG).show();
                });
            }
        }
    }

    public static void loadBanner(Context context, AdView object, LinearLayout container){
        if(appType.equals("basic")) {
            if (utilNetwork.isConnected(context)) {
                AdRequest adRequest = new AdRequest.Builder().build();
                //object.setAdUnitId(getBannerAdID(context));
                object.loadAd(adRequest);
                object.setVisibility(View.VISIBLE);
                container.setVisibility(View.VISIBLE);
            } else {
                object.setVisibility(View.GONE);
                container.setVisibility(View.GONE);
            }
        }else{
            object.setVisibility(View.GONE);
            container.setVisibility(View.GONE);
        }
    }

    public static void generateBottomBanner(Context context, ConstraintLayout constraintLayout){
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getBannerAdID(context));
        ConstraintLayout.LayoutParams banner_params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        banner_params.startToStart = constraintLayout.getId();
        banner_params.bottomToBottom = constraintLayout.getId();
        banner_params.endToEnd = constraintLayout.getId();
        adView.setLayoutParams(banner_params);
        constraintLayout.addView(adView);
    }

    public static void loadRewarded(Context context){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, utilAds.getRewardedID(context),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        //mRewardAd = null;
                    }
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        //mRewardAd = ad;
                        //mRewardAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        //    @Override
                        //    public void onAdDismissedFullScreenContent() {
                        //        Toast.makeText(getApplicationContext(),"dismis",Toast.LENGTH_LONG).show();
                        //    }
                        //});
                    }
                });
    }

    public static String getBannerAdID(Context context){
        if(adType.equals("testing")){
            return context.getResources().getString(R.string.ad_banner_testing);
        }else{
            return context.getResources().getString(R.string.ad_banner);
        }
    }

    public static String getInterstitialAdID(Context context){
        if(adType.equals("testing")){
            return context.getResources().getString(R.string.ad_interstitial_testing);
        }else{
            return context.getResources().getString(R.string.ad_interstitial);
        }
    }

    public static String getRewardedID(Context context){
        if(adType.equals("testing")){
            return context.getResources().getString(R.string.ad_rewarded_test);
        }else{
            return context.getResources().getString(R.string.ad_rewarded);
        }
    }




}
