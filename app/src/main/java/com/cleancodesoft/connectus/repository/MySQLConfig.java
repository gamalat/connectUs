package com.cleancodesoft.connectus.repository;

public class MySQLConfig implements DBConfig {
    static final String  SERVER_IP_ADDRESS  = "localhost";  // mysql server ip address
    static final int SERVER_PORT_NUMBER  = 3306; // server port number.
    //Database Name
    static final String DB_NAME =  "connectus";
    // JDBC Driver Name
    static final String JDBC_DRIVER= "com.mysql.jdbc.Driver";
    // JDBC Database Credentials
    public static final String JDBC_USER  = "root";
    public static final String JDBC_PASS  = "root";

    @Override
    public String getUsername() {
        return JDBC_USER;
    }

    @Override
    public String getPassword() {
        return JDBC_PASS;
    }

    @Override
    public String getDriver() {
        return this.JDBC_DRIVER;
    }

    @Override
    public String getConnectionString() {
        //Database URL
        return "jdbc:mysql://" + SERVER_IP_ADDRESS + ":" + SERVER_PORT_NUMBER + "/" + DB_NAME;

    }

}
