package com.example.paul.studentbookandmore.ui.activity.selectGradeDialog;

import android.app.Application;

import com.example.paul.studentbookandmore.business_logic.GradesManager;

/**
 * Created by Paul on 20-Sep-17 at 1:23 PM.
 */

public class CustomDialogImpl implements CustomDialog {
    private CustomDialogView mView;
    public CustomDialogImpl(CustomDialogView customDialogView) {
        mView = customDialogView;
    }

    @Override
    public void addGradeForDiscipline(int value,String disciplineName, boolean isThesis, Application application) {
        GradesManager.getInstance(application).addGrade(value,disciplineName,isThesis,application);
    }
}
