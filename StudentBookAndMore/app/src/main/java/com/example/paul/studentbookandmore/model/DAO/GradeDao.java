package com.example.paul.studentbookandmore.model.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.paul.studentbookandmore.model.Grade;

import java.util.List;

@Dao
public interface GradeDao {
    @Insert
    void insert(Grade grade);

    @Delete
    void deleteGrade(Grade... grades);

    @Query("SELECT * FROM GRADE")
    LiveData<List<Grade>> getAllGrades();

    @Query("DELETE FROM Grade")
    void deleteAll();

    @Query("SELECT * FROM Grade WHERE correspondingDisciplineID = :disciplineID")
    public LiveData<List<Grade>> getGradesForDiscipline(int disciplineID);
}
