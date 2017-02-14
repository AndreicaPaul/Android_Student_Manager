package com.example.paul.androidstudentmanager.model;
import java.util.Comparator;
public class Student implements FileSavingObject { //Use own superclass
    private String firstName;
    private String lastName;
    private int age;


    public Student(String firstName, String lastName, int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (age != student.age) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;
        return lastName != null ? lastName.equals(student.lastName) : student.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    public static Comparator<Student> StuNameComparator = new Comparator<Student>() {

        public int compare(Student s1, Student s2) {
            String StudentName1 = s1.getFirstName().toUpperCase();
            String StudentName2 = s2.getFirstName().toUpperCase();

            //ascending order
            return StudentName1.compareTo(StudentName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };

    @Override
    public String stringForFileWriting() {
        return this.firstName + ";" + this.lastName + ";" + this.age;
    }

    //    @Override
//    public int compare(Student s1, Student s2) {
//        if(s1.getFirstName().equals(s2.getFirstName()) &&
//                s1.getLastName().equals(s2.getLastName()) &&
//                s1.getAge() == s2.getAge()) {
//            return 0;
//        }
//        else if(s1.getAge() < s2.getAge()){
//            return 1;
//        }
//        else
//            return -1;
//
//    }
}
