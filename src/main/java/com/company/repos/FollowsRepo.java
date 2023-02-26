package com.company.repos;

import com.company.artist.Artist;
import com.company.dbconfig.DbConfig;
import com.company.services.AuditService;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FollowsRepo {

    public static void createTable() {
        String newTable =
                "CREATE TABLE IF NOT EXISTS user_follows_artist " +
                        "(username varchar(30)," +
                        "artist_id varchar(30), " +
                        "PRIMARY KEY (username, artist_id)," +
                        "FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE," +
                        "FOREIGN KEY (artist_id) REFERENCES bands(id) ON DELETE CASCADE )";

        Connection connection = DbConfig.getDatabaseConnection();

            try (
        Statement statement = connection.createStatement()) {
            statement.execute(newTable);
            AuditService.writeLog("user_follows_artist TABLE created");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertFollow(User user, Artist artist) {

        String preparedSql = "{call insertFollow(?,?)}";

        Connection connection = DbConfig.getDatabaseConnection();

        try (CallableStatement callableStatement = connection.prepareCall(preparedSql)) {

            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, artist.getArtist_id());


            callableStatement.execute();

            AuditService.writeLog("Data added into users");

        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEntry(User user, Artist artist) {

        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {

            statement.execute("DELETE from user_follows_artist " +
            "WHERE username= '" + user.getUsername() + "' AND artist_id = '" + artist.getArtist_id() + "'");

            AuditService.writeLog("row dropped from FOLLOWS");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void dropTable() {
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table user_follows_artist");
            AuditService.writeLog("bands TABLE dropped");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
