package com.lpzoutreach.g12lpz.Models;

import android.widget.TextView;

public class ObjectModel {

    TextView chu_about;
    TextView chu_churchParent;
    TextView chu_address;
    TextView chu_mobile_no;
    TextView chu_email;

    public ObjectModel(){}

    public TextView getChu_churchParent() {
        return chu_churchParent;
    }

    public void setChu_churchParent(TextView chu_churchParent) {
        this.chu_churchParent = chu_churchParent;
    }

    public TextView getChu_address() {
        return chu_address;
    }

    public void setChu_address(TextView chu_address) {
        this.chu_address = chu_address;
    }

    public TextView getChu_mobile_no() {
        return chu_mobile_no;
    }

    public void setChu_mobile_no(TextView chu_mobile_no) {
        this.chu_mobile_no = chu_mobile_no;
    }

    public TextView getChu_email() {
        return chu_email;
    }

    public void setChu_email(TextView chu_email) {
        this.chu_email = chu_email;
    }

    public TextView getChu_about() {
        return chu_about;
    }

    public void setChu_about(TextView chu_about) {
        this.chu_about = chu_about;
    }
}
