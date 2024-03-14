package com.lpzoutreach.g12lpz.EventListener;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

public class elIDispatcher implements el_ImplementEventDispatcher{
    protected ArrayList<elListener> listenerList = new ArrayList<>();


    @Override
    public void addEventListener(String type, elHandler handler) {
        try{
            elListener listener = new elListener(type, handler);
            removeEventListener(type);
            listenerList.add(0,listener);
        }catch (Exception e){
            Log.e("AddEventListner",e.toString());
        }

    }

    @Override
    public void removeEventListener(String type) {
        try{
            for(Iterator<elListener> iterator = listenerList.iterator(); iterator.hasNext();) {
                elListener listener = (elListener) iterator.next();
                if(listener.getType()==type){
                    listenerList.remove(listener);
                }
            }
        }catch (Exception e){
            Log.e("removeEventListener",e.toString());
        }
    }

    @Override
    public void dispatchEvent(elEvent event) {
            for(Iterator<elListener> iterator = listenerList.iterator(); iterator.hasNext();){
            elListener listener = (elListener) iterator.next();
                    if(event.getstrType()==listener.getType()){
                    elHandler elHandler = listener.getHandler();
                        elHandler.callback(event);
                    }
            }
    }

    @Override
    public Boolean hasEventListener(String type) {
        return null;
    }

    @Override
    public void removeAllListeners() {

    }
}
