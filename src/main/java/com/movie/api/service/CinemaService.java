package com.movie.api.service;

import com.movie.api.db.Database;
import com.movie.api.db.MovieDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CinemaService {

    public ArrayList<HashMap<String, String>> findAllCinemas() {
        return Database.selectAll("cinema");
    }

    public ArrayList<HashMap<String, String>> findCinemaById(String id) {
        return Database.selectById("cinema", id );
    }

    public ArrayList<HashMap<String, String>> findAllScreenings() {
        return Database.selectAll("screening");
    }

    public ArrayList<HashMap<String, String>> findScreeningById(String id) {
        return Database.selectById("screening", id );
    }

    public ArrayList<HashMap<String, String>> findReservationByScreening(String id) {
        return Database.getReservationsByScreening( id );
    }

}
