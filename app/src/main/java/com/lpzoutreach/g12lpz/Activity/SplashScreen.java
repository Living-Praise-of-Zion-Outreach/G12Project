package com.lpzoutreach.g12lpz.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import com.lpzoutreach.g12lpz.MainActivity;
import com.lpzoutreach.g12lpz.R;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.file.utilFile;
import com.lpzoutreach.g12lpz.Utility.sql.local.utilDatabase;
import com.lpzoutreach.g12lpz.Utility.themes.themes;
import com.lpzoutreach.g12lpz.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    //private FirebaseAuth auth;
    private final Handler mHandler = new Handler();

    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        //auth = FirebaseAuth.getInstance();

        //CREATE DATABASE
        if(utilFile.isPermissionGranted(getApplicationContext())){
            utilDatabase.checkDatabase(getApplicationContext(),sharedData.DBNAME);
        }

            Runnable ChangeActivity = () -> {
                Intent intent = new Intent(getApplication(), Home.class);
                startActivity(intent);
                finish();
            };
            Runnable ExitActivity = this::finish;


            if(themes.isNightMode(getApplicationContext())){
                binding.pbBarSplashScreen.setBackground(getResources().getDrawable(R.color._super_dark_fifth));
            }

            mHandler.postDelayed(ChangeActivity,3000);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                ;;
        decorView.setSystemUiVisibility(uiOptions);
    }
}