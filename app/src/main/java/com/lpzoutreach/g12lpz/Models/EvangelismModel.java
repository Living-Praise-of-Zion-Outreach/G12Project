package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class EvangelismModel {

    private elUtil eventListenerUtil;
    private int tempProfileID=0;

    private int evangelizeID=0;

    private int userID=0;
    private String profile_photo_path="";

    private String photo_bitmap="";

    private String android_photo_path="";
    private String first_name="";
    private String middle_name="";
    private String last_name="";
    private String date_of_birth="";
    private String sex="";
    private String civil_status="";
    private String loc_country="";
    private String loc_province="";

    private boolean isGraduated=false;

    public String getPhotoBitmap() {
        return photo_bitmap;
    }

    public void setPhotoBitmap(String photo_bitmap) {
        this.photo_bitmap = photo_bitmap;
    }

    public String getAndroidPhotoPath() {
        return android_photo_path;
    }

    public void setAndroidPhotoPath(String android_photo_path) {
        this.android_photo_path = android_photo_path;
    }

    public boolean isGraduated() {
        return isGraduated;
    }

    public void setGraduated(boolean graduated) {
        isGraduated = graduated;
    }

    private String loc_city="";
    private String loc_barangay="";
    private String loc_address="";
    private String con_mobile_no="";
    private String email="";
    private String evangelizeType="";

    private int isEvangelize=0;
    private String evangelizeDt="";
    private int isDrop=0;
    private String dropDt="";
    private int isConsolidate=0;
    private String consolidateDt="";

    private int delFlag=0;
    private int actionFlag=0;
    private int createdBy=0;
    private String dtCreated="";
    private int updatedBy=0;
    private String dtUpdated="";

    public EvangelismModel(){}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getIsEvangelize() {
        return isEvangelize;
    }

    public void setIsEvangelize(int isEvangelize) {
        this.isEvangelize = isEvangelize;
    }

    public String getEvangelizeDt() {
        return evangelizeDt;
    }

    public void setEvangelizeDt(String evangelizeDt) {
        this.evangelizeDt = evangelizeDt;
    }

    public int getIsDrop() {
        return isDrop;
    }

    public void setIsDrop(int isDrop) {
        this.isDrop = isDrop;
    }

    public String getDropDt() {
        return dropDt;
    }

    public void setDropDt(String dropDt) {
        this.dropDt = dropDt;
    }

    public int getIsConsolidate() {
        return isConsolidate;
    }

    public void setIsConsolidate(int isConsolidate) {
        this.isConsolidate = isConsolidate;
    }

    public String getConsolidateDt() {
        return consolidateDt;
    }

    public void setConsolidateDt(String consolidateDt) {
        this.consolidateDt = consolidateDt;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public int getTempProfileID() {
        return tempProfileID;
    }

    public void setTempProfileID(int tempProfileID) {
        this.tempProfileID = tempProfileID;
    }

    public String getProfilePhotoPath() {
        return profile_photo_path;
    }

    public void setProfilePhotoPath(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
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

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCivil_status() {
        return civil_status;
    }

    public void setCivil_status(String civil_status) {
        this.civil_status = civil_status;
    }

    public String getLoc_country() {
        return loc_country;
    }

    public void setLoc_country(String loc_country) {
        this.loc_country = loc_country;
    }

    public String getLoc_province() {
        return loc_province;
    }

    public void setLoc_province(String loc_province) {
        this.loc_province = loc_province;
    }

    public String getLoc_city() {
        return loc_city;
    }

    public void setLoc_city(String loc_city) {
        this.loc_city = loc_city;
    }

    public String getLoc_barangay() {
        return loc_barangay;
    }

    public void setLoc_barangay(String loc_barangay) {
        this.loc_barangay = loc_barangay;
    }

    public String getLoc_address() {
        return loc_address;
    }

    public void setLoc_address(String loc_address) {
        this.loc_address = loc_address;
    }

    public String getCon_mobile_no() {
        return con_mobile_no;
    }

    public void setCon_mobile_no(String con_mobile_no) {
        this.con_mobile_no = con_mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEvangelizeType() {
        return evangelizeType;
    }

    public void setEvangelizeType(String evangelizeType) {
        this.evangelizeType = evangelizeType;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(int actionFlag) {
        this.actionFlag = actionFlag;
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

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(String dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public int getEvangelizeID() {
        return evangelizeID;
    }

    public void setEvangelizeID(int evangelizeID) {
        this.evangelizeID = evangelizeID;
    }

    public String getProfile_photo_path() {
        return profile_photo_path;
    }

    public void setProfile_photo_path(String profile_photo_path) {
        this.profile_photo_path = profile_photo_path;
    }

    public String getPhoto_bitmap() {
        return photo_bitmap;
    }

    public void setPhoto_bitmap(String photo_bitmap) {
        this.photo_bitmap = photo_bitmap;
    }

    public String getAndroid_photo_path() {
        return android_photo_path;
    }

    public void setAndroid_photo_path(String android_photo_path) {
        this.android_photo_path = android_photo_path;
    }
}
