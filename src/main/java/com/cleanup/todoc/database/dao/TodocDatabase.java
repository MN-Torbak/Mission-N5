package com.cleanup.todoc.database.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile TodocDatabase INSTANCE;

    // --- DAO ---
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    // --- INSTANCE ---
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "TodocDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // ---

    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues ProjectOne = new ContentValues();
                ProjectOne.put("id", 1L);
                ProjectOne.put("name", "Projet Tartampion");
                ProjectOne.put("color", 0xFFEADAD1);

                ContentValues ProjectTwo = new ContentValues();
                ProjectTwo.put("id", 2L);
                ProjectTwo.put("name", "Projet Lucidia");
                ProjectTwo.put("color", 0xFFB4CDBA);

                ContentValues ProjectThree = new ContentValues();
                ProjectThree.put("id", 3L);
                ProjectThree.put("name", "Projet Circus");
                ProjectThree.put("color", 0xFFA3CED2);

                db.insert("Project", OnConflictStrategy.IGNORE, ProjectOne);
                db.insert("Project", OnConflictStrategy.IGNORE, ProjectTwo);
                db.insert("Project", OnConflictStrategy.IGNORE, ProjectThree);
            }
        };
    }
}