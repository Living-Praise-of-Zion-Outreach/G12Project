package com.lpzoutreach.g12lpz.Models;

import android.widget.ImageView;
import android.widget.TextView;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class MembersG12NetworkModel {

    private elUtil eventListenerUtil;
    private int userID=0;
    private String member_logo="";
    private String first_name="";
    private String middle_name="";
    private String last_name="";
    private String organization_name="";
    private int primary_leaders=0;
    private int _144 = 0;
    private int _1789 = 0;

    private TextView tv_name_network_leaner;
    private ImageView iv_network_leader_logo_network;
    private TextView tv_organization_name_network;
    private ImageView iv_organization_logo_network;

    private TextView tv_count_members_network;

    private TextView tv_current_144_members_network6;

    private TextView tv_current_1789_members_network;

    private String actionType="";


    public MembersG12NetworkModel(){}

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

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public int getPrimary_leaders() {
        return primary_leaders;
    }

    public void setPrimary_leaders(int primary_leaders) {
        this.primary_leaders = primary_leaders;
    }

    public int get_144() {
        return _144;
    }

    public void set_144(int _144) {
        this._144 = _144;
    }

    public int get_1789() {
        return _1789;
    }

    public void set_1789(int _1789) {
        this._1789 = _1789;
    }

    public String getMember_logo() {
        return member_logo;
    }

    public void setMember_logo(String member_logo) {
        this.member_logo = member_logo;
    }

    public TextView getTv_name_network_leaner() {
        return tv_name_network_leaner;
    }

    public void setTv_name_network_leaner(TextView tv_name_network_leaner) {
        this.tv_name_network_leaner = tv_name_network_leaner;
    }

    public ImageView getIv_network_leader_logo_network() {
        return iv_network_leader_logo_network;
    }

    public void setIv_network_leader_logo_network(ImageView iv_network_leader_logo_network) {
        this.iv_network_leader_logo_network = iv_network_leader_logo_network;
    }

    public TextView getTv_organization_name_network() {
        return tv_organization_name_network;
    }

    public void setTv_organization_name_network(TextView tv_organization_name_network) {
        this.tv_organization_name_network = tv_organization_name_network;
    }

    public ImageView getIv_organization_logo_network() {
        return iv_organization_logo_network;
    }

    public void setIv_organization_logo_network(ImageView iv_organization_logo_network) {
        this.iv_organization_logo_network = iv_organization_logo_network;
    }

    public TextView getTv_count_members_network() {
        return tv_count_members_network;
    }

    public void setTv_count_members_network(TextView tv_count_members_network) {
        this.tv_count_members_network = tv_count_members_network;
    }

    public TextView getTv_current_144_members_network6() {
        return tv_current_144_members_network6;
    }

    public void setTv_current_144_members_network6(TextView tv_current_144_members_network6) {
        this.tv_current_144_members_network6 = tv_current_144_members_network6;
    }

    public TextView getTv_current_1789_members_network() {
        return tv_current_1789_members_network;
    }

    public void setTv_current_1789_members_network(TextView tv_current_1789_members_network) {
        this.tv_current_1789_members_network = tv_current_1789_members_network;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
