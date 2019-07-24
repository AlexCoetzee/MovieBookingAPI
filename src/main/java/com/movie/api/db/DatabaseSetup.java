package com.movie.api.db;

import java.sql.Timestamp;
import java.sql.Date;

public class DatabaseSetup {

    public DatabaseSetup() {
        //Database setup
        Database.createNewDatabase("testDatabase.sqlite");
        Database.createTables();
    }
}
