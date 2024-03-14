package com.lpzoutreach.g12lpz.Utility.string;

import android.content.ContentResolver;
import android.webkit.MimeTypeMap;
import android.content.Context;
import android.net.Uri;

import java.text.DecimalFormat;

public class utilFormatter {

        public static String setDefaultValues(String value, String defaultvalues){
            if (value.isEmpty()){
                return defaultvalues;
            }
            else
                return value;
        }

    // function to generate a random string of length n
    public static String getAlphaNumericString(int n)
    {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static String getFileExtension(Context context, Uri uri){
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public static String getIntegerWithComma(String value){
        int total_members = Integer.parseInt(value);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String yourFormattedString = formatter.format(total_members);
        return yourFormattedString;
    }

}
