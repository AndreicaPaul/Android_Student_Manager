package com.example.paul.studentbookandmore.ui.adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.paul.studentbookandmore.R;
import com.example.paul.studentbookandmore.business_logic.GradesManager;
import com.example.paul.studentbookandmore.model.Discipline;
import com.example.paul.studentbookandmore.model.Grade;
import com.example.paul.studentbookandmore.ui.activity.selectGradeDialog.CustomDialogView;
import com.ramotion.foldingcell.FoldingCell;

import java.util.HashSet;
import java.util.List;

/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
public class FoldingCellListAdapter extends ArrayAdapter<Discipline> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener defaultRequestBtnClickListener;
    private Context mContext;
    private Application application;

    public FoldingCellListAdapter(Context context, List<Discipline> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.application = application;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        Discipline discipline = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        final ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.tezaDetails = (RelativeLayout) cell.findViewById(R.id.teza_details);
            viewHolder.disciplineName = (TextView) cell.findViewById(R.id.discipline_name_in_cell_list);
            viewHolder.addGradeButton = (TextView) cell.findViewById(R.id.add_grade_button_in_custom_adapter);
            viewHolder.average = (TextView) cell.findViewById(R.id.average_text_in_list_view);
            viewHolder.discipline = (TextView) cell.findViewById(R.id.discipline_text_in_list_view);
            viewHolder.disciplineGrades = (TextView) cell.findViewById(R.id.grades_in_cell_content);
            viewHolder.teza = (TextView) cell.findViewById(R.id.teza_grade);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        // bind data from selected element to view through view holder

        assert discipline != null;
        viewHolder.disciplineName.setText(discipline.getName());

        int thesisValue = 0;
        StringBuilder disciplineGrades = new StringBuilder();
        disciplineGrades.append(" ");
        int i = 0;
        for(Grade grade : GradesManager.getInstance(application).getAllGradesForDiscipline(discipline)) {
            if(grade.isThesis()){
                thesisValue = grade.getGradeValue();
            } else {
                if (i == 0) {
                    disciplineGrades.append(grade.getGradeValue());
                    i++;
                } else {
                    disciplineGrades.append("," + " ").append(grade.getGradeValue());
                }
            }
        }

        //check if we have a thesis
        if(thesisValue != 0){
            viewHolder.teza.setText(Integer.toString(thesisValue));
        } else{
            viewHolder.tezaDetails.setVisibility(View.GONE);
        }

        viewHolder.disciplineGrades.setText(disciplineGrades);

        viewHolder.discipline.setText(discipline.getName());

        String average = GradesManager.getInstance(application).getGradesAverageForDiscipline(discipline).toString();
        if (Float.parseFloat(average) > 0) {
            viewHolder.average.setText(average);
        }
        else{
            viewHolder.average.setText("-");
        }

//        // set custom btn handler for list item from that item
//        if (discipline.getRequestBtnClickListener() != null) {
//            viewHolder.addGradeButton.setOnClickListener(discipline.getRequestBtnClickListener());
//        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.addGradeButton.setOnClickListener(defaultRequestBtnClickListener);

        viewHolder.addGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogView customDialogView = new CustomDialogView(getContext(),false);
                customDialogView.showDialog((Activity) mContext, viewHolder.discipline.getText().toString());
            }
        });

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    private void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    private void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return defaultRequestBtnClickListener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.defaultRequestBtnClickListener = defaultRequestBtnClickListener;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView disciplineName;
        TextView discipline;
        TextView disciplineGrades;
        TextView average;
        TextView teza;
        RelativeLayout tezaDetails;
        TextView addGradeButton;
    }
}
