package com.example.paul.studentbookandmore.ui.activity.selectGradeDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.model.Discipline;
import com.example.paul.studentbookandmore.model.Grade;

/**
 * Created by Paul on 05-Jun-17.
 */

public class CustomDialogView extends Dialog{
    private Context context;
    private Boolean isThesis;
    private CustomDialogImpl mCustomDialogImpl;
    private NumberPicker spinner;

    public CustomDialogView(@NonNull Context context, Boolean thesis) {
        super(context);
        this.context = context;
        this.isThesis = thesis;
        mCustomDialogImpl = new CustomDialogImpl(this);
    }

    public void showDialog(Activity activity, final Discipline discipline){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.add_grade_dialog);
        final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.is_thesis_check_box);
        spinner = (NumberPicker) dialog.findViewById(R.id.grade_picker_1);
        spinner.setMaxValue(10);
        spinner.setMinValue(1);

        Button add = (Button) dialog.findViewById(R.id.add_grade_button_2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Grade grade = new Grade(spinner.getValue(),discipline, false);
                if(checkBox.isChecked()) {
                    grade.setThesis(true);
                }
                mCustomDialogImpl.addGradeForDiscipline(grade);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
