package com.example.paul.studentbookandmore.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Paul on 10-Mar-17.
 */
@Entity
public class Discipline  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public Discipline() {
    }

    public Discipline(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;

    }
//
//    public String toNormalString(){
//        return this.name + " " + GradesManager.getInstance().getGradesAverageForDiscipline(this);
//    }

    public String toFileSave(){
        return name + ";" + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discipline that = (Discipline) o;

        return Objects.equals(name, that.name);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
