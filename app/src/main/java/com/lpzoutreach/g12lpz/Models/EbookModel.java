package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class EbookModel {

    private int ebookID;
    private String bookCoverUrl;
    private String bookName;
    private String bookTagLine;

    private String bookTag;
    private String bookShortDescription;
    private String bookCategory;
    private String bookUrlPath;
    private String bookAuthor;
    private String language;

    private String androidPhotoPath;

    private String bookGenre;
    private String dtCreated;
    private int createdBy;
    private elUtil eventListener;
    private String actionEvent;

    private String views;
    private String favorites;
    private String downloads;

    private String limit;

    private String authorLogo;

    private boolean contentData;

    private String info_header;
    private String info_body;
    private String info_url;

    private String info_url_type;
    private int add_to_favorite;
    private String orderType;

    public EbookModel(){}

    public int getEbookID() {
        return ebookID;
    }

    public void setEbookID(int ebookID) {
        this.ebookID = ebookID;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookTagLine() {
        return bookTagLine;
    }

    public void setBookTagLine(String bookTagLine) {
        this.bookTagLine = bookTagLine;
    }

    public String getBookShortDescription() {
        return bookShortDescription;
    }

    public void setBookShortDescription(String bookShortDescription) {
        this.bookShortDescription = bookShortDescription;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookUrlPath() {
        return bookUrlPath;
    }

    public void setBookUrlPath(String bookUrlPath) {
        this.bookUrlPath = bookUrlPath;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public elUtil getEventListener() {
        return eventListener;
    }

    public void setEventListener(elUtil eventListener) {
        this.eventListener = eventListener;
    }

    public String getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(String actionEvent) {
        this.actionEvent = actionEvent;
    }

    public String getBookTag() {
        return bookTag;
    }

    public void setBookTag(String bookTag) {
        this.bookTag = bookTag;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAndroidPhotoPath() {
        return androidPhotoPath;
    }

    public void setAndroidPhotoPath(String androidPhotoPath) {
        this.androidPhotoPath = androidPhotoPath;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public boolean isContentData() {
        return contentData;
    }

    public void setContentData(boolean contentData) {
        this.contentData = contentData;
    }

    public String getAuthorLogo() {
        return authorLogo;
    }

    public void setAuthorLogo(String authorLogo) {
        this.authorLogo = authorLogo;
    }

    public String getInfo_header() {
        return info_header;
    }

    public void setInfo_header(String info_header) {
        this.info_header = info_header;
    }

    public String getInfo_body() {
        return info_body;
    }

    public void setInfo_body(String info_body) {
        this.info_body = info_body;
    }

    public String getInfo_url() {
        return info_url;
    }

    public void setInfo_url(String info_url) {
        this.info_url = info_url;
    }

    public String getInfo_url_type() {
        return info_url_type;
    }

    public void setInfo_url_type(String info_url_type) {
        this.info_url_type = info_url_type;
    }

    public int getAdd_to_favorite() {
        return add_to_favorite;
    }

    public void setAdd_to_favorite(int add_to_favorite) {
        this.add_to_favorite = add_to_favorite;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
