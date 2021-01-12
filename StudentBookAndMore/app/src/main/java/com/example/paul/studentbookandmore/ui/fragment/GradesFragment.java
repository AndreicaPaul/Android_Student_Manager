package com.example.paul.studentbookandmore.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.business_logic.GradesManager;
import com.example.paul.studentbookandmore.model.Grade;

/**
 * Created by Paul on 21-Apr-17.
 */

public class GradesFragment extends Fragment {

    ListView listView;
    private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grades_fragment, container, false);


        listView = (ListView) view.findViewById(R.id.list_view_grades_fragment);
        final ArrayAdapter<Grade> adapter = new ArrayAdapter<Grade>(GradesFragment.this.getContext(), android.R.layout.simple_list_item_1, GradesManager.getInstance().getGrades());
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Grade grade = (Grade) listView.getItemAtPosition(position);
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setTitle("Șterge nota")
                        .setMessage("Sunteți sigur că vreți să ștergeți nota?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                GradesManager.getInstance().deleteGrade(grade.getGradeValue(),grade.getCorrespondingDiscipline().getName());
//                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return false;
            }
        });
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer_forGrades);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                adapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.setAdapter(adapter);
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        return view;
    }
}
