package com.example.paul.studentbookandmore.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.ui.activity.delete.SelectDisciplineToDeleteView;
import com.example.paul.studentbookandmore.ui.activity.save.SaveDisciplineView;
import com.example.paul.studentbookandmore.ui.adapter.ViewPagerAdapter;
import com.example.paul.studentbookandmore.ui.helper.SlidingTabLayout;

import io.realm.Realm;

//import android.support.annotation.NonNull;
//import android.support.design.widget.BottomNavigationView;
//import android.support.v4.app.FragmentActivity;
//import android.view.MenuItem;
//import android.view.View;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"General","Discipline","Absențe"};
    int Numboftabs = 3;
    int bottomNavigationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing falalalala database
        Realm.init(this);
//        Discipline discipline = new Discipline("Română");
//        DisciplinesManager.getInstance().addDiscipline(discipline);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    //3 dots menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addDiscipline(MenuItem item) {
        Intent intent = new Intent(this, SaveDisciplineView.class);
        startActivity(intent);
    }

    public void deleteDiscipline(MenuItem item){
        Intent intent = new Intent(this, SelectDisciplineToDeleteView.class);
        intent.putExtra("TEZA",false);
        startActivity(intent);
    }

    public void addTeza(MenuItem item) {
        Intent intent = new Intent(this, SelectDisciplineToDeleteView.class);
        intent.putExtra("TEZA",true);
        startActivity(intent);
    }

}
