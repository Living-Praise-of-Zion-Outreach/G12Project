package com.lpzoutreach.g12lpz.Models;

public class AuthModel {

    private String google_id="";
    private String google_name = "";
    private String google_email = "";
    private String google_family_name = "";
    private String google_given_name = "";
    private String google_photo_url = "";

    public AuthModel(){}

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public String getGoogle_name() {
        return google_name;
    }

    public void setGoogle_name(String google_name) {
        this.google_name = google_name;
    }

    public String getGoogle_email() {
        return google_email;
    }

    public void setGoogle_email(String google_email) {
        this.google_email = google_email;
    }

    public String getGoogle_family_name() {
        return google_family_name;
    }

    public void setGoogle_family_name(String google_family_name) {
        this.google_family_name = google_family_name;
    }

    public String getGoogle_given_name() {
        return google_given_name;
    }

    public void setGoogle_given_name(String google_given_name) {
        this.google_given_name = google_given_name;
    }

    public String getGoogle_photo_url() {
        return google_photo_url;
    }

    public void setGoogle_photo_url(String google_photo_url) {
        this.google_photo_url = google_photo_url;
    }
}
