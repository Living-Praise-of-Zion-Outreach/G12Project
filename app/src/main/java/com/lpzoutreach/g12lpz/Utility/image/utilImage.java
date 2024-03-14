package com.lpzoutreach.g12lpz.Utility.image;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.lpzoutreach.g12lpz.R;

import java.io.File;
public class utilImage {
    public static void src_path(ImageView iv, String path){
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            iv.setImageBitmap(myBitmap);
        }else{
            iv.setImageResource(R.drawable.no_image_available);
        }
    }
}
