package com.example.paul.studentbookandmore.model;

public class Grade {
    private int gradeValue;
    private Discipline correspondingDiscipline;
    private boolean isThesis;

    public Grade() {

    }

    public boolean isThesis() {
        return isThesis;
    }

    public void setThesis(boolean thesis) {
        this.isThesis = thesis;
    }

    public Grade(int gradeValue, Discipline discipline, boolean isThesis) {
        this.gradeValue = gradeValue;
        this.correspondingDiscipline = discipline;
        this.isThesis = isThesis;
    }

    public Discipline getCorrespondingDiscipline() {
        return correspondingDiscipline;
    }

    public void setCorrespondingDiscipline(Discipline correspondingDiscipline) {
        this.correspondingDiscipline = correspondingDiscipline;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String toFileSave(){
        return gradeValue + ";" + correspondingDiscipline + ";" + isThesis + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (Float.compare(grade.gradeValue, gradeValue) != 0) return false;
        return correspondingDiscipline.equals(grade.correspondingDiscipline);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {

        return gradeValue + " " + correspondingDiscipline + "\n";
    }

}
