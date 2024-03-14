package com.lpzoutreach.g12lpz.Utility.file;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

public class utilPermission {

    private static final int MY_PERFMISSION_REQUEST_STORAGE=1;

    public static void requestExternalAccess(Activity activity){
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERFMISSION_REQUEST_STORAGE);
            }else
            {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERFMISSION_REQUEST_STORAGE);
            }
        }
    }

    public static boolean checkExternalAccess(Activity activity){
        if(ContextCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            return false;
        else
            return true;
    }

}
