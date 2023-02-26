package com.company.repos;

import com.company.artist.Song;
import com.company.dbconfig.DbConfig;
import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SongRepo {


    public static void createTable() {

        String newTable =
                "CREATE TABLE IF NOT EXISTS songs " +
                        "(id varchar(30) PRIMARY KEY, " +
                        "name varchar(30), " +
                        "artist_id varchar(30) , " +
                        "album_id varchar(30) , " +
                        "released year," +
                        "length int, " +
                        "FOREIGN KEY(artist_id) REFERENCES bands(id) ON DELETE CASCADE," +
                        "FOREIGN KEY(album_id) REFERENCES albums(id) ON DELETE CASCADE)";

        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(newTable);
            AuditService.writeLog("songs TABLE created");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertSong(Song song) {

        String preparedSql = "{call insertSong(?,?,?,?,?,?)}";

        Connection connection = DbConfig.getDatabaseConnection();

        try (CallableStatement callableStatement = connection.prepareCall(preparedSql)) {

            callableStatement.setString(1, song.getSong_id());
            callableStatement.setString(2, song.getName());
            callableStatement.setString(3, song.getArtist().getArtist_id());
            callableStatement.setString(4, song.getAlbum().getAlbum_id());
            callableStatement.setInt(5, song.getRelease_date().getValue());
            callableStatement.setInt(6, song.getMin());

            callableStatement.execute();

            AuditService.writeLog("Data added into songs");

        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table songs");
            AuditService.writeLog("bands TABLE dropped");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
