package com.example.paul.androidstudentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String studentDirectData = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String[] studentData = studentDirectData.split(";");
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setText("First name: " + studentData[0] + "\n" + "Last name: " + studentData[1] + "\n" + "Age: " + studentData[2]);
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }

    public void returnToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
