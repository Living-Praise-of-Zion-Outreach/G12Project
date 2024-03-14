package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class HolyBibleModel {

    private int book_number;
    private String short_name;
    private String long_name;
    private String book_color;
    private int sorting_order;
    private int chapter;
    private int verse;
    private String text;

    private String bookVersion;

    private elUtil eventListenerUtil;

    private String actionEvent;

    private String shortBibleName;
    private String longBibleName;
    private String description;
    private String copyright;
    private String androidPath;
    private String bibleType;
    private String language;

    private String bibleUrl;

    private int selected_index;
    private boolean isSelected;
    private boolean isDownloaded;

    private boolean isIntroduction;

    private int holyBibleID;

    public HolyBibleModel(){}

    public int getBook_number() {
        return book_number;
    }

    public void setBook_number(int book_number) {
        this.book_number = book_number;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getBook_color() {
        return book_color;
    }

    public void setBook_color(String book_color) {
        this.book_color = book_color;
    }

    public int getSorting_order() {
        return sorting_order;
    }

    public void setSorting_order(int sorting_order) {
        this.sorting_order = sorting_order;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(String actionEvent) {
        this.actionEvent = actionEvent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getSelected_index() {
        return selected_index;
    }

    public void setSelected_index(int selected_index) {
        this.selected_index = selected_index;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    public String getShortBibleName() {
        return shortBibleName;
    }

    public void setShortBibleName(String shortBibleName) {
        this.shortBibleName = shortBibleName;
    }

    public String getLongBibleName() {
        return longBibleName;
    }

    public void setLongBibleName(String longBibleName) {
        this.longBibleName = longBibleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAndroidPath() {
        return androidPath;
    }

    public void setAndroidPath(String androidPath) {
        this.androidPath = androidPath;
    }

    public String getBibleType() {
        return bibleType;
    }

    public void setBibleType(String bibleType) {
        this.bibleType = bibleType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getHolyBibleID() {
        return holyBibleID;
    }

    public void setHolyBibleID(int holyBibleID) {
        this.holyBibleID = holyBibleID;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public boolean isIntroduction() {
        return isIntroduction;
    }

    public void setIntroduction(boolean introduction) {
        isIntroduction = introduction;
    }

    public String getBibleUrl() {
        return bibleUrl;
    }

    public void setBibleUrl(String bibleUrl) {
        this.bibleUrl = bibleUrl;
    }
}
