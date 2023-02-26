package com.company.repos;

import com.company.dbconfig.DbConfig;
import com.company.services.AuditService;
import com.company.user.User;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepo {

    public static void createTable() {

        String newTable =
                "CREATE TABLE IF NOT EXISTS users " +
                        "(username varchar(30) PRIMARY KEY, " +
                        "email varchar(30), " +
                        "password varchar(30), " +
                        "reg_date year)";
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(newTable);
            AuditService.writeLog("users TABLE created");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(User user) {

        String preparedSql = "{call insertUser(?,?,?,?)}";

        Connection connection = DbConfig.getDatabaseConnection();

        try (CallableStatement callableStatement = connection.prepareCall(preparedSql)) {

            callableStatement.setString(1, user.getUsername());
            callableStatement.setString(2, user.getEmail());
            callableStatement.setString(3, user.getPassword());
            callableStatement.setInt(4, user.getYearOfReg().getValue());

            callableStatement.execute();

            AuditService.writeLog("Data added into users");

        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        Connection connection = DbConfig.getDatabaseConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table users CASCADE ");
            AuditService.writeLog("users TABLE dropped");
        } catch (SQLException | UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
