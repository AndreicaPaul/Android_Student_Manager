package com.example.paul.studentbookandmore.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.paul.studentbookandmore.model.DAO.DisciplineDao;
import com.example.paul.studentbookandmore.model.Discipline;

@Database(entities = {Discipline.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DisciplineDao disciplineDao();
    private static AppDatabase instance;

    public static AppDatabase getDatabase(final Context context){
        if(instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
