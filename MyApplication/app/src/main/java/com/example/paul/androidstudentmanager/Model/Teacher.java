package com.example.paul.androidstudentmanager.model;
public class Teacher extends Object implements FileSavingObject {
    private String name;
    private int age;
    private Discipline discipline;

    public Teacher(String name, int age, Discipline discipline) {
        this.name = name;
        this.age = age;
        this.discipline = discipline;
    }
    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public Discipline getDiscipline(){ return this.discipline; }

    @Override
    public String toString() {
        return "Teacher name: " + this.getName() + "\n" + "Theacher age: " + this.getAge() + "\n" + this.getDiscipline() + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (age != teacher.age) return false;
        return name != null ? name.equals(teacher.name) : teacher.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String stringForFileWriting() {
        return this.name + ";" + this.age + ";" + this.discipline.stringForFileWriting();
    }
}
