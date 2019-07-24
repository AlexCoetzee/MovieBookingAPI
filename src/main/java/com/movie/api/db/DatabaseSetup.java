package com.movie.api.db;

import java.sql.Timestamp;
import java.sql.Date;

public class DatabaseSetup {

    public DatabaseSetup() {
        //Database setup
        Database.createNewDatabase("testDatabase.sqlite");
        Database.createTables();
        //example of inserting data into the database
//        Database.insertMovie("This is a movie", "this is the description");
//        Database.insertMovie("This is a movie2", "this is the description2");
//        Database.insertTheatre("Theatre 1", "The location that cannot be named");
//        Database.insertCinema("Cinema 1", 40, 1);
//        Database.insertSeat(3, 5, 1);
//        Date date =new Date(20000);
//        Timestamp timestamp = new Timestamp(date.getTime());
//        Database.insertScreening(1,timestamp,1);
//        Database.insertReservation(2,"Ronan");
//        Database.insertSeatReservation(1,1,1);
//        //Example of getting data from the database
//        System.out.println(Database.selectAll("movie"));
//        System.out.println(Database.selectAll("cinema"));
//        System.out.println(Database.selectAll("theatre"));
//        System.out.println(Database.selectAll("seat"));
//        System.out.println(Database.selectAll("screening"));
//        System.out.println(Database.selectAll("reservation"));
//        System.out.println(Database.selectAll("seat_reservation"));
    }
}
