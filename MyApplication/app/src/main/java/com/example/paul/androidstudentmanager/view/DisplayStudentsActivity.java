package com.example.paul.androidstudentmanager.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.paul.androidstudentmanager.R;
import com.example.paul.androidstudentmanager.controller.Controller;

public class DisplayStudentsActivity extends AppCompatActivity {
    public DisplayStudentsActivity() {
    }
//    Repository repository = new Repository();
//    Controller controller = new Controller(repository);             //am impresia ca e gresit aici ceva, dar nu imi dau seama ce
    Controller controller;                                            //eroarea care apare e ca stringul de studenti e gol, mereu, desi am verificat, iar in MainActivity apare ca fiind populat
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        ArrayAdapter adapter = new ArrayAdapter<>(DisplayStudentsActivity.this, R.layout.activity_listview,this.controller.getAllStudents());
        ListView listView = (ListView) findViewById(R.id.students_list);
        listView.setAdapter(adapter);
                                                                    //daca as face toate astea in MainActivity ar functiona si ar afisa toti studentii in lista, frumos
    }                                                               //am impresia ca stringul de studenti se goleste cumva atunci cand pornesc activitatea asta
    public void returnToMainActivity(View view) {
        finish();
    }
}








