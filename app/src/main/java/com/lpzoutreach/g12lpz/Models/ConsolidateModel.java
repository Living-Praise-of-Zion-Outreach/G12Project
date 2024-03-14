package com.lpzoutreach.g12lpz.Models;

public class ConsolidateModel {

    private int consolidateID=0;
    private int linkedUserID=0;
    private int temp_profileID=0;
    private int userID=0;
    private String book_name="";
    private int lessonNo=0;
    private String lessonTitle="";
    private String dtTrain="";

    public ConsolidateModel(){}

    public int getConsolidateID() {
        return consolidateID;
    }

    public void setConsolidateID(int consolidateID) {
        this.consolidateID = consolidateID;
    }

    public int getTemp_profileID() {
        return temp_profileID;
    }

    public void setTemp_profileID(int temp_profileID) {
        this.temp_profileID = temp_profileID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getDtTrain() {
        return dtTrain;
    }

    public void setDtTrain(String dtTrain) {
        this.dtTrain = dtTrain;
    }

    public int getLinkedUserID() {
        return linkedUserID;
    }

    public void setLinkedUserID(int linkedUserID) {
        this.linkedUserID = linkedUserID;
    }
}
