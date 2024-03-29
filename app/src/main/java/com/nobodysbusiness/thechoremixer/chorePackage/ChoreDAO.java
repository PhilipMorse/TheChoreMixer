package com.nobodysbusiness.thechoremixer.chorePackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ChoreDAO {
    @Insert
    public void insertSingle(Chore... chores);

    @Update
    public void update(Chore... chores);

    @Delete
    public void delete(Chore... chores);

    @Query("SELECT * FROM chore")
    public List<Chore> getAllChores();

    @Query("SELECT * FROM chore ORDER BY RANDOM() LIMIT 1")
    public Chore[] getRandomChore();


}
