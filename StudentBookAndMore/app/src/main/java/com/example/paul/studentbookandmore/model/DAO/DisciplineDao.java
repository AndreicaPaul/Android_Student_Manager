package com.example.paul.studentbookandmore.model.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.paul.studentbookandmore.model.Discipline;

import java.util.List;

@Dao
public interface DisciplineDao {
    @Insert
    void insert(Discipline discipline);

    @Delete
    void deleteDiscipline(Discipline... disciplines);

    @Query("DELETE FROM Discipline")
    void deleleAll();

    @Query("SELECT * from Discipline ORDER BY name ASC")
    LiveData<List<Discipline>> getAllDisciplines();

    @Query("SELECT * FROM Discipline WHERE name LIKE :name ")
    public LiveData<List<Discipline>> findDiscipline(String name);
}
