package com.example.shortenURL.backend.database;


import com.example.shortenURL.backend.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DBQueries {

    private static DBConnection dbConnection;

    @Autowired
    public DBQueries(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public static void saveUrl(ShortUrl shortUrl){
        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Urls (LongUrl, ShortUrl) VALUES (?, ?)");
            statement.setString(1, shortUrl.getLongURL());
            statement.setString(2, shortUrl.getShortenURL());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ShortUrl getLongUrl(String shortUrl) {
        ShortUrl matchedURL = new ShortUrl();

        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT LongUrl FROM Urls WHERE ShortUrl = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, shortUrl);

            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                throw new RuntimeException("URL does not exist");
            }

            while (resultSet.next()) {
                matchedURL.setLongURL(resultSet.getString("LongUrl"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchedURL;
    }

    public static String getExistingUrl(String longUrl) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "SELECT ShortUrl FROM Urls WHERE LongUrl = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, longUrl);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return null;
            }

            while (resultSet.next()) {
                return resultSet.getString("ShortUrl");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
