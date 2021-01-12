package com.example.paul.studentbookandmore.ui.activity.deleteChangeGradeDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.model.Discipline;

public class DeleteChangeGradeDialogView extends Dialog{

    Context mContext;
    DeleteChangeGradeDialog mDeleteChangeGradeDialog;

    public DeleteChangeGradeDialogView(@NonNull Context context) {
        super(context);
        this.mContext = context;
        mDeleteChangeGradeDialog = new DeleteChangeGradeDialogImpl(this);
    }

    public void showDialog(Activity activity, final Discipline discipline){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.delete_change_grade_dialog);
        ListView listView = (ListView) findViewById(R.id.allGradesListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        dialog.show();
    }

}
