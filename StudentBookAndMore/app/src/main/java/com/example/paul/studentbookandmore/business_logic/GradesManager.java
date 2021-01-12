package com.example.paul.studentbookandmore.business_logic;

import android.os.Environment;
import android.util.Log;
import com.example.paul.studentbookandmore.model.Discipline;
import com.example.paul.studentbookandmore.model.Grade;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Paul on 14-Mar-17.
 */

public class GradesManager {
    private static String TAG = "GradesManager";
    private ArrayList<Grade> grades = new ArrayList<>();
    private static GradesManager instance = null;
    private static String disciplineFileSavePath = Environment.getExternalStorageDirectory().getPath();
    private static String folderName = "StudentBookAndMore";
    private static String gradeFileSaveName = "Grades_File.txt";
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    protected GradesManager() {
        //Defeat instantiation
        this.loadGradesFromFile();
    }
    public static GradesManager getInstance() {
        if(instance == null) {
            instance = new GradesManager();
        }
        return instance;
    }

    private void loadGradesFromFile(){
        try {
            File file = new File(disciplineFileSavePath + File.separator + folderName, gradeFileSaveName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while (line != null && line.length() > 0) {
                String[] lineComponents = line.split(";");
                Integer gradeValue = Integer.parseInt(lineComponents[0]);
                Discipline discipline = new Discipline(lineComponents[1]);
                boolean isThesis = Boolean.parseBoolean(lineComponents[2]);
                Grade grade = new Grade(gradeValue,discipline,isThesis);

                this.grades.add(grade);
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            Log.e(TAG, "loadGradesFromFile: something went wrong", e);
        }
    }

    private void saveToFile(){

        try {
            File folder = new File(disciplineFileSavePath,folderName);
            if(!folder.exists()){
                folder.mkdirs();
            }
            File file = new File(folder, gradeFileSaveName);
            FileOutputStream fileOutput = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutput);

            for(Grade grade : this.grades) {
                outputStreamWriter.append(grade.toFileSave());
            }
            outputStreamWriter.close();
            fileOutput.close();
        } catch (IOException e) {
            Log.e(TAG, "saveToFile: something went wrong", e);
        }

    }

    public void addGrade(Grade grade){
        this.grades.add(grade);
        this.saveToFile();
    }

    public ArrayList<Grade> getGrades(){
        return this.grades;
    }

    public Float getGradesAverageForDiscipline(Discipline discipline){
        int sum = 0;
        int thesis = 0;
        int counter = 0;
        for(Grade grade : this.grades){
            if(grade.getCorrespondingDiscipline().equals(discipline)){
                if(grade.isThesis()){
                    thesis = grade.getGradeValue();
                } else {
                    sum += grade.getGradeValue();
                    counter++;
                }
            }
        }
        if(thesis != 0){
            return Float.valueOf(decimalFormat.format(((sum/Float.valueOf(counter))*3+thesis)/4));
        } else {
            return Float.valueOf(decimalFormat.format(sum / Float.valueOf(counter)));
        }
    }

    public Float getGeneralAverage(){
        int sum = 0;
        int counter = 0;
        Float currentGrade;
        for(Discipline discipline : DisciplinesManager.getInstance().getDisciplines()){
            currentGrade = this.getGradesAverageForDiscipline(discipline);
            if(currentGrade > 0){
                sum += Math.round(currentGrade);
                counter++;
            }
        }
        return Float.valueOf(decimalFormat.format(sum / Float.valueOf(counter)));
    }

    public ArrayList<Grade> getAllGradesForDiscipline(Discipline discipline){
        ArrayList<Grade> grades1 = new ArrayList<>();
        for(Grade grade : this.grades){
            if(grade.getCorrespondingDiscipline().equals(discipline)){
                grades1.add(grade);
            }
        }
        return grades1;
    }

    public void deleteGrade(int value, String corespondingDiscipline){
        Grade gradeToDelete = null;
        for(Grade grade : this.getGrades()){
            if(grade.getGradeValue() == value && grade.getCorrespondingDiscipline().getName().equals(corespondingDiscipline)){
                gradeToDelete = grade;
            }
        }
        this.grades.remove(gradeToDelete);
        this.saveToFile();
    }

    void removeAllGradesForDiscipline(Discipline discipline){
        ArrayList<Grade> gradesToDelete = new ArrayList<Grade>();
        for(Grade grade : this.getGrades()){
            if(grade.getCorrespondingDiscipline().getName().equals(discipline.getName())){
                gradesToDelete.add(grade);
            }
        }
        if(gradesToDelete != null) {
            this.grades.removeAll(gradesToDelete);
            this.saveToFile();
        }
    }
}
