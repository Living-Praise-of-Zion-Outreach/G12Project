package com.lpzoutreach.g12lpz.Utility.image;
import android.graphics.Bitmap;
import android.util.Base64;
import java.io.ByteArrayOutputStream;

public class utilBitmap
{
    public static String encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }

}
