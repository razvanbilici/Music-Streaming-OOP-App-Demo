package com.company.repos;

import com.company.artist.Album;
import com.company.dbconfig.DbConfig;
import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AlbumRepo {

    public static void createTable() {

        String newTable =
                "CREATE TABLE IF NOT EXISTS albums " +
                        "(id varchar(30) PRIMARY KEY, " +
                        "name varchar(30), " +
                        "artist_id varchar(30) , " +
                        "released year, " +
                        "genre varchar(30)," +
                        "FOREIGN KEY(artist_id) REFERENCES bands(id) ON DELETE CASCADE)";

        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(newTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertAlbum(Album album) {

        String preparedSql = "{call insertAlbum(?,?,?,?,?)}";

        Connection connection = DbConfig.getDatabaseConnection();

        try (CallableStatement callableStatement = connection.prepareCall(preparedSql)) {

            callableStatement.setString(1, album.getAlbum_id());
            callableStatement.setString(2, album.getName());
            callableStatement.setString(3, album.getArtist().getArtist_id());
            callableStatement.setInt(4, album.getRelease_date().getValue());
            callableStatement.setString(5, album.getGenre().toString());

            callableStatement.execute();

            AuditService.writeLog("Data added into albums");

        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table albums CASCADE ");
            AuditService.writeLog("bands TABLE dropped");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
