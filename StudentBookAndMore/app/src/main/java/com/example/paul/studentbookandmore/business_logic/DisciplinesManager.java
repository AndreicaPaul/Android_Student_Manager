package com.example.paul.studentbookandmore.business_logic;

import android.os.Environment;
import android.util.Log;

import com.example.paul.studentbookandmore.model.Discipline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Paul on 10-Mar-17.
 */

public class DisciplinesManager {
    private static String TAG = "DisciplinesManager";
    private ArrayList<Discipline> disciplines = new ArrayList<>();
    private static DisciplinesManager instance = null;
    private static String disciplineFileSavePath = Environment.getExternalStorageDirectory().getPath();
    private static String folderName = "StudentBookAndMore";
    private static String disciplineFileSaveName = "Disciplines_File.txt";

    protected DisciplinesManager() {
        this.loadDisciplinesFromFile();
    }

    public static DisciplinesManager getInstance() {
        if(instance == null) {
            instance = new DisciplinesManager();
        }
        return instance;
    }

    private void loadDisciplinesFromFile(){
        try {
            File file = new File(disciplineFileSavePath + File.separator + folderName, disciplineFileSaveName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null && line.length() > 0) {
                String[] lineComponents = line.split(";");
                String disciplineName = lineComponents[0];
                Discipline discipline = new Discipline(disciplineName);

                this.disciplines.add(discipline);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            Log.e(TAG, "loadDisciplinesFromFile: something went wrong", e);
        }
    }

    private void saveToFile(){
        try {
            File folder = new File(disciplineFileSavePath,folderName);
            //Check if folder exists and created it if it doesn't
            if(!folder.exists()){
                folder.mkdirs();
            }
            File file = new File(folder, disciplineFileSaveName);
            FileOutputStream fileOutput = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutput);

            for(Discipline discipline : this.disciplines) {
                outputStreamWriter.append(discipline.toFileSave());
            }
            outputStreamWriter.close();
            fileOutput.close();
        } catch (IOException e) {
            Log.e(TAG, "saveToFile: something went wrong", e);
        }
    }

    public void addDiscipline(Discipline discipline){

        this.disciplines.add(discipline);
        this.saveToFile();
    }

    public ArrayList<Discipline> getDisciplines(){
        return disciplines;
    }

    public void deleteDiscipline(Discipline disciplineName){
        this.disciplines.remove(disciplineName);
        GradesManager.getInstance().removeAllGradesForDiscipline(disciplineName);
        this.saveToFile();
    }
    public Discipline getDisciplineForName(String disciplineName){
        Discipline discipline1 = null;
        for (Discipline discipline : this.getDisciplines()){
            if(discipline.getName().equals(disciplineName)) {
                discipline1 = discipline;
            }
        }
        return discipline1;
    }
}
