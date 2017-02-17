package com.example.paul.androidstudentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paul.androidstudentmanager.controller.Controller;
import com.example.paul.androidstudentmanager.model.StringContainsNumbersException;
import com.example.paul.androidstudentmanager.model.Student;
import com.example.paul.androidstudentmanager.repository.Repository;
import com.example.paul.androidstudentmanager.view.DisplayStudentsActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFirstName,editTextLastName,editTextAge;
    Controller controller = new Controller(Repository.getInstance());
    public static final String EXTRA_MESSAGE = "mesage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextFirstName = (EditText) findViewById(R.id.first_name_input);
        editTextLastName = (EditText) findViewById(R.id.last_name_input);
        editTextAge = (EditText) findViewById(R.id.age_input);
    }
    public void checkStudent(View view){
        String firstName = String.valueOf(editTextFirstName.getText());
        String lastName = String.valueOf(editTextLastName.getText());
        String age = String.valueOf(editTextAge.getText());
        String yourData = "Your name is: " + firstName + " " + lastName + ", and you are " + age + " years old.";
        Toast.makeText(this,yourData,Toast.LENGTH_LONG).show();
    }

    public void saveStudent(View view) {
        String firstName = String.valueOf(editTextFirstName.getText());
        String lastName = String.valueOf(editTextLastName.getText());
        int age = Integer.parseInt(String.valueOf(editTextAge.getText()));
        Student student = new Student(firstName,lastName,age);
        try {
            this.controller.addStudent(student);
        } catch (StringContainsNumbersException e) {
            e.printStackTrace();
        }//Salvarea simpla(fara fisier) functioneaza perfect
        for (Student student1 : this.controller.getAllStudents()){
            Toast.makeText(this,student1.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void showStudents(View view){
        Intent intent = new Intent(this, DisplayStudentsActivity.class);
        startActivity(intent);
    }
}
