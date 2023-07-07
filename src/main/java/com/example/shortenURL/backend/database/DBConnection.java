package com.example.shortenURL.backend.database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

    public Connection getConnection() throws SQLException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://aws.connect.psdb.cloud/project?sslMode=VERIFY_IDENTITY",
                "j0wowssijt19tgh5jwgo",
                "pscale_pw_7CQjHaRqOow2U9lj5PV1TKbXAExPBFi0zWJQxXNfDpW");

        return conn;
    }

}