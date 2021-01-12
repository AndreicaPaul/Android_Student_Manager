package com.example.paul.studentbookandmore.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.business_logic.DisciplinesManager;
import com.example.paul.studentbookandmore.model.Discipline;
import com.example.paul.studentbookandmore.ui.adapter.FoldingCellListAdapter;
import com.ramotion.foldingcell.FoldingCell;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/**
 * Created by Paul on 21-Apr-17.
 */

public class DisciplinesFragment extends Fragment {
    ListView listView;
    FoldingCellListAdapter adapter;
    private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.disciplines_fragment, container, false);


        listView = (ListView) view.findViewById(R.id.list_view);
        final ArrayList<Discipline> disciplines = DisciplinesManager.getInstance().getDisciplines();

        // create custom adapter that holds elements and their state (we need hold a id's of unfolded elements for reusable elements)
        adapter = new FoldingCellListAdapter(this.getActivity(), disciplines);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
        return view;
    }
}
