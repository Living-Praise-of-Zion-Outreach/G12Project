package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;



public class PersonalInformationModel {
    elUtil handler;
    private int profileID=0;
    private int userID=0;
    private String cuserID="";
    private String about="";
    private String first_name="";
    private String middle_name="";
    private String last_name="";
    private String name_ext="";
    private String nickname="";
    private String date_of_birth="";
    private String place_of_birth="";
    private String sex="";
    private String civil_status="";
    private String occupation="";
    private String height="";
    private String height_metric="";
    private String weight="";
    private String weight_metric="";
    private String blood_type="";
    private int chu_churchID=0;
    private String chu_church_name="";
    private int chu_cellLeaderID=0;
    private String chu_cellLeader="";
    private int chu_roleID=0;
    private String chu_roleName="";
    private String loc_country="";
    private String loc_province="";
    private String loc_city="";
    private String loc_barangay="";
    private String loc_address="";
    private String loc_zipcode="";
    private String con_mobile_no="";
    private String con_tel_no="";
    private String educ_elem="";
    private String educ_elem_year_graduated="";
    private String educ_high_school="";
    private String educ_high_school_graduated="";
    private String educ_college="";
    private String educ_college_graduate="";
    private String educ_attainment="";
    private String educ_course="";
    private String soc_facebook_url="";
    private String soc_youtube_url="";
    private String soc_instagram_url="";
    private String soc_linkin_url="";
    private String soc_tiktok_url="";
    private String soc_twitter_url="";
    private String occ_name_of_employer="";
    private String occ_occupation="";
    private String occ_address="";
    private String privacy_settings="";
    private String dtUpdated="";
    private String profile_photo="";
    private String android_photo_path="";
    private String bitmap_photo="";
    private String chu_leader_name="";
    private String chu_invited_by="";

    private elUtil eventListenerUtil;


    public String getChu_leader_name() {
        return chu_leader_name;
    }

    public void setChu_leader_name(String chu_leader_name) {
        this.chu_leader_name = chu_leader_name;
    }

    public String getChu_invited_by() {
        return chu_invited_by;
    }

