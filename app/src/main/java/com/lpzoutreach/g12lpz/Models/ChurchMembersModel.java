package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class ChurchMembersModel {
    private int churchID;
    private String church_name;
    private int userID;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String profile_photo_path;

    private String address;
    private String barangay;
    private String city;
    private String province;

    private elUtil eventListenerUtil;

    private String church_position;

    public ChurchMembersModel(){}

    public int getChurchID() {
        return churchID;
    }

    public void setChurchID(int churchID) {
        this.churchID = churchID;
    }

    public String getChurch_name() {
        return church_name;
    }

    public void setChurch_name(String church_name) {
        this.church_name = church_name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getChurch_position() {
        return church_position;
    }

    public void setChurch_position(String church_position) {
        this.church_position = church_position;
    }
}
