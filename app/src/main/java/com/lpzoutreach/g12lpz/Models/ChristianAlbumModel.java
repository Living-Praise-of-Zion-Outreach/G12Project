package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class ChristianAlbumModel {

    private String album_id,album_name,foreign_artist_name,foreign_artist_id,url_logo,year_published;
    private String song;
    private elUtil eventListenerUtil;

    public String getYear_published() {
        return year_published;
    }

    public void setYear_published(String year_published) {
        this.year_published = year_published;
    }

    public ChristianAlbumModel(){}

    public String getUrl_logo() {
        return url_logo;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public void setUrl_logo(String url_logo) {
        this.url_logo = url_logo;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getForeign_artist_name() {
        return foreign_artist_name;
    }

    public void setForeign_artist_name(String foreign_artist_name) {
        this.foreign_artist_name = foreign_artist_name;
    }

    public String getForeign_artist_id() {
        return foreign_artist_id;
    }

    public void setForeign_artist_id(String foreign_artist_id) {
        this.foreign_artist_id = foreign_artist_id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
