package com.lpzoutreach.g12lpz.Models;

import com.lpzoutreach.g12lpz.EventListener.elUtil;

public class SlidesModel {

    private int pptID;
    private String ppt_logo;
    private String ppt_name;
    private String ppt_description;
    private String ppt_total_lessons;
    private String ppt_language;
    private String dtCreated;
    private int createdBy;
    private int ppt_lessonID;
    private String ppt_lesson_logo;
    private String ppt_lesson_no;
    private String ppt_lesson_name;
    private String ppt_lesson_description;
    private String ppt_url;

    private elUtil eventListenerUtil;
    private String actionEvent;

    public SlidesModel(){}

    public int getPptID() {
        return pptID;
    }

    public void setPptID(int pptID) {
        this.pptID = pptID;
    }

    public String getPpt_logo() {
        return ppt_logo;
    }

    public void setPpt_logo(String ppt_logo) {
        this.ppt_logo = ppt_logo;
    }

    public String getPpt_name() {
        return ppt_name;
    }

    public void setPpt_name(String ppt_name) {
        this.ppt_name = ppt_name;
    }

    public String getPpt_description() {
        return ppt_description;
    }

    public void setPpt_description(String ppt_description) {
        this.ppt_description = ppt_description;
    }

    public String getPpt_total_lessons() {
        return ppt_total_lessons;
    }

    public void setPpt_total_lessons(String ppt_total_lessons) {
        this.ppt_total_lessons = ppt_total_lessons;
    }

    public String getPpt_language() {
        return ppt_language;
    }

    public void setPpt_language(String ppt_language) {
        this.ppt_language = ppt_language;
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

    public int getPpt_lessonID() {
        return ppt_lessonID;
    }

    public void setPpt_lessonID(int ppt_lessonID) {
        this.ppt_lessonID = ppt_lessonID;
    }

    public String getPpt_lesson_logo() {
        return ppt_lesson_logo;
    }

    public void setPpt_lesson_logo(String ppt_lesson_logo) {
        this.ppt_lesson_logo = ppt_lesson_logo;
    }

    public String getPpt_lesson_no() {
        return ppt_lesson_no;
    }

    public void setPpt_lesson_no(String ppt_lesson_no) {
        this.ppt_lesson_no = ppt_lesson_no;
    }

    public String getPpt_lesson_name() {
        return ppt_lesson_name;
    }

    public void setPpt_lesson_name(String ppt_lesson_name) {
        this.ppt_lesson_name = ppt_lesson_name;
    }

    public String getPpt_url() {
        return ppt_url;
    }

    public void setPpt_url(String ppt_url) {
        this.ppt_url = ppt_url;
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

    public String getPpt_lesson_description() {
        return ppt_lesson_description;
    }

    public void setPpt_lesson_description(String ppt_lesson_description) {
        this.ppt_lesson_description = ppt_lesson_description;
    }
}
