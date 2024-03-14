package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class BookModel {
    private int bookID=0;
    private String book_logo="";
    private String book_name="";
    private String book_description="";
    private String book_language="";
    private String dtCreated="";
    private int lessonsID=0;
    private String lessonNo="";
    private String lessonType;

    private String lessonsLogo = "";
    private String lessonTitle="";
    private String lessonSubtitle="";
    private String content="";
    private String createdBy="";

    private String actionEvent;

    private elUtil eventListenerUtil;

    private int book_total_chapters=0;

    private boolean is_file_downloaded=false;

    private String content_dark="";

    private String android_content_path="";

    private String android_content_path_dark="";

    private String android_folder="";

    private String book_classification="";

    private String html_content;
    private String study_the_word;

    private String html_header;

    public String getContent_dark() {
        return content_dark;
    }

    public void setContent_dark(String content_dark) {
        this.content_dark = content_dark;
    }

    public String getAndroid_content_path() {
        return android_content_path;
    }

    public void setAndroid_content_path(String android_content_path) {
        this.android_content_path = android_content_path;
    }

    public String getAndroid_content_path_dark() {
        return android_content_path_dark;
    }

    public void setAndroid_content_path_dark(String android_content_path_dark) {
        this.android_content_path_dark = android_content_path_dark;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBook_logo() {
        return book_logo;
    }

    public void setBook_logo(String book_logo) {
        this.book_logo = book_logo;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public String getBook_language() {
        return book_language;
    }

    public void setBook_language(String book_language) {
        this.book_language = book_language;
    }

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }

    public int getLessonsID() {
        return lessonsID;
    }

    public void setLessonsID(int lessonsID) {
        this.lessonsID = lessonsID;
    }

    public String getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(String lessonNo) {
        this.lessonNo = lessonNo;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonSubtitle() {
        return lessonSubtitle;
    }

    public void setLessonSubtitle(String lessonSubtitle) {
        this.lessonSubtitle = lessonSubtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public int getBook_total_chapters() {
        return book_total_chapters;
    }

    public void setBook_total_chapters(int book_total_chapters) {
        this.book_total_chapters = book_total_chapters;
    }

    public boolean isIs_file_downloaded() {
        return is_file_downloaded;
    }

    public void setIs_file_downloaded(boolean is_file_downloaded) {
        this.is_file_downloaded = is_file_downloaded;
    }

    public String getAndroid_folder() {
        return android_folder;
    }

    public void setAndroid_folder(String android_folder) {
        this.android_folder = android_folder;
    }

    public String getBook_classification() {
        return book_classification;
    }

    public void setBook_classification(String book_classification) {
        this.book_classification = book_classification;
    }

    public String getLessonsLogo() {
        return lessonsLogo;
    }

    public void setLessonsLogo(String lessonsLogo) {
        this.lessonsLogo = lessonsLogo;
    }

    public String getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(String actionEvent) {
        this.actionEvent = actionEvent;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getHtml_content() {
        return html_content;
    }

    public void setHtml_content(String html_content) {
        this.html_content = html_content;
    }

    public String getStudy_the_word() {
        return study_the_word;
    }

    public void setStudy_the_word(String study_the_word) {
        this.study_the_word = study_the_word;
    }

    public String getHtml_header() {
        return html_header;
    }

    public void setHtml_header(String html_header) {
        this.html_header = html_header;
    }
}
