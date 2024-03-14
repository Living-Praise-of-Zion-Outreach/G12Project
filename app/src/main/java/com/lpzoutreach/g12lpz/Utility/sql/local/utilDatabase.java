package com.lpzoutreach.g12lpz.Utility.sql.local;
import android.content.Context;
import android.util.Log;

import com.lpzoutreach.g12lpz.Utility.file.sharedData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class utilDatabase {

    public static void checkDatabase(Context context, String dbname){
        File database = context.getDatabasePath(dbname);
        if(false==database.exists())
        {
            copyDatabase(context,dbname);
        }
    }
    public static void checkDatabase_other_database(Context context, String dbname){
        File database = context.getDatabasePath(dbname);
        if(false==database.exists())
        {
            //THIS IS A FILE TO DOWNLOAD
            copyDatabase_from_file_to_database(dbname);
        }
    }

    public static void copyDatabase_from_file_to_database(String dbname){
        try{
            //String filePath = "/data/user/0/com.lpzoutreach.g12lpz/files/databases/" + dbname;
            String filePath = sharedData.DBFILEPATH + dbname;
            FileInputStream inputStream1 = new FileInputStream(filePath);
            File x = new File(filePath);


            String out_filename = openHelperProfileInformation.DBPATH + dbname;
            OutputStream outputStream = new FileOutputStream(out_filename);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length=inputStream1.read(buff)) > 0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();

            if(x.exists()){
                x.delete();
            }

        }catch (Exception e){
            e.printStackTrace();
            Log.v("ERRORCOPYDATABASE",e.toString());
        }
    }

    public static void copyDatabase(Context context, String dbname){
        try{
            InputStream inputStream = context.getAssets().open("databases/" + dbname);
            String outfilename = openHelperProfileInformation.DBPATH + dbname;
            OutputStream outputStream = new FileOutputStream(outfilename);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length=inputStream.read(buff)) > 0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            //Toast.makeText(context,"New State is created",Toast.LENGTH_LONG).show();
            //Log.v(dbname,"");

        }catch (Exception e){
            e.printStackTrace();

        }
    }


}
