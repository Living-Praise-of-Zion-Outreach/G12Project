package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class MnemonicVerseModel {

    private elUtil eventListenerUtil;
    private int minemonicVerseID;
    private int groupVerseID;
    private String verseTitle;
    private String verseReference;
    private String verse;
    private String tag;
    private String bibleVersion;

    private String bibleVersionText;
    private String language;
    private String dtCreated;

    private String publishedBy;

    public MnemonicVerseModel(){}

    public int getMinemonicVerseID() {
        return minemonicVerseID;
    }

    public void setMinemonicVerseID(int minemonicVerseID) {
        this.minemonicVerseID = minemonicVerseID;
    }

    public int getGroupVerseID() {
        return groupVerseID;
    }

    public void setGroupVerseID(int groupVerseID) {
        this.groupVerseID = groupVerseID;
    }

    public String getVerseTitle() {
        return verseTitle;
    }

    public void setVerseTitle(String verseTitle) {
        this.verseTitle = verseTitle;
    }

    public String getVerseReference() {
        return verseReference;
    }

    public void setVerseReference(String verseReference) {
        this.verseReference = verseReference;
    }

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBibleVersion() {
        return bibleVersion;
    }

    public void setBibleVersion(String bibleVersion) {
        this.bibleVersion = bibleVersion;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getBibleVersionText() {
        return bibleVersionText;
    }

    public void setBibleVersionText(String bibleVersionText) {
        this.bibleVersionText = bibleVersionText;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }
}
