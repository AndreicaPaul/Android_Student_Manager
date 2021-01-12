package com.example.paul.studentbookandmore.model;

import com.example.paul.studentbookandmore.business_logic.GradesManager;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Paul on 10-Mar-17.
 */

public class Discipline extends RealmObject implements Serializable {

    @PrimaryKey
    private String id;
    private String name;

    public Discipline() {
    }

    public Discipline(String name) {
        this.name = name;

        //this.details = details;
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

    public String toNormalString(){
        return this.name + " " + GradesManager.getInstance().getGradesAverageForDiscipline(this);
    }

    public String toFileSave(){
        return name + ";" + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discipline that = (Discipline) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
