package com.lpzoutreach.g12lpz.Models;

public class AppAssetModel {
    private int appAssetID=0;
    private String assetLogo="";
    private String assetName="";
    private String assetType="";
    private String assetDescription="";
    private int createdBy=0;
    private String dtCreated="";
    private String dtUpdated="";
    private int updatedBy=0;
    private String android_asset_logo_path="";

    public AppAssetModel(){}

    public int getAppAssetID() {
        return appAssetID;
    }

    public void setAppAssetID(int appAssetID) {
        this.appAssetID = appAssetID;
    }

    public String getAssetLogo() {
        return assetLogo;
    }

    public void setAssetLogo(String assetLogo) {
        this.assetLogo = assetLogo;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }

    public String getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(String dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getAndroid_asset_logo_path() {
        return android_asset_logo_path;
    }

    public void setAndroid_asset_logo_path(String android_asset_logo_path) {
        this.android_asset_logo_path = android_asset_logo_path;
    }
}
