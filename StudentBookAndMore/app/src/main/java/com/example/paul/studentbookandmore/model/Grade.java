package com.example.paul.studentbookandmore.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Discipline.class,
parentColumns = "id",
childColumns = "correspondingDisciplineID",
onDelete = ForeignKey.CASCADE))
public class Grade {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int gradeValue;
    private int correspondingDisciplineID;
    private boolean isThesis;

    public Grade() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isThesis() {
        return isThesis;
    }

    public void setThesis(boolean thesis) {
        this.isThesis = thesis;
    }

    public Grade(int gradeValue, Discipline discipline, boolean isThesis) {
        this.gradeValue = gradeValue;
        this.correspondingDisciplineID = discipline.getId();
        this.isThesis = isThesis;
    }

    public int getCorrespondingDisciplineID() {
        return correspondingDisciplineID;
    }

    public void setCorrespondingDisciplineID(int correspondingDisciplineID) {
        this.correspondingDisciplineID = correspondingDisciplineID;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String toFileSave(){
        return gradeValue + ";" + correspondingDisciplineID + ";" + isThesis + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (Float.compare(grade.gradeValue, gradeValue) != 0) return false;
        return correspondingDisciplineID == grade.correspondingDisciplineID;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {

        return gradeValue + " " + correspondingDisciplineID + "\n";
    }

}
