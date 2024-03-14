package com.lpzoutreach.g12lpz.Models;

public class UsersModel {

    private int userID=0;
    private String userCode="";
    private String userLevel="";
    private String username="";
    private String password="";
    private String email_address="";
    private String profile_photo_path="";
    private String android_photo_path="";
    private String bitmap_photo="";

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }

    public String getAndroid_photo_path() {
        return android_photo_path;
    }

    public void setAndroid_photo_path(String android_photo_path) {
        this.android_photo_path = android_photo_path;
    }

    public String getBitmap_photo() {
        return bitmap_photo;
    }

    public void setBitmap_photo(String bitmap_photo) {
        this.bitmap_photo = bitmap_photo;
    }
}
