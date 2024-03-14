package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class LessonsModel {

    private String lesson_id="";

    private String content="";

    private String content_dark="";

    private String lessonLogo = "";
    private String lesson_number="";
    private String lesson_name="";
    private String lesson_subname="";
    private String lesson_type="";



    private String android_content="";
    private String android_content_dark="";

    private Boolean Is_file_downloaded=false;

    private String study_the_word;
    private String html_content;

    private String html_header;

    private elUtil eventListenerUtil;

    private String word_1, word_2,word_3,word_4,word_5,word_6,word_7;



    public LessonsModel(){}

    public elUtil getEventListenerUtil() {
        return eventListenerUtil;
    }

    public void setEventListenerUtil(elUtil eventListenerUtil) {
        this.eventListenerUtil = eventListenerUtil;
    }

    public String getLesson_number() {
        return lesson_number;
    }

    public void setLesson_number(String lesson_number) {
        this.lesson_number = lesson_number;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getLesson_subname() {
        return lesson_subname;
    }

    public void setLesson_subname(String lesson_subname) {
        this.lesson_subname = lesson_subname;
    }

    public String getAndroid_content() {
        return android_content;
    }

    public void setAndroid_content(String android_content) {
        this.android_content = android_content;
    }

    public String getAndroid_content_dark() {
        return android_content_dark;
    }

    public void setAndroid_content_dark(String android_content_dark) {
        this.android_content_dark = android_content_dark;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
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

    public String getLesson_type() {
        return lesson_type;
    }

    public void setLesson_type(String lesson_type) {
        this.lesson_type = lesson_type;
    }

    public String getLessonLogo() {
        return lessonLogo;
    }

    public void setLessonLogo(String lessonLogo) {
        this.lessonLogo = lessonLogo;
    }

    public Boolean getIs_file_downloaded() {
        return Is_file_downloaded;
    }

    public void setIs_file_downloaded(Boolean is_file_downloaded) {
        Is_file_downloaded = is_file_downloaded;
    }

    public String getStudy_the_word() {
        return study_the_word;
    }

    public void setStudy_the_word(String study_the_word) {
        this.study_the_word = study_the_word;
    }

    public String getHtml_content() {
        return html_content;
    }

    public void setHtml_content(String html_content) {
        this.html_content = html_content;
    }

    public String getWord_1() {
        return word_1;
    }

    public void setWord_1(String word_1) {
        this.word_1 = word_1;
    }

    public String getWord_2() {
        return word_2;
    }

    public void setWord_2(String word_2) {
        this.word_2 = word_2;
    }

    public String getWord_3() {
        return word_3;
    }

    public void setWord_3(String word_3) {
        this.word_3 = word_3;
    }

    public String getWord_4() {
        return word_4;
    }

    public void setWord_4(String word_4) {
        this.word_4 = word_4;
    }

    public String getWord_5() {
        return word_5;
    }

    public void setWord_5(String word_5) {
        this.word_5 = word_5;
    }

    public String getWord_6() {
        return word_6;
    }

    public void setWord_6(String word_6) {
        this.word_6 = word_6;
    }

    public String getWord_7() {
        return word_7;
    }

    public void setWord_7(String word_7) {
        this.word_7 = word_7;
    }

    public String getHtml_header() {
        return html_header;
    }

    public void setHtml_header(String html_header) {
        this.html_header = html_header;
    }
}
