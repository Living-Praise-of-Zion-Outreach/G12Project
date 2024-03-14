package com.lpzoutreach.g12lpz.EventListener;

public class elEvent {
public static final String COMPLETE = "complete";
protected String type;
protected Object data;

public elEvent(String type, Object data){
    initProperties(type,data);
}

protected void initProperties(String type, Object data){
    this.type=type;
    this.data=data;
}

public String getstrType(){return this.type;}
public Object getData() {return this.data;}

}
