package com.example.paul.androidstudentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editTextFirstName,editTextLastName,editTextAge;
    public final static String EXTRA_MESSAGE = "com.example.paul.androidstudentmanager";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.check_button);
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

    public void showStudent(View view) {
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        String firstName = String.valueOf(editTextFirstName.getText());
        String lastName = String.valueOf(editTextLastName.getText());
        String age = String.valueOf(editTextAge.getText());
        String yourData = firstName + ";" + lastName+ ";" + age;
        intent.putExtra(EXTRA_MESSAGE,yourData);
        startActivity(intent);
    }
}
