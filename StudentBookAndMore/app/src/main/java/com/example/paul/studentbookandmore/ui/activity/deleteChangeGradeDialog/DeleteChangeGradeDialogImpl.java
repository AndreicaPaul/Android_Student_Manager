package com.example.paul.studentbookandmore.ui.activity.deleteChangeGradeDialog;

import com.example.paul.studentbookandmore.business_logic.GradesManager;
import com.example.paul.studentbookandmore.model.Grade;
import com.example.paul.studentbookandmore.ui.activity.selectGradeDialog.CustomDialogView;

/**
 * Created by Paul on 20-Sep-17 at 1:23 PM.
 */

public class DeleteChangeGradeDialogImpl implements DeleteChangeGradeDialog {
    private DeleteChangeGradeDialogView mView;

    public DeleteChangeGradeDialogImpl(DeleteChangeGradeDialogView deleteChangeGradeDialogView) {
        mView = deleteChangeGradeDialogView;
    }

}
