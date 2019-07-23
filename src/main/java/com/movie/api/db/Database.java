package com.movie.api.db;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    static String url =  null;

    private static Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase(String fileName) {
        if(url == null) {
            url = "jdbc:sqlite:" + fileName;
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void createTables() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS movie (Id Integer PRIMARY KEY, movieName varchar(50) NOT NULL, movieDescription varchar(100) )";
        String sql2 = "CREATE TABLE IF NOT EXISTS cinema (Id Integer PRIMARY KEY, name varchar(50) NOT NULL, seatCount Integer NOT NULL, theatre Integer NOT NULL, FOREIGN KEY(theatre) REFERENCES theatre(Id)  )";
        String sql3 = "CREATE TABLE IF NOT EXISTS screening (Id Integer PRIMARY KEY, movie Integer NOT NULL, time TimeStamp NOT NULL, cinema Integer, FOREIGN KEY(movie) REFERENCES movie(Id), FOREIGN KEY(cinema) REFERENCES cinema(Id))";
        String sql4 = "CREATE TABLE IF NOT EXISTS reservation (Id Integer PRIMARY KEY, screening Integer NOT NULL, name varchar(50), FOREIGN KEY(screening) REFERENCES screening(Id) )";
        String sql5 = "CREATE TABLE IF NOT EXISTS seat_reservation (Id Integer PRIMARY KEY, seat Integer NOT NULL, reservation Integer NOT NULL, screening Integer NOT NULL, FOREIGN KEY(seat) REFERENCES seat(Id), FOREIGN KEY(reservation) REFERENCES reservation(Id), FOREIGN KEY(screening) REFERENCES screening(Id) )";
        String sql6 = "CREATE TABLE IF NOT EXISTS seat (Id Integer PRIMARY KEY, row Integer NOT NULL, number Integer NOT NULL, cinema Integer NOT NULL, FOREIGN KEY(cinema) REFERENCES cinema(Id) )";
        String sql7 = "CREATE TABLE IF NOT EXISTS theatre (Id Integer PRIMARY KEY, name varchar(50) NOT NULL, Location varchar(100) NOT NULL)";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            stmt.execute(sql5);
            stmt.execute(sql6);
            stmt.execute(sql7);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int insertMovie(String name, String description) {
        String sql = "INSERT INTO movie(movieName,movieDescription) VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public static int insertCinema(String name, int seatCount, int theatre ) {
        String sql = "INSERT INTO cinema(name,seatCount, theatre) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, seatCount);
            pstmt.setInt(3, theatre);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public static int insertTheatre(String name, String location) {
        String sql = "INSERT INTO theatre(name,location) VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public static int insertReservation( int screening, String name) {
        String sql = "INSERT INTO reservation(screening, name) VALUES(?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, screening);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public static int insertScreening(int movie, Timestamp time, int cinema) {
        String sql = "INSERT INTO screening(movie, time,cinema) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, movie);
            pstmt.setTimestamp(2, time);
            pstmt.setInt(3, cinema);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public static int insertSeat(int row, int number, int cinema) {
        String sql = "INSERT INTO seat(row,number, cinema) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, row);
            pstmt.setInt(2, number);
            pstmt.setInt(3, cinema);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    public static int insertSeatReservation(int seat, int reservation, int screening) {
        String sql = "INSERT INTO seat_reservation(seat, reservation, screening) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, seat);
            pstmt.setInt(2, reservation);
            pstmt.setInt(3, screening);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public static ArrayList<HashMap<String, String>> selectAll(String table){
        String sql = "SELECT * FROM " + table;
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
       // HashMap<String, String> resultSet = new HashMap<String, String>();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            ResultSetMetaData rsmd = rs.getMetaData();
            // loop through the result set
            while (rs.next()) {
                HashMap<String, String> resultSet = new HashMap<String, String>();

                for(int i = 1; i<= rsmd.getColumnCount(); i++) {
                    String column = rs.getString(i);
                    resultSet.put(rsmd.getColumnName(i),column);
                }
                rows.add(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    public static ArrayList<HashMap<String, String>> selectById(String table, String id){
        String sql = "SELECT * FROM " + table + " WHERE Id = " + id;
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> resultSet = new HashMap<String, String>();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            ResultSetMetaData rsmd = rs.getMetaData();
            // loop through the result set
            while (rs.next()) {
                HashMap<String, String> resultSet = new HashMap<String, String>();

                for(int i = 1; i<= rsmd.getColumnCount(); i++) {
                    String column = rs.getString(i);
                    resultSet.put(rsmd.getColumnName(i),column);
                }
                rows.add(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    public static void delete(String table, int id) {
        String sql = "DELETE FROM " + table + " WHERE Id = " + id;
        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<HashMap<String, String>> getReservationsByScreening(String id){
        String sql = "SELECT * FROM seat_reservation WHERE screening = " + id;
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> resultSet = new HashMap<String, String>();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            ResultSetMetaData rsmd = rs.getMetaData();
            // loop through the result set
            while (rs.next()) {
                HashMap<String, String> resultSet = new HashMap<String, String>();

                for(int i = 1; i<= rsmd.getColumnCount(); i++) {
                    String column = rs.getString(i);
                    resultSet.put(rsmd.getColumnName(i),column);
                }
                rows.add(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    public static ArrayList<HashMap<String, String>> findSeatByCinema(String id){
        String sql = "SELECT * FROM seat WHERE cinema = " + id;
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> resultSet = new HashMap<String, String>();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            ResultSetMetaData rsmd = rs.getMetaData();
            // loop through the result set
            while (rs.next()) {
                HashMap<String, String> resultSet = new HashMap<String, String>();

                for(int i = 1; i<= rsmd.getColumnCount(); i++) {
                    String column = rs.getString(i);
                    resultSet.put(rsmd.getColumnName(i),column);
                }
                rows.add(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    public static boolean checkSeatOccupied(int id) {
        String sql = "SELECT * FROM seat_reservation WHERE seat = " + id;
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> resultSet = new HashMap<String, String>();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            ResultSetMetaData rsmd = rs.getMetaData();
            // loop through the result set
            while (rs.next()) {
                HashMap<String, String> resultSet = new HashMap<String, String>();

                for(int i = 1; i<= rsmd.getColumnCount(); i++) {
                    String column = rs.getString(i);
                    resultSet.put(rsmd.getColumnName(i),column);
                }
                rows.add(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows.size() == 1 ? true : false;
    }
    public static ArrayList<HashMap<String, String>> findMovieByTheatre(String id){
        String sql = "SELECT movie FROM screening WHERE cinema in (SELECT Id from cinema WHERE theatre = " + id + " )";
        ArrayList<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
        // HashMap<String, String> resultSet = new HashMap<String, String>();

        try (Connection conn = connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            ResultSetMetaData rsmd = rs.getMetaData();
            // loop through the result set
            while (rs.next()) {
                HashMap<String, String> resultSet = new HashMap<String, String>();

                for(int i = 1; i<= rsmd.getColumnCount(); i++) {
                    String column = rs.getString(i);
                    resultSet.put(rsmd.getColumnName(i),column);
                }
                rows.add(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rows;
    }

    public static int bookSeat(int screening,String name,int seatId) {
        try {
            int reservationId = insertReservation(screening,name);
            int seat_reservationId = insertSeatReservation(seatId,reservationId,screening);
            return seat_reservationId;

        }
        catch (Exception e) {
            //
        }
        return 0;
    }

    public static void deleteBooking(int id) {
        try {
            ArrayList<HashMap<String, String>> reservation = selectById("seat_reservation", String.valueOf(id));
            delete("seat_reservation", id);
            String _id = reservation.get(0).get("Id");
            delete("reservation", Integer.valueOf(_id));
        } catch(Exception e) {

        }
    }



}
