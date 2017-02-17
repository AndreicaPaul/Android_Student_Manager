package com.example.paul.androidstudentmanager.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.paul.androidstudentmanager.R;
import com.example.paul.androidstudentmanager.controller.Controller;
import com.example.paul.androidstudentmanager.repository.Repository;

public class DisplayStudentsActivity extends AppCompatActivity {
    public DisplayStudentsActivity() {
    }
    Controller controller = new Controller(Repository.getInstance());
@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_message);
    //ar trebui facute 3 liste separate pt nume, prenume, varsta
    ArrayAdapter adapter = new ArrayAdapter<>(DisplayStudentsActivity.this, R.layout.activity_listview, this.controller.getAllStudents());
    ListView listView = (ListView) findViewById(R.id.students_list);
    listView.setAdapter(adapter);
}

    public void returnToMainActivity(View view) {
        finish();
    }
}








