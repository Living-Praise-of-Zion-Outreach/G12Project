package com.lpzoutreach.g12lpz.EventListener;

public interface el_ImplementEventDispatcher {
    public void addEventListener(String type, elHandler handler);
    public void removeEventListener(String type);
    public void dispatchEvent(elEvent event);
    public Boolean hasEventListener(String type);
    public void removeAllListeners();
}
