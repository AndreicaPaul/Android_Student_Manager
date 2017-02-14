package com.example.paul.androidstudentmanager.view;


import com.example.paul.androidstudentmanager.controller.Controller;
import com.example.paul.androidstudentmanager.controller.Validator;
import com.example.paul.androidstudentmanager.model.Discipline;
import com.example.paul.androidstudentmanager.model.Grade;
import com.example.paul.androidstudentmanager.model.StringContainsNumbersException;
import com.example.paul.androidstudentmanager.model.Student;
import com.example.paul.androidstudentmanager.model.Teacher;

import java.util.Scanner;
public class Menu
{
    private Controller controller;
    private Scanner scanner = new Scanner(System.in);
    public Menu(Controller controller){
        this.controller = controller;
    }

    public void run(){
        while(true)
        {
            this.showMenu();
        }
    }

    private String getUserInput(String inputMessage) {
        System.out.println(inputMessage);
        String name = null;
        try {
            name = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred!");
        }
        return name;
    }

    private void showStudentSubMenus(){
        System.out.println("    1. Add Student");
        System.out.println("    2. View all Students");
        System.out.println("    3. Remove Student");
        System.out.println("    4. Return to main menu");
        String userInput;
        userInput = scanner.nextLine();
        switch (userInput){
            case "0":
            {
                scanner.close();
                break;
            }
            case "1":
            {
                String firstName = getUserInput("Input first name: ");
                String lastName = getUserInput("Input last name: ");
                try {
                    int age = Integer.parseInt(getUserInput("Input age: "));
                    Student student = new Student(firstName,lastName,age);
                    this.controller.addStudent(student);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number! Nothing saved.");
                } catch (StringContainsNumbersException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "2" : {
                this.controller.sortByFirstName();
                for(Student student : this.controller.getAllStudents()) {
                    System.out.println(student.toString());
                }
                break;
            }
            case "3" : {
                this.controller.sortByFirstName();
                for(Student student : this.controller.getAllStudents()) {
                    System.out.println(student.toString());
                }
                try {
                    this.controller.removeStudentFormPosition(Integer.parseInt(getUserInput("Input position.")));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds, please recheck the number of students!");
                } catch (Exception e) {
                    System.out.println("We didn't saw that coming! :)");
                }
                break;
            }
            case "4" : {
                this.showMenu();
                break;
            }
            default : {
                System.out.println("Please input a valid number!(a number between 1 and 4, to close the app please input 0)");
                break;
            }
        }
    }

    private void showDisciplineSubMenus(){
        System.out.println("    1. Add Discipline");
        System.out.println("    2. View all Disciplines");
        System.out.println("    3. Remove Discipline");
        System.out.println("    4. Return to main menu");
        String userInput;
        userInput = scanner.nextLine();
        switch (userInput){
            case "0" : {
                scanner.close();
                break;
            }
            case "1" : {
                String disciplineName = getUserInput("Input discipline name: ");
                Discipline discipline = new Discipline(disciplineName);
                try {
                    this.controller.addDiscipline(discipline);
                } catch (StringContainsNumbersException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "2" : {
                for (Discipline discipline : this.controller.getAllDisciplines()){
                    System.out.println(discipline.toString());
                }
                break;
            }
            case "3" : {
                for (Discipline discipline : this.controller.getAllDisciplines()){
                    System.out.println(discipline.toString());
                }
                try {
                    this.controller.removeDisciplineFromPosition(Integer.parseInt(getUserInput("Input position: ")));
                } catch (IndexOutOfBoundsException e){
                    System.out.println("Index out of bounds.");
                } catch (Exception e) {
                    System.out.println("We didn't saw that coming! :)");
                }
                break;
            }
            case "4" : {
                this.showMenu();
                break;
            }
            default : {
                System.out.println("Please input a valid number!(a number between 1 and 4, to close the app please input 0)");
                break;
            }
        }
    }

    private void showGradeSubMenus(){
        System.out.println("    1. Give a Student a Grade");
        System.out.println("    2. View all Grades");
        System.out.println("    3. Remove Grade");
        System.out.println("    4. Return to main menu");
        String userInput;
        userInput = scanner.nextLine();
        switch (userInput){
            case "0" : {
                scanner.close();
                break;
            }
            case "1" : {
                for (Discipline discipline : this.controller.getAllDisciplines()){
                    System.out.println(discipline.toString());
                }
                int posDisc = Integer.parseInt(getUserInput("Input discipline position: "));
                for(Student student : this.controller.getAllStudents()) {
                    System.out.println(student.toString());
                }
                int posStud = Integer.parseInt(getUserInput("Input student position: "));
                try {
                    int gradeValue = Integer.parseInt(getUserInput("Input grade value: "));
                    Grade grade = new Grade(gradeValue, this.controller.getDisciplineFromPosition(posDisc), this.controller.getStudentFromPosition(posStud));
                    this.controller.giveGrade(grade);
                } catch (NumberFormatException e) {
                    System.out.println("Please input a number! Nothing changed.");
                    break;
                } catch (Exception e){
                    System.out.println("An unexpected error occurred.");
                }
                break;
            }
            case "2" : {
                for(Grade grade : this.controller.getAllGrades()){
                    System.out.println(grade.toString());
                }
                break;
            }
            case "3" : {
                for(Grade grade : this.controller.getAllGrades()){
                    System.out.println(grade.toString());
                }
                try {
                    this.controller.removeGradeFromPosition(Integer.parseInt(getUserInput("Input position: ")));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds, please recheck the number of grades!");
                } catch (Exception e){
                    System.out.println("An unexpected error occurred.");
                }
                break;
            }
            case "4" : {
                this.showMenu();
                break;
            }
            default : {
                System.out.println("Please input a valid number!(a number between 1 and 4, to close the app please input 0)");
            }
        }
    }

    private void showTeacherSubMenus(){
        System.out.println("    1. Add Teacher");
        System.out.println("    2. View Teachers");
        System.out.println("    3. Remove Teacher");
        System.out.println("    4. Return to main menu");
        String userInput;
        userInput = scanner.nextLine();
        switch (userInput)
        {
            case "0" : {
                scanner.close();
                break;
            }
            case "1" : {
                String name = getUserInput("Please Input Teacher Name: ");
                try {
                    int age = Integer.parseInt(getUserInput("Please Input Teacher Age: "));
                    String disciplineName = getUserInput("Please Input Teacher Discipline: ");
                    Discipline discipline = new Discipline(disciplineName);
                    Validator.validateDiscipline(discipline);
                    Teacher teacher = new Teacher(name,age,discipline);
                    this.controller.addTeacher(teacher);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                } catch (StringContainsNumbersException e){         //something is wrong here
                    System.out.println(e.getMessage());             //it says that the 2 exceptions are identical!?
                }
                break;
            }
            case "2" : {
                for (Teacher teacher : this.controller.getTeachers()){
                    System.out.println(teacher.toString());
                }
                break;
            }
            case "3" : {
                for (Teacher teacher : this.controller.getTeachers()){
                    System.out.println(teacher.toString());
                }
                try {
                    this.controller.removeTeacher(Integer.parseInt(getUserInput("Input position: ")));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds, please recheck the number of teachers!");              ///modificari!
                } catch (Exception e){
                    System.out.println("Unexpected error occurred.");
                }
                break;
            }
            case "4" : {
                this.showMenu();
                break;
            }
            default:
            {
                System.out.println("Please input a valid number!(a number between 1 and 4, to close the app please input 0)");
                break;
            }
        }
    }

    private void showMenu(){
        System.out.println("1. Students");
        System.out.println("2. Disciplines");
        System.out.println("3. Grades");
        System.out.println("4. Teachers");
        String userInput;
        userInput = scanner.nextLine();
        switch (userInput)
        {
            case "0" : {
                scanner.close();
                break;
            }
            case "1" : {
                this.showStudentSubMenus();
                break;
            }
            case "2" : {
                this.showDisciplineSubMenus();
                break;
            }
            case "3" : {
                this.showGradeSubMenus();
                break;
            }
            case "4" : {
                this.showTeacherSubMenus();
                break;
            }
            default : {
                System.out.println("Please input a valid number(a number between 1 and 4, to exit input 0)");
            }

        }
    }
}