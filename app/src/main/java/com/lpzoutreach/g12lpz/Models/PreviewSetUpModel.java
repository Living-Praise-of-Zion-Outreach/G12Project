package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class PreviewSetUpModel {

    private int churchID=0;
    private String church_photo="";
    private String church_name="";

    public String getChurch_address() {
        return church_address;
    }

    public void setChurch_address(String church_address) {
        this.church_address = church_address;
    }

    private String church_address="";

    private int userID_network_leader=0;
    private String name_network_leader="";
    private String photo_network_leader="";
    private String church_network_leader="";
    private String church_network_name_leader="";

    private int userID_invite=0;
    private String name_invite="";
    private String photo_invite="";
    private String church_invite="";
    private String network_name_invite="";

    elUtil eventListener;

    public elUtil getEventListener() {
        return eventListener;
    }

    public void setEventListener(elUtil eventListener) {
        this.eventListener = eventListener;
    }

    public int getChurchID() {
        return churchID;
    }

    public void setChurchID(int churchID) {
        this.churchID = churchID;
    }

    public String getChurch_photo() {
        return church_photo;
    }

    public void setChurch_photo(String church_photo) {
        this.church_photo = church_photo;
    }

    public String getChurch_name() {
        return church_name;
    }

    public void setChurch_name(String church_name) {
        this.church_name = church_name;
    }

    public int getUserID_network_leader() {
        return userID_network_leader;
    }

    public void setUserID_network_leader(int userID_network_leader) {
        this.userID_network_leader = userID_network_leader;
    }

    public String getName_network_leader() {
        return name_network_leader;
    }

    public void setName_network_leader(String name_network_leader) {
        this.name_network_leader = name_network_leader;
    }

    public String getPhoto_network_leader() {
        return photo_network_leader;
    }

    public void setPhoto_network_leader(String photo_network_leader) {
        this.photo_network_leader = photo_network_leader;
    }

    public String getChurch_network_leader() {
        return church_network_leader;
    }

    public void setChurch_network_leader(String church_network_leader) {
        this.church_network_leader = church_network_leader;
    }

    public String getChurch_network_name_leader() {
        return church_network_name_leader;
    }

    public void setChurch_network_name_leader(String chruch_network_name_leader) {
        this.church_network_name_leader = chruch_network_name_leader;
    }

    public int getUserID_invite() {
        return userID_invite;
    }

    public void setUserID_invite(int userID_invite) {
        this.userID_invite = userID_invite;
    }

    public String getName_invite() {
        return name_invite;
    }

    public void setName_invite(String name_invite) {
        this.name_invite = name_invite;
    }

    public String getPhoto_invite() {
        return photo_invite;
    }

    public void setPhoto_invite(String photo_invite) {
        this.photo_invite = photo_invite;
    }

    public String getChurch_invite() {
        return church_invite;
    }

    public void setChurch_invite(String church_invite) {
        this.church_invite = church_invite;
    }

    public String getNetwork_name_invite() {
        return network_name_invite;
    }

    public void setNetwork_name_invite(String network_name_invite) {
        this.network_name_invite = network_name_invite;
    }
}
