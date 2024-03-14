package com.lpzoutreach.g12lpz.Models;

public class NotifyAppUpdateModel {

    private int appUpdateID=0;
    private String appTitle="";
    private String appMinRequirements="";
    private String appMaxRequirements="";

    private String appAndroidDescription="";
    private String appDescription="";
    private String appVersion="";
    private String appPath="";
    private String publishReady="";
    private String appDatePublished="";
    private String dtCreated="";

    public NotifyAppUpdateModel(){}

    public int getAppUpdateID() {
        return appUpdateID;
    }

    public void setAppUpdateID(int appUpdateID) {
        this.appUpdateID = appUpdateID;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppMinRequirements() {
        return appMinRequirements;
    }

    public void setAppMinRequirements(String appMinRequirements) {
        this.appMinRequirements = appMinRequirements;
    }

    public String getAppMaxRequirements() {
        return appMaxRequirements;
    }

    public void setAppMaxRequirements(String appMaxRequirements) {
        this.appMaxRequirements = appMaxRequirements;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getPublishReady() {
        return publishReady;
    }

    public void setPublishReady(String publishReady) {
        this.publishReady = publishReady;
    }

    public String getAppDatePublished() {
        return appDatePublished;
    }

    public void setAppDatePublished(String appDatePublished) {
        this.appDatePublished = appDatePublished;
    }

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }

    public String getAppAndroidDescription() {
        return appAndroidDescription;
    }

    public void setAppAndroidDescription(String appAndroidDescription) {
        this.appAndroidDescription = appAndroidDescription;
    }
}