    public void setChu_invited_by(String chu_invited_by) {
        this.chu_invited_by = chu_invited_by;
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

    public String getNetwork_name() {
        return network_name;
    }

    public void setNetwork_name(String network_name) {
        this.network_name = network_name;
    }

    private String network_name="";

    private String Google_UID="";
    private String Facebook_UID="";

    private String email="";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogle_UID() {
        return Google_UID;
    }

    public void setGoogle_UID(String google_UID) {
        Google_UID = google_UID;
    }

    public String getFacebook_UID() {
        return Facebook_UID;
    }

    public void setFacebook_UID(String facebook_UID) {
        Facebook_UID = facebook_UID;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public elUtil getHandler() {
        return handler;
    }

    public void setHandler(elUtil handler) {
        this.handler = handler;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCuserID() {
        return cuserID;
    }

    public void setCuserID(String cuserID) {
        this.cuserID = cuserID;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public String getName_ext() {
        return name_ext;
    }

    public void setName_ext(String name_ext) {
        this.name_ext = name_ext;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight_metric() {
        return height_metric;
    }

    public void setHeight_metric(String height_metric) {
        this.height_metric = height_metric;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight_metric() {
        return weight_metric;
    }

    public void setWeight_metric(String weight_metric) {
        this.weight_metric = weight_metric;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public int getChu_churchID() {
        return chu_churchID;
    }

    public void setChu_churchID(int chu_churchID) {
        this.chu_churchID = chu_churchID;
    }

    public String getChu_church_name() {
        return chu_church_name;
    }

    public void setChu_church_name(String chu_church_name) {
        this.chu_church_name = chu_church_name;
    }

    public int getChu_cellLeaderID() {
        return chu_cellLeaderID;
    }

    public void setChu_cellLeaderID(int chu_cellLeaderID) {
        this.chu_cellLeaderID = chu_cellLeaderID;
    }

    public String getChu_cellLeader() {
        return chu_cellLeader;
    }

    public void setChu_cellLeader(String chu_cellLeader) {
        this.chu_cellLeader = chu_cellLeader;
    }

    public int getChu_roleID() {
        return chu_roleID;
    }

    public void setChu_roleID(int chu_roleID) {
        this.chu_roleID = chu_roleID;
    }

    public String getChu_roleName() {
        return chu_roleName;
    }

    public void setChu_roleName(String chu_roleName) {
        this.chu_roleName = chu_roleName;
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

    public String getLoc_zipcode() {
        return loc_zipcode;
    }

    public void setLoc_zipcode(String loc_zipcode) {
        this.loc_zipcode = loc_zipcode;
    }

    public String getCon_mobile_no() {
        return con_mobile_no;
    }

    public void setCon_mobile_no(String con_mobile_no) {
        this.con_mobile_no = con_mobile_no;
    }

    public String getCon_tel_no() {
        return con_tel_no;
    }

    public void setCon_tel_no(String con_tel_no) {
        this.con_tel_no = con_tel_no;
    }

    public String getEduc_elem() {
        return educ_elem;
    }

    public void setEduc_elem(String educ_elem) {
        this.educ_elem = educ_elem;
    }

    public String getEduc_elem_year_graduated() {
        return educ_elem_year_graduated;
    }

    public void setEduc_elem_year_graduated(String educ_elem_year_graduated) {
        this.educ_elem_year_graduated = educ_elem_year_graduated;
    }

    public String getEduc_high_school() {
        return educ_high_school;
    }

    public void setEduc_high_school(String educ_high_school) {
        this.educ_high_school = educ_high_school;
    }

    public String getEduc_high_school_graduated() {
        return educ_high_school_graduated;
    }

    public void setEduc_high_school_graduated(String educ_high_school_graduated) {
        this.educ_high_school_graduated = educ_high_school_graduated;
    }

    public String getEduc_college() {
        return educ_college;
    }

    public void setEduc_college(String educ_college) {
        this.educ_college = educ_college;
    }

    public String getEduc_college_graduate() {
        return educ_college_graduate;
    }

    public void setEduc_college_graduate(String educ_college_graduate) {
        this.educ_college_graduate = educ_college_graduate;
    }

    public String getEduc_attainment() {
        return educ_attainment;
    }

    public void setEduc_attainment(String educ_attainment) {
        this.educ_attainment = educ_attainment;
    }

    public String getEduc_course() {
        return educ_course;
    }

    public void setEduc_course(String educ_course) {
        this.educ_course = educ_course;
    }

    public String getSoc_facebook_url() {
        return soc_facebook_url;
    }

    public void setSoc_facebook_url(String soc_facebook_url) {
        this.soc_facebook_url = soc_facebook_url;
    }

    public String getSoc_youtube_url() {
        return soc_youtube_url;
    }

    public void setSoc_youtube_url(String soc_youtube_url) {
        this.soc_youtube_url = soc_youtube_url;
    }

    public String getSoc_instagram_url() {
        return soc_instagram_url;
    }

    public void setSoc_instagram_url(String soc_instagram_url) {
        this.soc_instagram_url = soc_instagram_url;
    }

    public String getSoc_linkin_url() {
        return soc_linkin_url;
    }

    public void setSoc_linkin_url(String soc_linkin_url) {
        this.soc_linkin_url = soc_linkin_url;
    }

    public String getSoc_tiktok_url() {
        return soc_tiktok_url;
    }

    public void setSoc_tiktok_url(String soc_tiktok_url) {
        this.soc_tiktok_url = soc_tiktok_url;
    }

    public String getSoc_twitter_url() {
        return soc_twitter_url;
    }

    public void setSoc_twitter_url(String soc_twitter_url) {
        this.soc_twitter_url = soc_twitter_url;
    }

    public String getOcc_name_of_employer() {
        return occ_name_of_employer;
    }

    public void setOcc_name_of_employer(String occ_name_of_employer) {
        this.occ_name_of_employer = occ_name_of_employer;
    }

    public String getOcc_occupation() {
        return occ_occupation;
    }

    public void setOcc_occupation(String occ_occupation) {
        this.occ_occupation = occ_occupation;
    }

    public String getOcc_address() {
        return occ_address;
    }

    public void setOcc_address(String occ_address) {
        this.occ_address = occ_address;
    }

    public String getPrivacy_settings() {
        return privacy_settings;
    }

    public void setPrivacy_settings(String privacy_settings) {
        this.privacy_settings = privacy_settings;
    }

    public String getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(String dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public PersonalInformationModel(){}


}
