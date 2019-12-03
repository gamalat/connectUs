package com.cleancodesoft.connectus.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBManager {

    DBConfig dBConfig;
    Connection connObj;

    public DBManager(DBConfig dBConfig1) {
        dBConfig = dBConfig1;
    }

    public boolean connect() {
        String JDBC_DRIVER = dBConfig.getDriver();
        String JDBC_DB_URL = dBConfig.getConnectionString();
        try {
            DriverManager.registerDriver((Driver) Class.forName(JDBC_DRIVER).newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register SQLDroidDriver");
        }
        try {
//            String JDBC_USER = dBConfig.getUsername();
//            String JDBC_PASS = dBConfig.getPassword();
//            connObj = DriverManager.getConnection(JDBC_DB_URL, JDBC_USER, JDBC_PASS);
            connObj = DriverManager.getConnection(JDBC_DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connObj != null){
                return true;
            }
        }
        return false;
    }

    public Connection getConnection() {
        return connObj;
    }

}
