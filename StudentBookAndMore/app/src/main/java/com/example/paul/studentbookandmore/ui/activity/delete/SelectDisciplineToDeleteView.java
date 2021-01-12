package com.example.paul.studentbookandmore.ui.activity.delete;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paul.studentbookandmore.R;

public class SelectDisciplineToDeleteView extends AppCompatActivity {
    private SelectDisciplineToDeletePresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_discipline_to_delete_view);

        mPresenter = new SelectDisciplineToDeletePresenterImpl(this);
        final ListView listView = (ListView) findViewById(R.id.disciplines_to_delete_list);
        mPresenter.setListItems();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.ListItemSelected(position);
                finish();
            }
        });
    }
}
