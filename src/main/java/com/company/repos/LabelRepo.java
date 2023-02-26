package com.company.repos;

import com.company.label.Label;
import com.company.dbconfig.DbConfig;
import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LabelRepo {

    public static void createTable() {

        String newTable =
                "CREATE TABLE IF NOT EXISTS labels " +
                "(id varchar(30) PRIMARY KEY, " +
                "name varchar(30), " +
                "est year) ";
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(newTable);
            AuditService.writeLog("labels TABLE created");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertLabel(Label label) {

        String preparedSql = "{call insertLabel(?,?,?)}";

        Connection connection = DbConfig.getDatabaseConnection();

        try (CallableStatement callableStatement = connection.prepareCall(preparedSql)) {

            callableStatement.setString(1, label.getLabel_id());
            callableStatement.setString(2, label.getName());
            callableStatement.setInt(3, label.getEst().getValue());

            callableStatement.execute();

            AuditService.writeLog("Data added into labels");

        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table labels CASCADE ");
            AuditService.writeLog("bands TABLE dropped");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
