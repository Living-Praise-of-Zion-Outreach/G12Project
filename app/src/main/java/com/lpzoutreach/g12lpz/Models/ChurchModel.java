package com.lpzoutreach.g12lpz.Models;

import android.widget.ListView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class ChurchModel {

    public static final String MOTHER_CHURCH_SEARCH_PICK_TYPE = "Mother-Church";
    public static final String FOUNDER_CHURCH_SEARCH_PICK_TYPE = "Founder-Church";



    private elUtil eventListenerUtil;
    private ConstraintLayout notify_empty;
    private ListView searchPickerListView;
    int CID,user_ID,main_church_CID,person_founded_user_ID, image_ID, churchID;
    String church_logo_bitmap, church_cover_photo_bitmap, church_logo_local_source, church_cover_photo_local_source,url_logo, url_cover_photo;
    String church_code,church_name, house_block_lot_no, street, subdivision_village, barangay, municipality_city, province, zip_code, region, country, date_anniversary_celebration,
            date_founded, search_privacy, searchPickType,total_members;

    private String about, church_parent, address, email, mobile_no;


    public ConstraintLayout getNotify_Empty() {
        return notify_empty;
    }

    public void setNotify_Empty(ConstraintLayout notify_empty) {
        this.notify_empty = notify_empty;
    }

    public ChurchModel(){}

    public String getUrl_logo() {
        return url_logo;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public String getUrl_cover_photo() {
        return url_cover_photo;
    }

    public void setUrl_cover_photo(String url_cover_photo) {
        this.url_cover_photo = url_cover_photo;
    }

    public ListView getSearchPickerListView() {
        return searchPickerListView;
    }

    public void setSearchPickerListView(ListView searchPickerListView) {
        this.searchPickerListView = searchPickerListView;
    }

    public String getTotal_members() {
        return total_members;
    }

    public String getSearchPickType() {
        return searchPickType;
    }

    public void setSearchPickType(String searchPickType) {
        this.searchPickType = searchPickType;
    }

    public void setTotal_members(String total_members) {
        this.total_members = total_members;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getChurch_logo_bitmap() {
        return church_logo_bitmap;
    }

    public void setChurch_logo_bitmap(String church_logo_bitmap) {
        this.church_logo_bitmap = church_logo_bitmap;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getChurch_cover_photo_bitmap() {
        return church_cover_photo_bitmap;
    }

    public void setChurch_cover_photo_bitmap(String church_cover_photo_bitmap) {
        this.church_cover_photo_bitmap = church_cover_photo_bitmap;
    }

    public String getChurch_logo_local_source() {
        return church_logo_local_source;
    }

    public void setChurch_logo_local_source(String church_logo_local_source) {
        this.church_logo_local_source = church_logo_local_source;
    }

    public String getChurch_cover_photo_local_source() {
        return church_cover_photo_local_source;
    }

    public void setChurch_cover_photo_local_source(String church_cover_photo_local_source) {
        this.church_cover_photo_local_source = church_cover_photo_local_source;
    }

    public int getImage_ID() {
        return image_ID;
    }

    public void setImage_ID(int image_ID) {
        this.image_ID = image_ID;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public int getMain_church_CID() {
        return main_church_CID;
    }

    public void setMain_church_CID(int main_church_CID) {
        this.main_church_CID = main_church_CID;
    }

    public int getPerson_founded_user_ID() {
        return person_founded_user_ID;
    }

    public void setPerson_founded_user_ID(int person_founded_user_ID) {
        this.person_founded_user_ID = person_founded_user_ID;
    }

    public String getChurch_code() {
        return church_code;
    }

    public void setChurch_code(String church_code) {
        this.church_code = church_code;
    }

    public String getChurch_name() {
        return church_name;
    }

    public void setChurch_name(String church_name) {
        this.church_name = church_name;
    }

    public String getHouse_block_lot_no() {
        return house_block_lot_no;
    }

    public void setHouse_block_lot_no(String house_block_lot_no) {
        this.house_block_lot_no = house_block_lot_no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSubdivision_village() {
        return subdivision_village;
    }

    public void setSubdivision_village(String subdivision_village) {
        this.subdivision_village = subdivision_village;
    }

    public String getChurch_parent() {
        return church_parent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setChurch_parent(String church_parent) {
        this.church_parent = church_parent;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getMunicipality_city() {
        return municipality_city;
    }

    public void setMunicipality_city(String municipality_city) {
        this.municipality_city = municipality_city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate_anniversary_celebration() {
        return date_anniversary_celebration;
    }

    public void setDate_anniversary_celebration(String date_anniversary_celebration) {
        this.date_anniversary_celebration = date_anniversary_celebration;
    }

    public String getDate_founded() {
        return date_founded;
    }

    public void setDate_founded(String date_founded) {
        this.date_founded = date_founded;
    }

    public String getSearch_privacy() {
        return search_privacy;
    }

    public void setSearch_privacy(String search_privacy) {
        this.search_privacy = search_privacy;
    }

    public int getChurchID() {
        return churchID;
    }

    public void setChurchID(int churchID) {
        this.churchID = churchID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }
}
