package com.example.paul.androidstudentmanager.controller;
import com.example.paul.androidstudentmanager.model.Discipline;
import com.example.paul.androidstudentmanager.model.Grade;
import com.example.paul.androidstudentmanager.model.StringContainsNumbersException;
import com.example.paul.androidstudentmanager.model.Student;
import com.example.paul.androidstudentmanager.model.Teacher;
import com.example.paul.androidstudentmanager.repository.Repository;

import java.util.ArrayList;
public class Controller
{
    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) throws StringContainsNumbersException, NumberFormatException {
        Validator.validateStudent(student);
        this.repository.addStudent(student);
    }

    public ArrayList<Student> getAllStudents(){
        return this.repository.getStudents();
    }

    public void addDiscipline(Discipline discipline) throws StringContainsNumbersException{
        Validator.validateDiscipline(discipline);
        this.repository.addDiscipline(discipline);
    }

    public ArrayList<Discipline> getAllDisciplines(){
        return this.repository.getDisciplines();
    }

    public void sortByFirstName(){
        this.repository.sortByFirstName();
    }

    public void removeStudentFormPosition(int pos){
        this.repository.removeStudentFromPosition(pos);
    }

    public void removeDisciplineFromPosition(int pos){
        this.repository.removeDisciplineFromPosition(pos);
    }

    public void giveGrade (Grade grade) {
        this.repository.giveGrade(grade);
    }

    public Student getStudentFromPosition(int posStud){
        return this.repository.getStudentFromPosition(posStud);
    }

    public Discipline getDisciplineFromPosition(int posDisc){ return this.repository.getDisciplineFromPosition(posDisc); }

    public ArrayList<Grade> getAllGrades() { return this.repository.getGrades(); }

    public void removeGradeFromPosition(int pos){
        this.repository.removeGradeFromPosition(pos);
    }

    public void addTeacher (Teacher teacher) throws StringContainsNumbersException, NumberFormatException{
        Validator.validateTeacher(teacher);
        this.repository.addTeacher(teacher);
    }

    public ArrayList<Teacher> getTeachers(){
        return this.repository.getTeachers();
    }

    public void removeTeacher (int posT){
        this.repository.removeTeacher(posT);
    }

}
