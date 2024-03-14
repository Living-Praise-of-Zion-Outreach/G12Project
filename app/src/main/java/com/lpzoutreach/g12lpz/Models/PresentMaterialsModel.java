package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class PresentMaterialsModel {
    private int materialPresentID=0;
    private String present_Logo="";
    private String presentTitle="";
    private String presentSubTitle="";
    private String presentLanguage="";
    private String presentSlides="";
    private String content="";
    private String content_dark="";
    private Boolean Is_file_downloaded=false;
    private elUtil eventListenerUtil;

    private String android_content_path="";

    public PresentMaterialsModel(){
    }

    public Boolean getIs_file_downloaded() {
        return Is_file_downloaded;
    }

    public void setIs_file_downloaded(Boolean is_file_downloaded) {
        Is_file_downloaded = is_file_downloaded;
    }


    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public int getMaterialPresentID() {
        return materialPresentID;
    }

    public void setMaterialPresentID(int materialPresentID) {
        this.materialPresentID = materialPresentID;
    }

    public String getPresent_Logo() {
        return present_Logo;
    }

    public void setPresent_Logo(String present_Logo) {
        this.present_Logo = present_Logo;
    }

    public String getPresentTitle() {
        return presentTitle;
    }

    public void setPresentTitle(String presentTitle) {
        this.presentTitle = presentTitle;
    }

    public String getPresentSubTitle() {
        return presentSubTitle;
    }

    public void setPresentSubTitle(String presentSubTitle) {
        this.presentSubTitle = presentSubTitle;
    }

    public String getPresentLanguage() {
        return presentLanguage;
    }

    public void setPresentLanguage(String presentLanguage) {
        this.presentLanguage = presentLanguage;
    }

    public String getPresentSlides() {
        return presentSlides;
    }

    public void setPresentSlides(String presentSlides) {
        this.presentSlides = presentSlides;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_dark() {
        return content_dark;
    }

    public void setContent_dark(String content_dark) {
        this.content_dark = content_dark;
    }

    public String getAndroid_content_path() {
        return android_content_path;
    }

    public void setAndroid_content_path(String android_content_path) {
        this.android_content_path = android_content_path;
    }
}
