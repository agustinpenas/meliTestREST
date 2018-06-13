package com.h2.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;

import com.apenas.ws.rest.vo.Request;

import solarsystem.SolarSystem;
import solarsystem.Weather;

// H2 In-Memory Database Example shows about storing the database contents into memory. 

public class H2Database {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/db";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
	static final int DAYS_IN_YEAR=360;


    public static void main(String[] args) throws Exception {

		SolarSystem system = new SolarSystem();
		
		createTable();
		//We assume planets start aligned so no rain.

        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            
    		for(int day=0; day<DAYS_IN_YEAR*10;day++) {
    			Weather weather = system.weatherForToday();
    			stmt.execute("INSERT INTO WEATHER(day,weather) VALUES("+day+",'"+ weather.getName()+"' )");
    			
    			system.newDay();
    		}
    		
            ResultSet rs = stmt.executeQuery("select * from WEATHER");
            System.out.println("H2 In-Memory Database inserted through Statement");
            while (rs.next()) {
                System.out.println("day " + rs.getInt("day") + " weather " + rs.getString("weather"));
            }
    		
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

		
	}

    public static Request getweatherForDay(int day) throws SQLException {
    	Request request = new Request();
    	
        Connection connection = getDBConnection();
        Statement stmt = null;
    	
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from WEATHER where day="+day);
            rs.next();

            request.setDia(rs.getInt("day"));
            request.setClima(rs.getString("weather"));
            
            
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        
    	return request;
    }

    private static void createTable() throws SQLException {
    	DeleteDbFiles.execute("~", "db", true);
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            //stmt.execute("DROP TABLE WEATHER");
            stmt.execute("CREATE TABLE WEATHER(day int primary key, weather varchar(255))");

            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}