package com.example.paul.androidstudentmanager.repository;

import com.example.paul.androidstudentmanager.model.Discipline;
import com.example.paul.androidstudentmanager.model.Grade;
import com.example.paul.androidstudentmanager.model.Student;
import com.example.paul.androidstudentmanager.model.Teacher;

import java.util.ArrayList;
import java.util.Collections;

public class Repository {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Discipline> disciplines = new ArrayList<>();
    private ArrayList<Grade> grades = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();
    //Singleton
    private static Repository instance = null;
    protected Repository() {
        // Exists only to defeat instantiation.
//        this.loadStudentsFromFile();
//        this.loadDisciplinesFromFile();
//        this.loadTeachersFromFile();
//        this.loadGradesFromFile();
    }
    public static Repository getInstance() {
        if(instance == null) {
            instance = new Repository();
        }
        return instance;
    }


//
//    //General writer
//    private void saveObjectsInFile(ArrayList<? extends FileSavingObject> objects, String fileName) throws Exception {
//        PrintWriter writer = null;
//        try {
//            writer = new PrintWriter(fileName, "UTF-8");
//            for (FileSavingObject object : objects) {
//                writer.println(object.stringForFileWriting());
//            }
//            writer.close();
//        } catch (Exception e) {
//            System.out.println("Saving error!");
//        }
//
//    }
//
//    //File reading
//    private void loadStudentsFromFile(){
//        try {
//            FileReader fileReader = new FileReader("Students_list.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
//            while (line != null){
//                String[] studentComponents = line.split(";");
//                Student student = new Student(studentComponents[0],studentComponents[1],Integer.parseInt(studentComponents[2]));
//                this.students.add(student);
//                line = bufferedReader.readLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadDisciplinesFromFile(){
//        try {
//            FileReader fileReader = new FileReader("Disciplines_list.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
//            while (line != null){
//                String disciplineName = line;
//                Discipline discipline = new Discipline(disciplineName);
//                this.disciplines.add(discipline);
//                line = bufferedReader.readLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadTeachersFromFile(){
//        try {
//            FileReader fileReader = new FileReader("Teachers_list.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
//            while (line != null){
//                String[] teacherComponents = line.split(";");
//                Discipline discipline = null;
//                for (Discipline existingDiscipline : this.disciplines){
//                    if(existingDiscipline.stringForFileWriting().equals(teacherComponents[2])){
//                        discipline = existingDiscipline;
//                        break;
//                    }
//                }
//                if(discipline == null){
//                    discipline = new Discipline(teacherComponents[2]);
//                    this.addDiscipline(discipline);
//                }
//                Teacher teacher = new Teacher(teacherComponents[0],Integer.parseInt(teacherComponents[1]),discipline);
//                this.teachers.add(teacher);
//                line = bufferedReader.readLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadGradesFromFile(){
//        try {
//            FileReader fileReader = new FileReader("Grades_list.txt");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();                             //value+discipline+student
//            while (line != null){
//                String[] gradeComponents = line.split(";");
//                Discipline discipline = null;
//                for (Discipline existingDiscipline : this.disciplines){
//                    if(existingDiscipline.stringForFileWriting().equals(gradeComponents[1])){
//                        discipline = existingDiscipline;
//                        break;
//                    }
//                }
//                if(discipline == null){
//                    discipline = new Discipline(gradeComponents[1]);
//                    this.addDiscipline(discipline);
//                }
//                Student student = null;
//                Student fileStudent = new Student(gradeComponents[2],gradeComponents[3],Integer.parseInt(gradeComponents[4]));
//                for(Student existingStudent : this.students){
//                    if(existingStudent.equals(fileStudent)){
//                        student = existingStudent;
//                        break;
//                    }
//                }
//                if(student == null){
//                    student = new Student(gradeComponents[2],gradeComponents[3],Integer.parseInt(gradeComponents[4]));
//                    this.addStudent(student);
//                }
//                Grade grade = new Grade(Integer.parseInt(gradeComponents[0]),discipline,student);
//                this.grades.add(grade);
//                line = bufferedReader.readLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //Add/Remove/Read operations
    public void addStudent(Student student) {
        this.students.add(student);
//        try {
//            this.saveObjectsInFile(this.students, "Students_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public void addDiscipline(Discipline discipline){
        disciplines.add(discipline);
//        try {
//            this.saveObjectsInFile(this.disciplines, "Disciplines_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void giveGrade (Grade grade){
        grades.add(grade);
//        try {
//            this.saveObjectsInFile(this.grades, "Grades_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void addTeacher (Teacher teacher){
        teachers.add(teacher);
//        try {
//            this.saveObjectsInFile(this.teachers, "Teachers_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public ArrayList<Discipline> getDisciplines() {
        return disciplines;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public ArrayList<Grade> getGrades() {return this.grades; }

    public ArrayList<Teacher> getTeachers() {
        return this.teachers;
    }

    public Student getStudentFromPosition(int posStud){
        return  students.get(posStud);
    }

    public Discipline getDisciplineFromPosition(int posDisc){
        return  disciplines.get(posDisc);
    }

    public void removeStudentFromPosition (int pos){
        students.remove(pos);
//        try {
//            saveObjectsInFile(students,"Students_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void removeDisciplineFromPosition (int pos){
        disciplines.remove(pos);
//        try {
//            saveObjectsInFile(disciplines,"Disciplines_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void removeGradeFromPosition (int pos){
        grades.remove(pos);
//        try {
//            saveObjectsInFile(grades,"Grades_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void removeTeacher(int posT){
        teachers.remove(posT);
//        try {
//            saveObjectsInFile(teachers,"Teachers_list.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void sortByFirstName(){
        try {
            Collections.sort(students, Student.StuNameComparator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}