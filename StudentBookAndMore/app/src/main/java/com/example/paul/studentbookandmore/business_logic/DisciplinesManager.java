package com.example.paul.studentbookandmore.business_logic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.paul.studentbookandmore.model.DAO.DisciplineDao;
import com.example.paul.studentbookandmore.model.Discipline;
import com.example.paul.studentbookandmore.model.database.AppDatabase;

import java.util.List;

/**
 * Created by Paul on 10-Mar-17.
 */

public class DisciplinesManager {
    private final DisciplineDao mDisciplineDao;
    private LiveData<List<Discipline>> disciplines;
    private Context context;
//    private static String TAG = "DisciplinesManager";
    private static DisciplinesManager instance = null;
//    private static String disciplineFileSavePath = Environment.getExternalStorageDirectory().getPath();
//    private static String folderName = "StudentBookAndMore";
//    private static String disciplineFileSaveName = "Disciplines_File.txt";

    private DisciplinesManager(Context application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDisciplineDao = db.disciplineDao();
        disciplines = mDisciplineDao.getAllDisciplines();
        this.context = application;
    }

    public static DisciplinesManager getInstance(Context application) {
        if(instance == null) {
            instance = new DisciplinesManager(application);
        }
        return instance;
    }


    public void addDiscipline(Discipline discipline){
        new insertAsyncTask(mDisciplineDao).execute(discipline);
    }

    public LiveData<List<Discipline>> getDisciplines(){
        return disciplines;
    }

    public void deleteDiscipline(Discipline disciplineName){
        new deleteAsyncTask(mDisciplineDao).execute(disciplineName);
        GradesManager.getInstance(context).removeAllGradesForDiscipline(disciplineName);
    }
    public LiveData<List<Discipline>> getDisciplineForName(String disciplineName){
        return  mDisciplineDao.findDiscipline(disciplineName);
    }

    private static class deleteAsyncTask extends AsyncTask<Discipline, Void, Void>{
        private DisciplineDao mAsyncDao;
        deleteAsyncTask(DisciplineDao dao){
            mAsyncDao = dao;
        }
        @Override
        protected Void doInBackground(final Discipline... params){
            mAsyncDao.deleteDiscipline(params);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Discipline, Void, Void>{
        private DisciplineDao mAsyncTaskDao;
        insertAsyncTask(DisciplineDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Discipline... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
