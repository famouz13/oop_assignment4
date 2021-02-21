package com.company.data;

import com.company.data.interfaces.IDB;

import java.sql.*;

//database
public class PostgresDB implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        //path to database
        String connectionUrl = "jdbc:postgresql://localhost:5432/ITCompany";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, "postgres", "Famouz13");

        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }
}