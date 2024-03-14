package com.lpzoutreach.g12lpz.Utility.file;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.ContextCompat;

import java.io.File;

public class utilFile {

    public static void deleteFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    public static boolean isPermissionGranted(Context context){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
            return Environment.isExternalStorageManager();
        }else{
            int readExtStorage = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
            return readExtStorage == PackageManager.PERMISSION_GRANTED;
        }
    }

}
