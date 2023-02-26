package com.company.repos;

import com.company.dbconfig.DbConfig;
import com.company.artist.Band;
import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.*;

public class BandRepo {

    public static void createTable() {

        String newTable =
                "CREATE TABLE IF NOT EXISTS bands " +
                "(id varchar(30) PRIMARY KEY, " +
                "name varchar(30), " +
                "members int, " +
                "genre varchar(30)," +
                "debut year, " +
                "label_id varchar (30), " +
                "status varchar(30)," +
                "FOREIGN KEY(label_id) REFERENCES labels(id) ON DELETE CASCADE)";

        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(newTable);
            AuditService.writeLog("bands TABLE created");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertBand(Band band) {

        String preparedSql = "{call insertBand(?,?,?,?,?,?,?)}";

        Connection connection = DbConfig.getDatabaseConnection();

        try (CallableStatement callableStatement = connection.prepareCall(preparedSql)) {

            callableStatement.setString(1, band.getArtist_id());
            callableStatement.setString(2, band.getName());
            callableStatement.setInt(3, band.getMembers());
            callableStatement.setString(4, band.getGenre().toString());
            callableStatement.setInt(5, band.getDebut().getValue());
            callableStatement.setString(6, band.getLabel().getLabel_id());
            callableStatement.setString(7, band.isStatus().toString());


            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void dropTable() {
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table bands CASCADE ");
            AuditService.writeLog("bands TABLE dropped");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
