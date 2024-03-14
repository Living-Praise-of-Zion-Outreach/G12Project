package com.lpzoutreach.g12lpz.Utility.string;

public class utilString {
    public static String setConcat(String source, String newValue, String separator){

        if(!newValue.isEmpty()){
            if(source.equals("")){
                if(!newValue.equals(""))
                    source = newValue;
            }else{
             source += separator + " " + newValue;
            }
        }


        return source;
    }

}
