package com.lpzoutreach.g12lpz.EventListener;

public class elUtil extends elIDispatcher{
    private static elUtil ourInstance = new elUtil();
    private String type;
    private Object data;
    public static final elUtil getInstance() {return  ourInstance;}

    private elUtil(){}

    public void myCallBack(String type, Object data){
        elEvent event = new elEvent(type,data);
        dispatchEvent(event);
    }
}
