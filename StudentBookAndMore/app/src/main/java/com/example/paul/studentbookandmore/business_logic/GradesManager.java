package com.example.paul.studentbookandmore.business_logic;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.paul.studentbookandmore.model.DAO.GradeDao;
import com.example.paul.studentbookandmore.model.Discipline;
import com.example.paul.studentbookandmore.model.Grade;
import com.example.paul.studentbookandmore.model.database.AppDatabase;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Paul on 14-Mar-17.
 */

public class GradesManager {
    private final GradeDao mGradeDao;
    private final LiveData<List<Grade>> grades;
    private final Context context;
    private static GradesManager instance = null;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private GradesManager(Context application) {
        //Defeat instantiation
        AppDatabase db = AppDatabase.getDatabase(application);
        mGradeDao = db.gradeDao();
        grades = mGradeDao.getAllGrades();
        this.context = application;
    }
    public static GradesManager getInstance(Context context) {
        if(instance == null) {
            instance = new GradesManager(context);
        }
        return instance;
    }


    public void addGrade(int value, String disciplineName, boolean isThesis, Application application){
        final Grade grade = new Grade();
        grade.setGradeValue(value);
        grade.setThesis(isThesis);
        DisciplinesManager.getInstance(application).getDisciplineForName(disciplineName).observeForever(new Observer<List<Discipline>>() {
            @Override
            public void onChanged(List<Discipline> disciplines) {
                if(!disciplines.isEmpty())
                    grade.setCorrespondingDisciplineID(disciplines.get(0).getId());
                    new insertAsyncTask(mGradeDao).execute(grade);
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<Grade, Void, Void>{
        private final GradeDao mAsyncDao;
        insertAsyncTask(GradeDao dao){mAsyncDao = dao;}

        @Override
        protected Void doInBackground(final Grade... params){
            mAsyncDao.insert(params[0]);
            return null;
        }
    }

    public LiveData<List<Grade>> getGrades(){
        return this.grades;
    }

    public Float getGradesAverageForDiscipline(final Discipline discipline){
        final Float[] average = new Float[1];
        average[0] = Float.NaN;
        grades.observeForever(new Observer<List<Grade>>() {
            @Override
            public void onChanged(List<Grade> grades) {
                int sum = 0;
                int thesis = 0;
                int counter = 0;
                for(Grade grade : grades){
                    if(grade.getCorrespondingDisciplineID() == discipline.getId()){
                        if(grade.isThesis()){
                            thesis = grade.getGradeValue();
                        } else {
                            sum += grade.getGradeValue();
                            counter++;
                        }
                    }
                }
                if(thesis != 0){
                    average[0] = Float.valueOf(decimalFormat.format(((sum/ (float) counter)*3+thesis)/4));
                } else {
                    average[0] = Float.valueOf(decimalFormat.format(sum / (float) counter));
                }
            }
        });
       return average[0];
    }

    public String getGeneralAverage(){
        int sum = 0;
        int counter = 0;
        Float currentGrade;
        if(DisciplinesManager.getInstance(context).getDisciplines().getValue() != null) {
            for (Discipline discipline : DisciplinesManager.getInstance(context).getDisciplines().getValue()) {
                currentGrade = this.getGradesAverageForDiscipline(discipline);
                if (currentGrade > 0) {
                    sum += Math.round(currentGrade);
                    counter++;
                }
            }
            if (counter>0)
            return Float.valueOf(decimalFormat.format(sum / (float) counter)).toString();
        }
        return "Insuficiente note.";
    }

    public LiveData<List<Grade>> getAllGradesForDiscipline(Discipline discipline){
       return mGradeDao.getGradesForDiscipline(discipline.getId());
    }

    public void deleteGrade(Grade grade){
        new deleteAsyncTask(mGradeDao).execute(grade);
    }

    private static class deleteAsyncTask extends AsyncTask<Grade, Void, Void>{
        private GradeDao mAsyncDao;
        deleteAsyncTask(GradeDao dao){
            mAsyncDao = dao;
        }
        @Override
        protected Void doInBackground(final Grade... params){
            mAsyncDao.deleteGrade(params);
            return null;
        }
    }

}
