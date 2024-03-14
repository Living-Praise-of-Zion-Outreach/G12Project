package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class FamilyBackgroundModel {

    elUtil eventListenerUtil;
    int familyBackgroundID=0;
    int userID=0;
    String profile_photo_path="";
    String android_photo_path="";
    String photo_bitmap="";
    String relationship="";
    int rel_profileID=0;
    String rel_name="";
    int rel_age=0;
    String rel_birthdate="";
    String rel_occupation="";
    String rel_contact_no="";
    String rel_status="";
    String rel_condition="";
    String dtCreated="";

    public FamilyBackgroundModel(){}

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public int getFamilyBackgroundID() {
        return familyBackgroundID;
    }

    public void setFamilyBackgroundID(int familyBackgroundID) {
        this.familyBackgroundID = familyBackgroundID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getPhoto_bitmap() {
        return photo_bitmap;
    }

    public void setPhoto_bitmap(String photo_bitmap) {
        this.photo_bitmap = photo_bitmap;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public int getRel_profileID() {
        return rel_profileID;
    }

    public void setRel_profileID(int rel_profileID) {
        this.rel_profileID = rel_profileID;
    }

    public String getRel_name() {
        return rel_name;
    }

    public void setRel_name(String rel_name) {
        this.rel_name = rel_name;
    }

    public int getRel_age() {
        return rel_age;
    }

    public void setRel_age(int rel_age) {
        this.rel_age = rel_age;
    }

    public String getRel_birthdate() {
        return rel_birthdate;
    }

    public void setRel_birthdate(String rel_birthdate) {
        this.rel_birthdate = rel_birthdate;
    }

    public String getRel_occupation() {
        return rel_occupation;
    }

    public void setRel_occupation(String rel_occupation) {
        this.rel_occupation = rel_occupation;
    }

    public String getRel_contact_no() {
        return rel_contact_no;
    }

    public void setRel_contact_no(String rel_contact_no) {
        this.rel_contact_no = rel_contact_no;
    }

    public String getRel_status() {
        return rel_status;
    }

    public void setRel_status(String rel_status) {
        this.rel_status = rel_status;
    }

    public String getRel_condition() {
        return rel_condition;
    }

    public void setRel_condition(String rel_condition) {
        this.rel_condition = rel_condition;
    }

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }
}
