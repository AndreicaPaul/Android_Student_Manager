package com.example.paul.studentbookandmore.ui.activity.save;

import android.widget.EditText;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.business_logic.DisciplinesManager;
import com.example.paul.studentbookandmore.model.Discipline;

/**
 * Created by Paul on 19-Jul-17.
 */

public class SaveDisciplinePresenterImpl implements SaveDisciplinePresenter {
    private SaveDisciplineView view;

    public SaveDisciplinePresenterImpl(SaveDisciplineView saveDisciplineView) {
        this.view = saveDisciplineView;
    }

    @Override
    public void addDiscipline() {
        EditText name = (EditText) view.findViewById(R.id.discipline_name);
        Discipline discipline = new Discipline(name.getText().toString());
        DisciplinesManager.getInstance().addDiscipline(discipline);
    }

}
