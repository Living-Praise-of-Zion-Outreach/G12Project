package com.lpzoutreach.g12lpz.Utility.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.lpzoutreach.g12lpz.Models.StudentRecordTracker;
import com.lpzoutreach.g12lpz.Utility.file.sharedData;
import com.lpzoutreach.g12lpz.Utility.sql.local.openHelperLifeClass;

public class JS {

    private Context context;

    public JS(Context context){
        this.context = context;
    }

    @JavascriptInterface
    public void getData(String LessonGuideLeaderID, String NetworkLeaderID, String EnrollmentType,  String book_name, String lesson_no, String ans_1, String ans_2, String ans_3, String ans_4, String ans_5, String ans_6, String ans_7, String ans_8, String ans_9, String study){
        StudentRecordTracker data = new StudentRecordTracker();
        data.setUserID(sharedData.get_userID(context));
        data.setBook_name(book_name);
        data.setLessonNo(Integer.parseInt(lesson_no));
        data.setLessonGuideLeaderID(Integer.parseInt(LessonGuideLeaderID));
        data.setNetworkLeaderID(Integer.parseInt(NetworkLeaderID));
        data.setEnrollmentType(EnrollmentType);
        data.setAnswer_1(ans_1);
        data.setAnswer_2(ans_2);
        data.setAnswer_3(ans_3);
        data.setAnswer_4(ans_4);
        data.setAnswer_5(ans_5);
        data.setAnswer_6(ans_6);
        data.setAnswer_7(ans_7);
        data.setAnswer_8(ans_8);
        data.setAnswer_9(ans_9);
        data.setStudy(study);
        data.setOrder(0);
        data.setDtCreated("");
        data.setCreatedBy(0);
        openHelperLifeClass helper = new openHelperLifeClass(context);
        helper.insert_student_record_tracker(data);
        Toast.makeText(context,"You have successfully updated an answers!",Toast.LENGTH_LONG).show();
    }

}
