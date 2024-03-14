package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class MusicModel {

    private int artistID;
    private String artistPhoto;
    private String artistName;
    private String artistBiography;
    private String artistUrlSite;

    private String artistType;
    private String artistDtCreated;
    private String artistCreatedBy;

    private int albumID;
    private String albumPhoto;
    private String albumName;
    private String albumYearReleased;
    private String albumDescription;
    private String albumDtCreated;
    private String albumCreatedBy;

    private int songID;
    private String songTitle;
    private String songType;
    private String content;
    private String content_dark;
    private String mp3Path;
    private String officialMusic;
    private String tutorialGuitar;
    private String order;
    private String dtCreated;
    private int publishedBy;

    private String views;
    private String download;
    private String favorite;

    private String actionEvent;
    private elUtil eventListenerUtil;

    private String video_Category;
    private String video_youtubeID;
    private String video_youtubeThumbnails;
    private String video_youtubeTitle;
    private String video_youtubeChannel;
    private String video_dtCreated;

    public MusicModel(){}

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getArtistPhoto() {
        return artistPhoto;
    }

    public void setArtistPhoto(String artistPhoto) {
        this.artistPhoto = artistPhoto;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistBiography() {
        return artistBiography;
    }

    public void setArtistBiography(String artistBiography) {
        this.artistBiography = artistBiography;
    }

    public String getArtistUrlSite() {
        return artistUrlSite;
    }

    public void setArtistUrlSite(String artistUrlSite) {
        this.artistUrlSite = artistUrlSite;
    }

    public String getArtistDtCreated() {
        return artistDtCreated;
    }

    public void setArtistDtCreated(String artistDtCreated) {
        this.artistDtCreated = artistDtCreated;
    }

    public String getArtistCreatedBy() {
        return artistCreatedBy;
    }

    public void setArtistCreatedBy(String artistCreatedBy) {
        this.artistCreatedBy = artistCreatedBy;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumPhoto() {
        return albumPhoto;
    }

    public void setAlbumPhoto(String albumPhoto) {
        this.albumPhoto = albumPhoto;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumYearReleased() {
        return albumYearReleased;
    }

    public void setAlbumYearReleased(String albumYearReleased) {
        this.albumYearReleased = albumYearReleased;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getAlbumDtCreated() {
        return albumDtCreated;
    }

    public void setAlbumDtCreated(String albumDtCreated) {
        this.albumDtCreated = albumDtCreated;
    }

    public String getAlbumCreatedBy() {
        return albumCreatedBy;
    }

    public void setAlbumCreatedBy(String albumCreatedBy) {
        this.albumCreatedBy = albumCreatedBy;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongType() {
        return songType;
    }

    public void setSongType(String songType) {
        this.songType = songType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_dark() {
        return content_dark;
    }

    public void setContent_dark(String content_dark) {
        this.content_dark = content_dark;
    }

    public String getMp3Path() {
        return mp3Path;
    }

    public void setMp3Path(String mp3Path) {
        this.mp3Path = mp3Path;
    }

    public String getOfficialMusic() {
        return officialMusic;
    }

    public void setOfficialMusic(String officialMusic) {
        this.officialMusic = officialMusic;
    }

    public String getTutorialGuitar() {
        return tutorialGuitar;
    }

    public void setTutorialGuitar(String tutorialGuitar) {
        this.tutorialGuitar = tutorialGuitar;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }

    public int getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(int publishedBy) {
        this.publishedBy = publishedBy;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getArtistType() {
        return artistType;
    }

    public void setArtistType(String artistType) {
        this.artistType = artistType;
    }

    public String getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(String actionEvent) {
        this.actionEvent = actionEvent;
    }

    public String getVideo_Category() {
        return video_Category;
    }

    public void setVideo_Category(String video_Category) {
        this.video_Category = video_Category;
    }

    public String getVideo_youtubeID() {
        return video_youtubeID;
    }

    public void setVideo_youtubeID(String video_youtubeID) {
        this.video_youtubeID = video_youtubeID;
    }

    public String getVideo_youtubeThumbnails() {
        return video_youtubeThumbnails;
    }

    public void setVideo_youtubeThumbnails(String video_youtubeThumbnails) {
        this.video_youtubeThumbnails = video_youtubeThumbnails;
    }

    public String getVideo_youtubeTitle() {
        return video_youtubeTitle;
    }

    public void setVideo_youtubeTitle(String video_youtubeTitle) {
        this.video_youtubeTitle = video_youtubeTitle;
    }

    public String getVideo_youtubeChannel() {
        return video_youtubeChannel;
    }

    public void setVideo_youtubeChannel(String video_youtubeChannel) {
        this.video_youtubeChannel = video_youtubeChannel;
    }

    public String getVideo_dtCreated() {
        return video_dtCreated;
    }

    public void setVideo_dtCreated(String video_dtCreated) {
        this.video_dtCreated = video_dtCreated;
    }
}
