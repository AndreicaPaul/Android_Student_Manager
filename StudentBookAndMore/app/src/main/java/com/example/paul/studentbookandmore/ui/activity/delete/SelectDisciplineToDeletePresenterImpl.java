package com.example.paul.studentbookandmore.ui.activity.delete;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LiveData;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.business_logic.DisciplinesManager;
import com.example.paul.studentbookandmore.model.Discipline;

import java.util.List;

/**
 * Created by Paul on 15-Sep-17 at 4:53 PM.
 */

public class SelectDisciplineToDeletePresenterImpl implements SelectDisciplineToDeletePresenter {

    private SelectDisciplineToDeleteView mView;
    private ListView listView;


    SelectDisciplineToDeletePresenterImpl(SelectDisciplineToDeleteView view) {
        mView = view;
        listView = (ListView) mView.findViewById(R.id.disciplines_to_delete_list);
    }

    @Override
    public void setListItems() {
        LiveData<List<Discipline>> disciplines = DisciplinesManager.getInstance(mView.getApplication()).getDisciplines();
        ArrayAdapter<Discipline> arrayAdapter = new ArrayAdapter<Discipline>(mView,android.R.layout.simple_list_item_1,disciplines.getValue());
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void ListItemSelected(int position) {
        Discipline discipline = (Discipline) listView.getItemAtPosition(position);
        DisciplinesManager.getInstance(mView.getApplication()).deleteDiscipline(discipline);
    }
}
