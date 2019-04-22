package com.nobodysbusiness.thechoremixer.chorePackage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Chore.class}, version = 1)
public abstract class ChoreDatabase extends RoomDatabase {
    public abstract ChoreDAO getChoreDAO();
}
