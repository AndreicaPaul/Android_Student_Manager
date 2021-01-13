package com.example.paul.studentbookandmore.ui.activity.save;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paul.studentbookandmore.R;

public class SaveDisciplineView extends AppCompatActivity {
    private SaveDisciplinePresenterImpl saveDisciplinePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discipline_view);
        this.saveDisciplinePresenter = new SaveDisciplinePresenterImpl(this);
    }

    public void addDiscipline(View view){
        this.saveDisciplinePresenter.addDiscipline(getApplication());
        finish();
    }
}
