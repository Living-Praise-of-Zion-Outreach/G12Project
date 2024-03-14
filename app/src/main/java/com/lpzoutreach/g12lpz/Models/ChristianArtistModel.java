package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class ChristianArtistModel {
    private String artist_id, biography, filter_mode, name_of_artist, url_logo, url_site;
    private elUtil eventListenerUtil;

    public ChristianArtistModel(){}

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getFilter_mode() {
        return filter_mode;
    }

    public void setFilter_mode(String filter_mode) {
        this.filter_mode = filter_mode;
    }

    public String getName_of_artist() {
        return name_of_artist;
    }

    public void setName_of_artist(String name_of_artist) {
        this.name_of_artist = name_of_artist;
    }

    public String getUrl_logo() {
        return url_logo;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public String getUrl_site() {
        return url_site;
    }

    public void setUrl_site(String url_site) {
        this.url_site = url_site;
    }
}
