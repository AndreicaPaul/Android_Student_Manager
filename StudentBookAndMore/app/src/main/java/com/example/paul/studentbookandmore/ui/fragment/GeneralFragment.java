package com.example.paul.studentbookandmore.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.business_logic.GradesManager;

/**
 * Created by Paul on 19-Apr-17.
 */

public class GeneralFragment extends Fragment {
    TextView generalAvereage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_fragment, container, false);
        generalAvereage = (TextView) view.findViewById(R.id.general_average);

        generalAvereage.setText("Media generală este:" + "\n" + GradesManager.getInstance(getContext()).getGeneralAverage().toString());
        generalAvereage.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        generalAvereage.setTextSize(50);
        return view;
    }
}
