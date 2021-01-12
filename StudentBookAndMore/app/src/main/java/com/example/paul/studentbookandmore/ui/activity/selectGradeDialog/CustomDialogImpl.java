package com.example.paul.studentbookandmore.ui.activity.selectGradeDialog;

import com.example.paul.studentbookandmore.business_logic.GradesManager;
import com.example.paul.studentbookandmore.model.Grade;

/**
 * Created by Paul on 20-Sep-17 at 1:23 PM.
 */

public class CustomDialogImpl implements CustomDialog {
    private CustomDialogView mView;
    public CustomDialogImpl(CustomDialogView customDialogView) {
        mView = customDialogView;
    }

    @Override
    public void addGradeForDiscipline(Grade grade) {
        GradesManager.getInstance().addGrade(grade);
    }
}
