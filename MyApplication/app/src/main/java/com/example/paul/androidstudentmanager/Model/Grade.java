package com.example.paul.androidstudentmanager.model;
public class Grade extends Object implements FileSavingObject {
    private int value;
    private Discipline discipline;
    private Student student;

    public Grade(int value, Discipline discipline, Student student) {
        this.value = value;
        this.discipline = discipline;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public Discipline getDiscipline() {

        return discipline;
    }

    public int getValue() {

        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        if (value != grade.value) return false;
        if (discipline != null ? !discipline.equals(grade.discipline) : grade.discipline != null) return false;
        return student != null ? student.equals(grade.student) : grade.student == null;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student: " + student + "\n" + "Discipline: " + discipline.getName() + "\n" + "Grade: " + value + "\n" ;
    }

    @Override
    public String stringForFileWriting() {
        return this.value + ";" + this.discipline.stringForFileWriting() + ";" + this.student.stringForFileWriting();
    }
}
